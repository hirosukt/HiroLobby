package works.hirosuke.hirolobby

import org.bukkit.Location
import works.hirosuke.hirolobby.HiroLobby.Companion.lobby

object HiroData {

    var spawnLocation = Location(lobby.server.getWorld("world"), 0.0, 0.0, 0.0)
    var spawnOnJoin = false
}