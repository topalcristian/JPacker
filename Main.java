package sample;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    private static final float WIDTH = 1400;
    private static final float HEIGHT = 1000;
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    //Field initialization
    public static int width = 5;
    public static int height = 8;
    public static int depth = 33;
    public static int[][][] field = new int[height][width][depth];
    public static int pentID = 0;
    public static int mutation = 0;
    public static int locH = 0;
    public static int locW = 0;
    public static int locD = 0;
    public static int[] value = {3,4,5};
    public static int sPentID = 0;
    public static int sMutation = 0;
    public static int tournamentSize = 10;

    // How many Candidates are from new Population
    public static int newCandidatesLength = 80;

    // How many Candidates are from the old Population
    public static int oldCandidatesLength = 20;

    // How many
    public static int N1[] = {0,0,0};
    public static int Y2[] = {300,300,300};
    public static int Y1[] = new int[3];


    @Override
    public void start(Stage primaryStage) {

        // Initializes population from file
        int popSize = 100;
        Individual[] population = new Individual[popSize];

        // Initializing weights of each individual randomly
        for (int i = 0; i < popSize; i++) {
            double[] weights = new double[3];
            for (int j = 0; j < weights.length; j++) {
                weights[j] = Math.abs((Math.random() - 0.5));
            }


            population[i] = normalize(new Individual(weights));
        }

        // Generates Fitness by playing game
        fitnessGenerator(population);
        int generation = 0;

        while (generation < 10) {

            // Next generation
            generation++;

            // Create New Population out of the old one by crossover / mutation
            population = deleteNLastReplacement(population, mutatePopulation(crossover(population)));

            // Generates Fitness by playing game
            fitnessGenerator(population);


            // Shows the best fitness in each generation
            System.out.println("Generation " + generation + "\n Best Individual Fitness = " + population[0].getFitness());
            for (int i = 0; i < population[0].chromosome.length; i++) {
                System.out.println(population[0].chromosome[i] + " " );
            }
        }

        //Shows the best Inidividual
        System.out.println(playUI(population[0], primaryStage));
        for (int i = 0; i < N1.length; i++) {
            System.out.println(N1[i]);
        }

    }

    //Creates UI for an Individual
    private double playUI(Individual I, Stage primaryStage) {
        double locScore = 0;
        clearField();
        for (locD = 0; locD < field[0][0].length; locD++) {
            for (locH = 0; locH < field.length; locH++) {
                for (locW = 0; locW < field[0].length; locW++) {
                    boolean end = false;
                    end = checkOptimal(I.chromosome);
                    if(!end) {
                        pentID = sPentID;
                        Y1[sPentID]--;
                        mutation = sMutation;
                        sPentID = 0;
                        sMutation = 0;
                        locScore += value[pentID];
                        addPiece();
                        drawUI(primaryStage);
                        /*try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    }
                }
            }
        }
        return (double)locScore  - calcHoles()/1000 ;
    }

    private static double calcHoles() {

        boolean check = false;
        double holes = 0;
        for (int k = 0; k < field[0][0].length; k++) {
            for (int j = 0; j < field[0].length; j++) {
                for (int i = 0; i < field.length; i++) {
                    if (field[i][j][k] != -1) {
                        check = true;
                    }
                    if (field[i][j][k] == -1 && check)
                        holes++;

                }
                check = false;
            }
        }

        //Holes 2
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                for (int k = 0; k < field[0][0].length; k++) {

                    if (field[i][j][k] != -1) {
                        check = true;
                    }
                    if (field[i][j][k] == -1 && check)
                        holes++;

                }
                check = false;
            }
        }
        return holes;
    }


    // Combines part of old population with the new one
    private static Individual[] deleteNLastReplacement(Individual[] candidates, Individual[] newCandidates) {
        for (int i = 0; i < newCandidates.length; i++) {
            candidates[oldCandidatesLength + i] = newCandidates[i];
        }
        HeapSort.sort(candidates);
        return candidates;
    }

    // TODO: fitness change
    // Generates fitness and then Heapsorts
    private static void fitnessGenerator(Individual[] individual) {

        for (int i = 0; i < individual.length; i++) {
            individual[i].setFitness(play(individual[i]));
        }
        HeapSort.sort(individual);

    }

    // Tries to fill as much of the box as possible
    private static double play(Individual I) {
        double locScore = 0;
        clearField();
        for (locD = 0; locD < field[0][0].length; locD++) {
            for (locH = 0; locH < field.length; locH++) {
                for (locW = 0; locW < field[0].length; locW++) {
                    boolean end = checkOptimal(I.chromosome);
                    if(!end) {
                        pentID = sPentID;
                        Y1[sPentID]--;
                        mutation = sMutation;
                        sPentID = 0;
                        sMutation = 0;
                        locScore += value[pentID];
                        addPiece();
                    }
                }
            }
        }
        return (double)locScore - calcHoles()/1000;
    }

    private static double emptySpaces() {
        double empty = 0;
        for (int ii = 0; ii < field.length; ii++) {
            for (int jj = 0; jj < field[0].length; jj++) {
                for (int nn = 0; nn < field[0][0].length; nn++) {
                    if (field[ii][jj][nn] == -1)
                        empty++;
                }
            }
        }

        return empty;
    }

    // Searches for the best box in each position
    private static boolean checkOptimal(double[] weight) {
        double score1 = 0;
        boolean first = true;
        for (pentID = 0; pentID < Pentominoes.data.length; pentID++) {
            for (mutation = 0; mutation < Pentominoes.data[pentID].length; mutation++) {
                if(fitInMove() && Y1[pentID]>0){
                    if (first){
                        score1 = value[pentID] * weight[0] - calculateHoles(pentID, mutation) * weight[1] + calculateHeight(pentID, mutation)* weight[2];
                        first = false;
                        sPentID = pentID;
                        sMutation = mutation;
                    } else{
                        double score2 = value[pentID] * weight[0] - calculateHoles(pentID, mutation) * weight[1] + calculateHeight(pentID, mutation)* weight[2];
                        if (score1 <= score2) {
                            score1 = score2;
                            sPentID = pentID;
                            sMutation = mutation;
                        }
                    }
                }
            }
        }
        return first;
    }

    // Weighed Variable used to determine the volume occupied per value
    private static int occupiedSpace(int PentId, int Mutation) {

        int [][][] fieldCalc = new int[field.length][field[0].length][field[0][0].length];
        for (int ii = 0; ii < field.length; ii++) {
            for (int jj = 0; jj < field[0].length; jj++) {
                for (int nn = 0; nn < field[0][0].length; nn++) {
                    fieldCalc[ii][jj][nn] = field[ii][jj][nn];
                }
            }
        }

        fieldCalc = addPiece(fieldCalc, PentId, Mutation);

        int volume = 0;
        for (int k = 0; k < fieldCalc[0][0].length; k++) {
            for (int j = 0; j < fieldCalc[0].length; j++) {
                for (int i = 0; i < fieldCalc.length; i++) {
                    if (fieldCalc[i][j][k] == 1) volume++;
                }
            }
        }
        return volume;
    }

    // Weighed variable used to determine the number of empty spaces left after placing a figure
    private static int calculateHoles(int PentId, int Mutation) {

        int [][][] fieldCalc = new int[field.length][field[0].length][field[0][0].length];
        for (int ii = 0; ii < field.length; ii++) {
            for (int jj = 0; jj < field[0].length; jj++) {
                for (int nn = 0; nn < field[0][0].length; nn++) {
                    fieldCalc[ii][jj][nn] = field[ii][jj][nn];
                }
            }
        }

        fieldCalc = addPiece(fieldCalc, PentId, Mutation);

        boolean check = false;
        int holes = 0;
        for (int k = 0; k < fieldCalc[0][0].length; k++) {
            for (int j = 0; j < fieldCalc[0].length; j++) {
                for (int i = 0; i < fieldCalc.length; i++) {
                    if (fieldCalc[i][j][k] != -1) {
                        check = true;
                    }
                    if (fieldCalc[i][j][k] == -1 && check)
                        holes++;

                }
                check = false;
            }
        }

        //Holes 2
        for (int i = 0; i < fieldCalc.length; i++) {
            for (int j = 0; j < fieldCalc[0].length; j++) {
                for (int k = 0; k < fieldCalc[0][0].length; k++) {

                    if (fieldCalc[i][j][k] != -1) {
                        check = true;
                    }
                    if (fieldCalc[i][j][k] == -1 && check)
                        holes++;

                }
                check = false;
            }
        }




        return holes;
    }

    private static int calculateHeight(int PentId, int Mutation) {

        int [][][] fieldCalc = new int[field.length][field[0].length][field[0][0].length];
        for (int ii = 0; ii < field.length; ii++) {
            for (int jj = 0; jj < field[0].length; jj++) {
                for (int nn = 0; nn < field[0][0].length; nn++) {
                    fieldCalc[ii][jj][nn] = field[ii][jj][nn];
                }
            }
        }

        fieldCalc = addPiece(fieldCalc, PentId, Mutation);

        int height = 0;
        for (int k = 0; k < fieldCalc.length; k++) {
            for (int j = 0; j < fieldCalc[0].length; j++) {
                for (int i = fieldCalc[0][0].length-1; i > 0; i--) {
                    if (fieldCalc[k][j][i] == -1) {
                        height++;
                    }else{
                        i=0;
                    }

                }
            }
        }
        return height;
    }


    // Adds the piece to the testing field
    private static int[][][] addPiece(int[][][] fieldCalc, int pentId, int Mutation) {

        int [][][] piece = Pentominoes.data[pentId][Mutation];
        for (int i = 0; i < piece.length; i++) // loop over x position of pentomino
        {
            for (int j = 0; j < piece[0].length; j++) // loop over y position of pentomino
            {
                for (int n = 0; n < piece[0][0].length; n++) {
                    if (piece[i][j][n] == 0) {
                        continue;
                    }
                    int cH = i + locH;
                    int cW = j + locW;
                    int cD = n + locD;
                    fieldCalc[cH][cW][cD] = pentId;
                }
            }
        }
        return fieldCalc;
    }

    // Draws the general UI of the box
    private void drawUI(Stage primaryStage) {

        Box box1 = prepareBox();
        box1.setTranslateX(0);
        box1.setTranslateY(0);
        box1.setTranslateZ(0);
        box1.setDrawMode(DrawMode.LINE);
        SmartGroup group = new SmartGroup();
        group.getChildren().add(box1);

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                for (int n = 0; n < field[0][0].length; n++) {
                    if(field[i][j][n] == 0) {
                        Box boxA = prepareBoxA();
                        boxA.setTranslateX(boxA.getWidth()/2-box1.getWidth()/2+boxA.getWidth()*j);
                        boxA.setTranslateY(boxA.getHeight()/2-box1.getHeight()/2+boxA.getHeight()*i);
                        boxA.setTranslateZ(boxA.getDepth()/2-box1.getDepth()/2+boxA.getDepth()*n);
                        group.getChildren().add(boxA);
                    }
                    if(field[i][j][n] == 1) {
                        Box boxB = prepareBoxB();
                        boxB.setTranslateX(boxB.getWidth()/2-box1.getWidth()/2+boxB.getWidth()*j);
                        boxB.setTranslateY(boxB.getHeight()/2-box1.getHeight()/2+boxB.getHeight()*i);
                        boxB.setTranslateZ(boxB.getDepth()/2-box1.getDepth()/2+boxB.getDepth()*n);
                        group.getChildren().add(boxB);
                    }
                    if(field[i][j][n] == 2) {
                        Box boxC = prepareBoxC();
                        boxC.setTranslateX(boxC.getWidth()/2-box1.getWidth()/2+boxC.getWidth()*j);
                        boxC.setTranslateY(boxC.getHeight()/2-box1.getHeight()/2+boxC.getHeight()*i);
                        boxC.setTranslateZ(boxC.getDepth()/2-box1.getDepth()/2+boxC.getDepth()*n);
                        group.getChildren().add(boxC);
                    }

                }
            }
        }

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(group, WIDTH, HEIGHT, true);
        scene.setFill(Color.BLANCHEDALMOND);
        scene.setCamera(camera);

        group.translateXProperty().set(WIDTH / 2);
        group.translateYProperty().set(HEIGHT / 2);
        group.translateZProperty().set(-1500);

        initMouseControl(group, scene, primaryStage);

        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    camera.setTranslateZ(camera.getTranslateZ()+10);
                    break;
                case S:
                    camera.setTranslateZ(camera.getTranslateZ()-10);
                    break;
                case Q:
                    group.rotateByX(10);
                    break;
                case E:
                    group.rotateByX(-10);
                    break;
                case NUMPAD6:
                    group.rotateByY(10);
                    break;
                case NUMPAD4:
                    group.rotateByY(-10);
                    break;
                case A:
                    camera.setTranslateX(camera.getTranslateX()-10);
                    break;
                case D:
                    camera.setTranslateX(camera.getTranslateX()+10);
                    break;
            }
        });
        primaryStage.setTitle("JPACKER");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Adds the piece to the main board
    private static void addPiece() {
        int [][][] piece = Pentominoes.data[pentID][mutation];
        for (int i = 0; i < piece.length; i++) // loop over x position of pentomino
        {
            for (int j = 0; j < piece[0].length; j++) // loop over y position of pentomino
            {
                for (int n = 0; n < piece[0][0].length; n++) {
                    if (piece[i][j][n] == 0) {
                        continue;
                    }
                    int cH = i + locH;
                    int cW = j + locW;
                    int cD = n + locD;
                    field[cH][cW][cD] = pentID;
                }
            }
        }
        if (pentID == 0) N1[0]++;
        if (pentID == 1) N1[1]++;
        if (pentID == 2) N1[2]++;
    }

    // Empties the field
    private static void clearField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                for (int n = 0; n < field[0][0].length; n++) {
                    field[i][j][n] = -1;
                }
            }
        }

        for (int i = 0; i < Y1.length; i++) {
            Y1[i] = Y2[i];
        }
        N1[0] = 0;
        N1[1] = 0;
        N1[2] = 0;
    }

    // Gives the color Green to the 3rd piece
    private Box prepareBoxC() {
        PhongMaterial materaial = new PhongMaterial(Color.GREENYELLOW);
        Box box = new Box(10, 10, 10);
        box.setMaterial(materaial);
        return box;
    }

    // Gives the color red to the 2nd piece
    private Box prepareBoxB() {
        PhongMaterial materaial = new PhongMaterial(Color.ORANGERED);
        Box box = new Box(10, 10, 10);
        box.setMaterial(materaial);
        return box;
    }

    // Gives the color Blue to the 1st piece
    private Box prepareBoxA() {
        PhongMaterial materaial = new PhongMaterial(Color.LIGHTBLUE);
        Box box = new Box(10, 10, 10);
        box.setMaterial(materaial);
        return box;
    }

    // Initializes the Big container with invisible walls
    private Box prepareBox() {
        PhongMaterial materaial = new PhongMaterial(Color.BEIGE);
        Box box = new Box(50, 80, 330);
        box.setMaterial(materaial);
        return box;
    }

    // Initializes the mouse control
    private void initMouseControl(SmartGroup group, Scene scene, Stage stage) {
        Rotate xRotate;
        Rotate yRotate;
        group.getTransforms().addAll(
                xRotate = new Rotate(0, Rotate.X_AXIS),
                yRotate = new Rotate(0, Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });

        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ() - delta);
        });
    }

    // Launches the code
    public static void main(String[] args) {
        launch(args);
    }

    // Checks if a certain piece fits from the selected location
    public static boolean fitInMove() {
        int[][][] pieceTemp = Pentominoes.data[pentID][mutation];

        for (int i = 0; i < pieceTemp.length; i++) {
            for (int j = 0; j < pieceTemp[0].length; j++) {
                for (int n = 0; n < pieceTemp[0][0].length; n++) {
                    if (pieceTemp[i][j][n] == 0) {
                        continue;
                    }
                    int cH = i + locH;
                    int cW = j + locW;
                    int cD = n + locD;

                    if (cH < 0 || cW < 0 || cD < 0 ||cH >= field.length || cW >= field[0].length|| cD >= field[0][0].length) {
                        return false;
                    }
                    if (field[cH][cW][cD] > -1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Mutates the new population with the Mutation Rate 20%
    private static Individual[] mutatePopulation(Individual[] population) {
        double mutationRate = 0.2;

        Random weightChromosome = new Random(System.currentTimeMillis());

        for (int i = 0; i < population.length; i++) {
            double roll = Math.random();
            if (roll <= mutationRate) {
                population[i].chromosome[weightChromosome.nextInt(3)] += (Math.random() * 0.4 - 0.2);
            }
        }
        fitnessGenerator(population);

        return population;
    }

    // Crossovers the Population via tournament selection
    private static Individual[] crossover(Individual[] parents) {

        Individual[] child = new Individual[newCandidatesLength];

        for (int i = 0; i < child.length; i++) {
            Individual parent1 = selectionMethod(parents)[0];
            Individual parent2 = selectionMethod(parents)[0];

            child[i] = crossoverMethod(parent1, parent2);
        }

        return child;
    }

    // Tournament Selection
    private static Individual[] selectionMethod(Individual[] select) {

        Individual[] tournamentPopulation = new Individual[tournamentSize];

        for (int i = 0; i < tournamentSize; i++) {
            tournamentPopulation[i] = select[(int) (Math.random() * select.length)];
        }
        HeapSort.sort(tournamentPopulation);
        return tournamentPopulation;
    }

    //TODO:
    // Crossover of two parents
    private static Individual crossoverMethod(Individual parent1, Individual parent2) {
        double[] chrom1 = new double[3];
        Individual child = new Individual(chrom1);

        for (int i = 0; i < parent1.getChromosome().length; i++) {
            child.chromosome[i] =  (parent1.chromosome[i] + parent2.chromosome[i])/2;

        }

        return normalize(child);
    }

    // Normalizes the population
    private static Individual normalize(Individual candidate) {
        double norm = Math.sqrt(candidate.chromosome[0] * candidate.chromosome[0] + candidate.chromosome[1] * candidate.chromosome[1]+ candidate.chromosome[2] * candidate.chromosome[2]);
        candidate.chromosome[0] /= norm;
        candidate.chromosome[1] /= norm;
        candidate.chromosome[2] /= norm;
        return candidate;
    }

    // Creates smart group to rotate the figures
    class SmartGroup extends Group {
        Rotate r;
        Transform t = new Rotate();

        void rotateByX(int ang) {
            r = new Rotate(ang, Rotate.X_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }

        void rotateByY(int ang) {
            r = new Rotate(ang, Rotate.Y_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }
    }

}