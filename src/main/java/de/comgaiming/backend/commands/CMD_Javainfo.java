package de.comgaiming.backend.commands;

import de.comgaiming.backend.BackendApplication;

public class CMD_Javainfo {

    public static void onCommand() {
        BackendApplication.getLogger().logInfo("---------------[Javainfo]---------------");
        BackendApplication.getLogger().logInfo( "Javaversion for the JRE: " + BackendApplication.getInternalMethods().getJavaVersion());
        BackendApplication.getLogger().logInfo( "Vendorname for the JRE: " + BackendApplication.getInternalMethods().getJavaVendorName());
        BackendApplication.getLogger().logInfo( "Java installation directory for the JRE: " + BackendApplication.getInternalMethods().getInstallationPathJava() + "\n");
    }
}
