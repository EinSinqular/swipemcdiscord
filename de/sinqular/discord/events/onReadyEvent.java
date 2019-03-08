package de.sinqular.discord.events;

import de.sinqular.discord.Start;
import de.sinqular.discord.utils.LogLevel;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class onReadyEvent extends ListenerAdapter {

    public void onReady(ReadyEvent e) {
        Start.getLogger().log(LogLevel.INFORMATION, "Der Bot wurde gestartet");
        Start.getLogger().log(LogLevel.INFORMATION, "Nutze /help für Hilfe!");
    }

}
