package org.house.predict.repository;

import org.house.predict.config.DBHealper;
import org.house.predict.model.AreaSquareModel;

public class AreaSquareFeetRepository extends DBHealper {
	public boolean isAddAreaSquareFeet(AreaSquareModel model) {
		try {
			stmt=conn.prepareStatement("insert into areasquarefeet values('0',?)");
			stmt.setInt(1, model.getSquateFeet());
			int value=stmt.executeUpdate();
			return value>0?true:false;
			
		}
		catch(Exception ex) {
			System.out.println("Error is:"+ex);
			return false;
		}
		
		
	}
	
	public int getSquareFeetID(int sqFeet) {
		try {
			
			stmt=conn.prepareStatement("select sqid from areaSquareFeet where areainfeet=?");
			stmt.setInt(1, sqFeet);
			rs=stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);		
			}
			else {
				return 0;
			}
			
			
		}catch(Exception ex){
			System.out.println("Error is:"+ex);
			return -1;
			
		}
		
		
		
	}

}
