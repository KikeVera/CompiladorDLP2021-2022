package ast.expressions.unary;

import ast.expressions.Expression;
import ast.expressions.ExpressionNode;

public abstract class UnaryExpression extends ExpressionNode {
    public Expression expression;



    public UnaryExpression( Expression expression,int line, int column){
        super(line,column);
        this.expression=expression;

    }

    public Expression getExpression() {
        return expression;
    }


}
