import org.junit.Test;
import org.junit.Assert;

import java.util.Comparator;

public class OffByN implements CharacterComparator {
    public OffByN(int N) {
        this.N = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        return (x - y == N) || (y - x == N);
    }

    private final int N;

}
