package codeGenerator;

import ast.FunctionInvocation;
import ast.Program;
import ast.definitions.Definition;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.Expression;
import ast.statements.*;
import ast.types.FunctionType;
import ast.types.VoidType;


public class ExecuteCGVisitor extends AbstractCGVisitor<FuncDefinition,Void> {

    private final CodeGenerator codeGenerator;
    private final AddressCGVisitor addressCGVisitor;
    private final ValueCGVisitor valueCGVisitor;






    public ExecuteCGVisitor(CodeGenerator codeGenerator){
        this.codeGenerator=codeGenerator;
        this.addressCGVisitor=new AddressCGVisitor();
        this.valueCGVisitor=new ValueCGVisitor();
        this.addressCGVisitor.setResources(this.codeGenerator,this.valueCGVisitor);
        this.valueCGVisitor.setResources(this.codeGenerator,this.addressCGVisitor);

    }


    /*
    execute[[Program : program -> definition*]]()
    for(Definition definition:definition*)
            if(definition instanceof VarDefinition)
                execute[[definition]]()
        <call main>
        <halt>
        for(Definition definition:definition*)
            if(definition instanceof FuncDefinition)
                execute[[definition]]()

    */

    @Override
    public Void visit(Program campo, FuncDefinition param) {
        for(Definition definition:campo.getDefinitions()){
            if(definition instanceof VarDefinition){
                definition.accept(this,param);
            }
        }

        codeGenerator.callMain();
        for(Definition definition:campo.getDefinitions()){
            if(definition instanceof FuncDefinition){

                definition.accept(this,param);
            }
        }

        return null;
    }

    /*
    execute[[Print : statement -> expression*]]()
    for(Expression expression:expression*){
        value[[expression]]()
        <out>expression.type.suffix()
      }

    */

    @Override
    public Void visit(Print campo, FuncDefinition param) {

        for(Expression expression :campo.getExpressions()){
            codeGenerator.line(campo.getLine());
            codeGenerator.comment("* Print");
            expression.accept(this.valueCGVisitor,null);
            codeGenerator.out(expression.getType());

        }

        return null;
    }

    /*
    execute[[Input : statement -> expression*]]()
    for(Expression expression:expression*){
        address[[expression]]()
        <in> expression.type.suffix()
        <store> expression.type.suffix()
      }

    */

    @Override
    public Void visit(Input campo, FuncDefinition param) {
        codeGenerator.line(campo.getLine());
        codeGenerator.comment("* Input");
        for(Expression expression :campo.getExpressions()){
            expression.accept(this.addressCGVisitor,null);
            codeGenerator.in(expression.getType());
            codeGenerator.store(expression.getType());

        }

        return null;
    }


    /*
    execute[[Assignment : statement -> expressionDer:expression expressionIzq:expression ]]()
        address[[expressionIzq]]()
        value[[expressionDer]]()
        <store> expressionIzq.type.suffix()


    */

    @Override
    public Void visit(Assignment campo, FuncDefinition param) {
        codeGenerator.line(campo.getLine());
        codeGenerator.comment("* Assignment");
        campo.getExpressionIzq().accept(this.addressCGVisitor,null);
        campo.getExpressionDer().accept(this.valueCGVisitor,null);
        codeGenerator.store(campo.getExpressionIzq().getType());
        return null;
    }



    /*
    execute[[VarDefinition : Definition -> ID:String type:Type offset:int ]]()
        comentario

    */


    @Override
    public Void visit(VarDefinition campo, FuncDefinition param) {
        codeGenerator.comment("* "+campo.getType()+" "+campo.getName()+" (offset "+campo.getOffset()+")");


        return null;

    }

    /*
    execute[[FuncDefinition : Definition -> ID:String funcType:Type statement* ]]()
        label<:>
        execute[[funcType]]()
        for(Statement statement:statement*){
            if(statement instance of VarDefinition)
             execute[[statement]]
        }
        <enter> localVariablesBytes
        for(Statement statement:statement*){
            if(!(statement instance of VarDefinition))
              execute[[statement]]
        }

        if((functionType)campo.getType.returnType() instanceof VoidType)
            <ret> functionType, localVariablesBytes;
    */

    @Override
    public Void visit(FuncDefinition campo, FuncDefinition param) {
        codeGenerator.line(campo.getLine());
        codeGenerator.comment("* Function Definition");
        codeGenerator.defineLabel(campo.getName());
        codeGenerator.comment("* Parameters");
        campo.getType().accept(this,null);
        codeGenerator.comment("* LocalVariables");


        for(Statement statement: campo.getStatements()){
            if(statement instanceof  VarDefinition)
                statement.accept(this,param);

        }
        this.codeGenerator.enter(campo.offSetLocal);

        for(Statement statement: campo.getStatements()){
            if(!(statement instanceof  VarDefinition))
                statement.accept(this,campo);

        }




        this.codeGenerator.ret((FunctionType) campo.getType(),campo.offSetLocal );

        return null;

    }

    /*
    execute[[FunctionType : Type -> varDefinition* returnType:Type]]()

        for(VarDefinition varDefinition: varDefinition*){
            address[[varDefinition]]()
        }
    */

    @Override
    public Void visit(FunctionType campo, FuncDefinition param) {

        for(VarDefinition varDefinition:campo.getParams()){
            varDefinition.accept(this,param);


        }

        return null;
    }


     /*
    execute[[WhileStatement : statement -> expression statement*]]()
        int label=codeGenerator.getLabel();
        int end=codeGenerator.getLabel();
        <label>label<:>
        value[[expression]]()
        <jz label>end
        for(Statement statement: statement*)
            execute[[statement]]()
        <jmp label>label
        <label>end<:>
    */

    @Override
    public Void visit(While campo, FuncDefinition param) {
        codeGenerator.line(campo.getLine());
        codeGenerator.comment("* While");
        int label=codeGenerator.getLabel();
        int end=codeGenerator.getLabel();
        codeGenerator.defineLabel("label"+label);
        campo.getCondition().accept(valueCGVisitor,null);
        codeGenerator.jz("label"+end);
        for(Statement statement:campo.getWhileST()){
            statement.accept(this,param);
        }
        codeGenerator.jmp("label"+label);
        codeGenerator.defineLabel("label"+end);
        return null;

    }



    /*
    execute[[IfElse : statement -> expression statement1* statement2*]]()
        int label=codeGenerator.getLabel();
        int end=codeGenerator.getLabel();
        value[[expression]]()
        <jz label>label
        for(Statement statement: statement1*)
            execute[[statement]]()
        <jmp label>end
        <label> label <:>
        for(Statement statement: statement2*)
            execute[[statement]]()

        <label>end<:>
    */

    @Override
    public Void visit(IfElse campo, FuncDefinition param) {
        codeGenerator.line(campo.getLine());
        codeGenerator.comment("* IfElse");
        int label=codeGenerator.getLabel();
        int end=codeGenerator.getLabel();
        campo.getCondition().accept(valueCGVisitor,null);
        codeGenerator.jz("label"+label);
        for (Statement statement:campo.getIfSt()){
            statement.accept(this,param);
        }

        codeGenerator.jmp("label"+end);
        codeGenerator.defineLabel("label"+label);
        for (Statement statement:campo.getElseSt()){
            statement.accept(this,param);
        }
        codeGenerator.defineLabel("label"+end);

        return null;

    }

    /*
    value[[Functioninvocation : statement -> function:Expression expression* ]]()
       value[[(Expression)statement]](
       if(!((Expression)statement).type instanceof VoidType)
        <pop>((Expression)statement).type.suffix()
    */


    @Override
    public Void visit(FunctionInvocation campo, FuncDefinition param) {
        codeGenerator.line(campo.getLine());
        codeGenerator.comment("* Function Invocation");
        campo.accept(this.valueCGVisitor,null);
        if(!campo.getType().equals(VoidType.getInstance())){
            this.codeGenerator.pop(campo.getType());
        }

        return null;

    }


    /*
    value[[Return : statement -> expression ]]()
       value[[expression]]()
       ret expression.type.bytes, localVariablesBytes,paramBytes
    */

    @Override
    public Void visit(Return campo, FuncDefinition param) {
        codeGenerator.line(campo.getLine());
        codeGenerator.comment("* Return");
        campo.getExpression().accept(this.valueCGVisitor,null);



        return null;

    }




}
