package ExampleEA;

import ECTemplate.EvaluateBase;
import ECTemplate.PopBase;

/**
 * Created by yj910929 on 16/11/2017.
 */
public class FunctionEval extends EvaluateBase<Double>{

    //arbitrarily setting the target here for now
     public double[] target = {150,000};

    /**
     * calculateFitness
     * @param popMem the population member we are assessing
     * @return fitness
     *
     *
     * Calls the test function and returns the fitness
     */
    public float calculateFitness(PopBase<Double> popMem){
        //assertion that we actually are dealing with a FunctionPopClass
        assert popMem.getClass() == FunctionPop.class;
        //return fitness
        return (float)testFunction((FunctionPop)popMem);
    }

    /**
     * testfunction
     * @param mem the population member to evaluate
     *
     * Our test function is the forward kinematics of a
     * robot arm simulation (in 2d) with 2 joints that
     * have 360deg of freedom and the length of each
     * joint is 100 so
     * par1 - joint 1 angle - min:0 max:360
     * par2 - joint 2 angle - min:0 max:360
     */
    public double testFunction(FunctionPop mem){
        //get pop member genes
        Double[] pars = mem.genes;
        //temp vars for calculation of error
        double x1, y1, xp, yp, xDif, yDif, err;

        //calc intermediate and end point locations
        x1 = 400+ 200*Math.cos(Math.toRadians(pars[0]));
        y1 = 400+ 200*Math.sin(Math.toRadians(pars[0]));
        xp = x1+(200*Math.cos(Math.toRadians(pars[1])));
        yp = y1+(200*Math.sin(Math.toRadians(pars[1])));

        //calculate distance to target
        xDif = target[0] - xp;
        yDif = target[1] - yp;

        //Calculate error as squared euclidean distance from target and return
        err = Math.pow(xDif,2)+Math.pow(yDif,2);
        return err;
    }

}
