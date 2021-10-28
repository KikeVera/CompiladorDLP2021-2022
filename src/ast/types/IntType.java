package ast.types;

import errorHandler.ErrorType;
import semantic.Visitor;

public class IntType extends TypeNode {

    static IntType instance;

    public static IntType getInstance(){

        if(instance==null) {
            instance = new IntType(0,0);
        }
        return instance;

    }

    private IntType(int line,int column){
        super(line, column);
    }

    @Override
    public boolean isLogical() {
        return true;
    }

    @Override
    public boolean isBuiltInType(){
        return true;
    }

    @Override
    public Type arithmetic(Type right){
        if(right.equals(IntType.getInstance()) || right instanceof ErrorType)
            return right;
        return null;

    }

    @Override
    public Type arithmetic(){
        return this;
    }

    @Override
    public Type comparison(Type right){
        if(right.equals(IntType.getInstance()) )
            return IntType.getInstance();
        return null;
    }

    @Override
    public Type logic(Type right){
        if(right.equals(IntType.getInstance()) || right instanceof ErrorType )
            return right;
        return null;
    }

    @Override
    public Type logic(){
        return IntType.getInstance();

    }

    @Override
    public Type promotesTo(Type left){
        if(left.equals(IntType.getInstance()) || left instanceof ErrorType  )
            return left;

        return null;
    }

    @Override
    public Type canBeCastTo(Type right){
        if(right.equals(IntType.getInstance()) || right.equals(DoubleType.getInstance()) || right.equals(CharType.getInstance()))
            return right;
        return null;
    }



    public int numberOfBytes(){
        return 2;
    }

    public boolean isLvalue(){
        return true;
    }

    public char getSuffix(){
        return 'i';
    }



    @Override
    public String toString() {
        return "IntType";
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
