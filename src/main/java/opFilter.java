

import Operator.Operator;
import java.util.*;

public class opFilter {
    //filter by tag
    private ArrayList<String> RcTag = new ArrayList<String>();
    private List<String> AbTag = new ArrayList<String>();
    //filter by status
    private List<Integer> opRarity = new ArrayList<>();
    private List<String> opName = new ArrayList<String>();
    private List<String> opClass = new ArrayList<String>();
    private List<String> opSubClass = new ArrayList<String>();
    //filter by value
    private List<Integer> opATK = new ArrayList<>();
    private List<Integer> opHP = new ArrayList<>();
    private List<Integer> opDP = new ArrayList<>();
    private List<Integer> opRES = new ArrayList<>();
    private List<Integer> opBLK = new ArrayList<>();
    private List<Integer> ATINT = new ArrayList<>();

    //Rep invariant
    //RcTag, AbTag, opRarity, opName, opClass, opClass, opHp, opDp, opATK, opRES, opBLK, opATINT have >0 element
    //RcTag, AbTag, opRarity, opName, opClass, opClass should not contain duplicate
    //RcTag, AbTag, opName, opClass,opSubClass should only contain lowercase alphabet
    //opRarity should only contain values in range of 1 to 6
    //opHp, opDp, opATK, opRES, opBLK, opATINT should only contain no element or evennumber of elements and have value >1
    //opBlk should contain value between 1 and 3

    //Abstraction
    //represents different ways to filter operators

    //Safety from rep exposure

    /**
     * initialize a new full operator search
     */
    public opFilter (){
        RcTag = new ArrayList<String>();
        AbTag = new ArrayList<String>();
        //filter by status
        opRarity = new ArrayList<>();
        opName = new ArrayList<String>();
        opClass = new ArrayList<String>();
        opSubClass = new ArrayList<String>();
        //filter by value
        opATK = new ArrayList<>();
        opHP = new ArrayList<>();
        opDP = new ArrayList<>();
        opRES = new ArrayList<>();
        opBLK = new ArrayList<>();
        ATINT = new ArrayList<>();
        System.out.println("An empty operator search has been created");
    }
    /**
     * initialize a new specific field operator search
     */
    public opFilter (String type) {
        switch (type.toLowerCase()) {
            case "recruit":
                RcTag = new ArrayList<String>();
                System.out.println("An empty operator search of type recruitment tag has been created");
                break;
            case "class":
                opClass = new ArrayList<String>();
                opSubClass = new ArrayList<String>();
                System.out.println("An empty operator search of type filter by classhas been created");
                break;
            default:
                System.out.println("unsupported filter " + type + " try again with full filter mode");
        }
    }

    /**
     * return the current tag filter applied to result
     * @return all of the current tag filter applied (Rc, Ab)
     */
    public Set TagFilter(){
        HashSet currentFilter = new HashSet();
        currentFilter.add((ArrayList)RcTag.clone());
        currentFilter.add(AbTag);
        ArrayList sec_list = new ArrayList();
        sec_list = (ArrayList)RcTag.clone();
        return currentFilter;
    }

    /**
     * check representation invariant holds
     * @return true if held, false if not;
     */
    private boolean RepCheck() {

        return true;
    }

    /* HashSet currentFilter = new HashSet();
        Set currentFilterA = new HashSet();
        currentFilter.add("A");
        currentFilter.add("B");
        currentFilterA.add("A");
        currentFilterA.add("B");
        ArrayList sec_list = new ArrayList();
        System.out.println("??");
        Set<Integer> no= new HashSet<Integer>();
        Object one=no;
        one.size();






     */
    public static void main (String[]args){
//random list
        ArrayList<Operator> opList = new ArrayList<Operator>();
        ArrayList<String> test = new ArrayList<String>(Arrays.asList("test", "A", "B"));
        Operator Siege=new Operator("Siege", 6, new ArrayList(Arrays.asList("DP recovery")), new ArrayList<String>(Arrays.asList("Melee", "Vangsuard")));
        Operator Amiya=new Operator("Amiya", 5, new ArrayList(Arrays.asList("True Damage", "Art Damage")), new ArrayList<String>(Arrays.asList("Range", "Caster")));
        Operator Skadi=new Operator("Skadi", 6, new ArrayList(Arrays.asList("On Deploy")), new ArrayList<String>(Arrays.asList("Melee", "Guard")));
        Operator Coebe=new Operator("Coebe", 6, new ArrayList(Arrays.asList("Art Damage", "Silent")), new ArrayList<String>(Arrays.asList("Range", "Caster")));

        //lambda test
        opList.add(Siege);
        opList.add(Amiya);
        opList.add(Skadi);
        opList.add(Coebe);
        opList.forEach((x)->{x.addAbTag(test);});
        opList.forEach((x)->{System.out.println(x.getOperatorName());});
        opList.sort((x, y)->x.getOperatorName().compareTo(y.getOperatorName()));
        //stream test
        opList.stream().map(Operator::getOperatorName).forEach(System.out::println);
        System.out.println("rarity sum "+opList.stream().map(str -> str.getOperatorRarity()).reduce(0, (x,y)->x+y));
        opList.stream().map(x->x.getAbTag()).forEach(x->System.out.println(x));
        Amiya.getAbTag().stream().map(str -> str.length()).forEach(System.out::println);
        System.out.println("End line");
        Amiya.getRcTag().stream().map(str2 -> str2.length()).forEach(System.out::println);
        System.out.println("Ending");
    }
}

