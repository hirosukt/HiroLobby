package works.hirosuke.hirolobby.event

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import works.hirosuke.hirolobby.HiroData
import works.hirosuke.hirolobby.HiroLobby.Companion.lobby
import works.hirosuke.hirolobby.util.SchedularUtil.runTaskLater
import works.hirosuke.hirolobby.util.ScoreboardUtil

class PlayerEvent: Listener {

    @EventHandler
    fun on(e: FoodLevelChangeEvent) {
        e.isCancelled = true
    }

    @EventHandler
    fun on(e: PlayerJoinEvent) {
        val player = e.player

        if (HiroData.spawnOnJoin) {
            player.teleport(HiroData.spawnLocation)
        }

        ScoreboardUtil.update()
    }

    @EventHandler
    fun on(e: PlayerQuitEvent) {
        lobby.runTaskLater(1) {
            ScoreboardUtil.update()
        }
    }
}