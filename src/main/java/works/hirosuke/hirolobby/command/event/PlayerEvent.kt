package works.hirosuke.hirolobby.command.event

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerJoinEvent
import works.hirosuke.hirolobby.HiroData

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
    }
}