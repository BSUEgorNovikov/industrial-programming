package org.example;

import org.example.test_json.Root;

import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException {
        String fileName;
        String fileData;
        Scanner in = new Scanner(System.in);

        System.out.println("Введите расширение файла (txt/xml/json/zip): ");
        fileData = in.nextLine();

        System.out.println("Введите имя файла (без расширения): ");
        fileName = in.nextLine();

        fileName += '.';
        fileName += fileData;

        PlainTextWorker ptw = new PlainTextWorker(fileName);
        List<String> gainData = new ArrayList<>();
        ExpressionsCalculator expCalc = new ExpressionsCalculator();

        switch (fileData) {
            case "zip" -> {
                zipFileWorker zfw = new zipFileWorker();
                String unpackedfile = zfw.zipInput(fileName);

                ptw = new PlainTextWorker(unpackedfile);

                gainData = ptw.readingFromPlain(unpackedfile);
                Vector<Integer> vectorOfResults = expCalc.calculateExpressions(gainData);
                ptw.writeInPlain(vectorOfResults);
                zfw.zipOutput("output.txt");
            }
            case "txt" -> {
                gainData = ptw.readingFromPlain(fileName);
                //Vector<Integer> vectorOfResults = expCalc.externalLibCalculateExpressions(gainData);
                //Vector<Integer> vectorOfResults = expCalc.calculateExpressions(gainData);
                Vector<Integer> vectorOfResults = expCalc.regexCalculateExpressions(gainData);
                ptw.writeInPlain(vectorOfResults);

                zipFileWorker zfw = new zipFileWorker();
                zfw.zipOutput("output.txt");

                final String secretKey = "11111";
                for (int i = 0; i < gainData.size(); i++) {
                    String encryptedString = aesCipher.encrypt(gainData.get(i), secretKey);
                    String decryptedString = aesCipher.decrypt(encryptedString, secretKey);
                    System.out.println(i + ")" + encryptedString);
                    System.out.println(i + ")" + decryptedString);
                }
            }
            case "json" -> {
                JsonSimpleParser parser = new JsonSimpleParser();
                gainData = parser.parse(fileName);

                Vector<Integer> vectorOfResults = expCalc.calculateExpressions(gainData);
                ptw.writeInPlain(vectorOfResults);
            }
            case "xml" -> {
                XmlParser xmlParser = new XmlParser();
                gainData = xmlParser.parse(fileName);

                Vector<Integer> vectorOfResults = expCalc.calculateExpressions(gainData);
                ptw.writeInPlain(vectorOfResults);
            }
        }
    }
}