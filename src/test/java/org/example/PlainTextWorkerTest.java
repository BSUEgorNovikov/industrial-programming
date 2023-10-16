package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlainTextWorkerTest {

    @Test
    void readingFromPlain() {
        List<String> expextedList = new ArrayList<>();
        expextedList.add("4 + 3 * 4");
        expextedList.add("3 + 2 - 7");
        expextedList.add("4 * 5 - 6 / 3");

        PlainTextWorker ptw = new PlainTextWorker("input.txt");
        List<String> actualList = ptw.readingFromPlain("input.txt");

        assertEquals(expextedList, actualList);
    }
}