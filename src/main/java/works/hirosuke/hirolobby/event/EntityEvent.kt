package works.hirosuke.hirolobby.event

import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class EntityEvent: Listener {

    @EventHandler
    fun on(e: EntityDamageByEntityEvent) {
        val damager = e.damager

        e.isCancelled = true

        if(damager !is Player) return

        if(damager.gameMode != GameMode.CREATIVE) {
            e.isCancelled = false
        }
    }
}