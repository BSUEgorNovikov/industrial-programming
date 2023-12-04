package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProgramManager {
    ExpressionsCalculator expCalc = new ExpressionsCalculator();
    Vector<Double> vectorOfResults = new Vector<>();
    public void manageInput(IOFileInfo ioFileInfo) {
        PlainTextWorker ptw = new PlainTextWorker(ioFileInfo.inputFileName);
        List<String> gainData = new ArrayList<>();

        switch (ioFileInfo.inputFileData) {
            case "zip", "rar" -> {
                archiveFileWorker zfw = new archiveFileWorker();
                String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName);

                ptw = new PlainTextWorker(unpackedfile);
                gainData = ptw.readingFromPlain(unpackedfile);
            }
            case "txt" -> {
                gainData = ptw.readingFromPlain(ioFileInfo.inputFileName);
            }
            case "json" -> {
                JsonSimpleParser parser = new JsonSimpleParser();
                gainData = parser.parse(ioFileInfo.inputFileName);
            }
            case "xml" -> {
                XmlParser xmlParser = new XmlParser();
                gainData = xmlParser.parse(ioFileInfo.inputFileName);
            }
        }

        switch (ioFileInfo.numberOfCalculationMethod) {
            case "1" -> {
                for (int i = 0; i < gainData.size(); i++) {
                    double res = expCalc.calculateExpression(gainData.get(i));
                    vectorOfResults.add(res);
                }
            }
            case "2" -> {
                for (int i = 0; i < gainData.size(); i++) {
                    double res = expCalc.regexCalculateExpression(gainData.get(i));
                    vectorOfResults.add(res);
                }
            }
            case "3" -> {
                vectorOfResults = expCalc.externalLibCalculateExpressions(gainData);
            }
        }
    }

    public void manageOutput(IOFileInfo ioFileInfo) {
        PlainTextWorker ptw = new PlainTextWorker(ioFileInfo.inputFileName);

        switch (ioFileInfo.outputFileData) {
            case "zip", "rar" -> {
                archiveFileWorker zfw = new archiveFileWorker();

                ptw.writeInPlain(ioFileInfo.outputFileName + ".txt", vectorOfResults.toString());

                if(ioFileInfo.isOutputFileEncrypted) {
                    //fileEncryption(ptw, ioFileInfo);
                }

                zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData);
                File fileToDelete = new File(ioFileInfo.outputFileName + ".txt");
                fileToDelete.delete();
            }
            case "txt" -> {
                ptw.writeInPlain(ioFileInfo.outputFileName + ".txt", vectorOfResults.toString());

                if(ioFileInfo.isOutputFileEncrypted) {
                    //fileEncryption(ptw, ioFileInfo);
                }
            }
        }
    }
}
