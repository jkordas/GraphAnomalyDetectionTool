import GAD.algorithms.utils.CombinationGenerator;
import GAD.algorithms.utils.PermutationGenerator;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by jkordas on 10/05/16.
 */
public class GeneratorsTest {
    @Test
    public void combinationTest() {
        CombinationGenerator g = new CombinationGenerator(7, 4);

        int count = 0;
        while (g.hasNext()) {
            g.next();
            count++;
        }

        assertEquals(35, count);
        assertEquals("[1, 1, 1, 1, 0, 0, 0]", Arrays.toString(g.getState()));
    }

    @Test
    public void permutationTest() {
        PermutationGenerator generator = new PermutationGenerator(6);

        int count = 0;
        while (generator.hasNext()) {
            count++;
            generator.next();
        }

        assertEquals(720, count);
        assertEquals("[5, 4, 3, 2, 1, 0]", Arrays.toString(generator.getState()));
    }


}
