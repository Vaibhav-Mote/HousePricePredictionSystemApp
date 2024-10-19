package org.house.predict.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PathHealper {
	public static FileInputStream finf=null;
	public static File f=new File("");
	public static String path=f.getAbsolutePath()+"\\src\\";
	static {
	
	String path1=path+"db.properties";	
	try {
		finf=new FileInputStream(path1);
	} catch (Exception e) {
		System.out.println("Error is:"+e);
	}
	
	}
}
