package modules.general.abstracts;

import data.enums.Module;
import data.general.Tuple;
import data.translations.ui.modules.TAbstractModule;
import general.Initialisation;
import javafx.fxml.FXML;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;
import javafx.scene.control.Tooltip;
import modules.general.facades.IController;
import modules.general.facades.IModel;
import modules.general.facades.IView;
import modules.start.ConstStartModule;
import org.slf4j.Logger;
import utils.general.Utils;
import utils.handler.LoggerHandler;
import utils.handler.TranslationHandler;

import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * The abstract controller class of the MVC pattern. Extend this class to create the controller for the new module.
 */
public abstract class AbstractController implements IController
{
    private static final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    private IModel model;
    private IView  view;

    protected String moduleName;

    // ############################
    // Initializing / Loading Scene
    // ############################

    @FXML
    public void initialize()
    {
        initScreen(false);
        if (getModel().initialize() && getView().initialize())
        {
            initElements();
        }
    }

    @Override
    public void initScreen(boolean isModuleSwitch)
    {
        // Load initial language
        TranslationHandler.updateTranslations();

        Module module = Module.getModuleFromController(this.getClass());

        String moduleName;
        if (module == Initialisation.STARTING_MODULE)
        {
            moduleName =
                    Utils.cap(TranslationHandler.getTransl(
                            Module.buildDefaultPath() + module.name().toLowerCase() + "." + TAbstractModule.SEGM_GENERAL +
                            "." + TAbstractModule.SEGM_LABEL));
        }
        else
        {
            moduleName = Utils.cap(TranslationHandler.getTransl(module.getModuleButtonLabelKey()));
        }

        setModuleName(moduleName);
        setScreenTitle(getModuleName());
        setView(createView());
        setModel(createModel());
    }

    // ###########################
    //

    @Override
    public boolean deconstruct(boolean isModuleSwitch)
    {
        // If this is a module switch, then deconstruct the model, too. Lazy loading allows for the second getModel()
        // .deconstruct()
        // to not get executed, if isModuleSwitch is set to false.
        return (!isModuleSwitch || getModel().deconstruct()) && getView().deconstruct();
    }

    @Override
    public void switchToModule(Module module)
    {
        if (module == null)
        {
            return;
        }

        getModel().setPrevModule(getModuleName());
        try
        {
            Initialisation.loadRoot(module);
        }
        catch (Exception e)
        {
            LOG.error("Couldn't load module {}.", module.name(), e);
            throw new RuntimeException(e);
        }
    }

    public void assignTooltipsToElements(List<Tuple<Control, String>> elements)
    {
        if (elements == null)
        {
            return;
        }

        // Iterate over each entry and set the tool tip text to the respective JavaFX element.
        for (Tuple<Control, String> element : elements)
        {
            // If any element is null, skip it.
            if (element == null || element.getVal1() == null || element.getVal2() == null)
            {
                continue;
            }

            // Apply settings values to tooltip
            Tooltip tip = Utils.toToolTip(element.getVal2());
            Utils.run(() -> element.getVal1().setTooltip(tip));
        }
    }

    /**
     * Assigns the given Labeled-Element the text retrieved from the translation files.
     *
     * @param elements The list of tuples: Val1 = Element in JavaFX, that needs to be labeled, Val2 = Translation
     */
    public void assignTranslLabels(List<Tuple<Labeled, String>> elements)
    {
        if (elements == null)
        {
            return;
        }

        // Iterate over each entry and set the tool tip text to the respective JavaFX element.
        for (Tuple<Labeled, String> element : elements)
        {
            // If any element is null, skip it.
            if (element == null || element.getVal1() == null || element.getVal2() == null)
            {
                continue;
            }

            Utils.run(() -> element.getVal1().setText(element.getVal2()));
        }
    }

    // #################
    // Getter and Setter
    // #################

    public IModel getModel()
    {
        return model;
    }

    public void setModel(IModel model)
    {
        this.model = model;
    }

    public IView getView()
    {
        return view;
    }

    public void setView(IView view)
    {
        this.view = view;
    }

    /**
     * The name of the associated module to this controller.
     *
     * @return The module's name.
     */
    public String getModuleName()
    {
        return moduleName;
    }

    /**
     * Sets the new module name.
     *
     * @param moduleName The new module name.
     */
    public void setModuleName(String moduleName)
    {
        this.moduleName = moduleName;
    }

    /**
     * Sets the new screen title and immediately updates the title.
     *
     * @param screenTitle The new title for this window. The name of the application will stay as a prefix, followed
     *                    by a dash "-".
     */
    protected void setScreenTitle(String screenTitle)
    {
        getStage().setTitle(ConstStartModule.FXML_MAIN_TITLE + " - " + screenTitle);
    }
}
