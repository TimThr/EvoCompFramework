package ExampleEA;

import ECTemplate.MutateBase;
import ECTemplate.PopBase;

import java.util.Random;

/**
 * Created by yj910929 on 16/11/2017.
 * UniformMutation for the FunctionPop class - will fail if any other class passed to it
 */
public class UniformMutation extends MutateBase<Double> {

    //mutation chance should be between 0 and 1
    float mutationChance;
    Random numGen = new Random(System.currentTimeMillis());

    /**
     * Default Constructor
     * Sets the mutation chance to 0.5 by default
     */
    public UniformMutation(){this.mutationChance=0.5f;}

    /**
     * Constructor
     * @param mChance - the desired mutation chance which should be a float between 0 and 1
     */
    public UniformMutation(float mChance){this.mutationChance=mChance;}

    /**
     * MutatePopMem
     * @param member - the population member to mutate
     * @return A population member who has possibly been mutated
     */
    @Override
    public PopBase<Double> MutatePopMem(PopBase<Double> member) {
        //assert that the population memebr passed in is of type functionpop
        assert member.getClass() == FunctionPop.class;
        //perform uniform mutation and return
        return DoUniformMutation((FunctionPop)member);
    }

    /**
     * UniformMutation
     * @param member - the FunctionPop population member to mutate
     * @return The mutated population member
     *
     * for each gene in the population member this function generates a random number and if that number is less than
     * the set population chance then it replaces that gene with a random value between the upper and lower bounds
     * for that gene.
     */
    private FunctionPop DoUniformMutation(FunctionPop member){

        //loops through all genes of the population member and if they fall within the population chance then
        // sets them to a random value
        for(int geneInd = 0; geneInd<member.getnumGenes(); geneInd++){
            if(numGen.nextFloat()<mutationChance){
                //set to random value scaled to between min and max bounds for this gene
                member.genes[geneInd] = member.geneMin[geneInd] +
                                        ((member.geneMax[geneInd]-member.geneMin[geneInd])*numGen.nextDouble());
            }
        }

        return member;
    }

}
