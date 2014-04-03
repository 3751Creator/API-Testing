package fr.updater.main;

import fr.update.util.Info;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.zip.ZipInputStream;

/**
 * User: Alexandre
 */
public class Download implements Info{


	private static Scanner Scanner_VersionLocal, Scanner_FichierMods;
	private static int Int_VersionLocal = 0;
	private static String Load, String_FichierMC;
	public static String[] Content;

	public static void preDownload(){

		try{
			Scanner_VersionLocal = new Scanner(File_VersionLocal);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		Int_VersionLocal = Scanner_VersionLocal.nextInt();

		try{
			Scanner_FichierMods = new Scanner(File_FichierMods);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		String_FichierMC = Scanner_FichierMods.nextLine();
		System.out.println(String_FichierMC);


		DocumentBuilderFactory builderfactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder dBuilder = null;
		try{
			dBuilder = builderfactory.newDocumentBuilder();
			Document doc = dBuilder.parse(File_Update);
			doc.normalize();

			NodeList rootUpdate = doc.getElementsByTagName("update");
			Node NodeUpdate = rootUpdate.item(0);

			Element rootVersion = (Element)NodeUpdate;
			NodeList NodeVersion = rootVersion.getElementsByTagName("version");

			Node theNote = NodeVersion.item(0);
			Element noteElement = (Element)theNote;
			if(Int_VersionLocal < NodeVersion.getLength()){
				System.out.println(Int_VersionLocal);
				System.out.println(NodeVersion.getLength());
				for(int i = Int_VersionLocal; i < NodeVersion.getLength(); i++){
					theNote = NodeVersion.item(i);
					noteElement = (Element)theNote;
					Node thecontent = noteElement.getElementsByTagName("Mod").item(0);
					Element contentElement = (Element)thecontent;

					Load = contentElement.getTextContent();
					Content = Load.split(" ");

					try{
						new Download();
					}catch(Exception e1){
						e1.printStackTrace();
					}
				}
			}
		}catch(ParserConfigurationException|SAXException|IOException e){
			e.printStackTrace();
		}
	}

	public Download() {
		for(int a = 0; a < Content.length; a++){
			File File_MCFolder = new File(String_FichierMC);
			File File_ModsFolder = new File(String_FichierMC + "\\mods");
			File File_ModsFolderMod = new File(String_FichierMC+"\\mods\\"+Content[a]);
			File File_ConfigFolder = new File(String_FichierMC + "\\config");
			File File_ConfigFolderConfig = new File(String_FichierMC + "\\" + Content[a]);  //TODO Organiser File.

			String[] String_ModSplit = Content[a].split("\\.");
			File File_Delete = new File(File_ModsFolder + "\\");
			/**
			 * Creats link to mod file with a .zip and .jar to make sure the exist or not.
			 */
			File String_ModZip = new File(File_ModsFolder + "\\" + String_ModSplit[0]+".zip");
			File String_ModJar = new File(File_ModsFolder + "\\" + String_ModSplit[0]+".jar");

			if(String_ModZip.exists() || String_ModJar.exists()){
				try{
					String_ModZip.delete();
					String_ModJar.delete();
				}catch(Exception e){
					e.printStackTrace();
				}
			}

			try{
				if(!Content[a].equals("config.zip")){
					InputStream in = URI.create("http://alexandre-sp.cu.cc/Mods/" + Content[a]).toURL().openStream();
					Files.copy(in, Paths.get(File_ModsFolderMod.getAbsolutePath()));
				}
			}catch(IOException e){
				System.out.println("WRONG URL");
				e.printStackTrace();
			}

			if(Content[a].startsWith("-")){
				try{
					File_Delete.delete();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(Content[a].equals("config.zip")){
				try{
					System.out.println(Content[a]);
					InputStream in = URI.create("http://alexandre-sp.cu.cc/Mods/" + Content[a]).toURL().openStream();
					Files.copy(in, Paths.get(File_ConfigFolderConfig.getAbsolutePath()));

					try {
						ZipFile zipFile = new ZipFile(File_ConfigFolderConfig);
						zipFile.extractAll(String_FichierMC);
					} catch (ZipException e) {
						e.printStackTrace();
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		/*try{
			Scanner_FichierMods = new Scanner(File_FichierMods);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		String Where = Scanner_FichierMods.nextLine();

		try{
			Scanner_VersionLocal = new Scanner(File_VersionLocal);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		String Version = Scanner_VersionLocal.nextLine();

		System.out.println(Where);

		for(int i = 0; i < Content.length; i++){
			File f3 = new File(Where + "\\" + Content[i]);
			System.out.println(f3);
			if(f3.exists()){
				f3.delete();
			}
			try {
				InputStream in = URI.create("http://alexandre-sp.cu.cc/Mods/"+Check.Content[i]).toURL().openStream();
				Files.copy(in, Paths.get((f3).getAbsolutePath()));

			}catch(IOException e) {
				System.out.println("WRONG URL");
				e.printStackTrace();
			}*/
		}
	}
}
