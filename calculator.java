package calc;
import java.util.LinkedList;
import java.util.Scanner;
import java.lang.Math;

public class calculator {
    public static void main(String[] args) {
<<<<<<< HEAD
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Mathemcatical expression here: ");
        String num = sc.nextLine();

        
        
    }
    //got evalRPN from Rosetta Code website
    //https://rosettacode.org/wiki/Parsing/RPN_calculator_algorithm#Java_2
    private static void evalRPN(String expr){
        LinkedList<Double> stack = new LinkedList<Double>();
        System.out.println("Input\tOperation\tStack after");
        for (String token : expr.split("\\s")){
            System.out.print(token + "\t");
            if (token.equals("*")) {
                System.out.print("Operate\t\t");
                double secondOperand = stack.pop();
                double firstOperand = stack.pop();
                stack.push(firstOperand * secondOperand);
            } else if (token.equals("/")) {
                System.out.print("Operate\t\t");
                double secondOperand = stack.pop();
                double firstOperand = stack.pop();
                stack.push(firstOperand / secondOperand);
            } else if (token.equals("-")) {
                System.out.print("Operate\t\t");
                double secondOperand = stack.pop();
                double firstOperand = stack.pop();
                stack.push(firstOperand - secondOperand);
            } else if (token.equals("+")) {
                System.out.print("Operate\t\t");
                double secondOperand = stack.pop();
                double firstOperand = stack.pop();
                stack.push(firstOperand + secondOperand);
            } else if (token.equals("^")) {
                System.out.print("Operate\t\t");
                double secondOperand = stack.pop();
                double firstOperand = stack.pop();
                stack.push(Math.pow(firstOperand, secondOperand));
            } else {
                System.out.print("Push\t\t");
                try {
                    stack.push(Double.parseDouble(token+""));
                } catch (NumberFormatException e) {
                        System.out.println("\nError: invalid token " + token);
                        return;
                }
            }
            System.out.println(stack);
        }
        if (stack.size() > 1) {
            System.out.println("Error, too many operands: " + stack);
            return;
        }
        System.out.println("Final answer: " + stack.pop());
=======
        System.out.println("hello world");

        System.out.println("test");
>>>>>>> c7f88964da72fa5e11fcb79234f142c7e8c5f618
    }
}
