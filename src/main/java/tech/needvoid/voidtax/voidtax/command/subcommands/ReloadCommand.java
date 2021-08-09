package tech.needvoid.voidtax.voidtax.command.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import tech.needvoid.voidtax.VoidTaxPlugin;
import tech.needvoid.voidtax.utils.CC;
import tech.needvoid.voidtax.utils.command.SubCommand;

import java.util.Arrays;

public class ReloadCommand extends SubCommand {

    private final VoidTaxPlugin plugin;

    public ReloadCommand(VoidTaxPlugin plugin) {
        super("reload");

        this.setDescription("A command to reload the plugin");
        this.setAliases(Arrays.asList("rl"));

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        this.plugin.getServer().getScheduler().cancelTasks(this.plugin);
        this.plugin.onEnable();

        sender.sendMessage(CC.colour("&aVoidTax has been reloaded"));

        return true;
    }
}
