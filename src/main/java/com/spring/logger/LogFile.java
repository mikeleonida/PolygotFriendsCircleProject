package com.spring.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFile {
	private static String fileName = "UserLog.txt";
	
	public static void log(String message) {
		File file = new File(fileName);
		FileWriter fr = null;
		try {
			fr = new FileWriter(file, true);
			
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy h:mmaa");
			fr.append(message + " at " + formatter.format(date) + "\n");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
