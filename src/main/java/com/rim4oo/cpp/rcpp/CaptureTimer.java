package com.rim4oo.cpp.rcpp;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Bukkit;
import java.util.Timer;
import java.util.TimerTask;

public class CaptureTimer extends JavaPlugin {

    public void startTimer(int seconds) {
        new BukkitRunnable() {
            int countdown = seconds;

            @Override
            public void run() {
                if (countdown >= 0) {
                    // Отправляем сообщение в Action Bar всем игрокам
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        sendActionBar(player, "Осталось времени: " + countdown + " секунд");
                    }
                    countdown--;
                } else {
                    // Когда время вышло, останавливаем таймер
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        sendActionBar(player, "Время вышло!");
                    }
                    cancel(); // Останавливаем задачу
                }
            }
        }.runTaskTimer(this, 0, 20); // Запускаем задачу с периодом в 20 тиков (1 секунда)
    }

    private void sendActionBar(Player player, String message) {
        player.sendActionBar(message);
    }
}
