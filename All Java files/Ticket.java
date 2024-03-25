import java.util.ArrayList;

public class Ticket {
    int row;
    int seat;
    int price;
    Person person_info;

    public Ticket (int row, int seat, int price, Person psn){
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person_info = psn;
    }
    public void print(){
        System.out.println("Name :" + this.person_info.name + "\nSurname :" + this.person_info.surname + "\nE-mail :" + this.person_info.email + "\nPrice :" + this.price + "\nRow :" + this.row + "\nSeat :" + this.seat);
    }


}
