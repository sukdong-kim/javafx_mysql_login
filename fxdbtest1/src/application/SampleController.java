package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class SampleController {
	Connection conn;
	PreparedStatement pst = null;
	ResultSet srs;
	
	@FXML
	private void initialize() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb?serverTimezone=UTC", "root","brd901as-kim");
			System.out.println("DB 연결 완료");
			
			pst = conn.prepareStatement("select * from student where id =? and name=?");
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 에러");
		} catch (SQLException e) {
			System.out.println("SQL 실행 에러");
		} 
	}
	
    @FXML
    private TextField tf1;

    @FXML
    private PasswordField tf2;

    @FXML
    void onClickb1(ActionEvent event) {
    	String uname = tf1.getText();
    	String pass = tf2.getText();
    	
    	if(uname.equals("") && pass.equals(""))
    	{
    		JOptionPane.showMessageDialog(null, "UserName or Password Blank");    		    		
    	} else 
    	{
			try {
				pst.setString(1, uname);
				pst.setString(2, pass);
				
				srs = pst.executeQuery();
				if(srs.next()) {
					JOptionPane.showMessageDialog(null, "Login Success");  
				} else
				{
					JOptionPane.showMessageDialog(null, "Login Failed");  
					tf1.setText("");
					tf2.setText("");
					tf1.requestFocus();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }
}

