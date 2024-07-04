package de.comgaiming.backend.commands;

import de.comgaiming.backend.BackendApplication;

public class CMD_Stop {

    public static void onCommand() {
        BackendApplication.onStop();
    }
}
