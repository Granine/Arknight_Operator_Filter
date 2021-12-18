package General;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class StaticHelper {

    public static final boolean debug=true;
    /**
     * store String to cache, each request stores to a new line
     * @param toCache String to store, not NUll, not empty
     * @param location Location to store,
     * @return if store is sucessful, if not check location
     * throws IOException is the file cannot be opened
     */
    public static void cache(String toCache, String location) throws IOException {
        File file = new File(location);
        FileWriter write = new FileWriter(file, true);
        write.append(toCache+"\n");
        write.close();
    }
    /**
     * store String to cache, each request stores to a new line free of exceptiions
     * @param toCache String to store, not NUll, not empty
     * @param location Location to store,
     * @return if store is sucessful, if not check location
     */
    public static void cacheSafe(String toCache, String location) {
        try {
        File file = new File(location);
        FileWriter write = new FileWriter(file, true);
        write.append(toCache+"\n");
        write.close();}
        catch (IOException e){
            System.out.println("IOException at cacheSafe when storing "+toCache);
        }
    }
    /**
     * erase all file history/c,vache/timestamp, EVERYTHING
     * @return true if sucessful
     */
    public static boolean killHistory(){
        try {
            File ab = new File("Data/OperatorData/OperatorABTag");
            PrintWriter sab = new PrintWriter(ab);
            sab.close();
            File nm = new File("Data/OperatorData/OperatorName");
            PrintWriter snm = new PrintWriter(nm);
            snm.close();
            File id = new File("Data/OperatorData/OperatorID");
            PrintWriter sid = new PrintWriter(id);
            sid.close();
            File rt = new File("Data/OperatorData/OperatorRarity");
            PrintWriter srt = new PrintWriter(rt);
            srt.close();
            File rc = new File("Data/OperatorData/OperatorRCTag");
            PrintWriter src = new PrintWriter(rc);
            src.close();
            File ach = new File("Data/OperatorData/ActionHistory");
            PrintWriter sach = new PrintWriter(ach);
            sach.close();

            //File Request = new File("local/CacheRequest");
            //PrintWriter SRequest = new PrintWriter(Request);
            //SRequest.close();

        } catch (IOException Exception){
            System.out.println("Mandatory File removed");
            return false;
        }
        return true;
    }
}
