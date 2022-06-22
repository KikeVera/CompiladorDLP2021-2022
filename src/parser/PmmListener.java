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



import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PmmParser}.
 */
public interface PmmListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PmmParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(PmmParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link PmmParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(PmmParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link PmmParser#mainDef}.
	 * @param ctx the parse tree
	 */
	void enterMainDef(PmmParser.MainDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link PmmParser#mainDef}.
	 * @param ctx the parse tree
	 */
	void exitMainDef(PmmParser.MainDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link PmmParser#definitions}.
	 * @param ctx the parse tree
	 */
	void enterDefinitions(PmmParser.DefinitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PmmParser#definitions}.
	 * @param ctx the parse tree
	 */
	void exitDefinitions(PmmParser.DefinitionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PmmParser#funcDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFuncDefinition(PmmParser.FuncDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PmmParser#funcDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFuncDefinition(PmmParser.FuncDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PmmParser#funcStatements}.
	 * @param ctx the parse tree
	 */
	void enterFuncStatements(PmmParser.FuncStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PmmParser#funcStatements}.
	 * @param ctx the parse tree
	 */
	void exitFuncStatements(PmmParser.FuncStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PmmParser#functionType}.
	 * @param ctx the parse tree
	 */
	void enterFunctionType(PmmParser.FunctionTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PmmParser#functionType}.
	 * @param ctx the parse tree
	 */
	void exitFunctionType(PmmParser.FunctionTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PmmParser#defParameters}.
	 * @param ctx the parse tree
	 */
	void enterDefParameters(PmmParser.DefParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link PmmParser#defParameters}.
	 * @param ctx the parse tree
	 */
	void exitDefParameters(PmmParser.DefParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link PmmParser#varDefinitions}.
	 * @param ctx the parse tree
	 */
	void enterVarDefinitions(PmmParser.VarDefinitionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PmmParser#varDefinitions}.
	 * @param ctx the parse tree
	 */
	void exitVarDefinitions(PmmParser.VarDefinitionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PmmParser#functionInvocation}.
	 * @param ctx the parse tree
	 */
	void enterFunctionInvocation(PmmParser.FunctionInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PmmParser#functionInvocation}.
	 * @param ctx the parse tree
	 */
	void exitFunctionInvocation(PmmParser.FunctionInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PmmParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(PmmParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PmmParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(PmmParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PmmParser#multiStatement}.
	 * @param ctx the parse tree
	 */
	void enterMultiStatement(PmmParser.MultiStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PmmParser#multiStatement}.
	 * @param ctx the parse tree
	 */
	void exitMultiStatement(PmmParser.MultiStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PmmParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(PmmParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PmmParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(PmmParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PmmParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(PmmParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PmmParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(PmmParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PmmParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(PmmParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PmmParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(PmmParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PmmParser#simpleType}.
	 * @param ctx the parse tree
	 */
	void enterSimpleType(PmmParser.SimpleTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PmmParser#simpleType}.
	 * @param ctx the parse tree
	 */
	void exitSimpleType(PmmParser.SimpleTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PmmParser#complexType}.
	 * @param ctx the parse tree
	 */
	void enterComplexType(PmmParser.ComplexTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PmmParser#complexType}.
	 * @param ctx the parse tree
	 */
	void exitComplexType(PmmParser.ComplexTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PmmParser#fields}.
	 * @param ctx the parse tree
	 */
	void enterFields(PmmParser.FieldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PmmParser#fields}.
	 * @param ctx the parse tree
	 */
	void exitFields(PmmParser.FieldsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PmmParser#ids}.
	 * @param ctx the parse tree
	 */
	void enterIds(PmmParser.IdsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PmmParser#ids}.
	 * @param ctx the parse tree
	 */
	void exitIds(PmmParser.IdsContext ctx);
}