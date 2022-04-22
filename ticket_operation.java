import java.util.*;

public class ticket_operation{
    int l=3; //consider our train can have maximum 3 lower berths,
    int u=3; //3 upper berths,
    int m=3; //3 middle berths,
    int rac=2; //2 rac
    int wait=1; //1 waiting list

    Queue<Integer> lowerSeatAvailable = new LinkedList<>(); //this queue will have available seats in lower berths
    Queue<Integer> midSeatAvailable = new LinkedList<>();   //this queue will have available seats in middle berths
    Queue<Integer> upperSeatAvailable = new LinkedList<>(); //this queue will have available seats in upper berths

    Queue<passenger> racList = new LinkedList<passenger>(); //this queue will allocate rac to passengers
    // reason why we took racList because when any seat cancelled in a berth, racList's first person will get the seat

    Queue<passenger> waitList = new LinkedList<passenger>(); ////this queue will allocate rac to passengers
    // reason why we took waitList because when any seat cancelled in rac, waitList's first person will get the rac



    HashMap<Integer, passenger> lowerMap = new HashMap<Integer, passenger>();//Here the lower berths seat number mapped to allocated passenger
    HashMap<Integer, passenger> upperMap = new HashMap<Integer, passenger>();//Here the upper berths seat number mapped to allocated passenger
    HashMap<Integer, passenger> midMap = new HashMap<Integer, passenger>();//Here the middle berths seat number mapped to allocated passenger

    //Initializing available seats
    //since our train can have maximum 3 seats in each, we run loop till 3
    ticket_operation(){
        for(int i=1; i<=3; i++){
            lowerSeatAvailable.add(i);
            midSeatAvailable.add(i);
            upperSeatAvailable.add(i);
        }
    }

    //Lower berth booking function
    public void lowerBook(passenger p){
        p.book='l';
        l=l-1;
        int k = lowerSeatAvailable.remove();
        lowerMap.put(k,p);
        System.out.println("Hello "+p.name+"! your ticket number "+k +" lower is booked.");
    }

    //upper berth booking function
    public void upperBook(passenger p){
        p.book = 'u';
        u=u-1;
        int k = upperSeatAvailable.remove();
        upperMap.put(k,p);
        System.out.println("Hello "+p.name+"! your number "+k +" upper is booked");
    }

    //middle berth booking function
    public void midBook(passenger p){
        p.book='m';
        m=m-1;
        int k = midSeatAvailable.remove();
        midMap.put(k,p);
        System.out.println("Hello "+p.name+"! your number "+k +" middle is booked");
    }

    //RAC booking function
    public void racBook(passenger p){
        p.book = 'r';
        rac = rac-1;
        racList.add(p);
        System.out.println("Hello "+p.name+"! RAC is booked for you.");
    }

    //Waiting List booking function
    public void waitBook(passenger p){
        p.book = 'w';
        wait = wait-1;
        waitList.add(p);
        System.out.println("Hello "+p.name+"! you are in waiting list");
    }
}