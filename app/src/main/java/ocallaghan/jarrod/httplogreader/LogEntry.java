package ocallaghan.jarrod.httplogreader;


// Class to capture Common Log Format files
public class LogEntry{
    private String raw;
    private String ip;
    private String identity;
    private String userID;
    private String dateTime;
    private String requestLine;
    private String httpStatusCode; 
    private String returnSize;
	private String referrer;
	private String userAgent;

	private void setRaw(String raw){
		this.raw = raw;
	}
	public String getRaw(){
		return this.raw;
	}

	private void setIP(String ip){
		this.ip = ip;
	}

	public String getIP(){
		return this.ip;
	}

	private void setIdentity(String identity){
		this.identity = identity;
	}

	public String getIdentity(){
		return this.identity;
	}

	private void setUserID(String userID){
		this.userID = userID;
	}

	public String getUserID(){
		return this.userID;
	}

	private void setDateTime(String dateTime){
		this.dateTime = dateTime;
	}

	public String getDateTime(){
		return this.dateTime;
	}

	private void setRequestLine(String requestLine){
		this.requestLine = requestLine;
	}

	public String getRequestLine(){
		return this.requestLine;
	}

	private void setHttpStatusCode(String httpStatusCode){
		this.httpStatusCode = httpStatusCode;
	}
	
	public String getHttpStatusCode(){
		return this.httpStatusCode;
	}

	private void setReturnSize(String returnSize){
		this.returnSize = returnSize;
	}

	public String getReturnSize(){
		return this.returnSize;
	}


	private void setReferrer(String referrer){
		this.referrer = referrer;
	}
	
	public String getReferrer(){
		return this.referrer;
	}

	private void setUserAgent(String userAgent){
		this.userAgent = userAgent;
	}

	public String getUserAgent(){
		return this.userAgent;
	}

	public void parseLogData(String data){
		setRaw(data);

		// Could raise an exception here but I think its better to catch it one level up.
		// If Log entry throws exception, it would be due to a incorrect log file and the file parser should catch it
		// Splitting first few log headers by spaces
		String splitData[] = data.split(" ", 4);
		setIP(splitData[0]);
		setIdentity((splitData[1]));
		setUserID(splitData[2]);

		// Switching to square brackets as spaces are valid chars in datetime
		splitData = splitData[3].split("] ", 2);
		setDateTime(splitData[0].replace("[", ""));

		// Using <" "> to split so that we can also get rid or a character here
		splitData = splitData[1].split("\" ", 2);
		setRequestLine(splitData[0].replace("\"", ""));

		splitData = splitData[1].split(" ", 3);
		setHttpStatusCode(splitData[0]);

		setReturnSize(splitData[1]);

		splitData = splitData[2].split("\" \"", 2);
		setReferrer(splitData[0].replace("\"", ""));

		setUserAgent(splitData[1].replace("\"", ""));
	}

	public void printLogLine(){
		System.out.println("IP: " + getIP());
		System.out.println("Identity: " + getIdentity());
		System.out.println("User ID: " + getUserID());
		System.out.println("DateTime: " + getDateTime());
		System.out.println("Request: " + getRequestLine());
		System.out.println("HTTP status code: " + getHttpStatusCode());
		System.out.println("Return size: " + getReturnSize());
		System.out.println("Referrer: " + getReferrer());
		System.out.println("User Agent: " + getUserAgent());
	}

	public void printLogLineRaw(){
		System.out.println(getRaw());
	}
}