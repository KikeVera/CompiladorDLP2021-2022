// Generated from C:/Users/UO246711/Desktop/DLPPracticas2022/src/parser\Pmm.g4 by ANTLR 4.9.2
package parser;

import ast.*;
import ast.expressions.*;
import ast.expressions.literals.*;
import ast.expressions.operators.*;
import ast.expressions.unary.*;
import ast.definitions.*;
import ast.statements.*;
import ast.types.*;



import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PmmParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PmmVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PmmParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(PmmParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link PmmParser#mainDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainDef(PmmParser.MainDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link PmmParser#definitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinitions(PmmParser.DefinitionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PmmParser#funcDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDefinition(PmmParser.FuncDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PmmParser#funcStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncStatements(PmmParser.FuncStatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PmmParser#functionType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionType(PmmParser.FunctionTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PmmParser#defParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefParameters(PmmParser.DefParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link PmmParser#varDefinitions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDefinitions(PmmParser.VarDefinitionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PmmParser#functionInvocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionInvocation(PmmParser.FunctionInvocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PmmParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(PmmParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PmmParser#multiStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiStatement(PmmParser.MultiStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PmmParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(PmmParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PmmParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(PmmParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PmmParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(PmmParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PmmParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleType(PmmParser.SimpleTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PmmParser#complexType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexType(PmmParser.ComplexTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PmmParser#fields}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFields(PmmParser.FieldsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PmmParser#ids}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIds(PmmParser.IdsContext ctx);
}