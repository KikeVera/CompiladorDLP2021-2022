package ast.types;

import errorHandler.ErrorType;
import semantic.Visitor;

public class CharType extends TypeNode {

    static CharType instance;

    public static CharType getInstance(){

        if(instance==null) {
            instance = new CharType(0,0);
        }
        return instance;

    }

    private CharType(int line, int column){

        super(line, column);
    }

    @Override
    public boolean isBuiltInType(){
        return true;
    }

    @Override
    public Type arithmetic(Type right){
        if(right.equals(CharType.getInstance()) || right instanceof ErrorType)
            return IntType.getInstance();
        if( right instanceof ErrorType)
            return right;
        return null;

    }

    @Override
    public Type arithmetic(){
        return IntType.getInstance();
    }


    @Override
    public Type comparison(Type right){
        if(right.equals(CharType.getInstance()) )
            return IntType.getInstance();
        return null;
    }

    @Override
    public Type promotesTo(Type left){
        if(left.equals(CharType.getInstance()) || left instanceof ErrorType  )
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
        return 1;
    }

    public char getSuffix(){
        return 'b';
    }

    public boolean isLvalue(){
        return true;
    }


    @Override
    public String toString() {
        return "CharType";
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
