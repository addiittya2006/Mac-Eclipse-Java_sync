package com.addiittya;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CallTest {

	public static void main(String[] args) {

		Connection conn = null;

		try {
			String drvr_class_name = null;
			String conn_string = null;
			String uname = null;
			String pass = null;

			URL url = CallTest.class.getClassLoader().getResource("com/addiittya/jdbcfile.properties");
			FileInputStream fp = new FileInputStream(url.toURI().getSchemeSpecificPart());

			Properties p = new Properties();
			p.load(fp);

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

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			CallableStatement stmp = conn.prepareCall("{call getStuName(?, ?)}");
			CallableStatement stmf = conn.prepareCall("{ ?= call jdbc_test.getstufun(?)}");

			System.out.println("Enter Batch");
			String bat = String.valueOf(br.readLine());

			stmp.setString(1, bat);
			stmp.registerOutParameter(2, Types.VARCHAR);

			stmf.setString(2, bat);
			stmf.registerOutParameter(1, Types.VARCHAR);
			
			boolean j = stmp.execute();
			boolean k = stmf.execute();
			
			if(j==false && k==false){
				System.out.println("Statement Execeuted!\nResults:");
			}

			System.out.println(stmp.getString(2)+" <=1 and 2=> "+stmf.getString(1));

			br.close();
			stmp.close();
			conn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
