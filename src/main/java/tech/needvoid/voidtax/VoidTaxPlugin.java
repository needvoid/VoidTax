package tech.needvoid.voidtax;

import org.bukkit.plugin.java.JavaPlugin;
import tech.needvoid.voidtax.voidtax.command.VoidTaxCommand;
import tech.needvoid.voidtax.voidtax.command.TaxCommand;
import tech.needvoid.voidtax.voidtax.schedule.TaxRunnable;
import tech.needvoid.voidtax.utils.CC;
import tech.needvoid.voidtax.utils.ConfigFile;
import tech.needvoid.voidtax.utils.command.CommandHandler;

public final class VoidTaxPlugin extends JavaPlugin {

    private ConfigFile settingsFile;
    private ConfigFile messagesFile;

    @Override
    public void onEnable() {
        CC.log("Starting plugin");
        long start = System.currentTimeMillis();

        this.settingsFile = new ConfigFile(this, "settings.yml");
        this.messagesFile = new ConfigFile(this, "messages.yml");

        VaultHook vaultHook = new VaultHook(this);

        if (getServer().getPluginManager().getPlugin("Vault") != null) {
            CC.log("Vault hook found, enabling");
            new VaultHook(this);
            if (!vaultHook.setupEconomy()) {
                CC.log("Disabled because no economy was found!");
                this.getServer().getPluginManager().disablePlugin(this);
                return;
            }
        }

        long delay = (long) (getSettingsFile().getAsYaml().getDouble("TAX.DELAY-IN-MINUTES") * 60 * 20);

        new TaxRunnable(this).runTaskTimerAsynchronously(this, delay, delay);

        CommandHandler commandHandler = new CommandHandler(this);
        commandHandler.setNoPermissionMessage(getMessagesFile().getString(CC.colour("COMMANDS.NO-PERMISSION")));
        commandHandler.registerSimpleCommand(new TaxCommand(this));
        commandHandler.registerAdvancedCommand(new VoidTaxCommand(this));

        CC.log("Plugin started in " + (System.currentTimeMillis() - start) + "ms");
    }

    @Override
    public void onDisable() {

    }

    public ConfigFile getSettingsFile() {
        return settingsFile;
    }

    public ConfigFile getMessagesFile() {
        return messagesFile;
    }
}
