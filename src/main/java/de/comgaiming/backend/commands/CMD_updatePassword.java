package de.comgaiming.backend.commands;

import de.comgaiming.backend.BackendApplication;
import de.comgaiming.backend.user.User;

public class CMD_updatePassword {

    public static void onCommand(){
        User user = new User();
        BackendApplication.getLogger().logInfo("Wie heißt der User, welcher ein neues Passwort bekommen soll?");
        String username;
        username = BackendApplication.getScanner().nextLine();
        System.out.println(user.isUserExists(username));
        if(user.isUserExists(username)) {
            user.updatePassword(username);
        }else{
            BackendApplication.getLogger().logInfo("Der User " + username + " existiert nicht.");
        }
    }

}
