package de.comgaiming.backend.commands;

import de.comgaiming.backend.BackendApplication;
import de.comgaiming.backend.user.User;

public class CMD_GetUser {

    public static void onCommand(){
        User user = new User();
        BackendApplication.getLogger().logInfo("Über welchen User möchtest du Informationen erhalten?");
        String eingabe = BackendApplication.getScanner().nextLine();
        BackendApplication.getLogger().logInfo("Email: " + user.getEmail(eingabe));
        BackendApplication.getLogger().logInfo("UserID: " + user.getUserID(eingabe));
        BackendApplication.getLogger().logInfo("Groupname" + user.getGroup().getGroupname());
        BackendApplication.getLogger().logInfo("username  " + user.getUsername());
    }
}
