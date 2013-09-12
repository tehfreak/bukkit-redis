package com.tehfreak.bukkit.redis;

import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.JedisPubSub;

import java.util.logging.Level;

/**
 * @author Teh Freak
 */
public class RedisListener extends JedisPubSub {

    private JavaPlugin plugin;

    public void setPlugin(JavaPlugin plugin) {
        this.plugin= plugin;
    }

    public void onMessage(String channel, String message) {
        this.plugin.getLogger().info("MESSAGE channel: " + channel + ", message: " + message);

        if (message.equalsIgnoreCase("reload")) {
            this.plugin.getLogger().info("DO reload");
            this.plugin.getServer().reload();
        }

    }

    public void onSubscribe(String channel, int subscribedChannels) {
        this.plugin.getLogger().info("SUBSCRIBE channel: " + channel);
    }

    public void onUnsubscribe(String channel, int subscribedChannels) {
    }

    public void onPSubscribe(String pattern, int subscribedChannels) {
    }

    public void onPUnsubscribe(String pattern, int subscribedChannels) {
    }

    public void onPMessage(String pattern, String channel,
        String message) {
    }
}
