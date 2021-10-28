package parser;

public class LexerHelper {
	
	public static int lexemeToInt(String str) {
		try {
			return Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1;
	}

	public static double lexemeToReal(String str) {
		try {
			return Double.parseDouble(str);
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1;
	}

	public static char lexemeToChar(String str) {
			if(str.length()==3) {
				return str.charAt(1);
			}

			String character=str.substring(1,str.length()-1);

			if(character.equals("\\n")){
				return '\n';
			}
			else if(character.equals("\\t")) {
				return '\t';
			}
			else{
				return (char) Integer.parseInt(character.substring(1));
			}


	}

	// TODO: Implement the lexemeToChar and lexemeToReal methods
	
}
