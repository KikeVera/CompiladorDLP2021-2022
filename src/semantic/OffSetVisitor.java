package semantic;

import ast.RecordField;
import ast.definitions.Definition;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.Variable;
import ast.statements.Statement;
import ast.types.FunctionType;
import ast.types.RecordType;
import errorHandler.ErrorType;
import semantic.symboltable.SymbolTable;

public class OffSetVisitor extends AbstractVisitor {

    SymbolTable symbolTable= new SymbolTable();
    int offSetGlobal=0;
    int offSetLocal=0;

    @Override
    public Object visit(FuncDefinition campo, Object param) {

       campo.getType().accept(this,param);
        for(Statement statement:campo.getStatements()){
            statement.accept(this,param);
        }
       offSetLocal=0;
        return defaultMethod();
    }



    @Override
    public Object visit(VarDefinition campo, Object param) {
        campo.getType().accept(this,param);
        if(campo.getScope()==0){
            campo.setOffset(offSetGlobal);
            offSetGlobal+=campo.getType().numberOfBytes();

        }

        else {
            offSetLocal-=campo.getType().numberOfBytes();
            campo.setOffset(offSetLocal);

        }



        return defaultMethod();
    }

    @Override
    public Object visit(FunctionType campo, Object param) {
        campo.getReturnType().accept(this,null);

        int cont=4;

        for(int i=campo.getParams().size()-1;i>=0;i--) {
            campo.getParams().get(i).setOffset(cont);
            cont+=campo.getParams().get(i).getType().numberOfBytes();

        }

        return defaultMethod();
    }

    @Override
    public Object visit(RecordType campo, Object param) {
        int cont=0;
        for(RecordField field: campo.getFields()){
            field.setOffset(cont);
            cont+=field.getType().numberOfBytes();
        }

        return defaultMethod();
    }


}
