package by.epam.litvin.util;

import by.epam.litvin.constant.SQLFieldConstant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Packer {


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
