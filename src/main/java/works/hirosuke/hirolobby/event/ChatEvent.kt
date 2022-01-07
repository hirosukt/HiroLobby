package works.hirosuke.hirolobby.event

import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatEvent: Listener {

    @EventHandler
    fun on(e: AsyncPlayerChatEvent) {
        val player = e.player
        val team = player.scoreboard.getEntryTeam(player.name)
        var format = ""

        if (team != null) {
            format = team.prefix
            format += team.color.toString()
        }

        format += "${player.name}: "
        format += ChatColor.RESET.toString() + e.message
        e.format = format
    }
}