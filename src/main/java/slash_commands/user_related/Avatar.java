package slash_commands.user_related;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import slash_commands.SlashCommand;

public class Avatar implements SlashCommand {

    public SlashCommandData getSlashInfo() {
        SlashCommandData result = Commands.slash("avatar",
                "Description: Shows the avatar of the user provided, default user is the caller.");
        addOptions(result);
        return result;
    }

    public void addOptions(SlashCommandData slashCommandData) {
        slashCommandData.addOption(OptionType.USER, "user",
                "The user to get the avatar from.", false);

    }

    public void execute(SlashCommandInteractionEvent event) {
        User user = event.getOption("user") == null ? event.getUser()
                : event.getOption("user").getAsUser();
        event.reply(user.getAvatarUrl()).queue();
    }

}