package Operator;

import General.StaticHelper;
import java.util.*;

public class Operator implements OperatorSKL {
    private boolean debug=true;
    private String operatorName;
    private int operatorRarity;
    private String operatorID;
    private ArrayList<String> rcTag = new ArrayList<String>();
    private ArrayList<String> abTag = new ArrayList<String>();

    //create empty operator
    public Operator(){
        operatorName="unnamed";
        operatorRarity=0;
        operatorID="no ID";
    }
    //create operator with tag
    public Operator(String name, int rarity, String id, ArrayList rcTag, ArrayList abTag){
        operatorName=name;
        operatorRarity=rarity;
        operatorID=id;
        this.abTag.addAll(abTag);
        this.rcTag.addAll(rcTag);
        System.out.println(operatorRarity+" star operator "+operatorName+" created with Ability:\n"+this.abTag+"\nRecruitment Tag:\n"+this.rcTag+"\n");
    }

    public boolean changeOperatorName(String newName) {
        operatorName = newName;
        return true;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public int getOperatorRarity() {
        return operatorRarity;
    }
    public String getOperatorID() {
        return operatorID;
    }

    public ArrayList<String> getAbTag() {
        if (StaticHelper.debug)System.out.println(abTag.size());
        return abTag;
    }
    public ArrayList<String> getRcTag() {
        if (StaticHelper.debug)System.out.println(rcTag.size());
        return rcTag;
    }

    public void addAbTag(ArrayList abTag) {
        this.abTag.addAll(abTag);
    }

    public void addRcTag(ArrayList rcTag) {
        this.rcTag.addAll(rcTag);
    }

    /**
     *
     * @return Arraylist of format NAME + RARITY + ID + RCTAG + ABTAG
     * where RC and AB are separated by "+"
     */
    public ArrayList<String> opDataArray() {
        ArrayList<String> data = new ArrayList<String>(Arrays.asList(operatorName, Integer.toString(operatorRarity), operatorID));
        data.add(rcTag.stream().reduce("",(String x, String y)->x+"+"+y));
        if (StaticHelper.debug)System.out.println(data.get(3).length());
        data.add(abTag.stream().reduce("",(String x, String y)->x+"+"+y));
        if(debug)System.out.println(data);
        return data;
    }

}
