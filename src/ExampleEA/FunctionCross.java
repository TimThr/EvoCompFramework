package ExampleEA;

import ECTemplate.PopBase;
import ECTemplate.XoverBase;

import java.util.Random;

/**
 * Created by yj910929 on 16/11/2017.
 */
public class FunctionCross extends XoverBase<Double> {

    Random numGen = new Random(System.currentTimeMillis());

    /**
     * performCross
     * @param parent1 The first selected population member - should be functionpop class
     * @param parent2 The second selected population member - should be functionpop class
     * @return A 'child' population member
     *
     *
     */
    @Override
    public PopBase<Double> performCross(PopBase<Double> parent1, PopBase<Double> parent2){
        //Assert popbase classes
        assert parent1.getClass() == FunctionPop.class;
        assert parent2.getClass() == FunctionPop.class;

        //return the crossed over population member
        return UniformCrossover((FunctionPop)parent1, (FunctionPop)parent2);
    }


    /**
     * UniformCrossover
     * @param p1 first parent
     * @param p2 second parent
     * @return The child with combined genes
     *
     * For each possible gene will either take the gene from the first or second parent with a 50/50 chance and
     * then returns a new population member with the selected genes and all other parameters copied from the first parent
     */
    private FunctionPop UniformCrossover(FunctionPop p1, FunctionPop p2){
        int numGenes = p1.getnumGenes();
        Double[] newGenes = new Double[numGenes];

        //loop through the genes and take gene from one parent or the other based with a 50/50 chance
        for(int gInd=0; gInd<numGenes; gInd++){

            if(this.numGen.nextDouble()>0.5){
                newGenes[gInd] = p1.genes[gInd];
            }else{
                newGenes[gInd] = p2.genes[gInd];
            }
        }

        return new FunctionPop(numGenes,newGenes,p1.geneMin,p1.geneMax);
    }

}
