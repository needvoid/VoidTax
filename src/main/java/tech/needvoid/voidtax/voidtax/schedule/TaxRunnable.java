package tech.needvoid.voidtax.voidtax.schedule;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import tech.needvoid.voidtax.voidtax.TaxModule;
import tech.needvoid.voidtax.VoidTaxPlugin;
import tech.needvoid.voidtax.utils.CC;


public class TaxRunnable extends BukkitRunnable {

    private final VoidTaxPlugin plugin;

    public TaxRunnable(VoidTaxPlugin plugin) {
        this.plugin = plugin;
    }

    public void run() {
        new TaxModule(this.plugin).taxModule();
        Bukkit.broadcastMessage(CC.colour(this.plugin.getMessagesFile().getString("TAX-COLLECT")));
    }
}
