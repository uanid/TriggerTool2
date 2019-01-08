package kr.tpsw.triggertool2.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TT2Command implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		int page = 0;
		if (args.length == 0) {
			page = 1;
		} else if (args[0].equals("1")) {
			page = 1;
		} else if (args[0].equals("2")) {
			page = 2;
		}

		if (page == 1) {
			sender.sendMessage("§a/cca");
			sender.sendMessage("§a/ccu <player>");
			sender.sendMessage("§a/cmd <player> <message>");
			sender.sendMessage("§a/cmdop <player> <message>");
			sender.sendMessage("§a/cmdcon <player> <message>");
			sender.sendMessage("§a/chat <player> <message>");
			sender.sendMessage("§a/chatop <player> <message>");
			sender.sendMessage("§a/talk <player> <message>");
			sender.sendMessage("§a/talkop <player> <message>");
			sender.sendMessage("§a/broadcast <message>");
			sender.sendMessage("§a/sendmessage <player> <message>");
			sender.sendMessage("§a/setallowfly <player> (true|false)");
			sender.sendMessage("§a/setdisplay <player> <display name>");
			sender.sendMessage("§a/setexhaustion <player> <exhaustion>");
			sender.sendMessage("§a다른 명령어를 보려면 /tt2 2");
		} else if (page == 2) {
			sender.sendMessage("§a/setexp <player> <0.0 - 1.0>");
			sender.sendMessage("§a/setfly <player> (true|false)");
			sender.sendMessage("§a/setflyspeed <player> <speed>");
			sender.sendMessage("§a/setwalkspeed <player> <speed>");
			sender.sendMessage("§a/setfood <player> <food level>");
			sender.sendMessage("§a/sethealth <player> <health>");
			sender.sendMessage("§a/setlevel <player> <level>");
			sender.sendMessage("§a/setmaxhealth <player> <amx health>");
			sender.sendMessage("§a/setplayertime <player> (<time>|reset)");
			sender.sendMessage("§a/setplayerweather <player> (downfall|clear|reset)");
			sender.sendMessage("§a/setsaturation <player> <saturaion>");
			sender.sendMessage("§a/setsneak <player> (true|false)");
			sender.sendMessage("§a/setsprint <player> (true|false)");
			sender.sendMessage("§a/setvelocity <player> <x> <y> <z>");
			
		} else {
			sender.sendMessage("§a올바른 명령어를 입력하세요.");
		}
		return true;
	}
}
