package fr.updater.main;

import fr.update.util.Info;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * User: Alexandre
 */
public class Download implements Info, Runnable{


	private static Scanner Scanner_VersionLocal, Scanner_FichierMods;
	private static String String_FichierMC;
	public static String[] Content;
	public static int int_VersionLocal;
	public static NodeList NodeVersion;

	public void run(){
		preDownload();

		Frame.Button_MAJ.setEnabled(true);
		Frame.Button_Redefinir.setEnabled(true);
		Frame.Button_Rafraichir.setEnabled(true);
		JOptionPane.showMessageDialog(null, "Téléchargement fini! \nVous pouvez maintenant fermer MSU et aller sur le serveur.", "Téléchargement fini", JOptionPane.PLAIN_MESSAGE);
		Frame.ProgressBar_Download.setValue(0);

		try{
			FileWriter fstream = new FileWriter(File_VersionLocal);
			BufferedWriter out = new BufferedWriter(fstream);
			String String_Version = String.valueOf(NodeVersion.getLength());
			out.write(String_Version);
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}

		try{
			Frame.TextPane_List.setText("");
			new Check();
		}catch(Exception e1){
			e1.printStackTrace();
		}

	}

	public static void preDownload(){

		try{
			Scanner_VersionLocal = new Scanner(File_VersionLocal);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		int_VersionLocal = Scanner_VersionLocal.nextInt();

		try{
			Scanner_FichierMods = new Scanner(File_FichierMods);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		String_FichierMC = Scanner_FichierMods.nextLine();

		DocumentBuilderFactory builderfactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder dBuilder;
		try{
			dBuilder = builderfactory.newDocumentBuilder();
			Document doc = dBuilder.parse(File_Update);
			doc.normalize();

			NodeList rootUpdate = doc.getElementsByTagName("update");
			Node NodeUpdate = rootUpdate.item(0);

			Element rootVersion = (Element)NodeUpdate;
			NodeVersion = rootVersion.getElementsByTagName("version");

			Node theNote;
			Element noteElement;

			if(int_VersionLocal < NodeVersion.getLength()){
				for(int i = int_VersionLocal; i < NodeVersion.getLength(); i++){
					theNote = NodeVersion.item(i);
					noteElement = (Element)theNote;
					Node thecontent = noteElement.getElementsByTagName("Mod").item(0);
					Element contentElement = (Element)thecontent;

					String load = contentElement.getTextContent();
					Content = load.split(" ");
					Frame.ProgressBar_Download.setMaximum(Content.length);
					System.out.println(Content.length);
					Download();
				}
			}
		}catch(ParserConfigurationException|SAXException|IOException e){
			e.printStackTrace();
		}
	}

	@SuppressWarnings({"ForLoopReplaceableByForEach", "ResultOfMethodCallIgnored", "MethodNameSameAsClassName"})
	public static void Download() {
		Frame.Button_MAJ.setEnabled(false);
		Frame.Button_Redefinir.setEnabled(false);
		Frame.Button_Rafraichir.setEnabled(false);
		for(int a = 0; a < Content.length; a++){
			File File_ModsFolder = new File(String_FichierMC + "\\mods");
			File File_ModsFiles = new File(String_FichierMC + "\\mods\\" + Content[a]);
			File File_ConfigZip = new File(String_FichierMC + "\\config.zip");

			String[] String_ModSplit = Content[a].split("\\.");
			/**
			 * Creats link to mod file with a .zip and .jar to make sure the exist or not.
			 */
			File String_ModZip = new File(File_ModsFolder + "\\" + String_ModSplit[0] + ".zip");
			File String_ModJar = new File(File_ModsFolder + "\\" + String_ModSplit[0] + ".jar");

			if(String_ModZip.exists() || String_ModJar.exists()){
				try{
					String_ModZip.delete();
					String_ModJar.delete();
				}catch(Exception e){
					e.printStackTrace();
				}
			}

			try{
				if(!Content[a].equals("config.zip") && !Content[a].startsWith("-")){
					InputStream in = URI.create("http://alexandre-sp.cu.cc/Mods/" + Content[a]).toURL().openStream();
					Files.copy(in, Paths.get(File_ModsFiles.getAbsolutePath()));
				}
			}catch(IOException e){
				System.out.println("WRONG URL");
				e.printStackTrace();
			}

			if(Content[a].startsWith("-")){
				try{
					String[] String_DelSplit = Content[a].split("\\-");
					new File(File_ModsFolder + "\\" + String_DelSplit[1]).delete();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(Content[a].equals("config.zip")){
				if(File_ConfigZip.exists()){
					File_ConfigZip.delete();
				}
				try{
					InputStream in = URI.create("http://alexandre-sp.cu.cc/Mods/" + Content[a]).toURL().openStream();
					Files.copy(in, Paths.get(File_ConfigZip.getAbsolutePath()));
					try {
						ZipFile zipFile = new ZipFile(File_ConfigZip);
						zipFile.extractAll(String_FichierMC);
					} catch (ZipException e) {
						e.printStackTrace();
					}
					File_ConfigZip.delete();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			System.out.println(a+1);
			Frame.ProgressBar_Download.setValue(a+1);
		}
	}
}
