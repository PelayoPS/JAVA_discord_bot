package src.commandUpdaters;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.general.Repeat;

import java.util.ArrayList;
import java.util.List;

public class GeneralCommandUpdater {
    /**
     * Updates the commands for the general category
     * @param jda the JDA instance
     * @return a list of commands
     */
    public static List<CommandData> updateCommands(JDA jda) {
        //command list
        List<CommandData> commandList = new ArrayList<CommandData>();
        commandList.add(//repeat command
                Commands.slash("repeat", "Repeats the content given")
                        .addOption(OptionType.STRING, "content", "The content to repeat", true));
        return commandList;
    }

    /**
     * Adds the command listeners for the general category
     * @param jda the JDA instance
     */
    public static void addCommandListeners(JDA jda) {
        jda.addEventListener(new Repeat());
    }
}
