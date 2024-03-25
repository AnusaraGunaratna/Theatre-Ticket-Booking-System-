import java.io.FileReader;  //Importing the packages.
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class Theatre {
    static int[] row1={0,0,0,0,0,0,0,0,0,0,0,0};    //The main 3 arrays where all the main changes occur when running the program
    static int[] row2={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    static int[] row3={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    static int rowNum, seatNum, price, quit;    //static variables used within the program.
    static Person person_info;  // Static object created in the 'Person' class.
    static Ticket ticket_details;   // Static object created in the 'Ticket' class.
    static ArrayList <Ticket> information = new ArrayList <Ticket> ();  //An arraylist created to store the input information.
    static ArrayList <Ticket> sorted_info = new ArrayList <Ticket> ();  //An arraylist created to store the data when sorting all the data in ascending order.

    public static void main(String[] args) {
        System.out.println("\nWelcome to the New Theatre !");
        System.out.println("--------------------------------------------------\n" +
                "Please select an option: \n" +
                "1) Buy a ticket\n" +
                "2) Print seating area\n" +
                "3) Cancel ticket\n" +
                "4) List available seats\n" +
                "5) Save to file\n" +
                "6) Load from file\n" +
                "7) Print ticket information and total price\n" +
                "8) Sort tickets by price\n" +
                "   0) Quit\n" +
                "--------------------------------------------------");

        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("\nEnter option:");
                int option = input.nextInt();
                System.out.print("\n");

                switch (option) {
                    case 1:
                        System.out.println("* Ticket Booking *\n");
                        buy_ticket(true,0);
                        break;
                    case 2:
                        System.out.print("     ***********\n"+
                                         "     *  STAGE  *\n"+
                                         "     ***********\n"+"\n");
                        System.out.print("    ");
                        print_seating_area(row1,6);
                        System.out.println("\n");
                        System.out.print("  ");
                        print_seating_area(row2,8);
                        System.out.println("\n");
                        print_seating_area(row3,10);
                        System.out.println("\n");
                        break;
                    case 3:
                        System.out.println("* Ticket cancellation *\n");
                        cancel_ticket();
                        break;
                    case 4:
                        System.out.println("* Available Seats *\n");
                        show_available(row1,1);
                        show_available(row2,2);
                        show_available(row3,3);
                        break;
                    case 5:
                        save();
                        break;

                    case 6:
                        load();
                        break;

                    case 7:
                        System.out.println("* Ticket information and total price. *\n");
                        show_tickets_info();
                        break;

                    case 8:
                        System.out.println("* Tickets Sorted. *\n");
                        sort_tickets();
                        break;

                    case 0:
                        System.out.println("Are you sure you want to quit?\n" +
                                "1) Yes\n"+
                                "2) No\n");
                        System.out.print("Please enter (1 or 2) :");
                        quit = input.nextInt();                     //this option quits the program.
                        break;

                    default:
                        System.out.println("Not an option, Please re-enter.");
                        continue;

                }
            } catch (Exception e) {
                System.out.println("Invalid input, Please re-enter.");
            }
            if (quit ==1){
                System.out.println("\n* End *");
                break;}
            else if (quit==2)
               continue;
        }
    }

    //This method is used for buying the ticket.
    public static void buy_ticket(boolean buy , int cancel_ticket){
        while (true) {
            Scanner input2 = new Scanner(System.in);
            if (cancel_ticket==0) {             //validation to check if the user wants to cancel or buy a ticket.
                System.out.print("Input your name: ");
                String name = input2.nextLine();
                System.out.print("Input your surname: ");
                String surname = input2.nextLine();
                String email;

                while (true) {
                    System.out.print("Input your email: ");
                    email = input2.nextLine();
                    if (email.contains("@")) {      //email validation.
                        if (email.contains(".")) {
                            price_validation();       // A new method 'price_validation' is called to avoid repetition.
                            person_info = new Person(name, surname, email);
                            break;
                        } else
                            System.out.println("    Invalid E-mail format.");
                        continue;
                    } else
                        System.out.println("    Invalid E-mail format.");
                    continue;
                }
            }

            System.out.println("Please input the row number and seat number:-");
            try {
                while (true){
                    System.out.print("  Row number: ");
                    rowNum = input2.nextInt();
                    if (rowNum < 0 || rowNum > 3) {
                        System.out.println("Invalid row number, Please re-enter a valid row number (1, 2, 3). ");
                        }
                    else
                        break;
                }
                switch (rowNum){
                    case 1:
                        seat_validation(12,row1,buy);       // A new method 'seat_validation ' is called to avoid repetition.
                        break;
                    case 2:
                        seat_validation(16,row2,buy);
                        break;
                    case 3:
                        seat_validation(20,row3,buy);
                        break;
                }

                if (cancel_ticket ==0) {
                    ticket_details = new Ticket(rowNum, (seatNum + 1), price, person_info);
                    information.add(ticket_details);
                    sorted_info.add(ticket_details);
                }
                break;

            }catch(Exception e){
                System.out.println("Invalid input, Please re-enter.\n");
            }


        }
    }

    //This below method is used to validate the seat number and also to cancel a ticket when needed.
    public static void seat_validation(int totalSeats, int[] row_in, boolean buy) {
        while (true) {
            try {
                Scanner input3 = new Scanner(System.in);
                System.out.print("  Seat number: ");
                seatNum = input3.nextInt();
                if (seatNum > totalSeats || seatNum < 0) {
                    System.out.println("Invalid seat number, Please re-enter a valid seat number (1 - "+totalSeats+").");
                    continue;
                    }
                seatNum = seatNum - 1;
                if (buy == true){           //Buying a new ticket.
                    if (row_in[seatNum] != 1) {
                        row_in[seatNum] = 1;
                        System.out.println("Booking of seat " + (seatNum+1) + " is complete.\n");
                    }else {
                        System.out.println("This seat is already booked, Please book another.\n");
                        continue;
                    }
                }else if (buy == false){            //cancelling a booked ticket
                    if (row_in[seatNum] != 1){
                        System.out.println("This seat is still not booked.");
                        break;
                    }
                    System.out.println("Do you want to cancel this booking ?\n" +
                            "1) Yes\n"+
                            "2) No\n");
                    System.out.print("Please enter (1 or 2) :");
                    int yes_no = input3.nextInt();
                    if (yes_no==1){
                        row_in[seatNum] = 0;
                        if (information.size()>0) {                         //Data collected for ''show_tickets_info' and 'sort_tickets'.
                            for (int i = 0; i <= information.size(); i++) {
                                if (information.get(i).row == rowNum && information.get(i).seat == seatNum + 1) {
                                    information.remove(i);
                                    sorted_info.remove(i);
                                    System.out.println("Booking of " + "seat " + (seatNum + 1) + " is cancelled.");
                                    break;
                                }
                            }
                        }else {
                            System.out.println("Booking of " + "seat " + (seatNum + 1) + " is cancelled.");
                            break;
                        }
                        break;
                    }else if (yes_no == 2){
                        break;
                    }else{
                        System.out.println("Invalid option!");
                        continue;
                        }
                    }
                    break;

            } catch (Exception e) {
                System.out.println("Invalid input, Please re-enter\n");
            }

        }
    }

    //This below method is used to check if input price is an integer.
    public static void price_validation(){
        while(true) {
            try {
                Scanner input4 = new Scanner(System.in);
                System.out.print("Input the price: ");
                price = input4.nextInt();
                break;
            }catch (Exception e){
                System.out.println("    Invalid input.");
            }
        }

    }

    //This below method is used to print the seating area.
    public static void print_seating_area(int [] printRow, int x){
        int count = 1;
        for (int i : printRow){
            if (i == 0)
                System.out.print("O");
            else if (i == 1)
                System.out.print("X");
            if (count == x)
                System.out.print(" ");
            count++;
        }
    }

    //This below method is used to cancel a booked ticket.
    public static void cancel_ticket(){
        buy_ticket(false,1);
    }                                   //Ticket cancellation is done in the buy_ticket method as well to avoid code repetition.


    //This below method is used to show available seats.
    public static void show_available(int [] row_show, int row_num){
        int num = 0;
        ArrayList <Integer> available = new ArrayList ();
        System.out.print("Seats available in row " + row_num + " : ");

        while (num <= row_show.length-1) {
            if (row_show[num] == 0) {
                available.add(num+1);
            }
            num++;
        }
        String available_2 = Arrays.toString(available.toArray()).replace("[", "").replace("]", "").replace(" ", "");
        System.out.println((available_2));
    }

    //This method will save the all rows information in a text file.
    public static void save (){
        try {
            FileWriter info_file = new FileWriter("Theatre information.txt");     //A file named 'Theatre information' is created in the same folder.
            info_file.write("[");
            for (int num : row1){
                info_file.write(Integer.toString(num));
            }
            info_file.write("]");
            info_file.write("[");
            for (int num : row2)
                info_file.write(Integer.toString(num));
            info_file.write("]");
            info_file.write("[");
            for (int num : row3)
                info_file.write(Integer.toString(num));
            info_file.write("]");
            info_file.write("\n");

            info_file.close();
            System.out.print("File created ('Theatre information').\n");

        }catch(IOException e){
            System.out.println("An error occurred when creating the file.");
        }
    }

    //This method is used to load the row information from the text file created.
    public static void load (){
        try {
            FileReader info_file = new FileReader("Theatre information.txt");
            Scanner file_reader = new Scanner (info_file);
            while (file_reader.hasNext()) {
                String text = file_reader.next();
                String new_text = text.replace("[", "").replace("]", "");
                load_2(new_text,12,0,row1);
                load_2(new_text,16,12,row2);                //A new method named 'load_2' is called to avoid code repetition.
                load_2(new_text,20,28,row3);
            }
            System.out.println("'Theatre information' file is loaded.");
            info_file.close();
        }catch (IOException e){
            System.out.println("Error while loading up the file !");
        }
    }

    //This method replaces the row information.
    public static void load_2(String load_text, int row_max, int new_counter, int [] load_row) {
        int read_char;
        int counter = new_counter;
        for (int i = 0; i < row_max; i++) {
            read_char = Character.getNumericValue(load_text.charAt(counter));
            if (read_char == 1) {
                load_row[i] = 1;
            } else if (read_char == 0) {
                load_row[i] = 0;
            }
            counter++;
        }
    }

    //This method is used to show the ticket information and total price.
    public static void show_tickets_info () {
        int total_price = 0;
        int counter = 0;
        for (Ticket tickets : information) {
            tickets.print();
            System.out.println("---------------------------------------");
            total_price = total_price + information.get(counter).price;
            counter++;
        }
        System.out.println("Total price of all tickets : " + "\u00A3" + total_price);
    }

    //This method is used to sort the information in the ascending order according to its price.
    public static void sort_tickets (){
        ArrayList <Ticket> sort_array = information;
        for (int count_1 = 0; count_1<sort_array.size();count_1 ++){
            for (int count_2 = count_1; count_2 < sort_array.size();count_2++){
                if (sort_array.get(count_1).price> sort_array.get(count_2).price) {
                    Ticket temp = sort_array.get(count_1);
                    sort_array.set(count_1, sort_array.get(count_2));
                    sort_array.set(count_2,temp);
                }
            }
        }
        for (Ticket ticket_print : sort_array){
            ticket_print.print();
            System.out.println("---------------------------------------");
        }
    }

}

