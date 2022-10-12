package stacker.rpn.lexer;

public class Regex {
	
	private static final String REGEX_NUM = "[0-9]+";
	private static final String REGEX_OP = "(\\+|\\-|\\*|\\/)";
	private static final String REGEX_PLUS = "\\+";
	private static final String REGEX_MINUS = "\\-";
	private static final String REGEX_STAR = "\\*";
	private static final String REGEX_SLASH = "\\/";

	public static boolean isNum(String token) {
		return token.matches(REGEX_NUM);
	}
	
	public static boolean isOP(String token) {
		return token.matches(REGEX_OP);
	}

	public static boolean isPlus(String token) {
		return token.matches(REGEX_PLUS);
	}

	public static boolean isMinus(String token) {
		return token.matches(REGEX_MINUS);
	}

	public static boolean isStar(String token) {
		return token.matches(REGEX_STAR);
	}

	public static boolean isSlash(String token) {
		return token.matches(REGEX_SLASH);
	}
	
	public static TokenType getOPType(String token) {
		if (isPlus(token)) {
			return TokenType.PLUS;
		}
		else if (isMinus(token)) {
			return TokenType.MINUS;
		}
		else if (isStar(token)) {
			return TokenType.STAR;
		}
		else if (isSlash(token)) {
			return TokenType.SLASH;
		}
		return null;
	}

}
