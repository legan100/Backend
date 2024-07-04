package de.comgaiming.backend.commands;

import de.comgaiming.backend.BackendApplication;
import de.comgaiming.backend.user.User;

import java.sql.SQLException;

public class CMD_CheckEmail {

    public static void onCommand(){
        User user = null;
        String eingabe = "";
        BackendApplication.getLogger().logInfo("Which emailadress do you like to check?");
        System.out.print("> ");
        eingabe = BackendApplication.getScanner().nextLine();
        try {
            user.checkEmail(eingabe);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
