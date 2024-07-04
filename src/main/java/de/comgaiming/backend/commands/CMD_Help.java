package de.comgaiming.backend.commands;

import de.comgaiming.backend.BackendApplication;

public class CMD_Help {

    public static void onCommand(){
        BackendApplication.getLogger().logInfo("----------[Help]----------");
        BackendApplication.getLogger().logInfo("clear \t\tclear the console");
        BackendApplication.getLogger().logInfo("createuser\tcreate new ACP-User");
        BackendApplication.getLogger().logInfo("help\t\tShow all commands");
        BackendApplication.getLogger().logInfo("stop\t\tStop the backend");
    }

}
