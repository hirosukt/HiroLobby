package works.hirosuke.hirolobby

import org.bukkit.plugin.java.JavaPlugin
import works.hirosuke.hirolobby.command.commands.Lobby
import works.hirosuke.hirolobby.event.BlockEvent
import works.hirosuke.hirolobby.event.EntityEvent
import works.hirosuke.hirolobby.event.PlayerEvent
import works.hirosuke.hirolobby.config.ConfigManager

class HiroLobby : JavaPlugin() {

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

        server.pluginManager.registerEvents(BlockEvent(), this)
        server.pluginManager.registerEvents(PlayerEvent(), this)
        server.pluginManager.registerEvents(EntityEvent(), this)

        Lobby.register()
    }

    override fun onDisable() {
        // Plugin shutdown logic
        logger.info("plugin has unloaded.")
    }
}
