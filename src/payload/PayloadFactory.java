/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payload;

import burp.IIntruderAttack;
import burp.IIntruderPayloadGenerator;
import burp.IIntruderPayloadGeneratorFactory;
import java.io.File;

/**
 *
 * @author tmendo
 */
public class PayloadFactory implements IIntruderPayloadGeneratorFactory{

    private final PayloadTab tab;
    private final boolean useFilename;
    
    public PayloadFactory(PayloadTab tab, boolean useFilename) {
        this.tab = tab;
        this.useFilename = useFilename;
    }
    
    @Override
    public String getGeneratorName() {
        if (useFilename) {
            return "Filename as Payload";
        }
        else {
            return "File as Payload";            
        }
    }

    @Override
    public IIntruderPayloadGenerator createNewInstance(IIntruderAttack attack) {
        return new IntruderPayloadGenerator(tab.choosenFolder(), useFilename);
    }
    
}
