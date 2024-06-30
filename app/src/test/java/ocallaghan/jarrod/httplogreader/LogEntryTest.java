package ocallaghan.jarrod.httplogreader;


import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class LogEntryTest {

    
    LogEntry entry = new LogEntry();
    String raw = "177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] \"GET /intranet-analytics/ HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0 (X11; U; Linux x86_64; fr-FR) AppleWebKit/534.7 (KHTML, like Gecko) Epiphany/2.30.6 Safari/534.7\"";

    @Before public void initialize(){
        entry.parseLogData(raw);
    }

    // Test returning vars are working
    @Test public void FileParserReturnsRaw(){
        assertEquals(raw, entry.getRaw());
    }

    @Test public void FileParserReturnsip(){
        assertEquals("177.71.128.21", entry.getIP());
    }


    // private String ;
// private String identity;
// private String userID;
// private String dateTime;
// private String requestLine;
// private String httpStatusCode; 
// private String returnSize;
// private String referrer;
// private String userAgent;
    


    // Test we parse the correct line data

    // Test we parse the wrong format

    // Test line is null
    
    // Test line is too long?

    //
}
