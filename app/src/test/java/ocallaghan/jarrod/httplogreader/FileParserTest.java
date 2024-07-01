package ocallaghan.jarrod.httplogreader;

import org.junit.Test;
import static org.junit.Assert.*;

public class FileParserTest {
    @Test public void appReturnsTrue(){
        FileParser fp = new FileParser();
        assertTrue(fp.FileParser(""));
    }

    // @Test public void appReturnsFalse(){
    //     FileParser fp = new FileParser();
    //     assertFalse(fp.FileParser(""));
    // }
}
