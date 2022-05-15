package timisimys.beacons;

import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;
import timisimys.beacons.commands.settings;
import timisimys.beacons.commands.stopgame;
import timisimys.beacons.events.*;
import timisimys.beacons.game.BossBarGame;
import timisimys.beacons.game.ReadyMap;
import timisimys.beacons.game.ScoreBoards;
import timisimys.beacons.game.TimersGame;

import static timisimys.beacons.game.CheckSettings.*;


public final class Main extends JavaPlugin {

    private static Main instance;



    @Override
    public void onEnable() {
        System.out.println("Плагин Включен");
        instance = this;



        Bukkit.getPluginManager().registerEvents(new mapsChanges(), this);
        Bukkit.getPluginManager().registerEvents(new moving(), this);
        Bukkit.getPluginManager().registerEvents(new joinServer(), this);
        Bukkit.getPluginManager().registerEvents(new death(), this);
        Bukkit.getPluginManager().registerEvents(new inventoryClick(), this);
        Bukkit.getPluginManager().registerEvents(new inventoryMove(), this);
        Bukkit.getPluginManager().registerEvents(new leaveServer(), this);
        Bukkit.getPluginManager().registerEvents(new messageSend(), this);
        Bukkit.getPluginManager().registerEvents(new dropEvent(), this);
        new settings(this);
        new stopgame(this);
        saveDefaultConfig();

        onStart();
        ReadyMap.BeaconsReset();
        ScoreBoards.MainBoard();
        BossBarGame.createBossBar();
        BossBarGame.showBossBar("none");
        TimersGame.onTimer();
    }



    @Override
    public void onDisable() {

        System.out.println("Плагин Выключен");

    }

    public static Main getInstence() {
        return instance;
    }

}
