package utilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.apache.log4j.Logger;

public class ExecutableFunctionalities {

	// returns the path of exe files
	private static Logger log= Logger.getLogger(ExecutableFunctionalities.class);
	public static <T> String getPath(Class<T> classEntity, String resource) {
		log.info("Returning path for resource: "+resource+ " invoked from class: "+classEntity.getName());
		File target = null;
		try {
			URL res = classEntity.getResource("/"+resource);
			File file = new File(res.getPath());
			target = new File(
					System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + file.getName());
			if (!target.exists())
				Files.copy(file.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
			return target.getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
			return null;
	}
}
