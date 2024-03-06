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
 *
 * @author kjaks
 */
public class modifyController implements Initializable{
    private PrimaryController primaryController;
    
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
       
       @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(this.oldName != null){
        oldName.setText(oldName.getText() +oldTeam[0]) ;
        oldPlayed.setText(oldPlayed.getText() +oldTeam[1]); 
        oldWins.setText(oldWins.getText() +oldTeam[2]);
        oldDraw.setText(oldDraw.getText() +oldTeam[3]);
        }
    }

    @FXML
    private void modifyFormButton(){
       boolean insert = false;
        
      // Nombre del equipo
      name = newTeamName.getText();
      if (name.equals("")) name = oldTeam[0];   // No cambiamos los datos antiguos
      
      // Partidos jugados
      input = newPlayedMatches.getText();
      if (input.equals("")) {
          pM = Integer.parseInt(oldTeam[1]);
          insert = true;
      }  // No cambiamos los datos antiguos
      else  {
          pM= Integer.parseInt(input);
          insert = true;
      }

      // Partidos ganados (no pueden ser más que los partidos jugados)
        input = newWinnedMatches.getText();
        
        if (input.equals("")) {
            wM = Integer.parseInt(oldTeam[2]);
             insert = true;
        }
        else if(Integer.parseInt(input) < 1){
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Los partidos ganados no pueden ser menor que 1!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
        }
        else if (wM >pM){
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Los partidos ganados no pueden ser mas que los partidos jugados!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
        }
        else {
            wM = Integer.parseInt(input);
            insert = true;
        }
      
      // Partidos empatados (no pueden ser más que los partidos jugados menos los ganados)
        input = newDrawMatches.getText();
        if (input.equals("")) {
            dM = Integer.parseInt(oldTeam[3]);
            insert = true;
        }
        else if(Integer.parseInt(input) < 1){
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Los partidos empatados no pueden ser menor que 1!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
        }
        else if (dM > (wM - pM)){
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Los partidos Empatados no pueden ser mayores que los partidos ganados menos los jugados!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();
        }
        else {
            dM = Integer.parseInt(input);
            insert = true;
        }

      // Partidos perdidos (no se preguntan sino que se calculan)
      lM = pM - wM - dM;
      
      // Puntos. Se calcula como partGanados * 3 + partEmpatados
      pts = wM * 3 + dM;

      // Enviamos los datos nuevos para que se escriban en el fichero
      if(insert) {
          int result = dataFile.update(dataFile.getPos(oldTeam[0]), name, pM, wM, dM, lM, pts);
      }
      if (result == -1) {
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText("Ha ocurrido un error al actualizar el fichero!");
        // Mostrar la alerta como un pop-up
        alert.showAndWait();      }
      else {
         Stage currentStage = (Stage) newTeamName.getScene().getWindow();

        alert.setTitle("INFO");
        alert.setHeaderText(null);
        alert.setContentText("Datos actualizados con exito!");
        PrimaryController.getInstance().refreshTable();
        // Mostrar la alerta como un pop-up
        alert.showAndWait();      
         currentStage.close();

      }

      
      
    }    

}