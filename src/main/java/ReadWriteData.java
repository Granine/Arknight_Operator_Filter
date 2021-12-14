
import Operator.Operator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * read and write data to record using thread
 */
public class ReadWriteData implements Runnable{
    @Override
    public void run() {

    }
    public ReadWriteData (Operator operator){

    }

    public static boolean writeLot(){

        return true;
    }
    /**
     * store String to cache, each request stores to a new line
     * @param toCache String to store, not NUll, not empty
     * @param location Location to store,
     * @return if store is sucessful, if not check location
     */
    private static void cache(String toCache, String location) throws IOException {
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
               // searchItm.add(SItm.nextLine());
            }
            SItm.close();

        } catch (IOException Exception){
            System.out.println("Mandatory File CacheItm/Occ/Request/Tim removed!");
            return false;
        }
        return true;
    }

    /**
     * erase all file history/c,vache/timestamp, EVERYTHING
     * @return true if sucessful
     */
    public static boolean killHistory(){
        try {
            File Itm = new File("Data/ActionHistory");
            PrintWriter SItm = new PrintWriter(Itm);
            SItm.close();
            //File Request = new File("local/CacheRequest");
            //PrintWriter SRequest = new PrintWriter(Request);
            //SRequest.close();

        } catch (IOException Exception){
            System.out.println("Mandatory File CacheItm/Occ/Request/Tim removed!");
            return false;
        }
        return true;
    }
}
