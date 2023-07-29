import cloud.commandframework.annotations.AnnotationParser
import cloud.commandframework.arguments.parser.ParserParameters
import cloud.commandframework.arguments.parser.StandardParameters
import cloud.commandframework.execution.AsynchronousCommandExecutionCoordinator
import cloud.commandframework.meta.CommandMeta
import cloud.commandframework.minestom.MinestomCommandManager
import net.minestom.server.command.CommandSender

object MinestomCloudCommandRegisterer {
    private val manager: MinestomCommandManager<CommandSender>
    private val annotationParser: AnnotationParser<CommandSender>

    init {
        val executionCoordinatorFunction =
            AsynchronousCommandExecutionCoordinator.builder<CommandSender>().build()
        manager = MinestomCommandManager(
            executionCoordinatorFunction,
            { it },
            { it })

        val commandMetaFunction = { p: ParserParameters ->
            CommandMeta.simple()
                .with(CommandMeta.DESCRIPTION, p.get(StandardParameters.DESCRIPTION, "No description"))
                .build()
        }

        annotationParser = AnnotationParser(manager, CommandSender::class.java, commandMetaFunction)
    }

    fun register(bean: Any) {
        annotationParser.parse(bean)
    }
}