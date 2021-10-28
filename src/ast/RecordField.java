package ast;

import ast.types.Type;
import semantic.Visitor;

public class RecordField implements ASTNode {


    Type type;
    int offset;
    String name;
    int line;
    int column;

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
                ", offset=" + offset +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
