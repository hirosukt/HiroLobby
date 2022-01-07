package works.hirosuke.hirolobby.command.event

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.FoodLevelChangeEvent

class PlayerEvent: Listener {

    @EventHandler
    fun on(e: FoodLevelChangeEvent) {
        e.isCancelled = true
    }
}