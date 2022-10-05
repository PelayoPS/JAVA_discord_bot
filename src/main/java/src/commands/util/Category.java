package src.commands.util;

public enum Category {
    GENERAL("General"),
    MOD("Moderation");
    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
