package org.house.predict.repository;

import org.house.predict.config.DBHealper;
import org.house.predict.model.AminityModel;

public class AminityRepository extends DBHealper{
	public boolean isAddAminity(AminityModel model) {
		
		try {
			stmt=conn.prepareStatement("insert into aminitymaster values('0',?)");
			stmt.setString(1, model.getName());
			int value=stmt.executeUpdate();
			return value>0?true:false;
		}
		catch(Exception ex) {
			System.out.println("Error is:"+ex);
			return false;
			
		}
	}
	
	public int getAminityId(String aminityName) {
		try {
			stmt=conn.prepareStatement("select amid from aminitymaster where aminityname=?");
			stmt.setString(1, aminityName);
			rs=stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
		}
		catch(Exception ex) {
			System.out.println("Error is:"+ex);
			return 0;
		}
		
		
		
	}

}
