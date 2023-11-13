package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class archiveFileWorkerTest {

    @Test
    void zipInput() {
        archiveFileWorker zfw = new archiveFileWorker();

        String actualTxtStr = zfw.archiveInput("zip_input.zip");

        assertEquals("new_input.txt", actualTxtStr);
    }
}