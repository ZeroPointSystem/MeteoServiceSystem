package meteoserver.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import jssc.SerialPortList;
import meteoserver.dao.MeteoDataDAOImpl;
import meteoserver.utils.COMConnector;
import meteoserver.utils.DataBaseManager;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Created by Zetro on 08.05.2015.
 */
public class ServerConsoleController implements Initializable {
    @FXML
    private Button startServerButton;
    @FXML
    private Button stopServerButton;
    @FXML
    private Button connectionTestButton;
    @FXML
    private Button clearLoggingAreaButton;
    @FXML
    private ComboBox<Integer> baudRateComboBox;
    @FXML
    private ComboBox<String> comComboBox;
    @FXML
    private TextField dataBaseURLTextField;
    @FXML
    private TextField userNameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextArea loggingTextArea;

    private COMConnector comConnector;
    private DataBaseManager dataBaseManager;
    private MeteoDataDAOImpl meteoDataDAO;


    public void initialize(URL location, ResourceBundle resources) {
        initialize();
        setActionListeners();
    }
    private void initialize() {
        baudRateComboBox.getItems().setAll(new Integer[]{300, 1200, 2400, 4800, 9600, 19200, 38400, 57600, 115200});
        startServerButton.setDisable(true);
        stopServerButton.setDisable(true);
        loggingTextArea.setEditable(false);
        dataBaseURLTextField.setText("localhost:5432/meteo");
        userNameTextField.setText("postgres");
    }
    private void setActionListeners() {
        comComboBox.setOnMouseEntered(event -> {
            comComboBox.getItems().clear();
            comComboBox.getItems().addAll(SerialPortList.getPortNames());
            comComboBox.show();
        });

        baudRateComboBox.setOnMouseEntered(event -> baudRateComboBox.show());

        startServerButton.setOnAction(event -> {
            try {
                String portName = comComboBox.getSelectionModel().getSelectedItem();
                int baudRate = baudRateComboBox.getSelectionModel().getSelectedItem();
                comConnector = new COMConnector(portName, baudRate);
                comConnector.setDataDAO(meteoDataDAO);
                comConnector.setLoggingTextArea(loggingTextArea);
                comConnector.createConnection();
                startServerButton.setDisable(true);
                stopServerButton.setDisable(false);
                connectionTestButton.setDisable(true);
                setControlsDisable(true);
            } catch (NullPointerException e) {
                loggingTextArea.appendText(" >> Please, confirm the form >>\n");
            }
        });

        stopServerButton.setOnAction(event -> {
            comConnector.closeConnection();
            try {
                dataBaseManager.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            startServerButton.setDisable(true);
            stopServerButton.setDisable(true);
            connectionTestButton.setDisable(false);
            setControlsDisable(false);
            loggingTextArea.appendText(" >> Server is stopped : " + Timestamp.valueOf(LocalDateTime.now()) + " >>\n");

        });

        connectionTestButton.setOnAction(event -> {
                try {
                    dataBaseManager = new DataBaseManager(dataBaseURLTextField.getText(), userNameTextField.getText(), passwordTextField.getText());
                    meteoDataDAO = new MeteoDataDAOImpl(dataBaseManager);
                    startServerButton.setDisable(false);
                    loggingTextArea.appendText(" >> Database is connected successfully >>\n");
                    connectionTestButton.setDisable(true);
                } catch (Exception e) {
                    loggingTextArea.appendText(" >> Error connecting to database >>\n");
                }
        });

        clearLoggingAreaButton.setOnAction(event -> {
            loggingTextArea.clear();
            loggingTextArea.autosize();
        });
    }

    private void setControlsDisable(boolean disable) {
        comComboBox.setDisable(disable);
        baudRateComboBox.setDisable(disable);
        dataBaseURLTextField.setDisable(disable);
        userNameTextField.setDisable(disable);
        passwordTextField.setDisable(disable);
    }
}
