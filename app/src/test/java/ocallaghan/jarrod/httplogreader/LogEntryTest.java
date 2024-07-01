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

    @Test public void FileParserReturnsIP(){
        assertEquals("177.71.128.21", entry.getIP());
    }

    @Test public void fileParserReturnsIdentity(){
        assertEquals("-", entry.getIdentity());
    }

    @Test public void fileParserReturnsUserID(){
        assertEquals("-", entry.getUserID());
    }

    @Test public void fileParserReturnsDateTime(){
        assertEquals("10/Jul/2018:22:21:28 +0200", entry.getDateTime());
    }

    @Test public void fileParserReturnsRequestLine(){
        assertEquals("GET /intranet-analytics/ HTTP/1.1", entry.getRequestLine());
    }

    @Test public void fileParserReturnsHttpStatusCode(){
        assertEquals("200", entry.getHttpStatusCode());
    }

    @Test public void fileParserReturnsReturnSize(){
        assertEquals("3574", entry.getReturnSize());
    }

    @Test public void fileParserReturnsReferrer(){
        assertEquals("-", entry.getReferrer());
    }

    @Test public void fileParserReturnsUserAgent(){
        assertEquals("Mozilla/5.0 (X11; U; Linux x86_64; fr-FR) AppleWebKit/534.7 (KHTML, like Gecko) Epiphany/2.30.6 Safari/534.7", entry.getUserAgent());
    }

    @Test public void fileParserHandlesIncorrectString(){
        LogEntry incorrectLog = new LogEntry();
        assertThrows(RuntimeException.class, () -> {
            incorrectLog.parseLogData("INCORRECTSTRING");
        });


    }

}
