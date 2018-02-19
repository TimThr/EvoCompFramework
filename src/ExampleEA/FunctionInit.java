package ExampleEA;

import ECTemplate.InitBase;
import ECTemplate.PopBase;

import java.util.Random;

/**
 * Created by yj910929 on 16/11/2017.
 * Random Initialisation for the FunctionPop class
 */
public class FunctionInit extends InitBase<Double> {

    //random number generator
    Random numGen = new Random(System.currentTimeMillis());

    /**
     * CreateIndiviual
     * @param example An example population member that has all required additional parameters set
     *
     * @return A single new population member
     *
     * Calls CreatePopMems with an argument of 1 so essentially a convenience function
     */
    @Override
    public PopBase<Double> CreateIndividual(PopBase<Double> example){
        //make sure that it is the correct class
        assert example.getClass() == FunctionPop.class;
        //create and return the new population member
        return CreatePopMems(1,example)[0];
    }

    /**
     * CreatePopMems
     * @param numToCreate the number of population members you wish to create
     * @param example An example population member that has all required additional parameters set
     *
     * @return an array of new randomly generated population members
     */
    @Override
    public PopBase<Double>[] CreatePopMems(int numToCreate, PopBase<Double> example){

        //make sure that it is the correct class
        assert example.getClass() == FunctionPop.class;
        //init return array
        FunctionPop[] pop = new FunctionPop[numToCreate];

        //create all the new members
        for(int memInd=0; memInd<numToCreate; memInd++){
            pop[memInd] = newMem((FunctionPop)example);
        }

        //Return generated population
        return pop;
    }

    /**
     * newMem
     * @param example An example population member that has all required additional parameters set
     * @return A randomly generated FunctionPop population members
     *
     * The newMem function uses the parameters from the example method to generate random double values for the new
     * population member - so the new member will have the same number of genes and the same min/max bounds
     */
    private FunctionPop newMem(FunctionPop example){
        //init new populaiton Member
        FunctionPop newMem;

        //get number fo genes and
        int numGenes = example.getnumGenes();
        Double[] Genes = new Double[numGenes];

        //loop through and create random values for
        for(int geneInd = 0; geneInd<numGenes; geneInd++){
            //set to random value scaled to between min and max bounds for this gene
            Genes[geneInd] = example.geneMin[geneInd] +
                    ((example.geneMax[geneInd]-example.geneMin[geneInd])*numGen.nextDouble());

        }

        //put together components and return new population member
        newMem = new FunctionPop(numGenes,Genes,example.geneMin,example.geneMax);
        return newMem;
    }
}
