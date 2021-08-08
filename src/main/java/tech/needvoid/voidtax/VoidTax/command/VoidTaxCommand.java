package tech.needvoid.voidtax.VoidTax.command;

import tech.needvoid.voidtax.VoidTax.command.subcommands.ReloadCommand;
import tech.needvoid.voidtax.VoidTaxPlugin;
import tech.needvoid.voidtax.utils.command.AdvancedCommand;

import java.util.Arrays;

public class VoidTaxCommand extends AdvancedCommand {

    private final VoidTaxPlugin plugin;

    public VoidTaxCommand(VoidTaxPlugin plugin) {
        super("voidtax");

        this.setDescription("The admin command for VoidTax.");
        this.setPermission("tax.admin");
        this.setAliases(Arrays.asList(
                "vtax"
        ));

        this.addSubCommands(Arrays.asList(
                new ReloadCommand(plugin)
        ));

        this.plugin = plugin;
    }
}
