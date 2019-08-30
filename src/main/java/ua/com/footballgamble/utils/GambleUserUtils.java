package ua.com.footballgamble.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ua.com.footballgamble.model.entity.GambleEntity;
import ua.com.footballgamble.model.entity.GambleUser;

public class GambleUserUtils {
	public static List<GambleUser> filterByGamble(List<GambleUser> list, GambleEntity gamble) {
		// logger.info("Get All Gamble Users List for " + gamble);

		if (list == null || list.isEmpty()) {
			return null;
		}
		if (gamble == null || gamble.getParticipants() == null || gamble.getParticipants().isEmpty()) {
			return list;
		}
		List<GambleUser> filteredlist = new ArrayList<>();
		Map<Long, GambleUser> userMap = gamble.getParticipants().stream()
				.collect(Collectors.toMap(GambleUser::getId, gambleUser -> gambleUser));
		for (GambleUser gambleUser : list) {
			if (!userMap.containsKey(gambleUser.getId())) {
				filteredlist.add(gambleUser);
			}
		}

		return filteredlist;
	}

}
