package main;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import util.logs.LogConsole;
import io.github.cdimascio.dotenv.Dotenv;
import listenners.OnReady;
import listenners.SlashCommandListener;
import java.util.List;
import java.util.Arrays;

public class Bot {

    public static void main(String[] args) {
        //loggers
        LogConsole logConsole = new LogConsole();

        // Load .env file
        Dotenv dotenv = Dotenv.configure()
                .directory("/secret")
                .filename(".env") // instead of '.env', use 'env'
                .load();

        // Get the bot token from .env file
        String botToken = dotenv.get("TOKEN");

        // Error control
        if (botToken == null || botToken.isEmpty()) {
            System.err.println("Missing DISCORD_TOKEN in .env file");
            return;
        }

        // Set up the JDA builder
        JDABuilder jdaBuilder = JDABuilder.createLight(botToken);

        // Set cache policy
        jdaBuilder.setMemberCachePolicy(MemberCachePolicy.ALL);

        // Sets all the possible intents
        // Gets a list of all intents
        List<GatewayIntent> intents = Arrays.asList(GatewayIntent.values());
        // Enable all intents
        jdaBuilder.enableIntents(intents);
        
        // Set the bot's status and activity
        jdaBuilder.setStatus(OnlineStatus.ONLINE);
        jdaBuilder.setActivity(Activity.playing("with JDA"));

        // Takes care if any build exception happens
        try {
            CommandManager commandManager = new CommandManager(logConsole);
            // Add event listeners (you can add more listeners here)
            jdaBuilder.addEventListeners(new SlashCommandListener(commandManager));
            jdaBuilder.addEventListeners(new OnReady());
            // Build and connect the JDA
            JDA jda = jdaBuilder.build();
            jda.awaitReady();
            // register the command
            commandManager.addCommands(jda);
            
        } catch (Exception e) {
            System.err.println("Error building or connecting the JDA: " + e.getMessage());
        }

    }
}
