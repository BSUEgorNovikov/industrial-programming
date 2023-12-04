package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionsCalculatorTest {

    @Test
    void calculateExpression() {
        Vector<Double> shouldEquals = new Vector<>();
        shouldEquals.add(16.0);
        shouldEquals.add(-2.0);
        shouldEquals.add(-4.0/3.0);
        shouldEquals.add(1.36);

        List<String> strList = Arrays.asList("4 + 3 * 4", "3 + 2 - 7", "4 * (5 - 6) / 3", "0.56 + 0.2 * 4");
        ExpressionsCalculator ec = new ExpressionsCalculator();
        Vector<Double> result = new Vector<>();
        for(int i = 0; i < strList.size(); i++)
        {
            double val = ec.calculateExpression(strList.get(i));
            result.add(val);
        }

        assertEquals(shouldEquals, result);
    }

    @Test
    void externalLibCalculateExpressions() {
        Vector<Double> shouldEquals = new Vector<>();
        shouldEquals.add(16.0);
        shouldEquals.add(-2.0);
        shouldEquals.add(-4.0/3.0);
        shouldEquals.add(1.36);

        List<String> strList = Arrays.asList("4 + 3 * 4", "3 + 2 - 7", "4 * (5 - 6) / 3", "0.56 + 0.2 * 4");
        ExpressionsCalculator ec = new ExpressionsCalculator();
        Vector<Double> result = ec.externalLibCalculateExpressions(strList);

        assertEquals(shouldEquals, result);
    }

//    @Test
//    void regexCalculateExpressions() {
//        Vector<Double> shouldEquals = new Vector<>();
//        shouldEquals.add(16);
//        shouldEquals.add(-2);
//        shouldEquals.add(18);
//
//        List<String> strList = Arrays.asList("4 + 3 * 4", "3 + 2 - 7", "4 * 5 - 6 / 3");
//        ExpressionsCalculator ec = new ExpressionsCalculator();
//        Vector<Double> result = ec.regexCalculateExpressions(strList);
//
//        assertEquals(shouldEquals, result);
//    }
}