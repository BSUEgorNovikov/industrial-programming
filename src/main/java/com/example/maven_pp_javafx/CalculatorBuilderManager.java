package com.example.maven_pp_javafx;

public class CalculatorBuilderManager {
    CalculatorBuilder calc;

    public void setCalc(CalculatorBuilder calc) {
        this.calc = calc;
    }

    double calculate(String expression) {
        return calc.calculate(expression);
    }
}
