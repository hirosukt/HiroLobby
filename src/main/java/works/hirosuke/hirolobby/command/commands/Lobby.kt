package works.hirosuke.hirolobby.command.commands

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import works.hirosuke.hirolobby.command.Command
import works.hirosuke.hirolobby.config.ConfigManager
import works.hirosuke.hirolobby.config.EnumConfig
import works.hirosuke.hirolobby.config.configs.Main
import works.hirosuke.hirolobby.util.MessageUtil.sendPrefixedMessage

object Lobby: Command("lobby") {

    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {
        if (sender !is Player) return

        if (args.isNotEmpty()) {
            when (args[0]) {
                "config" -> {
                    fun <T> setConfigWithMessage(value: T) {
                        val formattedPathString = args.toList().minus("config").minus(args.last()).joinToString(ConfigManager.PATH_SEPARATOR)
                        if (ConfigManager.available(formattedPathString, ConfigManager.getInstanceByName(args[1]) ?: Main)) {
                            ConfigManager.getInstanceByName(args[1])?.set(formattedPathString, value)
                            ConfigManager.init(EnumConfig.valueOf(args[1].uppercase()))
                            sender.sendPrefixedMessage("値を設定しました: §b${formattedPathString}§f = §b$value")
                        } else {
                            sender.sendPrefixedMessage("§cコンフィグが見つかりません: §b${formattedPathString}")
                        }
                    }

                    fun getConfigWithMessage() {
                        val formattedPathString = args.toList().minus("config").joinToString(ConfigManager.PATH_SEPARATOR)
                        if (ConfigManager.available(formattedPathString, ConfigManager.getInstanceByName(args[1]) ?: Main)) {
                            val value = ConfigManager.getInstanceByName(args[1])?.get(formattedPathString)
                            sender.sendPrefixedMessage("値を取得しました: §b${formattedPathString}§f = §b$value")
                        } else {
                            sender.sendPrefixedMessage("§cコンフィグが見つかりません: §b${formattedPathString}")
                        }
                    }

                    if (ConfigManager.getInstanceByName(args[1])?.get(args.toList().minus("config").joinToString(ConfigManager.PATH_SEPARATOR)) == null) {
                        if (args.contains("#here")) {
                            setConfigWithMessage(sender.location)
                        } else {
                            setConfigWithMessage(args.last())
                        }
                    } else {
                        getConfigWithMessage()
                    }
                }
            }
        }
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>): List<String>? {
        if (args.size == 1) return listOf("config")

        return when (args[0]) {
            "config" -> {
                when (args.size) {
                    2 -> {
                        EnumConfig.values().filter { it != EnumConfig.ALL }.map { it.name.lowercase() }
                    }
                    else -> {
                        (ConfigManager.getInstanceByName(args[1])?.getKeys(args.toList().minus(args[args.lastIndex]).joinToString(ConfigManager.PATH_SEPARATOR)) ?: emptySet()).toList()
                    }
                }
            }
            else -> {
                emptyList()
            }
        }
    }
}