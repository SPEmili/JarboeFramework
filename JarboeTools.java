package jarboe;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class JarboeTools {
	private static String defaultFile;
	
	public static void write(String dir, String data)
	{
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(dir));
		} catch (IOException whoops) {}
		try {
		      writer.write(data);
		      writer.close();
		} catch (IOException e) {}
	}
	
	public static void qwrite(String data)
	{
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(defaultFile));
		} catch (IOException whoops) {}
		try {
		      writer.write(data);
		      writer.close();
		} catch (IOException e) {}
	}
	
	public static void setFile(String x)
	{
		if (checkFile(x))
			defaultFile = x;
		else
			System.out.println(JarboeANSI.RED + "ERROR - File not found" + JarboeANSI.RESET);
	}
	
	public static String read(String dir)
	{
		String datiCaricati="";
		try
		    {datiCaricati = Files.readString(new File(dir).toPath());}
		catch (IOException e) {}
		return datiCaricati;
	}
	
	public static String qread()
	{
		return read(defaultFile);
	}
	
	public static boolean checkFile(String dir)
	{
		File f = new File(dir);
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		} else {
			return false;
		}
	}
	
	public static boolean checkFolder(String dir)
	{
		File f = new File(dir);
		if(f.exists() && f.isDirectory()) { 
		    return true;
		} else {
			return false;
		}
	}
}
