package src.commands.mod.setup;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

import java.awt.*;

public class SetUp implements CommandInterface {

    public final Color setUpColor = Color.MAGENTA;

    private static final String name = "setup";
    private final String description = "sets up the bot";
    private final Category category = Category.MOD;

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        /*
        SERVERID=779805177984712716
        TEXTLOGCHANELLID=1025127078120669254
        COMMANDLOGCHANNELID=1023666295713050654
        SERVERJOINLOGCHANNELID=1023666367586648094
        SERVERLEAVELOGCHANNELID=1023666393587122196
        DMLOGCHANNELID=1023675197674491996
        MUTEDLOGCHANNELID=1025088132833615872
        WARNLOGCHANNELID=1025088318695800864
        WELCOMECHANNELID=1028364135664197774
        ALLMEMBERSCOUNTCHANNELID=793948912191930379
        MEMBERSCOUNTCHANNELID=793948915329138700
        BOTSCOUNTCHANNELID=793948920203051089
        BOOSTTIERCHANNELID=793949272382504990
        EMOJICOUNTCHANNELID=794640418930819113
        */

        //creates a modal that asks for the server id
        event.replyEmbeds(new EmbedBuilder()
                .setTitle("Set Up")
                .setDescription("Please click the button below to set up the bot")
                .setColor(setUpColor)
                .build())
                .addActionRow(Button.of(ButtonStyle.PRIMARY, "setup", "Enter set up"))
                .queue();
    }

    @Override
    public CommandData getSlash() {
        return Commands.slash(name, description);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public String getHelp() {
        return description;
    }

    public static String getNameForManagement() {
        return name;
    }
}
