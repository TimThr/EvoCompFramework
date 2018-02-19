package ExampleEA;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * Created by yj910929 on 20/11/2017.
 */
public class TestMain extends Application{


    Canvas drawCanvas;


    public static void main(String[] args){

        launch(args);

    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        Double[] min = {0.0,0.0};
        Double[] max = {360.0,360.0};
        Double[] initial = {180.0, 180.0};

        // Create example that contains the min/max for the problem
        FunctionPop example = new FunctionPop(2,initial,min,max);

        //create FunctionGA Test
        TestEA GA = new TestEA(0.1f, 150,
                new FunctionInit(),
                new UniformMutation(0.05f),
                new FunctionCross(),
                new TournamentSelection(),
                new GreedySelection(),
                new FunctionEval(),
                example);

        //set GA to debug output
        GA.parTesting = true;

        //Set up screen to draw too
        primaryStage.setTitle("Evolutionary Algorithm Example");
        drawCanvas = new Canvas(800,800);
        StackPane root = new StackPane();
        root.getChildren().add(drawCanvas);

        primaryStage.setScene(new Scene(root,800,800));

        //handle click
        drawCanvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                //convert java graphic coordinates to cartesian
                float cx = (float)e.getX();
                float cy = (float)e.getY();
                GA.setTarget(cx,cy);

                //get the best
                FunctionPop newBest = (FunctionPop) GA.RunEA();

                //Get Graphics context and set beginning of path to the origin
                GraphicsContext gc = drawCanvas.getGraphicsContext2D();
                gc.clearRect(0,0,800,800);

                gc.beginPath();
                gc.moveTo(400,400);
                //get the coordinate of the first joint
                double x1 = 400+(200*Math.cos(Math.toRadians(newBest.genes[0].doubleValue())));
                double y1 = 400+(200*Math.sin(Math.toRadians(newBest.genes[0].doubleValue())));
                gc.lineTo(x1,y1);

                gc.stroke();

                //then the second joint
                double xp = (x1+(200*Math.cos(Math.toRadians(newBest.genes[1].doubleValue()))));
                double yp = (y1+(200*Math.sin(Math.toRadians(newBest.genes[1].doubleValue()))));
                gc.lineTo(xp,yp);

                gc.stroke();

            }
        });


        primaryStage.show();

    }
}
