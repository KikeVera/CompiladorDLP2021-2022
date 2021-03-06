package ast.expressions.literals;

import ast.expressions.ExpressionNode;
import semantic.Visitor;


public class IntLiteral extends ExpressionNode {

    int value;

    public IntLiteral(int value,int line,int column){
        super(line, column);
        this.value=value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "IntLiteral{" +
                "value=" + value +
                '}';
    }

    public <TP, TR> TR accept(Visitor<TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }


}
