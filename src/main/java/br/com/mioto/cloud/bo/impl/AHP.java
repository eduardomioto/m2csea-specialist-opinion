/*
* Simple implementation of a Analytical Hierarchy Process
* The only thing this class is doing is calculate the weights (priorities) of
* some children nodes. Example is taken from http://www.thecourse.us/5/library/AHP/AHP_Tutorial.pdf
*
*/
package br.com.mioto.cloud.bo.impl;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealVector;

/**
 *
 * @author chris
 */
public class AHP {

    /**
     * Random Consistency Index
     */
    protected static double RI[] = {0.0, 0.0, 0.58, 0.9, 1.12, 1.24, 1.32, 1.41, 1.45, 1.49};

    /**
     * The matrix
     */
    protected Array2DRowRealMatrix mtx;

    /**
     * Contains
     */
    protected double pairwiseComparisonArray[];

    /**
     * Number of alternatives
     */
    protected int nrAlternatives;

    /**
     * The resulting weights/priorities
     */
    protected double weights[];

    /**
     * Corresponds to the weights
     */
    protected String labels[] = null;

    /**
     *
     */
    protected EigenDecomposition evd;

    /**
     * Convenience array, i.e. comparisonIndices[length=NumberOfPairwiseComparisons][2]
     * Contains minimum number of comparisons.
     */
    protected int[][] comparisonIndices;

    /**
     * Index of the greatest Eigenvalue/ -vector
     */
    protected int evIdx = 0; // index of actual eigenvalue/-vector

    /**
     *
     * @param labels
     */
    public AHP(String labels[]) {
        this(labels.length);
        this.labels = labels;
    }

    /**
     * Construct an AHP with number of alternatives
     * @param nrAlternatives
     */
    public AHP(int nrAlternatives) {
        this.nrAlternatives = nrAlternatives;
        mtx = new Array2DRowRealMatrix(nrAlternatives, nrAlternatives);
        weights = new double[nrAlternatives];

        pairwiseComparisonArray = new double[getNrOfPairwiseComparisons()];
        comparisonIndices = new int[getNrOfPairwiseComparisons()][];
        for (int i = 0; i < getNrOfPairwiseComparisons(); i++) {
            comparisonIndices[i] = new int[2];
        }

        // only need diagonal 1, but set everything to 1.0
        for (int row = 0; row < nrAlternatives; row++) {
            for (int col = 0; col < nrAlternatives; col++) {
                mtx.setEntry(row, col, 1.0);
            }
        }
    }

    /**
     *
     * @return the number of pairwise comparisons which have to be done by the user
     */
    public int getNrOfPairwiseComparisons() {
        return ((nrAlternatives - 1) * nrAlternatives) / 2;
    }

    /**
     *
     * @return the user input of the pairwise comparisons
     */
    public double[] getPairwiseComparisonArray() {
        return pairwiseComparisonArray;
    }

    /**
     * Set the pairwise comparison scores and calculate all relevant numbers
     * @param a
     */
    public void setPairwiseComparisonArray(double a[]) {
        int i = 0;
        for (int row = 0; row < nrAlternatives; row++) {
            for (int col = row + 1; col < nrAlternatives; col++) {
                //System.out.println(row + "/" + col + "=" + a[i]);
                mtx.setEntry(row, col, a[i]);
                mtx.setEntry(col, row, 1.0 / mtx.getEntry(row, col));
                comparisonIndices[i][0] = row;
                comparisonIndices[i][1] = col;
                i++;
            }
        }
        evd = new EigenDecomposition(mtx);

        evIdx = 0;
        for (int k = 0; k < evd.getRealEigenvalues().length; k++) {
            //System.out.println(evd.getRealEigenvalues()[k]);
            evIdx = (evd.getRealEigenvalue(k) > evd.getRealEigenvalue(evIdx)) ? k : evIdx;
        }
        System.out.println("evIdx=" + evIdx);
        System.out.println("EigenValue=" + evd.getRealEigenvalue(evIdx));

        double sum = 0.0;
        final RealVector v = evd.getEigenvector(evIdx);
        for (final double d : v.toArray()) {
            sum += d;
        }
        System.out.println(sum);
        for (int k = 0; k < v.getDimension(); k++) {
            weights[k] = v.getEntry(k) / sum;
        }
    }

    /**
     *
     * @param arrayIdx
     * @return
     */
    public int[] getIndicesForPairwiseComparison(int arrayIdx) {
        return comparisonIndices[arrayIdx];
    }

    /**
     *
     * @return resulting weights for alternatives
     */
    public double[] getWeights() {
        return weights;
    }

    /**
     *
     * @return the consistency index
     */
    public double getConsistencyIndex() {
        return (evd.getRealEigenvalue(evIdx) - nrAlternatives) / (nrAlternatives - 1);
    }

    /**
     *
     * @return the consistency ratio. Should be less than 10%
     */
    public double getConsistencyRatio() {
        return (getConsistencyIndex() / RI[nrAlternatives]) * 100.0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i=0; i<nrAlternatives; i++) {
            sb.append(mtx.getRowVector(i) + "\n");
        }
        return sb.toString();
    }

    public static double[] populateComparison(final AHP ahp) {
        final double compArray[] = ahp.getPairwiseComparisonArray();

        // Set the pairwise comparison values
        compArray[0] = 3.0;         //Interdependence compared to Response Time         = 4.0
        compArray[1] = 4.0;         //Interdependence compared to Unit Test Coverage    = 3.0
        compArray[2] = 5.0;         //Interdependence compared to Technical Debt        = 7.0
        compArray[3] = 2.0;         //Interdependence compared to Comp. Resource Usage  = 0.3333333333333333
        compArray[4] = 1.0 / 4;     //Interdependence compared to Availability= 0.0

        compArray[5] = 3.0;         //Response Time compared to Unit Test Coverage      = 3.0
        compArray[6] = 4.0;         //Response Time compared to Technical Debt          = 5.0
        compArray[7] = 2.0;         //Response Time compared to Comp. Resource Usage    = 2.0
        compArray[8] = 1.0 / 6;     //Response Time compared to Availability= 0.0

        compArray[9] = 1.0 / 2;     //Unit Test Coverage compared to Technical Debt     = 4.0
        compArray[10] = 1.0 / 5;    //Unit Test Coverage compared to Comp. Resource Usage= 6.0
        compArray[11] = 1.0 / 7;    //Unit Test Coverage compared to Availability= 0.0

        compArray[12] = 1.0 / 5;     //Technical Debt compared to Comp. Resource Usage   = 5.0
        compArray[13] = 1.0 / 8;    //Technical Debt compared to Availability= 0.0

        compArray[14] = 1.0 / 5;    //Comp. Resource Usage compared to Availability= 0.0
        return compArray;
    }

}
