package src.Controller;

import src.Database.UserDatabase;

public class Main {
    public static void main(String[] args) {
        UserDatabase userDatabase = new UserDatabase();
        RegistrationController registrationController = new RegistrationController(userDatabase);
    }
}
