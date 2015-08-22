package payload;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author tmendo
 */
public class FileUtils {
        
    public static ArrayList<String> listFilesForFolder(String folder) {
        ArrayList<String> files = new ArrayList<>();
               
        File inputFolder = new File(folder);
        for (final File fileEntry : inputFolder.listFiles()) {
            if (fileEntry.isDirectory()) {
                files.addAll(listFilesForFolder(fileEntry.getAbsolutePath()));
            } else {
                files.add(fileEntry.getAbsolutePath());
            }
        }
        
        return files;
    }
}
