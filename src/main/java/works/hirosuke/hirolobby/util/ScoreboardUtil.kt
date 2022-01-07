package works.hirosuke.hirolobby.util

import org.bukkit.scoreboard.DisplaySlot
import works.hirosuke.hirolobby.HiroLobby.Companion.lobby

object ScoreboardUtil {

    fun update() {
        lobby.server.onlinePlayers.forEach { player ->
            player.scoreboard.clearSlot(DisplaySlot.SIDEBAR)

            val scoreboard = lobby.server.scoreboardManager?.newScoreboard!!
            val objective = scoreboard.getObjective("hub") ?: scoreboard.registerNewObjective("hub", "", "   §a§l§n HiroHub ${lobby.description.version.substringBeforeLast(".")} §r   ")
            val scores = mutableListOf<String>()

            scores.add("")
            scores.add("Players: ${lobby.server.onlinePlayers.size}/${lobby.server.maxPlayers}")
            scores.add(" ")

            scores.forEachIndexed { index, it ->
                objective.getScore(it).score = scores.lastIndex - index
            }

            objective.displaySlot = DisplaySlot.SIDEBAR
            player.scoreboard = scoreboard
        }
    }
}