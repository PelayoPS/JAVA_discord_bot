package src;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class DiscordBot {

    private final ShardManager shardManager;
    private final Dotenv config;

    public DiscordBot() throws LoginException {
        config = Dotenv.load();//Used to load all the environment variables from the .env file

        /*
         * The DefaultShardManagerBuilder is used to build a new ShardManager instance.
         * 
         * The login method is blocking, this means that JDA will attempt to login and
         * it will either succeed or it will throw an exception.
         * 
         * If you call this on the main thread, it will prevent your bot from starting
         * until it has either succeeded or failed to login.
         * 
         * This is fine for most use cases, but if you require your bot to start
         * immediately and perform actions while attempting to login, consider using
         * the asynchronous login method.
         * 
         * In this example, since we are just starting the bot and immediately waiting
         * for it to fully start up, we will use the blocking login method.
         * 
         * Using a shard manager is not required, but it is recommended for most bots as
         * it allows to be run on multiple servers at once.
         * 
         * The DefaultShardManagerBuilder will automatically use the recommended number
         * of shards for the bot based on the amount of guilds the bot is in.
         * 
         * If you would like to manually specify the amount of shards to use, you can
         * use the setShardsTotal method.
         */
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(config.get("TOKEN"));

        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        /*
         * Optional: if you would like to set an activity for your bot, you can do so
         * with the setActivity method.
         * 
         * The options are:
         * - playing
         * - listening
         * - watching
         * - competing
         * (Rich presence is not supported by the official Discord client, but is supported
         * by other custom clients)
         * You can also use the Activity class to create your own custom activity
         */
        builder.setActivity(Activity.streaming("Follow my author :D","https://www.twitch.tv/pelayo_p_s"));//sets the bot activity
        addListeners(builder);//adds the listeners to the bot
        shardManager = builder.build();//builds the shard manager instance
    }

    /**
     * Adds all the listeners to the bot
     * @param builder the shard manager builder
     */
    private void addListeners(DefaultShardManagerBuilder builder) {
        builder.addEventListeners(new src.listeners.OnReady());//adds the OnReady listener
        builder.addEventListeners(new src.listeners.OnMessageReceived());//adds the OnMessageReceived listener
    }

    /**
     *  Used to get the shard manager instance in other classes
     *  @return the shard manager instance
     */
    public ShardManager getShardManager() {
        return shardManager;
    }

    /**
     *  Used to get the config instance in other classes
     *  @return the config instance
     */
    public Dotenv getConfig() {
        return config;
    }

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
}
