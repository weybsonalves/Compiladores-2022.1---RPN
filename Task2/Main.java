import java.io.File;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import stacker.rpn.lexer.Token;
import stacker.rpn.lexer.TokenType;

public class Main { 
    public static void main (String [] args) {
        try {
            List<Token> tokenList = scan("Calc2.stk");
            Stack<Float> stack = new Stack<Float>();
            
            for (int i = 0; i < tokenList.size(); i++) {
                String element = tokenList.get(i).lexeme;

                if (!"*+-/".contains(element)) {
                    stack.add(Float.parseFloat(element));
                } else {
                    Float b = stack.pop();
                    Float a = stack.pop();
                    if (element.equals("+")) {
                        stack.add(a + b);
                    } else if (element.equals("-")) {
                        stack.add(a - b);
                    } else if (element.equals("*")) {
                        stack.add(a * b);
                    } else if (element.equals("/")) {
                        stack.add(a / b); 
                    }
                }
            }
            
            System.out.println("The result of the expression is " + stack.pop());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	private static List<Token> scan(String sourceFilePath) throws IOException {
        List<Token> tokens = new ArrayList<>();
        File input = new File(sourceFilePath);
        Scanner in = new Scanner(input);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.equals("+")) {
               Token token = new Token(TokenType.PLUS, line);
               tokens.add(token);
            } else if (line.equals("-")) {
                Token token = new Token(TokenType.PLUS, line);
                tokens.add(token);
            } else if (line.equals("*")) {
               Token token = new Token(TokenType.PLUS, line);
               tokens.add(token);
            } else if (line.equals("/")) {
                Token token = new Token(TokenType.PLUS, line);
                tokens.add(token);
            } else {
                try {
                    Double.parseDouble(line); 
                    Token token = new Token(TokenType.PLUS, line);
                    tokens.add(token);
                } catch (Exception e) {
                    System.err.println("Error: Unexpected character: " + line);
                    System.exit(1);
                }
            }
        }
        in.close();
        return tokens;
	}
}