package br.com.mioto.cloud.bo.impl;

import java.sql.SQLException;

import br.com.mioto.cloud.bo.AhpBO;
import br.com.mioto.cloud.external.AHP;

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
        System.out.println("Weights: ");
        for (int k=0; k<ahp.getWeights().length; k++) {
            System.out.println(labels[k] + ": " + (ahp.getWeights()[k] * 100));
        }
        return ahp;
    }

    public static String[] getLabels() {
        final String labels[] = {"Interdependence","Response Time","Unit Test Coverage","Technical Debt","Comp. Resource Usage"};
        return labels;
    }

}
