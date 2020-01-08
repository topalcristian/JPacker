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


    @Override
    public void start(Stage primaryStage) {

        clearField();
        nextPiece();
        for (locH = 0; locH < field.length; locH++) {
            for (locW = 0; locW < field[0].length; locW++) {
                for (locD = 0; locD < field[0][0].length; locD++) {
                    if (fitInMove()){
                        addPiece();
                        nextPiece();
                    }
                }
            }
        }


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
                    }
                    if(field[i][j][n] == 1) {
                        Box boxB = prepareBoxB();
                        boxB.setTranslateX(boxB.getWidth()/2-box1.getWidth()/2+boxB.getWidth()*j);
                        boxB.setTranslateY(boxB.getHeight()/2-box1.getHeight()/2+boxB.getHeight()*i);
                        boxB.setTranslateZ(boxB.getDepth()/2-box1.getDepth()/2+boxB.getDepth()*n);
                    }
                    if(field[i][j][n] == 2) {
                        Box boxC = prepareBoxC();
                        boxC.setTranslateX(boxC.getWidth()/2-box1.getWidth()/2+boxC.getWidth()*j);
                        boxC.setTranslateY(boxC.getHeight()/2-box1.getHeight()/2+boxC.getHeight()*i);
                        boxC.setTranslateZ(boxC.getDepth()/2-box1.getDepth()/2+boxC.getDepth()*n);
                    }

                }
            }
        }

        Box box2 = prepareBoxA();
        Box box3 = prepareBoxB();

        box2.setTranslateX(box2.getWidth()/2-box1.getWidth()/2);
        box2.setTranslateY(box2.getHeight()/2-box1.getHeight()/2);
        box2.setTranslateZ(box2.getDepth()/2-box1.getDepth()/2);
        box3.setTranslateX(box3.getWidth()/2-box1.getWidth()/2+box2.getWidth());
        box3.setTranslateY(box3.getHeight()/2-box1.getHeight()/2);
        box3.setTranslateZ(box3.getDepth()/2-box1.getDepth()/2);


        group.getChildren().add(box2);
        group.getChildren().add(box3);

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(group, WIDTH, HEIGHT, true);
        scene.setFill(Color.SILVER);
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

    private void addPiece() {
        int [][][] piece = Boxes.data[pentID][mutation];
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
    }

    private void nextPiece() {
        Random r = new Random();
        pentID = r.nextInt(Boxes.data.length);
        mutation = r.nextInt(Boxes.data[0].length);
    }

    private void clearField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                for (int n = 0; n < field[0][0].length; n++) {
                     field[i][j][n] = -1;
                }
            }
        }
    }

    private Box prepareBoxC() {
        PhongMaterial materaial = new PhongMaterial(Color.GREEN);
        Box box = new Box(10, 10, 10);
        box.setMaterial(materaial);
        return box;
    }

    private Box prepareBoxB() {
        PhongMaterial materaial = new PhongMaterial(Color.RED);
        Box box = new Box(10, 10, 10);
        box.setMaterial(materaial);
        return box;
    }

    private Box prepareBoxA() {
        PhongMaterial materaial = new PhongMaterial(Color.BLUE);
        Box box = new Box(10, 10, 10);
        box.setMaterial(materaial);
        return box;
    }

    private Box prepareBox() {
        PhongMaterial materaial = new PhongMaterial(Color.BEIGE);
        Box box = new Box(50, 80, 330);
        box.setMaterial(materaial);
        return box;
    }

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

    public static void main(String[] args) {
        launch(args);
    }

    public static boolean fitInMove() {
        int[][][] pieceTemp = Boxes.data[pentID][mutation];

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