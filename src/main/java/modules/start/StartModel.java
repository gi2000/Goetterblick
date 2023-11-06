package modules.start;

import modules.general.abstracts.AbstractModel;
import org.apache.commons.configuration2.Configuration;
import utils.handler.ConfigHandler;


/**
 * The model holding all information on database data and other things for the view.
 */
public class StartModel extends AbstractModel
{

    public boolean initialize(String moduleName)
    {
        return true;
    }

    public boolean deconstruct()
    {
        return false;
    }

    public Configuration getMainCfg()
    {
        return ConfigHandler.getMainConfig();
    }
}
