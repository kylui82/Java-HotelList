import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main extends Application {

    public void start(Stage stage) {
        Pane pane = new Pane(); // Create a pane
        // Add Label
        Label title = new Label("Hotel information");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 28));
        title.setTextFill(Color.CADETBLUE);
        Label lname = new Label("Name");
        Label lstar = new Label("Star");
        Label lprice = new Label("Price");

        //Add button
        Button btn1 = new Button("Sort by Stars");
        Button btn2 = new Button("Sort by Lowest Price");
        Button btn3 = new Button("Export");

        // Add GridPane
        GridPane root = new GridPane();
        root.setVgap(5); // setting vertical gap
        root.setHgap(5); // setting horizontal gap
        root.addColumn(0, title);
        root.addColumn(0, btn1, btn2, btn3);
        root.add(lname, 0, 5, 22, 1);
        root.add(lstar, 2, 5, 22, 1);
        root.add(lprice, 8, 5, 22, 1);

        int total = 0;
        int i = 0;

        //Count number of line
        try (BufferedReader in = new BufferedReader(new FileReader("Hotels.txt"))) {
            while ((in.readLine()) != null) {
                total++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Text t1 = new Text();
        Text t2 = new Text();
        Text t3 = new Text();
        Text export = new Text();

        List hotels = new ArrayList<Hotel>();
        String result ="";
        String result_name = "";
        String result_stars = "";
        String result_price = "";

        try (BufferedReader in = new BufferedReader(new FileReader("Hotels.txt"))) {
            String line;

            //Check if reach last line of the file
            while (i < total) {
                line = in.readLine();
                String str = line;

                String[] arrOfStr = str.split(" ");

                int length = arrOfStr.length;
                String name = "";
                for (int j = 0; j < length - 5; j++) {
                    name += arrOfStr[j] + " ";
                }
                name = name.strip();
                int l1 = length - 5;
                int l2 = length - 3;

                Hotel hotel = new Hotel(name, Integer.parseInt(arrOfStr[l1]), Integer.parseInt(arrOfStr[l2]));
                hotels.add(hotel);
                result = result + hotel.name + ", Stars: "+ hotel.stars + ", Price: " + hotel.price +  "\n";
                result_name = result_name + hotel.name + "\n";
                result_stars = result_stars + hotel.stars + "\n";
                result_price = result_price + hotel.price + "\n";
                i++;
            }
            t1.setText(result_name);
            t2.setText(result_stars);
            t3.setText(result_price);
            export.setText(result);

            root.add(t1, 0, 8, 22, 1);
            root.add(t2, 2, 8, 22, 1);
            root.add(t3, 8, 8, 22, 1);

            // Sort by rating after click button
            btn1.setOnAction(e -> {
                String result2_name = "";
                String result2_stars = "";
                String result2_price = "";
                String result2 = "";

                Collections.sort(hotels, Hotel.STAR_NAME);

                for (int j = 0; j < hotels.size(); j++) {
                    result2_name = result2_name + ((Hotel) hotels.get(j)).name + "\n";
                    result2_stars = result2_stars + ((Hotel) hotels.get(j)).stars + "\n";
                    result2_price = result2_price + ((Hotel) hotels.get(j)).price + "\n";
                    result2 = result2 + ((Hotel) hotels.get(j)).name +  ", Stars: " +
                            ((Hotel) hotels.get(j)).stars +  ", Price:" + ((Hotel) hotels.get(j)).price + "\n";
                }
                t1.setText(result2_name);
                t2.setText(result2_stars);
                t3.setText(result2_price);
                export.setText(result2);

                root.add(t1, 0, 8, 22, 1);
                root.add(t2, 2, 8, 22, 1);
                root.add(t3, 8, 8, 22, 1);
            });

            // Sort by lowest price after click button
            btn2.setOnAction(e -> {
                String result3_name = "";
                String result3_stars = "";
                String result3_price = "";
                String result3 = "";

                Collections.sort(hotels, Hotel.PRICE);
                for (int j = 0; j < hotels.size(); j++) {
                    result3_name = result3_name + ((Hotel) hotels.get(j)).name + "\n";
                    result3_stars = result3_stars + ((Hotel) hotels.get(j)).stars + "\n";
                    result3_price = result3_price + ((Hotel) hotels.get(j)).price + "\n";
                    result3 = result3 + ((Hotel) hotels.get(j)).name +  ", Stars: " +
                            ((Hotel) hotels.get(j)).stars +  ", Price: " + ((Hotel) hotels.get(j)).price + "\n";
                }
                t1.setText(result3_name);
                t2.setText(result3_stars);
                t3.setText(result3_price);
                export.setText(result3);

                root.add(t1, 0, 8, 22, 1);
                root.add(t2, 2, 8, 22, 1);
                root.add(t3, 8, 8, 22, 1);

            });

            // Export txt file after click button
            btn3.setOnAction(e -> {
                //Write result to output.txt
                try (BufferedWriter output = new BufferedWriter(new FileWriter("Sorted.txt"))) {
                        output.write(export.getText());
                        System.out.print(export.getText());

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Scene scene = new Scene(root, 700, 700);
        stage.setTitle("Hotel");
        stage.setScene(scene);
        stage.show();
        pane.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
