package ast.expressions.literals;

import ast.expressions.ExpressionNode;
import semantic.Visitor;


public class DoubleLiteral extends ExpressionNode {

    double value;

    public DoubleLiteral(double value,int line,int column){
        super(line, column);
        this.value=value;
    }



    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "DoubleLiteral{" +
                "value=" + value +
                '}';
    }

    public <TP, TR> TR accept(Visitor<TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }


}
