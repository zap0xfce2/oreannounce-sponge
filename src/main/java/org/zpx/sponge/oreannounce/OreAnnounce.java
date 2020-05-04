package org.zpx.sponge.oreannounce;

import com.flowpowered.math.vector.Vector3i;
import com.google.inject.Inject;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.io.IOException;

@Plugin(id = "oreannounce", name = "OreAnnounce", version = "7.2.1", authors = "Zap0xfce2",
        url = "https://github.com/zap0xfce2/oreannounce-sponge",
        description = "Announces your ore findings.")
public class OreAnnounce {

    @DefaultConfig(sharedRoot = false)
    @Inject
    private ConfigurationLoader<CommentedConfigurationNode> configLoader;
    @Inject
    private Logger logger;

    @Inject
    private PluginContainer container;

    private Config config;
    private String OreBlock = "";
    private Vector3i CurrentOreBlockPosition;
    private Vector3i LastOreBlockPosition;
    private boolean FirstOreBlock = true;
    private String LastOreBlock = "";

    @Listener
    public void onServerStart(GameStartedServerEvent ServerStartEvent) throws IOException {

        //Setup configuration
        try {
            CommentedConfigurationNode node = configLoader.load();
            config = Config.MAPPER.bindToNew().populate(node);
            Config.MAPPER.bind(config).serialize(node);
            configLoader.save(node);
        } catch (ObjectMappingException e) {
            logger.error("Couldn't populate Config!", e);
        }

        //Reload command
        CommandSpec reloadCommand = CommandSpec.builder().permission("oreannounce.reload").description(Text.of("Reloads the OreAnnounce config"))
                .arguments(GenericArguments.literal(Text.of("reload"), "reload")).executor((src, args) -> {
                    if (args.hasAny("reload")) {
                        reloadConfig();
                        src.sendMessage(Text.builder("[OreAnnounce] Config Reloaded!").color(TextColors.GREEN).build());
                    }
                    return CommandResult.empty();
                }).build();

        Sponge.getCommandManager().register(this, reloadCommand, "oreannounce");

        // Log Start Up to Console
        logger.info(
                container.getName() + " version " + container.getVersion().orElse("")
                        + " enabled!");

    }

    public void reloadConfig() {
        try {
            config = Config.MAPPER.bindToNew().populate(configLoader.load());
        } catch (ObjectMappingException e) {
            logger.error("Couldn't repopulate Config!", e);
        } catch (IOException e) {
            logger.error("Couldn't open or didn't have access to config file!", e);
        }
    }

    @Listener
    public void BlockDestroy(ChangeBlockEvent.Break BreakBlockEvent, @Root Player player) {

        if (!OreBlock.equals("") && FirstOreBlock) {
            LastOreBlockPosition = CurrentOreBlockPosition;
            FirstOreBlock = false;
        }

        if (OreBlock.contains("gold") && config.GOLD_ANNOUNCE && checkForOreAnnounce(CurrentOreBlockPosition, LastOreBlockPosition) && player.hasPermission("oreannounce.gold")) {
            Sponge.getServer().getBroadcastChannel().send(toColoredText(config.USERNAME_COLOR + player.getName() + " " + config.GOLD_MESSAGE));
            resetOreStatus();
        }

        if (OreBlock.contains("iron") && config.IRON_ANNOUNCE && checkForOreAnnounce(CurrentOreBlockPosition, LastOreBlockPosition) && player.hasPermission("oreannounce.iron")) {
            Sponge.getServer().getBroadcastChannel().send(toColoredText(config.USERNAME_COLOR + player.getName() + " " + config.IRON_MESSAGE));
            resetOreStatus();
        }

        if (OreBlock.contains("coal") && config.COAL_ANNOUNCE && checkForOreAnnounce(CurrentOreBlockPosition, LastOreBlockPosition) && player.hasPermission("oreannounce.coal")) {
            Sponge.getServer().getBroadcastChannel().send(toColoredText(config.USERNAME_COLOR + player.getName() + " " + config.COAL_MESSAGE));
            resetOreStatus();
        }

        if (OreBlock.contains("lapis") && config.LAPIS_ANNOUNCE && checkForOreAnnounce(CurrentOreBlockPosition, LastOreBlockPosition) && player.hasPermission("oreannounce.lapis")) {
            Sponge.getServer().getBroadcastChannel().send(toColoredText(config.USERNAME_COLOR + player.getName() + " " + config.LAPIS_MESSAGE));
            resetOreStatus();
        }

        if (OreBlock.contains("diamond") && config.DIAMOND_ANNOUNCE && checkForOreAnnounce(CurrentOreBlockPosition, LastOreBlockPosition) && player.hasPermission("oreannounce.diamond")) {
            Sponge.getServer().getBroadcastChannel().send(toColoredText(config.USERNAME_COLOR + player.getName() + " " + config.DIAMOND_MESSAGE));
            resetOreStatus();
        }

        if (OreBlock.contains("redstone") && config.REDSTONE_ANNOUNCE && checkForOreAnnounce(CurrentOreBlockPosition, LastOreBlockPosition) && player.hasPermission("oreannounce.redstone")) {
            Sponge.getServer().getBroadcastChannel().send(toColoredText(config.USERNAME_COLOR + player.getName() + " " + config.REDSTONE_MESSAGE));
            resetOreStatus();
        }

        if (OreBlock.contains("emerald") && config.EMERALD_ANNOUNCE && checkForOreAnnounce(CurrentOreBlockPosition, LastOreBlockPosition) && player.hasPermission("oreannounce.emerald")) {
            Sponge.getServer().getBroadcastChannel().send(toColoredText(config.USERNAME_COLOR + player.getName() + " " + config.EMERALD_MESSAGE));
            resetOreStatus();
        }

        if (OreBlock.contains("quartz") && config.QUARTZ_ANNOUNCE && checkForOreAnnounce(CurrentOreBlockPosition, LastOreBlockPosition) && player.hasPermission("oreannounce.quartz")) {
            Sponge.getServer().getBroadcastChannel().send(toColoredText(config.USERNAME_COLOR + player.getName() + " " + config.QUARTZ_MESSAGE));
            resetOreStatus();
        }
    }

    @Listener
    public void onInteract(InteractBlockEvent.Primary.MainHand InteractEvent) {
        String BlockType = InteractEvent.getTargetBlock().getState().getName();

        if (BlockType.contains("ore")) {
            // behebt das Problem das Redstoneerz zwei Zustände hat
            if (BlockType.contains("lit_redstone")) {
                OreBlock = "";
            } else {
                OreBlock = BlockType;
            }
            CurrentOreBlockPosition = InteractEvent.getTargetBlock().getPosition();
        }
    }

    public boolean checkForOreAnnounce(Vector3i LocalCurrentOreBlockPosition, Vector3i LocalLastOreBlockPosition) {

        boolean canBeAnnounced = false;

        // Spammen der Nachricht verhindern
        if (LocalCurrentOreBlockPosition.getX() - LocalLastOreBlockPosition.getX() >= 5 || LocalCurrentOreBlockPosition.getX() - LocalLastOreBlockPosition.getX() <= -5 || !OreBlock.equals(LastOreBlock)) {
            canBeAnnounced = true;
        }

        return canBeAnnounced;
    }

    public void resetOreStatus(){
        LastOreBlock = OreBlock;
        OreBlock = "";
        LastOreBlockPosition = CurrentOreBlockPosition;
    }

    public static Text toColoredText(String str){
        return TextSerializers.FORMATTING_CODE.deserialize(str);
    }
}