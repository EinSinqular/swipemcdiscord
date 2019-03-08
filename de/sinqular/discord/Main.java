package de.sinqular.discord;

import de.sinqular.discord.controller.PermissionController;
import de.sinqular.discord.events.onMessageEvent;
import de.sinqular.discord.events.onReadyEvent;
import de.sinqular.discord.mysql.MySQL;
import de.sinqular.discord.utils.Data;
import de.sinqular.discord.utils.LogLevel;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Member;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Main discordBot;

    private static boolean running = false;

    public static boolean MySQL = false;

    public static ArrayList<String> blackWords = new ArrayList<>();

    private static MySQL mySQL;
    private static PermissionController permissionController = new PermissionController();

    public static ArrayList<Member> mutes = new ArrayList<>();

    public Main() {
        discordBot = this;
    }

    public static void initBot() {


        Scanner scanner = new Scanner(System.in);
        String answer;
        String[] args;
        String command;

        System.out.println("  ____               _                  __  __    ____     ____    _                                   _  \n" +
                           " / ___|  __      __ (_)  _ __     ___  |  \\/  |  / ___|   |  _ \\  (_)  ___    ___    ___    _ __    __| |\n" +
                           " \\___ \\  \\ \\ /\\ / / | | | '_ \\   / _ \\ | |\\/| | | |       | | | | | | / __|  / __|  / _ \\  | '__|  / _` |\n" +
                           "  ___) |  \\ V  V /  | | | |_) | |  __/ | |  | | | |___    | |_| | | | \\__ \\ | (__  | (_) | | |    | (_| |\n" +
                           " |____/    \\_/\\_/   |_| | .__/   \\___| |_|  |_|  \\____|   |____/  |_| |___/  \\___|  \\___/  |_|     \\__,_|\n" +
                           "                        |_|                                                                              ");
        Start.getLogger().log(LogLevel.INFORMATION, "Version " + Data.version);
        Start.getLogger().log(LogLevel.INFORMATION, "by EinSinqular");
        Start.getLogger().log(LogLevel.INFORMATION, "Bot startet....");
        registerBlackListedWords();
        setRunning(true);
        if(MySQL == false) {
            Start.getLogger().log(LogLevel.WARNING, "MySQL Verbindung nicht aktiviert.");
        } else {
            setupMySQL();
            getMySQL().update("CREATE TABLE IF NOT EXISTS Discord (Mutes TEXT, Banns TEXT, User INT(32))");
        }

        try {
            JDA jdaBuilder = new JDABuilder(AccountType.BOT).setToken(Data.token).setGame(Game.of(Game.GameType.DEFAULT, "auf SwipeMC.net")).setAutoReconnect(true)
                    .addEventListener(new onReadyEvent())
                    .addEventListener(new onMessageEvent())
                    .buildAsync();
        } catch (LoginException ex) {
            ex.printStackTrace();
        }


        while(true) {
            answer = scanner.nextLine();
            command = answer.split(" ")[0];
            args = dropFirstString(answer.split(" "));
            if(command.equalsIgnoreCase("stop")) {
                setRunning(false);
                Start.getLogger().log(LogLevel.INFORMATION, "Der Bot wird nun gestoppt!");
                System.exit(1);
            } else if(command.equalsIgnoreCase("help")) {
                Start.getLogger().log(LogLevel.INFORMATION, "Stop - Stoppt den Bot");
                Start.getLogger().log(LogLevel.INFORMATION, "Kick - Kickt einen Spieler vom Server");
                Start.getLogger().log(LogLevel.INFORMATION, "Penis - gibt dir eine saftie CockSchelle");
                Start.getLogger().log(LogLevel.INFORMATION, "Author - Sehe den Author des Bots");
                Start.getLogger().log(LogLevel.INFORMATION, "Clear - Leert die Konsole");
                Start.getLogger().log(LogLevel.INFORMATION, "sqlconnect - Stelle einer Verbindung mit MySQL her");
                Start.getLogger().log(LogLevel.INFORMATION, "info - Information über den Bot!");
            } else if(command.equalsIgnoreCase("penis")) {
                Start.getLogger().log(LogLevel.CRITICAL, "Die PENISE sind außer RAND UND BAND");
                Start.getLogger().log(LogLevel.CRITICAL, "Die PENISE sind außer RAND UND BAND");
                Start.getLogger().log(LogLevel.CRITICAL, "Die PENISE sind außer RAND UND BAND");
                Start.getLogger().log(LogLevel.CRITICAL, "Die PENISE sind außer RAND UND BAND");
                Start.getLogger().log(LogLevel.CRITICAL, "Die PENISE sind außer RAND UND BAND");
                Start.getLogger().log(LogLevel.CRITICAL, "Die PENISE sind außer RAND UND BAND");
                Start.getLogger().log(LogLevel.CRITICAL, "Die PENISE sind außer RAND UND BAND");
                Start.getLogger().log(LogLevel.CRITICAL, "Die PENISE sind außer RAND UND BAND");
                Start.getLogger().log(LogLevel.CRITICAL, "Die PENISE sind außer RAND UND BAND");
                Start.getLogger().log(LogLevel.CRITICAL, "Die PENISE sind außer RAND UND BAND");
            } else if(command.equalsIgnoreCase("author")) {
                Start.getLogger().log(LogLevel.WARNING, "Der Author des Bot's ist xShirro");
            } else if(command.equalsIgnoreCase("clear")) {
                for(int i = 0; i < 50; i++) {
                    System.out.println(" ");
                }
                Start.getLogger().log(LogLevel.INFORMATION, "Die Konsole wurde geleert.");
            } else if(command.equalsIgnoreCase("sqlconnect")) {
                if(MySQL == false) {
                    Start.getLogger().log(LogLevel.WARNING, "MySQL Verbindung nicht aktiviert.");
                } else {
                    mySQL.close();
                    setupMySQL();
                    getMySQL().update("CREATE TABLE IF NOT EXISTS Discord (Mutes TEXT, Banns TEXT, User INT(32))");
                    Start.getLogger().log(LogLevel.INFORMATION, "MySQL Verbindung wurde hergestellt.");
                }
            } else if(command.equalsIgnoreCase("info")) {
                Start.getLogger().log(LogLevel.INFORMATION, "Version: " + Data.version);
                Start.getLogger().log(LogLevel.INFORMATION, "Token: " + Data.token);
                if(MySQL == false) {
                    Start.getLogger().log(LogLevel.INFORMATION, "MySQL: Deaktiviert");
                } else {
                    Start.getLogger().log(LogLevel.INFORMATION, "MySQL: Aktiviert");
                }


            } else {
                Start.getLogger().log(LogLevel.ERROR, "Dieser Command existiert nicht!");
            }
        }

    }
    private static String[] dropFirstString(String[] array) {
        String[] strings = new String[array.length -1];
        System.arraycopy(array, 1, strings, 0, array.length -1);
        return strings;
    }

    public static void setupMySQL() {
        mySQL = new MySQL();
    }

    public static MySQL getMySQL() {
        return mySQL;
    }

    public static PermissionController getPermissionController() {
        return permissionController;
    }
    public static void registerBlackListedWords() {
        Start.getLogger().log(LogLevel.INFORMATION, "BlackList wurde geladen..");
        blackWords.add("hurensohn");
        blackWords.add("nigger");
        blackWords.add("jude");
        blackWords.add("nuttensohn");
        blackWords.add("bitch");
    }

    public static ArrayList<String> getBlackWords() {
        return blackWords;
    }

    public static Main getDiscordBot() {
        return discordBot;
    }

    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean running) {
        Main.running = running;
    }
}
