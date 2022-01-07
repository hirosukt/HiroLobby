package works.hirosuke.hirolobby.util

import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask

object SchedularUtil {
    inline fun bukkitRunnable(crossinline block: BukkitRunnable.() -> Unit) = object : BukkitRunnable() {
        override fun run() = block()
    }

    inline fun Plugin.runTaskLater(delay: Long, crossinline block: BukkitRunnable.() -> Unit): BukkitTask {
        return bukkitRunnable(block).runTaskLater(this, delay)
    }

    inline fun Plugin.runTaskTimer(delay: Int, period: Long, crossinline block: BukkitRunnable.() -> Unit): BukkitTask {
        return bukkitRunnable(block).runTaskTimer(this, delay.toLong(), period)
    }

    inline fun Plugin.runTaskTimerAsync(delay: Long, period: Long, crossinline block: BukkitRunnable.() -> Unit): BukkitTask {
        return bukkitRunnable(block).runTaskTimerAsynchronously(this, delay, period)
    }

    inline fun Plugin.runTaskAsync(crossinline block: BukkitRunnable.() -> Unit): BukkitTask {
        return bukkitRunnable(block).runTaskAsynchronously(this)
    }
}