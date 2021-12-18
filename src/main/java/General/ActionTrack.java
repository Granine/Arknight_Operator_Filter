package General;

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * record action and read action history using thread
 */
public class ActionTrack{
    private ArrayList<String> searchItm = new ArrayList<>();
    private ArrayList<Integer> searchOcc = new ArrayList<>();
    private ArrayList< ArrayList<Double>> searchTim= new ArrayList<>();
    private ArrayList<Double> request = new ArrayList<>();
    public boolean debug;

    private String actionName;
    private int[] actionStartTime = new int[7];

    public ActionTrack (String action){
        actionName=action;
        this.actionStart();
    }
    private void actionStart(){
        actionStartTime[0]= LocalDateTime.now().getYear();
        actionStartTime[1]= LocalDateTime.now().getMonthValue();
        actionStartTime[2]= LocalDateTime.now().getDayOfMonth();
        actionStartTime[3]= LocalDateTime.now().getHour();
        actionStartTime[4]= LocalDateTime.now().getMinute();
        actionStartTime[5]= LocalDateTime.now().getSecond();
        actionStartTime[6]= LocalDateTime.now().getNano();
    }

    /**
     *
     * @return the time take the action to finish in seconds
     */
    public double actionEnd(){
        //time now
        double timeNow=actionStartTime[3]*3600+actionStartTime[4]*60+actionStartTime[5]+actionStartTime[6]/1000000000.0;
        double timePass=LocalDateTime.now().getHour()*3600+LocalDateTime.now().getMinute()*60+LocalDateTime.now().getSecond()+LocalDateTime.now().getNano()/1000000000.0-timeNow;
        StaticHelper.cacheSafe(Arrays.stream(actionStartTime).mapToObj((int x)->String.valueOf(x)).reduce(actionName,(x, y)->x+"+"+y)+"+"+timePass, "Data/OperatorData/ActionHistory");
        return timePass;
    }
    /**
     *record the search result, count as well as search time
     * @param searchR the string to record
     * modify: searchTim value is modified (new timestamp added)
     */
    private void actionRcd(String searchR){
        //if itm contain searchR find index at Itm and use such index to increase value of Occ;
        if (searchItm.contains(searchR)){
            searchOcc.set(searchItm.indexOf(searchR), searchOcc.get(searchItm.indexOf(searchR))+1);
            //searchTim.get(searchItm.indexOf(searchR)).add(timeSince2021());
        } else {
            searchItm.add(searchR);
            searchOcc.add(1);
            //create a new array inside searchTime Array, add current time
            searchTim.add(new ArrayList<Double>());
            //searchTim.get(searchItm.size()-1).add(timeSince2021());
        }
        //if (debug) System.out.println("search for: "+searchR+" recorded at time "+timeSince2021());
    }

    /**
     * cache all interaction history: searchItm/Occ/Tim and Request
     * @return true if update is sucessful
     */
    private boolean cacheAll(){
        try{
            for (int i=0; i<searchItm.size(); i++) {
                cache(this.searchItm.get(i), "local/CacheItm");
            }
            for (int i=0; i<searchOcc.size(); i++) {
                cache(this.searchOcc.get(i).toString(), "local/CacheOcc");
            }
            for (int i=0; i<searchTim.size(); i++) {
                cache(this.searchTim.get(i), "local/CacheTim");
            }
        } catch (IOException ExceptionCache){
            System.out.println("Mandatory File CacheItm/Occ/Request/Tim removed!");
            return false;
        }
        return true;
    }
    /**
     * store request time to cache, each request stores to a new line
     * @return if store is sucessful, if not check location
     */
    private boolean cacheReq(){
        try {
            File file = new File("local/CacheRequest");
            FileWriter write = new FileWriter(file, true);
           // write.append(this.timeSince2021()+ "\n");
            write.close();
            //System.out.println("req time save to file "+timeSince2021());
        }catch (IOException Exception){
            System.out.println("Error with cacheReq");
            return false;
        }
        return true;
    }

    /**
     * store String to cache, each request stores to a new line
     * @param toCache String to store, not NUll, not empty
     * @param location Location to store,
     * @return if store is sucessful, if not check location
     */
    private static void cache(String toCache, String location) throws IOException{
        File file = new File(location);
        FileWriter write = new FileWriter(file, true);
        write.append(toCache+"\n");
        write.close();
    }

    /**
     * store Array to cache, each request stores to a new line
     * @param toCache Array to store, do not contain NUll, not empty, do not contain ", ", "[]"
     * @param location Location to store,
     * @return if store is sucessful, if not check location
     */
    private static void cache(ArrayList toCache, String location) throws IOException{
        File file = new File(location);
        FileWriter write = new FileWriter(file, true);
        write.append(toCache+"\n");
        write.close();
    }

    /**
     * restore previous cache to searchItm/Occ/Tim and request
     * require the file to not conatin empty line and follow format outlined in cache
     * @return if restore is sucessful and whether format match RI
     */
    private boolean restore(){
        try {
            File Itm = new File("local/CacheItm");
            Scanner SItm = new Scanner(Itm);
            while (SItm.hasNextLine()){
                searchItm.add(SItm.nextLine());
            }
            SItm.close();

            File Occ = new File("local/CacheOcc");
            Scanner SOcc = new Scanner(Occ);
            while (SOcc.hasNextLine()){
                searchOcc.add(Integer.parseInt(SOcc.nextLine()));
            }
            SOcc.close();

            String tmpLine;
            ArrayList<Double> tmpArr = new ArrayList<Double>();
            File Tim = new File("local/CacheTim");
            Scanner STim = new Scanner(Tim);
            while (STim.hasNextLine()){
                tmpLine=STim.nextLine();
                tmpLine=tmpLine.substring(1, tmpLine.length()-1);
                if (debug) System.out.println("CacheTim read "+tmpLine);
                tmpArr.addAll(DoubleStream.of(Arrays.stream(tmpLine.split(", ")).mapToDouble(Double::parseDouble).toArray()).boxed().collect(Collectors.toList()));
                searchTim.add(tmpArr);
            }
            STim.close();

            File Request = new File("local/CacheRequest");
            Scanner SRequest = new Scanner(Request);
            while (SRequest.hasNextLine()){
                this.request.add(Double.parseDouble(SRequest.nextLine()));
                if (debug)System.out.println("request when restore: "+request);
            }
            SRequest.close();

        } catch (IOException Exception){
            System.out.println("Mandatory File CacheItm/Occ/Request/Tim removed!");
            return false;
        }
        return true;
    }

}
