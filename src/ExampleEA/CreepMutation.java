package ExampleEA;

import ECTemplate.MutateBase;
import ECTemplate.PopBase;

import java.util.Random;

/**
 * Created by yj910929 on 29/01/2018.
 */
public class CreepMutation extends MutateBase<Double> {

    //mutation chance should be between 0 and 1
    private float mutationChance;
    private double mutationAmount;
    Random numGen = new Random(System.currentTimeMillis());

    /**
     * Default Constructor
     * Sets the mutation chance to 0.5 by default
     */
    public CreepMutation(){this.mutationChance=0.5f;this.mutationAmount=20;}

    /**
     * Constructor
     * @param mChance - the desired mutation chance which should be a float between 0 and 1
     */
    public CreepMutation(float mChance, float mAmount){this.mutationChance=mChance;this.mutationAmount = mAmount;}

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
        return DoCreepMutation((FunctionPop)member);
    }

    /**
     * DoCreepMutation
     * @param member - the FunctionPop population member to mutate
     * @return The mutated population member
     *
     * for each gene in the population member this function generates a random number and if that number is less than
     * the set population chance then it adds a random amount and ensures it remains between the upper and lower bounds
     * for that gene.
     */
    private FunctionPop DoCreepMutation(FunctionPop member){

        //loops through all genes of the population member and if they fall within the population chance then
        // adds or removes a small value to them
        for(int geneInd = 0; geneInd<member.getnumGenes(); geneInd++){
            if(numGen.nextFloat()<mutationChance){

                //use box-Muller transfor. to get a random standard distributed variables. Scale random
                //values to our desired range of mutation
                double uni1 = numGen.nextDouble();
                double uni2 = numGen.nextDouble();
                double std1 = Math.sqrt(-2*Math.log(uni1))*Math.cos(2*Math.PI*uni2)*(mutationAmount/2);

                //we want it to be able to move in either direction so rather than 0 -> mutation amount
                // it should be -(mutationAmount/2) -> (mutationAmount/2) so we will take away from our
                //generated value
                std1-=(mutationAmount/2);

                //add to gene
                member.genes[geneInd] += std1;

                //check its still within bounds
                //if greater than max
                if(member.genes[geneInd]>member.geneMax[geneInd]){
                    //wrap around from max back to the beginning
                    member.genes[geneInd] -= (member.geneMax[geneInd]+member.geneMin[geneInd]);

                //else if smaller than min
                }else if(member.genes[geneInd]<member.geneMin[geneInd]){

                    //wrap around from max
                    member.genes[geneInd] = member.geneMax[geneInd]-Math.abs(member.genes[geneInd]);
                }
            }
        }

        return member;
    }

}
