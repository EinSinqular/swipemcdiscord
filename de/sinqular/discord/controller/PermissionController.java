package de.sinqular.discord.controller;

import net.dv8tion.jda.core.entities.Member;

public class PermissionController {

    public boolean isOwner(Member member) {
        return member.isOwner();
    }
    public boolean isAdmin(Member member) {
        return member.getRoles().contains(member.getGuild().getRolesByName("Serverinhaber", true).get(0));
    }
}
