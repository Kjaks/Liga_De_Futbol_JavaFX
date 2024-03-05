package com.karolis.futbolfx;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PrimaryController {
    private static PrimaryController instance;
    SoccerFile dataFile = new SoccerFile();
    
     public PrimaryController() {
        instance = this;
    }

    public static PrimaryController getInstance() {
        return instance;
    }
    
    @FXML
    private TableView<Team> teamTable;

    @FXML
    private TableColumn<Team, String> nameCol;

    @FXML
    private TableColumn<Team, Integer> matchesPlayedCol;

    @FXML
    private TableColumn<Team, Integer> matchesWonCol;

    @FXML
    private TableColumn<Team, Integer> matchesDrawnCol;

    @FXML
    private TableColumn<Team, Integer> matchesLostCol;

    @FXML
    private TableColumn<Team, Integer> pointsCol;

    @FXML
    final ObservableList<Team> data = FXCollections.observableArrayList();
    
    @FXML
    private void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        matchesPlayedCol.setCellValueFactory(new PropertyValueFactory<>("matchesPlayed"));
        matchesWonCol.setCellValueFactory(new PropertyValueFactory<>("matchesWon"));
        matchesDrawnCol.setCellValueFactory(new PropertyValueFactory<>("matchesDrawn"));
        matchesLostCol.setCellValueFactory(new PropertyValueFactory<>("matchesLost"));
        pointsCol.setCellValueFactory(new PropertyValueFactory<>("points"));
        refreshTable();
    }

    @FXML
    public void refreshTable(){
        clearData();
         String fullTeamText = "";
        
        for(int i = 0; i < dataFile.getAll().length;i++){
            fullTeamText += dataFile.getAll()[i];
        }
        
        String[] teamList = fullTeamText.split(";");
               
        
        for(int i = 0; i < teamList.length - 1;i+= 6){
            String name = teamList[i];
            int matchesPlayed = Integer.parseInt(teamList[i + 1]);
            int matchesWon = Integer.parseInt(teamList[i + 2]);
            int matchesDrawn = Integer.parseInt(teamList[i + 3]);
            int matchesLost = Integer.parseInt(teamList[i + 4]);
            int points = Integer.parseInt(teamList[i + 5]);

            data.add(new Team(name, matchesPlayed, matchesWon, matchesDrawn, matchesLost, points));

        }
                
        teamTable.setItems(data);
    }
    /**
     * This metod will throw a Pop Up when you click the "Añadir Equipo" button and will ask us info about the team
     * @throws IOException 
     */
    @FXML
    private void addButton() throws IOException {
        // Cargar el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addTeam.fxml"));
        Parent root = loader.load();
        
        // Crear la escena
        Scene scene = new Scene(root);
        
        // Obtener el escenario principal
        Stage stage = new Stage();
        
        // Establecer la escena en el escenario principal y mostrarlo
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void searchButton() throws IOException {
        // Cargar el archivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchTeam.fxml"));
        Parent root = loader.load();
        
        // Crear la escena
        Scene scene = new Scene(root);
        
        // Obtener el escenario principal
        Stage stage = new Stage();
        
        // Establecer la escena en el escenario principal y mostrarlo
        stage.setScene(scene);
        stage.show();
    }
    
    public void clearData() {
    data.clear();
    }

}