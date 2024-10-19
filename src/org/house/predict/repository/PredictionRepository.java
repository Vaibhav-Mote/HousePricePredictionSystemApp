package org.house.predict.repository;

import java.util.ArrayList;
import java.util.List;

import org.house.predict.config.DBHealper;

public class PredictionRepository extends DBHealper{
	
	public int getminOfX() {
		try {
			stmt=conn.prepareStatement("select avg(areainfeet) from areasquarefeet");
			stmt.executeQuery();
			rs=stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			else {
				return rs.getInt(2);
			}
		}catch(Exception ex) {
			return 0;
		}
	}
	
	public int getminOfY() {
		try {
			stmt=conn.prepareStatement("select avg(price)from propertyhistoricalprice");
			stmt.executeQuery();
			rs=stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			else {
				return rs.getInt(2);
			}		}catch(Exception ex) {
			System.out.println("Error is:"+ex);
			return 0;
		}
	}
	public List<Integer> getValueOfX(){
		try {
			List<Integer>list=new ArrayList<Integer>();
			stmt=conn.prepareStatement("select areainfeet From areasquarefeet");
			rs=stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt(1));
			}
			return list!=null?list:null;
		}catch(Exception ex) {
			return null;
		}
	}
	
	
	public List<Integer> getValueOfY(){
		try {
			List<Integer>list=new ArrayList<Integer>();
			stmt=conn.prepareStatement("select price from propertyhistoricalprice");
			rs=stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt(1));
			}
			return list!=null?list:null;
		}catch(Exception ex) {
			return null;
		}
	}

}

	


