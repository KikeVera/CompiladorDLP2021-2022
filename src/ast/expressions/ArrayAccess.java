package ast.expressions;


import semantic.Visitor;

public class ArrayAccess extends ExpressionNode {

    Expression Array;
    Expression Access;

    public ArrayAccess(Expression Array, Expression Access,int line,int column){
        super(line, column);
        this.Array=Array;
        this.Access=Access;

    }



    public Expression getArray() {
        return Array;
    }

    public Expression getAccess() {
        return Access;
    }

    @Override
    public String toString() {
        return "ArrayAccess{" +
                "Array=" + Array +
                ", Access=" + Access +
                '}';
    }

    public <TP, TR> TR accept(Visitor<TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }


}
