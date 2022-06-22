package ast.types;


import ast.ASTNode;
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
    public Type arithmetic(Type right, ASTNode campo){
        if(right.equals(IntType.getInstance()) || right instanceof ErrorType)
            return right;
        return super.arithmetic(right,campo);

    }

    @Override
    public Type arithmetic(ASTNode campo){
        return this;
    }


    @Override
    public Type comparison(Type right,ASTNode campo){
        if(right.equals(IntType.getInstance()) )
            return IntType.getInstance();
        return super.comparison(right,campo);
    }

    @Override
    public Type promotesTo(Type left,ASTNode campo){
        if(left.equals(IntType.getInstance()) || left instanceof ErrorType  )
            return left;

        return super.promotesTo(left,campo);
    }

    @Override
    public Type logic(Type right,ASTNode campo){
        if(right.equals(IntType.getInstance()) || right instanceof ErrorType )
            return right;
        return super.logic(right,campo);
    }

    @Override
    public Type logic(ASTNode campo){
        return IntType.getInstance();

    }
    @Override
    public Type canBeCastTo(Type right,ASTNode campo){
        if(right.equals(IntType.getInstance()) || right.equals(DoubleType.getInstance()) || right.equals(CharType.getInstance()))
            return right;
        return super.canBeCastTo(right,campo);
    }

    @Override
    public int numberOfBytes()  {
        return 2;
    }

    @Override
    public char getSuffix(){
        return 'i';
    }


    @Override
    public String toString() {
        return "IntType";
    }

    public <TP, TR> TR accept(Visitor<TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }


}
