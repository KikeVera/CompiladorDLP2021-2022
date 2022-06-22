package codeGenerator;

import ast.definitions.VarDefinition;
import ast.types.*;

import java.io.IOException;
import java.io.PrintWriter;

public class CodeGenerator {

    private PrintWriter out;
    private int labels=0;

    public CodeGenerator(String output,String input){
        try {
            this.out = new PrintWriter(output);
        }
        catch(IOException e){
            System.err.println("Error al abrir el archivo");
        }

        this.source(input);

    }

    public void pushBP(){
        out.println("\tpusha\tbp");
        out.flush();
    }

    public void push(char valor){
        out.println("\tpushb\t"+(int)valor);
        out.flush();
    }

    public void push(int valor){
        out.println("\tpushi\t"+valor);
        out.flush();
    }

    public void push(double valor){
        out.println("\tpushf\t"+valor);
        out.flush();
    }

    public void pusha(int offset){
        out.println("\tpusha\t"+offset);
        out.flush();
    }

    public void load(Type type){
        out.println("\tload"+type.getSuffix());
        out.flush();
    }

    public void store(Type type){
        out.println("\tstore"+type.getSuffix());
        out.flush();
    }
    public void pop(Type type){
        out.println("\tpop"+type.getSuffix());
        out.flush();
    }

    public void dup(Type type){
        out.println("\tdup"+type.getSuffix());
        out.flush();
    }

    public void add(Type type){
        out.println("\tadd"+type.getSuffix());
        out.flush();
    }

    public void sub(Type type){
        out.println("\tsub"+type.getSuffix());
        out.flush();
    }

    public void mul(Type type){
        out.println("\tmul"+type.getSuffix());
        out.flush();
    }

    public void div(Type type){
        out.println("\tdiv"+type.getSuffix());
        out.flush();
    }

    public void mod(Type type){
        out.println("\tmod"+type.getSuffix());
        out.flush();
    }

    public void gt(Type type){
        out.println("\tgt"+type.getSuffix());
        out.flush();
    }

    public void lt(Type type){
        out.println("\tlt"+type.getSuffix());
        out.flush();
    }

    public void ge(Type type){
        out.println("\tge"+type.getSuffix());
        out.flush();
    }

    public void le(Type type){
        out.println("\tle"+type.getSuffix());
        out.flush();
    }

    public void eq(Type type){
        out.println("\teq"+type.getSuffix());
        out.flush();
    }

    public void ne(Type type){
        out.println("\tne"+type.getSuffix());
        out.flush();
    }

    public void and(){
        out.println("\tand");
        out.flush();
    }
    public void or(){
        out.println("\tor");
        out.flush();
    }

    public void not(){
        out.println("\tnot");
        out.flush();
    }

    public void out(Type type){
        out.println("\tout"+type.getSuffix());
        out.flush();
    }

    public void in(Type type){
        out.println("\tin"+type.getSuffix());
        out.flush();
    }

    public void convert(Type of,Type to){
        if(of.equals(CharType.getInstance())){
            if(to.equals(IntType.getInstance())){
                out.println("\tb2i");
            }

            else if(to.equals(DoubleType.getInstance())){
                out.println("\tb2i");
                out.println("\ti2f");
            }
        }
        if(of.equals(IntType.getInstance())){
            if(to.equals(CharType.getInstance())){
                out.println("\ti2b");
            }
            else if(to.equals(DoubleType.getInstance())){
                out.println("\ti2f");
            }
        }

        if(of.equals(DoubleType.getInstance())){
            if(to.equals(IntType.getInstance())){
                out.println("\tf2i");
            }

            else if(to.equals(CharType.getInstance())){
                out.println("\tf2i");
                out.println("\ti2n");
            }
        }

        out.flush();

    }

    public void halt(){
        out.println("halt");
        out.flush();
    }

    public void ret(FunctionType funcType,int nBytesLocalVariables){
        int returnBytes= funcType.getReturnType().numberOfBytes();
       int paramBytes=0;

       for(VarDefinition param:funcType.getParams()){
           paramBytes+=param.getType().numberOfBytes();
       }

        out.println("\tret\t"+returnBytes+", "+nBytesLocalVariables+", "+paramBytes);
        out.flush();
    }

    public void enter(int bytes){
        out.println("\tenter\t"+bytes);
        out.flush();
    }

    public void call(String func){
        out.println("\tcall\t"+func);
        out.flush();
    }

    public void defineLabel(String label){
        out.println(" "+label+":");
        out.flush();
    }

    public void jmp(String label){
        out.println("\tjmp\t"+label);
        out.flush();
    }

    public void jz(String label){
        out.println("\tjz\t"+label);
        out.flush();
    }

    public void jnz(String label){
        out.println("\tjnz\t"+label);
        out.flush();
    }

    public void callMain(){
        out.println("\n");
        this.comment("Invocation to the main function");
        out.println("call main");
        this.halt();
        out.println("\n\n");

    }

    public void comment(String comment){
        out.println("\t'"+comment);
        out.flush();
    }

    public void line(int line){
        out.println("\n#line\t"+line);
        out.flush();
    }

    private void source(String input){

        out.println("\n#source\t\""+input+"\"\n");
        out.flush();
    }

    public int getLabel(){
        int label=this.labels;
        this.labels++;
        return label;
    }


}
