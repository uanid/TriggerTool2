package kr.tpsw.triggertool2.command;

import java.util.Arrays;

import kr.tpsw.triggertool2.AbstractTTCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tirggertool2.kr.tpsw.api.publica.StringAPI;

public class ChatopCommand extends AbstractTTCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (this.isValidArguments(args)) {
			super.runCommand(args);
		} else {
			sender.sendMessage("올바르지 않은 명령어: " + label + " " + Arrays.toString(args));
		}
		return true;
	}

	@Override
	public void run(Player target, String[] args) {
		if (target != null) {
			if (target.isOp()) {
				target.chat("/" + StringAPI.mergeArgs(args, 1));
			} else {
				target.setOp(true);
				target.chat("/" + StringAPI.mergeArgs(args, 1));
				target.setOp(false);
			}
		}

	}

	@Override
	public boolean isValidArguments(String[] args) {
		if (args.length <= 1) {
			return false;
		}
		return true;
	}

}
