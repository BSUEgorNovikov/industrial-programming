package com.example.maven_pp_javafx;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlainTextWorkerTest {

    @Test
    void readingFromPlain() {
        List<String> expected = new ArrayList<>();
        expected.add("4 + 3 * 4");
        expected.add("3 + 2 - 7");
        expected.add("4 * (5 - 6) / 3");
        expected.add("0.56 + 0.2 * 4");

        PlainTextWorker ptw = new PlainTextWorker("input.txt");
        assertEquals(expected, ptw.readingFromPlain("input.txt"));
    }
}