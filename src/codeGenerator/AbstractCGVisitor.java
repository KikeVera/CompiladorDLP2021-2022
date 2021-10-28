package codeGenerator;

import ast.FunctionInvocation;
import ast.Program;
import ast.RecordField;
import ast.definitions.Definition;
import ast.definitions.FuncDefinition;
import ast.definitions.VarDefinition;
import ast.expressions.*;
import ast.expressions.literals.CharLiteral;
import ast.expressions.literals.DoubleLiteral;
import ast.expressions.literals.IntLiteral;
import ast.expressions.operators.Arithmetic;
import ast.expressions.operators.Comparison;
import ast.expressions.operators.Logic;
import ast.expressions.unary.Negation;
import ast.expressions.unary.UnaryMinus;
import ast.statements.*;
import ast.types.*;
import semantic.Visitor;


public abstract class AbstractCGVisitor implements Visitor {



    @Override
    public Object visit(Program campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"Program\"");
    }
    @Override
    public Object visit(FuncDefinition campo, Object param) {

        throw new IllegalStateException("Generación de código no definida para \"FuncDefinition\"");
    }


    @Override
    public Object visit(VarDefinition campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"VarDefinition\"");
    }

    @Override
    public Object visit(CharLiteral campo, Object param) {

        throw new IllegalStateException("Generación de código no definida para \"CharLiteral\"");
    }

    @Override
    public Object visit(DoubleLiteral campo, Object param) {

        throw new IllegalStateException("Generación de código no definida para \"DoubleLiteral\"");
    }

    @Override
    public Object visit(IntLiteral campo, Object param) {

        throw new IllegalStateException("Generación de código no definida para \"IntLiteral\"");
    }

    @Override
    public Object visit(Arithmetic campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"Arithmetic\"");
    }

    @Override
    public Object visit(Comparison campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"Comparision\"");
    }

    @Override
    public Object visit(Logic campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"Logic\"");
    }

    @Override
    public Object visit(Negation campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"Negation\"");
    }

    @Override
    public Object visit(UnaryMinus campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"UnaryMinus\"");
    }

    @Override
    public Object visit(ArrayAccess campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"ArrayAcess\"");
    }

    @Override
    public Object visit(Cast campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"Cast\"");
    }

    @Override
    public Object visit(FieldAcess campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"FieldAcess\"");
    }

    @Override
    public Object visit(Variable campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"Variable\"");
    }

    @Override
    public Object visit(FunctionInvocation campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"FunctionInvocation\"");
    }

    @Override
    public Object visit(Assignment campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"Assignment\"");
    }

    @Override
    public Object visit(IfElse campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"IfElse\"");
    }

    @Override
    public Object visit(Input campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"Input\"");
    }

    @Override
    public Object visit(Print campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"Print\"");
    }

    @Override
    public Object visit(Return campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"Return\"");
    }

    @Override
    public Object visit(While campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"While\"");
    }

    @Override
    public Object visit(ArrayType campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"ArrayType\"");
    }

    @Override
    public Object visit(CharType campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"CharType\"");
    }

    @Override
    public Object visit(DoubleType campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"DoubleType\"");
    }

    @Override
    public Object visit(FunctionType campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"FunctionType\"");
    }

    @Override
    public Object visit(IntType campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"IntType\"");
    }

    @Override
    public Object visit(RecordType campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"RecordType\"");
    }

    @Override
    public Object visit(VoidType campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"VoidType\"");
    }

    @Override
    public Object visit(RecordField campo, Object param) {
        throw new IllegalStateException("Generación de código no definida para \"RecordField\"");
    }
}
