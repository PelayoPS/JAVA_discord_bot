package src.commandUpdaters;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.mod.*;

import java.util.ArrayList;
import java.util.List;

public class ModCommandUpdater {

    private static List<String> moderationCommands;
    /**
     * Updates the commands for the moderation category
     * @return a list of commands
     */
    public static List<CommandData> updateCommands() {
        //command list
        List<CommandData> commandList = new ArrayList<CommandData>();
        commandList.add(//ban command
                Commands.slash("ban", "Bans the user given")
                        .addOption(OptionType.USER, "user", "The user to ban", true));
        commandList.add(//kick command
                Commands.slash("kick", "Kicks the user given")
                        .addOption(OptionType.USER, "user", "The user to kick", true));
        commandList.add(//erase command
                Commands.slash("erase", "Deletes the amount of messages given in the channel provided")
                        .addOption(OptionType.INTEGER, "amount", "The amount of messages to delete", true)
                        .addOption(OptionType.CHANNEL, "channel", "The channel to delete the messages from", true));
        commandList.add(//mute command
                Commands.slash("mute", "Mutes the user given")
                        .addOption(OptionType.USER, "user", "The user to mute", true)
                        .addOption(OptionType.STRING, "reason", "The reason for the mute", true)
                        .addOption(OptionType.INTEGER, "time", "The time to mute the user for", true));
        commandList.add(//warn command
                Commands.slash("warn", "Warns the user given")
                        .addOption(OptionType.USER, "user", "The user to warn", true)
                        .addOption(OptionType.STRING, "reason", "The reason to warn the user for", true));
        commandList.add(//restart command
                Commands.slash("restart", "Restarts the bot"));

        moderationCommands = new ArrayList<String>();
        for (CommandData command : commandList) {
            moderationCommands.add(command.getName());
        }
        return commandList;
    }

    public static List<String> getModerationCommands() {
        return moderationCommands;
    }

    /**
     * Adds the command listeners for the moderation category
     * @param jda the JDA instance
     */
    public static void addCommandListeners(JDA jda) {
        jda.addEventListener(new Ban());
        jda.addEventListener(new Kick());
        jda.addEventListener(new Erase());
        jda.addEventListener(new Mute());
        jda.addEventListener(new Warn());
        jda.addEventListener(new Restart());
    }
}
