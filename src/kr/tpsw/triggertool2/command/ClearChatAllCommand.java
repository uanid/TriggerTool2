package kr.tpsw.triggertool2.command;

import java.util.Arrays;

import kr.tpsw.triggertool2.AbstractTTCommand;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import tirggertool2.kr.tpsw.api.bukkit.PlayersAPI;

public class ClearChatAllCommand extends AbstractTTCommand implements CommandExecutor {

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
		for (Player p : PlayersAPI.getOnlinePlayers()) {
			for (int i = 0; i < 20; i++) {
				p.sendMessage("");
			}
		}
		Bukkit.getConsoleSender().sendMessage("[TriggerTool2] 채팅창이 청소되었습니다.");
	}

	@Override
	public boolean isValidArguments(String[] args) {
		return true;
	}

}
