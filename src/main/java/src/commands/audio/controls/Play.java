package src.commands.audio.controls;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.audio.lavaplayer.PlayerManager;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

import java.net.URI;
import java.net.URISyntaxException;

public class Play implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static String name = "play";
    private Category category = Category.AUDIO;

    private String description = "Link to play";

    // ====================CONSTRUCTOR SECTION====================//

    public Play() {
    }

    // ====================HANDLING SECTION====================//

    /**
     * When the command is executed it plays a link from a song or playlist from youtube
     *
     * @param event The event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        if(event.getMember().getVoiceState().getChannel() != null){
            event.getMember().getVoiceState().getChannel().getGuild().getAudioManager().openAudioConnection(event.getMember().getVoiceState().getChannel());
            play(event,event.getChannel().asTextChannel());

        } else {
            event.reply("You are not in a voice channel").queue();
        }
    }

    // ====================UTIL SECTION====================//

    /**
     * Plays a song
     * if the link is from a song it adds ytsearch: to the link
     * @param event The event
     * @param channel The channel
     */
    private void play(SlashCommandInteractionEvent event, TextChannel channel) {
        String link = event.getOption("link").getAsString();
        if (link.contains("list")){
            //link doesn't need to be modified
        } else {
            if (!isUrl(link)) {
                link = "ytsearch:" + link;
            }
        }
        PlayerManager.getInstance().loadAndPlay(channel, link, event);
    }

    /**
     * Checks if the link is a valid url
     * @param url The url
     * @return true if it is a valid url
     */
    private boolean isUrl(String url) {
        try {
            new URI(url);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }

    // ====================RETURN INFO SECTION====================//

    /**
     * returns the slash command data
     */
    @Override
    public CommandData getSlash() {
        CommandData commandData = Commands.slash(name, description)
                .addOption(OptionType.STRING, "link", description, true);
        return commandData;
    }

    /**
     * returns the name of the command
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * returns the category of the command
     */
    @Override
    public Category getCategory() {
        return category;
    }

    /**
     * returns the name of the command for management
     */
    public static String getNameForManagement() {
        return name;
    }

    /**
     * returns the description of the command
     */
    @Override
    public String getHelp() {
        return description;
    }
}
