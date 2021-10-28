package errorHandler;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ErrorHandler {

    static ErrorHandler instance;
    public List<ErrorType> errors = new ArrayList<>();

   public static ErrorHandler getInstance(){

       if(instance==null) {
           instance = new ErrorHandler();
       }
       return instance;

    }

    public void addError(ErrorType error){

        errors.add(error);
    }

    public boolean hasAnyErrors(){
       return !errors.isEmpty();

    }

    public void showErrors(PrintStream out){
       for(ErrorType error: errors){
           out.println(error.getMessage());
       }
    }
}
