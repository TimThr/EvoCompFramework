package ECTemplate;

/**
 * Created by yj910929 on 13/11/2017.
 */
abstract public class InitBase<T> {

    /**
     * CreateIndividual
     * @param example An example population member that has all required additional parameters set
     *
     * @return A single population member that has been created using whatever method
     *
     */
    abstract public PopBase<T> CreateIndividual(PopBase<T> example);

    /**
     * CreatePopMems
     * @param numToCreate the number of population members you wish to create
     * @param example An example population member that has all required additional parameters set
     *
     * @return returns the desired number of created population members
     */
    abstract public PopBase<T>[] CreatePopMems(int numToCreate, PopBase<T> example);

}
