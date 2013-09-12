package com.tehfreak.bukkit.redis;

import org.bukkit.plugin.java.JavaPlugin;
import redis.clients.jedis.Jedis;

import java.util.logging.Level;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author Teh Freak
 */
public class Redis extends JavaPlugin {

    private FileConfiguration config;
    private Jedis redis;


    @Override
    public void onLoad(){
        this.getLogger().info("LOAD!11");
    }

    @Override
    public void onEnable(){
        this.getLogger().info("ENABLE!11");
        setup();
        setupRedis();
    }

    @Override
    public void onDisable(){
        this.getLogger().info("DISABLE!11");
        this.redis.disconnect();
    }

    private void setup() {
        saveDefaultConfig();
        config= getConfig();
    }

    private boolean setupRedis() {
        if (redis == null) {
            try {

                redis= new Jedis(
                    config.getString("redis.host"),
                    config.getInt("redis.port")
                );

                RedisListener redisListener= new RedisListener();
                redisListener.setPlugin(this);
                redis.subscribe(redisListener,
                    config.getString("redis.channel")
                );

            } catch (Exception e) {
                getLogger().severe("Caught exception: " + e.getMessage());
                return false;
            }
        }
        return true;
    }
}
