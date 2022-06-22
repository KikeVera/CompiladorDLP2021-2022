package ast.types;


import ast.ASTNode;
import ast.expressions.Expression;

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


    public boolean isBuiltInType(){return false;}


    public Type arithmetic(Type right, ASTNode campo){
        if(right instanceof ErrorType)
            return right;
        return  new ErrorType("Imposible realizar la operación entre un: "+this+
                " y un "+right,campo.getLine(),campo.getColumn());
    }

    public Type arithmetic(ASTNode campo){

        return  new ErrorType("Imposible realizar la operación con un: "
                +this,campo.getLine(),campo.getColumn());
    }


    public Type comparison(Type right,ASTNode campo){
        return new ErrorType("Imposible realizar la comparación entre un: "+this+
                " y un "+right,campo.getLine(),campo.getColumn());
    }


    public Type logic(Type right,ASTNode campo){
        return new ErrorType("Imposible realizar la operacion logica entre un: "+this+
                " y un "+right,campo.getLine(),campo.getColumn());
    }


    public Type logic(ASTNode campo){
        return  new ErrorType("Imposible realizar la operación lógica con un: "
                +this,campo.getLine(),campo.getColumn());

    }


    public Type promotesTo(Type left,ASTNode campo){
        return new ErrorType("Imposible realizar la promoción a un "+left+
                " de un "+this,campo.getLine(),campo.getColumn());
    }


    public Type canBeCastTo(Type right,ASTNode campo){
        return new ErrorType("Imposible realizar un cast: "+right+
                " en un "+this,campo.getLine(),campo.getColumn());
    }


    public Type squareBrackets(Type array,ASTNode campo){
        return new ErrorType("Imposible realizar el acceso al array",campo.getLine(),campo.getColumn());
    }


    public Type dot(String campo,ASTNode node){
        return new ErrorType("Imposible acceder al campo del struct: "+campo,node.getLine(),node.getColumn());
    }


    public Type parenthesis(List<Expression> list, ASTNode campo){
        return new ErrorType("Invocación a la función no válida: ",campo.getLine(),campo.getColumn());
    }


    public int numberOfBytes()  {
        throw new IllegalStateException("No se pueden obtener los bytes de este tipo: "+this);
    }

    public char getSuffix(){
        throw new IllegalStateException("No se pueden obtener el sufijo de este tipo: "+this);
    }



}
