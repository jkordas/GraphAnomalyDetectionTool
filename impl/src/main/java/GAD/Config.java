package GAD;

import GAD.algorithms.helper.IBestSubstructureFinder;
import GAD.algorithms.helper.ICompressor;
import GAD.algorithms.helper.IIncludedSubstructuresFinder;
import GAD.algorithms.helper.defaultImpl.BestSubstructureFinder;
import GAD.algorithms.helper.defaultImpl.Compressor;
import GAD.algorithms.helper.defaultImpl.IncludedSubstructuresFinder;

/**
 * Created by jkordas on 05/05/16.
 */
public class Config {
    private static Config ourInstance = new Config();

    public static Config getInstance() {
        return ourInstance;
    }

    private Config() {
    }

    public int MAX_SUBSTRUCTURE_SIZE = 8;

    public IBestSubstructureFinder BEST_SUBSTRUCTURE_FINDER = new BestSubstructureFinder();
    public ICompressor COMPRESSOR = new Compressor();
    public IIncludedSubstructuresFinder INCLUDED_SUBSTRUCTURE_FINDER = new IncludedSubstructuresFinder();
}
