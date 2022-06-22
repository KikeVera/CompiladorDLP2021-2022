package ast.types;


import errorhandler.ErrorHandler;
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




    @Override
    public String toString() {
        return "ErrorType";
    }

    public <TP, TR> TR accept(Visitor <TP,TR> visitor, TP param) {
        return visitor.visit(this,param);
    }


}

