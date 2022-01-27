package works.hirosuke.hirolobby.event

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.LeavesDecayEvent

class BlockEvent: Listener {

    @EventHandler
    fun on(e: LeavesDecayEvent) {
        e.isCancelled = true
    }
}