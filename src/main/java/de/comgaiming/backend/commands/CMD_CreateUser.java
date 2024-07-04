package de.comgaiming.backend.commands;

import de.comgaiming.backend.BackendApplication;
import de.comgaiming.backend.user.User;

public class CMD_CreateUser {

    public static void onCommand(){

        String username, password, email, groupid;
        password = BackendApplication.getInternalMethods().createPassword(16, "abcdefghijklmnopqrstuvwxyz","ABCDEFGHIJKLMNOPQRSTUVWXYZ","-.,;.-!0123456789");
        do{
            BackendApplication.getLogger().logInfo("What is the name of the new user?");
            System.out.print("> ");
            username = BackendApplication.getScanner().nextLine();
            switch (username)
            {
                case "backend", "root":
                    BackendApplication.getLogger().logError("You can not create the user " + username + ".");
                    break;
            }
        }while (username.equalsIgnoreCase(null));
        do {
            BackendApplication.getLogger().logInfo("Wie lautet die Emailadresse des neuen Users?");
            System.out.print("> ");
            email = BackendApplication.getScanner().nextLine();
        }while (email.equalsIgnoreCase(null));
        do {
            BackendApplication.getLogger().logInfo("Wie lautet die Gruppenid des neuen Users?");
            System.out.print("> ");
            groupid = BackendApplication.getScanner().nextLine();
        }while (groupid.equalsIgnoreCase(null));
        User user = new User();
        user.setUsername(username);
        user.setFirstpassword(password);
        user.setEmail(email);
        user.createUser(email);
    }
}