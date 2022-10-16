package src.commands.mod;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.DiscordBot;
import src.Main;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;


public class Restart implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static final String name = "restart";

    private static final Category category = Category.MOD;

    private String description = "Restarts the bot";

    // ====================CONSTRUCTOR SECTION====================//

    public Restart() {
    }

    // ====================HANDLING SECTION====================//

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

    /**
     * thows an exception to restart the bot
     * @return
     */
    private void restart() throws Exception {
        try
        {
            throw new Exception("Force restart!");
        }
        finally
        {
            Main.main(DiscordBot.isProductionEnabled()? new String[]{"prod"} : new String[]{"dev"});
        }
    }

    // ====================RETURN INFO SECTION====================//

    /**
     * returns the command data
     * @return
     */
    @Override
    public CommandData getSlash() {
        CommandData command = Commands.slash(name, description);
        return command;
    }

    /**
     * gets the name of the command
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * gets the category of the command
     * @return
     */
    @Override
    public Category getCategory() {
        return category;
    }

    /**
     * gets the name of the command for management
     * @return
     */
    public static String getNameForManagement() {
        return name;
    }

    /**
     * gets the description of the command
     * @return
     */
    @Override
    public String getHelp() {
        return description;
    }
}
