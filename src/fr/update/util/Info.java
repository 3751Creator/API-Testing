package fr.update.util;

import java.io.File;
import java.net.URI;

/**
 * User: Alexandre
 */
public interface Info {

	public File File_SystemRoot = new File(System.getenv("APPDATA") + "\\ModsChecker"),
				File_Update = new File(System.getenv("APPDATA") + "\\ModsChecker\\Version.xml"),
				File_VersionLocal = new File(System.getenv("APPDATA") + "\\ModsChecker\\VersionLocal.txt"),
				File_FichierMods = new File(System.getenv("APPDATA") + "\\ModsChecker\\FichierMinecraft.txt");

	public URI URI_ServerVersion = URI.create("http://alexandre-sp.cu.cc/Mods/Version.xml");


}
