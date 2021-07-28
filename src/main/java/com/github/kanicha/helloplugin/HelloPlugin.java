package com.github.kanicha.helloplugin;

import jdk.nashorn.internal.ir.Block;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class HelloPlugin extends JavaPlugin implements Listener {

    private Scheduler scheduler = new Scheduler();

    @Override
    public void onEnable() {
        // minecraftの時間処理が独特で Tick という値を使用をする
        // 20Tick = 1Sec.

        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);

        // 1秒毎にSchedulerクラスの中身を20tickごとに呼び出す
        scheduler.runTaskTimer(this, 0, 20);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        // Scheduler動作停止
        scheduler.cancel();
    }

    // ユーザーJoinした時関数
    @EventHandler
    public void inUser(PlayerJoinEvent pJoin)
    {
        pJoin.getPlayer().sendMessage("uooo!");
    }

    // ユーザーがベッドに寝ようとしたら処理関数
    @EventHandler
    public void inBed(PlayerBedEnterEvent betEvent)
    {
        // 変数群
        World world = betEvent.getBed().getWorld();
        Location location = betEvent.getBed().getLocation();
        Player player = betEvent.getPlayer();

        // ワールドの時間がよる時間だったら
        if (world.getTime() > 12517)
        {
            player.sendMessage("3秒後にばくは！！！！");
            Bukkit.getScheduler().runTaskLater(this, () -> {world.createExplosion(location, 100f);}, 60);
        }
        else
        {
            player.sendMessage("ねれないよ!");
        }
    }
}