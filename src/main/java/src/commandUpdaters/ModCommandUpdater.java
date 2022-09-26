package src.commandUpdaters;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.mod.Ban;
import src.commands.mod.Kick;

import java.util.ArrayList;
import java.util.List;

public class ModCommandUpdater {
    public static List<CommandData> updateCommands(JDA jda) {
        //command list
        List<CommandData> commandList = new ArrayList<CommandData>();
        commandList.add(//ban command
                Commands.slash("ban", "Bans the user given")
                        .addOption(OptionType.USER, "user", "The user to ban", true));
        commandList.add(//kick command
                Commands.slash("kick", "Kicks the user given")
                        .addOption(OptionType.USER, "user", "The user to kick", true));
        return commandList;
    }

    public static void addCommandListeners(JDA jda) {
        jda.addEventListener(new Ban());
        jda.addEventListener(new Kick());
    }
}
