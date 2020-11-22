package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BusController {
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
			
			pst = conn.prepareStatement("select * from buss where date =? and seatno=?");
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e) {
			System.out.println("SQL 실행 에러");
		} 
	}
	

    @FXML
    private TextField txtcustomer;

    @FXML
    private TextField txtprice;

    @FXML
    private DatePicker txtdate;

    @FXML
    private TextArea txtbill;

    @FXML
    void onClick1(ActionEvent event) {
    	seatno = 1;
    	JOptionPane.showMessageDialog(null, seatno);
    }

    @FXML
    void onClick10(ActionEvent event) {
    	seatno = 10;
    	JOptionPane.showMessageDialog(null, seatno);
    }

    @FXML
    void onClick11(ActionEvent event) {
    	seatno = 11;
    	JOptionPane.showMessageDialog(null, seatno);
    }

    @FXML
    void onClick12(ActionEvent event) {
    	seatno = 12;
    	JOptionPane.showMessageDialog(null, seatno);
    }

    @FXML
    void onClick2(ActionEvent event) {
    	seatno = 2;
    	JOptionPane.showMessageDialog(null, seatno);
    }

    @FXML
    void onClick3(ActionEvent event) {
    	seatno = 3;
    	JOptionPane.showMessageDialog(null, seatno);
    }

    @FXML
    void onClick4(ActionEvent event) {
    	seatno = 4;
    	JOptionPane.showMessageDialog(null, seatno);
    }

    @FXML
    void onClick5(ActionEvent event) {
    	seatno = 5;
    	JOptionPane.showMessageDialog(null, seatno);
    }

    @FXML
    void onClick6(ActionEvent event) {
    	seatno = 6;
    	JOptionPane.showMessageDialog(null, seatno);
    }

    @FXML
    void onClick7(ActionEvent event) {
    	seatno = 7;
    	JOptionPane.showMessageDialog(null, seatno);
    }

    @FXML
    void onClick8(ActionEvent event) {
    	seatno = 8;
    	JOptionPane.showMessageDialog(null, seatno);
    }

    @FXML
    void onClick9(ActionEvent event) {
    	seatno = 9;
    	JOptionPane.showMessageDialog(null, seatno);
    }

    @FXML
    void onClickBook(ActionEvent event) {
    	String customer = txtcustomer.getText();
    	int seat = seatno;
    	int price = Integer.parseInt(txtprice.getText());
    	
    	int year = (txtdate.getValue().getYear());
    	int month = (txtdate.getValue().getMonthValue());
    	int day = (txtdate.getValue().getDayOfMonth());
    	
    	String date = ""+year+"-"+month+"-"+day;
    	
    	System.out.println(seatno);
    	System.out.println(month);
    	
		try {
			pst.setString(1, date);
			pst.setInt(2, seat);
			srs = pst.executeQuery();
			
			if(srs.next()) {
				JOptionPane.showMessageDialog(null, "This seat is Already booked!");
			} else {
				pst = conn.prepareStatement("insert into buss (name, seatno, price,date) values (?,?,?,?)");
				pst.setString(1, customer);
				pst.setInt(2, seat);
				pst.setInt(3, price);
				pst.setString(4, date);
				int k =pst.executeUpdate();
				
				if(k==1) {
					JOptionPane.showMessageDialog(null, "Seat Booked!");
					bill();
				} else {
					JOptionPane.showMessageDialog(null, "Some error!");
				}

				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
    }
    
    public void bill() {
    	String customer = txtcustomer.getText();
    	int seat = seatno;
    	int price = Integer.parseInt(txtprice.getText());
    	
    	int year = (txtdate.getValue().getYear());
    	int month = (txtdate.getValue().getMonthValue());
    	int day = (txtdate.getValue().getDayOfMonth());
    	
    	String date = ""+year+"-"+month+"-"+day;
    	
    	txtbill.setText(txtbill.getText()+"***************************************************************\n");
    	txtbill.setText(txtbill.getText()+"****************    bill    ***********************************\n");
    	txtbill.setText(txtbill.getText()+"***************************************************************\n");
    	txtbill.setText(txtbill.getText()+"Customer" + "\t"+ customer + "\n");
    	txtbill.setText(txtbill.getText()+"SeatNo" + "\t"+ seat + "\n");
    	txtbill.setText(txtbill.getText()+"Prices" + "\t"+ price + "\n");
    	txtbill.setText(txtbill.getText()+"Date" + "\t"+ date + "\n\n");
    	txtbill.setText(txtbill.getText()+"***************************************************************\n");
    	txtbill.setText(txtbill.getText()+"****************    Thank you Come Again***********************\n");
    }

}
