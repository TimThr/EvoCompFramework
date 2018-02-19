package ECTemplate;

/**
 * Created by yj910929 on 13/11/2017.
 */
abstract public class EvaluateBase<T> {

    /**
     * calculateFitness
     * @param popMember
     * @return The calculated fitness of the population member passed into the method
     */
    abstract public float calculateFitness(PopBase<T> popMember);

}
