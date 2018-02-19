package ExampleEA;

import ECTemplate.*;


/**
 * Created by yj910929 on 16/11/2017.
 */
public class TestEA extends ECTemplate<Double> {


    public TestEA(float fitT,
                  int initPopSize,
                  InitBase<Double> initialisation,
                  MutateBase<Double> mutation,
                  XoverBase crossover,
                  SelectBase<Double>  parent,
                  SelectBase<Double> generation,
                  FunctionEval evaluation,
                  FunctionPop seed){
        //initialise super class variables
        super(true,
                initPopSize,
                initialisation,
                mutation,
                crossover,
                parent,
                generation,
                evaluation,
                seed);
        //add target fitness
        this.targetFit = fitT;
    }


    @Override
    protected boolean TerminalCondition(){

        boolean terminate = false;

        //check if value is within acceptable range
        if((this.best.getFitness() < this.targetFit) && (this.minimize = true)){
            terminate=true;
        }else if((this.best.getFitness() > this.targetFit) && (this.minimize = false)){
            terminate = true;
        }

        //check if we reach a large number of generations
        //Just so it will not run forever if it never finds a good enough solution
        //or if it is impossible to find the desired solution
        if(this.generations >= 500000){
            terminate = true;
        }

        //return termination condition
        return terminate;
    }


    public void setTarget(float x, float y){
        ((FunctionEval)this.evalOp).target[0]=x;
        ((FunctionEval)this.evalOp).target[1]=y;
    }

    @Override
    public String toString(){
        String self ="";

        //return string of important operators
        self= this.mutateOp.getClass().toString()+"_"+
                this.crossOp.getClass().toString()+"_"+
                this.parentSelOp.getClass().toString()+"_"+
                this.genSelOp.getClass().toString();


        return self;
    }

}
