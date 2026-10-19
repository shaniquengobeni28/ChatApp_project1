package com.mycompany.chatapp_part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatApp_part1 {

    private static List<Message> messageList = new ArrayList<>();
    private static int totalSentCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login loginSystem = new Login();

        System.out.println("--- User Registration ---");
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        String regStatus = loginSystem.registerUser(username, password, firstName, lastName);
        System.out.println(regStatus);

        if (!regStatus.equals("User registered successfully.")) {
            System.out.println("Registration failed. Exiting application.");
            return;
        }

        System.out.println("\n--- User Login ---");
        System.out.print("Enter Username: ");
        String loginUser = scanner.nextLine();
        System.out.print("Enter Password: ");
        String loginPass = scanner.nextLine();

        boolean loggedIn = loginSystem.loginUser(loginUser, loginPass);
        System.out.println(loginSystem.returnLoginStatus(loggedIn));

        if (!loggedIn) {
            System.out.println("Access Denied.");
            return;
        }

        System.out.println("\nWelcome to QuickChat.");

        boolean quit = false;

        while (!quit) {
            System.out.println("\n--- QuickChat Menu ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    System.out.print("How many messages would you like to enter? ");
                    int numMessages = scanner.nextInt();
                    scanner.nextLine(); 

                    for (int i = 1; i <= numMessages; i++) {
                        System.out.println("\n--- Entering Message " + i + " of " + numMessages + " ---");

                        String recipient = "";
                        boolean validRecipient = false;
                        while (!validRecipient) {
                            System.out.print("Enter Recipient Cell Number (e.g. +27718693002): ");
                            recipient = scanner.nextLine();
                            Message tempMsg = new Message(i, recipient, "test");
                            if (tempMsg.checkRecipientCell()) {
                                validRecipient = true;
                                System.out.println("Cell number successfully captured.");
                            } else {
                                System.out.println("Cell number is incorrectly formatted or exceeds 10 characters. Please re-enter.");
                            }
                        }

                        String text = "";
                        boolean validText = false;
                        while (!validText) {
                            System.out.print("Enter Message text (max 250 characters): ");
                            text = scanner.nextLine();
                            Message tempMsg = new Message(i, recipient, text);
                            if (tempMsg.checkMessageLength()) {
                                validText = true;
                                System.out.println("Message sent");
                            } else {
                                System.out.println("Please enter a message of less than 250 characters.");
                            }
                        }

                        Message msg = new Message(i, recipient, text);

                        System.out.println("\nChoose Message Action:");
                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message");
                        System.out.print("Selection: ");
                        int actionChoice = scanner.nextInt();
                        scanner.nextLine();

                        String actionResponse = msg.SentMessage(actionChoice);
                        System.out.println(actionResponse);

                        if (actionChoice == 1) {
                            totalSentCount++;
                            messageList.add(msg);
                            System.out.println("\n--- Message Details ---");
                            System.out.println(msg.printMessageDetails());
                        } else if (actionChoice == 3) {
                            System.out.println(msg.storeMessageJSON());
                        }
                    }
                    break;

                case 2:
                    System.out.println("Coming Soon.");
                    break;

                case 3:
                    quit = true;
                    System.out.println("\nTotal messages sent during this session: " + returnTotalMessages());
                    System.out.println("Thank you for using QuickChat!");
                    break;

                default:
                    System.out.println("Invalid option. Please select 1, 2, or 3.");
            }
        }
    }

    public static String printMessages() {
        if (messageList.isEmpty()) {
            return "No messages sent yet.";
        }
        StringBuilder sb = new StringBuilder();
        for (Message m : messageList) {
            sb.append(m.printMessageDetails()).append("\n------------------\n");
        }
        return sb.toString();
    }

    public static int returnTotalMessages() {
        return totalSentCount;
    }
}