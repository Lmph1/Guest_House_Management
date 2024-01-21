
import java.util.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import org.nocrala.tools.texttablefmt.CellStyle.HorizontalAlign;
public class GHM {
    public static void menus(){
        System.out.print("\n\n--------------Guest's House Management System-------------\n");
        System.out.print("1- Check In\n");
        System.out.print("2- Checkout\n");
        System.out.print("3- Display\n");
        System.out.print("4- Search Guest's Name\n");
        System.out.print("5- Exit\n");
        System.out.print("------------------------------------------------------\n");
    }
    public static void DisplayList(int floor, int room,String house[][]){
        System.out.println("---------- Display Guest’s House information ----------");
        for(int i=0;i<floor;i++){
            System.out.print("Floor ");
            System.out.print(i+1 + " : ");
            for(int j=0;j<room;j++){
                if(house[i][j]==null){
                    System.out.print("null\t\t");
                }
                else {
                    System.out.print(house[i][j] + "\t\t");
                }
            }
            System.out.println();
        }
    }
    public static int countPerpage(int floor,int page){
        page+=5;
        if(page>floor){
            return (5-(page-floor));
        }
        return 5;
    }
    public static void DisplayPage(int floor, int room,String house[][]){
        int numberFloor,page=0,option,Cpage=1;
        int TotalPage= (int) (Math.ceil(Double.valueOf(floor)/5.0));

        Scanner sc = new Scanner(System.in);

        System.out.println("---------- Display Guest’s House information ----------");
        System.out.println("Total Pages: "+ TotalPage);
        while (true) {

            System.out.println("\n"+"Perpage: " + countPerpage(floor,page));

            CellStyle numberStyle = new CellStyle(HorizontalAlign.center);
            Table t = new Table(room+1, BorderStyle.UNICODE_BOX_DOUBLE_BORDER,ShownBorders.ALL);
            t.setColumnWidth(0, 8, 20);
            t.addCell("Floor");

            for (int i = 0; i < room; i++) {
                t.addCell(i + 1 + "");
            }
            for (int i = page; i < page+countPerpage(floor,page); i++) {
                numberFloor = i + 1;
                t.addCell("Floor " + numberFloor);
                for (int j = 0; j < room; j++) {
                    if (house[i][j] == null) {
                        t.addCell("null");
                    } else {
                        t.addCell(house[i][j]);
                    }
                }
            }
            System.out.println(t.render());
            System.out.println("Current Page : "+ Cpage);
            System.out.println( "1) First\t" +
                    "2) Next\t" +
                    "3) Previous\t" +
                    "4) Last\t" +
                    "5) Back");
            System.out.print("=> Enter Your Option: ");
            option=sc.nextInt();
            switch (option){
                case 1: page=0;
                    Cpage=1;
                    break;
                case 2: if(Cpage==TotalPage){
                    System.out.println("This is already the last page!");
                    break;
                }
                    Cpage+=1;
                    page+=5;
                    break;
                case 3: if(Cpage==1){
                    System.out.println("This is already the first page!");
                    break;
                }
                    Cpage-=1;
                    page-=5;
                    break;
                case 4: page= (TotalPage-1)*5;
                    Cpage=TotalPage;
                    break;
                case 5: return;
            }
        }
    }
// Main Class
    public static void main(String[] args)
    {
        int floor,room,Totalrooms=0;
        int option;
        int floorNum;
        int roomNum;

        Scanner sc = new Scanner(System.in);

        System.out.print("--------------Setting up Guest's House--------------\n");
        System.out.print("-> Enter number of floors : ");
        floor= sc.nextInt();
        System.out.print("-> Enter number of room in each floor : ");
        room= sc.nextInt();
        Totalrooms=room*floor;
        System.out.print("=> Guest's House is already set up with "+floor+" floors and "+Totalrooms+" rooms successfully.");
        String house[][] =new String[floor][room];

        for(int i=0;i<floor;i++){
            for(int j=0;j<room;j++){
                house[i][j]=null;
            }
        }
        while(true)
        {
            menus();
            System.out.print("-> Choose option(1-5) : ");
            option=sc.nextInt();
            String st = String.valueOf(option);
            Pattern pt = Pattern.compile(("[1-5]"));
            Matcher mt = pt.matcher(st);
            int op = Integer.parseInt(st);
            switch (op)
            {
                case 1: {
                    System.out.print("\n---------- Checking to Guest’s House ----------\n");
                    String guestName;
                    System.out.print("-> Enter floor number(1-"+floor+") : ");
                    floorNum = sc.nextInt();
                    System.out.print("-> Enter room number(1-"+room+") : ");
                    roomNum= sc.nextInt();
                    System.out.print("-> Enter guest's name : ");
                    guestName = sc.next();

                    house[floorNum-1][roomNum-1]= guestName;
                    System.out.print("=> " +guestName + " checkin successfully!");
                }
                break;
                case 2: {
                    int checkout;
                    System.out.println("\n---------- Checkout from Guest’s House ----------");
                    System.out.print("-> Enter floor number(1-"+floor+") : ");
                    floorNum = sc.nextInt();
                    System.out.print("-> Enter room number(1-"+room+") : ");
                    roomNum = sc.nextInt();
                    if(house[floorNum-1][roomNum-1]!=null){
                        System.out.print("-> Guest's Name: " + house[floorNum-1][roomNum-1] + ", Press 1 to checkout and 0 to cancel : ");
                    }
                    else{
                        System.out.println("=> No one checkin in this room, you can't checkout!");
                        continue;
                    }
                    checkout = sc.nextInt();
                    if(checkout==1){
                        house[floorNum-1][roomNum-1]=null;
                        System.out.println("=> " + house[floorNum-1][roomNum-1] + " has been checked out successfully!");
                    }
                }
                break;
                case 3: {
                    System.out.println("1) Show as List");
                    System.out.println("2) Show as Table");
                    System.out.print("=> Enter Your Option: ");
                    int Display = sc.nextInt();
                    switch (Display){
                        case 1: DisplayList(floor,room,house); break;
                        case 2: DisplayPage(floor,room,house); break;
                    }
                }
                break;
                case 4: {
                    boolean found=false;
                    String SName;
                    System.out.println("---------- Search Guest's Name ----------");
                    System.out.println("-> enter guest's name to search : ");
                    SName = sc.next();
                    System.out.println("=> Result of searching : ");
                    for(int i=0;i<floor;i++){
                        for(int j=0;j<room;j++){
                            if (SName.equals(house[i][j])) {
                                System.out.print("Guest's Name : "+ house[i][j] + " is in Room : '");
                                System.out.print(j+1 + "' ");
                                System.out.print("On Floor : '");
                                System.out.print(i+1 + "'");
                                found=true;
                            }
                        }
                    }
                    if(found==false){
                        System.out.println("Guest's Name : ("+ SName + ") Not Stay Here!");
                    }
                }
                break;
                case 5: {
                    System.out.println("-> Good bye!");
                    return;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + op);
            }
        }
    }
}
