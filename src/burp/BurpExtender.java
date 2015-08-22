package burp;

import payload.IntruderPayloadGenerator;
import payload.PayloadTab;

public class BurpExtender implements IBurpExtender, IIntruderPayloadGeneratorFactory {

    private static IBurpExtenderCallbacks callbacks;
    private PayloadTab tab;
    
    //
    // implement IBurpExtender
    //
    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        callbacks.setExtensionName("File as Payload");

        // tab
        tab = PayloadTab.getInstance();
        tab.addMenuTab();
        
        // register ourselves as an Intruder payload generator
        callbacks.registerIntruderPayloadGeneratorFactory(this);
    }

    //
    // implement IIntruderPayloadGeneratorFactory
    //
    @Override
    public String getGeneratorName() {
        return "File as Payload";
    }

    @Override
    public IIntruderPayloadGenerator createNewInstance(IIntruderAttack attack) {
        return new IntruderPayloadGenerator(tab.choosenFolder());
    }
    
    public static IBurpExtenderCallbacks getBurpCallbacks() {
        return callbacks;
    }
}
