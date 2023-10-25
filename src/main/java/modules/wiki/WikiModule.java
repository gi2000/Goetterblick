package modules.wiki;

import data.annotations.Module;
import modules.general.abstracts.AbstractModule;

import java.net.URL;
import java.util.List;

@Module(name = "wiki")
public class WikiModule extends AbstractModule
{
    protected boolean initController()
    {
        return false;
    }

    protected URL getFXMLPath()
    {
        return null;
    }

    protected List<URL> getCSSPaths()
    {
        return null;
    }

    protected URL loadCSS(String cssPath)
    {
        return null;
    }

    public URL getModuleImage()
    {
        return null;
    }

    public String getName()
    {
        return null;
    }
}
