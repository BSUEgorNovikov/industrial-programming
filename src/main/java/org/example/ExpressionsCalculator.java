package org.example;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.List;
import java.util.Stack;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionsCalculator {
    public Vector<Integer> calculateExpressions(List<String> gainData)
    {
        Vector<Integer> vectorOfResults = new Vector<>();
        int numberOfExpressions = gainData.size();

        for (int i = 0; i < numberOfExpressions; i++)
        {
            int currentNumber = 0;
            char currentOperator = '+';
            int stringLength = gainData.get(i).length();
            Stack<Integer> stackOfNumbers = new Stack<>();

            for (int j = 0; j < stringLength; j++)
            {
                char currentChar = gainData.get(i).charAt(j);

                if (Character.isDigit(currentChar))
                    currentNumber = (currentNumber * 10) + (currentChar - '0');

                if (!Character.isDigit(currentChar) && currentChar != ' ' || j == stringLength - 1)
                {
                    if (currentOperator == '+')
                    {
                        stackOfNumbers.push(currentNumber);
                    }
                    else if (currentOperator == '-')
                    {
                        stackOfNumbers.push(-currentNumber);
                    }
                    else if (currentOperator == '*')
                    {
                        stackOfNumbers.push(stackOfNumbers.pop() * currentNumber);
                    }
                    else if (currentOperator == '/')
                    {
                        stackOfNumbers.push(stackOfNumbers.pop() / currentNumber);
                    }

                    currentNumber = 0;
                    currentOperator = currentChar;
                }
            }

            int result = 0;
            while (!stackOfNumbers.isEmpty())
            {
                result += stackOfNumbers.pop();
            }

            vectorOfResults.add(result);
        }

        return vectorOfResults;
    }

    public Vector<Integer> externalLibCalculateExpressions(List<String> gainData) {
        Vector<Integer> resultVector = new Vector<>();

        for(int i = 0; i < gainData.size(); i++) {
            Expression expression = new ExpressionBuilder(gainData.get(i)).build();
            int result = (int)expression.evaluate();
            resultVector.add(result);
        }

        return resultVector;
    }

    public Vector<Integer> regexCalculateExpressions(List<String> gainData) {
        Vector<Integer> vectorOfResults = new Vector<>();
        Stack<Integer> stackOfNumbers = new Stack<>();
        int numberOfExpressions = gainData.size();

        Pattern numberPattern = Pattern.compile("\\d+(\\.\\d+)?");
        Pattern operatorPattern = Pattern.compile("[+\\-*/()]");
        for (int i = 0; i < numberOfExpressions; i++)
        {
            Vector<Integer> numbers = new Vector<>();
            Vector<Character> operators = new Vector<>();

            if(Character.isDigit(gainData.get(i).charAt(0))) {
                operators.add('+');
            }

            Matcher matcher = numberPattern.matcher(gainData.get(i));
            while (matcher.find()) {
                int operand = Integer.parseInt(matcher.group());
                numbers.add(operand);
            }
            matcher = operatorPattern.matcher(gainData.get(i));
            while (matcher.find()) {
                char operator = matcher.group().charAt(0);
                operators.add(operator);
            }

            for(int j = 0; j < numbers.size(); j++) {
                char currentOperator = operators.get(j);
                int currentNumber = numbers.get(j);

                switch (currentOperator) {
                    case '+': {
                        stackOfNumbers.push(currentNumber);
                        break;
                    }
                    case '-': {
                        stackOfNumbers.push(-currentNumber);
                        break;
                    }
                    case '*': {
                        stackOfNumbers.push(stackOfNumbers.pop() * currentNumber);
                        break;
                    }
                    case '/': {
                        stackOfNumbers.push(stackOfNumbers.pop() / currentNumber);
                        break;
                    }
                }
            }

            int result = 0;
            while (!stackOfNumbers.isEmpty())
            {
                result += stackOfNumbers.pop();
            }

            vectorOfResults.add(result);
        }

        return vectorOfResults;
    }
}
