package works.hirosuke.hirolobby.config

import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

abstract class Config {
    abstract var configFile: File

    abstract fun init(temp: Boolean)

    fun <T> set(path: String, value: T) {
        val yaml = load()
        if (path.contains('.')) {
            val splited = path.split('.')
            val section = resolveSection(yaml, splited)
            section?.set(splited[splited.lastIndex], value)
        } else {
            yaml.set(path, value)
        }
        saveFile(yaml)
    }

    fun get(path: String): Any? {
        val yaml = load()
        return if (path.contains('.')) {
            val splited = path.split('.')
            val section = resolveSection(yaml, splited)
            section?.get(splited[splited.lastIndex])
        } else {
            yaml.get(path)
        }
    }

    private fun resolveSection(yaml: YamlConfiguration, path: List<String>): ConfigurationSection? {
        var section: ConfigurationSection? = null
        path.minus(path[path.lastIndex]).forEachIndexed { index, s ->
            section = if (index == 0) {
                yaml.getConfigurationSection(s)
            } else {
                section?.getConfigurationSection(s)
            }
        }
        return section
    }

    fun getKeys(path: String = ""): Set<String>? {
        val yaml = load()
        if (path.contains(ConfigManager.PATH_SEPARATOR)) {
            val splited = path.split(ConfigManager.PATH_SEPARATOR)
            var section = yaml.getConfigurationSection(splited[0])
            splited.minus(splited[0]).forEachIndexed { index, s ->
                section = if (index == splited.lastIndex) {
                    return section?.getKeys(false)
                } else {
                    section?.getConfigurationSection(s)
                }
            }
            return section?.getKeys(false)
        } else if (path == "") {
            return yaml.getKeys(false)
        } else {
            return (yaml.getConfigurationSection(path) ?: return emptySet()).getKeys(false)
        }
    }

    private fun saveFile(yamlConfiguration: YamlConfiguration) {
        yamlConfiguration.save(configFile)
    }

    fun copyDefault(value: Boolean) {
        load().options().copyDefaults(value)
    }

    fun load(): YamlConfiguration {
        val yamlConfiguration = YamlConfiguration()
        yamlConfiguration.load(configFile)
        return yamlConfiguration
    }

    fun available(path: String): Boolean {
        val yaml = load()
        return yaml.contains(path)
    }
}