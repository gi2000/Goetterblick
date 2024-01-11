package data.enums;

import data.consts.general.ConstDB;
import data.translations.ui.modules.TStartModule;

/**
 * All DSA Versions, that are also displayed in the home screen and used f. ex. for retrieving each database.
 */
public enum DSAVersion
{
    DSA1(TStartModule.VERSIONS_DSA1_LABEL, TStartModule.VERSIONS_DSA1_TT, ConstDB.DB_FILE_DSA1, false),
    DSA2(TStartModule.VERSIONS_DSA2_LABEL, TStartModule.VERSIONS_DSA2_TT, ConstDB.DB_FILE_DSA2, false),
    DSA3(TStartModule.VERSIONS_DSA3_LABEL, TStartModule.VERSIONS_DSA3_TT, ConstDB.DB_FILE_DSA3, false),
    DSA4(TStartModule.VERSIONS_DSA4_LABEL, TStartModule.VERSIONS_DSA4_TT, ConstDB.DB_FILE_DSA4, false),
    DSA4d1(TStartModule.VERSIONS_DSA4D1_LABEL, TStartModule.VERSIONS_DSA4D1_TT, ConstDB.DB_FILE_DSA4d1, true),
    DSA5(TStartModule.VERSIONS_DSA5_LABEL, TStartModule.VERSIONS_DSA5_TT, ConstDB.DB_FILE_DSA5, true),
    DSK(TStartModule.VERSIONS_DSK_LABEL, TStartModule.VERSIONS_DSK_TT, ConstDB.DB_FILE_DSK, false),
    MYRANOR(TStartModule.VERSIONS_MYR_LABEL, TStartModule.VERSIONS_MYR_TT, ConstDB.DB_FILE_MYR, false);


    private final String  translLabelKey;
    private final String  translToolTipKey;
    private final String  dbName;
    private final boolean isActive;

    /**
     * The main constructor with the translation key for the version and the database name file.
     *
     * @param translLabelKey   The translation key for the text label, that is used in translation files to find the correlating
     *                         translation.
     * @param translToolTipKey The translation key for the tool tipp, that is used in translation files to find the correlating
     *                         translation.
     * @param dbName           The name of the database file.
     * @param isActive         Whether the version is actively usable or not.
     */
    DSAVersion(String translLabelKey, String translToolTipKey, String dbName, boolean isActive)
    {
        this.translLabelKey = translLabelKey;
        this.translToolTipKey = translToolTipKey;
        this.dbName = dbName;
        this.isActive = isActive;
    }

    /**
     * Returns the translation key for the label, that is used in translation files to find the correlating translation.
     *
     * @return The translation key.
     */
    public String getTranslLabelKey()
    {
        return translLabelKey;
    }

    /**
     * Returns the translation key for the tool tipp, that is used in translation files to find the correlating
     * translation.
     *
     * @return The translation key.
     */
    public String getTranslToolTipKey()
    {
        return translToolTipKey;
    }

    /**
     * Returns the name of the corresponding database file.
     *
     * @return The name of the db file.
     */
    public String getDbName()
    {
        return dbName;
    }

    /**
     * If this TDE-Version is active / usable.
     *
     * @return Whether the version is active.
     */
    public boolean isActive()
    {
        return isActive;
    }
}
