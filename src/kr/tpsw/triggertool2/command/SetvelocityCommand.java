package kr.tpsw.triggertool2.command;

import java.util.Arrays;

import kr.tpsw.triggertool2.AbstractTTCommand;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import tirggertool2.kr.tpsw.api.bukkit.API;

public class SetvelocityCommand extends AbstractTTCommand implements CommandExecutor {

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
			target.setVelocity(new Vector(Double.valueOf(args[1]), Double.valueOf(args[2]), Double.valueOf(args[3])));
		}
	}

	@Override
	public boolean isValidArguments(String[] args) {
		if (args.length == 4) {
			if (API.isDouble(args[1]) && API.isDouble(args[2]) && API.isDouble(args[3])) {
				return true;
			}
			return false;
		}
		return false;
	}

}
