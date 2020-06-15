/*************
 *
 * Developped by: YASSER EL BACHIRI
 * E-mail: yasser.elbachiri@gmail.com
 *
 *
 */

package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.security.spec.ECField;
import java.sql.Date;
import java.sql.ResultSet;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("JavaFX Form Project");
        primaryStage.show();

        TableView<Client> tableClient;



        //Construction Tableau JavaFX

        //idClient Column
        TableColumn<Client, Integer> idClientColumn = new TableColumn<>("Client ID");
        idClientColumn.setMinWidth(35);
        idClientColumn.setCellValueFactory(new PropertyValueFactory<>("idClient"));

        //nomPrenom Column
        TableColumn<Client, String> nomPrenomColumn = new TableColumn<>("Nom et Prenom");
        nomPrenomColumn.setMinWidth(130);
        nomPrenomColumn.setCellValueFactory(new PropertyValueFactory<>("nomPrenom"));

        //Email Column
        TableColumn<Client, String> emailColumn = new TableColumn<>("E-mail");
        emailColumn.setMinWidth(200);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        //Password Column
        TableColumn<Client, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setMinWidth(100);
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        //Phone Column
        TableColumn<Client, String> phoneColumn = new TableColumn<>("Telephone");
        phoneColumn.setMinWidth(180);
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        //CreationDate Column
        TableColumn<Client, Date> creationDateColumn = new TableColumn<>("Date de Creation");
        creationDateColumn.setMinWidth(140);
        creationDateColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));

        tableClient = new TableView<>();
        tableClient.setItems(getClient());


        tableClient = new TableView<>();
        tableClient.setItems(getClient());
        tableClient.getColumns().addAll(idClientColumn, nomPrenomColumn, emailColumn, passwordColumn, phoneColumn,creationDateColumn);

        //Structure de l'interface de l'application
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10,10,10,10));
        layout.setSpacing(8);
        HBox hBoxLayout0 = new HBox();


        hBoxLayout0.setAlignment(Pos.CENTER);
        hBoxLayout0.setPadding(new Insets(10,10,10,10));
        hBoxLayout0.setSpacing(8);


        Scene scene = new Scene(layout, 840, 850);

        //Formulaire

        //Nom et Prenom
        Label l1 = new Label("Nom et prénom:");
        TextField t1 = new TextField();
        t1.setPromptText("Nom et Prenom");

        //Email
        Label l2 = new Label("E-mail:");
        TextField t2 = new TextField();
        t2.setPromptText("E-mail");

        //Password
        Label l3 = new Label("Password:");
        TextField t3 = new PasswordField();
        t3.setPromptText("Password");

        //Telephone
        Label l4 = new Label("Numéro de téléphone:");
        TextField t4 = new TextField();
        t4.setPromptText("Numéro de télephone");

        Label l5 = new Label("(Update pour actualiser la liste)");

        //Boutton Submit / Update / Delete
        Button submit = new Button("Submit");
        Button update = new Button("Update");
        Button delete = new Button("Delete");
        Button modify = new Button("Modify");




        //Submit Button Event
        submit.setOnAction( e -> {
            String nameInput = t1.getText();
            String emailInput = t2.getText();
            String passInput = t3.getText();
            String phoneInput = t4.getText();
            //Validation Formulaire
            if(!nameInput.equals("") && !emailInput.equals("") && !passInput.equals("") && !phoneInput.equals("")){
                try {
                    // Insertion des donnees
                    setClient(new Client(nameInput,emailInput,passInput,phoneInput));
                    getClient();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            //Effacer les champs de texte apres insertion des donnees
            t1.clear();
            t2.clear();
            t3.clear();
            t4.clear();
        });

        //Update Button Event
        TableView<Client> finalTableClient = tableClient;
        update.setOnAction(e -> {
            try {
                finalTableClient.setItems(getClient());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //Delete Button Event
        TableView<Client> finalTableClient1 = tableClient;
        TableView<Client> finalTableClient2 = tableClient;
        delete.setOnAction(e -> {
                    ObservableList<Client> clientSelected;
                    clientSelected = finalTableClient1.getSelectionModel().getSelectedItems();
                    clientSelected.forEach(client -> {
                        try {
                            deletClient(client);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    });
        });

        //Modify Button Event
        modify.setOnAction(e -> {
            String nameInput = t1.getText();
            String emailInput = t2.getText();
            String passInput = t3.getText();
            String phoneInput = t4.getText();

            ObservableList<Client> clientSelected;
            clientSelected = finalTableClient.getSelectionModel().getSelectedItems();
            clientSelected.forEach(client1 -> {
                try{
                    modifyClient(client1, new Client (nameInput, emailInput, passInput, phoneInput));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            });
            t1.clear();
            t2.clear();
            t3.clear();
            t4.clear();
        });

        hBoxLayout0.getChildren().addAll(submit,update,delete,modify);
        layout.getChildren().addAll(l1,t1,l2,t2,l3,t3,l4,t4,hBoxLayout0,l5, finalTableClient2);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
    }


    /* Database Connection */

    private ObservableList<Client> getClient() throws Exception {
        ObservableList<Client> clients = FXCollections.observableArrayList();
        if (DbConnection.verifyConnection()) {
            if (DbConnection.getData()) {
                ResultSet resultSet = DbConnection.getResultSet();
                while (resultSet.next()) {
                    int idClient = resultSet.getInt("clientID");
                    String nomPrenom = resultSet.getString("nomPrenom");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("pass");
                    String phone = resultSet.getString("phone");
                    Date dateCreation = resultSet.getDate("date_creation");
                    clients.add(new Client(idClient, nomPrenom, email, password, phone, dateCreation));
                }
                resultSet.close();
            }
        }
        DbConnection.getStatement().close();
        DbConnection.getConnection().close();
        return clients;
    }

    private void setClient(Client c1) throws Exception{
        if(DbConnection.verifyConnection()){
            DbConnection.setData(c1.getNomPrenom(),c1.getEmail(),c1.getPassword(),c1.getPhone());
        }

    }
    private void deletClient(Client c1) throws Exception{
        if(DbConnection.verifyConnection()){
                    DbConnection.deletData(c1.getIdClient());
        }

    }

    private void modifyClient(Client client1,Client client2) throws Exception {
        if (DbConnection.verifyConnection()) {
            System.out.println(client2.getNomPrenom());
            if(!client2.getNomPrenom().equals("")){
                DbConnection.modifyName(client1.getIdClient(), client2.getNomPrenom());
            }
            if(!client2.getEmail().equals("")){
                DbConnection.modifyEmail(client1.getIdClient(), client2.getEmail());
            }
            if(!client2.getPassword().equals("")){
                DbConnection.modifyPass(client1.getIdClient(), client2.getPassword());
            }
            if(!client2.getPhone().equals("")){
                DbConnection.modifyPhone(client1.getIdClient(), client2.getPhone());
            }
            DbConnection.getPreparedStatement().close();
            DbConnection.getConnection().close();

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
