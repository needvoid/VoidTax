package tech.needvoid.voidtax.VoidTax;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import tech.needvoid.voidtax.VaultHook;
import tech.needvoid.voidtax.VoidTaxPlugin;
import tech.needvoid.voidtax.utils.CC;

public class TaxModule {

    private final VoidTaxPlugin plugin;

    public TaxModule(VoidTaxPlugin plugin) {
        this.plugin = plugin;
    }

    public void taxModule() {
        Economy economy = VaultHook.getEconomy();

        double total;
        double balance;
        double allowance = this.plugin.getSettingsFile().getDouble("TAX.ALLOWANCE");

        String e = "Percentage not provided, please check settings file";

        for (OfflinePlayer player : Bukkit.getOfflinePlayers()) {
            balance = economy.getBalance(player);

            if (balance > allowance && balance <= this.plugin.getSettingsFile().getDouble("TAX.BAND-ONE.MAXIMUM")) {
                total = balance * ((100 - this.plugin.getSettingsFile().getDouble("TAX.BAND-ONE.TAX-PERCENTAGE"))/100);
                if (!(total >= 0 || total <= 100)) {
                    CC.log(e);
                } else if (total <= allowance) {
                    economy.withdrawPlayer(player, balance - allowance);
                    continue;
                }
                economy.withdrawPlayer(player, (balance * this.plugin.getSettingsFile().getDouble("TAX.BAND-ONE.TAX-PERCENTAGE"))/100);
            }

            if (balance > this.plugin.getSettingsFile().getDouble("TAX.BAND-ONE.MAXIMUM") &&
                    balance <= this.plugin.getSettingsFile().getDouble("TAX.BAND-TWO.MAXIMUM")) {
                total = balance * ((100 - this.plugin.getSettingsFile().getDouble("TAX.BAND-TWO.TAX-PERCENTAGE"))/100);
                if (!(total >= 0 || total <= 100)) {
                    CC.log(e);
                }
                economy.withdrawPlayer(player, (balance * this.plugin.getSettingsFile().getDouble("TAX.BAND-TWO.TAX-PERCENTAGE"))/100);
            }

            if (balance > this.plugin.getSettingsFile().getDouble("TAX.BAND-TWO.MAXIMUM")) {
                total = balance * ((100 - this.plugin.getSettingsFile().getDouble("TAX.BAND-THREE.TAX-PERCENTAGE"))/100);
                if (!(total >= 0 || total <= 100)) {
                    CC.log(e);
                }
                economy.withdrawPlayer(player, (balance * this.plugin.getSettingsFile().getDouble("TAX.BAND-THREE.TAX-PERCENTAGE")/100));
            }
        }
    }
}
