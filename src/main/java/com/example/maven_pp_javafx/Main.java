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
//        IOFileInfo ioFileInfo = new IOFileInfo();
//        CLI cli = new CLI();
//        ProgramManager programManager = new ProgramManager();
//
//        cli.intro();
//
//        while (cli.isContinueWorking()) {
//            cli.inputFileInfo(ioFileInfo);
//
//            programManager.manageInput(ioFileInfo);
//            programManager.manageOutput(ioFileInfo);
//
//            cli.finishOrContinue();
//        }

          String key = "secretkey1111111";
          SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
          FileEncrypterDecrypter cipher = new FileEncrypterDecrypter(secretKey, "AES/CBC/PKCS5Padding");

          cipher.encryptInput("json_input_to_enc", "json");
          String content = cipher.decrypt("json_input_to_enc.enc");
          System.out.println(content);


          String[] file_name_content = content.split("SEPARATOR");


//          FileOutputStream fout = new FileOutputStream("test_file_enc_dec.txt");
//          FileWriter fileWriter = new FileWriter("test_file_enc_dec.txt");
//          fout.write("[eq");

          try (BufferedWriter writer = new BufferedWriter(new FileWriter("json_input_to_enc.json"))) {
                      writer.write(file_name_content[1] + "\n");
          } catch (IOException e) {
                e.printStackTrace();
          }
    }
}