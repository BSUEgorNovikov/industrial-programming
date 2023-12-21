package com.example.maven_pp_javafx;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class FileEncrypterDecrypter {
    private static SecretKey secretKey;
    private static Cipher cipher;

    FileEncrypterDecrypter(SecretKey secretKey, String transformation) {
        this.secretKey = secretKey;
        try {
            this.cipher = Cipher.getInstance(transformation);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }
    void encrypt(String inFileName, String content, String outfileName) throws InvalidKeyException, IOException {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] iv = cipher.getIV();

        try (FileOutputStream fileOut = new FileOutputStream(outfileName + ".enc");
             CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)) {
            fileOut.write(iv);

            cipherOut.write(inFileName.getBytes());
            cipherOut.write(content.getBytes());
        }
    }

    String decrypt(String fileName) throws IOException, InvalidAlgorithmParameterException, InvalidKeyException {
        String content;

        try (FileInputStream fileIn = new FileInputStream(fileName)) {
            byte[] fileIv = new byte[16];
            fileIn.read(fileIv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(fileIv));

            try (
                    CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
                    InputStreamReader inputReader = new InputStreamReader(cipherIn);
                    BufferedReader reader = new BufferedReader(inputReader)
            ) {

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                content = sb.toString();
            }

        }
        return content;
    }

    void encryptInput(String inFileName, String inFileData) throws InvalidKeyException, IOException {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] iv = cipher.getIV();

        try (FileOutputStream fileOut = new FileOutputStream(inFileName + ".enc");
             CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)) {
            fileOut.write(iv);

            cipherOut.write(inFileName.getBytes());
            cipherOut.write(".".getBytes());
            cipherOut.write(inFileData.getBytes());
            cipherOut.write("SEPARATOR".getBytes());

            try
            {
                Scanner sc = new Scanner(new File(inFileName + "." + inFileData));

                while(sc.hasNext())
                {
                    String tmpLine = sc.nextLine();
                    cipherOut.write(tmpLine.getBytes());
                }
            }
            catch (IOException e)
            {
                System.out.println("Error" + e);
            }
        }
    }
}
