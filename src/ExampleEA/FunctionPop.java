package ExampleEA;

import ECTemplate.PopBase;

/**
 * Created by yj910929 on 16/11/2017.
 */
public class FunctionPop extends PopBase<Double>{

    //arrays for storing min and max possible values for each gene individually
    public Double[] geneMin;
    public Double[] geneMax;

    public FunctionPop(int memberlength, Double[] startGenes, Double[] gMin, Double[] gMax){
        //call super class constructor
        super(memberlength, startGenes);

        //set min/max
        this.geneMin = gMin;
        this.geneMax = gMax;
    }



}
