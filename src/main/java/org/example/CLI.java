package org.example;

import java.util.Scanner;
import java.util.stream.Stream;

public class CLI {
    boolean continueWorking = true;
    Scanner in = new Scanner(System.in);
    public boolean isContinueWorking() {
        return continueWorking;
    }

    public void setContinueWorking(boolean continueWorking) {
        this.continueWorking = continueWorking;
    }

    public void intro()
    {
        System.out.println("Здравствуйте!\nДанное приложение вычисляет результат математических выражений.\n" +
                "Приложение способно обрабатывать файлы расширения: txt, xml, json; архивы: zip, rar\n" +
                "Желаете начать работу?\n" +
                "1.ДА\n" +
                "2.НЕТ");

        byte yesOrNo;
        boolean isCorrectInput = false;
        while (!isCorrectInput) {
            yesOrNo = in.nextByte();
            if (yesOrNo == 1) {
                continueWorking = true;
                isCorrectInput = true;
            } else if (yesOrNo == 2) {
                continueWorking = false;
                isCorrectInput = true;
            }

            if(!isCorrectInput) {
                System.out.println("Неправильный ввод, попробуйте ещё раз.");
            }
        }

    }

    public void inputFileInfo(IOFileInfo file) {
        System.out.println("Введите расширение входного файла (txt/xml/json/zip/rar): ");

        boolean isCorrectInput = false;
        while (!isCorrectInput) {

            file.inputFileData = in.next();

            switch (file.inputFileData) {
                case "txt", "json", "zip", "xml", "rar" -> {
                    isCorrectInput = true;
                }
            }

            if(!isCorrectInput) {
                System.out.println("Неправильный ввод, попробуйте ещё раз.");
            }
        }

        System.out.println("Введите имя входного файла (без расширения): ");
        file.inputFileName = in.nextLine();

        file.inputFileName += '.';
        file.inputFileName += file.inputFileData;

        String isEncrypted;
        System.out.println("Зашифрован ли входной файл (ДА/НЕТ): ");

        isCorrectInput = false;
        while (!isCorrectInput) {
            isEncrypted = in.nextLine();

            if(isEncrypted.equals("ДА")) {
                file.isInputFileEncrypted = true;
                isCorrectInput = true;
            }
            else if(isEncrypted.equals("НЕТ")) {
                file.isInputFileEncrypted = false;
                isCorrectInput = true;
            }

            if(!isCorrectInput) {
                System.out.println("Неправильный ввод, попробуйте ещё раз.");
            }
        }

        System.out.println("Введите номер метода, которым хотите произвести подсчёт выражений:" +
                "\n1.Ручной" +
                "\n2.С помощью регулярных выражений" +
                "\n3.С помощью сторонней библиотеки");

        isCorrectInput = false;
        while (!isCorrectInput) {
            file.numberOfCalculationMethod = in.nextLine();
            if (Stream.of("1", "2", "3").anyMatch(s -> file.numberOfCalculationMethod.equals(s))) {
                isCorrectInput = true;
            }

            if(!isCorrectInput) {
                System.out.println("Неправильный ввод, попробуйте ещё раз.");
            }
        }

        System.out.println("Введите расширение выходного файла (txt/zip/rar): ");

        isCorrectInput = false;
        while (!isCorrectInput) {
            file.outputFileData = in.nextLine();

            switch (file.outputFileData) {
                case "txt", "zip", "rar" -> {
                    isCorrectInput = true;
                }
            }

            if(!isCorrectInput) {
                System.out.println("Неправильный ввод, попробуйте ещё раз.");
            }
        }

        System.out.println("Введите имя выходного файла (без расширения): ");
        file.outputFileName = in.nextLine();

        isEncrypted = "";
        System.out.println("Желаете ли зашифровать выходной файл (ДА/НЕТ): ");

        isCorrectInput = false;
        while (!isCorrectInput) {
            isEncrypted = in.nextLine();

            if(isEncrypted.equals("ДА")) {
                file.isOutputFileEncrypted = true;
                isCorrectInput = true;
            }
            else if(isEncrypted.equals("НЕТ")) {
                file.isOutputFileEncrypted = false;
                isCorrectInput = true;
            }

            if(!isCorrectInput) {
                System.out.println("Неправильный ввод, попробуйте ещё раз.");
            }
        }
    }

    public void finishOrContinue() {
        String isContinue;
        System.out.println("Желаете ли обработать ещё файл? (ДА/НЕТ): ");

        boolean isCorrectInput = false;
        while (!isCorrectInput) {
            isContinue = in.nextLine();

            if(isContinue.equals("ДА")) {
                setContinueWorking(true);
                isCorrectInput = true;
            }
            else if(isContinue.equals("НЕТ")) {
                setContinueWorking(false);
                isCorrectInput = true;
            }

            if(!isCorrectInput) {
                System.out.println("Неправильный ввод, попробуйте ещё раз.");
            }
        }
    }
}
