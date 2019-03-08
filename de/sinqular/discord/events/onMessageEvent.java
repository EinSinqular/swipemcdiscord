package de.sinqular.discord.events;

import de.sinqular.discord.Main;
import de.sinqular.discord.Start;
import de.sinqular.discord.api.DiscordBotAPI;
import de.sinqular.discord.utils.LogLevel;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.GuildController;
;

public class onMessageEvent extends ListenerAdapter {


    public void onMessageReceived(MessageReceivedEvent e) {
        Guild guild = e.getGuild();
        GuildController controller = new GuildController(e.getGuild());
        User objUser = e.getAuthor();
        if(!e.getAuthor().isBot()) {
          //  if(!Main.getPermissionController().isOwner(e.getMember()) && !Main.getPermissionController().isAdmin(e.getMember())) {
                for(String word : Main.getBlackWords()) {
                    if(e.getMessage().toString().toLowerCase().contains(word.toLowerCase())) {
                        DiscordBotAPI.mutePlayer(guild, objUser, e, "Test Mute");
                    }
                }
           // }
        }
        if(e.getMessage().getContentDisplay().startsWith(".s")) {
            String[] args = dropFirstString(e.getMessage().getContentDisplay().split(" "));
            if(args[0].equalsIgnoreCase("help")) {
                e.getTextChannel().sendMessage("@" + e.getAuthor().getName() + "\nAlle Commands wurden dir per Privat Nachricht gesendet").queue();
                e.getMember().getUser().openPrivateChannel().queue(success -> success.sendMessage("Guten Tag @" + e.getAuthor().getName() + " , \n Der bot besitzt folgende Commands! \n .s Mute <Client> \n .s Ban <Client> \n .s Kick <Client> ").queue());
                Start.getLogger().log(LogLevel.INFORMATION, "Der Bot hat eine Nachricht an " + e.getAuthor().getName() + " gesendet");
            } else if(args[0].equalsIgnoreCase("kerk")) {
                e.getMember().getUser().openPrivateChannel().queue(success -> success.sendMessage("Guten Tag @" + e.getAuthor().getName() + " , \n Du bist ein wahrer Kerk").queue());
            }
        }
    }
    private static String[] dropFirstString(String[] array) {
        String[] strings = new String[array.length -1];
        System.arraycopy(array, 1, strings, 0, array.length -1);
        return strings;
    }

}
