package modules.general.abstracts;

import modules.general.facades.IModel;
import org.apache.commons.configuration2.Configuration;
import utils.handler.ConfigHandler;
import utils.handler.TranslationHandler;

public abstract class AbstractModel implements IModel
{
    protected String prevModule;

    // ##################
    // Getters and Setter
    // ##################

    @Override
    public String getTransl(String translKey)
    {
        return TranslationHandler.getTransl(translKey);
    }

    @Override
    public Configuration getMainCfg()
    {
        return ConfigHandler.getMainConfig();
    }

    @Override
    public String getPrevModule()
    {
        return prevModule;
    }

    @Override
    public void setPrevModule(String prevModule)
    {
        this.prevModule = prevModule;
    }
}
