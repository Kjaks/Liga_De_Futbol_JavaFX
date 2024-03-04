package com.karolis.futbolfx;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class addTeamController {
    @FXML
    private TextField teamName, playedMatches, winnedMatches, drawMatches ;
    Alert alert = new Alert(AlertType.INFORMATION);
    private String name;
    private int played, winned, draw;

    @FXML
    private void add() throws IOException {
        
        try{
         name = teamName.getText();

            if (name.isEmpty()){
                throw new Exception(" ");
            }
            
                try {
               // Leer los valores de los TextField
               played = Integer.parseInt(playedMatches.getText());
               winned = Integer.parseInt(winnedMatches.getText());
               draw = Integer.parseInt(drawMatches.getText());

           } catch (NumberFormatException e) {
               // Manejar el caso en el que uno de los valores no sea un número entero válido
               alert.setTitle("ERROR");
               alert.setHeaderText(null);
               alert.setContentText("En las casillas de partidos debes introducir numeros!");
                // Mostrar la alerta como un pop-up
               alert.showAndWait();
           }
            
        } catch (Exception e){
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Debes ponerle un nombre a este equipo!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
        }
        
    }
    
}