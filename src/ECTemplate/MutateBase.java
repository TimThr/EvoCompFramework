package ECTemplate;

/**
 * Created by yj910929 on 13/11/2017.
 */
abstract public class MutateBase<T> {

    /**
     * MutatePopMem
     * @param member
     * @return mutate population member (with whatever method you have implemented) - should be the same length as
     * the passed
     */
    abstract public PopBase<T> MutatePopMem(PopBase<T> member);

}
