public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); ++i) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }

    private boolean isPalindrome(Deque<Character> charDeque) {
        if (charDeque.size() < 2) {
            return true;
        }
        if (charDeque.removeFirst() != charDeque.removeLast()) {
            return false;
        }
        return isPalindrome(charDeque);
    }

    private boolean isPalindrome(Deque<Character> charDeque, CharacterComparator cc) {
        if (charDeque.size() < 2) {
            return true;
        }
        if (!cc.equalChars(charDeque.removeFirst(), charDeque.removeLast())) {
            return false;
        }
        return isPalindrome(charDeque, cc);
    }
}
