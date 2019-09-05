package ua.com.footballgamble.model.entity;

public class GambleMatchScore {
	private GambleMatchEntity gambleMatch;

	public GambleMatchScore(GambleMatchEntity gambleMatch) {
		super();
		this.gambleMatch = gambleMatch;
	}

	public GambleMatchEntity getGambleMatch() {
		return gambleMatch;
	}

	//
	public void calcMatchScore() {
		Integer totalScore = null;
		if (gambleMatch != null && gambleMatch.getRule() != null && gambleMatch.getMatch() != null) {

			if (isMatchHasScore() && isGambleMatchHasScore()) {
				if (isExactScore()) {
					totalScore = gambleMatch.getRule().getExactScore();
				} else if (isOnlyDraw()) {
					totalScore = gambleMatch.getRule().getOnlyDraw();
				} else if (isGuessWinnerAndDifference()) {
					totalScore = gambleMatch.getRule().getWinnerAndDifferance();
				} else if (isGuessWinner()) {
					totalScore = gambleMatch.getRule().getOnlyWinner();
				} else {
					totalScore = gambleMatch.getRule().getOther();
				}

			}

		}
		gambleMatch.setTotal(totalScore);

	}

	public boolean isGuessWinnerAndDifference() {
		return isGuessWinner() && isGuessDifferance();
	}

	public boolean isGuessWinner() {
		return Integer.compare(gambleMatch.getMatch().getScoreFullTimeHomeTeam(),
				gambleMatch.getMatch().getScoreFullTimeAwayTeam()) == Integer
						.compare(gambleMatch.getScoreFullTimeHomeTeam(), gambleMatch.getScoreFullTimeAwayTeam());
	}

	public boolean isGuessDifferance() {
		return Integer.compare(
				gambleMatch.getMatch().getScoreFullTimeHomeTeam() - gambleMatch.getMatch().getScoreFullTimeAwayTeam(),
				gambleMatch.getScoreFullTimeHomeTeam() - gambleMatch.getScoreFullTimeAwayTeam()) == 0;
	}

	public boolean isOnlyDraw() {
		return Integer.compare(gambleMatch.getMatch().getScoreFullTimeHomeTeam(),
				gambleMatch.getMatch().getScoreFullTimeAwayTeam()) == 0
				&& Integer.compare(gambleMatch.getScoreFullTimeHomeTeam(), gambleMatch.getScoreFullTimeAwayTeam()) == 0;
	}

	public boolean isExactScore() {
		return gambleMatch.getMatch().getScoreFullTimeHomeTeam().equals(gambleMatch.getScoreFullTimeHomeTeam())
				&& gambleMatch.getMatch().getScoreFullTimeAwayTeam().equals(gambleMatch.getScoreFullTimeAwayTeam());
	}

	public boolean isMatchHasScore() {
		return gambleMatch.getMatch().getScoreFullTimeHomeTeam() != null
				&& gambleMatch.getMatch().getScoreFullTimeAwayTeam() != null;
	}

	public boolean isGambleMatchHasScore() {
		return gambleMatch.getScoreFullTimeHomeTeam() != null && gambleMatch.getScoreFullTimeAwayTeam() != null;
	}

}
