package utils;

import org.junit.jupiter.api.Test;
import utils.general.Utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTests
{
    @Test
    public void commutHash()
    {
        String s1 = "ABCabc";
        String s2 = "dDeEfF";
        long hash1 = Utils.commutHash(s1, s2);
        long hash2 = Utils.commutHash(s2, s1);

        assertEquals(hash1, hash2);

        s1 = "###ääüüßß";
        s2 = "###ääüüßß";
        hash1 = Utils.commutHash(s1, s2);
        hash2 = Utils.commutHash(s2, s1);
        long hash3 = Utils.commutHash(s1, s1);
        long hash4 = Utils.commutHash(s2, s2);

        assertEquals(hash1, hash2);
        assertEquals(hash2, hash3);
        assertEquals(hash3, hash4);
    }
}
