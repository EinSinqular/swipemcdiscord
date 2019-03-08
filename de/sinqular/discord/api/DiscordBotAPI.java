package de.sinqular.discord.api;

import de.sinqular.discord.Start;
import de.sinqular.discord.utils.LogLevel;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class DiscordBotAPI {

    public static void banPlayer(Guild guild, Member member, int time) {
        guild.getController().ban(member, time);
        Start.getLogger().log(LogLevel.INFORMATION, member.getNickname() + " wurde gebannt");
    }
    public static void mutePlayer(Guild guild, User objUser, MessageReceivedEvent e, String reason) {
        guild.getController().addSingleRoleToMember(guild.getMember(objUser), guild.getRolesByName("Muted", true).get(0));
        e.getMember().getUser().openPrivateChannel().queue(success -> success.sendMessage("Du wurdest vom SwipeMC.net Discord gemutet \nGrund: " + reason).queue());
        Start.getLogger().log(LogLevel.INFORMATION, e.getAuthor().getName() + " wurde gemutet");

    }


}
