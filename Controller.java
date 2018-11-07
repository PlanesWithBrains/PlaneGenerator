package sample;


import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;


import javax.swing.*;


public class Controller{

    final Random random = new Random();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button startGENButton;

    @FXML
    private TextArea FieldPathIN;
    @FXML
    private TextArea FieldPathOUT;

    @FXML
    private Spinner<Integer> SpinCountPlaneIN;
    @FXML
    private Spinner<Integer> SpinCountPlaneOUT;

    @FXML
    private TextArea logArea;

    @FXML
    private Spinner<Integer> SpinSTARThourIN;

    @FXML
    private Spinner<Integer> SpinSTARTminIN;

    @FXML
    private Spinner<Integer> SpinSTARTsecIN;

    @FXML
    private Spinner<Integer> SpinDELhourIN;

    @FXML
    private Spinner<Integer> SpinDELminIN;

    @FXML
    private Spinner<Integer> SpinDELsecIN;
    @FXML
    private Spinner<Integer> SpinSTARThourOUT;

    @FXML
    private Spinner<Integer> SpinSTARTminOUT;

    @FXML
    private Spinner<Integer> SpinSTARTsecOUT;

    @FXML
    private Spinner<Integer> SpinDELhourOUT;

    @FXML
    private Spinner<Integer> SpinDELminOUT;

    @FXML
    private Spinner<Integer> SpinDELsecOUT;


    @FXML
    void initialize() {



        //spinners

        //count planes IN
        SpinnerValueFactory<Integer> gradesCount = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2000,50);
        SpinCountPlaneIN.setValueFactory(gradesCount);
        SpinCountPlaneIN.setEditable(true);
        //count planes OUT
        SpinnerValueFactory<Integer> gradesCount1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2000,50);
        SpinCountPlaneOUT.setValueFactory(gradesCount1);
        SpinCountPlaneOUT.setEditable(true);
        //TIME START

        //TIME NOW
        Time NOW = new Time(LocalTime.now().getHour(),LocalTime.now().getMinute(), LocalTime.now().getSecond());

                //VALUE FACTORY
        SpinnerValueFactory<Integer> gradesHour = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23,NOW.getHours());
        SpinnerValueFactory<Integer> gradesMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, NOW.getMinutes());
        SpinnerValueFactory<Integer> gradesSec = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59,NOW.getSeconds());

        SpinnerValueFactory<Integer> gradesHourDEL = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23,0);
        SpinnerValueFactory<Integer> gradesMinDEL = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59,30);
        SpinnerValueFactory<Integer> gradesSecDEL = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59,30);

        SpinSTARThourIN.setValueFactory(gradesHour);
        SpinSTARTminIN.setValueFactory(gradesMin);
        SpinSTARTsecIN.setValueFactory(gradesSec);

        SpinDELhourIN.setValueFactory(gradesHourDEL);
        SpinDELminIN.setValueFactory(gradesMinDEL);
        SpinDELsecIN.setValueFactory(gradesSecDEL);

        SpinSTARThourIN.setEditable(true);
        SpinSTARTminIN.setEditable(true);
        SpinSTARTsecIN.setEditable(true);

        SpinDELhourIN.setEditable(true);
        SpinDELminIN.setEditable(true);
        SpinDELsecIN.setEditable(true);


        //OUT
        SpinnerValueFactory<Integer> gradesHour1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23,NOW.getHours());
        SpinnerValueFactory<Integer> gradesMin1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59,NOW.getMinutes());
        SpinnerValueFactory<Integer> gradesSec1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, NOW.getSeconds());

        SpinnerValueFactory<Integer> gradesHourDEL1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23,0);
        SpinnerValueFactory<Integer> gradesMinDEL1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59,30);
        SpinnerValueFactory<Integer> gradesSecDEL1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59,30);

        SpinSTARThourOUT.setValueFactory(gradesHour1);
        SpinSTARTminOUT.setValueFactory(gradesMin1);
        SpinSTARTsecOUT.setValueFactory(gradesSec1);

        SpinDELhourOUT.setValueFactory(gradesHourDEL1);
        SpinDELminOUT.setValueFactory(gradesMinDEL1);
        SpinDELsecOUT.setValueFactory(gradesSecDEL1);

        SpinSTARThourOUT.setEditable(true);
        SpinSTARTminOUT.setEditable(true);
        SpinSTARTsecOUT.setEditable(true);

        SpinDELhourOUT.setEditable(true);
        SpinDELminOUT.setEditable(true);
        SpinDELsecOUT.setEditable(true);


        startGENButton.setOnAction(event -> {
            System.out.println(FieldPathIN.getText());
            System.out.println(FieldPathOUT.getText());

            int CountFlightsIN = SpinCountPlaneIN.getValue();
            int CountFlightsOUT = SpinCountPlaneOUT.getValue();

            ArrayList<Plane> planes = Plane.getPlanes();
            Flight flightsIN[] = new Flight[CountFlightsIN];
            Flight flightsOUT[] = new Flight[CountFlightsOUT];

            Airline[] airlines = Airline.values();
            Direction[] directions = Direction.values();


            String log = "";
            logArea.setDisable(false);

            //create random buf IN flights
            for (int i = 0; i < CountFlightsIN; i++){
                //temps
                Plane temp = planes.get(random.nextInt(12));
                int flagAirLine = random.nextInt(airlines.length);
                int flagDirect = random.nextInt(directions.length);
                Time bufTime = new Time(SpinSTARThourIN.getValue()+ i*SpinDELhourIN.getValue(),SpinSTARTminIN.getValue()+ i*SpinDELminIN.getValue(),SpinSTARTsecIN.getValue()+ i*SpinDELsecIN.getValue());

                flightsIN[i] = new Flight(temp, airlines[flagAirLine], random.nextInt(9000)+1000, directions[flagDirect], bufTime,true,random.nextInt(210)+50,random.nextInt(2400) +600);

                log += "create IN_#" + temp.name + " " + airlines[flagAirLine].name() + "\n";
                logArea.setText(log);


            }

            //create random temp out flights
            for (int i = 0; i < CountFlightsOUT; i++){
                Plane temp = planes.get(random.nextInt(12));
                int flagAirLine = random.nextInt(airlines.length);
                int flagDirect = random.nextInt(directions.length);

                Time bufTime = new Time(SpinSTARThourOUT.getValue()+ i*SpinDELhourOUT.getValue(),SpinSTARTminOUT.getValue()+ i*SpinDELminOUT.getValue(),SpinSTARTsecOUT.getValue()+ i*SpinDELsecOUT.getValue());

                flightsOUT[i] = new Flight(temp, airlines[flagAirLine], random.nextInt(9000)+1000, directions[flagDirect], bufTime);

                log += "create OUT_#" + temp.name + " " + airlines[flagAirLine].name() + "\n";
                logArea.setText(log);


            }

            try {
                CreateJson(FieldPathIN.getText(), flightsIN);
                CreateJson(FieldPathOUT.getText(), flightsOUT);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    boolean CreateJson(String PATH,Flight[] buf) throws IOException, InterruptedException{
        Gson gson = new Gson();
        try(FileWriter wr = new FileWriter(PATH)) {
            //wr.write(gson.toJson(delay));
            String str = gson.toJson(buf);
            wr.write(str);
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
            logArea.setText(ex.getMessage());
            return false;
        }
        return true;
    }


    public void filechooser(javafx.event.ActionEvent actionEvent) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int chooseDirectiry = fc.showOpenDialog(null);
        if (chooseDirectiry == JFileChooser.APPROVE_OPTION){
            FieldPathIN.setText(fc.getSelectedFile().getAbsolutePath()+"\\testINPUT.json");
        }

    }
    public void filechooserOUT(javafx.event.ActionEvent actionEvent) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int chooseDirectiry = fc.showOpenDialog(null);
        if (chooseDirectiry == JFileChooser.APPROVE_OPTION){
            FieldPathOUT.setText(fc.getSelectedFile().getAbsolutePath()+"\\testOUTPUT.json");
        }

    }
}

