package calc;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.lang.Math;

public class calculator {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Mathemcatical expression here: ");
        String num = "-5.78 + ( 4 - 2.23 ) + sin 0 * cos 1 / ( 1 + tan ( 2 * ln ( - 3 + 2 * ( 1.23 + 99 .111 ) ) ) )";
        String num2 = "cos 5 + m 10" ;
        String num3  = "n 96 + l 96";

        evalRPN(num3);               
    }

    //got infixToPostFix from rosettacode.org website
    //https://rosettacode.org/wiki/Parsing/Shunting-yard_algorithm#Java
    static String infixToPostfix(String infix) {
        /* To find out the precedence, we take the index of the
           token in the ops string and divide by 2 (rounding down). 
           This will give us: 0, 0, 1, 1, 2 */
        final String ops = "-+/*^ctsmoln";

        StringBuilder sb = new StringBuilder();
        Stack<Integer> s = new Stack<>();

        for (String token : infix.split("\\s")) {
            if (token.isEmpty()){
                continue;
            }
            char c = token.charAt(0);
            int idx = ops.indexOf(c);

            // check for operator
            if (idx != -1) {
                if (s.isEmpty())
                    s.push(idx);
          
                else {
                    while (!s.isEmpty()) {
                        int prec2 = s.peek() / 2;
                        int prec1 = idx / 2;
                        if (prec2 > prec1 || (prec2 == prec1 && c != '^'))
                            sb.append(ops.charAt(s.pop())).append(' ');
                        else break;
                    }
                    s.push(idx);
                }
            } 
            else if (c == '(' || c == '{') {
                s.push(-2); // -2 stands for '('
            } 
            else if (c == ')' || c == '}') {
                // until '(' on stack, pop operators.
                while (s.peek() != -2)
                    sb.append(ops.charAt(s.pop())).append(' ');
                s.pop();
            }
            else {
                sb.append(token).append(' ');
            }
        }
        while (!s.isEmpty()){
            if (s.peek() != -2){
                sb.append(ops.charAt(s.pop())).append(' ');
                continue;
            }
            return "Incorrect amount of Parenthesis, please check your paranthesis and try again.";
        }
        return sb.toString();
    }

    //got evalRPN from Rosetta Code website
    //https://rosettacode.org/wiki/Parsing/RPN_calculator_algorithm#Java_2
    private static void evalRPN(String expr){
        expr = infixToPostfix(expr);
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
            }else if (token.equals("c")){
                double operand = stack.pop();
                operand = Math.toRadians(operand);
                stack.push(Math.cos(operand));

            }else if (token.equals("s")){
                double operand = stack.pop();
                operand = Math.toRadians(operand);
                stack.push(Math.sin(operand));
            }else if (token.equals("t")){
                double operand = stack.pop();
                operand = Math.toRadians(operand);
                stack.push(Math.tan(operand));
            }else if (token.equals("m")){
                double operand = stack.pop();
                operand = operand * -1;
                stack.push(operand);
            } else if (token.equals("o")){
                double operand = stack.pop();
                operand = Math.toRadians(operand);
                stack.push(1 / Math.tan(operand));
            }else if (token.equals("n")){
                double operand = stack.pop();
                stack.push(Math.log(operand));
            }else if (token.equals("l")){
                double operand = stack.pop();
                stack.push(Math.log10(operand));
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
    }
}
