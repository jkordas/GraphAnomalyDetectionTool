package performance;

import java.io.IOException;

/**
 * Created by jkordas on 15/08/16.
 */
public class Calculate {
    public static void main(String[] args) throws IOException {
        new PatternSearchStar().calculate();
        new PatternSearchRing().calculate();
        new PatternSearchComplete().calculate();
        new PatternSearchWheel().calculate();
        new PatternSearchLinear().calculate();

    }
}
