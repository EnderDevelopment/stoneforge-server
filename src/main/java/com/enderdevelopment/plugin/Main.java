package com.enderdevelopment.plugin;

import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("ResourceReport enabled. Use /serverresources to inspect this server process.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("serverresources")) {
            return false;
        }

        Runtime runtime = Runtime.getRuntime();
        long maximumMemory = runtime.maxMemory();
        int processors = runtime.availableProcessors();
        sender.sendMessage(Component.text("Server process resources", NamedTextColor.GOLD));
        sender.sendMessage(Component.text("JVM memory limit: " + formatGiB(maximumMemory), NamedTextColor.WHITE));
        sender.sendMessage(Component.text("JVM processors visible: " + processors, NamedTextColor.WHITE));
        reportDisk(sender);
        sender.sendMessage(Component.text(
                "These are process-visible limits, not proof of the host's purchased resources.", NamedTextColor.GRAY));
        return true;
    }

    private void reportDisk(CommandSender sender) {
        try {
            Path worldPath = getServer().getWorldContainer().toPath();
            FileStore store = Files.getFileStore(worldPath);
            sender.sendMessage(Component.text(
                    "World storage available: " + formatGiB(store.getUsableSpace())
                            + " / " + formatGiB(store.getTotalSpace()),
                    NamedTextColor.WHITE));
        } catch (Exception exception) {
            getLogger().warning("Could not inspect world storage: " + exception.getMessage());
            sender.sendMessage(Component.text("World storage: unavailable", NamedTextColor.RED));
        }
    }

    private String formatGiB(long bytes) {
        return String.format(Locale.ROOT, "%.2f GiB", bytes / (1024.0 * 1024.0 * 1024.0));
    }
}
