import cloud.commandframework.annotations.Argument
import cloud.commandframework.annotations.CommandMethod
import cloud.commandframework.annotations.suggestions.Suggestions
import cloud.commandframework.context.CommandContext
import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player
import java.util.*

object TestCommand {
    @CommandMethod("argtest <arg>")
    fun test1(
        sender: Player,
        @Argument("arg", suggestions = "test") arg: String
    ) {
        sender.sendMessage("Hello ${sender.username}! arg = $arg")
    }

    @CommandMethod("test command")
    fun test2(
        sender: Player
    ) {
        sender.sendMessage("test2")
    }

    @CommandMethod("test command eblan")
    fun test3(
        sender: Player
    ) {
        sender.sendMessage("test3")
    }

    @CommandMethod("test command eblan <idk>")
    fun test4(
        sender: Player,
        @Argument("idk", suggestions = "test") arg: String
    ) {
        sender.sendMessage("test4 ${arg}")
    }

    @CommandMethod("test command petuh")
    fun test5(
        sender: Player
    ) {
        sender.sendMessage("test5")
    }

    @Suggestions("test")
    fun testSuggestions(sender: CommandContext<CommandSender>, input: String): List<String> {
        return listOf("govno", "zalupa", "penis", "her")
    }
}