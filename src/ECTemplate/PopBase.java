package ECTemplate;

/**
 * Created by yj910929 on 13/11/2017.
 */
public class PopBase <T> implements Comparable<PopBase<T>>{

    //Variables to be stored in each population member
    private int numGenes;
    private float fitness;
    public T[] genes;

    public PopBase(){
        this.genes = null;
        this.fitness = 0;
        this.numGenes = 0;
    }

    public PopBase(int memberlength, T[] startGenes){
        this.numGenes = memberlength;
        this.genes = startGenes;
        this.fitness=0;
    }

    //Get Functions
    public float getFitness(){
        return this.fitness;
    }
    public int getnumGenes(){return this.numGenes;}

    //Set Functions
    public void setFitness(float newFit){
        this.fitness = newFit;
    }

    //Comparable method so that pop members are sortable
    @Override
    public int compareTo(PopBase<T> o) {
        int res = 0;
        if(this.fitness > o.getFitness()){
            res = 1;
        }else if(this.fitness < o.getFitness()){
            res = -1;
        }
        return res;
    }

}
