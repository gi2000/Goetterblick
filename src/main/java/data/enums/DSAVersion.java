package data.enums;

import data.consts.ConstDB;
import data.consts.ConstTranslation;

/**
 * All DSA Versions, that are also displayed in the home screen and used f. ex. for retrieving each database.
 */
public enum DSAVersion
{
    DSA1(ConstTranslation.MODULES_START_VERSIONS_DSA1, ConstDB.DB_FILE_DSA1),
    DSA2(ConstTranslation.MODULES_START_VERSIONS_DSA2, ConstDB.DB_FILE_DSA2),
    DSA3(ConstTranslation.MODULES_START_VERSIONS_DSA3, ConstDB.DB_FILE_DSA3),
    DSA4(ConstTranslation.MODULES_START_VERSIONS_DSA4, ConstDB.DB_FILE_DSA4),
    DSA4d1(ConstTranslation.MODULES_START_VERSIONS_DSA4d1, ConstDB.DB_FILE_DSA4d1),
    DSA5(ConstTranslation.MODULES_START_VERSIONS_DSA5, ConstDB.DB_FILE_DSA5),
    DSK(ConstTranslation.MODULES_START_VERSIONS_DSK, ConstDB.DB_FILE_DSK),
    MYRANOR(ConstTranslation.MODULES_START_VERSIONS_MYR, ConstDB.DB_FILE_MYR);


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
