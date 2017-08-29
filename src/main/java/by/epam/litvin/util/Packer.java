package by.epam.litvin.util;

import by.epam.litvin.bean.CompetitionTypeEntity;
import by.epam.litvin.bean.KindOfSportEntity;
import by.epam.litvin.constant.SQLFieldConstant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Packer {

    /**
     * Order kind of sport.
     *
     * @param kindOfSportList
     * @return
     */
    public  Map<KindOfSportEntity, List<CompetitionTypeEntity>>
    orderKindsOfSport(List<Map<String, Object>> kindOfSportList) {

        Map<KindOfSportEntity, List<CompetitionTypeEntity>> kindsOfSport = new HashMap<>();


        for (Map<String, Object> map :  kindOfSportList) {
            String sportName = (String) map.get(SQLFieldConstant.KindOfSport.NAME);
            int sportId = (int) map.get(SQLFieldConstant.KindOfSport.ID);
            String typeName = (String) map.get(SQLFieldConstant.CompetitionType.NAME);
            int typeId = (int) map.get(SQLFieldConstant.CompetitionType.ID);

            KindOfSportEntity sport = new KindOfSportEntity() {{
                setName(sportName);
                setId(sportId);
            }};
            CompetitionTypeEntity type = new CompetitionTypeEntity() {{
                setName(typeName);
                setId(typeId);
            }};

            if (!kindsOfSport.containsKey(sport)) {
                kindsOfSport.put(sport, new ArrayList<>());
            }

            kindsOfSport.get(sport).add(type);
        }

        return kindsOfSport;
    }
}
