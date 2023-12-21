package com.example.maven_pp_javafx;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileWriter;
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
                switch (ioFileInfo.inputEncryptionMethod) {
                    case "1" -> {
                        archiveFileWorker zfw = new archiveFileWorker();
                        String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName + "." + ioFileInfo.inArchveData);

                        gainData = ptw.readingFromPlain(unpackedfile);

                        File fileToDelete = new File(unpackedfile);
                        fileToDelete.delete();
                    }
                    case "2" -> {
                        //decryptFile()
                        gainData = ptw.readingFromPlain(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);
                    }
                    case "3" -> {
                        archiveFileWorker zfw = new archiveFileWorker();
                        String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName + "." + ioFileInfo.inArchveData);

                        ioFileInfo.inputFileName = "new_" + ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData;
                        //decryptFileIfNeeds(ioFileInfo);

                        gainData = ptw.readingFromPlain(unpackedfile);

                        File fileToDelete = new File(unpackedfile);
                        fileToDelete.delete();
                    }
                    case "4" -> {
                        //decryptFileIfNeeds(ioFileInfo);

                        archiveFileWorker zfw = new archiveFileWorker();
                        String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName + "." + ioFileInfo.inArchveData);

                        gainData = ptw.readingFromPlain(unpackedfile);

                        File fileToDelete = new File(unpackedfile);
                        fileToDelete.delete();
                    }
                    case "5" -> {
                        gainData = ptw.readingFromPlain(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);
                    }
                }
            }
            case "json" -> {
                switch (ioFileInfo.inputEncryptionMethod) {
                    case "1" -> {
                        archiveFileWorker zfw = new archiveFileWorker();
                        String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName + "." + ioFileInfo.inArchveData);

                        JsonSimpleParser parser = new JsonSimpleParser();
                        gainData = parser.parse(unpackedfile);

                        File fileToDelete = new File(unpackedfile);
                        fileToDelete.delete();
                    }
                    case "2" -> {
                        //decryptFile()
                        JsonSimpleParser parser = new JsonSimpleParser();
                        gainData = parser.parse(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);
                    }
                    case "3" -> {
                        archiveFileWorker zfw = new archiveFileWorker();
                        String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName + "." + ioFileInfo.inArchveData);

                        ioFileInfo.inputFileName = "new_" + ioFileInfo.inputFileName;
                        //decryptFileIfNeeds(ioFileInfo);

                        JsonSimpleParser parser = new JsonSimpleParser();
                        gainData = parser.parse(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);

                        File fileToDelete = new File(unpackedfile);
                        fileToDelete.delete();
                    }
                    case "4" -> {
                        //decryptFileIfNeeds(ioFileInfo);

                        archiveFileWorker zfw = new archiveFileWorker();
                        String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName + "." + ioFileInfo.inArchveData);

                        JsonSimpleParser parser = new JsonSimpleParser();
                        gainData = parser.parse(unpackedfile);

                        File fileToDelete = new File(unpackedfile);
                        fileToDelete.delete();
                    }
                    case "5" -> {
                        JsonSimpleParser parser = new JsonSimpleParser();
                        gainData = parser.parse(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);
                    }
                }
            }
            case "xml" -> {
                switch (ioFileInfo.outputEncryptionMethod) {
                    case "1" -> {
                        archiveFileWorker zfw = new archiveFileWorker();
                        String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName + "." + ioFileInfo.inArchveData);

                        XmlParser xmlParser = new XmlParser();
                        gainData = xmlParser.parse(unpackedfile);

                        File fileToDelete = new File(unpackedfile);
                        fileToDelete.delete();
                    }
                    case "2" -> {
                        //decryptFile()
                        XmlParser xmlParser = new XmlParser();
                        gainData = xmlParser.parse(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);
                    }
                    case "3" -> {
                        archiveFileWorker zfw = new archiveFileWorker();
                        String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName + "." + ioFileInfo.inArchveData);

                        ioFileInfo.inputFileName = "new_" + ioFileInfo.inputFileName;
                        //decryptFileIfNeeds(ioFileInfo);

                        XmlParser xmlParser = new XmlParser();
                        gainData = xmlParser.parse(unpackedfile);

                        File fileToDelete = new File(unpackedfile);
                        fileToDelete.delete();
                    }
                    case "4" -> {
                        //decryptFileIfNeeds(ioFileInfo);

                        archiveFileWorker zfw = new archiveFileWorker();
                        String unpackedfile = zfw.archiveInput(ioFileInfo.inputFileName + "." + ioFileInfo.inArchveData);

                        XmlParser xmlParser = new XmlParser();
                        gainData = xmlParser.parse(unpackedfile);

                        File fileToDelete = new File(unpackedfile);
                        fileToDelete.delete();
                    }
                    case "5" -> {
                        XmlParser xmlParser = new XmlParser();
                        gainData = xmlParser.parse(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);
                    }
                }
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


    private void encryptFileIfNeeds(IOFileInfo ioFileInfo) throws IOException, InvalidKeyException, NoSuchAlgorithmException {
        String key = "secretkey1111111";
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");

        FileEncrypterDecrypter cipher = new FileEncrypterDecrypter(secretKey, "AES/CBC/PKCS5Padding");
        File fileToEncrypt = new File(ioFileInfo.inputFileName + "." + ioFileInfo.inputFileData);
        cipher.encrypt(fileToEncrypt.toString(), vectorOfResults.toString(), ioFileInfo.outputFileName);
    }

    public void decryptFile(IOFileInfo ioFileInfo) throws InvalidAlgorithmParameterException, IOException, InvalidKeyException, NoSuchAlgorithmException {
        String key = "secretkey1111111";
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");

        FileEncrypterDecrypter cipher = new FileEncrypterDecrypter(secretKey, "AES/CBC/PKCS5Padding");
        String content = cipher.decrypt(ioFileInfo.inputFileName + ".enc");

        String[] file_name_content = content.split("SEPARATOR");

        File file = new File(file_name_content[0]);
        file.createNewFile();

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(file_name_content[1]);
    }
}
