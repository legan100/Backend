package de.comgaiming.backend.commands;

public class CMD_Stats {

    public static void onCommand(){

        CMD_Networkstats.onCommand();

        CMD_Serverstats.onCommand();

        CMD_Javainfo.onCommand();

    }
}
