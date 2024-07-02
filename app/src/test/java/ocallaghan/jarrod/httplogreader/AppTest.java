package ocallaghan.jarrod.httplogreader;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;

import org.junit.AfterClass;
import org.junit.BeforeClass;


public class AppTest {

    public static String tempFileCorrect = "./data.tmp";
    public static FileReader fr = new FileReader();
    @BeforeClass public static void setup(){
        try {
            FileWriter fw = new FileWriter(tempFileCorrect);
            fw.write("177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] \"GET /intranet-analytics/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0 (X11; U; Linux x86_64; fr-FR) AppleWebKit/534.7 (KHTML, like Gecko) Epiphany/2.30.6 Safari/534.7\"");
            fw.write("177.71.128.22 - - [10/Jul/2018:22:21:28 +0200] \"GET /HELLO/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0 (X11; U; Linux x86_64; fr-FR) AppleWebKit/534.7 (KHTML, like Gecko) Epiphany/2.30.6 Safari/534.7\"");
            fw.write("10.10.10.10 - - [10/Jul/2018:22:21:28 +0200] \"GET /GoodBye/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0 (X11; U; Linux x86_64; fr-FR) AppleWebKit/534.7 (KHTML, like Gecko) Epiphany/2.30.6 Safari/534.7\"");
            fw.close();
        } catch (Exception e){
            System.out.println("An error occurred with the tests");
        }
        fr.readFile(tempFileCorrect);
    }

    @AfterClass public static void teardown() {
        File f = new File(tempFileCorrect);
        f.delete();
    }

    @Test public void testReport(){
        try {
            App.report(fr);
        } catch (Exception e){
            fail("Exception shouldn't have been rasied");
        }
    }

    @Test public void getUniqueIPCount(){
        System.out.println(fr.getIPAddresses());
        assertEquals(3, fr.getIPAddresses());
    }

}
