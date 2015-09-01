package burp;

import java.io.File;
import java.io.PrintWriter;
import payload.PayloadFactory;
import payload.PayloadTab;

public class BurpExtender implements IBurpExtender {

    private static IBurpExtenderCallbacks callbacks;
    private PayloadTab tab;
    
    //
    // implement IBurpExtender
    //
    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        callbacks.setExtensionName("File as Payload");

        tab = PayloadTab.getInstance();
        tab.addMenuTab();
        
        callbacks.registerIntruderPayloadGeneratorFactory(new PayloadFactory(tab, false));
        callbacks.registerIntruderPayloadGeneratorFactory(new PayloadFactory(tab, true));
    }

    
    public static IBurpExtenderCallbacks getBurpCallbacks() {
        return callbacks;
    }
}
