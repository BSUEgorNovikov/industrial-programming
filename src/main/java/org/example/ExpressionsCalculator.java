package org.example;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.List;
import java.util.Stack;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionsCalculator {
    public static double calculateExpression(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        int i = 0;

        while (i < expression.length()) {
            char ch = expression.charAt(i);
            if (ch == ' ') {
                i++;
                continue;
            } else if (Character.isDigit(ch) || ch == '.') {
                StringBuilder numBuilder = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    numBuilder.append(expression.charAt(i));
                    i++;
                }
                double number = Double.parseDouble(numBuilder.toString());
                numbers.push(number);
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operators.isEmpty() && !hasPrecedence(ch, operators.peek()) && !operators.peek().equals('(')) {
                    double operand2 = numbers.pop();
                    double operand1 = numbers.pop();
                    char op = operators.pop();
                    double result = performOperation(op, operand1, operand2);
                    numbers.push(result);
                }
                operators.push(ch);
                i++;
            } else if (ch == '(') {
                operators.push(ch);
                i++;
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    double operand2 = numbers.pop();
                    double operand1 = numbers.pop();
                    char op = operators.pop();
                    double result = performOperation(op, operand1, operand2);
                    numbers.push(result);
                }
                operators.pop();
                i++;
            } else {
                throw new IllegalArgumentException("Недопустимый символ: " + ch);
            }
        }

        while (!operators.isEmpty()) {
            double operand2 = numbers.pop();
            double operand1 = numbers.pop();
            char op = operators.pop();
            double result = performOperation(op, operand1, operand2);
            numbers.push(result);
        }

        return numbers.pop();
    }

    private static boolean hasPrecedence(char operator1, char operator2) {
        if ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-')) {
            return true;
        }
        return false;
    }

    private static double performOperation(char operator, double operand1, double operand2) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 == 0) {
                    throw new ArithmeticException("Деление на ноль!");
                }
                return operand1 / operand2;
        }
        throw new IllegalArgumentException("Неизвестный оператор: " + operator);
    }

    public Vector<Double> externalLibCalculateExpressions(List<String> gainData) {
        Vector<Double> resultVector = new Vector<>();

        for (int i = 0; i < gainData.size(); i++) {
            Expression expression = new ExpressionBuilder(gainData.get(i)).build();
            double result = expression.evaluate();
            resultVector.add(result);
        }

        return resultVector;
    }

    public Double regexCalculateExpression(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<String> operators = new Stack<>();

        int i = 0;
        while (i < expression.length()) {
            String ch = Character.toString(expression.charAt(i));
            if (ch.equals(" ")) {
                i++;
                continue;
            } else if (ch.matches("\\d+(\\.\\d+)?") || ch.equals(".")) {
                StringBuilder numBuilder = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    numBuilder.append(expression.charAt(i));
                    i++;
                }
                double number = Double.parseDouble(numBuilder.toString());
                numbers.push(number);
            } else if (ch.matches("[+\\-*/]")) {
                while (!operators.isEmpty() && !hasPrecedence(ch.charAt(0), operators.peek().charAt(0)) && !operators.peek().equals("(")) {
                    double operand2 = numbers.pop();
                    double operand1 = numbers.pop();
                    String op = operators.pop();
                    double result = performOperation(op.charAt(0), operand1, operand2);
                    numbers.push(result);
                }
                operators.push(ch);
                i++;
            } else if (ch.equals("(")) {
                operators.push(ch);
                i++;
            } else if (ch.equals(")")) {
                while (!operators.isEmpty() && operators.peek().charAt(0) != '(') {
                    double operand2 = numbers.pop();
                    double operand1 = numbers.pop();
                    String op = operators.pop();
                    double result = performOperation(op.charAt(0), operand1, operand2);
                    numbers.push(result);
                }
                operators.pop();
                i++;
            } else {
                throw new IllegalArgumentException("Недопустимый символ: " + ch);
            }
        }

        while (!operators.isEmpty()) {
            double operand2 = numbers.pop();
            double operand1 = numbers.pop();
            String op = operators.pop();
            double result = performOperation(op.charAt(0), operand1, operand2);
            numbers.push(result);
        }

        return numbers.pop();
    }
}
