package general;

public class Function<T> {
    public int f(T element1, T element2) {
        return element1.hashCode() - element2.hashCode();
    }
}
