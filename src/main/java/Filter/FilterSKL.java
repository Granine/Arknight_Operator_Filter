package Filter;

import java.util.*;

public interface FilterSKL {
    //generate a new filter
    public void filter(ArrayList<String> list);
    //public void addOneFilter(String);
    //public void rmvOneFilter(String);
    //add all specified filter
    public void addFilter(List<String> list);
    //remove all specified filter
    public void rmvFilter(List<String> list);
    //return all current filter as list
    public List getCurrentFilter();
    //return all avaliable filter as list
    public List getReadyFilter();
    //remove all filter
    public void rmvAllFilter();
}
