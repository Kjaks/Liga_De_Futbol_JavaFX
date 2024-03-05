package com.karolis.futbolfx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author kjaks
 */
public class searchTeamController {
    SoccerFile dataFile = new SoccerFile();
    
    @FXML
    private TextField SearchTeamName;
    private String name, teamText;
    private String[] team;
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    private void search(){    
    
    name = SearchTeamName.getText();
    
    teamText = dataFile.get(name);
    
    if (name.equals("")){
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Escribe el nombre de un equipo!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
    }
    else if(teamText == null){    
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("El equipo " + name + " no existe!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
    } else{
        team = teamText.split(";");
        alert.setTitle("Informacion Equipo");
        alert.setHeaderText(null);
        alert.setContentText(
                "Nombre del equipo: " + team[0] 
                + "\nPartidos Jugados: " + team[1] 
                + "\nPartidos Ganados: " + team[2] 
                + "\nPartidos Empatados: " + team[3] 
                + "\nPartidos perdidos: " + team[4] 
                + "\nPuntos: " + team[5]);
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
    }
    
    }
}
