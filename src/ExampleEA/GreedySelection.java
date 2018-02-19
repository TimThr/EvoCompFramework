package ExampleEA;

import ECTemplate.ECTemplate;
import ECTemplate.PopBase;
import ECTemplate.SelectBase;

import java.util.Arrays;

/**
 * Created by yj910929 on 21/11/2017.
 * Greedy selection will always return the best population members
 */
public class GreedySelection extends SelectBase<Double> {

    /**
     *
     * @param pop - any number of the population array (not required to be the full amount -
     *            or necessarily more than one)
     * Takes a number of population members and returns a single best (from a method determined in the
     * @return
     */
    @Override
    public PopBase<Double> selectFromPop(PopBase<Double>[] pop) {

        //use other selection with argument of 1 for numebrtoselect
        return selectNumberFromPop(pop,1)[0];

    }


    /**
     *
     * @param pop - any number of the population array (not required to be the full amount - or necessarily more than one)
     * @param numToSel - number of population members to select
     *
     * As above but returns the a specified number of population members ordered from best to worst (as determined by
     * the implementing method.) Must handle taking any number of population members and the number of population
     * @return
     */
    @Override
    public PopBase<Double>[] selectNumberFromPop(PopBase<Double>[] pop, int numToSel) {

        //create array to store selected that we can return
        PopBase<Double>[] selected = new PopBase[numToSel];

        //sort the array
        Arrays.sort(pop);

        //copy the number of elements we need
        selected = Arrays.copyOfRange(pop,0,numToSel);

        return selected;
    }
}
