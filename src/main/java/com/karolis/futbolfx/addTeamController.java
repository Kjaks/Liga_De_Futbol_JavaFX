package com.karolis.futbolfx;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class addTeamController {
    private PrimaryController primaryController;
    
    public void setPrimaryController(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }
    
    @FXML
    private TextField teamName, playedMatches, winnedMatches, drawMatches ;
    
    Alert alert = new Alert(AlertType.INFORMATION);
    private String name;
    private int played, won, draw, lost, points;
    
    SoccerFile dataFile = new SoccerFile();
    
@FXML
private void add() throws IOException {

    name = teamName.getText();

    try {
        // Leer los valores de los TextField
        played = Integer.parseInt(playedMatches.getText());
        won = Integer.parseInt(winnedMatches.getText());
        draw = Integer.parseInt(drawMatches.getText());
    } catch (NumberFormatException e) {
        // Manejar el caso en el que uno de los valores no sea un número entero válido
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Debes rellenar el formulario!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
        return; // Salir del método si ocurre una excepción
    }

    if (name.isEmpty()) {
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Debes ponerle un nombre al equipo!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
    } else if (played < 0 || won < 0 || draw < 0) {
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Ningún número puede ser negativo!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
    } else if (won > played) {
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("El número de partidos ganados no puede ser mayor que el de partidos jugados!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
    } else if (draw > (played - won)) {
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Los partidos empatados no pueden ser más que los partidos jugados menos los ganados!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
    } else {

        // Partidos perdidos (no se preguntan sino que se calculan)
        lost = played - won - draw;

        // Puntos. Se calcula como partGanados * 3 + partEmpatados
        points = won * 3 + draw;

        // Escribimos todos los datos en el fichero
        int result = dataFile.save(name, played, won, draw, lost, points);
        if (result == 0) {
            teamName.clear();
            playedMatches.clear();
            winnedMatches.clear();
            drawMatches.clear();
            alert.setTitle("Equipo añadido!");
            alert.setHeaderText(null);
            alert.setContentText("Equipo añadido con éxito!");
            
            PrimaryController.getInstance().refreshTable();
            // Mostrar la alerta como un pop-up
            alert.showAndWait();
        } else {
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Error al guardar el equipo");
            // Mostrar la alerta como un pop-up
            alert.showAndWait();
        }
    }
}
}