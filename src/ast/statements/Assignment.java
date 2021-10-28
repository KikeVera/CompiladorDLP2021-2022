package ast.statements;

import ast.expressions.Expression;
import semantic.Visitor;

public class Assignment extends StatementNode {


    Expression expressionIzq;
    Expression expressionDer;

    public Assignment(Expression expressionIzq, Expression expressionDer,int line,int column){
        super(line, column);
        this.expressionIzq=expressionIzq;
        this.expressionDer=expressionDer;

    }




    public Expression getExpressionIzq() {
        return expressionIzq;
    }

    public Expression getExpressionDer() {
        return expressionDer;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "expressionIzq=" + expressionIzq +
                ", expressionDer=" + expressionDer +
                '}';
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
