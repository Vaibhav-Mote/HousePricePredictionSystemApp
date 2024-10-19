package org.house.predict.config;
import java.sql.*;
public class DBHealper {
	protected DBConfig db=DBConfig.getInstance();
	protected Connection conn=DBConfig.getConnection();
	protected PreparedStatement stmt=DBConfig.getStatement();
	protected ResultSet rs=DBConfig.getResult();
}
