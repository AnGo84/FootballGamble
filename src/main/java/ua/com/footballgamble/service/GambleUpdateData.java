package ua.com.footballgamble.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ua.com.footballgamble.model.entity.GambleCompetition;
import ua.com.footballgamble.model.entity.GambleEntity;
import ua.com.footballgamble.model.entity.GambleMatchEntity;
import ua.com.footballgamble.model.entity.GambleUser;

public class GambleUpdateData {
	public static final Logger logger = LoggerFactory.getLogger(GambleUpdateData.class);
	private GambleEntity gambleEntity;
	// private List<MatchEntity> matches;
	private List<GambleMatchEntity> gambleMatches;

	public GambleUpdateData() {
		super();
	}

	public GambleUpdateData(GambleEntity gambleEntity, List<GambleMatchEntity> gambleMatches) {
		super();
		this.gambleEntity = gambleEntity;
		this.gambleMatches = gambleMatches;
	}

	public GambleEntity getGambleEntity() {
		return gambleEntity;
	}

	public void setGambleEntity(GambleEntity gambleEntity) {
		this.gambleEntity = gambleEntity;
	}

	public List<GambleMatchEntity> getGambleMatches() {
		return gambleMatches;
	}

	public void setGambleMatches(List<GambleMatchEntity> gambleMatches) {
		this.gambleMatches = gambleMatches;
	}

	public boolean checkMissDataForMatches() {
		if (gambleEntity != null && (isGambleCompetitionsBlank() || isGambleParticipantsBlank())) {
			return true;
		}
		return false;
	}

	public List<GambleMatchEntity> gambleMatchesForDelete() {

		if (gambleEntity != null && gambleMatches != null && !gambleMatches.isEmpty() && !isGambleCompetitionsBlank()
				&& !isGambleParticipantsBlank()) {
			Long gambleId = gambleEntity.getId();

			List<GambleCompetition> competitions = gambleEntity.getCompetitions();
			List<GambleUser> participants = gambleEntity.getParticipants();

			List<GambleMatchEntity> deleteGambleMatchList = new ArrayList<>();
			for (GambleMatchEntity match : gambleMatches) {
				for (GambleCompetition competition : competitions) {
					for (GambleUser user : participants) {
						if (isExist(gambleId, competition.getId(), match.getMatchId(), user.getId()) == null) {
							deleteGambleMatchList.add(match);
						}
					}
				}
			}
			return deleteGambleMatchList;
		}
		return null;
	}

	//
	public boolean isGambleCompetitionsBlank() {
		return (gambleEntity.getCompetitions() == null || gambleEntity.getCompetitions().isEmpty());
	}

	public boolean isGambleParticipantsBlank() {
		return (gambleEntity.getParticipants() == null || gambleEntity.getParticipants().isEmpty());
	}

	//
	public GambleMatchEntity isExist(GambleMatchEntity object) {
		if (gambleMatches != null && !gambleMatches.isEmpty()) {
			for (GambleMatchEntity gambleMatch : gambleMatches) {
				if (gambleMatch.getGambleId().equals(object.getGambleId())
						&& gambleMatch.getCompetitionId().equals(object.getCompetitionId())
						&& gambleMatch.getMatchId().equals(object.getMatchId())
						&& gambleMatch.getUserId().equals(object.getUserId())

				) {
					return gambleMatch;
				}
			}
		}
		return null;
	}

	public GambleMatchEntity isExist(Long gambleId, Long competitionId, Long matchId, Long userId) {
		if (gambleMatches != null && !gambleMatches.isEmpty()) {
			for (GambleMatchEntity gambleMatch : gambleMatches) {
				if (gambleMatch.getGambleId().equals(gambleId) && gambleMatch.getCompetitionId().equals(competitionId)
						&& gambleMatch.getMatchId().equals(matchId) && gambleMatch.getUserId().equals(userId)) {
					return gambleMatch;
				}
			}
		}
		return null;
	}

	public boolean isExistMatchesByStage(Long competitionId, String stage) {
		if (gambleMatches != null && !gambleMatches.isEmpty()) {
			for (GambleMatchEntity gambleMatch : gambleMatches) {
				if (gambleMatch.getGambleId().equals(gambleEntity.getId())
						&& gambleMatch.getCompetitionId().equals(competitionId)
						&& gambleMatch.getStage().equals(stage)) {
					return true;
				}
			}
		}
		return false;
	}

}
