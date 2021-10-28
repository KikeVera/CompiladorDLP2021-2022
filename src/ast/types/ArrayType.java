package ast.types;

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
    public Type squareBrackets(Type array){
        if(array.equals(IntType.getInstance())){
            return this.getType();
        }

        return null;
    }

    public int numberOfBytes(){
        return type.numberOfBytes()*dimension;
    }

    public boolean isLvalue(){
        return true;
    }

    @Override
    public String toString() {
        return "ArrayType{" +
                "type=" + type +
                ", dimension=" + dimension +
                '}';
    }




    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
