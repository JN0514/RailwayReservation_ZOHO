//passenger's information along with preferred and booked seat
public class passenger{
    String name;
    int age;
    char pref;
    char book;
    static int count=0;
    int id;
    passenger(String name, int age, char pref){
        count=count+1;
        id = count;
        this.name = name;
        this.age = age;
        this.pref = pref;
    }
}