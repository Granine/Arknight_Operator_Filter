package src.main.java;Operator;

import java.util.*;

public class Operator implements OperatorSKL {
    private String operatorName;
    private int operatorRarity;
    private ArrayList rcTag = new ArrayList<String>();
    private ArrayList abTag = new ArrayList<String>();
    //create empty operator
    public Operator(){
        operatorName="unnamed";
        operatorRarity=0;
    }
    //create operator with tag
    public Operator(String name, int rarity, ArrayList abTag, ArrayList rcTag){
        operatorName=name;
        operatorRarity=rarity;
        this.abTag.addAll(abTag);
        this.rcTag.addAll(rcTag);
        System.out.println(operatorRarity+" star operator "+operatorName+" created with Ability:\n"+this.abTag+"\nRecruitment Tag:\n"+this.rcTag+"\n");
    }

    public String getOperatorName() {
        return operatorName;
    }

    public int getOperatorRarity() {
        return operatorRarity;
    }

    public ArrayList<String> getAbTag() {
        System.out.println(abTag.size());
        return abTag;
    }
    public ArrayList<String> getRcTag() {
        System.out.println(rcTag.size());
        return rcTag;
    }

    public void addAbTag(ArrayList abTag) {
        this.abTag.addAll(abTag);
    }

    public void addRcTag(ArrayList rcTag) {
        this.rcTag.addAll(rcTag);
    }


}
