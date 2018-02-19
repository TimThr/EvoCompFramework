package ECTemplate;

/**
 * Created by yj910929 on 13/11/2017.
 */
abstract public class XoverBase<T> {



    /**
     *  performCross
     *  performs a crossover operation on two parents and returns a single population member
     */
    abstract public PopBase<T> performCross(PopBase<T> parent1, PopBase<T> parent2);

}
