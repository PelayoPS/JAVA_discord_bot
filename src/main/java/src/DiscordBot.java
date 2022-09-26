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
    private final Dotenv config;

    /**
     * Main method, used to start the bot
     * @param args //not used
     */
    public static void main(String[] args) {
        try {
            DiscordBot bot = new DiscordBot();//creates a new instance of the bot
        } catch (LoginException e) {
            System.out.println("ERROR: Provided bot token is invalid");// prints the error message
        }
    }

    public DiscordBot() throws LoginException {
        config = Dotenv.load();//Used to load all the environment variables from the .env file
        jda = JDABuilder.createDefault(config.get("TOKEN"))//creates a new JDA instance
                .setActivity(Activity.playing("with the code"))//sets the activity
                .setStatus(OnlineStatus.ONLINE)//sets the status
                .enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))//for huge bots enable only the intents you need
                .build();//builds the JDA instance
        updateCommands();//updates the commands
        addCommandListeners();//adds the command listeners
        //console log name of commands loaded
        System.out.println("Commands loaded: " + jda.retrieveCommands().complete());
        setActivityAndStatus(jda);
        addListeners(jda);//adds the listeners to the bot
    }

    /**
     * Adds all the listeners to the bot
     * @param builder the shard manager builder
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
    public Dotenv getConfig() {
        return config;
    }

    /**
     * sets the activity and status of the bot
     * @param jda
     */
    private void setActivityAndStatus(JDA jda) {
        jda.getPresence().setActivity(Activity.streaming("Follow my author :D","https://www.twitch.tv/pelayo_p_s"));
        jda.getPresence().setStatus(OnlineStatus.ONLINE);
    }

    /**
     * gets the JDA instance
     * to be used in other classes
     * @return
     */
    public JDA getJda() {
        return jda;
    }

    /**
     * updates the commands
     */
    private void updateCommands() {
        //command list
        List<CommandData> commandList = new ArrayList<CommandData>();
        commandList.addAll(ModCommandUpdater.updateCommands(jda));
        commandList.addAll(GeneralCommandUpdater.updateCommands(jda));
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
