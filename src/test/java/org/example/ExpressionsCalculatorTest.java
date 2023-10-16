package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionsCalculatorTest {

    @Test
    void calculateExpressions() {
        Vector<Integer> shouldEquals = new Vector<>();
        shouldEquals.add(16);
        shouldEquals.add(-2);
        shouldEquals.add(18);

        List<String> strList = Arrays.asList("4 + 3 * 4", "3 + 2 - 7", "4 * 5 - 6 / 3");
        ExpressionsCalculator ec = new ExpressionsCalculator();
        Vector<Integer> result = ec.calculateExpressions(strList);

        assertEquals(shouldEquals, result);
    }

    @Test
    void externalLibCalculateExpressions() {
        Vector<Integer> shouldEquals = new Vector<>();
        shouldEquals.add(16);
        shouldEquals.add(-2);
        shouldEquals.add(18);

        List<String> strList = Arrays.asList("4 + 3 * 4", "3 + 2 - 7", "4 * 5 - 6 / 3");
        ExpressionsCalculator ec = new ExpressionsCalculator();
        Vector<Integer> result = ec.calculateExpressions(strList);

        assertEquals(shouldEquals, result);
    }

    @Test
    void regexCalculateExpressions() {
        Vector<Integer> shouldEquals = new Vector<>();
        shouldEquals.add(16);
        shouldEquals.add(-2);
        shouldEquals.add(18);

        List<String> strList = Arrays.asList("4 + 3 * 4", "3 + 2 - 7", "4 * 5 - 6 / 3");
        ExpressionsCalculator ec = new ExpressionsCalculator();
        Vector<Integer> result = ec.regexCalculateExpressions(strList);

        assertEquals(shouldEquals, result);
    }
}