package GAD;

import GAD.algorithms.helper.*;
import GAD.algorithms.helper.defaultImpl.*;

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

    public int MAX_SUBSTRUCTURE_SIZE = 31;

    public final IBestSubstructureFinder BEST_SUBSTRUCTURE_FINDER = new BestSubstructureFinder();
    public final ICompressor COMPRESSOR = new Compressor();
    public final IIncludedSubstructuresFinder INCLUDED_SUBSTRUCTURE_FINDER = new IncludedSubstructuresFinder();
    public final IInstanceFinder INSTANCE_FINDER = new InstanceFinder();
    public final IIsomorphismDetector ISOMORPHISM_DETECTOR = new IsomorphismDetector();
    public final IStructureExtender STRUCTURE_EXTENDER = new StructureExtender();
    public final ITransformationCostCalculator TRANSFORMATION_COST_CALCULATOR = new TransformationCostCalculator();
}
