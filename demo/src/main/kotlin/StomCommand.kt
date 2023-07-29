import net.minestom.server.command.builder.Command


object StomCommand : Command("stom") {
    init {
        addSyntax({ sender, context ->
            sender.sendMessage("started")
        }, "start")


        addSyntax({ sender, context ->
            sender.sendMessage("stopped")
        }, "stop")
    }
}