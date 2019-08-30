package ua.com.footballgamble.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import ua.com.footballgamble.model.entity.MatchEntity;
import ua.com.footballgamble.model.entity.SeasonEntity;
import ua.com.footballgamble.model.entity.SeasonStage;

public class SeasonUtils {
	public static String getYearFromDate(String date) {
		if (StringUtils.isBlank(date)) {
			return date;
		}
		String year = StringUtils.substringBefore(date.trim(), "-");
		return year;
	}

	public static int getIntYearFromDate(String date) {
		String year = getYearFromDate(date);
		try {
			return Integer.valueOf(year);
		} catch (Exception e) {

		}
		return -1;
	}

	public static Map<String, SeasonStage> getSeasonStagesMap(SeasonEntity season) {
		if (season == null || season.getMatches() == null || season.getMatches().isEmpty()) {
			return null;
		}
		Map<String, SeasonStage> map = new HashMap<>();

		for (MatchEntity match : season.getMatches()) {
			if (map.containsKey(match.getStage())) {
				SeasonStage seasonStage = map.get(match.getStage());
				if (seasonStage.getFrom().compareTo(match.getUtcDateAsDate()) > 0) {
					seasonStage.setFrom(match.getUtcDateAsDate());
				} else if (seasonStage.getTill().compareTo(match.getUtcDateAsDate()) < 0) {
					seasonStage.setTill(match.getUtcDateAsDate());
				}
			} else {
				map.put(match.getStage(),
						new SeasonStage(match.getStage(), match.getUtcDateAsDate(), match.getUtcDateAsDate()));
			}
		}

		List<SeasonStage> seasonStagesList = map.values().stream().sorted(Comparator.comparing(SeasonStage::getFrom))
				.collect(Collectors.toList());
		int i = 0;
		for (SeasonStage seasonStage : seasonStagesList) {
			seasonStage.setId(i++);
			map.put(seasonStage.getName(), seasonStage);
		}

		return map;
	}

}
