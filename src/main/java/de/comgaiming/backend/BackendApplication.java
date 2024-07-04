package de.comgaiming.backend;

import de.comgaiming.backend.commands.*;
import de.comgaiming.backend.serverhandler.ConsoleHandler;
import de.comgaiming.backend.serverhandler.LogHandler;
import de.comgaiming.backend.utils.DatabaseManager;
import de.comgaiming.backend.utils.InternalMethods;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "de.comgaiming.backend.restapi")
@EntityScan(basePackages = "de.comgaiming.backend.user")
public class BackendApplication {

	static ConsoleHandler consoleHandler = new ConsoleHandler();
	static LogHandler logger;
	static DatabaseManager databaseManager;
	static Scanner scanner = new Scanner(System.in);
	static InternalMethods internalMethods = new InternalMethods();

	public static void main(String[] args) {
		final long timeStart = System.currentTimeMillis();
		consoleHandler.setError("[ERROR] ");
		consoleHandler.setInfo("[INFO] ");
		consoleHandler.setWarning("[WARNING] ");
		logger = new LogHandler();
		logger.startLogging();
		logger.logInfo("Backend ist starting");
		SpringApplication.run(BackendApplication.class, args);
		databaseManager = new DatabaseManager("backend");
		databaseManager.connect();
		final long timeEnd = System.currentTimeMillis();
		getLogger().logInfo("Starttime: " + (timeEnd - timeStart) + " ms.\n");
		do {
			System.out.print("> ");
			String eingabe = scanner.nextLine();
			eingabe.toLowerCase();
			switch (eingabe) {
				case "stop", "end" -> CMD_Stop.onCommand();
				case "clear", "cls" -> CMD_Clear.onCommand();
				case "createuser" -> CMD_CreateUser.onCommand();
				case "updatepassword" -> CMD_updatePassword.onCommand();
				case "help", "?" -> CMD_Help.onCommand();
				case "getuser" -> CMD_GetUser.onCommand();
				case "checkusername" -> CMD_CheckUsername.onCommand();
				default -> getLogger().logInfo("Please use 'help' for help.\n");
			}
		}while (true);
	}

	public static void onStop() {
		getLogger().logInfo("Backend is stopping");
		getDatabaseManager().disconnect();
		getLogger().logInfo("Backend is stopped");
		System.exit(3);
	}

	public static LogHandler getLogger() {
		return logger;
	}

	public static DatabaseManager getDatabaseManager() {
		return databaseManager;
	}

	public static Scanner getScanner() {
		return scanner;
	}

	public static InternalMethods getInternalMethods() {
		return internalMethods;
	}

}
