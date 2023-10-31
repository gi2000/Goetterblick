package modules.general.facades;

/**
 * The model facade of the MVC pattern, which holds and processes all data for the view to display. The model itself follows the
 * observer pattern: It itself offers observable values to the outside, while knowing nothing about the observers, who take and
 * update upon this value.
 *
 * @apiNote It is recommended not to use this facade, if you are implementing a new Model class. Instead, extend from the {@code
 * AbstractModel}-class, since some methods are already implemented in the abstract Model.
 */
public interface IModel
{
    /**
     * Initializes / starts up the model. Here all initial data-loadings are done, while in the constructor mainly set-methods are
     * called.
     *
     * @return Whether the initialization was successful.
     */
    boolean initialize();

    /**
     * Returns the translation for the given translation key.
     *
     * @param translKey The translation key, which represents a set of strings from the currently loaded language properties.
     * @return The translation value for the given key.
     */
    String getTransl(String translKey);

    /**
     * Deconstructs this model, if anything needs to be torn down for the next or previous module.
     *
     * @return Whether the deconstruction was successful.
     */
    boolean deconstruct();
}
