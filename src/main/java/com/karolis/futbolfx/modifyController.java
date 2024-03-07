package com.karolis.futbolfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for modifying football team data.
 * This controller allows the user to modify team data such as name, matches played, won, and drawn.
 * It also handles displaying alerts in case of errors or successful operation.
 * 
 * @author kjaks
 */
public class modifyController implements Initializable{
    private PrimaryController primaryController;
    
     /**
     * Sets the primary controller. So we can refresh the table
     * @param primaryController The main controller of the application.
     */
    public void setPrimaryController(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }
    
   SoccerFile dataFile = new SoccerFile();    
   searchTeamController teamInfo = new searchTeamController();
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
   
   @FXML
   private TextField newTeamName, newPlayedMatches, newWinnedMatches, newDrawMatches;
       
   private String[] oldTeam = teamInfo.getTeam();
   private int result = 0, pM, wM, dM, lM, pts;
   private String name, input;
              
   @FXML
    private Label oldName, oldPlayed, oldWins, oldDraw;
       
     /**
     * Initializes the controller. So we can see the old data in the pop-up
     * Automatically called after the root object has been loaded.
     * @param url The location used to resolve relative paths for the root object.
     * @param rb The resources used to localize the root object.
     */
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(this.oldName != null){
        oldName.setText(oldName.getText() +oldTeam[0]) ;
        oldPlayed.setText(oldPlayed.getText() +oldTeam[1]); 
        oldWins.setText(oldWins.getText() +oldTeam[2]);
        oldDraw.setText(oldDraw.getText() +oldTeam[3]);
        }
    }

     /**
     * Handles the click event on the team data modification button.
     * This method validates the data entered by the user and updates the corresponding team data.
     * It also shows alerts in case of errors or successful operation.
     */
    @FXML
    private void modifyFormButton(){
       int result = -1;
       boolean nuevo = false;
       
      
      // Team name
      name = newTeamName.getText();
      if (name.equals("")) name = oldTeam[0];
      else{
          nuevo = true;
      }
      
      // Played Matches
      input = newPlayedMatches.getText();
      if (input.equals("")) {
          pM = Integer.parseInt(oldTeam[1]);
      } 
      else  {
          pM= Integer.parseInt(input);
          nuevo = true;
      }

      // Winned matches
        input = newWinnedMatches.getText();
        
        if (input.equals("")) {
            wM = Integer.parseInt(oldTeam[2]);
        }
        else if(Integer.parseInt(input) < 1){
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Los partidos ganados no pueden ser menor que 1!");
        wM = Integer.parseInt(oldTeam[2]);
        alert.showAndWait();
        }
        else if ( Integer.parseInt(input) >pM){
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Los partidos ganados no pueden ser mas que los partidos jugados!");
        wM = Integer.parseInt(oldTeam[2]);
        alert.showAndWait();
        }
        else {
            System.out.println("PARTIDOS JUGADOS: " + pM);
            wM = Integer.parseInt(input);
            nuevo = true;
        }
      
        // Draw matches
        input = newDrawMatches.getText();
        if (input.equals("")) {
            dM = Integer.parseInt(oldTeam[3]);
        }
        else if(Integer.parseInt(input) < 1){
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Los partidos empatados no pueden ser menor que 1!");
        dM = Integer.parseInt(oldTeam[3]);
        alert.showAndWait();
        }
        else if (Integer.parseInt(input) > (wM - pM)){
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Los partidos Empatados no pueden ser mayores que los partidos ganados menos los jugados!");
        dM = Integer.parseInt(oldTeam[3]);
        alert.showAndWait();
        }
        else {
            dM = Integer.parseInt(input);
            nuevo = true;
        }

      // Lost matches
      lM = pM - (wM + dM);
      
      // Calculate points
      pts = wM * 3 + dM;

      // Send new data to update the team
      result = dataFile.update(dataFile.getPos(oldTeam[0]), name, pM, wM, dM, lM, pts);
      Stage currentStage = (Stage) newTeamName.getScene().getWindow();
      if (result == -1) {
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Ha ocurrido un error al actualizar el fichero!");
        alert.showAndWait();      
         currentStage.close();
      }
      else if (nuevo){
        alert.setTitle("INFO");
        alert.setHeaderText(null);
        alert.setContentText("Datos actualizados con exito!");
        PrimaryController.getInstance().refreshTable();
        alert.showAndWait();      
         currentStage.close();

      }      
    }    
}