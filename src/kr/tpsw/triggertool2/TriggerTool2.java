package kr.tpsw.triggertool2;

import kr.tpsw.triggertool2.command.BroadcastCommand;
import kr.tpsw.triggertool2.command.ChatCommand;
import kr.tpsw.triggertool2.command.ChatopCommand;
import kr.tpsw.triggertool2.command.ClearChatAllCommand;
import kr.tpsw.triggertool2.command.ClearChatUserCommand;
import kr.tpsw.triggertool2.command.CmdCommand;
import kr.tpsw.triggertool2.command.CmdconCommand;
import kr.tpsw.triggertool2.command.CmdopCommand;
import kr.tpsw.triggertool2.command.SendmessageCommand;
import kr.tpsw.triggertool2.command.SetallowflyCommand;
import kr.tpsw.triggertool2.command.SetdisplaynameCommand;
import kr.tpsw.triggertool2.command.SetexhaustionCommand;
import kr.tpsw.triggertool2.command.SetexpCommand;
import kr.tpsw.triggertool2.command.SetflyCommand;
import kr.tpsw.triggertool2.command.SetflyspeedCommand;
import kr.tpsw.triggertool2.command.SetfoodCommand;
import kr.tpsw.triggertool2.command.SethealthCommand;
import kr.tpsw.triggertool2.command.SetlevelCommand;
import kr.tpsw.triggertool2.command.SetmaxhealthCommand;
import kr.tpsw.triggertool2.command.SetplayertimeCommand;
import kr.tpsw.triggertool2.command.SetplayerweatherCommand;
import kr.tpsw.triggertool2.command.SetsaturationCommand;
import kr.tpsw.triggertool2.command.SetsneakCommand;
import kr.tpsw.triggertool2.command.SetsprintCommand;
import kr.tpsw.triggertool2.command.SetvelocityCommand;
import kr.tpsw.triggertool2.command.SetwalkspeedCommand;
import kr.tpsw.triggertool2.command.TT2Command;
import kr.tpsw.triggertool2.command.TalkCommand;
import kr.tpsw.triggertool2.command.TalkopCommand;

import org.bukkit.plugin.java.JavaPlugin;

import tirggertool2.kr.tpsw.api.bukkit.PlayersAPI;

public class TriggerTool2 extends JavaPlugin {
	
	@Override
	public void onEnable() {
		PlayersAPI.initLoad(this);
		this.getCommand("tt2").setExecutor(new TT2Command());
		this.getCommand("chat").setExecutor(new ChatCommand());
		this.getCommand("broadcast").setExecutor(new BroadcastCommand());
		this.getCommand("chatop").setExecutor(new ChatopCommand());
		this.getCommand("cca").setExecutor(new ClearChatAllCommand());
		this.getCommand("ccu").setExecutor(new ClearChatUserCommand());
		this.getCommand("cmd").setExecutor(new CmdCommand());
		this.getCommand("cmdcon").setExecutor(new CmdconCommand());
		this.getCommand("cmdop").setExecutor(new CmdopCommand());
		this.getCommand("sendmessage").setExecutor(new SendmessageCommand());
		this.getCommand("setallowfly").setExecutor(new SetallowflyCommand());
		this.getCommand("setdisplay").setExecutor(new SetdisplaynameCommand());
		this.getCommand("setexhaustion").setExecutor(new SetexhaustionCommand());
		this.getCommand("setexp").setExecutor(new SetexpCommand());
		this.getCommand("setfly").setExecutor(new SetflyCommand());
		this.getCommand("setfood").setExecutor(new SetfoodCommand());
		this.getCommand("setflyspeed").setExecutor(new SetflyspeedCommand());
		this.getCommand("sethealth").setExecutor(new SethealthCommand());
		this.getCommand("setlevel").setExecutor(new SetlevelCommand());
		this.getCommand("setmaxlealth").setExecutor(new SetmaxhealthCommand());
		this.getCommand("setplayertime").setExecutor(new SetplayertimeCommand());
		this.getCommand("setplayerweather").setExecutor(new SetplayerweatherCommand());
		this.getCommand("setsaturation").setExecutor(new SetsaturationCommand());
		this.getCommand("setsneak").setExecutor(new SetsneakCommand());
		this.getCommand("setsprint").setExecutor(new SetsprintCommand());
		this.getCommand("setvelocity").setExecutor(new SetvelocityCommand());
		this.getCommand("setwalkspeed").setExecutor(new SetwalkspeedCommand());
		this.getCommand("talk").setExecutor(new TalkCommand());
		this.getCommand("talkop").setExecutor(new TalkopCommand());
		WordPressParsing.initRegister(this, this.getCommand("tt2update"), this.getFile());
	}

	@Override
	public void onDisable() {
			
	}

	
}
