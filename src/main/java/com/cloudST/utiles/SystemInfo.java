package com.cloudST.utiles;

public class SystemInfo {

	
	public static String pathStore(){
			if(OS().contains("Windows")){
				return "C://temp//";
			}
			return "//home//pi//CloudSTtemp//";
		}
	public static String path(){
		if(OS().contains("Windows")){
			return "C:\\";
		}
		return "//home//";
	}

	
	private static String OS(){
		return System.getProperty("os.name");
	}
}