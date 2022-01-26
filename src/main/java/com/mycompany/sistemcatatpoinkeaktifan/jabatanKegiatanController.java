package com.mycompany.sistemcatatpoinkeaktifan;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

/**
 *
 * @author acer
 */
public class jabatanKegiatanController implements Initializable{
    
    
     @FXML
    private void switchToSecondary() throws IOException {
            App.setRoot("secondary");
    }
    
    @FXML
    private TextField filterField;
    
    @FXML
    private Button cancelButton;

    @FXML
    private TextField IDTextField;

    @FXML
    private TextField KeteranganTextField;

    @FXML
    private DatePicker MasaTextField;
    
    
    @FXML
    private Label labelPoin;

    @FXML
    private Button insertButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox<String> comboOrganisasi;

    @FXML
    private ComboBox<String> comboTingkat;
    
    @FXML
    private ComboBox<String> comboJenis;

    @FXML
    private TableView<Jabatan> jabatanTableView;

    @FXML
    private TableColumn<Jabatan, Integer> colID;

    @FXML
    private TableColumn<Jabatan, String> colOrganisasi;
    
    @FXML
    private TableColumn<Jabatan, String> colTingkat;
    

    @FXML
    private TableColumn<Jabatan, String> colJenis;

    @FXML
    private TableColumn<Jabatan, String> colKeterangan;

    @FXML
    private TableColumn<Jabatan, String> colMasa;

    @FXML
    private TableColumn<Jabatan, Integer> colPoin;
    
    @FXML
    private TableColumn<Jabatan, String> colStatusJabatan;
    
    @FXML
    private void handleButtonAction(ActionEvent event){
        if(event.getSource() ==  insertButton){
            insertRecord();
        }else if (event.getSource() == updateButton){
            updateRecord();
        }else if(event.getSource() == deleteButton){
            deleteButton();
    }
        
        
    }
    
    ObservableList<String> list = FXCollections.observableArrayList();
    ObservableList<String> list1 = FXCollections.observableArrayList();
    ObservableList<String> list2 = FXCollections.observableArrayList();
    
    private void organisasi(){
       list.removeAll(list);
       String a="Universitas~1 Tahun (Kepengurusan)";
       String b="Universitas~<1 Tahun (Kepanitiaan)";
       String c="Fakultas/Program Studi~1 Tahun(Kepengurusan)";
       String d="Fakultas/Program Studi~<1 Tahun (Kepanitiaan)";
       list.addAll(a,b,c,d);
       comboOrganisasi.getItems().addAll(list);
   }
    
   private void lingkup(){
       list1.removeAll(list1);
       String a="Intern";
       String b="DIY";
       String c="Nasional";
       String d="Internasional";
       list1.addAll(a,b,c,d);
       comboTingkat.getItems().addAll(list1);
   }
   
   private void jenis(){
       list2.removeAll(list2);
       String a="Ketua";
       String b="Wakil Ketua";
       String c="Sekretatis/Bendahara";
       String d="Koordinator Dept/Seksi";
       String e="Anggota";
       String f="Ketua UKM";
       String g="Wakil Ketua UKM";
       String h="Sekr/Bendahara UKM";
       String i="Koord Divisi UKM";
       String j="Anggota UKM";
       list2.addAll(a,b,c,d,e,f,g,h,i,j);
       comboJenis.getItems().addAll(list2);
   }
    
   
    
    public ObservableList<Jabatan> getJabatanList(){
        ObservableList<Jabatan> kegiatanjabatanList = FXCollections.observableArrayList();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String query = "SELECT * FROM KegiatanJabatan";
        Statement st;
        ResultSet rs;
        
        try{
            st = connectDB.createStatement();
            rs = st.executeQuery(query);
            Jabatan kegiatanjabatan;
            while(rs.next()){
                kegiatanjabatan = new Jabatan(rs.getInt("IDJabatan"), rs.getString("Organisasi"), rs.getString("Tingkat"), rs.getString("JenisJabatan"), rs.getString("Keterangan"), rs.getString("MasaJabatan"), rs.getInt("Poin"));    
                kegiatanjabatanList.add(kegiatanjabatan);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return kegiatanjabatanList;
    }
    
    public void showJabatan(){
        ObservableList<Jabatan> list = getJabatanList();
        
        colID.setCellValueFactory(new PropertyValueFactory<Jabatan, Integer>("IDJabatan"));
        colOrganisasi.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("Organisasi"));
        colTingkat.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("Tingkat"));
        colJenis.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("JenisJabatan"));
        colKeterangan.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("Keterangan"));
        colMasa.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("MasaJabatan"));
        colPoin.setCellValueFactory(new PropertyValueFactory<Jabatan, Integer>("Poin"));
        
        
        jabatanTableView.setItems(list);
        search();
    }
    
    private void insertRecord(){
        String query = "INSERT INTO KegiatanJabatan VALUES (" + IDTextField.getText() + ", '" + comboOrganisasi.getValue() + "', '" + comboTingkat.getValue() + "' , '" + comboJenis.getValue() + "' , '" + KeteranganTextField.getText()+ "' , '" + MasaTextField.getValue() + "' , " + labelPoin.getText() + ")";
        executeQuery(query);
        search();
        showJabatan();
    }
    
    private void updateRecord(){
        String query = "UPDATE KegiatanJabatan SET Organisasi = '" + comboOrganisasi.getValue() + "', Tingkat = '" + comboTingkat.getValue() + "' , JenisJabatan = '" + comboJenis.getValue() + "',Keterangan = '" + KeteranganTextField.getText() + "', MasaJabatan = '" + MasaTextField.getValue() + "', Poin = " + labelPoin.getText() + " WHERE IDJabatan = " + IDTextField.getText() + "";
        executeQuery(query);
        search();
        showJabatan();
        
    }
    
    private void deleteButton(){
        String query = "DELETE FROM KegiatanJabatan WHERE IDJabatan = '" + IDTextField.getText() + "'";
        executeQuery(query);
        search();
        showJabatan();
    }

     @FXML
    void search(){
        ObservableList<Jabatan> list = getJabatanList();
        
        colID.setCellValueFactory(new PropertyValueFactory<Jabatan, Integer>("IDJabatan"));
        colOrganisasi.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("Organisasi"));
        colTingkat.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("Tingkat"));
        colJenis.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("JenisJabatan"));
        colKeterangan.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("Keterangan"));
        colMasa.setCellValueFactory(new PropertyValueFactory<Jabatan, String>("MasaJabatan"));
        colPoin.setCellValueFactory(new PropertyValueFactory<Jabatan, Integer>("Poin"));
        
        jabatanTableView.setItems(list);
        FilteredList<Jabatan> filteredData = new FilteredList<>(list, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(jabatan -> {
                // If filter text is empty, display all data.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (jabatan.getJenisJabatan().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches the JenisJabatan
                } else if (jabatan.getKeterangan().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches the Keterangan
                }else if (jabatan.getOrganisasi().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches the Organisasi
                }else if (jabatan.getTingkat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches the Tingkat
                }
                return false; // Does not match.
            });
        });
        SortedList<Jabatan> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(jabatanTableView.comparatorProperty());
        jabatanTableView.setItems(sortedData);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        organisasi();
        lingkup();
        jenis();
        search();
        showJabatan();
    }
    
   

    private void executeQuery(String query) {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        Statement st;
        try{
            st = connectDB.createStatement();
            st.execute(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
     public void comboChanged(ActionEvent event){
        String organisasi = comboOrganisasi.getValue();
        String tingkat = comboTingkat.getValue();
        String jenis = comboJenis.getValue();
        if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Intern") && jenis.equals("Ketua")){
            labelPoin.setText("30");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Intern") && jenis.equals("Wakil Ketua")){
            labelPoin.setText("28");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Intern") && jenis.equals("Sekretatis/Bendahara")){
            labelPoin.setText("25");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Intern") && jenis.equals("Koordinator Dept/Seksi")){
            labelPoin.setText("22");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Intern") && jenis.equals("Anggota")){
            labelPoin.setText("18");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Intern") && jenis.equals("Ketua UKM")){
            labelPoin.setText("20");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Intern") && jenis.equals("Wakil Ketua UKM")){
            labelPoin.setText("18");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Intern") && jenis.equals("Sekr/Bendahara UKM")){
            labelPoin.setText("16");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Intern") && jenis.equals("Koord Divisi UKM")){
            labelPoin.setText("14");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Intern") && jenis.equals("Anggota UKM")){
            labelPoin.setText("10");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("DIY") && jenis.equals("Ketua")){
            labelPoin.setText("40");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("DIY") && jenis.equals("Wakil Ketua")){
            labelPoin.setText("36");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("DIY") && jenis.equals("Sekretatis/Bendahara")){
            labelPoin.setText("32");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("DIY") && jenis.equals("Koordinator Dept/Seksi")){
            labelPoin.setText("28");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("DIY") && jenis.equals("Anggota")){
            labelPoin.setText("24");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Nasional") && jenis.equals("Ketua")){
            labelPoin.setText("50");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Nasional") && jenis.equals("Wakil Ketua")){
            labelPoin.setText("45");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Nasional") && jenis.equals("Sekretatis/Bendahara")){
            labelPoin.setText("40");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Nasional") && jenis.equals("Koordinator Dept/Seksi")){
            labelPoin.setText("36");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Nasional") && jenis.equals("Anggota")){
            labelPoin.setText("32");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Internasional") && jenis.equals("Ketua")){
            labelPoin.setText("80");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Internasional") && jenis.equals("Wakil Ketua")){
            labelPoin.setText("75");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Internasional") && jenis.equals("Sekretatis/Bendahara")){
            labelPoin.setText("70");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Internasional") && jenis.equals("Koordinator Dept/Seksi")){
            labelPoin.setText("65");
        }else if(organisasi.equals("Universitas~1 Tahun (Kepengurusan)") && tingkat.equals("Internasional") && jenis.equals("Anggota")){
            labelPoin.setText("50");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("Intern") && jenis.equals("Ketua")){
            labelPoin.setText("10");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("Intern") && jenis.equals("Wakil Ketua")){
            labelPoin.setText("9");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("Intern") && jenis.equals("Sekretatis/Bendahara")){
            labelPoin.setText("8");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("Intern") && jenis.equals("Koordinator Dept/Seksi")){
            labelPoin.setText("7");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("Intern") && jenis.equals("Anggota")){
            labelPoin.setText("5");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("DIY") && jenis.equals("Ketua")){
            labelPoin.setText("14");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("DIY") && jenis.equals("Wakil Ketua")){
            labelPoin.setText("12");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("DIY") && jenis.equals("Sekretatis/Bendahara")){
            labelPoin.setText("10");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("DIY") && jenis.equals("Koordinator Dept/Seksi")){
            labelPoin.setText("8");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("DIY") && jenis.equals("Anggota")){
            labelPoin.setText("6");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("Nasional") && jenis.equals("Ketua")){
            labelPoin.setText("20");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("Nasional") && jenis.equals("Wakil Ketua")){
            labelPoin.setText("18");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("Nasional") && jenis.equals("Sekretatis/Bendahara")){
            labelPoin.setText("15");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("Nasional") && jenis.equals("Koordinator Dept/Seksi")){
            labelPoin.setText("12");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("Nasional") && jenis.equals("Anggota")){
            labelPoin.setText("10");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("Internasional") && jenis.equals("Ketua")){
            labelPoin.setText("30");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("Internasional") && jenis.equals("Wakil Ketua")){
            labelPoin.setText("26");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("Internasional") && jenis.equals("Sekretatis/Bendahara")){
            labelPoin.setText("22");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("Internasional") && jenis.equals("Koordinator Dept/Seksi")){
            labelPoin.setText("18");
        }else if(organisasi.equals("Universitas~<1 Tahun (Kepanitiaan)") && tingkat.equals("Internasional") && jenis.equals("Anggota")){
            labelPoin.setText("15");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("Intern") && jenis.equals("Ketua")){
            labelPoin.setText("22");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("Intern") && jenis.equals("Wakil Ketua")){
            labelPoin.setText("20");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("Intern") && jenis.equals("Sekretatis/Bendahara")){
            labelPoin.setText("18");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("Intern") && jenis.equals("Koordinator Dept/Seksi")){
            labelPoin.setText("15");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("Intern") && jenis.equals("Anggota")){
            labelPoin.setText("10");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("DIY") && jenis.equals("Ketua")){
            labelPoin.setText("25");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("DIY") && jenis.equals("Wakil Ketua")){
            labelPoin.setText("22");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("DIY") && jenis.equals("Sekretatis/Bendahara")){
            labelPoin.setText("20");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("DIY") && jenis.equals("Koordinator Dept/Seksi")){
            labelPoin.setText("18");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("DIY") && jenis.equals("Anggota")){
            labelPoin.setText("12");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("Nasional") && jenis.equals("Ketua")){
            labelPoin.setText("32");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("Nasional") && jenis.equals("Wakil Ketua")){
            labelPoin.setText("28");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("Nasional") && jenis.equals("Sekretatis/Bendahara")){
            labelPoin.setText("25");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("Nasional") && jenis.equals("Koordinator Dept/Seksi")){
            labelPoin.setText("20");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("Nasional") && jenis.equals("Anggota")){
            labelPoin.setText("15");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("Internasional") && jenis.equals("Ketua")){
            labelPoin.setText("48");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("Internasional") && jenis.equals("Wakil Ketua")){
            labelPoin.setText("44");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("Internasional") && jenis.equals("Sekretatis/Bendahara")){
            labelPoin.setText("40");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("Internasional") && jenis.equals("Koordinator Dept/Seksi")){
            labelPoin.setText("35");
        }else if(organisasi.equals("Fakultas/Program Studi~1 Tahun(Kepengurusan)") && tingkat.equals("Internasional") && jenis.equals("Anggota")){
            labelPoin.setText("28");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("Intern") && jenis.equals("Ketua")){
            labelPoin.setText("8");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("Intern") && jenis.equals("Wakil Ketua")){
            labelPoin.setText("7");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("Intern") && jenis.equals("Sekretatis/Bendahara")){
            labelPoin.setText("6");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("Intern") && jenis.equals("Koordinator Dept/Seksi")){
            labelPoin.setText("5");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("Intern") && jenis.equals("Anggota")){
            labelPoin.setText("4");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("DIY") && jenis.equals("Ketua")){
            labelPoin.setText("12");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("DIY") && jenis.equals("Wakil Ketua")){
            labelPoin.setText("10");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("DIY") && jenis.equals("Sekretatis/Bendahara")){
            labelPoin.setText("8");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("DIY") && jenis.equals("Koordinator Dept/Seksi")){
            labelPoin.setText("7");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("DIY") && jenis.equals("Anggota")){
            labelPoin.setText("5");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("Nasional") && jenis.equals("Ketua")){
            labelPoin.setText("16");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("Nasional") && jenis.equals("Wakil Ketua")){
            labelPoin.setText("11");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("Nasional") && jenis.equals("Sekretatis/Bendahara")){
            labelPoin.setText("14");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("Nasional") && jenis.equals("Koordinator Dept/Seksi")){
            labelPoin.setText("9");
        }else if(organisasi.equals("FFakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("Nasional") && jenis.equals("Anggota")){
            labelPoin.setText("6");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("Internasional") && jenis.equals("Ketua")){
            labelPoin.setText("20");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("Internasional") && jenis.equals("Wakil Ketua")){
            labelPoin.setText("17");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("Internasional") && jenis.equals("Sekretatis/Bendahara")){
            labelPoin.setText("15");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("Internasional") && jenis.equals("Koordinator Dept/Seksi")){
            labelPoin.setText("12");
        }else if(organisasi.equals("Fakultas/Program Studi~<1 Tahun (Kepanitiaan)") && tingkat.equals("Internasional") && jenis.equals("Anggota")){
            labelPoin.setText("9");
        }
    }
    
}
