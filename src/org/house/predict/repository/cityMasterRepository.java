package org.house.predict.repository;

import org.house.predict.config.DBHealper;
import org.house.predict.config.PathHealper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.house.predict.config.DBConfig;
import org.house.predict.model.AreaMasterModel;
import org.house.predict.model.cityMasterModel;
import org.house.predict.model.cityWiseAreaCount;
import com.mysql.cj.jdbc.CallableStatement;

public class cityMasterRepository extends DBHealper {
	
	int areaid = 0;

	public boolean isAddNewCity(cityMasterModel model) {
		try {
			stmt = conn.prepareStatement("insert into citymaster values ('0',?)");
			stmt.setString(1, model.getCityname());
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;
		} catch (Exception ex) {
			System.out.println("Error is->:" + ex);
			return false;
		}
	}

	public List<cityMasterModel> getAllCities() {
	 List<cityMasterModel> list = new ArrayList<cityMasterModel>();
		try {
			stmt = conn.prepareStatement("select *from citymaster");
			rs = stmt.executeQuery();
			while (rs.next()) {
				cityMasterModel model = new cityMasterModel();
				model.setCityid(rs.getInt(1));
				model.setCityname(rs.getString(2));
				list.add(model);
			}
			return list.size() > 0 ? list : null;
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return null;
		}
	}

	public boolean isBulkAddCities() {
		try {
			FileReader fr = new FileReader(PathHealper.path + "city.csv");
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			int value = 0;
			while ((line = br.readLine()) != null) {
				String data[] = line.split(",");
				stmt = conn.prepareStatement("insert into citymaster values ('0',?)");
				stmt.setString(1, data[1]);
				value = stmt.executeUpdate();
			}
			return value > 0 ? true : false;
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return false;
		}
	}

	public int getcity(String name) {
		try {
			stmt = conn.prepareStatement("select cityid from citymaster where cityname=?");
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return -1;
		}
	}

	public int getAreaIdAtomatic() {
		try {
			stmt = conn.prepareStatement("select max(aid)from areamaster");
			rs = stmt.executeQuery();
			if (rs.next()) {
				this.areaid = rs.getInt(1);
			}
			++areaid;
			return this.areaid;
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return 0;
		}
	}

	public boolean isAddArea(AreaMasterModel model) {
		try {
			CallableStatement cstmt = (CallableStatement) conn.prepareCall("{call savearea(?,?,?)}");
			cstmt.setInt(1, model.getAreaId());
			cstmt.setString(2, model.getAreaName());
			cstmt.setInt(3, model.getCityid());
			boolean b = cstmt.execute();
			return !b;
		} catch (Exception ex) {
			System.out.println("Error is" + ex);
			return false;
		}
	}

	public List<cityWiseAreaCount> cityWiseAreaCount() {
		try {
			List<cityWiseAreaCount> list1 = new ArrayList<cityWiseAreaCount>();
			stmt = conn.prepareStatement(
					"select c.cityname as 'CityName',count(a.areaname) as 'Area Count'from citymaster c inner join cityareajoin caj"
							+ " on c.cityid=caj.cityid inner join areamaster a on a.aid=caj.aid group by cityname");
			rs = stmt.executeQuery();
			while (rs.next()) {
				cityWiseAreaCount ca = new cityWiseAreaCount();
				ca.setCityname(rs.getString(1));
				ca.setAreacount(rs.getInt(2));
				list1.add(ca);
			}
			return list1.size() > 0 ? list1 : null;
		} catch (Exception ex) {
			System.out.println("Exception is:" + ex);
			return null;
		}
	}

	public LinkedHashMap getCityWiseAreaName() {
		try {
			LinkedHashMap<String, ArrayList<String>> map = new LinkedHashMap<String, ArrayList<String>>();
			stmt = conn.prepareStatement(
					"select c.cityname as'CityName' ,group_concat(a.areaname) as'AreaNames' from citymaster c inner join cityareajoin"
							+ " caj on c.cityid=caj.cityid inner join areamaster a on a.aid=caj.aid group by c.cityname");
			rs = stmt.executeQuery();
			while (rs.next()) {
				ArrayList<String> al = new <String>ArrayList();
				String s = rs.getString(2);
				String areaname[] = s.split(",");
				for (int i = 0; i < areaname.length; i++) {
					al.add(areaname[i]);
				}
				map.put(rs.getString(1), al);
			}
			return map;
		} catch (Exception ex) {
			System.out.println("Error is:" + ex);
			return null;
		}
	}

	public boolean isDeleteCityByName(String cityname) {
		try {
			stmt = conn.prepareStatement("delete from citymaster where cityname =?");
			stmt.setString(1, cityname);
			int value = stmt.executeUpdate();
			return value > 0 ? true : false;
		} catch (Exception ex) {
			System.out.println("Error is:" + ex);
			return false;
		}
	}

	public boolean isDeleteAreaInCity(String areaname, int cityId) {
		try {
			stmt = conn.prepareStatement(
					"select c.cityid ,a.aid,a.areaname from citymaster c inner join cityareajoin caj on "
							+ "c.cityid=caj.cityid inner join areamaster a on a.aid=caj.aid");
			rs = stmt.executeQuery();
			int value = 0;
			while (rs.next()) {
				if (rs.getInt(1) == cityId && rs.getString(3).compareToIgnoreCase(areaname) == 0) {
					stmt = conn.prepareStatement("delete from areamaster where aid=?");
					stmt.setInt(1, rs.getInt(2));
					value = stmt.executeUpdate();
				}
			}
			return value != 0 ? true : false;
		} catch (Exception ex) {
			System.out.println("Error is " + ex);
			return false;
		}
	}
	
	public boolean isUpdateCity(String oldCityname,String newCityname) {
		try {
			int value=0;
			stmt=conn.prepareStatement("update citymaster set cityname=? where cityname=?");
			stmt.setString(1, newCityname);
			stmt.setString(2, oldCityname);
			value=stmt.executeUpdate();
			return value!=0?true:false;
		}
		catch(Exception ex){
			System.out.println("Error is:"+ex);
			return false;
		}
		
	}
	
	public int getAreaId(AreaMasterModel model) {
		try {
			stmt=conn.prepareStatement("select a.aid from areamaster a inner join cityareajoin "
+ "caj on a.aid=caj.aid inner join citymaster cm on caj.cityid=cm.cityid where areaname=? and cityname=?");
			stmt.setString(1, model.getAreaName());
			stmt.setString(2, model.getCityname());
			rs=stmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}else {
				return 0;
			}
			
			
		}catch(Exception ex) {
			System.out.println("Error is:"+ex);
			return -1;
		}
		
	}
	
	public List<String> getAllAreasCityWise(String cityname){
		try {
			 List <String>list = new ArrayList<String>();
			
			stmt=conn.prepareStatement("select c.cityname , a.areaname  from citymaster c inner join cityareajoin caj on c.cityid=caj.cityid inner join areamaster a on a.aid=caj.aid where cityname=?");
			stmt.setString(1, cityname);
			rs=stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(2));
			}
			return list!=null?list:null;
			
		}catch(Exception ex) {
			System.out.println("Error is:"+ex);
			return null;
		}
		
	}
	
	
	
}
