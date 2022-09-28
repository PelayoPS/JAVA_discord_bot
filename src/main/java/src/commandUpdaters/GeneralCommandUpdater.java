package src.commandUpdaters;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.general.Avatar;
import src.commands.general.Repeat;

import java.util.ArrayList;
import java.util.List;

public class GeneralCommandUpdater {
    /**
     * Updates the commands for the general category
     * @return a list of commands
     */
    public static List<CommandData> updateCommands() {
        //command list
        List<CommandData> commandList = new ArrayList<CommandData>();
        commandList.add(//repeat command
                Commands.slash("repeat", "Repeats the content given")
                        .addOption(OptionType.STRING, "content", "The content to repeat", true));
        commandList.add(//avatar command
                Commands.slash("avatar", "Sends the avatar of the user given")
                        .addOption(OptionType.USER, "user", "The user to get the avatar of", true));
        return commandList;
    }

    /**
     * Adds the command listeners for the general category
     * @param jda the JDA instance
     */
    public static void addCommandListeners(JDA jda) {
        jda.addEventListener(new Repeat());
        jda.addEventListener(new Avatar());
    }
}
