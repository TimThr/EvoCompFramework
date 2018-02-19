package ECTemplate;

/**
 * Created by yj910929 on 13/11/2017.
 */
abstract public class SelectBase<T> {

    /**
     * selectFromPop
     * @param pop - any number of the population array (not required to be the full amount -
     *            or necessarily more than one)
     * Takes a number of population members and returns a single best (from a method determined in the
     * inheriting class when implemented) Must handle taking any number of population members.
     */
    abstract public PopBase<T> selectFromPop(PopBase<T>[] pop);

    /**
     * selectNumberFromPop
     * @param pop - any number of the population array (not required to be the full amount - or necessarily more than one)
     * @param numToSel - number of population members to select
     *
     * As above but returns the a specified number of population members ordered from best to worst (as determined by
     * the implementing method.) Must handle taking any number of population members and the number of population
     * members passed to the method must be greater than the numebr requested to be returned
     *
     */
    abstract public PopBase<T>[] selectNumberFromPop(PopBase<T>[] pop,int numToSel);
}
