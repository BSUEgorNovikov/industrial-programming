package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;


public class Main
{
    public static void main(String[] args) throws IOException {
        IOFileInfo ioFileInfo = new IOFileInfo();
        CLI cli = new CLI();
        ProgramManager programManager = new ProgramManager();

        cli.intro();
        while (cli.isContinueWorking()) {
            cli.inputFileInfo(ioFileInfo);

            programManager.manageInput(ioFileInfo);
            programManager.manageOutput(ioFileInfo);

            cli.finishOrContinue();
        }
    }

//    public static void fileEncryption(PlainTextWorker ptw, IOFileInfo ioFileInfo) {
//        List<String> encryptedResults = new ArrayList<>();
//        encryptedResults = ptw.readingFromPlain(ioFileInfo.outputFileName + ".txt");
//
//        final String secretKey = "11111";
//        List<String> resultsForOutput = new ArrayList<>();
//        for (int i = 0; i < encryptedResults.size(); i++) {
//            String encryptedString = aesCipher.encrypt(encryptedResults.get(i), secretKey);
//            resultsForOutput.add(encryptedString);
//        }
//        File fileToDelete = new File(ioFileInfo.outputFileName + ".txt");
//        fileToDelete.delete();
//
//        ptw.writeInPlain(ioFileInfo.outputFileName + ".txt", resultsForOutput.toString());
//    }
}