package modules.start;

import data.consts.general.ConstScreen;
import data.consts.modules.ConstStartModule;
import data.general.Tuple;
import data.translations.ui.TStartModule;
import javafx.scene.control.Button;
import modules.general.abstracts.AbstractView;
import org.slf4j.Logger;
import utils.general.Utils;
import utils.handler.LoggerHandler;

import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class StartView extends AbstractView
{
    private final Logger LOG = LoggerHandler.getLogger(MethodHandles.lookup().lookupClass());

    private StartModel      startModel;
    private StartController startController;

    @Override
    public boolean initElements()
    {
        startModel = (StartModel) getModel();
        startController = (StartController) getController();

        assignTooltipsToElements(
                new Tuple<>((Button) lookupID("radioDSA1"), startModel.getTransl(TStartModule.VERSIONS_DSA1_TT)),
                new Tuple<>((Button) lookupID("radioDSA2"), startModel.getTransl(TStartModule.VERSIONS_DSA2_TT)),
                new Tuple<>((Button) lookupID("radioDSA3"), startModel.getTransl(TStartModule.VERSIONS_DSA3_TT)),
                new Tuple<>((Button) lookupID("radioDSA4"), startModel.getTransl(TStartModule.VERSIONS_DSA4_TT)),
                new Tuple<>((Button) lookupID("radioDSA4d1"), startModel.getTransl(TStartModule.VERSIONS_DSA4D1_TT)),
                new Tuple<>((Button) lookupID("radioDSA5"), startModel.getTransl(TStartModule.VERSIONS_DSA5_TT)),
                new Tuple<>((Button) lookupID("radioDSK"), startModel.getTransl(TStartModule.VERSIONS_DSK_TT)),
                new Tuple<>((Button) lookupID("radioMyranor"), startModel.getTransl(TStartModule.VERSIONS_MYR_TT))
        );

        assignTranslLabel(
                new Tuple<>((Button) lookupID("radioDSA1"), startModel.getTransl(TStartModule.VERSIONS_DSA1_LABEL)),
                new Tuple<>((Button) lookupID("radioDSA2"), startModel.getTransl(TStartModule.VERSIONS_DSA2_LABEL)),
                new Tuple<>((Button) lookupID("radioDSA3"), startModel.getTransl(TStartModule.VERSIONS_DSA3_LABEL)),
                new Tuple<>((Button) lookupID("radioDSA4"), startModel.getTransl(TStartModule.VERSIONS_DSA4_LABEL)),
                new Tuple<>((Button) lookupID("radioDSA4d1"), startModel.getTransl(TStartModule.VERSIONS_DSA4D1_LABEL)),
                new Tuple<>((Button) lookupID("radioDSA5"), startModel.getTransl(TStartModule.VERSIONS_DSA5_LABEL)),
                new Tuple<>((Button) lookupID("radioDSK"), startModel.getTransl(TStartModule.VERSIONS_DSK_LABEL)),
                new Tuple<>((Button) lookupID("radioMyranor"), startModel.getTransl(TStartModule.VERSIONS_MYR_LABEL))
        );

        return true;
    }

    @Override
    public boolean deconstruct()
    {
        return true;
    }

    // ###############
    // Getter & Setter
    // ###############

    @Override
    public URL getFXMLPath()
    {
        return getClass().getResource(ConstStartModule.FXML_START_WINDOW);
    }

    @Override
    public List<URL> getCSSPaths()
    {
        return Utils.toList(ConstStartModule.FXML_START_CSS)
                .stream()
                .map(StartView.class::getResource)
                .collect(Collectors.toList());
    }

    @Override
    public URL getModuleImage()
    {
        return getClass().getResource(ConstScreen.IMG_MODULE_PLACEHOLDER);
    }
}
