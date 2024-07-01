package ocallaghan.jarrod.httplogreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class App {
    public static void main(String[] args){
        HashMap<String, Integer> ipAddresses = new HashMap<String, Integer>();
        HashMap<String, Integer> urlAccessed = new HashMap<String, Integer>();
        String[] top3IP;
        String[] top3URL; 

        try {
            File file = new File("/Users/jarrod/Repositories/HTTPLogReader/SampleData/data.log");
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
        // System.out.println(ipAddresses);
        // hashmap.size() is O(1) time
        System.out.println("Unique IP Addresses: " + ipAddresses.size());

        System.out.println(urlAccessed);

    }
}
