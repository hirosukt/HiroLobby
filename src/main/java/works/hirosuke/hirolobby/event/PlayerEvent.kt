package works.hirosuke.hirolobby.event

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import works.hirosuke.hirolobby.HiroData
import works.hirosuke.hirolobby.HiroLobby.Companion.lobby
import works.hirosuke.hirolobby.util.ItemUtil
import works.hirosuke.hirolobby.util.SchedularUtil.runTaskAsync
import works.hirosuke.hirolobby.util.SchedularUtil.runTaskLater
import works.hirosuke.hirolobby.util.ScoreboardUtil
import java.io.ByteArrayOutputStream
import java.io.DataOutputStream


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

        player.inventory.setItem(0, ItemUtil.create(Material.COMPASS, "§6§lServer Selector"))

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
        } else if (e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK) {
            val serverSelectorGui = Bukkit.createInventory(null, 27, "§6§l§nServer Selection")

            serverSelectorGui.setItem(10, ItemUtil.create(Material.IRON_SWORD, "§4§lPractice Server - 1.8.8", listOf("§7Supported 1.7 ~ 1.8")))
            serverSelectorGui.setItem(11, ItemUtil.create(Material.GRASS_BLOCK, "§a§lCreative Server - 1.18.1", listOf("§7Supported 1.8 ~ 1.18.1")))
            serverSelectorGui.setItem(12, ItemUtil.create(Material.DIAMOND_AXE, "§6§lYpex Debugging Server - 1.18.1", listOf("§7Supported 1.18.1 Only")))
            serverSelectorGui.setItem(14, ItemUtil.create(Material.IRON_BLOCK, "§d§lEvent-1 Server"))
            serverSelectorGui.setItem(15, ItemUtil.create(Material.GOLD_BLOCK, "§d§lEvent-2 Server"))
            serverSelectorGui.setItem(16, ItemUtil.create(Material.DIAMOND_BLOCK, "§d§lEvent-3 Server"))

            player.openInventory(serverSelectorGui)
        }
    }

    @EventHandler
    fun on(e: InventoryClickEvent) {
        if ("Server Selection" in e.view.title) {
            e.isCancelled = true
            val name = e.currentItem?.itemMeta?.displayName ?: return
            val player = e.whoClicked as Player

            val channel = if ("Practice" in name) {
                "practice"
            } else if ("Creative" in name) {
                "creative"
            } else if ("Ypex" in name) {
                "ypex"
            } else if ("Event-1" in name) {
                "event1"
            } else if ("Event-2" in name) {
                "event2"
            } else if ("Event-3" in name) {
                "event3"
            } else return

            lobby.runTaskAsync {
                val b = ByteArrayOutputStream()
                val out = DataOutputStream(b)

                out.writeUTF("Connect")
                out.writeUTF(channel)

                player.sendPluginMessage(lobby, "BungeeCord", b.toByteArray())
            }
        }
    }
}