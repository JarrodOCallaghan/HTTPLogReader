package ocallaghan.jarrod.httplogreader;

import org.junit.Test;

import com.google.common.io.Files;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;


public class FileReaderTest {
    
    
    public static String tempFileCorrect = "./data.tmp";
    public static String tempFileIncorrect = "./dataBad.tmp";

    
    @BeforeClass public static void setup(){
        try {
            FileWriter fw = new FileWriter(tempFileCorrect);
            fw.write("177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] \"GET /intranet-analytics/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0 (X11; U; Linux x86_64; fr-FR) AppleWebKit/534.7 (KHTML, like Gecko) Epiphany/2.30.6 Safari/534.7\"");
            fw.write("177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] \"GET /HELLO/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0 (X11; U; Linux x86_64; fr-FR) AppleWebKit/534.7 (KHTML, like Gecko) Epiphany/2.30.6 Safari/534.7\"");
            fw.close();
        } catch (Exception e){
            System.out.println("An error occurred with the tests");
        }

        try {
            FileWriter fw = new FileWriter(tempFileIncorrect);
            fw.write("INCORRECT FORMAT ! - ");
            fw.close();
        } catch (Exception e){
            System.out.println("An error occurred with the tests");
        }
    }



    @Test public void checkNoExceptionForFileNotFound(){
        FileReader fr = new FileReader();
        try{
            fr.readFile("NotValidFile.tmp");
        } catch (Exception e){
            fail("No Exception should have been thrown");
        }
    }

    @Test public void checkWrongFormatLine(){
        FileReader fr = new FileReader();
        try{
            fr.readFile(tempFileIncorrect);
        } catch (Exception e){
            fail("No Exception should have been thrown");
        }
    }

    @Test public void fileReaderHasData(){
        System.out.println("Test Content here");
        FileReader fr = new FileReader();
        fr.readFile(tempFileCorrect);
        assertNotNull(fr.getIPAddresses());
        assertNotNull(fr.getUrlAddresses());
    }


    @AfterClass
    public static void teardown() {
        File f = new File(tempFileCorrect);
        f.delete();
        f = new File(tempFileIncorrect);
        f.delete();
    }
}
