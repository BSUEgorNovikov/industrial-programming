package com.example.maven_pp_javafx;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProgramManager {
    CalculatorBuilderManager calculator = new CalculatorBuilderManager();
    Vector<Double> vectorOfResults = new Vector<>();

    public ProgramManager() throws NoSuchAlgorithmException {
    }

    public void manageInput(IOFileInfo ioFileInfo) {
        PlainTextWorker ptw = new PlainTextWorker(ioFileInfo.inputFileName);
        List<String> gainData = new ArrayList<>();

        switch (ioFileInfo.inputFileData) {
//            case "zip", "rar" -> {
//                archiveFileWorker zfw = new archiveFileWorker();
//                String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName);
//
//                ptw = new PlainTextWorker(unpackedfile);
//                gainData = ptw.readingFromPlain(unpackedfile);
//            }
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
                calculator.setCalc(new HandleCalculatorBuilder());
            }
            case "2" -> {
                calculator.setCalc(new RegexCalculatorBuilder());
            }
            case "3" -> {
                calculator.setCalc(new ExternalCalculatorBuilder());
            }
        }

        for (int i = 0; i < gainData.size(); i++) {
            double res = calculator.calculate(gainData.get(i));
            vectorOfResults.add(res);
        }
    }

    public void manageOutput(IOFileInfo ioFileInfo) throws InvalidKeyException, IOException, NoSuchAlgorithmException {
        PlainTextWorker ptw = new PlainTextWorker(ioFileInfo.inputFileName);

        switch (ioFileInfo.outputFileData) {
            case "txt" -> {
                switch (ioFileInfo.outputEncryptionMethod) {
                    case "1" -> {
                        ptw.writeInPlain(ioFileInfo.outputFileName + ".txt", vectorOfResults.toString());

                        archiveFileWorker zfw = new archiveFileWorker();
                        zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData, ioFileInfo.outArchveData);
                        File fileToDelete = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
                        fileToDelete.delete();
                    }
                    case "2" -> {
                        encryptFileIfNeeds(ioFileInfo);
                    }
                    case "3" -> {
                        ptw.writeInPlain(ioFileInfo.outputFileName + ".txt", vectorOfResults.toString());

                        archiveFileWorker zfw = new archiveFileWorker();
                        zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData, ioFileInfo.outArchveData);
                        File fileToDelete = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
                        fileToDelete.delete();

                        ioFileInfo.outputFileData = ioFileInfo.outArchveData;
                        encryptFileIfNeeds(ioFileInfo);
                    }
                    case "4" -> {
                        encryptFileIfNeeds(ioFileInfo);
                        ioFileInfo.outputFileData = "enc";

                        archiveFileWorker zfw = new archiveFileWorker();
                        zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData, ioFileInfo.outArchveData);
                        File fileToDelete = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
                        fileToDelete.delete();
                    }
                    case "5" -> {
                        ptw.writeInPlain(ioFileInfo.outputFileName + ".txt", vectorOfResults.toString());
                    }
                }
            }
            case "json" -> {
                switch (ioFileInfo.outputEncryptionMethod) {
                    case "1" -> {
                        JsonSimpleParser parser = new JsonSimpleParser();
                        parser.writeInJson(ioFileInfo.outputFileName + ".json", vectorOfResults.toString());

                        archiveFileWorker zfw = new archiveFileWorker();
                        zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData, ioFileInfo.outArchveData);
                        File fileToDelete = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
                        fileToDelete.delete();
                    }
                    case "2" -> {
                        encryptFileIfNeeds(ioFileInfo);
                    }
                    case "3" -> {
                        JsonSimpleParser parser = new JsonSimpleParser();
                        parser.writeInJson(ioFileInfo.outputFileName + ".json", vectorOfResults.toString());

                        archiveFileWorker zfw = new archiveFileWorker();
                        zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData, ioFileInfo.outArchveData);
                        File fileToDelete = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
                        fileToDelete.delete();

                        ioFileInfo.outputFileData = ioFileInfo.outArchveData;
                        encryptFileIfNeeds(ioFileInfo);
                    }
                    case "4" -> {
                        encryptFileIfNeeds(ioFileInfo);
                        ioFileInfo.outputFileData = "enc";

                        archiveFileWorker zfw = new archiveFileWorker();
                        zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData, ioFileInfo.outArchveData);
                        File fileToDelete = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
                        fileToDelete.delete();
                    }
                    case "5" -> {
                        JsonSimpleParser parser = new JsonSimpleParser();
                        parser.writeInJson(ioFileInfo.outputFileName + ".json", vectorOfResults.toString());
                    }
                }
            }
            case "xml" -> {
                switch (ioFileInfo.outputEncryptionMethod) {
                    case "1" -> {
                        XmlParser xmlParser = new XmlParser();
                        xmlParser.writeInXml(ioFileInfo.outputFileName, vectorOfResults);

                        archiveFileWorker zfw = new archiveFileWorker();
                        zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData, ioFileInfo.outArchveData);
                        File fileToDelete = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
                        fileToDelete.delete();
                    }
                    case "2" -> {
                        encryptFileIfNeeds(ioFileInfo);
                    }
                    case "3" -> {
                        XmlParser xmlParser = new XmlParser();
                        xmlParser.writeInXml(ioFileInfo.outputFileName, vectorOfResults);

                        archiveFileWorker zfw = new archiveFileWorker();
                        zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData, ioFileInfo.outArchveData);
                        File fileToDelete = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
                        fileToDelete.delete();

                        ioFileInfo.outputFileData = ioFileInfo.outArchveData;
                        encryptFileIfNeeds(ioFileInfo);
                    }
                    case "4" -> {
                        encryptFileIfNeeds(ioFileInfo);
                        ioFileInfo.outputFileData = "enc";

                        archiveFileWorker zfw = new archiveFileWorker();
                        zfw.archiveOutput(ioFileInfo.outputFileName, ioFileInfo.outputFileData, ioFileInfo.outArchveData);
                        File fileToDelete = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
                        fileToDelete.delete();
                    }
                    case "5" -> {
                        XmlParser xmlParser = new XmlParser();
                        xmlParser.writeInXml(ioFileInfo.outputFileName, vectorOfResults);
                    }
                }
            }
        }
    }

    SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();

    private void encryptFileIfNeeds(IOFileInfo ioFileInfo) throws IOException, InvalidKeyException, NoSuchAlgorithmException {
        //SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();

        FileEncrypterDecrypter cipher = new FileEncrypterDecrypter(secretKey, "AES/CBC/PKCS5Padding");
        File fileToEncrypt = new File(ioFileInfo.outputFileName + "." + ioFileInfo.outputFileData);
        cipher.encrypt(fileToEncrypt.toString(), ioFileInfo.outputFileName);
    }

    private void decryptFile(IOFileInfo ioFileInfo) throws InvalidAlgorithmParameterException, IOException, InvalidKeyException, NoSuchAlgorithmException {
        //SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();

        FileEncrypterDecrypter cipher = new FileEncrypterDecrypter(secretKey, "AES/CBC/PKCS5Padding");
        cipher.decrypt(ioFileInfo.inputFileName);
    }
}
