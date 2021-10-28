
import codeGenerator.CodeGenerator;
import codeGenerator.ExecuteCGVisitor;
import errorHandler.ErrorHandler;
import parser.*;

import org.antlr.v4.runtime.*;

import ast.Program;
import introspector.model.IntrospectorModel;
import introspector.view.IntrospectorTree;
import semantic.OffSetVisitor;
import semantic.TypeCheckingVisitor;
import semantic.ScopeVisitor;

public class Main {
	
	public static void main(String... args) throws Exception {

		   if (args.length<1) {
		        System.err.println("Please, pass me the input file.");
		        return;
		    }

		 // create a lexer that feeds off of input CharStream

		CharStream input = CharStreams.fromFileName(args[0]);
		PmmLexer lexer = new PmmLexer(input);

		// create a parser that feeds off the tokens buffer
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		PmmParser parser = new PmmParser(tokens);
		Program ast = parser.program().ast;
		ast.accept(new ScopeVisitor(),null);
		ast.accept(new TypeCheckingVisitor(),null);

		if(ErrorHandler.getInstance().hasAnyErrors()){
			ErrorHandler.getInstance().showErrors(System.err);
		}


		else {
			// * The AST is shown
			ast.accept(new OffSetVisitor(),null);
			ast.accept(new ExecuteCGVisitor(new CodeGenerator(args[1],args[0])),null);
			IntrospectorModel model = new IntrospectorModel("Program", ast);
			new IntrospectorTree("Introspector", model);
		}

	}

}




