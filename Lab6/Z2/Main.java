package org.example;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Tomek", "Domek", "01234567890", 21, "tdomek@gmail.com");
        Person p2 = new Person("Marcin", "Basak", "12345678901", 22, "martin@dingomc.net");
        Person p3 = new Person("Franek", "Majeranek", "23456789012", 20, "fmajeranek@gmail.com");
        Person p4 = new Person();
        p4.setAge(19);
        p4.setName("Janusz");
    }
}
