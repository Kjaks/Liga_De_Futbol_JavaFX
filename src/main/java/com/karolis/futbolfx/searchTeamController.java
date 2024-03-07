package com.karolis.futbolfx;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller class for searching and modifying football team data.
 * This controller allows users to search for a team by name and view its information,
 * as well as modify the team's data.
 * 
 * This class interacts with the SoccerFile class to retrieve and update team data.
 * 
 * @author kjaks
 */
public class searchTeamController {
    SoccerFile dataFile = new SoccerFile();
    
    @FXML
    private TextField SearchTeamName, selectTeamName;
    
    private static String[] team;
    private static String name, teamText;

    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

     /**
     * Searches for a team based on the entered name.
     * Displays team information if found, otherwise shows an error message.
     */
    @FXML
    private void search(){    
    
    name = SearchTeamName.getText();
    
    teamText = dataFile.get(name);
    
    if (name.equals("")){
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Escribe el nombre de un equipo!");
        alert.showAndWait();
    }
    else if(teamText == null){    
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("El equipo " + name + " no existe!");
        alert.showAndWait();
    } else{
        team = teamText.split(";");
        SearchTeamName.clear();
        alert.setTitle("Informacion Equipo");
        alert.setHeaderText(null);
        alert.setContentText(
                "Nombre del equipo: " + team[0] 
                + "\nPartidos Jugados: " + team[1] 
                + "\nPartidos Ganados: " + team[2] 
                + "\nPartidos Empatados: " + team[3] 
                + "\nPartidos perdidos: " + team[4] 
                + "\nPuntos: " + team[5]);
        alert.showAndWait();
    }
    
    }
    
     /**
     * Opens the modify team window for the selected team.
     * Closes the current window after opening the modify team window.
     * @throws IOException If an error occurs while loading the modify team window.
     */
    @FXML
    private void modifyTeamButton() throws IOException {
    name = selectTeamName.getText();

     teamText = dataFile.get(name);

    if (name.equals("")) {
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Escribe el nombre de un equipo!");
        alert.showAndWait();
    } else if (teamText == null) {    
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("El equipo " + name + " no existe!");
        alert.showAndWait();
    } else {
        team = teamText.split(";");
        
        Stage currentStage = (Stage) selectTeamName.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifyTeam.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.show();
        
        currentStage.close();
    } 
    }
    
      /**
     * Retrieves the last searched team's data.
     * @return An array containing the team's data.
     */
    public String[] getTeam(){
        return team;
    }
    
}
