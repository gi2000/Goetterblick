package data.translations.ui.modules;

import utils.general.Utils;

/**
 * The interface holding all translation keys for the Start Module UI.
 */
public interface TStartModule extends TAbstractModule
{
    // ####################
    // Config path segments
    // ####################
    String SEGM_START    = "start";
    String SEGM_VERSIONS = "versions";

    // ################
    // Translation Keys
    // ################

    String VERSIONS_DSA1_LABEL   = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsa1", SEGM_LABEL);
    String VERSIONS_DSA1_TT      = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsa1", SEGM_TOOLTIP);
    String VERSIONS_DSA2_LABEL   = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsa2", SEGM_LABEL);
    String VERSIONS_DSA2_TT      = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsa2", SEGM_TOOLTIP);
    String VERSIONS_DSA3_LABEL   = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsa3", SEGM_LABEL);
    String VERSIONS_DSA3_TT      = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsa3", SEGM_TOOLTIP);
    String VERSIONS_DSA4_LABEL   = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsa4", SEGM_LABEL);
    String VERSIONS_DSA4_TT      = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsa4", SEGM_TOOLTIP);
    String VERSIONS_DSA4D1_LABEL = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsa4d1", SEGM_LABEL);
    String VERSIONS_DSA4D1_TT    = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsa4d1", SEGM_TOOLTIP);
    String VERSIONS_DSA5_LABEL   = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsa5", SEGM_LABEL);
    String VERSIONS_DSA5_TT      = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsa5", SEGM_TOOLTIP);
    String VERSIONS_DSK_LABEL    = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsk", SEGM_LABEL);
    String VERSIONS_DSK_TT       = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "dsk", SEGM_TOOLTIP);
    String VERSIONS_MYR_LABEL    = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "myr", SEGM_LABEL);
    String VERSIONS_MYR_TT       = Utils.joinSegms(SEGM_UI, SEGM_MODULES, SEGM_START, SEGM_VERSIONS, "myr", SEGM_TOOLTIP);
}
