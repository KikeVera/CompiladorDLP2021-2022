package codeGenerator;

import ast.FunctionInvocation;
import ast.expressions.*;
import ast.expressions.literals.CharLiteral;
import ast.expressions.literals.DoubleLiteral;
import ast.expressions.literals.IntLiteral;
import ast.expressions.operators.Arithmetic;
import ast.expressions.operators.Comparison;
import ast.expressions.operators.Logic;
import ast.expressions.unary.Negation;
import ast.expressions.unary.UnaryMinus;
import ast.types.CharType;
import ast.types.IntType;

public class ValueCGVisitor extends AbstractCGVisitor<Void,Void> {

    private CodeGenerator codeGenerator;
    private AddressCGVisitor addressCGVisitor;


    public void setResources(CodeGenerator codeGenerator,AddressCGVisitor addressCGVisitor){
        this.codeGenerator=codeGenerator;
        this.addressCGVisitor=addressCGVisitor;

    }

    /*
    value[[Variable : Expression -> ID:String ]]()
         address[[variable]]()
         <load> expression.type.suffix()

    */

    @Override
    public Void visit(Variable campo, Void param) {
        campo.accept(this.addressCGVisitor,param);
        codeGenerator.load(campo.getType());

        return null;
    }

    /*
    value[[CharLiteral : Expression -> value:char ]]()
         <pushb> value

    */

    @Override
    public Void visit(CharLiteral campo, Void param) {

        codeGenerator.push(campo.getValue());
        return null;
    }

    /*
    value[[DoubleLiteral : Expression -> value:double ]]()
         <pushf> value

    */

    @Override
    public Void visit(DoubleLiteral campo, Void param) {

        codeGenerator.push(campo.getValue());
        return null;
    }

    /*
    value[[IntLiteral : Expression -> value:int ]]()
         <pushi> value

    */

    @Override
    public Void visit(IntLiteral campo, Void param) {

         codeGenerator.push(campo.getValue());
         return null;
    }

    /*
    value[[Arithmetic : expression -> expressionDer:Expression expressionIzq:Expression operator:String ]]()
         value[[expressionDer]]()
         value[[expressionIzq]]()
         if(operator.equals("+"))
            <add> expression.type.suffix()
         if(operator.equals("-"))
            <sub> expression.type.suffix()
         if(operator.equals("*"))
            <mul> expression.type.suffix()
         if(operator.equals("/"))
            <div> expression.type.suffix()
         if(operator.equals("%"))
            <mod> expression.type.suffix()

    */

    @Override
    public Void visit(Arithmetic campo, Void param) {
        campo.getExpressionIzq().accept(this,null);
        if(campo.getExpressionIzq().getType().equals(CharType.getInstance()))
            codeGenerator.convert(CharType.getInstance(), IntType.getInstance());
        campo.getExpressionDer().accept(this,null);
        if(campo.getExpressionDer().getType().equals(CharType.getInstance()))
            codeGenerator.convert(CharType.getInstance(), IntType.getInstance());
        if(campo.getOperator().equals("+"))
            codeGenerator.add(campo.getType());
        if(campo.getOperator().equals("-"))
            codeGenerator.sub(campo.getType());
        if(campo.getOperator().equals("*"))
            codeGenerator.mul(campo.getType());
        if(campo.getOperator().equals("/"))
            codeGenerator.div(campo.getType());
        if(campo.getOperator().equals("%"))
            codeGenerator.mod(campo.getType());

        return null;
    }

    /*
    value[[Comparison : expression -> expressionDer:Expression expressionIzq:Expression operator:String ]]()
         value[[expressionDer]]()
         value[[expressionIzq]]()
         if(operator.equals(">"))
            <gt> expression.type.suffix()
         if(operator.equals(">="))
            <ge> expression.type.suffix()
         if(operator.equals("<"))
            <lt> expression.type.suffix()
         if(operator.equals("<="))
            <le> expression.type.suffix()
         if(operator.equals("!="))
            <ne> expression.type.suffix()
         if(operator.equals("=="))
            <eq> expression.type.suffix()

    */

    @Override
    public Void visit(Comparison campo, Void param) {
        campo.getExpressionIzq().accept(this,null);
        if(campo.getExpressionIzq().getType().equals(CharType.getInstance()))
            codeGenerator.convert(CharType.getInstance(), IntType.getInstance());
        campo.getExpressionDer().accept(this,null);
        if(campo.getExpressionDer().getType().equals(CharType.getInstance()))
            codeGenerator.convert(CharType.getInstance(), IntType.getInstance());

        if(campo.getOperator().equals(">"))
            codeGenerator.gt(campo.getType());
        if(campo.getOperator().equals(">="))
            codeGenerator.ge(campo.getType());
        if(campo.getOperator().equals("<"))
            codeGenerator.lt(campo.getType());
        if(campo.getOperator().equals("<="))
            codeGenerator.le(campo.getType());
        if(campo.getOperator().equals("!="))
            codeGenerator.ne(campo.getType());
        if(campo.getOperator().equals("=="))
            codeGenerator.eq(campo.getType());

        return null;
    }

    /*
    value[[Logic : expression -> expressionDer:Expression expressionIzq:Expression operator:String ]]()
         value[[expressionDer]]()
         value[[expressionIzq]]()
         if(operator.equals("&&"))
            <and>
         if(operator.equals("||"))
            <or>
    */

    @Override
    public Void visit(Logic campo, Void param) {
        campo.getExpressionIzq().accept(this,null);
        campo.getExpressionDer().accept(this,null);
        if(campo.getOperator().equals("&&"))
            codeGenerator.and();
        if(campo.getOperator().equals("||"))
            codeGenerator.or();


        return null;
    }

     /*
    value[[Negation : Expression -> expression:Expression ]]()
         value[[expression]]()
            <not>
    */

    @Override
    public Void visit(Negation campo, Void param) {
        campo.getExpression().accept(this,param);
        codeGenerator.not();
        return null;
    }

    /*
    value[[UnaryMinus : Expression -> expression:Expression ]]()

         value[[expression]]()
         <push> -1
         <mul> expression.type.suffix()


    */

    @Override
    public Void visit(UnaryMinus campo, Void param) {
        campo.getExpression().accept(this,param);
        this.codeGenerator.push(-1);
        this.codeGenerator.mul(campo.getType());
        return null;
    }

    /*
    value[[ArrayAccess : Expression -> array:Expression access:Expression ]]()
         address[[ArrayAccess]]()
         <load> expression.type.suffix()

    */

    @Override
    public Void visit(ArrayAccess campo, Void param) {
        campo.accept(addressCGVisitor,param);
        codeGenerator.load(campo.getType());
        return null;
    }

    /*
    value[[Cast : Expression -> expression:Expression castType:Type ]]()
         value[[expression]]()
         if(expression.type.equals(CharType)&&castType.equals(IntType))
            <b2i>
         if(expression.type.equals(CharType)&&castType.equals(DoubleType)){
            <b2i>
            <i2f>
         }
         if(expression.type.equals(IntType)&&castType.equals(CharType))
            <i2b>
         if(expression.type.equals(IntType)&&castType.equals(DoubleType))
            <i2f>
         if(expression.type.equals(DoubleType)&&castType.equals(IntType))
            <f2i>
         if(expression.type.equals(DoubleType)&&castType.equals(CharType)){
            <f2i>
            <i2f>
          }

    */

    @Override
    public Void visit(Cast campo, Void param) {
        campo.getExpression().accept(this,null);
        codeGenerator.convert(campo.getExpression().getType(),campo.getCastType());
        return null;
    }

    /*
    value[[FieldAccess : Expression -> left:Expression field:String ]]()
         address[[FieldAccess]]()
         <load> expression.type.suffix()

    */

    @Override
    public Void visit(FieldAccess campo, Void param) {
        campo.accept(this.addressCGVisitor,param);
        this.codeGenerator.load(campo.getType());
        return null;
    }

    /*
    value[[Functioninvocation : expression -> function:Expression expression* ]]()
        for(Expression arg: expression*)
            value[[arg]]()
        <call> function.name
    */

    @Override
    public Void visit(FunctionInvocation campo, Void param) {
       for(Expression arg:campo.getParams()){
           arg.accept(this,param);
       }
       this.codeGenerator.call(campo.getFunction().getName());
        return null;
    }
}
