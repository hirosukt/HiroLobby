package works.hirosuke.hirolobby.command.commands

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import works.hirosuke.hirolobby.command.Command

object Lobby: Command("lobby") {

    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {
        if (sender !is Player) return
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>): List<String>? {
        TODO("Not yet implemented")
    }
}