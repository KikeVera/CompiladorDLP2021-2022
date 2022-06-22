package ast.expressions;


import ast.types.Type;
import semantic.Visitor;

public class Cast extends ExpressionNode{

    Expression expression;
    Type castType;

    public Cast(Expression expression, Type castType,int line,int column){
        super(line, column);
        this.expression=expression;
        this.castType=castType;
    }



    public Expression getExpression() {
        return expression;
    }

    public Type getCastType() {
        return castType;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "expression=" + expression +
                ", type=" + castType +
                '}';
    }

    public <TP, TR> TR accept(Visitor<TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }


}
