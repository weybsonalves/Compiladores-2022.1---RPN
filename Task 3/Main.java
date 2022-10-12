import java.io.File;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import stacker.rpn.lexer.Token;
import stacker.rpn.lexer.TokenType;
import stacker.rpn.lexer.Regex;

public class Main { 
    public static void main (String [] args) {
        try {
            List<Token> tokenList = scan("Calc3.stk");
            Stack<Float> stack = new Stack<Float>();
            
            for (int i = 0; i < tokenList.size(); i++) {
                String element = tokenList.get(i).lexeme;
                TokenType type = tokenList.get(i).type;

                if (type == TokenType.NUM) {
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
        
        Path input = Path.of(sourceFilePath);
        String str = Files.readString(input);
        StringTokenizer in = new StringTokenizer(str, "\t\r\n\f ");
        while (in.hasMoreTokens()) {
            String token = in.nextToken();
            if (Regex.isOP(token)) {
                tokens.add(new Token(Regex.getOPType(token), token));
            } else if (Regex.isNum(token)) {
                tokens.add(new Token(TokenType.NUM, token));
            } else {
                throw new Error("Error: Unexpected character: " + token);
            }
        }
        return tokens;
	}
}