package org.house.predict.repository;

import java.util.ArrayList;
import java.util.List;

import org.house.predict.config.DBHealper;
import org.house.predict.model.AminityModel;
import org.house.predict.model.DealModel;
import org.house.predict.model.PropertyModel;

public class PropertyRepository extends DBHealper {
	int pid;
	private List<Object[]>areawisecount;

	public int getPropertyId() {

		try {
			stmt = conn.prepareStatement("select max(pid)from propertymaster");
			rs = stmt.executeQuery();
			if (rs.next()) {
				pid = rs.getInt(1);
			}
			return ++pid;
		} catch (Exception ex) {
			System.out.println("Errot is :" + ex);
			return 0;
		}

	}

	public boolean isAddNewProperty(PropertyModel model) {
		pid = this.getPropertyId();
		String propertyName = model.getName();
		int sqId = model.getSqmodel().getId();
		int areaId = model.getAreaId();
		int cityId = model.getCityId();
		int nbed = model.getNbed();
		int nbath = model.getNbath();

//	System.out.println("property master");
//	System.out.println((pid+1)+"\t"+propertyName+"\t"+sqId+"\t"+areaId+"\t"+cityId+"\t"+nbed+"\t"+nbath);
		try {
			stmt = conn.prepareStatement("insert into propertymaster values(?,?,?,?,?,?,?)");
			stmt.setInt(1, pid);
			stmt.setString(2, propertyName);
			stmt.setInt(3, sqId);
			stmt.setInt(4, areaId);
			stmt.setInt(5, cityId);
			stmt.setInt(6, nbed);
			stmt.setInt(7, nbath);
			int value = stmt.executeUpdate();
			if (value > 0) {
				List<AminityModel> list = model.getList();
				int count = 0;
				for (AminityModel m : list) {
					stmt = conn.prepareStatement("insert into propertyaminityjoin values(?,?)");
					stmt.setInt(1, pid);
					stmt.setInt(2, m.getId());
					value = stmt.executeUpdate();
				}
				DealModel dealmodel = model.getDealmodel();
				int price = dealmodel.getPrice();
				stmt = conn.prepareStatement("insert into propertyhistoricalprice values('0',?,?,(curDate()))");
				stmt.setInt(1, pid);
				stmt.setInt(2, price);
				value = stmt.executeUpdate();
				if (value > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				System.out.println("some problems is there.....");
				return false;
			}
		} catch (Exception ex) {
			System.out.println("Error is:" + ex);
			return false;
		}
	}
	
	public List<Object[]>getAreaWisePropertyCount(String cityname)
	{
		try {
			this.areawisecount=new ArrayList<Object[]>();
			stmt=conn.prepareStatement("select a.areaname,count(p.pid) from areamaster a inner join propertymaster p on a.aid=p.aid inner join citymaster c on c.cityid=p.cityid where cityname=? group by areaname");
			stmt.setString(1, cityname);
			rs=stmt.executeQuery();
			while(rs.next()) {
				Object obj[]=new Object[] {rs.getString(1),rs.getInt(2)};
				this.areawisecount.add(obj);
			}
			return areawisecount;
			
		}catch (Exception ex) {
			System.out.println("Error is:"+ex);
			return null;
		}
		
	}
	
	
}
