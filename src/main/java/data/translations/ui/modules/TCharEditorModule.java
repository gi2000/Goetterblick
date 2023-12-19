package data.translations.ui.modules;

import utils.general.Utils;

public interface TCharEditorModule extends TAbstractModule
{
    // ####################
    // Config path segments
    // ####################

    String SEGM_CHAR_EDITOR = "chareditor";


    // ################
    // Translation Keys
    // ################

    String MODULEBUTTON_LABEL   = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_CHAR_EDITOR, SEGM_MODULE_BUTTON, SEGM_LABEL);
    String MODULEBUTTON_TOOLTIP = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_CHAR_EDITOR, SEGM_MODULE_BUTTON, SEGM_TOOLTIP);

}
