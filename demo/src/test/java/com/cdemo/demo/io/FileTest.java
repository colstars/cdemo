package com.cdemo.demo.io;

import com.cdemo.util.file.CipherUtils;
import com.cdemo.util.file.ZipUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.util.Date;

@Slf4j
public class FileTest {

    @Test
    public void test1() throws  Exception{
        File file = new File("E:\\logs\\cdemo\\temp.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(file))));

        try {
            for (int i = 0; i < 100000; i++) {
                bufferedWriter.write("12345678979789|12345678979789|12345678979789|12345678979789|12345678979789|12345678979789|12345678979789|12345678979789|12345678979789|12345678979789");
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        bufferedWriter.flush();
        bufferedWriter.close();
    }


    @Test
    public void zip() throws  Exception{
        File file = new File("E:\\logs\\cdemo\\temp.zip");
        ZipUtils.toZip("E:\\logs\\cdemo\\temp.txt", new FileOutputStream(file), true);
    }

    @Test
    public void encrypt() throws  Exception{

        System.out.println(new Date());

        String file ="E:\\logs\\cdemo\\temp.zip";
        String file2 = "E:\\logs\\cdemo\\tempEnc.zip";

        CipherUtils.encrypt(file, file2, "123456");
        System.out.println(new Date());
    }


}
