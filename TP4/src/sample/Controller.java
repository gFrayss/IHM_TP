package sample;

import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


public class Controller {

    @FXML
    private ToggleGroup tglMenu;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClone;
    @FXML
    private Canvas canDessin;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private RadioButton rdBtnSelect;
    @FXML
    private RadioButton rdBtnEllipse;
    @FXML
    private RadioButton rdBtnRectangle;
    @FXML
    private RadioButton rdBtnLine;


    //private EventHandler tglMenuListener;
    private EventHandler btnDeleteListener;
    private EventHandler btnCloneListener;
    private EventHandler canvasListener;



    ////////////////////////////////
    //Initilisation de l'application
    ////////////////////////////////
    @FXML
    public void initialize() {
        initListener();

        btnDelete.setOnAction(btnDeleteListener);
        btnClone.setOnAction(btnCloneListener);
        canDessin.setOnMouseClicked(canvasListener);

    }


    //////////////////////////////
    //Initialisation des listeners
    //////////////////////////////
    public void initListener() {

        //Listener des RadioButtons
        tglMenu.selectedToggleProperty().addListener(
                (ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) -> {
                    if(tglMenu.getSelectedToggle() != null) {
                        String tmp = tglMenu.getSelectedToggle().getUserData().toString();
                        //System.out.println(tmp);
                        if(rdBtnSelect.isSelected()) {
                            btnDelete.setDisable(false); btnClone.setDisable(false);
                        } else { btnDelete.setDisable(true); btnClone.setDisable(true); }
                        System.out.println(tmp);
                    }
                });


        //Listener du Canvas
        canvasListener = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("---Canvas---");
                GraphicsContext gc = canDessin.getGraphicsContext2D();

                gc.setFill(colorPicker.getValue()); gc.setStroke(colorPicker.getValue());
                Double X = event.getX(); Double Y = event.getY();

                if(rdBtnLine.isSelected()) {
                    gc.setLineWidth(10);
                    gc.strokeLine(X-30, Y, X+30, Y);

                }
                else if(rdBtnRectangle.isSelected()) {
                    gc.fillRoundRect(X-30, Y-20, 60, 40, 0, 0);


                }
                else if (rdBtnEllipse.isSelected()) {
                    gc.fillOval(X-25, Y-25, 55, 45);
                    //MouseClick ms = new MouseClick(X-25, Y-25, 55.0, 45.0);

                }
                else {

                }
            }
        };




        //Listener du Button "Delete"
        btnDeleteListener = new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("Delete");
            }
        };


        //Listener du Button "Clone"
        btnCloneListener = new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("Clone");
            }
        };



    }


    public static class MouseClick {
        private double _x;
        private double _y;
        private double _w;
        private double _h;

        public MouseClick(double x, double y, double w, double h) {
            _x=x;
            _y=y;
            _w=w;
            _h=h;
        }


        public double get_x(){
            return _x;
        }
        public double get_y(){
            return _y;
        }
        public double get_w(){
            return _w;
        }
        public double get_h(){
            return _h;
        }


    }






}




