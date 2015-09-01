package payload;

import burp.BurpExtender;
import burp.IBurpExtenderCallbacks;
import burp.IIntruderPayloadGenerator;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author tmendo
 */
public class IntruderPayloadGenerator implements IIntruderPayloadGenerator {

    int payloadIndex;
    ArrayList<String> files;
    IBurpExtenderCallbacks callbacks = BurpExtender.getBurpCallbacks();
    
    // if true returns the filenames instead of the payload
    boolean useFilename;

    public IntruderPayloadGenerator(File folder, boolean useFilename) {
        if (folder == null) {
            callbacks.issueAlert("Please choose the payload folder first");
            files = new ArrayList<>();
        }
        else {
            files = FileUtils.listFilesForFolder(folder.getAbsolutePath());
        }
        this.useFilename = useFilename;
    }

    @Override
    public boolean hasMorePayloads() {
        return payloadIndex < files.size();
    }

    @Override
    public byte[] getNextPayload(byte[] baseValue) {
        String file = files.get(payloadIndex);
        payloadIndex++;
        
        byte[] payload = new byte[0];
        try {
            if (useFilename) {
                payload = callbacks.getHelpers().stringToBytes(Paths.get(file).getFileName().toString());
            }
            else {
                payload = Files.readAllBytes(Paths.get(file));
            }
        } catch (IOException ex) {
            PrintWriter stderr = new PrintWriter(callbacks.getStderr(), true);
            stderr.println("Could not read \"" + file + "\" - " + ex.getLocalizedMessage());
        }
        
        return payload;
    }

    @Override
    public void reset() {
        payloadIndex = 0;
    }
}
