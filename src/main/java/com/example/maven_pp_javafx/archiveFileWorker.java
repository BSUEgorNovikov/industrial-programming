package com.example.maven_pp_javafx;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class archiveFileWorker {
    public String archiveInput(String zipFileName){
        String unpackedFile = new String();
        try(ZipInputStream zin = new ZipInputStream(new FileInputStream(zipFileName)))
        {
            ZipEntry entry;
            String name;
            while((entry=zin.getNextEntry())!=null) {

                name = entry.getName(); // получим название файла
                System.out.printf("File name: %s \n", name);

                //unpackedFile = "new_" + name;
                unpackedFile = name;
                // распаковка
                FileOutputStream fout = new FileOutputStream(unpackedFile);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return unpackedFile;
    }
    public void archiveOutput(String fileName, String outType, String archiveType){
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(fileName + "." + archiveType));
            FileInputStream fis= new FileInputStream(fileName + "." + outType))
        {
            ZipEntry entry1=new ZipEntry(fileName + "." + outType);
            zout.putNextEntry(entry1);
            // считываем содержимое файла в массив byte
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // добавляем содержимое к архиву
            zout.write(buffer);
            // закрываем текущую запись для новой записи
            zout.closeEntry();
        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
    }
}