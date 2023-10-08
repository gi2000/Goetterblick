package modules.general;

import javafx.stage.Stage;

public abstract class AbstractModule implements IModule
{


    @Override
    public boolean initScreen(Stage stage)
    {

        return false;
    }

    protected abstract String getScreenTitle();

    protected void setScreenTitle(String title)
    {
        
    }
}
