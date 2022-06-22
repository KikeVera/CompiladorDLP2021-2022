package codeGenerator;

import ast.FunctionInvocation;
import ast.Program;
import ast.RecordField;

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


public abstract class AbstractCGVisitor<TP,TR> implements Visitor<TP,TR> {



    @Override
    public TR visit(Program campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"Program\"");
    }
    @Override
    public TR visit(FuncDefinition campo, TP param) {

        throw new IllegalStateException("Generación de código no definida para \"FuncDefinition\"");
    }


    @Override
    public TR visit(VarDefinition campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"VarDefinition\"");
    }

    @Override
    public TR visit(CharLiteral campo, TP param) {

        throw new IllegalStateException("Generación de código no definida para \"CharLiteral\"");
    }

    @Override
    public TR visit(DoubleLiteral campo, TP param) {

        throw new IllegalStateException("Generación de código no definida para \"DoubleLiteral\"");
    }

    @Override
    public TR visit(IntLiteral campo, TP param) {

        throw new IllegalStateException("Generación de código no definida para \"IntLiteral\"");
    }

    @Override
    public TR visit(Arithmetic campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"Arithmetic\"");
    }

    @Override
    public TR visit(Comparison campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"Comparision\"");
    }

    @Override
    public TR visit(Logic campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"Logic\"");
    }

    @Override
    public TR visit(Negation campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"Negation\"");
    }

    @Override
    public TR visit(UnaryMinus campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"UnaryMinus\"");
    }

    @Override
    public TR visit(ArrayAccess campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"ArrayAcess\"");
    }

    @Override
    public TR visit(Cast campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"Cast\"");
    }

    @Override
    public TR visit(FieldAccess campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"FieldAcess\"");
    }

    @Override
    public TR visit(Variable campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"Variable\"");
    }

    @Override
    public TR visit(FunctionInvocation campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"FunctionInvocation\"");
    }

    @Override
    public TR visit(Assignment campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"Assignment\"");
    }

    @Override
    public TR visit(IfElse campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"IfElse\"");
    }

    @Override
    public TR visit(Input campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"Input\"");
    }

    @Override
    public TR visit(Print campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"Print\"");
    }

    @Override
    public TR visit(Return campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"Return\"");
    }

    @Override
    public TR visit(While campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"While\"");
    }

    @Override
    public TR visit(ArrayType campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"ArrayType\"");
    }

    @Override
    public TR visit(CharType campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"CharType\"");
    }

    @Override
    public TR visit(DoubleType campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"DoubleType\"");
    }

    @Override
    public TR visit(FunctionType campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"FunctionType\"");
    }

    @Override
    public TR visit(IntType campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"IntType\"");
    }

    @Override
    public TR visit(RecordType campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"RecordType\"");
    }

    @Override
    public TR visit(VoidType campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"VoidType\"");
    }

    @Override
    public TR visit(RecordField campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"RecordField\"");
    }

    @Override
    public TR visit(ErrorType campo, TP param) {
        throw new IllegalStateException("Generación de código no definida para \"ErrorType\"");
    }
}
