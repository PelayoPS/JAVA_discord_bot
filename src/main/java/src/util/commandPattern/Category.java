package src.util.commandPattern;

import net.dv8tion.jda.api.Permission;

public enum Category {
    GENERAL("General"),
    MOD("Moderation"),

    AUDIO("Audio"),

    GAME("Game");
    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Permission getPermission() {
        switch (this) {
            case MOD -> {
                return Permission.ADMINISTRATOR;
            }
            case GENERAL, GAME -> {
                return Permission.USE_APPLICATION_COMMANDS;
            }
            case AUDIO -> {
                return Permission.VOICE_CONNECT;
            }
            default -> {
                return Permission.UNKNOWN;
            }
        }
    }
}
