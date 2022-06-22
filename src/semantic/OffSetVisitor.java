package semantic;

import ast.RecordField;

import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;

import ast.statements.Statement;
import ast.types.FunctionType;
import ast.types.RecordType;


public class OffSetVisitor extends AbstractVisitor<Void,Void> {


    int offSetGlobal=0;
    int offSetLocal=0;

    @Override
    public Void visit(FuncDefinition campo, Void param) {
        offSetLocal=0;
        campo.getType().accept(this,param);
        for(Statement statement:campo.getStatements()){
            statement.accept(this,param);
        }

        campo.offSetLocal=-1*offSetLocal;
        return null;
    }



    @Override
    public Void visit(VarDefinition campo, Void param) {
        campo.getType().accept(this,param);
        if(campo.getScope()==0){
            campo.setOffset(offSetGlobal);
            offSetGlobal+=campo.getType().numberOfBytes();

        }

        else {
            offSetLocal-=campo.getType().numberOfBytes();
            campo.setOffset(offSetLocal);

        }



        return null;
    }

    @Override
    public Void visit(FunctionType campo, Void param) {
        campo.getReturnType().accept(this,null);

        int cont=4;

        for(int i=campo.getParams().size()-1;i>=0;i--) {
            campo.getParams().get(i).setOffset(cont);
            cont+=campo.getParams().get(i).getType().numberOfBytes();

        }

        return null;
    }

    @Override
    public Void visit(RecordType campo, Void param) {
        int cont=0;
        for(RecordField field: campo.getFields()){
            field.setOffset(cont);
            cont+=field.getType().numberOfBytes();
        }

        return null;
    }


}
