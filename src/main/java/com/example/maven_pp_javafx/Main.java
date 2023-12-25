package com.example.maven_pp_javafx;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Stream;


public class Main
{
    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
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

//        String key = "secretkey1111111";
//        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
//        FileEncrypterDecrypter cipher = new FileEncrypterDecrypter(secretKey, "AES/CBC/PKCS5Padding");
//
//        cipher.encryptInputArchive("enc_zip_txt_input", "zip");
//        String content = cipher.decrypt("enc_zip_txt_input.enc");
//        System.out.println(content);
    }
}