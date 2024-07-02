package ocallaghan.jarrod.httplogreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileReader {
    private Map<String, Integer> ipAddresses = new HashMap<String, Integer>();
    private Map<String, Integer> urlAddresses = new HashMap<String, Integer>(); 

    // public void FileReader(String filePath){

    // }

    public void readFile(String filePath){
        Map<String, Integer> ipAddresses = new HashMap<String, Integer>();
        Map<String, Integer> urlAccessed = new HashMap<String, Integer>();

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                LogEntry entry = new LogEntry();
                entry.parseLogData(data);

                // Hashmap for ip addresses and occurrences
                String ip = entry.getIP();
                if (ipAddresses.containsKey(ip)){
                    ipAddresses.put(ip, ipAddresses.get(ip) + 1);
                } else {
                    ipAddresses.put(ip, 1);
                }

                // Hashmap for URLs and occurrences
                String url = entry.getRequestLine().split(" ", 3)[1];
                if (urlAccessed.containsKey(url)){
                    urlAccessed.put(url, urlAccessed.get(url) + 1);
                } else {
                    urlAccessed.put(url, 1);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            // Goin to gracefully exit the program as we don't have anything to process
            System.exit(0);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Unable to parse file, it may not be the correct format");
            System.exit(0);
        }

        setIPAddresses(ipAddresses);
        setUrlAddresses(urlAddresses);
    }

    private void setIPAddresses(Map<String, Integer> ipAddresses){
        this.ipAddresses = ipAddresses;
    }

    public Map<String,Integer> getIPAddresses(){
        return this.ipAddresses;
    }

    private void setUrlAddresses(Map<String, Integer>urlAddresses){
        this.urlAddresses = urlAddresses;
    }

    public Map<String,Integer> getUrlAddresses(){
        return this.urlAddresses;
    }
}
