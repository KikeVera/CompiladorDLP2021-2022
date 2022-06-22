package codeGenerator;

import ast.RecordField;
import ast.definitions.VarDefinition;
import ast.expressions.ArrayAccess;
import ast.expressions.FieldAccess;
import ast.expressions.Variable;
import ast.types.IntType;
import ast.types.RecordType;

public class AddressCGVisitor extends AbstractCGVisitor<Void,Void> {

    private CodeGenerator codeGenerator;
    private ValueCGVisitor valueCGVisitor;




    public void setResources (CodeGenerator codeGenerator, ValueCGVisitor valueCGVisitor){
        this.codeGenerator=codeGenerator;
        this.valueCGVisitor=valueCGVisitor;

    }



    /*
    address[[Variable : expression -> ID:String ]]()
        if(expression.definition.scope==0)
            <pusha> expression.definition.offset
        else{
            <push bp>
            <pushi> expression.definition.offset
            <addi>
         }
    */

    @Override
    public Void visit(Variable campo, Void param) {
        if(campo.getDefinition().getScope()==0)
           codeGenerator.pusha(((VarDefinition)campo.getDefinition()).getOffset());

        else{
            codeGenerator.pushBP();
            codeGenerator.push(((VarDefinition)campo.getDefinition()).getOffset());
            codeGenerator.add(IntType.getInstance());

        }

        return null;
    }

    /*
     address[[ArrayAccess : Expression -> array:Expression access:Expression ]]()

            address[[array]]()
            value[[access]]()
            <pushi> expression.type.numberOfBytes*
            <muli>
            <addi>

    */

    @Override
    public Void visit(ArrayAccess campo, Void param) {
        campo.getArray().accept(this,param);
        campo.getAccess().accept(this.valueCGVisitor,param);
        codeGenerator.push(campo.getType().numberOfBytes());
        codeGenerator.mul(IntType.getInstance());
        codeGenerator.add(IntType.getInstance());
        return null;

    }

    /*
     address[[FieldAccess : Expression -> left:Expression field:String ]]()

            address[[left]]()
            for(RecordField field:left.type.fields*){
                if(field.name.equals(field))
                    <pushi>field.offset
            }
            <addi>

    */

    @Override
    public Void visit(FieldAccess campo, Void param) {
        campo.getLeft().accept(this,param);

        for(RecordField field:((RecordType)campo.getLeft().getType()).getFields()){
                if(field.getName().equals(campo.getField())){
                    codeGenerator.push(field.getOffset());
                }
        }

        this.codeGenerator.add(IntType.getInstance());

        return null;
    }



}
