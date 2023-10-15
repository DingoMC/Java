package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean ok = false;
        Scanner sc = new Scanner(System.in);
        while (!ok) {
            System.out.println("- Formularz rejestracynjny -");
            System.out.print("Podaj imie: ");
            String name = sc.nextLine();
            if (name.isEmpty()) name = null;
            System.out.print("Podaj nazwisko: ");
            String surname = sc.nextLine();
            if (surname.isEmpty()) surname = null;
            System.out.print("Podaj email: ");
            String email = sc.nextLine();
            if (email.isEmpty()) email = null;
            System.out.print("Podaj haslo: ");
            String password = sc.nextLine();
            if (password.isEmpty()) password = null;
            try {
                User u = new User(name, surname, email, password);
                System.out.println("Zarejestrowano. Witaj " + u.getName() + " " + u.getSurname() + "!");
                ok = true;
            } catch (Exception e) {
                System.out.println("Formularz nieprawidlowy!");
            }
        }
    }
}
