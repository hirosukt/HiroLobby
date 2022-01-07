package works.hirosuke.hirolobby.event

import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.player.PlayerInteractEvent
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

        player.isFlying = false

        ScoreboardUtil.update()
    }

    @EventHandler
    fun on(e: PlayerQuitEvent) {
        lobby.runTaskLater(1) {
            ScoreboardUtil.update()
        }
    }

    @EventHandler
    fun on(e: PlayerInteractEvent) {
        val player = e.player
        val block = e.clickedBlock

        if (e.action == Action.PHYSICAL && block != null && block.type == Material.HEAVY_WEIGHTED_PRESSURE_PLATE) {
            player.velocity = player.location.direction.multiply(3).setY(1)
            player.playSound(player.location, Sound.ENTITY_WITHER_SHOOT, 0.5f, 1f)
        }
    }
}