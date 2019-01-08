package kr.tpsw.triggertool2;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import tirggertool2.kr.tpsw.api.bukkit.API;
import tirggertool2.kr.tpsw.api.bukkit.PlayersAPI;
import tirggertool2.kr.tpsw.api.publica.StringAPI;

@SuppressWarnings("unused")
public abstract class AbstractTTCommand {

	private static final String ALL_PLAYER = "#a";
	private static final String ALL_OP = "#opa";
	private static final String ALL_OFFPLINE_LAYER = "#offa";
	private static final String ALL_LEVEL_DOWN = "#leveldown";
	private static final String ALL_LEVEL_UP = "#levelup";
	private static final String ALL_LEVEL_EQUAL = "#levelequal";
	private static final String RANDOM_PLAYER = "#r";
	private static final String RANDOM_OFFLINE_PLAYER = "#offr";
	private static final String RANDOM_OP = "#opr";
	private static final String SEARCH_ONLINE = "#search";
	private static final String SEARCH_OFFLINE = "#searchoff";

	private boolean hasEscapeSequence(String s) {
		return StringAPI.contains(s, '#');
	}

	private static Random random = new Random();

	abstract public void run(Player target, String[] args);

	abstract public boolean isValidArguments(String[] args);

	public void runCommand(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (StringAPI.contains(args[i], '&'))
				args[i] = args[i].replace('&', '§');
		}

		boolean hasEscapeSequence = false;
		boolean[] ran = new boolean[args.length];
		boolean[] offran = new boolean[args.length];
		boolean[] allarr = new boolean[args.length];
		// boolean opran = false;

		int all = -1;
		// 0 #a, 1 #offa,2 #opr 3 #levelup, 4 #leveldown, 5 #levelequal,
		// 6 #search, 7 #searchoff

		for (int i = 0; i < args.length; i++) {
			if (hasEscapeSequence(args[i])) {
				hasEscapeSequence = true;
				if (args[i].contains(RANDOM_PLAYER)) {
					ran[i] = true;
				} else if (args[i].contains(RANDOM_OFFLINE_PLAYER)) {
					offran[i] = true;
				}
				// else if (args[i].contains(RANDOM_OP)) {
				// opran = true;
				// }

				if (all == -1) {
					if (args[i].contains(ALL_PLAYER)) {
						all = 0;
					} else if (args[i].contains(ALL_OFFPLINE_LAYER)) {
						all = 1;
					} else if (args[i].contains(ALL_OP)) {
						all = 2;
					} else if (args[i].contains(ALL_LEVEL_UP)) {
						all = 3;
					} else if (args[i].contains(ALL_LEVEL_DOWN)) {
						all = 4;
					} else if (args[i].contains(ALL_LEVEL_EQUAL)) {
						all = 5;
					} else if (args[i].contains(SEARCH_ONLINE)) {
						all = 6;
					} else if (args[i].contains(SEARCH_OFFLINE)) {
						all = 7;
					} else {
						continue;
					}
					allarr[i] = true;
				}
			}
		}

		// System.out.println("all " + all);
		// System.out.println("allarr " + Arrays.toString(allarr));
		// System.out.println("ran " + Arrays.toString(ran));
		// System.out.println("offran " + Arrays.toString(offran));

		if (!hasEscapeSequence) {
			if (args.length == 0) {
				this.run(null, null);
			} else {
				String s = PlayersAPI.findPlayerName(args[0]);
				if (s != null) {
					this.run(Bukkit.getPlayer(s), args);
				} else {
					this.run(null, args);
				}
			}
		} else {
			String[] args2;
			switch (all) {
			case -1: {
				args2 = getReplacedArgs(args, ran, offran);
				String s = PlayersAPI.findPlayerName(args2[0]);
				if (s != null)
					this.run(Bukkit.getPlayer(s), args2);
				break;
			}

			case 0:
				for (Player p : PlayersAPI.getOnlinePlayers()) {
					args2 = getReplacedArgs(args, ran, offran);
					for (int i = 0; i < args2.length; i++) {
						args2[i] = args2[i].replace("#a", p.getName());
					}
					this.run(PlayersAPI.getPlayer(args2[0]), args2);
				}
				break;

			case 1:
				for (OfflinePlayer p : PlayersAPI.getOfflinePlayers()) {
					args2 = getReplacedArgs(args, ran, offran);
					for (int i = 0; i < args2.length; i++) {
						args2[i] = args2[i].replace("#offa", p.getName());
					}
					this.run(PlayersAPI.getPlayer(args2[0]), args2);
				}
				break;

			case 2:
				for (Player p : PlayersAPI.getOnlinePlayers()) {
					if (p.isOp()) {
						args2 = getReplacedArgs(args, ran, offran);
						for (int i = 0; i < args2.length; i++) {
							args2[i] = args2[i].replace("#opa", p.getName());
						}
						this.run(PlayersAPI.getPlayer(args2[0]), args2);
					}
				}
				break;

			case 3: {
				// 이상
				int level = 0;
				for (int i = 0; i < args.length; i++) {
					if (allarr[i]) {
						int i1 = args[i].indexOf("#levelup");
						String s = args[i].substring(i1 + 8, args[i].length());
						if (API.isIntegerPositive(s)) {
							level = Integer.valueOf(s);
						} else {
							// System.out.println("레벨 업 잘못됨 <" + s + ">");
							return;
						}
					}
				}
				for (Player p : PlayersAPI.getOnlinePlayers()) {
					if (p.getLevel() >= level) {
						args2 = getReplacedArgs(args, ran, offran);
						for (int i = 0; i < args2.length; i++) {
							if (allarr[i]) {
								args2[i] = p.getName();
							}
						}
						this.run(PlayersAPI.getPlayer(args2[0]), args2);
					}
				}
				break;
			}

			case 4: {
				// 이하
				int level = 0;
				for (int i = 0; i < args.length; i++) {
					if (allarr[i]) {
						int i1 = args[i].indexOf("#leveldown");
						String s = args[i].substring(i1 + 10, args[i].length());
						if (API.isIntegerPositive(s)) {
							level = Integer.valueOf(s);
						} else {
							// System.out.println("레벨 다운 잘못됨 <" + s + ">");
							return;
						}
					}
				}
				for (Player p : PlayersAPI.getOnlinePlayers()) {
					if (p.getLevel() <= level) {
						args2 = getReplacedArgs(args, ran, offran);
						for (int i = 0; i < args2.length; i++) {
							if (allarr[i]) {
								args2[i] = p.getName();
							}
						}
						this.run(PlayersAPI.getPlayer(args2[0]), args2);
					}
				}
				break;
			}

			case 5: {
				// 동일
				int level = 0;
				for (int i = 0; i < args.length; i++) {
					if (allarr[i]) {
						int i1 = args[i].indexOf("#levelequal");
						String s = args[i].substring(i1 + 11, args[i].length());
						if (API.isIntegerPositive(s)) {
							level = Integer.valueOf(s);
						} else {
							// System.out.println("레벨 이퀄 잘못됨 <" + s + ">");
							return;
						}
					}
				}
				for (Player p : PlayersAPI.getOnlinePlayers()) {
					if (p.getLevel() == level) {
						args2 = getReplacedArgs(args, ran, offran);
						for (int i = 0; i < args2.length; i++) {
							if (allarr[i]) {
								args2[i] = p.getName();
							}
						}
						this.run(PlayersAPI.getPlayer(args2[0]), args2);
					}
				}
				break;
			}

			case 6: {
				// 서치
				String search = null;
				for (int i = 0; i < args.length; i++) {
					if (allarr[i]) {
						int i1 = args[i].indexOf("#search");
						String s = args[i].substring(i1 + 7, args[i].length());
						String target = PlayersAPI.findPlayerName(s);
						if (target == null) {
							// System.out.println("서치 잘못됨 <" + s + ">");
							return;
						} else {
							search = target;
						}
					}
				}
				args2 = getReplacedArgs(args, ran, offran);
				for (int i = 0; i < args2.length; i++) {
					if (allarr[i]) {
						args2[i] = search;
					}
				}
				this.run(PlayersAPI.getPlayer(args2[0]), args2);

				break;
			}

			case 7: {
				// 서치
				String search = null;
				for (int i = 0; i < args.length; i++) {
					if (allarr[i]) {
						int i1 = args[i].indexOf("#searchoff");
						String s = args[i].substring(i1 + 10, args[i].length());
						String target = PlayersAPI.findOfflinePlayerName(s);
						if (target == null) {
							// System.out.println("서치 오프라인 잘못됨 <" + s + ">");
							return;
						} else {
							search = target;
						}
					}
				}
				args2 = getReplacedArgs(args, ran, offran);
				for (int i = 0; i < args2.length; i++) {
					if (allarr[i]) {
						args2[i] = search;
					}
				}
				this.run(PlayersAPI.getPlayer(args2[0]), args2);
				break;
			}

			default:
				System.out.println("잘못된 all 코드");
				break;
			}
		}
	}

	public String[] getReplacedArgs(String[] args, boolean[] ran, boolean[] offran) {
		String[] args2 = new String[args.length];
		for (int i = 0; i < args.length; i++) {
			args2[i] = replaceArg(args[i], ran[i], offran[i]);
		}
		return args2;
	}

	public String replaceArg(String arg, boolean r, boolean offr) {
		if (r) {
			List<Player> on = PlayersAPI.getOnlinePlayers();
			String target;
			if (on.size() == 0) {
				target = "#r";
			} else {
				target = on.get(random.nextInt(on.size())).getName();
			}
			arg = arg.replace("#r", target);
		}
		if (offr) {
			List<OfflinePlayer> on = PlayersAPI.getOfflinePlayers();
			String target;
			if (on.size() == 0) {
				target = "#offr";
			} else {
				target = on.get(random.nextInt(on.size())).getName();
			}
			arg = arg.replace("#offr", target);
		}
		return arg;
	}
}
