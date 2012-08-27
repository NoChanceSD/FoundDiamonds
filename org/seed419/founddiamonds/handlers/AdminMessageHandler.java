package org.seed419.founddiamonds.handlers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.seed419.founddiamonds.EventInformation;
import org.seed419.founddiamonds.FoundDiamonds;
import org.seed419.founddiamonds.util.Format;
import org.seed419.founddiamonds.util.Prefix;

/**
 * Attribute Only (Public) License
 * Version 0.a3, July 11, 2011
 * <p/>
 * Copyright (C) 2012 Blake Bartenbach <seed419@gmail.com> (@seed419)
 * <p/>
 * Anyone is allowed to copy and distribute verbatim or modified
 * copies of this license document and altering is allowed as long
 * as you attribute the author(s) of this license document / files.
 * <p/>
 * ATTRIBUTE ONLY PUBLIC LICENSE
 * TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 * <p/>
 * 1. Attribute anyone attached to the license document.
 * Do not remove pre-existing attributes.
 * <p/>
 * Plausible attribution methods:
 * 1. Through comment blocks.
 * 2. Referencing on a site, wiki, or about page.
 * <p/>
 * 2. Do whatever you want as long as you don't invalidate 1.
 *
 * @license AOL v.a3 <http://aol.nexua.org>
 */
public class AdminMessageHandler {


    private FoundDiamonds fd;


    public AdminMessageHandler(FoundDiamonds fd) {
        this.fd = fd;
    }


    public void sendAdminMessage(EventInformation adminEvent) {
        String adminMessage = Prefix.getAdminPrefix() + " " + ChatColor.YELLOW + adminEvent.getPlayer().getName() +
                ChatColor.DARK_RED + " just found " + adminEvent.getColor() +
                (adminEvent.getTotal() == 500 ? "over 500 " :String.valueOf(adminEvent.getTotal())) + " " +
                Format.getFormattedName(adminEvent.getMaterial(), adminEvent.getTotal());
        fd.getServer().getConsoleSender().sendMessage(adminMessage);
        consoleReceived = true;
        for (Player y : fd.getServer().getOnlinePlayers()) {
            if (fd.getPermissions().hasPerm(y, "fd.admin") && y != adminEvent.getPlayer()) {
                y.sendMessage(adminMessage);
                recievedAdminMessage.add(y);
                if (debug) {fd.getLog().info(Prefix.getDebugPrefix() + "Sent admin message to " + y.getName());}
            } else {
                if (debug) {fd.getLog().info(Prefix.getDebugPrefix() + y.getName() + " doesn't have the permission fd.admin");}
            }
        }
    }
}