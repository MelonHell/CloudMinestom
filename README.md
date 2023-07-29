# CloudMinestom

A minestom implementation of the [Cloud Command Framework](https://github.com/Incendo/cloud).

## Usage

### Gradle
```kts
repositories {
    maven("https://maven.melonhell.ru/public")
}

dependencies {
    implementation("ru.melonhell.cloudminestom:CloudMinestom:1.0.0-SNAPSHOT")
}
```

### Examples

Creating the command manager. You can also use the `AsyncCommandExecutionCoordinator`.
And custom command sender mapper functions.
```java
MinestomCommandManager<CommandSender> commandManager = new MinestomCommandManager<>(
        CommandExecutionCoordinator.simpleCoordinator(),
        Function.identity(),
        Function.identity()
);
```