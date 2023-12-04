package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class zipFileWorkerTest {

    @Test
    void zipInput() {
        zipFileWorker zfw = new zipFileWorker();

        String actualTxtStr = zfw.zipInput("zip_input.zip");

        assertEquals("new_input.txt", actualTxtStr);
    }
}