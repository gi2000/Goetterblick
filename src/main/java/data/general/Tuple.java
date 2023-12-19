package data.general;

import java.util.Map;

/**
 * A pair of two values, coupled together as a tuple.
 *
 * @param <T> The class of the first value.
 * @param <U> The class of the second value.
 */
public class Tuple<T, U>
{
    private T val1;
    private U val2;

    /**
     * Creates a new pair / tuple with two given values.
     *
     * @param val1 The first value to save.
     * @param val2 The second value to save.
     */
    public Tuple(T val1, U val2)
    {
        setVal1(val1);
        setVal2(val2);
    }

    /**
     * Creates a new pair / tuple with given map entry.
     *
     * @param entry The map entry to construct a tuple from.
     */
    public Tuple(Map.Entry<T, U> entry)
    {
        setVal1(entry.getKey());
        setVal2(entry.getValue());
    }

    public T getVal1()
    {
        return val1;
    }

    public void setVal1(T val1)
    {
        this.val1 = val1;
    }

    public U getVal2()
    {
        return val2;
    }

    public void setVal2(U val2)
    {
        this.val2 = val2;
    }

    @Override
    public String toString()
    {
        return "[ " + val1 + " | " + val2 + " ]";
    }
}
