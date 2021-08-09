package tech.needvoid.voidtax.voidtax.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import tech.needvoid.voidtax.voidtax.TaxModule;
import tech.needvoid.voidtax.VoidTaxPlugin;
import tech.needvoid.voidtax.utils.CC;
import tech.needvoid.voidtax.utils.command.SimpleCommand;

import java.util.Arrays;

public class TaxCommand extends SimpleCommand {

    private final VoidTaxPlugin plugin;

    public TaxCommand(VoidTaxPlugin plugin) {
        super("tax");

        this.setDescription("Make the rich become poor");
        this.setPermission("voidtax.tax");
        this.setAliases(Arrays.asList("takemoney", "makepoor"));

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        new TaxModule(this.plugin).taxModule();

        sender.sendMessage(CC.colour(this.plugin.getMessagesFile().getString(CC.colour("COMMANDS.TAX"))));
        return true;
    }
}
