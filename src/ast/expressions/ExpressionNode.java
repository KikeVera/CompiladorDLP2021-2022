package ast.expressions;

import ast.types.Type;

public abstract class ExpressionNode implements Expression {
    int line;
    int column;
    boolean lValue;
    Type type;

    public ExpressionNode(int line,int column){
        this.line=line;
        this.column=column;

    }

    public int getLine(){
        return line;

    }
    public int getColumn(){
        return column;
    }

    public boolean getLValue() {
        return lValue;
    }

    public void setLValue(boolean lValue) {
        this.lValue = lValue;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
