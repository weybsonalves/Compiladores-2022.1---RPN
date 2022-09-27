import java.io.File;
import java.util.Stack;
import java.util.List;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.charset.Charset;

public class Main { 
    public static void main (String [] args) {
        try {
            List<String> input = Files.readAllLines(new File("Calc1.stk").toPath(), Charset.forName("utf-8"));
            Stack<Float> stack = new Stack<Float>();
            
            for (int i = 0; i < input.size(); i++) {
                String element = input.get(i);
    
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
            
            System.out.println("O resultado de '" + String.join(" ", input) + "' eh " + stack.pop());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}