package br.com.mioto.cloud;

import br.com.mioto.cloud.external.AHP;

public class Main {

    public static void main(String[] args) {

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
    }



    public static String[] getLabels() {
        final String labels[] = {"Interdependence","Response Time","Unit Test Coverage","Technical Debt","Comp. Resource Usage"};
        return labels;
    }

    //obter os valores de cada uma das caracteristicas para multiplicar por cada um dos pesos obtidos <ou> somente ordenar os microserviços de cada visão em relação aos pesos (abordagem menos correta)

    //Exemplo dos pesos abaixo:
    //Interdependence: 31.446613898467152
    //Response Time: 21.253557094972596
    //Unit Test Coverage: 20.63441958116334
    //Technical Debt: 11.816404599343866
    //Comp. Resource Usage: 14.849004826053047

}
