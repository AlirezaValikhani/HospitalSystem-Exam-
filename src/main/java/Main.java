import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Menu menu = new Menu();

        while(true){
            switch(menu.firstMenu()){
                case 1:
                    menu.signIn();
                    break;

                case 2:
                    /*menu.RegisterMenu();*/
                    break;

                case 3:
                    System.out.println("Have a nice day!");
                    System.exit(0);

                case 0:
                    System.out.println("you enter a wrong number!");
                    break;
            }
        }
    }
}
