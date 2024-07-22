package data.translations.ui.modules;

import utils.general.Utils;

public interface TGmModule extends TAbstractModule
{
    // ####################
    // Config path segments
    // ####################

    String SEGM_GM = "gm";

    // ################
    // Translation Keys
    // ################
    String MODULEBUTTON_LABEL   = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_GM, SEGM_GENERAL, SEGM_LABEL);
    String MODULEBUTTON_TOOLTIP = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_GM, SEGM_GENERAL, SEGM_TOOLTIP);

}
