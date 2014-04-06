package fr.update.util;

import java.io.File;
import java.net.URI;

/**
 * User: Alexandre
 */
public interface Info {

	public File File_SystemRoot = new File(System.getenv("APPDATA") + "\\ServerModUpdater"),
				File_Update = new File(System.getenv("APPDATA") + "\\ServerModUpdater\\Version.xml"),
				File_VersionLocal = new File(System.getenv("APPDATA") + "\\ServerModUpdater\\VersionLocal.txt"),
				File_FichierMods = new File(System.getenv("APPDATA") + "\\ServerModUpdater\\FichierMinecraft.txt");

	public URI URI_ServerVersion = URI.create("http://alexandre-sp.cu.cc/Mods/Version.xml");


}
