package modules.gm;

import modules.general.abstracts.AbstractView;
import modules.general.facades.IController;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.List;

public class GmView extends AbstractView
{
    public GmView(IController controller)
    {
        super(controller);
    }

    protected URL getFXMLPath()
    {
        return null;
    }

    protected List<URL> getCSSPaths()
    {
        return null;
    }

    public boolean initElements()
    {
        return false;
    }

    public boolean deconstruct()
    {
        return false;
    }

    @Override
    public FontIcon getModuleImage()
    {
        return new FontIcon("bi-map");
    }
}
