package de.comgaiming.backend.commands;

import de.comgaiming.backend.BackendApplication;

public class CMD_Networkstats {

    public static void onCommand(){
        BackendApplication.getLogger().logInfo("---------[Networkstats]---------");
        BackendApplication.getLogger().logInfo("Hostname: \t\t\t\t" +  BackendApplication.getInternalMethods().getOwnerHostName());
        String ownerNetworkDeviceName = BackendApplication.getInternalMethods().getOwnerNetworkDeviceName();
        if(ownerNetworkDeviceName!= null){
            BackendApplication.getLogger().logInfo("IPv4-Adresse: \t\t\t" + BackendApplication.getInternalMethods().getOwnerIp());
            BackendApplication.getLogger().logInfo("Owner Network Device Name:\t\t"+BackendApplication.getInternalMethods().getOwnerNetworkDeviceName() + "\n");
        }else{
            BackendApplication.getLogger().logInfo("IPv4-Adresse: \t\t\t" + BackendApplication.getInternalMethods().getOwnerIp() + "\n\n");
        }
    }
}
