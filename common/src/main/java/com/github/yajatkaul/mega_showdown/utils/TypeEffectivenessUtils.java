package com.github.yajatkaul.mega_showdown.utils;

import java.util.HashMap;
import java.util.Map;

public class TypeEffectivenessUtils {
    public static Map<String, Map<String, Double>> typeChartMap = new HashMap<>();

    /**
     * Effectiveness of a single attacking type against a single pure defending type.
     */
    public static double getEffectiveness(String attackingType, String defendingType) {
        attackingType = attackingType.toLowerCase();
        defendingType = defendingType.toLowerCase();

        Map<String, Double> defenderChart = typeChartMap.get(defendingType);
        if (defenderChart == null) {
            return -99.0;
        }
        Double multiplier = defenderChart.get(attackingType);
        if (multiplier == null) {
            return -99.0;
        }
        return multiplier;
    }

    /**
     * Effectiveness of a single attacking type against a (possibly dual-typed) defender.
     * Pass null or empty string for defendingType2 if the defender has only one type.
     */
    public static double getEffectiveness(String attackingType, String defendingType1, String defendingType2) {
        double multiplier = getEffectiveness(attackingType, defendingType1);
        if (defendingType2 != null && !defendingType2.isBlank()) {
            multiplier *= getEffectiveness(attackingType, defendingType2);
        }
        return multiplier;
    }
}
