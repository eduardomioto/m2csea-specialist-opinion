package br.com.mioto.cloud.bo.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.mioto.cloud.bo.AhpBO;
import br.com.mioto.cloud.external.AHP;

@Component
public class AhpBOImpl implements AhpBO {

    @Override
    public AHP calculateAHP() throws SQLException {
        return this.calculate();
    }

    public AHP calculate() {

        final String labels[] = {"Interdependence","Response Time","Unit Test Coverage","Technical Debt","Comp. Resource Usage", "Availability"};

        final int nrVx = labels.length;

        final AHP ahp = new AHP(nrVx);
        System.out.println(ahp);

        final int nrOfPairwiseComparisons = ahp.getNrOfPairwiseComparisons();
        System.out.println("nrOfPairwiseComparisons: " + nrOfPairwiseComparisons);

        final double[] compArray = ahp.populateComparison(ahp);

        ahp.setPairwiseComparisonArray(compArray);

        for (int i = 0; i < ahp.getNrOfPairwiseComparisons(); i++) {
            System.out.print("Importance of " + labels[ahp.getIndicesForPairwiseComparison(i)[0]] + " compared to ");
            System.out.print(labels[ahp.getIndicesForPairwiseComparison(i)[1]] + "= ");
            System.out.println(ahp.getPairwiseComparisonArray()[i]);
        }

        System.out.println("\n" + ahp + "\n");

        System.out.println("Consistency Index: " + ahp.getConsistencyIndex());
        System.out.println("Consistency Ratio: " + ahp.getConsistencyRatio() + "%");
        System.out.println();
        final Map<Integer, Double> weightsMap = new HashMap<Integer, Double>();
        for (int k=0; k<ahp.getWeights().length; k++) {

            System.out.println("W: " + labels[k] + ": " + (ahp.getWeights()[k]));

            final Integer visionId = getVisionId(labels[k]);
            weightsMap.put(visionId, ahp.getWeights()[k]);
        }

        final Map<String, Double> rankingMap = new HashMap<String, Double>();
        for (int i = 0; i < ahp.getNrOfPairwiseComparisons(); i++) {
            System.out.print("Importance of " + labels[ahp.getIndicesForPairwiseComparison(i)[0]] + " compared to ");
            System.out.print(labels[ahp.getIndicesForPairwiseComparison(i)[1]] + "= ");
            System.out.println(ahp.getPairwiseComparisonArray()[i]);

            rankingMap.put(labels[ahp.getIndicesForPairwiseComparison(i)[0]] + "_" + labels[ahp.getIndicesForPairwiseComparison(i)[1]], ahp.getPairwiseComparisonArray()[i]);
        }

        ahp.setWeightsMap(weightsMap);
        ahp.setRankingMap(rankingMap);
        return ahp;
    }

    public Integer getVisionId(String label) {
        Integer visionId = 0;
        switch (label) {
            case "Comp. Resource Usage":
                visionId = 5;
                break;
            case "Response Time":
                visionId = 2;
                break;
            case "Availability":
                visionId = 6;
                break;
            case "Technical Debt":
                visionId = 4;
                break;
            case "Interdependence":
                visionId = 1;
                break;
            case "Unit Test Coverage":
                visionId = 3;
                break;
            default:
                break;
        }
        return visionId;
    }

    public static String[] getLabels() {
        final String labels[] = {"Interdependence","Response Time","Unit Test Coverage","Technical Debt","Comp. Resource Usage"};
        return labels;
    }

}
