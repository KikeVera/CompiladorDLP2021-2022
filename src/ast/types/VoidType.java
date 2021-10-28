package ast.types;


import semantic.Visitor;

public class VoidType extends TypeNode {

    static VoidType instance;

    public static VoidType getInstance(){

        if(instance==null) {
            instance = new VoidType(0,0);
        }
        return instance;

    }

     private VoidType(int line,int column){
        super(line, column);
    }

    public int numberOfBytes(){
        return 0;
    }

    @Override
    public String toString() {
        return "VoidType";
    }

    @Override
    public Object accept(Visitor visitor, Object param) {
        return visitor.visit(this,param);
    }
}
