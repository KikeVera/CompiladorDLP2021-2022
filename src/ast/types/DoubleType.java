package ast.types;

import errorHandler.ErrorType;
import semantic.Visitor;

public class DoubleType extends TypeNode {

    static DoubleType instance;

    public static DoubleType getInstance(){

        if(instance==null) {
            instance = new DoubleType(0,0);
        }
        return instance;

    }

    private DoubleType(int line,int column){
        super(line, column);
    }

    @Override
    public boolean isBuiltInType(){
        return true;
    }

    @Override
    public Type arithmetic(Type right){
        if(right.equals(DoubleType.getInstance()) || right instanceof ErrorType)
            return right;
        return null;

    }

    @Override
    public Type arithmetic(){
        return this;
    }

    @Override
    public Type comparison(Type right){
        if(right.equals(DoubleType.getInstance()) )
            return IntType.getInstance();
        return null;
    }

    @Override
    public Type promotesTo(Type left){
        if(left.equals(DoubleType.getInstance())|| left instanceof ErrorType  )
            return left;

        return null;
    }

    @Override
    public Type canBeCastTo(Type right){
        if(right.equals(IntType.getInstance()) || right.equals(DoubleType.getInstance()) || right.equals(CharType.getInstance()) )
            return right;
        return null;
    }

    public int numberOfBytes(){
        return 4;
    }

    public char getSuffix(){
        return 'f';
    }

    public boolean isLvalue(){
        return true;
    }


    @Override
    public String toString() {
        return "DoubleType";
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
