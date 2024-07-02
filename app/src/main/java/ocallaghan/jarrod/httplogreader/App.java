package ocallaghan.jarrod.httplogreader;


import java.util.SortedMap;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Map;

public class App {
    public static void main(String[] args){
        if (args.length == 0){
            System.out.println("No filepath provided");
            System.exit(0);
        }

        // Process file and get log entries
        String filePath = args[0];
        // String filePath = "/Users/jarrod/Repositories/HTTPLogReader/SampleData/data.log";
        FileReader fr = new FileReader();
        fr.readFile(filePath);

        // Run the report
        report(fr);
    }

    
    public static void printMenu(){
        System.out.println("HTTP Log Reader");
        System.out.println("Please provide a filepath");
    }


    public static void report(FileReader fr){
        System.out.println("Number of unique IP addresses: " + getUniqueIPCount(fr.getIPAddresses()));
        System.out.println(getTop3IpAddresses(fr.getIPAddresses()));
        System.out.println(getTop3URLAccessed(fr.getUrlAddresses()));
    }

    private static Integer getUniqueIPCount(Map<String, Integer> ipAddresses){
        return ipAddresses.size();
    }

    private static String getTop3IpAddresses(Map<String, Integer> ipAddresses){
        SortedMap<Integer, HashSet<String>> sortedIPList = sortHashMapByValueReversed(ipAddresses);

        String[] topIPs = Top3orNItemsFromMap(sortedIPList);
        String formattedString = topIPs[0] + " most active IP addresses by occurrence";

        for (int i = 1; i < topIPs.length; i ++){
            formattedString += "\n" + topIPs[i];
        }

        return formattedString;
    }

    private static String getTop3URLAccessed(Map<String, Integer> urlAccessed){
        SortedMap<Integer, HashSet<String>> sortedURLList = sortHashMapByValueReversed(urlAccessed);

        String[] topUrls = Top3orNItemsFromMap(sortedURLList);
        String formattedString = topUrls[0] + " most visited URLs";

        for (int i = 1; i < topUrls.length; i ++){
            formattedString += "\n" + topUrls[i];
        }
        return formattedString;
    }

    private static SortedMap<Integer, HashSet<String>> sortHashMapByValueReversed(Map<String, Integer> unsortedMap){
        SortedMap<Integer, HashSet<String>> sortedMap = new TreeMap<>();

        unsortedMap.forEach((k, v) -> {
            if (sortedMap.containsKey(v)){
                sortedMap.get(v).add(k);
            } else {
                HashSet<String> tmp = new HashSet<String>();
                tmp.add(k);
                sortedMap.put(v, tmp);
            }
        });

        return sortedMap.reversed();
    }

    private static String[] Top3orNItemsFromMap(SortedMap<Integer, HashSet<String>> map){
        Integer maxMapSize = (map.size() < 3) ? map.size(): 3;
        String[] formattedString = new String[maxMapSize + 1];
        formattedString[0] = "The top " + maxMapSize;

        for (int i = 0; i < maxMapSize; i++){
            String tmp =  map.firstKey() + " - " + map.get(map.firstKey()).toString();
            formattedString[i+1] = tmp;
            map.remove(map.firstKey());
        }
        
        return formattedString;
    }

}
