package payload;

import burp.BurpExtender;
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

    public IntruderPayloadGenerator(File folder) {
        if (folder == null) {
            BurpExtender.getBurpCallbacks().issueAlert("Please choose the payload folder first");
            files = new ArrayList<>();
        }
        else {
            files = FileUtils.listFilesForFolder(folder.getAbsolutePath());
        }
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
            payload = Files.readAllBytes(Paths.get(file));
        } catch (IOException ex) {
            PrintWriter stderr = new PrintWriter(BurpExtender.getBurpCallbacks().getStderr(), true);
            //stderr
        }
        
        return payload;
    }

    @Override
    public void reset() {
        payloadIndex = 0;
    }
}
