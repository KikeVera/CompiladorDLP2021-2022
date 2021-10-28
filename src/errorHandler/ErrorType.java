package errorHandler;

import ast.types.TypeNode;
import semantic.Visitor;

public class ErrorType extends TypeNode {
    String message;

    public ErrorType(String message,int line, int column){
        super(line,column);
        ErrorHandler.getInstance().addError(this);
        this.message=message;
    }


    public String getMessage(){
        return message +" line:"+this.getLine()+" column:"+this.getColumn();
    }

    public int numberOfBytes() {
        throw new RuntimeException("Accediendo al tamaño de un tipo error");

    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return null;
    }

    @Override
    public String toString() {
        return "ErrorType";
    }
}

