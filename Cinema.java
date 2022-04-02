package cinema;

import javax.security.auth.kerberos.KerberosTicket;
import java.util.Scanner;

public class Cinema {
    public static void room(int rows, int seats,char[][] room){
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (i == 0 && j == 0) {
                    room[i][j] = ' ';
                } else if (i == 0) {
                    room[i][j] = (char) (j + '0');
                } else if (j == 0) {
                    System.out.print("\n");
                    room[i][j] = (char) (i + '0');
                } else {
                    room[i][j] = 'S';
                }

            }
        }
    }
    public static void show(int rows, int seats, char[][] room) {
        System.out.print("Cinema: \n");

        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                System.out.print(room[i][j] + " ");

            }
            System.out.print("\n");
        }

    }

    public static void statistics(int rows, int seats, char[][] room) {
        int numbersOfTickets = 0;
        float overall = 0;
        int wholeRoom = rows * seats;
        int total = 0;
        int incomeMoney = 0;
        int polowa = rows / 2;
        if (rows * seats < 60) {
            for (int i = 0; i <= rows; i++) {
                for (int j = 0; j <= seats; j++) {
                    if (room[i + 1][j + 1] == 'B') {
                        numbersOfTickets++;
                        incomeMoney += 10;
                    }
                }
            }
        } else if (rows % 2 == 0) {
            for (int i = 0; i <= rows; i++) {
                for (int j = 0; j <= seats; j++) {
                    if (i >= polowa && room[i + 1][j + 1] == 'B') {
                        numbersOfTickets++;
                        incomeMoney += 8;
                    } else if (room[i + 1][j + 1] == 'B'){
                        numbersOfTickets++;
                        incomeMoney += 10;
                    }
                }
            }
        } else {
            for (int i = 0; i <= rows; i++) {
                for (int j = 0; j <= seats; j++) {
                    if (i >= polowa && room[i + 1][j + 1] == 'B') {
                        numbersOfTickets++;
                        incomeMoney += 8;
                    } else if(room[i + 1][j + 1] == 'B'){
                        numbersOfTickets++;
                        incomeMoney += 10;
                    }
                }
            }
        }
            if (rows * seats < 60) {
                total = rows*seats * 10;
            } else if (rows % 2 == 0) {
                total = polowa * 10 * seats;
                total += polowa * 8 * seats;
            } else {
                total = polowa * 10 * seats;
                total += (polowa + 1) * 8 * seats;
            }
            overall = (float) numbersOfTickets/(float) wholeRoom;
            overall *= 100;
            System.out.printf("Number of purchased tickets: %d \n", numbersOfTickets);
            System.out.printf("Percentage: %.2f%%\n", overall);
            System.out.printf("Current income: $%d \n", incomeMoney);
            System.out.printf("Total income: $%d \n", total);

    }
    public static void buy(int rows, int seats, char[][] room) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a row number:");
        int rows1 = scan.nextInt();
        System.out.print("Enter a seat number in that row:");
        int seats1 = scan.nextInt();
        String osiem = "$8";
        String dziesiec = "$10";
        if (room.length < rows1+2 || room[0].length < seats1+2) {
            System.out.println("Wrong input!");
            return;
        }
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (room[rows1][seats1] == 'B') {
                    System.out.println("That ticket has already been purchased!");
                    buy(rows, seats, room);
                    return;
                }
            }
        }

        int polowa = rows/2;
        if (rows*seats < 60) {
            System.out.print("Ticket price: " + dziesiec + "\n");
        } else if(rows % 2 == 0) {
            if (rows1 >= polowa) {
                System.out.print("Ticket price: " + osiem + "\n");
            } else {
                System.out.print("Ticket price: " + dziesiec + "\n");
            }
        } else {
            if (rows1 > polowa) {
                System.out.print("Ticket price: " + osiem + "\n");
            } else {
                System.out.print("Ticket price: " + dziesiec + "\n");
            }
        }

        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (i + 1 == rows1 && j + 1 == seats1) {
                    room[i+1][j+1] = 'B';
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of rows:");
        int rows = scan.nextInt();
        System.out.print("Enter the number of seats in each row:");
        int seats = scan.nextInt();
        char[][] room = new char[rows+2][seats+2];
        room(rows,seats,room);
        for (int i = 0; i < (rows+2)*(seats+2);i++) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    show(rows,seats,room);
                    break;
                case 2:
                    buy(rows, seats, room);
                    break;
                case 3:
                    statistics(rows, seats, room);
                    break;
                case 0:
                    i = rows*seats+10000;
                    break;
                default:
                    System.out.print("wrong choice!!!\n");
                    break;
            }

        }

    }

}
