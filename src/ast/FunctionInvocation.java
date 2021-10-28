package ast;

import ast.statements.Statement;
import ast.expressions.Expression;
import ast.expressions.Variable;
import ast.types.Type;
import semantic.Visitor;

import java.util.List;

public class FunctionInvocation implements Expression, Statement {

    Variable function;
    List<Expression> params;
    int line;
    int column;
    boolean lValue;
    Type type;

    public FunctionInvocation(Variable function, List<Expression> params,int line,int column){
        this.function=function;
        this.params=params;
        this.line=line;
        this.column=column;
    }


    @Override
    public int getLine() {
        return line;
    }

    @Override
    public int getColumn() {
        return column;
    }

    public Variable getFunction() {
        return function;
    }

    public List<Expression> getParams() {
        return params;
    }

    @Override
    public String toString() {
        return "FunctionInvocation{" +
                "function=" + function +
                ", params=" + params +
                ", line=" + line +
                ", column=" + column +
                '}';
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }

    @Override
    public boolean getLValue() {
        return lValue;
    }

    @Override
    public void setLValue(boolean lValue) {
        this.lValue=lValue;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type=type;
    }
}
