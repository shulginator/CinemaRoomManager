package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int boughtTicketQuantity = 0;
        int soldTicketPrice = 0;
        boolean ticketsBought = false;
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();
        scanner.nextLine(); //reset input
        final int totalSeats = rows * seatsInRow;
        String[] seats = new String[totalSeats];
        //Fill the seats array
        for (int i = 0; i < totalSeats; i++) {
            seats[i] = "S";
        }

        boolean exit = false;
        while (!exit) {
            System.out.println();
            System.out.printf("1. Show the seats %n2. Buy a ticket %n3. Statistics %n0. Exit %n");
            String action = scanner.nextLine();
            switch (action) {
                case "Show the seats":
                case "1":
                    drawSeats(rows, seatsInRow, seats);
                    break;
                case "Exit":
                case "0":
                    exit = true;
                    break;
                case "Buy a ticket":
                case "2":
                    // Loop to check is desired place is free
                    while (!ticketsBought) {
                        System.out.println();
                        System.out.println("Enter a row number:");
                        int rowNumber = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int seatNumber = scanner.nextInt();
                        System.out.println();
                        // Check out of bound index
                        if (rowNumber > rows || seatNumber > seatsInRow) {
                            System.out.println("Wrong input!");
                            continue;
                        }
                        // Find a bought ticket index
                        int boughtTicket = (((rowNumber - 1) * seatsInRow) + seatNumber) - 1;
                        if (seats[boughtTicket].compareTo("B") == 0) {
                            System.out.println("That ticket has already been purchased!");
                            continue;
                        } else {
                            seats[boughtTicket] = "B";
                            boughtTicketQuantity++;
                            ticketsBought = true;
                        }
                        //Calculate ticket price
                        if (totalSeats < 60) {
                            System.out.printf("Ticket price: $%d%n", 10);
                            soldTicketPrice += 10;
                        } else {
                            int firstHalf = rows / 2;
                            if (rowNumber <= firstHalf) {
                                System.out.printf("Ticket price: $%d%n", 10);
                                soldTicketPrice += 10;
                            } else {
                                System.out.printf("Ticket price: $%d%n", 8);
                                soldTicketPrice += 8;
                            }
                        }
                        scanner.nextLine(); //reset input
                    }
                    break;
                case "Statistics":
                case "3":
                    System.out.println();
                    System.out.printf("Number of purchased tickets: %d%n", boughtTicketQuantity);
                    double percentage = 100.0 * ((double)boughtTicketQuantity / (double)totalSeats);
                    System.out.printf("Percentage: %.2f%s%n", percentage, "%");
                    System.out.printf("Current income: $%d%n", soldTicketPrice);
                    calculateIncome(rows, seatsInRow);
                    break;
                default:
                    System.out.println("incorrect input: " + action);
                    break;
            }
            ticketsBought = false;
        }
    }

    public static void drawSeats(int rows, int seats, String[] seatsData) {
        System.out.println("");
        System.out.println("Cinema:");
        System.out.print(" ");
        for (int i = 1; i < seats + 1; i++) {
            System.out.print(" " + i);
        }
        System.out.println("");
        for (int j = 1; j < rows + 1; j++) {
            System.out.print(j);
            for (int s = 1; s < seats + 1; s++) {
                int seatIndex = (s + ((j - 1) * seats) - 1);
                System.out.print(" " + seatsData[seatIndex]);
            }
            System.out.println("");
        }
    }

    public static void calculateIncome(int rows, int seats) {
        int totalSeats = rows * seats;
        if (totalSeats < 60) {
            int totalIncome = totalSeats * 10;
            System.out.printf("Total income: $%d%n", totalIncome);
        } else {
            int firstHalf = rows / 2;
            int secondHalf = rows - firstHalf;
            int totalIncome = firstHalf * seats * 10;
            totalIncome += secondHalf * seats * 8;
            System.out.printf("Total income: $%d%n", totalIncome);
        }
    }
}