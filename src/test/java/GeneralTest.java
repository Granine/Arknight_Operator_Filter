import General.ActionTrack;
import General.ReadWriteData;
import General.StaticHelper;
import Operator.Operator;

import java.util.ArrayList;
import java.util.Arrays;

public class GeneralTest implements Runnable{

    public static void main (String args[]){
        ActionTrack main=new ActionTrack("test");
        String name="name";
        int rarity=9;
        String ID="ID";
        ArrayList<String> RC = new ArrayList<String>(Arrays.asList("a1", "a2"));
        ArrayList<String> AB = new ArrayList<String>(Arrays.asList("b1", "b2"));
        /*
        //General.ReadWriteData.killHistory();
        Scanner keyboard = new Scanner(System.in);
        //prompt entry
        System.out.println("Enter Operator Name to save");
        name = keyboard.nextLine();
        System.out.println("Enter "+name+"'s Rarity to save");
        rarity = keyboard.nextInt();
        //keyboard.nextLine();
        System.out.println("Enter Operator ID to save");
        ID = keyboard.nextLine();
        do {
            System.out.println("Enter RC tag, enter done to finish");
            RC.add(keyboard.nextLine());
        } while (!RC.get(RC.size()-1).equals("done"));
        //System.out.println(RC.get(RC.size()-1));
        //System.out.println(RC.get(RC.size()-1).equals("done"));
        RC.remove(RC.size()-1);
        do  {
            System.out.println("Enter AB tag, enter done to finish");
            AB.add(keyboard.nextLine());
        } while (!AB.get(AB.size()-1).equals("done"));
        AB.remove(AB.size()-1);
         */

        StaticHelper.killHistory();
        Operator operator = new Operator(name, rarity, ID, RC, AB);
        operator.opDataArray();
        //General.ReadWriteData.storeOpData(operator);
        ArrayList<Operator> op=new ArrayList<Operator>();
        for (int i=0; i<100000; i++){
            op.add(operator);
        }
        ReadWriteData.storeAllOpData(op);
        /*
        try {
            TimeUnit.NANOSECONDS.sleep(300000000); //0.3 sec
        }catch(InterruptedException e){
            System.out.println("delay exception");
        }

         */

        main.actionEnd();
    }


    @Override
    public void run() {

    }
}
