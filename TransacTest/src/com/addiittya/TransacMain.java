package com.addiittya;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.sql.PreparedStatement;

public class TransacMain {

	public static void main(String[] args) throws IOException, ClassNotFoundException , FileNotFoundException, URISyntaxException{

		Connection conn = null;
		
		try{
			String drvr_class_name = null;
			String conn_string = null;
			String uname = null;
			String pass = null;

//			FileInputStream fp = new FileInputStream("/Users/addiittya/Develop/Eclipse WS/JWSP/TransacTest/src/com/addiittya/jdbc_transac.properties");
			URL url = TransacMain.class.getClassLoader().getResource("com/addiittya/jdbc_transac.properties");
//			System.out.println(url.toURI().getSchemeSpecificPart());
			FileInputStream fp = new FileInputStream(url.toURI().getSchemeSpecificPart());
			
			Properties p = new Properties();
			p.load(fp);
			fp.close();

			Set<Map.Entry<Object, Object>> s = p.entrySet();
			Iterator<Map.Entry<Object, Object>> itr = s.iterator();
			while (itr.hasNext()) {
				Map.Entry<Object, Object> m = itr.next();
				if (m.getKey().equals("jdbc_driver")) {
					drvr_class_name = (String) m.getValue();
				}
				if(m.getKey().equals("jdbc_url")){
					conn_string = (String) m.getValue();
				}
				if(m.getKey().equals("jdbc_user")){
					uname = (String) m.getValue();
				}
				if(m.getKey().equals("jdbc_pass")){
					pass = (String) m.getValue();
				}
			}
			Class.forName(drvr_class_name);
			conn = DriverManager.getConnection(conn_string, uname, pass);
			
			PreparedStatement stmt1 = conn.prepareStatement("update account set balance = balance+? where name = ?");
			PreparedStatement stmt2 = conn.prepareStatement("update account set balance = balance-? where name = ?");
			
			Scanner read = new Scanner(System.in);
			
//			Validating with accno
//			System.out.println("Enter Source:");
//			int src = read.nextInt();
//			System.out.println("Enter Target:");
//			int target = read.nextInt();
//			System.out.println("Enter Amount:");
//			int amount = read.nextInt();
			
//			Validating with Name
			System.out.println("Enter Source Name:");
			String src = read.nextLine();
			System.out.println("Enter Target Name:");
			String tar = read.nextLine();
			System.out.println("Enter Amount:");
			int amount = read.nextInt();
			
			conn.setAutoCommit(false);
			
			stmt1.setInt(1, amount);
//			stmt1.setInt(2, target);
			stmt1.setString(2, tar);
			int j = stmt1.executeUpdate();
			
			stmt2.setInt(1, amount);
//			stmt2.setInt(2, src);
			stmt2.setString(2, src);
			int l = stmt2.executeUpdate();
			
			conn.commit();
			
			if(j==1 && l==1)
				System.out.println("Transaction Successful!");
//			System.out.println(j+"    "+l);
			
			read.close();
			conn.close();
			
//			System.out.println("Transaction Successful!");
		}catch(SQLException e){
			System.out.println("Transaction Failed");
			try{
				System.out.println("Transaction Rolling Back");
				conn.rollback();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		catch(ClassNotFoundException ce){
			ce.printStackTrace();
		}
	}
}
