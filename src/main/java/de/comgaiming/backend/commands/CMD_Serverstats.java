package de.comgaiming.backend.commands;

import de.comgaiming.backend.BackendApplication;

public class CMD_Serverstats {

    public static void onCommand(){
        BackendApplication.getLogger().logInfo("---------------[ServerInfo]---------------");
        //BackendApplication.getLogger().logInfo("Port: \t\t\t\t" + Main.getServerAPI().getServerPort());
        BackendApplication.getLogger().logInfo("Uptime: \t\t\t\t" + BackendApplication.getInternalMethods().getUptime()/1000 + " Sekunden");
        BackendApplication.getLogger().logInfo("System-Time: \t\t\t\t" + BackendApplication.getInternalMethods().getTimeForStats());
        BackendApplication.getLogger().logInfo("Operating system: \t\t\t" + BackendApplication.getInternalMethods().getOS());
        BackendApplication.getLogger().logInfo("Operating system architecture: \t" + BackendApplication.getInternalMethods().getOSArch());
        BackendApplication.getLogger().logInfo("Operating system version: \t\t" + BackendApplication.getInternalMethods().getOSVersion());
        BackendApplication.getLogger().logInfo("Used RAM: \t\t\t\t" + BackendApplication.getInternalMethods().getUsedMemory());
        BackendApplication.getLogger().logInfo("Free RAM: \t\t\t\t" + BackendApplication.getInternalMethods().getFreeMemory());
        BackendApplication.getLogger().logInfo("Total RAM: \t\t\t\t" + BackendApplication.getInternalMethods().getTotalMemory() + " GB\n");
        BackendApplication.getLogger().logInfo("Streamermodus: \t\t\t" + BackendApplication.getInternalMethods().isSecret() + "\n\n");

        BackendApplication.getLogger().logInfo( "---------------[Saveinfo]---------------");
        BackendApplication.getLogger().logInfo("Freespace: \t\t\t\t" + BackendApplication.getInternalMethods().getFreeSaveStorage());
        BackendApplication.getLogger().logInfo("Usespace: \t\t\t\t" + BackendApplication.getInternalMethods().getUsedSaveStorage());
        BackendApplication.getLogger().logInfo("Totalspace: \t\t\t\t" + BackendApplication.getInternalMethods().getTotalSaveStorage() + "\n");
    }

}
