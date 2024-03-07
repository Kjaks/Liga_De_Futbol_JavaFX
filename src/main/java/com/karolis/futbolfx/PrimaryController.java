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

/**
 * Controller class for the primary view.
 */
public class PrimaryController {
    private static PrimaryController instance;
    SoccerFile dataFile = new SoccerFile();

    public PrimaryController() {
        instance = this;
    }

    /**
     * Returns the instance of PrimaryController, this method is used for refreshing the table in the other controllers.
     * @return The instance of PrimaryController.
     */
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

    /**
     * Initializes the controller. Sets up table columns and refreshes the table.
     */
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

    /**
     * Refreshes the table with data from the SoccerFile.
     */
    @FXML
    public void refreshTable(){
        clearData();
        String fullTeamText = "";

        for(int i = 0; i < dataFile.getAll().length;i++){
            fullTeamText += dataFile.getAll()[i];
        }

        String[] teamList = fullTeamText.split(";");

        /**
         * Iterates over array and puts the data in the table
         */
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
     * This method loads the "addTeam.fxml" file when the "Add Team" button is clicked.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    private void addButton() throws IOException {
        loadFXML("addTeam.fxml");
    }

    /**
     * This method loads the "searchTeam.fxml" file when the "Search Team" button is clicked.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    private void searchButton() throws IOException{
        loadFXML("searchTeam.fxml");
    }

    /**
     * This method sorts the data and refreshes the table.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    private void sortTable() throws IOException{
        clearData();
        dataFile.sort();
        refreshTable();
    }

    /**
     * This method loads the "deleteTeam.fxml" file when the "Delete Team" button is clicked.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    private void deleteTeam() throws IOException{
        loadFXML("deleteTeam.fxml");
    }

    /**
     * This method loads the "selectTeam.fxml" file when the "Select Team" button is clicked.
     * @throws IOException If there is an error loading the FXML file.
     */
    @FXML
    private void selectTeam() throws IOException{
        loadFXML("selectTeam.fxml");
    }

    /**
     * Utility method to load FXML files and display corresponding scenes.
     * @param view The name of the FXML file to load.
     * @throws IOException If there is an error loading the FXML file.
     */
    private void loadFXML(String view) throws IOException{
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
        Parent root = loader.load();

        // Create the scene
        Scene scene = new Scene(root);

        // Get the main stage
        Stage stage = new Stage();

        // Set the scene on the main stage and show it
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Clears the data from the table.
     */
    private void clearData() {
        data.clear();
    }
}
