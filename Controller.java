package sample;


import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
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

    final Random random = new Random(); //для рандома

    @FXML
    private ResourceBundle resources; //хуйня чисто для scene builder

    @FXML
    private URL location; //хуйня чисто для scene builder

    @FXML
    private Button startGENButton; //кнопка старта генерации

    @FXML
    private TextArea FieldPath; //поле для адреса джсона


    @FXML
    private Spinner<Integer> SpinCountPlane; //поле количества записей (счетчик)

    @FXML
    private TextArea logArea; //поле лога создания самолетов



    @FXML
    void initialize() {



        //spinners

        //count planes
        SpinnerValueFactory<Integer> gradesCount = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 2000,50);
        SpinCountPlane.setValueFactory(gradesCount);
        SpinCountPlane.setEditable(true);


        //если жмякнули кнопку
        startGENButton.setOnAction(event -> {
            System.out.println(FieldPath.getText()); //печатаем в консоль, адрес файла, куда создаем (для теста)
            int CountFlights = SpinCountPlane.getValue(); //получаем количество самолетов из счетчика
            ArrayList<Plane> planes = Plane.getPlanes(); //буфер
            Airline[] airlines = Airline.values(); //получаем значения для рандома
            Flight flights[] = new Flight[CountFlights]; //буфер на то количетсво самолетов, которое мы ввели
            String log = ""; //строка для вывода лога
            logArea.setDisable(false); //выключаем возможность изменять лог
            for (int i = 0; i < CountFlights; i++){
                Plane temp = planes.get(random.nextInt(12)); //рандомим самолет
                int flagAirLine = random.nextInt(airlines.length); //рандомим авиакомпанию
                flights[i] = new Flight(temp, airlines[flagAirLine], random.nextInt(9000)+1000, true); //создаем рандомный рейс
                log += "create #" + temp.name + " " + airlines[flagAirLine].name() + "\n"; //добавляем в лог запись о его создании
                logArea.setText(log); //добавляем в лог запись о созданном самолете

            }
            try {
                CreateJson(FieldPath.getText(), flights); //создаем джсон из всех самолетов
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    boolean CreateJson(String PATH,Flight[] buf) throws IOException, InterruptedException{ //создание джсона
        Gson gson = new Gson();//библиотека для сериализации сашкина
        try(FileWriter wr = new FileWriter(PATH)) {
            String str = gson.toJson(buf);
            wr.write(str);
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }


    public void filechooser(javafx.event.ActionEvent actionEvent) { //для выбора пути папки, в которую создадим джсон через проводник
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int chooseDirectiry = fc.showOpenDialog(null);
        if (chooseDirectiry == JFileChooser.APPROVE_OPTION){
            FieldPath.setText(fc.getSelectedFile().getAbsolutePath()+"\\test.json");
        }

    }
}

