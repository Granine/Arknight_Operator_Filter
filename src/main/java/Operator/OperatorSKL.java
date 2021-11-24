package Operator;

import java.util.ArrayList;

public interface OperatorSKL {
    public String getOperatorName();
    public int getOperatorRarity();
    public ArrayList<String> getAbTag();
    public ArrayList<String> getRcTag();
    public void addAbTag(ArrayList abTag);
    public void addRcTag(ArrayList rctag);
}
