package ua.com.footballgamble.utils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import ua.com.footballgamble.model.entity.SeasonEntity;

public class SelectItemUtils {

	public static List<SelectItem> getSelectItemList(List<SeasonEntity> seasonsList) {
		if (seasonsList == null || seasonsList.isEmpty()) {
			return null;
		}
		List<SelectItem> list = new ArrayList<>();

		seasonsList.stream().forEach(item -> list.add(new SelectItem(item.getId(), item.getSeasonName())));

		return list;
	}

}
