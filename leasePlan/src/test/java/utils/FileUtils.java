package utils;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
	public static String readJsonFromFile(String relativePath) {
		try {
			return new String(Files.readAllBytes(Paths.get("src/test/resources/" + relativePath)));
		} catch(Exception e) {
			throw new RuntimeException("Failed to read JSON file: " + relativePath, e);
		}
	}

}
