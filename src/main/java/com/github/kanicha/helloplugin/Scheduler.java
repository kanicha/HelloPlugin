package com.github.kanicha.helloplugin;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Scheduler extends BukkitRunnable {

    private long count = 0;

    // Update らしい
    @Override
    public void run() {
        if (count % 600 == 0)
        Bukkit.broadcastMessage("10分!");

        count++;
    }
}
