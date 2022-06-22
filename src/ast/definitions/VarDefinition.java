package ast.definitions;



import ast.statements.Statement;
import ast.types.Type;
import semantic.Visitor;

import java.util.Objects;


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
                "type=" + type +
                ", name='" + name + '\'' +
                '}';
    }

    public <TP, TR> TR accept(Visitor<TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
       VarDefinition that = (VarDefinition) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
