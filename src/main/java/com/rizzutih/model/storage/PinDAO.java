package com.rizzutih.model.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.sql.DataSource;
import com.rizzutih.model.Pin;

public class PinDAO {

	@Resource(name="dataSource")
	private DataSource datasource;
	private Connection conn;
	private PreparedStatement ps;

	public Pin getPin(String pinIn) throws SQLException{
		conn = datasource.getConnection();
		ps = conn.prepareCall("SELECT pin,user FROM pins WHERE pin = ?");
		ps.setString(1, pinIn);
		ResultSet rs = ps.executeQuery();
		Pin pinOut = null;
		try{
			if (rs.next()){
				pinOut = new Pin();
				pinOut.setPin(rs.getString("pin"));
				pinOut.setUser(rs.getString("user"));
			}
		}finally {
			if(ps!=null)ps.close();
			if (conn!=null)conn.close();
		}
		return pinOut;
	}
}
