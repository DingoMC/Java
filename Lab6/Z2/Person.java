package org.example;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Person {
    private String name;
    private String surname;
    private String pesel;
    private int age;
    private String email;
}
