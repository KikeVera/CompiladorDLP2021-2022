package ast;

import ast.types.Type;
import semantic.Visitor;

import java.util.Objects;


public class RecordField implements ASTNode {


    Type type;
    String name;
    int line;
    int column;
    int offset;

    public RecordField(Type type, String name,int line,int column) {
        this.type=type;

        this.name=name;
        this.line=line;
        this.column=column;
    }


    @Override
    public int getLine() {return line; }

    @Override
    public int getColumn() {
        return column;
    }



    public Type getType() {
        return type;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "RecordField{" +
                "type=" + type +
                ", name='" + name + '\'' +
                '}';
    }

    public <TP, TR> TR accept(Visitor <TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordField that = (RecordField) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
