package works.hirosuke.hirolobby.event

import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
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
        if (e.clickedBlock?.type == Material.HEAVY_WEIGHTED_PRESSURE_PLATE) {
            e.player.playSound(e.player.location, Sound.ENTITY_WITHER_SHOOT, 0.5f, 1f)
            e.player.velocity = e.player.velocity.setX(e.player.location.direction.x * 2.5).setY(1).setZ(e.player.location.direction.z * 2.5)
        }
    }
}