package works.hirosuke.hirolobby

import org.bukkit.plugin.java.JavaPlugin

class HiroLobby : JavaPlugin() {

    override fun onEnable() {
        // Plugin startup logic
        logger.info("plugin has loaded.")

        saveResource("config.yml", false)
    }

    override fun onDisable() {
        // Plugin shutdown logic
        logger.info("plugin has unloaded.")
    }
}
