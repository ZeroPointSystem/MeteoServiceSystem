package meteoserver.utils;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import meteoserver.dao.MeteoDataDAOImpl;
import meteoserver.entities.MeteoData;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
/**
 * Created by Zetro on 10.05.2015.
 */
public class COMConnector {
    private int baudRateValue;
    private SerialPort port;
    private TextArea loggingTextArea;
    private MeteoDataDAOImpl dataDAO;
    private int inputPackageSize;
    private boolean selector;
    private MeteoData data;

    public COMConnector(String portName, int baudRateValue) {
        inputPackageSize = 1;
        port = new SerialPort(portName);
        this.baudRateValue = baudRateValue;
    }

    public void createConnection() {
        try {
            port.openPort();
            port.setParams(baudRateValue, 8, 1, 0);
            port.setEventsMask(SerialPort.MASK_RXCHAR);
            port.addEventListener(serialPortEvent -> {
                switch (serialPortEvent.getEventType()) {
                    case SerialPortEvent.BREAK:
                        System.out.println("BREAK");
                        break;
                    case SerialPortEvent.CTS:
                        System.out.println("CTS");
                        break;
                    case SerialPortEvent.DSR:
                        System.out.println("DSR");
                        break;
                    case SerialPortEvent.ERR:
                        System.out.println("ERR");
                        break;
                    case SerialPortEvent.RING:
                        System.out.println("RING");
                        break;
                    case SerialPortEvent.RLSD:
                        System.out.println("RLSD");
                        break;
                    case SerialPortEvent.RXCHAR:
                        Platform.runLater(() -> {
                        if (serialPortEvent.getEventValue() == inputPackageSize) {
                            if (serialPortEvent.getEventValue() == 1) {
                                try {
                                     inputPackageSize = port.readBytes()[0];
                                } catch (SerialPortException e) {
                                    System.out.println(e.getMessage());
                                }
                            } else if (serialPortEvent.getEventValue() == inputPackageSize) {
                                try {
                                    byte[] buffer = port.readBytes();
                                    if (selector) {
                                        // PRESSURE
                                        data.setPressure(new Float(new String(buffer, "UTF-8")));
                                        dataDAO.create(data);
                                        loggingTextArea.appendText(" >> TIME : "+ data.getTime() +", data has been obtained >> \n");
                                        data = null;
                                        selector = !selector;
                                    } else {
                                        // TEMPERATURE
                                        data = new MeteoData();
                                        data.setTemperature(new Float(new String(buffer, "UTF-8")));
                                        data.setHumidity(90F);
                                        data.setTime(Timestamp.valueOf(LocalDateTime.now()));
                                        selector = !selector;
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                inputPackageSize = 1;
                            }
                        }
                        });
                        break;
                    case SerialPortEvent.RXFLAG:
                        System.out.println("RXFLAG");
                        break;
                    case SerialPortEvent.TXEMPTY:
                        System.out.println("RLSD");
                        break;
                }
            });
            loggingTextArea.appendText(" >> The server is started : " + Timestamp.valueOf(LocalDateTime.now()) + " >>\n");
        } catch (SerialPortException e) {
            loggingTextArea.appendText(" >> COM CONNECTOR : " + e.getMessage() + " >>\n");
        }
    }
     public void closeConnection() {
         try {
             port.closePort();
             loggingTextArea.appendText(" >> COM CONNECTOR : Connection with Arduino closed >> \n");
         } catch (SerialPortException e) {
             loggingTextArea.appendText(" << COM CONNECTOR : " + e.getMessage() + " >> \n");
         }
     }
    // PRTS

    public void setDataDAO(MeteoDataDAOImpl dataDAO) {
        this.dataDAO = dataDAO;
    }

    public void setLoggingTextArea(TextArea loggingTextArea) {
        this.loggingTextArea = loggingTextArea;
    }
}
