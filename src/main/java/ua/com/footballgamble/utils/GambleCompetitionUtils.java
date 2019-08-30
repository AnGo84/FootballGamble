package ua.com.footballgamble.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ua.com.footballgamble.model.entity.GambleCompetition;
import ua.com.footballgamble.model.entity.GambleEntity;

public class GambleCompetitionUtils {
	public static List<GambleCompetition> filterByGamble(List<GambleCompetition> list, GambleEntity gamble) {
		// logger.info("Get All Gamble Competitions List for " + gamble);

		if (list == null || list.isEmpty()) {
			return null;
		}
		if (gamble == null || gamble.getCompetitons() == null || gamble.getCompetitons().isEmpty()) {
			return list;
		}
		List<GambleCompetition> filteredlist = new ArrayList<>();
		Map<Long, GambleCompetition> competitionMap = gamble.getCompetitons().stream()
				.collect(Collectors.toMap(GambleCompetition::getId, gambleCompetition -> gambleCompetition));
		for (GambleCompetition gambleCompetition : list) {
			if (!competitionMap.containsKey(gambleCompetition.getId())) {
				filteredlist.add(gambleCompetition);
			}
		}
		return filteredlist;
	}

}
