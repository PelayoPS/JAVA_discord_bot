package src.commands.mod;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.DiscordBot;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

public class Warn implements CommandInterface {
    Dotenv config = DiscordBot.getConfig();

    private static String name = "warn";

    private Category category = Category.MOD;

    /**
     * When a slash command with the name warn is used this method is called
     * it warns the user given in the channel provided
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        if(!event.getMember().getPermissions().contains(Permission.ADMINISTRATOR)){
            return;
        }
        warnUser(event, event.getOption("user"), event.getOption("reason"));
        event.reply("User [" + event.getOption("user").getAsUser().getAsTag() + "] warned").queue();
    }

    /**
     * sends a private message to the user warned and logs the warning in the warn log channel
     * @param user
     */
    private void warnUser(SlashCommandInteractionEvent event, OptionMapping user, OptionMapping reason){
        user.getAsUser().openPrivateChannel().queue((channel) -> {
            channel.sendMessage("You have been warned for " + reason.getAsString()).queue();
        });
        logWarn(event, user, reason);
    }

    private void logWarn(SlashCommandInteractionEvent event, OptionMapping user, OptionMapping reason){
        event.getGuild().getTextChannelById(config.get("WARNLOGCHANNELID"))
                .sendMessage(user.getAsUser().getAsTag() +
                        " was warned by " + event.getUser().getAsTag() +
                        " for " + reason.getAsString()).queue();
    }

    @Override
    public CommandData getSlash() {
        return Commands.slash(name, "Restarts the bot");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    public static String getNameForManagement(){
        return name;
    }
}
