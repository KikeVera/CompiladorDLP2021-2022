package ast.types;


import ast.ASTNode;
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
    public Type arithmetic(Type right, ASTNode campo){
        if(right.equals(DoubleType.getInstance()) || right instanceof ErrorType)
            return right;
        return super.arithmetic(right,campo);

    }

    @Override
    public Type arithmetic(ASTNode campo){
        return this;
    }


    @Override
    public Type comparison(Type right,ASTNode campo){
        if(right.equals(DoubleType.getInstance()) )
            return IntType.getInstance();
        return super.comparison(right,campo);
    }

    @Override
    public Type promotesTo(Type left,ASTNode campo){
        if(left.equals(DoubleType.getInstance())|| left instanceof ErrorType  )
            return left;

        return super.promotesTo(left,campo);
    }

    @Override
    public Type canBeCastTo(Type right,ASTNode campo){
        if(right.equals(IntType.getInstance()) || right.equals(DoubleType.getInstance()) || right.equals(CharType.getInstance()) )
            return right;
        return super.canBeCastTo(right,campo);
    }


    @Override
    public int numberOfBytes()  {
        return 4;
    }

    @Override
    public char getSuffix(){
        return 'f';
    }

    @Override
    public String toString() {
        return "DoubleType";
    }

    public <TP, TR> TR accept(Visitor<TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }


}
