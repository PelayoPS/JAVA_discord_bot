package src;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.GatewayIntent;
import src.commandUpdaters.GeneralCommandUpdater;
import src.commandUpdaters.ModCommandUpdater;
import src.listeners.OnMessageReceived;
import src.listeners.OnReady;
import src.listeners.OnUserJoin;
import src.listeners.OnUserLeave;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

public class DiscordBot {

    private final JDA jda;
    private static Dotenv config = null;

    /**
     * Default constructor for the DiscordBot class
     * @throws LoginException if the bot token is invalid
     */
    public DiscordBot() throws LoginException {
        config = Dotenv.load();//Used to load all the environment variables from the .env file
        jda = JDABuilder.createDefault(config.get("TOKEN"))//creates a new JDA instance
                .setActivity(Activity.playing("with the code"))//sets the activity
                .setStatus(OnlineStatus.ONLINE)//sets the status
                .enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))//for huge bots enable only the intents you need
                .enableIntents(GatewayIntent.GUILD_PRESENCES)//enables the presence intent
                .build();//builds the JDA instance
        updateCommands();//updates the commands
        addCommandListeners();//adds the command listeners
        //console log name of commands loaded
        System.out.println("Commands loaded: ");
        jda.retrieveCommands().complete().forEach(command -> System.out.println(command.getName()));
        setActivityAndStatus();//sets the activity and status
        addListeners(jda);//adds the listeners to the bot
    }

    /**
     * Adds all the listeners to the bot
     * @param jda the JDA instance
     */
    private void addListeners(JDA jda) {
        jda.addEventListener(new OnReady());//adds the OnReady listener
        jda.addEventListener(new OnMessageReceived(this));//adds the OnMessageReceived listener
        jda.addEventListener(new OnUserJoin(this));//adds the OnUserJoin listener
        jda.addEventListener(new OnUserLeave(this));//adds the OnUserLeave listener
    }

    /**
     *  Used to get the config instance in other classes
     *  @return the config instance
     */
    public static Dotenv getConfig() {
        return config;
    }

    /**
     * sets the activity and status of the bot
     */
    private void setActivityAndStatus() {
        jda.getPresence().setActivity(Activity.streaming("Follow my author :D","https://www.twitch.tv/pelayo_p_s"));
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
    }

    /**
     * gets the JDA instance
     * to be used in other classes
     * @return the JDA instance
     */
    public JDA getJda() {
        return jda;
    }

    /**
     * updates the commands
     */
    private void updateCommands() {
        //command list
        List<CommandData> commandList = new ArrayList<>();
        commandList.addAll(ModCommandUpdater.updateCommands());
        commandList.addAll(GeneralCommandUpdater.updateCommands());
        jda.updateCommands().addCommands(commandList).queue();
    }

    /**
     * adds the command listeners
     */
    private void addCommandListeners() {
        ModCommandUpdater.addCommandListeners(jda);
        GeneralCommandUpdater.addCommandListeners(jda);
    }


}
