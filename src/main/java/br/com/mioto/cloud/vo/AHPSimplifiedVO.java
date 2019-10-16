package br.com.mioto.cloud.vo;

import java.util.Map;

public class AHPSimplifiedVO {

    private Map<String, Double> weightsMap;
    private Map<String, Double> rankingMap;
    private Integer nrOfPairwiseComparisons;
    private Double consistencyRatio;
    private Double consistencyIndex;
    private Double eigenValue;


    public Map<String, Double> getRankingMap() {
        return rankingMap;
    }
    public void setRankingMap(Map<String, Double> rankingMap) {
        this.rankingMap = rankingMap;
    }
    public Map<String, Double> getWeightsMap() {
        return weightsMap;
    }
    public void setWeightsMap(Map<String, Double> weightsMap) {
        this.weightsMap = weightsMap;
    }

    public Integer getNrOfPairwiseComparisons() {
        return nrOfPairwiseComparisons;
    }
    public void setNrOfPairwiseComparisons(Integer nrOfPairwiseComparisons) {
        this.nrOfPairwiseComparisons = nrOfPairwiseComparisons;
    }
    public Double getConsistencyRatio() {
        return consistencyRatio;
    }
    public void setConsistencyRatio(Double consistencyRatio) {
        this.consistencyRatio = consistencyRatio;
    }
    public Double getConsistencyIndex() {
        return consistencyIndex;
    }
    public void setConsistencyIndex(Double consistencyIndex) {
        this.consistencyIndex = consistencyIndex;
    }
    public Double getEigenValue() {
        return eigenValue;
    }
    public void setEigenValue(Double eigenValue) {
        this.eigenValue = eigenValue;
    }



}
