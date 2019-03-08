package de.sinqular.discord.utils;

public enum LogLevel {
    DEBUG(0, "DEBUG"), INFORMATION(1, "INFORMATION"), WARNING(2, "WARNING"), ERROR(3, "ERROR"), CRITICAL(4, "CRITICAL");

    private int level;
    private String name;

    LogLevel(int level, String name) {
        this.level = level;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}
