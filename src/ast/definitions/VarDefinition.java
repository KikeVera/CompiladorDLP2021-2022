package ast.definitions;


import ast.statements.Statement;
import ast.types.Type;
import semantic.Visitor;


public class VarDefinition extends DefinitionNode implements Statement {

    int offset;


    public VarDefinition(Type type, String name,int line, int column){
        super(type, name, line, column);


    }


    public int getOffset() {

        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "VarDefinition{" +
                "offset=" + offset +
                '}';
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);

    }
}
