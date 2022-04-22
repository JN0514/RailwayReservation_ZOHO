import java.util.*;
public class railway_reservation
{
    Scanner sc = new Scanner(System.in);
    ticket_operation t = new ticket_operation();
    //Booking function
    public void booking(){
        int op=0;
        String name;
        int age;
        char pref;

        //Gets name, age, preference of passenger
        System.out.println("Enter your name:");
        name = sc.next();
        System.out.println("Enter your age:");
        age = sc.nextInt();
        System.out.println("Enter your pref:");
        pref = sc.next().charAt(0);

        //Store the information in passenger object
        passenger p = new passenger(name, age, pref);

        //Check If the passenger's preferred seat available or not
        //If not available allocate on your choice
        //If no ticket available, say no ticket available
        if(pref=='l'){
            if(t.l>0) op = 1;
            else if(t.m>0) op = 2;
            else if(t.u>0) op = 3;
            else if(t.rac>0) op = 4;
            else if(t.wait>0) op=5;
        }
        if(pref=='u'){
            if(t.u>0) op = 3;
            else if(t.m>0) op = 2;
            else if(t.l>0) op = 1;
            else if(t.rac>0) op = 4;
            else if(t.wait>0) op=5;
        }
        if(pref=='m'){
            if(t.m>0) op = 2;
            else if(t.l>0) op = 1;
            else if(t.u>0) op = 3;
            else if(t.rac>0) op = 4;
            else if(t.wait>0) op=5;
        }
        switch (op) {
            case 1 -> t.lowerBook(p);
            case 2 -> t.midBook(p);
            case 3 -> t.upperBook(p);
            case 4 -> t.racBook(p);
            case 5 -> t.waitBook(p);
            default -> System.out.println("No ticket Available");
        }

    }

    //print booked tickets along with passenger's detail
    public void printBooked(){
        System.out.println("ID          NAME            AGE             BERTH     NO");
        for(int i: t.lowerMap.keySet()){
            passenger v = t.lowerMap.get(i);
            System.out.println(v.id +"          "+v.name+"              "+v.age+"           lower       "+i);
        }
        for(int i: t.upperMap.keySet()){
            passenger v = t.upperMap.get(i);
            System.out.println(v.id +"          "+v.name+"              "+v.age+"           upper       "+i);
        }
        for(int i: t.midMap.keySet()){
            passenger v = t.midMap.get(i);
            System.out.println(v.id +"          "+v.name+"              "+v.age+"           middle       "+i);
        }
        for(passenger v: t.racList){
            System.out.println(v.id+"           "+v.name+"              "+v.age+"           RAC");
        }
    }

    //Cancelling ticket based on passenger's ID
    public void cancelling(){
        System.out.println("ENter your ID:");
        int id = sc.nextInt();
        for(int i: t.lowerMap.keySet()){
            passenger v = t.lowerMap.get(i);
            if(v.id == id){
                t.l=t.l+1;
                t.lowerSeatAvailable.add(i);
                t.lowerMap.remove(i);
                System.out.println("Cancelled Successfully!");
                if(t.racList.size()>0){
                    passenger vv = t.racList.remove();
                    int u = t.lowerSeatAvailable.remove();
                    t.rac=t.rac+1;
                    t.lowerMap.put(u,vv);
                    t.l=t.l-1;
                    System.out.println("Lower "+u+" booked for "+vv.name);
                    if(t.waitList.size()>0){
                        passenger vvv = t.waitList.remove();
                        t.racList.add(vvv);
                        t.wait=t.wait+1;
                        t.rac=t.rac-1;
                        System.out.println("RAC booked for "+vvv.name);
                    }
                }
            }
        }
        for(int i: t.upperMap.keySet()){
            passenger v = t.upperMap.get(i);
            if(v.id == id){
                t.upperMap.remove(i);
                t.upperSeatAvailable.add(i);
                t.u=t.u+1;
                System.out.println("Cancelled Successfully!");
                if(t.racList.size()>0){
                    passenger vv = t.racList.remove();
                    int u = t.upperSeatAvailable.remove();
                    t.upperMap.put(u,vv);
                    t.rac=t.rac+1;
                    t.u=t.u-1;
                    System.out.println("Upper "+u +" is booked for "+vv.name);
                    if(t.waitList.size()>0){
                        passenger vvv = t.waitList.remove();
                        t.racList.add(vvv);
                        t.wait=t.wait+1;
                        t.rac=t.rac-1;
                        System.out.println("RAC is booked for "+vvv.name);
                    }
                }
            }
        }
        for(int i: t.midMap.keySet()){
            passenger v= t.midMap.get(i);
            if(v.id == id){
                t.midMap.remove(i);
                t.m=t.m+1;
                t.midSeatAvailable.add(i);
                System.out.println("Cancelled Successfully");
                if(t.racList.size()>0){
                    passenger vv = t.racList.remove();
                    int u = t.midSeatAvailable.remove();
                    t.midMap.put(u,vv);
                    t.m=t.m-1;
                    t.rac = t.rac+1;
                    System.out.println("Middle "+u+" booked for "+vv.name);
                    if(t.waitList.size()>0){
                        passenger vvv = t.waitList.remove();
                        t.racList.add(vvv);
                        t.rac=t.rac-1;
                        t.wait=t.wait+1;
                        System.out.println("RAC booked for "+vvv.name);
                    }
                }
            }
        }
        for(passenger i: t.racList){
            if(i.id == id){
                t.racList.remove(i);
                t.rac = t.rac+1;
                System.out.println("Cancelled Successfully!");
                if(t.waitList.size()>0){
                    passenger v=t.waitList.remove();
                    t.racList.add(v);
                    t.wait=t.wait+1;
                    t.rac = t.rac-1;
                    System.out.println("RAC is booked for "+v.name);
                }
            }
        }
        for(passenger i: t.waitList){
            if(i.id == id){
                t.waitList.remove(i);
                t.wait=t.wait+1;
                System.out.println("Cancelled Successfully!");
            }
        }
    }

    //Print all available tickets
    public void printAvailable(){
        System.out.println("Lower Available seats");
        for(int i: t.lowerSeatAvailable) System.out.print(i+"  ");
        System.out.println();

        System.out.println("upper Available seats");
        for(int i: t.upperSeatAvailable) System.out.print(i+"  ");
        System.out.println();

        System.out.println("Middle Available seats");
        for(int i: t.midSeatAvailable) System.out.print(i+"  ");
        System.out.println();
    }
    public static void main(String[] args) {
        railway_reservation n = new railway_reservation();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Enter");
            System.out.println("1 for booking");
            System.out.println("2 for booked details");
            System.out.println("3 for Available seats");
            System.out.println("4 for cancelling");
            System.out.println("Others for exit");

            switch (sc.nextInt()) {
                case 1 -> n.booking();
                case 2 -> n.printBooked();
                case 3 -> n.printAvailable();
                case 4 -> n.cancelling();
                default -> System.exit(0);
            }
            System.out.println("********************************************");
        }
    }
}
