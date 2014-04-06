package fr.updater.main;

import fr.update.util.Info;
import fr.updater.main.Console.Level;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * User: Alexandre
 */
@SuppressWarnings({"UnnecessaryContinue", "ResultOfMethodCallIgnored", "ForLoopReplaceableByForEach"})
public class Check extends JFrame implements Info {

	private void preCheck(){

		/**
		 * Create ModsChecker file if doesn't exist.
		 */
		if(!File_SystemRoot.exists()){
			File_SystemRoot.mkdir();
		}

		/**
		 * Dels Version.xml if exists.
		 */
		if(File_Update.exists()){
			File_Update.delete();
		}

		/**
		 * Create txt file to put local version
		 */
		if(!File_VersionLocal.exists()){
			try{
				File_VersionLocal.createNewFile();
				FileWriter fstream = new FileWriter(File_VersionLocal);
				BufferedWriter out = new BufferedWriter(fstream);
				out.write("0");
				out.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}

		/**
		 * If no mods file has been selected
		 */
		if(!File_FichierMods.exists()){
			ModFile();
		}

		/**
		 * Downloads latest Version.xml
		 */
		try {
			InputStream in = URI_ServerVersion.toURL().openStream();
			Files.copy(in, Paths.get(File_Update.getAbsolutePath()));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void ModFile(){
		JFileChooser FileChooser_FichierMods = new JFileChooser();

		FileChooser_FichierMods.setDialogTitle("Sélectionnez le fichier minecraft souhaité.");
		FileChooser_FichierMods.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		FileChooser_FichierMods.showOpenDialog(null);

		add(FileChooser_FichierMods);

		/**
		 * Splits directory to extract file name
		 */
		String String_FichierMods = String.valueOf(FileChooser_FichierMods.getSelectedFile());
		String[] String_DirectorySplit = String_FichierMods.split("\\\\");

		/**
		 * Checks if directory is "minecraft"
		 */
		if(!String_DirectorySplit[String_DirectorySplit.length - 1].equals("minecraft")){
			ModFile();
		}

		/**
		 * Writes file directory to FichierMinecraft.txt
		 */
		try{
			File_FichierMods.createNewFile();
			FileWriter fstream = new FileWriter(File_FichierMods);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(String.valueOf(FileChooser_FichierMods.getSelectedFile()));
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private Scanner Scanner_VersionLocal, Scanner_FichierMods;
	public static String[] Content;

	public Check(){

		preCheck();

		Frame.Button_MAJ.setEnabled(true);

		/**
		 * Take local version
		 */
		try{
			Scanner_VersionLocal = new Scanner(File_VersionLocal);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		int int_VersionLocal = Scanner_VersionLocal.nextInt();

		try{
			Scanner_FichierMods = new Scanner(File_FichierMods);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		String string_FichierMods = Scanner_FichierMods.nextLine();

		DocumentBuilderFactory builderfactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder dBuilder;
		try{
			dBuilder = builderfactory.newDocumentBuilder();
			Document doc = dBuilder.parse(File_Update);
			doc.normalize();

			NodeList rootUpdate = doc.getElementsByTagName("update");
			Node NodeUpdate = rootUpdate.item(0);

			Element rootVersion = (Element) NodeUpdate;
			NodeList NodeVersion = rootVersion.getElementsByTagName("version");

			Node theNote;
			Element noteElement;

			if(int_VersionLocal < NodeVersion.getLength()){
				for(int i = int_VersionLocal; i < NodeVersion.getLength(); i++){
					theNote = NodeVersion.item(i);
					noteElement = (Element) theNote;
					Node thecontent = noteElement.getElementsByTagName("Mod").item(0);
					Element contentElement = (Element) thecontent;

					String load = contentElement.getTextContent();
					Content = load.split(" ");
					Console.add(Level.CLASSIC, ((Element)theNote).getAttribute("id"));
					for(int ii = 0; ii < Content.length; ii++){
						String[] String_ModSplit = Content[ii].split("\\.");
						/**
						 * Creats link to mod file with a .zip and .jar to make sure the exist or not.
						 */
						File Stirng_ModZip = new File(string_FichierMods+ "\\mods\\" + String_ModSplit[0] + ".zip");
						File Stirng_ModJar = new File(string_FichierMods+ "\\mods\\" + String_ModSplit[0] + ".jar");

						if(String_ModSplit[0].equals("config")){
							Console.add(Level.CONFIG, Content[ii]);
							continue;
						}if(String_ModSplit[0].startsWith("-")){
							String[] String_Del = Content[ii].split("\\-");
							Console.add(Level.SUPRESSION, String_Del[1]);
							continue;
						}if(Stirng_ModZip.exists() || Stirng_ModJar.exists()){
							Console.add(Level.MAJ, Content[ii]);
							continue;
						}else{
							Console.add(Level.AJOUT, Content[ii]);
							continue;
						}
					}
				}
			}else{
				Console.add(Level.NOMAJ, "-- PAS DE MISE A JOUR --");
				Frame.Button_MAJ.setEnabled(false);
			}
		}catch(ParserConfigurationException|SAXException| IOException e){
			e.printStackTrace();
		}
	}
}
