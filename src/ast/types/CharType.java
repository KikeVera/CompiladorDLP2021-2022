package ast.types;


import ast.ASTNode;
import semantic.Visitor;

public class CharType extends TypeNode {

    static CharType instance;

    public static CharType getInstance() {

        if (instance == null) {
            instance = new CharType(0, 0);
        }
        return instance;

    }

    private CharType(int line, int column) {

        super(line, column);
    }


    @Override
    public String toString() {
        return "CharType";
    }

    public <TP, TR> TR accept(Visitor<TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }

    @Override
    public boolean isBuiltInType(){
        return true;
    }

    @Override
    public Type arithmetic(Type right, ASTNode campo){
        if(right.equals(CharType.getInstance()))
            return IntType.getInstance();
        if( right instanceof ErrorType)
            return right;
        return super.arithmetic(right,campo);

    }

    @Override
    public Type arithmetic(ASTNode campo){
        return IntType.getInstance();
    }


    @Override
    public Type comparison(Type right,ASTNode campo){
        if(right.equals(CharType.getInstance()) )
            return IntType.getInstance();
        return super.comparison(right,campo);
    }

    @Override
    public Type promotesTo(Type left,ASTNode campo){
        if(left.equals(CharType.getInstance()) || left instanceof ErrorType  )
            return left;

        return super.promotesTo(left,campo);
    }

    @Override
    public Type canBeCastTo(Type right,ASTNode campo){
        if(right.equals(IntType.getInstance()) || right.equals(DoubleType.getInstance()) || right.equals(CharType.getInstance()))
            return right;
        return super.canBeCastTo(right,campo);
    }
    @Override
    public int numberOfBytes()  {
        return 1;
    }

    @Override
    public char getSuffix(){
        return 'b';
    }


}


