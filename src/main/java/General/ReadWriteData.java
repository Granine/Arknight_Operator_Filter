package General;

import Operator.Operator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
 * read and write data to record using thread
 */
 class WriteDataThread implements Runnable{
    private static boolean debug=true;
    private ArrayList<Operator> opList;
    private String fileName=null;
    public WriteDataThread(ArrayList<Operator> toSave, String fileName) {
        opList =toSave;
        this.fileName=fileName;
    }
    /**
     * store String to cache, each request stores to a new line
     * @param opList String to store, not NUll, not empty
     * @param fileName Location to store,
     * @return if store is sucessful, if not check location
     */
    public static void cacheAll (ArrayList<Operator> opList, String fileName) throws IOException {
        File file = new File(fileName);
        FileWriter write = new FileWriter(file, true);
        //System.out.println(fileName+opList);
        switch (fileName.substring(fileName.length()-4)){
            case "Name":
                for (Operator i: opList){
                    write.append(i.getOperatorName()+"\n");
                }
                break;
            case "rity":
                for (Operator i: opList){
                    write.append(Integer.toString(i.getOperatorRarity())+"\n");
                }
                break;
            case "orID":
                for (Operator i: opList){
                    write.append(i.getOperatorID()+"\n");
                }
                break;
            case "CTag":
                for (Operator i: opList){
                    write.append(i.getRcTag().stream().reduce("",(String x, String y)->x+"+"+y)+"\n");
                }
                break;
            case "BTag":
                for (Operator i: opList){
                    write.append(i.getAbTag().stream().reduce("",(String x, String y)->x+"+"+y)+"\n");
                }
                break;
            default:
                if  (debug) System.out.println("no match while saving to "+fileName);
               throw new IOException();
        }
        //System.out.println(fileName+opList);
        write.close();
    }
    @Override
    public void run() {
        try {
            this.cacheAll(opList, fileName);
        }catch(IOException e){
            if  (debug) System.out.println("IOE exception while saving "+fileName);
        }
    }
}
public class ReadWriteData {
private static boolean debug=true;

    /**
     * save a single opertor to file
     * @param operator save a single operator to file
     * @return is the save sucessed
     */
    public static boolean storeOpData (Operator operator) {
        try {
            //save name
            cache(operator.getOperatorName(), "Data/OperatorData/OperatorName");
            cache(Integer.toString(operator.getOperatorRarity()), "Data/OperatorData/OperatorRarity");
            cache(operator.getOperatorID(), "Data/OperatorData/OperatorID");
            cache(operator.getRcTag().stream().reduce("",(String x, String y)->x+"+"+y), "Data/OperatorData/OperatorRCTag");
            cache(operator.getAbTag().stream().reduce("",(String x, String y)->x+"+"+y), "Data/OperatorData/OperatorABTag");
        }catch (IOException e){
            if  (debug) System.out.println("IOE exception while saving "+operator.opDataArray());
            return false;
        }
        return true;
    }

    /**
     * each thread stores a type of data
     * @param allData
     * @return
     */
    public static boolean storeAllOpData (ArrayList<Operator> allData) {
        //save name
        WriteDataThread opName =new WriteDataThread(allData, "Data/OperatorData/OperatorName");
        Thread threadName=new Thread(opName);
        threadName.start();
        //save rarity
        WriteDataThread opRarity =new WriteDataThread(allData, "Data/OperatorData/OperatorRarity");
        Thread threadRarity=new Thread(opRarity);
        threadRarity.start();
        //save id
        WriteDataThread opID =new WriteDataThread(allData, "Data/OperatorData/OperatorID");
        Thread threadID=new Thread(opID);
        threadID.start();
        //save rc tag
        WriteDataThread opRC =new WriteDataThread(allData, "Data/OperatorData/OperatorRCTag");
        Thread threadRC=new Thread(opRC);
        threadRC.start();
        //save ab tag
        //not using a new thread at it would be wasted
        WriteDataThread opAB =new WriteDataThread(allData, "Data/OperatorData/OperatorABTag");
        opAB.run();
        //Thread threadAB=new Thread(opAB);
        //threadAB.start();


        return true;

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
