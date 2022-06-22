package ast.expressions.literals;

import ast.expressions.ExpressionNode;
import semantic.Visitor;


public class CharLiteral extends ExpressionNode {

    char value;

    public CharLiteral(char value,int line,int column){
        super(line, column);
        this.value=value;
    }



    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "CharLiteral{" +
                "value=" + value +
                '}';
    }

    public <TP, TR> TR accept(Visitor<TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }


}
