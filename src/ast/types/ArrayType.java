package ast.types;


import ast.ASTNode;
import semantic.Visitor;

public class ArrayType extends TypeNode {

    Type type;
    int dimension;

    public ArrayType(Type type, int dimension,int line,int column){
        super(line, column);
        this.type=type;
        this.dimension=dimension;
    }

    public Type getType() {
        return type;
    }
    public int getDimension() {
        return dimension;
    }

    @Override
    public Type squareBrackets(Type array, ASTNode campo){
        if(array.equals(IntType.getInstance())){
            return this.getType();
        }

        return super.squareBrackets(array,campo);
    }

    @Override
    public int numberOfBytes()  {
        return type.numberOfBytes()*dimension;
    }

    @Override
    public String toString() {
        return "ArrayType{" +
                "type=" + type +
                ", dimension=" + dimension +
                '}';
    }

    public <TP, TR> TR accept(Visitor<TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }

}
