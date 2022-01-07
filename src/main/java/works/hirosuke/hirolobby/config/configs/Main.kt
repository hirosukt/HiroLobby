package works.hirosuke.hirolobby.config.configs

import works.hirosuke.hirolobby.HiroData
import works.hirosuke.hirolobby.config.Config
import works.hirosuke.hirolobby.config.ConfigManager
import java.io.File

object Main: Config() {
    override var configFile: File = ConfigManager.config

    override fun init(temp: Boolean) {
        val yaml = load()
        val section = yaml.getConfigurationSection("main") ?: throw NullPointerException("main section not found in ${configFile.name}.")
        HiroData.spawnLocation = section.getLocation("spawnLocation") ?: throw NullPointerException("spawnLocation config not found.")
        HiroData.spawnOnJoin = section.getBoolean("spawnOnJoin")
    }
}