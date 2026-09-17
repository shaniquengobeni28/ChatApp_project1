package com.mycompany.chatapp_part1;

import java.util.Scanner;

public class ChatApp_part1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login loginSystem = new Login();

        System.out.println("--- User Registration ---");
        
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        String cellNumber = "";
        boolean validCell = false;
        while (!validCell) {
            System.out.print("Enter Cell Phone Number (incl. country code e.g., +27): ");
            cellNumber = scanner.nextLine();
            if (loginSystem.checkCellPhoneNumber(cellNumber)) {
                System.out.println("Cell number successfully captured.");
                validCell = true;
            } else {
                System.out.println("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.");
            }
        }

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        if (loginSystem.checkUserName(username)) {
            System.out.println("Username successfully captured.");
        }

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        if (loginSystem.checkPasswordComplexity(password)) {
            System.out.println("Password successfully captured.");
        }

        String regMessage = loginSystem.registerUser(username, password, firstName, lastName);
        System.out.println(regMessage);

        if (regMessage.contains("successfully")) {
            System.out.println("\n--- User Login ---");
            System.out.print("Enter Username: ");
            String loginUser = scanner.nextLine();
            
            System.out.print("Enter Password: ");
            String loginPass = scanner.nextLine();

            boolean isSuccess = loginSystem.loginUser(loginUser, loginPass);
            System.out.println(loginSystem.returnLoginStatus(isSuccess));
        }
        
        scanner.close();
    }
}


