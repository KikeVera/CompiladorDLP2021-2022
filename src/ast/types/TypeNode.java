package ast.types;

import ast.expressions.Expression;
import errorHandler.ErrorType;

import java.util.List;

public abstract class TypeNode implements Type {

    int line;
    int column;

    public TypeNode(int line, int column){
        this.line=line;
        this.column=column;
    }
    
    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean isLogical(){
        return false;
    }

    @Override
   public boolean isBuiltInType(){return false;}

    @Override
    public Type arithmetic(Type right){
        if(right instanceof ErrorType)
            return right;
        return null;
    }
    @Override
    public Type arithmetic(){

        return null;
    }

    @Override
    public Type comparison(Type right){
        return null;
    }

    @Override
    public Type logic(Type right){
        return null;
    }

    @Override
    public Type logic(){
        return null;

    }

    @Override
    public Type promotesTo(Type left){
        return null;
    }

    @Override
    public Type canBeCastTo(Type right){
        return null;
    }

    @Override
    public Type squareBrackets(Type array){
        return null;
    }

    @Override
    public Type dot(String campo){
        return null;
    }

    @Override
    public Type parenthesis(List<Expression> list){
        return null;
    }

    @Override
    public int numberOfBytes()  {
        return 0;
    }

   public char getSuffix(){
     throw new RuntimeException("Accediendo a un tipo no BuiltIn para obtener su sufijo");
    }

    public boolean isLvalue(){
        return false;
    }



}
