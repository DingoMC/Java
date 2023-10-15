package org.example;

import lombok.*;

@Getter @Setter @AllArgsConstructor
public class User {
    private @NonNull String name;
    private @NonNull String surname;
    private @NonNull String email;
    private @NonNull String password;
}
