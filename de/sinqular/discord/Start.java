package de.sinqular.discord;

import de.sinqular.discord.utils.Logger;

public class Start {

    private static Logger logger;
    private Main main;

    public static void main(String[] args) {
        logger = new Logger(System.out, true);
        Main.initBot();
    }

    public static Logger getLogger() {
        return logger;
    }
}
