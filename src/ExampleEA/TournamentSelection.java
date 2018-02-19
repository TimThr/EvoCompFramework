package ExampleEA;

import ECTemplate.SelectBase;
import ECTemplate.PopBase;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by yj910929 on 15/11/2017.
 */
public class TournamentSelection extends SelectBase<Double> {

    //number of population members to compete in each tournament
    private int TournamentSize;

    /**
     * Default constructor
     *
     * Sets tournament size to 5 by default
     */
    public TournamentSelection(){TournamentSize=5;}

    /**
     * Constructor
     * @param tSize
     *
     * constructor that allows you to set the tournament size
     */
    public TournamentSelection(int tSize){
        TournamentSize=tSize;
    }


    /**
     * selectFromPop
     * @param pop - The population memebers you wish to select from (not required to be entire pop)
     * @return selected population member
     *
     * Selects a single population member with tournament selection - See selectNumberFromPop for selection process,
     * as this method calls that one with an argument of 1 for numToSel.
     */
    public PopBase<Double> selectFromPop(PopBase<Double>[] pop){

        //Create return var
        PopBase<Double>[] selected = null;

        //Call other method with selection size of 1
        selected = selectNumberFromPop(pop,1);

        //return selected
        return selected[0];
    }

    /**
     * selectNumberFromPop
     * @param pop - The population memebers you wish to select from (not required to be entire pop)
     * @param numToSel - number of population memebrs to select
     * @return array of length numToSel, containing selected population members
     *
     * Select multiple population members with tournament selection. Randomly selects population members at
     * a number of indexes (equal to the tournamentsize) and then selects the best. Multiple copies of a single
     * population member could potentially be in the same tournament - otherwise we would have to check that
     * the length of pop was greater than our tournament size.
     */
    public PopBase<Double>[] selectNumberFromPop(PopBase<Double>[] pop,int numToSel){

        //create array for return
        PopBase<Double>[] selected = new PopBase[numToSel];

        //local vars for selection process
        int numSelected = 0;
        PopBase<Double>[] competitors = new PopBase[this.TournamentSize];
        PopBase<Double> tmpBest;

        //initialise random number generator with current time as seed
        Random numGen = new Random(System.currentTimeMillis());


        //Loop while we have not selected enough members
        while(numSelected<numToSel){

            //randomly select the required number of competitors
            for(int compInd=0; compInd<this.TournamentSize; compInd++){


                competitors[compInd] = pop[numGen.nextInt(pop.length)];
            }

            //set best to first of competitors
            tmpBest = competitors[0];
            //find actual best
            for(int tournInd =0; tournInd<this.TournamentSize; tournInd++){
                //if competitor is better than current best then set
                //best to that competitor
                if(tmpBest.compareTo(competitors[tournInd])==1){
                    tmpBest=competitors[tournInd];
                }
            }

            //set the selected member and increment numSelected
            selected[numSelected] = tmpBest;
            numSelected++;

        }

        //return selected array
        return selected;
    }


}
