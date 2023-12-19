package data.enums;

import data.consts.general.ConstDB;
import data.translations.ui.modules.TStartModule;

/**
 * All DSA Versions, that are also displayed in the home screen and used f. ex. for retrieving each database.
 */
public enum DSAVersion
{
    DSA1(TStartModule.VERSIONS_DSA1_LABEL, ConstDB.DB_FILE_DSA1),
    DSA2(TStartModule.VERSIONS_DSA2_LABEL, ConstDB.DB_FILE_DSA2),
    DSA3(TStartModule.VERSIONS_DSA3_LABEL, ConstDB.DB_FILE_DSA3),
    DSA4(TStartModule.VERSIONS_DSA4_LABEL, ConstDB.DB_FILE_DSA4),
    DSA4d1(TStartModule.VERSIONS_DSA4D1_LABEL, ConstDB.DB_FILE_DSA4d1),
    DSA5(TStartModule.VERSIONS_DSA5_LABEL, ConstDB.DB_FILE_DSA5),
    DSK(TStartModule.VERSIONS_DSK_LABEL, ConstDB.DB_FILE_DSK),
    MYRANOR(TStartModule.VERSIONS_MYR_LABEL, ConstDB.DB_FILE_MYR);


    private final String translKey;
    private final String dbName;

    /**
     * The main constructor with the translation key for the version and the database name file.
     *
     * @param translKey The translation key, that is used in translation files to find the correct translation.
     * @param dbName    The name of the database file.
     */
    DSAVersion(String translKey, String dbName)
    {
        this.translKey = translKey;
        this.dbName = dbName;
    }

    /**
     * Returns the translation key, that is used in translation files to find the correct translation.
     *
     * @return The translation key.
     */
    public String getTranslKey()
    {
        return translKey;
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
}
