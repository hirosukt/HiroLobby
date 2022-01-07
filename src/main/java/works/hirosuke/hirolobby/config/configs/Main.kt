package works.hirosuke.hirolobby.config.configs

import org.bukkit.Location
import works.hirosuke.hirolobby.HiroData
import works.hirosuke.hirolobby.config.Config
import works.hirosuke.hirolobby.config.ConfigManager
import java.io.File

object Main: Config() {
    override var configFile: File = ConfigManager.config

    override fun init(temp: Boolean) {
        HiroData.spawnLocation = get("spawnLocation") as Location
        HiroData.spawnOnJoin = get("spawnOnJoin") as Boolean
    }
}