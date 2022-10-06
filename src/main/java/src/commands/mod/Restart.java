package src.commands.mod;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.Main;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;


public class Restart implements CommandInterface {

    private static String name = "restart";

    private static Category category = Category.MOD;

    /**
     * When a slash command with the name restart is used this method is called
     * it restarts the bot
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        if(!event.getMember().getPermissions().contains(Permission.ADMINISTRATOR)){
            return;
        }
        event.reply("Restarting...").setEphemeral(true).queue();

        try {
            Main.bot.getJda().shutdown();//this makes the bot go offline so a new instance can be created
            restart();
        }catch (Exception e) {
            System.out.println("Restarting bot");
        }
    }

    public void restart() throws Exception {
        try
        {
            throw new Exception("Force restart!");
        }
        finally
        {
            Main.main(new String[0]);
        }
    }

    @Override
    public CommandData getSlash() {
        CommandData command = Commands.slash(name, "Warns the user given")
                .addOption(OptionType.USER, "user", "The user to warn", true)
                .addOption(OptionType.STRING, "reason", "The reason to warn the user for", true);
        return command;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    public static String getNameForManagement() {
        return name;
    }
}
