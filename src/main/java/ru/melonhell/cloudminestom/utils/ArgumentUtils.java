package ru.melonhell.cloudminestom.utils;

import cloud.commandframework.arguments.CommandArgument;
import cloud.commandframework.arguments.parser.ArgumentParser;
import cloud.commandframework.arguments.standard.*;
import ru.melonhell.cloudminestom.MinestomCommandManager;
import net.minestom.server.command.builder.arguments.*;
import net.minestom.server.command.builder.arguments.minecraft.ArgumentUUID;
import net.minestom.server.command.builder.arguments.number.ArgumentDouble;
import net.minestom.server.command.builder.arguments.number.ArgumentFloat;
import net.minestom.server.command.builder.arguments.number.ArgumentInteger;
import net.minestom.server.command.builder.arguments.number.ArgumentNumber;
import net.minestom.server.command.builder.suggestion.SuggestionEntry;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class ArgumentUtils {
    public static <C> Argument<?> createMinestomArgument(CommandArgument<C, ?> arg, MinestomCommandManager<C> manager) {
        Argument<?> minestomArgument = convertArgument(arg);
        addSuggestions(minestomArgument, manager);
        return minestomArgument;
    }

    private static <C> void addSuggestions(Argument<?> argument, MinestomCommandManager<C> manager) {
        argument.setSuggestionCallback(((sender, context, suggestion) -> {
            List<@NonNull String> suggestionList = manager.suggest(manager.mapCommandSender(sender), fixInput(context.getInput()));
            for (String sug : suggestionList) {
                suggestion.addEntry(new SuggestionEntry(sug));
            }
        }));
    }

    private static String fixInput(String input) {
        if (input.endsWith("\u0000")) return input.substring(0, input.length() - 1);
        return input;
    }

    private static <C> Argument<?> convertArgument(CommandArgument<C, ?> arg) {
        ArgumentParser<C, ?> parser = arg.getParser();

        String defaultValue = arg.getDefaultValue();
        boolean hasDefaultValue = !defaultValue.equals("");


        if (parser instanceof StringArgument.StringParser<?> sp && sp.getStringMode() == StringArgument.StringMode.SINGLE) {
            ArgumentWord result = new ArgumentWord((arg.getName()));
            if (hasDefaultValue) result.setDefaultValue(defaultValue);
            return result;
        }

        if (parser instanceof StringArgument.StringParser<?> sp && sp.getStringMode() == StringArgument.StringMode.GREEDY) {
            ArgumentStringArray result = new ArgumentStringArray((arg.getName()));
            if (hasDefaultValue) result.setDefaultValue(defaultValue.split(Pattern.quote(" ")));
            return result;
        }

        if (parser instanceof StringArgument.StringParser<?> sp && sp.getStringMode() == StringArgument.StringMode.QUOTED) {
            ArgumentString result = new ArgumentString((arg.getName()));
            if (hasDefaultValue) result.setDefaultValue(defaultValue);
            return result;
        }

        if (arg instanceof FloatArgument<?> fa) {
            ArgumentNumber<Float> result = new ArgumentFloat(arg.getName()).min(fa.getMin()).max(fa.getMax());
            if (hasDefaultValue) result.setDefaultValue(Float.parseFloat(defaultValue));
            return result;
        }

        if (arg instanceof DoubleArgument<?> da) {
            ArgumentNumber<Double> result = new ArgumentDouble(arg.getName()).min(da.getMin()).max(da.getMax());
            if (hasDefaultValue) result.setDefaultValue(Double.parseDouble(defaultValue));
            return result;
        }

        if (arg instanceof IntegerArgument<?> ia) {
            ArgumentNumber<Integer> result = new ArgumentInteger(arg.getName()).min(ia.getMax()).max(ia.getMax());
            if (hasDefaultValue) result.setDefaultValue(Integer.parseInt(defaultValue));
            return result;
        }

        if (arg instanceof UUIDArgument) {
            ArgumentUUID result = new ArgumentUUID(arg.getName());
            if (hasDefaultValue) result.setDefaultValue(UUID.fromString(defaultValue));
            return result;
        }

        if (arg instanceof BooleanArgument) {
            ArgumentBoolean result = new ArgumentBoolean(arg.getName());
            if (hasDefaultValue) result.setDefaultValue(Boolean.parseBoolean(defaultValue));
            return result;
        }

        ArgumentWord result = new ArgumentWord(arg.getName());
        if (hasDefaultValue) result.setDefaultValue(defaultValue);
        return result;
    }
}
