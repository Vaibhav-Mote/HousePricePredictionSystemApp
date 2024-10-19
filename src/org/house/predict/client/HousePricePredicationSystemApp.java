package org.house.predict.client;

import java.util.*;

import org.house.predict.model.AminityModel;
import org.house.predict.model.AreaMasterModel;
import org.house.predict.model.AreaSquareModel;
import org.house.predict.model.DealModel;
import org.house.predict.model.PropertyModel;
import org.house.predict.model.cityMasterModel;
import org.house.predict.model.cityWiseAreaCount;
import org.house.predict.service.AminityService;
import org.house.predict.service.AreaSquareFeetService;
import org.house.predict.service.CityMasterService;
import org.house.predict.service.PredictionService;
import org.house.predict.service.PropertyService;

public class HousePricePredicationSystemApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CityMasterService csm = new CityMasterService();
		AreaSquareFeetService areaSq=new AreaSquareFeetService();
		AminityService aminityService=new AminityService();
		PropertyService PropService=new PropertyService();
		PredictionService predService=new PredictionService();
		do {
			System.out.println("1:Add new city");
			System.out.println("2:Show All Cites");
			System.out.println("3:Add New Bulk Cities");
			System.out.println("4:Add new Area");
			System.out.println("5:Show cityWise AreaCount");
			System.out.println("6:Show cityWise AreaName");
			System.out.println("7:Delete city ");
			System.out.println("8:Delete Area");
			System.out.println("9:Update city Name");
			System.out.println("10:Enter the square feet");
			System.out.println("11:Enter the Aminities:");
			System.out.println("12:Enter the property in  database:");
			System.out.println("13:Show AreaWise property count");
			System.out.println("14:Predicted price");
			System.out.println("Enter your choice");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter city name:");
				sc.nextLine();
				String cityname = sc.nextLine();
				cityMasterModel model = new cityMasterModel();
				model.setCityname(cityname);
				boolean b = csm.isAddCity(model);
				if (b) {
					System.out.println("new city added in database");
				} else {
					System.out.println("city not added");
				}

				break;
			case 2:
				List<cityMasterModel> list=csm.getAllCities();
				if(list!=null) {
					System.out.println("=================");
					System.out.println("CityId"+"\t "+"CityName");
					list.forEach((m)->System.out.println(m.getCityid()+"\t"+m.getCityname()));
					System.out.println("=================");
				}
				else {
					System.out.println("city not present in database table");
				}
				break;
			case 3:
				 b=csm.isBulkAddCities();
				if(b)System.out.println("Bulk file Added Successfully...");
				else System.out.println("Bulk file not Added");
				break;
				
			case 4:
				sc.nextLine();
				System.out.println("Enter city name");
				cityname=sc.nextLine();
				int cityId=csm.getcityid(cityname);
				if(cityId!=-1) {
					System.out.println("Enter area name");
					String areaname=sc.nextLine();
					int areaid=csm.getAreaIdAtomatic();
					AreaMasterModel amodel=new AreaMasterModel();
					amodel.setCityid(cityId);
					amodel.setAreaId(areaid);
					amodel.setAreaName(areaname);
					b=csm.isAddArea(amodel);
					if(b)System.out.println("Area Added Successfully");
					else System.out.println("Area not Added");
					
				}else {
					System.out.println("City is not present in database");
					System.out.println("Do you want to add city in database");
					String msg=sc.next();
					if(msg.equalsIgnoreCase("yes")) {
						model = new cityMasterModel();
						model.setCityname(cityname);
						b = csm.isAddCity(model);
						if (b) {
							System.out.println("new city added in database");
						} else {
							System.out.println("city not added");
						}

					}else {
						System.out.println("Thank You");
					}
				}
				break;
				
			case 5:
				List <cityWiseAreaCount> list1=csm.cityWiseAreaCount();
				if(list1!=null) {
				System.out.println("======City Wise Area Count=======");
				System.out.print("CityName"+"\t"+"Areacount\n");
				list1.forEach((m)->System.out.println(m.getCityname()+"\t\t"+m.getAreacount()));
				System.out.println("=================================");
				}
				else {
					System.out.println("no any area in every city");
				}
				break;
				
				
			case 6:
				LinkedHashMap <String ,ArrayList<String>> map=csm.getCityWiseAreaName();
				Set<Map.Entry<String,ArrayList<String>>> mset=map.entrySet();
				if(mset.size()>0) {
			    for(Map.Entry<String, ArrayList<String>> m:mset) {
			    	cityname=m.getKey();
			    	System.out.println("======CityName:"+cityname+"=======");
			    	ArrayList <String>al=m.getValue();
			    	int i=1;
			    	for(String s:al) {
			    		System.out.println((i++)+"  "+s);
			    	}
			    	
			    	System.out.println();
			    }
				}
				else {
					System.out.println("No any area in every city");
				}
				
				break;
				
			case 7:
				System.out.println("Enter ciyt name:");
				sc.nextLine();
				cityname=sc.nextLine();
				b=csm.isDeleteCityByName(cityname);
				if(b)System.out.println("City is Deleted Sucessfully");
				else System.out.println("City is not Available or not Deleted");
				break;
				
			case 8:
				sc.nextLine();
				System.out.println("Enter city name");
				cityname=sc.nextLine();
				 cityId=csm.getcityid(cityname);
				 if(cityId!=-1) {
					 System.out.println("Enter the area name:");
					 String areaname=sc.nextLine();
					 b=csm.isDeleteAreaInCity(areaname,cityId);
					 if(b)System.out.println("Area is Deleted Sucessfully");
						else System.out.println("Area is not Available or not Deleted");
				 }
				 else {
					  System.out.println("Area is not Available or not Deleted");

				 }
				 break;
				
			case 9:
				sc.nextLine();
				System.out.println("Enter the Old cityName :");
				cityname=sc.nextLine();
				System.out.println("Enter the Old cityName :");
				String newcityname=sc.nextLine();
				b=csm.isUpdateCity(cityname, newcityname);
				if(b)System.out.println("City is updated Sucessfully......");
				else System.out.println("City is not Updated");
				break;
				
				
			case 10:
				System.out.println("Enter area value in SquareFeet:");
				int sqFeet=sc.nextInt();
				AreaSquareModel  areasqmodel=new AreaSquareModel();
				areasqmodel.setSquateFeet(sqFeet);
				b=areaSq.isAddAreaSquareFeet(areasqmodel);
				if(b) System.out.println("Square Feet is Added in database table\n");
				else System.out.println("Some problems is there");
				break;
				
				
			case 11:
				sc.nextLine();
	           System.out.println("Enter the aminity name:");
	           String aminity=sc.nextLine();
	           AminityModel amodel=new AminityModel();
	           amodel.setName(aminity);
	           b=aminityService.isAddAminity(amodel);
	           if(b)System.out.println("new Aminity is Added in Database");
	           else System.out.println("No Aminity Added");
				break;
			case 12:
				sc.nextLine();
				System.out.println("Enter the cityName:");
				cityname=sc.nextLine();
				System.out.println("Enter the AreaName");
				String AreaName=sc.nextLine();
				System.out.println("Enter the Address of Property");
				String Address=sc.nextLine();
				System.out.println("Enter the land Area:");
				int landArea=sc.nextInt();
				System.out.println("Enter the Number Of Bed and Number of Bath");
				int nbed=sc.nextInt();
				int nbath=sc.nextInt();
				sc.nextLine();
				cityId=csm.getcityid(cityname);
				AreaMasterModel m=new AreaMasterModel();
				m.setAreaName(AreaName);
				m.setCityname(cityname);
				int areaId=csm.getAreaId(m);
				int sqid=areaSq.getSquareFeetID(landArea);
				if(sqid==-1) {
					System.out.println("There is some problems is there");
				}else if(sqid==0){
					System.out.println("square area not present do you want to add new area");
					String str=sc.nextLine();
					if(str.equals("yes")) {
					  areasqmodel=new AreaSquareModel();
						areasqmodel.setSquateFeet(landArea);
						b=areaSq.isAddAreaSquareFeet(areasqmodel);
						if(b) {
							System.out.println("Square Feet is Added in database table\n");
							sqid=areaSq.getSquareFeetID(landArea);
						}
						else {
							System.out.println("Some problems is there");
						}
					}
					
				}else {
				List<AminityModel> aminityList=new ArrayList();
				String str="";
				do {
					
					System.out.println("Enter the aminity Name:");
					String aname=sc.nextLine();
					AminityModel ammodel =new AminityModel();
					int AminityId=new AminityService().getAminityId(aname);
					ammodel.setName(aname);
					ammodel.setId(AminityId);
					aminityList.add(ammodel);
					System.out.println("do you want to add more aminities");
				 str=sc.nextLine();
				}while(str.equals("yes"));
			
				PropertyModel pm =new PropertyModel();
				pm.setAreamodel(m);
				pm.setName(Address);
				pm.setNbed(nbed);
				pm.setNbath(nbath);
				pm.setAreaId(areaId);
				pm.setCityId(cityId);
				areasqmodel =new AreaSquareModel();
				areasqmodel.setSquateFeet(landArea);
				areasqmodel.setId(sqid);
				pm.setSqmodel(areasqmodel);
				pm.setList(aminityList);
				System.out.println("Enter the price Registry");
				int price=sc.nextInt();
				String date =sc.nextLine();
				DealModel dmodel=new DealModel();
				dmodel.setPrice(price);
			     pm.setDealmodel(dmodel);
				b=PropService.isAddNewProperty(pm);
				if(b)System.out.println("Property Added successfully");
				else System.out.println("Property not added");
				}
				break;
				
			case 13:
				System.out.println("Enter the cityname:");
				sc.nextLine();
				cityname=sc.nextLine();
				List <Object[]>propCountList=PropService.getAreaWisePropertyCount(cityname);
				System.out.println("Area Name"+"\t"+"Property Count");
				for(Object obj[]:propCountList) {
					System.out.println(obj[0]+"\t\t"+obj[1]);
				}
				System.out.println("========================================");
				break;
				
			case 14:
				System.out.println("Enter city name:");
				sc.nextLine();
				cityname=sc.nextLine();
				List <String>citywiseAreas=csm.getAllAreasCityWise(cityname);
				System.out.println("====Areas Name======");
				for(String c:citywiseAreas) {
					System.out.println(c);
				}
				System.out.println("Enter area name");
				String areaName=sc.nextLine();
				System.out.println("Enter land excepted land size");
				int landSize=sc.nextInt();
				int predictedPrice=predService.getPredictedPrice(landSize);
				System.out.println("Your predicted price is :"+predictedPrice);
				
				 break;
			default:
				System.out.println("wrong choice");
			}
			

		} while (true);

	}

}
