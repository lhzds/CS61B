import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("abcddcba"));
        assertTrue(palindrome.isPalindrome("abcdcba"));
        assertFalse(palindrome.isPalindrome("abcdecba"));
        assertTrue(palindrome.isPalindrome("aa"));
        assertFalse(palindrome.isPalindrome("ac"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));

        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("acb", obo));
        assertTrue(palindrome.isPalindrome("acdb", obo));
        assertFalse(palindrome.isPalindrome("abcdecba", obo));
        assertTrue(palindrome.isPalindrome("ab", obo));
        assertFalse(palindrome.isPalindrome("ac", obo));
        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome("", obo));
    }
}
