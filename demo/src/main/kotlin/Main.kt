import net.minestom.server.MinecraftServer
import net.minestom.server.coordinate.Pos
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.instance.block.Block
import java.time.Duration

fun main() {
    val minecraftServer = MinecraftServer.init()
    minecraftServer.start("0.0.0.0", 25565)

    MinecraftServer.getCommandManager().register(StomCommand)

    val instanceContainer = MinecraftServer.getInstanceManager().createInstanceContainer().apply {
        timeUpdate = Duration.ofMinutes(1)
        setGenerator { it.modifier().fillHeight(0, 32, Block.GRASS_BLOCK) }
    }

    MinecraftServer.getGlobalEventHandler().addListener(PlayerLoginEvent::class.java) { event ->
        event.setSpawningInstance(instanceContainer)
       event.player.respawnPoint = Pos(0.0, 32.0, 0.0)
    }

    MinestomCloudCommandRegisterer.register(TestCommand)
}

