package works.hirosuke.hirolobby

import org.bukkit.entity.Player
import works.hirosuke.hirolobby.command.commands.Lobby
import works.hirosuke.hirolobby.event.BlockEvent
import works.hirosuke.hirolobby.event.EntityEvent
import works.hirosuke.hirolobby.event.PlayerEvent
import works.hirosuke.hirolobby.config.ConfigManager
import works.hirosuke.hirolobby.event.ChatEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.plugin.messaging.PluginMessageListener

class HiroLobby : JavaPlugin(), PluginMessageListener {

    companion object {
        lateinit var lobby: JavaPlugin
    }

    init {
        lobby = this
    }

    override fun onEnable() {
        // Plugin startup logic
        logger.info("plugin has loaded.")

        saveResource("config.yml", false)
        config.options().copyDefaults(true)

        ConfigManager.getAllInstance().forEach {
            it.init(true)
        }

        server.messenger.registerOutgoingPluginChannel(this, "BungeeCord");

        server.pluginManager.registerEvents(BlockEvent(), this)
        server.pluginManager.registerEvents(ChatEvent(), this)
        server.pluginManager.registerEvents(PlayerEvent(), this)
        server.pluginManager.registerEvents(EntityEvent(), this)

        Lobby.register()
    }

    override fun onDisable() {
        // Plugin shutdown logic
        server.messenger.unregisterOutgoingPluginChannel(this)

        logger.info("plugin has unloaded.")
    }

    override fun onPluginMessageReceived(channel: String, player: Player, message: ByteArray) {

    }
}
