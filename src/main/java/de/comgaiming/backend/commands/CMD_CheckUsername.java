package de.comgaiming.backend.commands;

import de.comgaiming.backend.BackendApplication;
import de.comgaiming.backend.user.User;

import java.sql.SQLException;

public class CMD_CheckUsername {

    public static void onCommand(){
        String eingabe = "";
        BackendApplication.getLogger().logInfo("Which username do you like to check?");
        System.out.print("> ");
        BackendApplication.getScanner().nextLine();
        User user = null;
        try {
            user.checkUsername(eingabe);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
