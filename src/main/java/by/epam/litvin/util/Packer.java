package by.epam.litvin.util;

import by.epam.litvin.constant.SQLFieldConstant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Packer {

    public List<Map<String, Object>> orderLiveAndUpcomingGames(List<Map<String, Object>> gameList) {
        List<Map<String, Object>> gameListResult = new ArrayList<>();

        for (Map<String, Object> liveGame : gameList) {
            Integer competitionId = (Integer) liveGame.get(SQLFieldConstant.Competition.ID);

            for (Map<String, Object> liveGame1 : gameList) {
                Integer competitionId1 = (Integer) liveGame.get(SQLFieldConstant.Competition.ID);

                long count = gameListResult.stream().filter(stringObjectMap -> {
                    Integer id = (Integer) stringObjectMap.get(SQLFieldConstant.Competition.ID);
                    return id.equals(competitionId1);
                }).count();

                if (liveGame != liveGame1 &&
                        competitionId.equals(competitionId1) && count == 0) {
                    ArrayList<String> commandNames = new ArrayList<>();
                    ArrayList<BigDecimal> competitorWinCoeffs = new ArrayList<>();
                    ArrayList<Integer> competitorIds = new ArrayList<>();

                    commandNames.add((String) liveGame.get(SQLFieldConstant.Command.NAME));
                    commandNames.add((String) liveGame1.get(SQLFieldConstant.Command.NAME));
                    competitorWinCoeffs.add((BigDecimal) liveGame.get(SQLFieldConstant.Competitor.WIN_COEFF));
                    competitorWinCoeffs.add((BigDecimal) liveGame1.get(SQLFieldConstant.Competitor.WIN_COEFF));
                    competitorIds.add((Integer) liveGame.get(SQLFieldConstant.Competitor.ID));
                    competitorIds.add((Integer) liveGame1.get(SQLFieldConstant.Competitor.ID));

                    liveGame.put(SQLFieldConstant.Command.NAME, commandNames);
                    liveGame.put(SQLFieldConstant.Competitor.WIN_COEFF, competitorWinCoeffs);
                    liveGame.put(SQLFieldConstant.Competitor.ID, competitorIds);
                    gameListResult.add(liveGame);

                    break;
                }
            }
        }

        return gameListResult;
    }

    public List<Map<String, Object>> orderPastGames(List<Map<String, Object>> gameList) {
        List<Map<String, Object>> gameListResult = new ArrayList<>();

        for (Map<String, Object> liveGame : gameList) {
            Integer competitionId = (Integer) liveGame.get(SQLFieldConstant.Competition.ID);

            for (Map<String, Object> liveGame1 : gameList) {
                Integer competitionId1 = (Integer) liveGame.get(SQLFieldConstant.Competition.ID);

                long count = gameListResult.stream().filter(stringObjectMap -> {
                    Integer id = (Integer) stringObjectMap.get(SQLFieldConstant.Competition.ID);
                    return id.equals(competitionId1);
                }).count();

                if (liveGame != liveGame1 &&
                        competitionId.equals(competitionId1) && count == 0) {
                    ArrayList<String> commandNames = new ArrayList<>();
                    ArrayList<Integer> competitorWinCoeff = new ArrayList<>();

                    competitorWinCoeff.add((Integer) liveGame.get(SQLFieldConstant.Competitor.RESULT));
                    competitorWinCoeff.add((Integer) liveGame1.get(SQLFieldConstant.Competitor.RESULT));
                    commandNames.add((String) liveGame.get(SQLFieldConstant.Command.NAME));
                    commandNames.add((String) liveGame1.get(SQLFieldConstant.Command.NAME));

                    liveGame.put(SQLFieldConstant.Command.NAME, commandNames);
                    liveGame.put(SQLFieldConstant.Competitor.RESULT, competitorWinCoeff);
                    gameListResult.add(liveGame);

                    break;
                }
            }
        }

        return gameListResult;
    }


    public  Map<String,Map<String, Integer>> orderKindsOfSport(List<Map<String, Object>> kindOfSportList) {
        Map<String, Map<String, Integer>> kindOfSportListResult = new HashMap<>();


        for (Map<String,Object> kind :  kindOfSportList) {
            String kindOfSportName = (String) kind.get(SQLFieldConstant.KindOfSport.NAME);
            boolean hasCoincidence = false;

            for (String s : kindOfSportListResult.keySet()) {
                if (s.equals(kindOfSportName)) {
                    String competitionType = (String) kind.get(SQLFieldConstant.CompetitionType.NAME);
                    Integer competition_type_id = (Integer) kind.get(SQLFieldConstant.CompetitionType.ID);
                    kindOfSportListResult.get(kindOfSportName).put(competitionType, competition_type_id);
                    hasCoincidence = true;
                    break;
                }
            }

            if (!hasCoincidence) {
                String competitionTypeName = (String) kind.get(SQLFieldConstant.CompetitionType.NAME);
                Integer competitionTypeId = (Integer) kind.get(SQLFieldConstant.CompetitionType.ID);
                HashMap<String, Integer> competitionTypeMap = new HashMap<>();
                competitionTypeMap.put(competitionTypeName, competitionTypeId);
                kindOfSportListResult.put(kindOfSportName, competitionTypeMap);
            }
        }

        return kindOfSportListResult;
    }
}
