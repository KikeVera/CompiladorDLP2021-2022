package ast.types;

import ast.RecordField;
import semantic.Visitor;

import java.util.List;

public class RecordType extends TypeNode {

    List<RecordField> fields;

    public RecordType(List<RecordField> fields,int line,int column){
        super(line, column);
        this.fields=fields;
    }

    public List<RecordField> getFields() {
        return fields;
    }

    @Override
    public Type dot(String campo){
        for(RecordField field:fields){
            if(field.getName().equals(campo)){
                return field.getType();
            }
        }

        return null;
    }

    public int numberOfBytes(){
        int cont=0;
        for(RecordField field: getFields()){
            cont+=field.getType().numberOfBytes();
        }
        return cont;
    }

    public boolean isLvalue(){
        return true;
    }


    @Override
    public String toString() {
        return "RecordType{" +
                "fields=" + fields +
                '}';
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
