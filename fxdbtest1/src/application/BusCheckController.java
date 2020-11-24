package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BusCheckController {
	Connection conn;
	PreparedStatement pst = null;
	ResultSet srs;
	int seatno = 0;
	
	@FXML
	private void initialize() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb?serverTimezone=UTC", "root","brd901as-kim");
			System.out.println("DB 연결 완료");
			
	
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e) {
			System.out.println("SQL 실행 에러");
		} 
	}
    @FXML
    private TableView<users> tableContent;
    @FXML
    private TableColumn<users, String> col_name;

    @FXML
    private TableColumn<users, Integer> col_seat;

    @FXML
    private TableColumn<users, Integer> col_price;

    @FXML
    private TableColumn<users, String> col_date;

    @FXML
    private DatePicker txtdate;

    @FXML
    void onClickSearch(ActionEvent event) {
    	int year = (txtdate.getValue().getYear());
    	int month = (txtdate.getValue().getMonthValue());
    	int day = (txtdate.getValue().getDayOfMonth());
    	String date = ""+year+"-"+month+"-"+day;
    	ObservableList<users> list = FXCollections.observableArrayList();
    	
		try {
			pst = conn.prepareStatement("select * from buss where date =?");
			pst.setString(1, date);
			
			srs = pst.executeQuery();
			if(!srs.next()) {
				JOptionPane.showMessageDialog(null, "No Seat Booked!");				
			} else 
			{				
				ResultSetMetaData rsd = srs.getMetaData();
				int c = rsd.getColumnCount();
				
				while(srs.next()) {
					String r1 = srs.getString("name");
					int r2 = srs.getInt("seatno");
					int r3 = srs.getInt("price");
					String r4 = srs.getString("date");
					
					System.out.println(r1);
					System.out.println(r2);
					System.out.println(r3);
					System.out.println(r4);
					
					list.add(new users(r1, r2,r3,r4));
					
					col_name.setCellValueFactory(new PropertyValueFactory<users,String>("name"));
					col_seat.setCellValueFactory(new PropertyValueFactory<users,Integer>("seatno"));
					col_price.setCellValueFactory(new PropertyValueFactory<users,Integer>("price"));
					col_date.setCellValueFactory(new PropertyValueFactory<users,String>("date"));
					
					tableContent.setItems(list);
				}								
			}																
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /*
     *     @FXML
    void onClickSearch(ActionEvent event) {
    	int year = (txtdate.getValue().getYear());
    	int month = (txtdate.getValue().getMonthValue());
    	int day = (txtdate.getValue().getDayOfMonth());
    	String date = ""+year+"-"+month+"-"+day;
    	
		try {
			pst = conn.prepareStatement("select * from buss where date =?");
			pst.setString(1, date);
			
			srs = pst.executeQuery();
			if(!srs.next()) {
				JOptionPane.showMessageDialog(null, "No Seat Booked!");				
			} else 
			{
				ResultSetMetaData rsd = srs.getMetaData();
				int c = rsd.getColumnCount();
				DefaultTableModel d = (DefaultTableModel)TableView.getClassCssMetaData();
				d.setRowCount(0);
				
				while(srs.next()) {
					Vector v2 = new Vector();
					for(int i = 1; i<c;i++)
					{
						v2.add(srs.getString("name"));
						v2.add(srs.getInt("setno"));
						v2.add(srs.getInt("price"));
						v2.add(srs.getString("date"));						
					}
					d.addRow(v2);										
				}								
			}											
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
     */
}
