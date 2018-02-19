package ECTemplate;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.UUID;
import java.util.Vector;

/**
 * Created by yj910929 on 13/11/2017.
 * Contains an abstract template for evolutionary computation
 * formed from components that inherit the correct interfaces.
 */
abstract public class ECTemplate<T> {

    // class varaibales for tracking algorithm
    protected boolean minimize; //true if minimising, false if maximising (i.e. is low or high fitness desired)
    protected float targetFit; //target fitness to reach
    protected int populationSize; //size of the population (either fixed or current)
    protected int generations; //for tracking number of generations the algorithm has run for
    protected Vector<Float> fitnessOverTime; //should store the fitness for each generation - vector used in case generations
                                   // is not fixed
    public PopBase<T> best;

    public boolean parTesting=false;
    private String filePath = "C:\\Temp\\GARuns\\";
    private PrintWriter parWriter;
    private int filecount;

    // ExampleEA components that form a evolutionary algorithm
    // Initialisation
    protected InitBase<T> initOp;

    // Mutation
    protected MutateBase<T> mutateOp;

    // Crossover
    protected XoverBase<T> crossOp;

    // Parent Selection
    protected SelectBase<T> parentSelOp;

    // Evaluation
    protected EvaluateBase<T> evalOp;

    // Generation Selection
    protected SelectBase<T> genSelOp;

    // Population - Population is stored as a vector in case it should be resized
    protected Vector<PopBase<T>> population;

    /**
     * Default Constructor
     *
     */
    public ECTemplate(){

        //init vectors
        this.population = new Vector<PopBase<T>>(0);
        this.fitnessOverTime = new Vector<Float>(0);
    }

    public ECTemplate(boolean minmax,
                      int initPopSize,
                      InitBase<T> initialisation,
                      MutateBase<T> mutation,
                      XoverBase<T> crossover,
                      SelectBase<T> parent,
                      SelectBase<T> generation,
                      EvaluateBase<T> evaluation,
                      PopBase<T> seed){
        //Set the input values
        this.minimize = minmax;
        this.populationSize = initPopSize;
        this.initOp = initialisation;
        this.mutateOp = mutation;
        this.crossOp = crossover;
        this.parentSelOp = parent;
        this.genSelOp = generation;
        this.evalOp = evaluation;
        this.best = seed;

        //init vectors
        this.population = new Vector<PopBase<T>>(0);
        this.fitnessOverTime = new Vector<Float>(0);

        //init file count
        filecount=1;
    }

    /**
     * RunEA
     * @return the best population member having run the algorithm
     * Run The Algorithm
     */
    public PopBase<T> RunEA(){

        //if we ware testing we want to output results so we should open the file
        if(parTesting){
            try {
                String filename = this.toString()+ (this.filecount++) +".txt";
                parWriter = new PrintWriter(filePath+filename);
            }catch(IOException e){
                System.out.println(e.toString());
            }
        }


        //Temporary Arrays
        PopBase<T>[] tmpPop = new PopBase[this.populationSize];
        PopBase<T>[] tmpSelect = new PopBase[this.populationSize*2];

        //Initialise population
        population.clear();//ensure empty
        population.addAll(Arrays.asList(initOp.CreatePopMems(this.populationSize,this.best)));

        //Evaluate the initial population
        for(PopBase<T> popM: population )
            popM.setFitness(evalOp.calculateFitness(popM));


        //Main Evolutionary Loop
        boolean finished = false; //Sentinel for while loop
        Vector<PopBase<T>> Children = new Vector<PopBase<T>>(); //temp pop for storing children
        this.generations =0;//set generations back to 0
        while(!finished){

            //Perform mutation
            for (PopBase<T> popM:population) {
                popM = mutateOp.MutatePopMem(popM); //mutate
                popM.setFitness(evalOp.calculateFitness(popM)); //evaluate
            }

            //Clear Children from old gen and generate new ones
            Children.clear();

            tmpPop = population.toArray(tmpPop);
            while(Children.size()<population.size()) {
                //perform crossover on selected parents and add to children
                Children.add(this.crossOp.performCross(
                        parentSelOp.selectFromPop(tmpPop),
                        parentSelOp.selectFromPop(tmpPop)));
                //Evaluate Fitness

            }

            //Evaluate children
            for(PopBase<T> childM: Children )
                childM.setFitness(evalOp.calculateFitness(childM));

            //Perform selection for next generation
            //temporarily add all pop to children
            Children.addAll(population);
            tmpSelect = Children.toArray(tmpSelect);
            population.clear();//clear pop ready for next gen
            population.addAll(Arrays.asList(
                                        genSelOp.selectNumberFromPop(
                                                tmpSelect,
                                                populationSize)));

            //make sure to find the best for this gen
            SetBest();

            //increment generations
            this.generations++;

            //check for terminal condition
            finished = TerminalCondition();

            if(parTesting) {
                if (generations == 1) {
                    System.out.println("Generation:\tFitness:\tGene1:\tGene2:");
                    parWriter.println("Generation:\tFitness:\tGene1:\tGene2:");

                } else if ((generations % 50) == 0) {
                    System.out.println(this.generations + "\t" +
                            this.best.getFitness() + "\t" +
                            this.best.genes[0] + "\t" + this.best.genes[1]);
                    parWriter.println(this.generations + "\t" +
                            this.best.getFitness() + "\t" +
                            this.best.genes[0] + "\t" + this.best.genes[1]);
                }
            }

        }

        //close the file
        if(parWriter!=null) {
            parWriter.close();
        }
        //check that best really is before returning
        SetBest();
        return this.best;
    }




    /**
     * SetBest
     * When run this function loops through all population members
     * and sets this.best to the one with the highest fitness
     */
    protected void SetBest(){

        if(this.generations<=1){
            this.best = population.firstElement();
        }

        for (PopBase<T> curM:population) {
            if(this.best.compareTo(curM)==1)
                this.best = curM;
        }

    }

    /**
     * terminalCondition
     * @return true if we should stop evolving, or false if we should run for another generation
     */
    abstract protected boolean TerminalCondition();


    /**
     *
     */

}
