package works.hirosuke.hirolobby.util

import org.bukkit.entity.Player

object MessageUtil {

    fun Player.sendPrefixedMessage(text: String) {
        sendMessage("§a§l[Lobby] §r$text")
    }
}