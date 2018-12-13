package sample;


import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.nio.file.DirectoryIteratorException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;


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
    private TableView<TableIN> TableIN;

    @FXML
    private TableColumn<TableIN, String> columnTripIN;

    @FXML
    private TableColumn<TableIN, String> columnPlaneIN;

    @FXML
    private TableColumn<TableIN, String> columnDistance;

    @FXML
    private TableColumn<TableIN, String> columnHight;

    @FXML
    private TableView<TableOUT> TableOUT;

    @FXML
    private TableColumn<TableOUT, String> columnTripOUT;

    @FXML
    private TableColumn<TableOUT, String> columnPlaneOUT;

    @FXML
    private TableColumn<TableOUT, String> columnTime;
    @FXML
    private TableColumn<TableOUT, String> columnDate;


    @FXML
    void initialize() {
        LocalDateTime NOW = LocalDateTime.now();

        initializeSPINNERS(NOW);
        initializeTable();

        FieldPathIN.setText(System.getProperty("user.home")+"\\Documents\\"+"testINPUT.json");
        FieldPathOUT.setText(System.getProperty("user.home")+"\\Documents\\"+"testOUTPUT.json");

        startGENButton.setOnAction(event -> {
            //table buffers
            ObservableList<TableIN> bufIN = FXCollections.observableArrayList();
            ObservableList<TableOUT> bufOUT = FXCollections.observableArrayList();

            createFileInput(Plane.getPlanes(),Airline.values(), Direction.values(), NOW, bufIN);
            createFileOutput(Plane.getPlanes(),Airline.values(), Direction.values(), bufOUT);

            TableIN.setItems(bufIN);
            TableOUT.setItems(bufOUT);
        });
    }

    boolean createJson(String PATH, Flight[] buf) throws IOException, InterruptedException{
        Gson gson = new Gson();
        try(FileWriter wr = new FileWriter(PATH)) {
            //wr.write(gson.toJson(delay));
            String str = gson.toJson(buf);
            wr.write(str);
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }


    public void filechooser(javafx.event.ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));
        //директория по умолчанию
        try {
            File initialDirectory = new File(System.getProperty("user.home") + "\\Documents\\");
            fc.setInitialDirectory(initialDirectory);
            fc.setInitialFileName("testINPUT.json");
        }
        catch (Exception exp){ //если MAC os
            System.out.println((char)27 + "[32m"+exp.getMessage());
        }
        File f = fc.showSaveDialog(null);
        if (f != null)
            System.out.println((char)27 + "[32mWARNING: (#FileChooser) no directory selected"+(char)27+"[30m");
        else
            FieldPathIN.setText(f.getAbsolutePath());
    }
    public void filechooserOUT(javafx.event.ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files", "*.json"));
        //директория по умолчанию
        try {
            File initialDirectory = new File(System.getProperty("user.home") + "\\Documents\\");
            fc.setInitialDirectory(initialDirectory);
            fc.setInitialFileName("testOUTPUT.json");
        }
        catch (Exception exp){ //если MAC os
            System.out.println((char)27 + "[32m"+exp.getMessage());
        }
        File f = fc.showSaveDialog(null);
        if (f != null)
            System.out.println((char)27 + "[32mWARNING: (#FileChooser) no directory selected"+(char)27+"[30m");
        else
            FieldPathOUT.setText(f.getAbsolutePath());
    }

    void initializeSPINNERS(LocalDateTime NOW){
        //spinners

        //count planes IN
        SpinnerValueFactory<Integer> gradesCount = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2000, 50);
        SpinCountPlaneIN.setValueFactory(gradesCount);
        SpinCountPlaneIN.setEditable(true);
        //count planes OUT
        SpinnerValueFactory<Integer> gradesCount1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2000, 50);
        SpinCountPlaneOUT.setValueFactory(gradesCount1);
        SpinCountPlaneOUT.setEditable(true);
        //TIME START

        //TIME NOW

//        //VALUE FACTORY
//        SpinnerValueFactory<Integer> gradesHour = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, NOW.getHour());
//        SpinnerValueFactory<Integer> gradesMin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, NOW.getMinute());
//        SpinnerValueFactory<Integer> gradesSec = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, NOW.getSecond());
//
//        SpinnerValueFactory<Integer> gradesHourDEL = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
//        SpinnerValueFactory<Integer> gradesMinDEL = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 30);
//        SpinnerValueFactory<Integer> gradesSecDEL = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 30);
//
//        SpinSTARThourIN.setValueFactory(gradesHour);
//        SpinSTARTminIN.setValueFactory(gradesMin);
//        SpinSTARTsecIN.setValueFactory(gradesSec);
//
//        SpinDELhourIN.setValueFactory(gradesHourDEL);
//        SpinDELminIN.setValueFactory(gradesMinDEL);
//        SpinDELsecIN.setValueFactory(gradesSecDEL);
//
//        SpinSTARThourIN.setEditable(true);
//        SpinSTARTminIN.setEditable(true);
//        SpinSTARTsecIN.setEditable(true);
//
//        SpinDELhourIN.setEditable(true);
//        SpinDELminIN.setEditable(true);
//        SpinDELsecIN.setEditable(true);


        //OUT

        SpinnerValueFactory<Integer> gradesHour1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, NOW.getHour());
        SpinnerValueFactory<Integer> gradesMin1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, NOW.getMinute());
        SpinnerValueFactory<Integer> gradesSec1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, NOW.getSecond());

        SpinnerValueFactory<Integer> gradesHourDEL1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
        SpinnerValueFactory<Integer> gradesMinDEL1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 30);
        SpinnerValueFactory<Integer> gradesSecDEL1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 30);

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
    }
    void initializeTable(){
        columnTripIN.setCellValueFactory(new PropertyValueFactory<TableIN, String>("trip"));
        columnPlaneIN.setCellValueFactory(new PropertyValueFactory<TableIN,String>("plane"));
        columnHight.setCellValueFactory(new PropertyValueFactory<TableIN, String>("hight"));
        columnDistance.setCellValueFactory(new PropertyValueFactory<TableIN, String>("distance"));

        columnTripOUT.setCellValueFactory(new PropertyValueFactory<TableOUT, String>("trip"));
        columnPlaneOUT.setCellValueFactory(new PropertyValueFactory<TableOUT,String>("plane"));
        columnTime.setCellValueFactory(new PropertyValueFactory<TableOUT,String>("time"));
        columnDate.setCellValueFactory(new PropertyValueFactory<TableOUT,String>("date"));
    }

    void createFileInput(ArrayList<Plane> planes, Airline[] airlines, Direction[] directions, LocalDateTime NOW, ObservableList<TableIN> table){

        System.out.println((char) 27 + "[34m"+"\n"+FieldPathIN.getText());;
        int CountFlightsIN = SpinCountPlaneIN.getValue();

        Flight flightsIN[] = new Flight[CountFlightsIN];

        double[]hightDist = generateDistANDHight(CountFlightsIN);

        //create random buf IN flights
        for (int i = 0; i < CountFlightsIN; i++) {
            //temps
            Plane temp = planes.get(random.nextInt(12));
            int flagAirLine = random.nextInt(airlines.length);
            int flagDirect = random.nextInt(directions.length);

            flightsIN[i] = new Flight(temp, airlines[flagAirLine], random.nextInt(900) + 100,
                    directions[flagDirect], NOW, true, hightDist[i+CountFlightsIN], hightDist[i]);

            System.out.println((char) 27 + "[30mcreate IN ↓ _#" + temp.name + " " + airlines[flagAirLine].name() + " |Distance: " + hightDist[i+CountFlightsIN] +" |Hight: " + hightDist[i]);
            table.add(new TableIN(flightsIN[i].number,flightsIN[i].carrier,hightDist[i+CountFlightsIN], hightDist[i], flightsIN[i].plane));
        }
            try {
                createJson(FieldPathIN.getText(), flightsIN);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    void createFileOutput(ArrayList<Plane> planes, Airline[] airlines, Direction[] directions, ObservableList<TableOUT> table){
        System.out.println((char) 27 + "[34m"+"\n"+FieldPathOUT.getText());
        int CountFlightsOUT = SpinCountPlaneOUT.getValue();

        Flight flightsOUT[] = new Flight[CountFlightsOUT];

        String log = "";

        //create random temp out flights
        LocalDateTime bufTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(SpinSTARThourOUT.getValue(), SpinSTARTminOUT.getValue(), SpinSTARTsecOUT.getValue()));
        for (int i = 0; i < CountFlightsOUT; i++){
            Plane temp = planes.get(random.nextInt(12));
            int flagAirLine = random.nextInt(airlines.length);
            int flagDirect = random.nextInt(directions.length);
            if (i != 0) {
                bufTime = bufTime.plusHours(SpinDELhourOUT.getValue());
                bufTime = bufTime.plusMinutes(SpinDELminOUT.getValue());
                bufTime = bufTime.plusSeconds(SpinDELsecOUT.getValue());
            }
            flightsOUT[i] = new Flight(temp, airlines[flagAirLine], random.nextInt(900)+100, directions[flagDirect], bufTime);
            table.add(new TableOUT( flightsOUT[i].number, flightsOUT[i].carrier, bufTime,  flightsOUT[i].plane));
            System.out.println((char) 27 + "[30mcreate OUT ↑ _#" + temp.name + " " + airlines[flagAirLine].name() + " |Time: " + bufTime.toString());
        }

        try {
            createJson(FieldPathOUT.getText(), flightsOUT);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //подумать над возвращаемым значением - двумерный массив оч долго
    public double[] generateDistANDHight (int count){
        //так же, поступает n - кол-во элементов
        double[] res = new double[count*2];
        double[] hight = new double[count];
        double[] dist = new double[count];

        hight[0] = 0.6;
        dist[0] = 60;

        for(int i=1;i<4;i++){
            hight[i] = 0.2*(((i*i)+i)+3);
            dist[i]=(11.1806*(hight[i]*hight[i]))+(128.313*hight[i])- 4.4237;
        }
        int t,y=4;
        while(y!=count){
            hight[y] = hight[y-4]+0.15;
            dist[y] = dist[y-4]+10;
            y++;
        }
        for(int i=0;i<count;i++){
            hight[i]*=1000;
        }
        double tt;
        for (int gap = count / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < count; i += 1) {
                tt = hight[i];
                int j;
                for (j = i; j >= gap && hight[j - gap]>tt; j -= gap)
                    hight[j] = hight[j - gap];
                hight[j] = tt;
            }
        }
        for (int gap = count / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < count; i += 1) {
                t = (int)dist[i];
                int j;
                for (j = i; j >= gap && (int)dist[j - gap]>t; j -= gap)
                    dist[j] = (int)dist[j - gap];
                dist[j] = t;
            }
        }
        for(int i=0;i<count;i++){
            //System.out.println((int)(hight[i]*1000)+"м" + "\t" +(int)dist[i]+"км");
            res[i] = (int)hight[i];
            res[i+count]= (int)dist[i];
        }
        return res;
    }
}

