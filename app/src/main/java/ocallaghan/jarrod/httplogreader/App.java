package ocallaghan.jarrod.httplogreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args){
        Map<String, Integer> ipAddresses = new HashMap<String, Integer>();
        Map<String, Integer> urlAccessed = new HashMap<String, Integer>();

        // Lets store the top 3 items
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

        System.out.println("Unique IP Addresses: " + ipAddresses.size());

        //Going to now 'flip' the ip list so it can be ordered
        SortedMap<Integer, HashSet<String>> sortedIPList = new TreeMap<>();

        ipAddresses.forEach((ip, count) -> {
            if (sortedIPList.containsKey(count)){
                sortedIPList.get(count).add(ip);
            } else {
                HashSet<String> tmp = new HashSet<String>();
                tmp.add(ip);
                sortedIPList.put(count, tmp);
            }
        });

        // Top 3 IP Addresses
        SortedMap<Integer, HashSet<String>> reversedIPList = sortedIPList.reversed();
        Integer reversedIPListLength = reversedIPList.size();
        System.out.println(reversedIPListLength);
        Integer topIPLen = (reversedIPListLength < 3) ? reversedIPListLength: 3;
        System.out.println("The top " + topIPLen + " most active IP addresses by occurrence");
        for (int i = 0; i < reversedIPListLength && i < 2 ; i ++){
            System.out.println("Occurrences: " + reversedIPList.firstKey() + " - " + reversedIPList.get(reversedIPList.firstKey()));
            reversedIPList.remove(reversedIPList.firstKey());
        }


        // URL's
        SortedMap<Integer, HashSet<String>> sortedURLList = new TreeMap<>();

        urlAccessed.forEach((url, count) -> {
            if (sortedURLList.containsKey(count)){
                sortedURLList.get(count).add(url);
            } else {
                HashSet<String> tmp = new HashSet<String>();
                tmp.add(url);
                sortedURLList.put(count, tmp);
            }
        });

        // Top 3 IP Addresses
        SortedMap<Integer, HashSet<String>> reversedURLList = sortedURLList.reversed();
        Integer reversedURLListLength = reversedURLList.size();
        Integer topURLLen = (reversedURLListLength < 3) ? reversedURLListLength: 3;
        System.out.println("The top " + topURLLen + " most visited URLs");
        for (int i = 0; i < reversedURLListLength && i < 2 ; i ++){
            System.out.println("Occurrences: " + reversedURLList.firstKey() + " - " + reversedURLList.get(reversedURLList.firstKey()));
            reversedURLList.remove(reversedURLList.firstKey());
        }


        


    }

}
