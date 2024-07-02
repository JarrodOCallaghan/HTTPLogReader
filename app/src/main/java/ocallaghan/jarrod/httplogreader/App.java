package ocallaghan.jarrod.httplogreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
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

    private Map<String, Integer> ipAddresses = new HashMap<String, Integer>();
    private Map<String, Integer> urlAccessed = new HashMap<String, Integer>();
    public static void main(String[] args){

        // Process file and get log entries
        String filePath = "/Users/jarrod/Repositories/HTTPLogReader/SampleData/data.log";
        FileReader fr = new FileReader();
        fr.readFile(filePath);

        // Run the report
        report(fr);
    }

    // Report could be broken down into its own class if needed, for now its fine if they are static with the main class
    static public void report(FileReader fr){
        System.out.println("Number of unique IP addresses: " + getUniqueIPCount(fr.getIPAddresses()));
        System.out.println("Top 3 active IP addresses");

        System.out.println("Top 3 visited URLs");



        SortedMap<Integer, HashSet<String>> sortedIPList = new TreeMap<>();

        fr.getIPAddresses().forEach((ip, count) -> {
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

        fr.getUrlAddresses().forEach((url, count) -> {
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

    static public Integer getUniqueIPCount(Map<String, Integer> ipAddresses){
        // Get size of unique IP Addresses
        return ipAddresses.size();
    }

}
