package com.toyota.carservice.controller;

import java.net.URL;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.context.ApplicationContext;

import com.toyota.carservice.animations.FadeInUpTransition;
import com.toyota.carservice.config.config;
import com.toyota.carservice.config.config2;
import com.toyota.carservice.implement.CarDaoImpl;
import com.toyota.carservice.implement.PartDaoImpl;
import com.toyota.carservice.implement.ServiceDaoImpl;
import com.toyota.carservice.model.Car;
import com.toyota.carservice.model.Part;
import com.toyota.carservice.model.Service;

public class CarServiceController implements Initializable {

    @FXML
    private Button btnAddOnClick;

    @FXML
    private Label lblCarId;

    @FXML
    private TextField txtCarName;

    @FXML
    private Label lblLocationImage;

    @FXML
    private Button btnBrowseImage;

    @FXML
    private ImageView imageCar;

    @FXML
    private TextField txtPartId;

    @FXML
    private TextField txtPartNumber;

    @FXML
    private TextField txtPartName;

    @FXML
    private TextField txtPrice;

    @FXML
    private Button btnSavePart;

    @FXML
    private TextField txtServiceName;

    @FXML
    private TextField txtQty;

    @FXML
    private Button btnSaveService;

    @FXML
    private ComboBox<String> cmbPart;

    @FXML
    private AnchorPane paneCar;

    @FXML
    private AnchorPane paneService;

    @FXML
    private AnchorPane panePart;

    @FXML
    private TableView<Part> tablePart;

    private TableColumn columnAction;

    @FXML
    private TableColumn<Part, String> PartIdColumn;

    @FXML
    private TableColumn<Part, String> CarColumn;

    @FXML
    private TableColumn<Part, String> PartNumberColumn;

    @FXML
    private TableColumn<Part, String> PartNameColumn;

    @FXML
    private TableColumn<Part, String> PriceColumn;

    @FXML
    private TableView<Service> tableService;

    @FXML
    private TableColumn<Service, String> NameServiceColumn;

    @FXML
    private TableColumn<Service, String> PartServiceColumn;

    @FXML
    private TableColumn<Service, String> QtyColumn;

    @FXML
    private TableColumn<Service, String> PeriodeColumn;

    @FXML
    private ProgressBar barCar;

    @FXML
    private ProgressBar barPart;

    @FXML
    private ProgressBar barService;

    @FXML
    private ImageView imgLoadCar;

    @FXML
    private ImageView imgLoadPart;

    @FXML
    private ImageView imgLoadService;

    @FXML
    private AnchorPane anchorCar;

    private EntityManagerFactory emf;

    private EntityManager em;

    private Car car;

    private PartDaoImpl partDaoImpl;

    private CarDaoImpl carDaoImpl;

    private ServiceDaoImpl serviceDaoImpl;

    private ObservableList<Part> listPart;

    Integer status;

    config2 con = new config2();

    private CarMenuManagement carMenuManagement;

    private Part part;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            ApplicationContext ctx = config.getInstance()
                    .getApplicationContext();
            partDaoImpl = ctx.getBean(PartDaoImpl.class);
            carDaoImpl = ctx.getBean(CarDaoImpl.class);
            serviceDaoImpl = ctx.getBean(ServiceDaoImpl.class);
            if (car != null) {
                listPart = FXCollections.observableArrayList();
                config2.setModelColumn(PartIdColumn, "partId");
                config2.setModelColumn(PartNumberColumn, "partNumber");
                config2.setModelColumn(PartNameColumn, "partName");
                columnAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, Boolean>, ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(
                            TableColumn.CellDataFeatures<Object, Boolean> p) {
                        return new SimpleBooleanProperty(
                                p.getValue() != null);
                    }
                });
                columnAction
                        .setCellFactory(new Callback<TableColumn<Object, Boolean>, TableCell<Object, Boolean>>() {
                            @Override
                            public TableCell<Object, Boolean> call(
                                    TableColumn<Object, Boolean> p) {
                                return new ButtonCell(tablePart);
                            }
                        });
                txtCarName.setText(car.getName());
                selectPartWithService();
                selectServiceWithService();
            }
        });
    }

    private void selectServiceWithService() {
        javafx.concurrent.Service<Integer> service = new javafx.concurrent.Service<Integer>() {
            @Override
            protected Task<Integer> createTask() {
                selectDataPart();
                return new Task<Integer>() {
                    @Override
                    protected Integer call() throws Exception {
                        Integer max = serviceDaoImpl.findByCar(car).size();
                        if (max > 35) {
                            max = 30;
                        }
                        updateProgress(0, max);
                        for (int k = 0; k < max; k++) {
                            Thread.sleep(40);
                            updateProgress(k + 1, max);
                        }
                        return max;
                    }
                };
            }
        };

        service.start();
        barPart.progressProperty().bind(service.progressProperty());
        service.setOnRunning((WorkerStateEvent event) -> {
            imgLoadPart.setVisible(true);
        });
        service.setOnSucceeded((WorkerStateEvent event) -> {
            imgLoadPart.setVisible(false);
            new FadeInUpTransition(panePart).play();
        });
    }

    private void selectPartWithService() {
        javafx.concurrent.Service<Integer> service = new javafx.concurrent.Service<Integer>() {
            @Override
            protected Task<Integer> createTask() {
                return new Task<Integer>() {
                    @Override
                    protected Integer call() throws Exception {
                        Integer max = partDaoImpl.findByCar(car).size();
                        if (max > 35) {
                            max = 30;
                        }
                        updateProgress(0, max);
                        for (int k = 0; k < max; k++) {
                            Thread.sleep(40);
                            updateProgress(k + 1, max);
                        }
                        return max;
                    }
                };
            }
        };

        service.start();
        barPart.progressProperty().bind(service.progressProperty());
        service.setOnRunning((WorkerStateEvent event) -> {
            imgLoadPart.setVisible(true);
        });
        service.setOnSucceeded((WorkerStateEvent event) -> {
            imgLoadPart.setVisible(false);
            new FadeInUpTransition(panePart).play();
        });
    }

    private void selectDataPart() {
        if (listPart == null) {
            listPart = FXCollections.observableArrayList();
            Iterator<Part> i = partDaoImpl.findByCar(car).iterator();
            while (i.hasNext()) {
                Part obj = (Part) i.next();
                listPart.add(obj);
            }
        } else {
            listPart.clear();
            listPart.addAll(partDaoImpl.findByCar(car));
        }
        tablePart.setItems(listPart);
    }

    @FXML
    public void btnBrowseImageOnClick() {

    }

    // ================CRUD FOR CAR=================
    @FXML
    public void btnSaveCarOnClick() {
        if (car == null) {
            car = new Car();
        }
        car.setName(txtCarName.getText());
        car.setPhoto(lblLocationImage.getText());
        carDaoImpl.save(car);
        carMenuManagement.loadMenu();
    }

    @FXML
    public void btnDeleteCarOnClick() {
        carDaoImpl.delete(car);
        carMenuManagement.loadMenu();
    }

    @FXML
    public void btnCancelOnClick() {
        carMenuManagement.loadMenu();
    }

    // =================CRUD FOR PART================
    @FXML
    public void btnSavePartOnClick() {
        Part part = new Part();
    }

    @FXML
    public void btnDeletePartOnClick() {

    }

    @FXML
    public void btnLoadPart() {

    }

    private class ButtonCell extends TableCell<Object, Boolean> {

        final Hyperlink cellButtonDelete = new Hyperlink("Delete");
        final Hyperlink cellButtonEdit = new Hyperlink("Edit");
        final HBox hb = new HBox(cellButtonDelete, cellButtonEdit);

        ButtonCell(final TableView tblView) {
            hb.setSpacing(4);
            cellButtonDelete.setOnAction((ActionEvent t) -> {
                status = 1;
                int row = getTableRow().getIndex();
                tablePart.getSelectionModel().select(row);
                aksiKlikTableData(null);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "Are You Sure Delete Data ?");// +txtName.getText()+" ?");
                alert.initStyle(StageStyle.UTILITY);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    Part p = new Part();
                    p.setPartId(Integer.valueOf(txtPartId.getText()));
                    partDaoImpl.delete(p);
                    clearPart();
                    selectDataPart();
                } else {
                    clearPart();
                    selectDataPart();
                }
                status = 0;
            });
            cellButtonEdit.setOnAction((ActionEvent event) -> {
                status = 1;
                int row = getTableRow().getIndex();
                tablePart.getSelectionModel().select(row);
                aksiKlikTableData(null);
                paneCar.setOpacity(0);
                new FadeInUpTransition(paneCar).play();
                status = 0;
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(hb);
            } else {
                setGraphic(null);
            }
        }

    }

    @FXML
    private void aksiKlikTableData(MouseEvent event) {
        if (status == 1) {
            try {
                Part part = tablePart.getSelectionModel().getSelectedItem();
                this.part = part;
                txtPartNumber.setText(part.getPartNumber());
                txtPartName.setText(part.getPartName());
                txtPrice.setText(part.getPrice().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void clearPart() {
        txtPartName.setText("");
        txtPartNumber.setText("");
        txtPrice.setText("0");
    }

    // =================CRUD FOR SERVICE==============
    @FXML
    public void btnSaveServiceOnClick() {

    }

    @FXML
    public void btnDeleteServiceOnClick() {

    }

    @FXML
    public void btnLoadService() {

    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setListener(CarMenuManagement carMenuManagement) {
        this.carMenuManagement = carMenuManagement;
    }
}
