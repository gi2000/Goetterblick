package data.translations.ui.modules;

import utils.general.Utils;

public interface TWikiModule extends TAbstractModule
{
    // ####################
    // Config path segments
    // ####################

    String SEGM_WIKI = "wiki";

    // ################
    // Translation Keys
    // ################

    String MODULEBUTTON_LABEL   = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_WIKI, SEGM_MODULE_BUTTON, SEGM_LABEL);
    String MODULEBUTTON_TOOLTIP = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_WIKI, SEGM_MODULE_BUTTON, SEGM_TOOLTIP);
}
