package kr.tpsw.triggertool2.command;

import java.util.Arrays;

import kr.tpsw.triggertool2.AbstractTTCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tirggertool2.kr.tpsw.api.bukkit.API;

public class SetmaxhealthCommand extends AbstractTTCommand implements CommandExecutor {

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
			if (args[1].equals("reset")) {
				target.resetMaxHealth();
			} else {
				int h = Integer.valueOf(args[1]);
				target.setMaxHealth(h);
			}
		}

	}

	@Override
	public boolean isValidArguments(String[] args) {
		if (args.length == 2) {
			if (API.isIntegerPositive(args[1]) || args[1].equals("reset")) {
				return true;
			}
		}
		return false;
	}

}
