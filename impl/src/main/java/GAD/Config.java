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

    public final int MAX_SUBSTRUCTURE_SIZE = 8;
    public final int BEST_SUBSTRUCTURES_LIMIT = 1;
    public final int GBAD_P_THRESHOLD = 20;
    public final int GBAD_MDL_THRESHOLD = 10;
    public final int GBAD_MPS_THRESHOLD = 10;

    public final IBestSubstructureFinder BEST_SUBSTRUCTURE_FINDER = new BestSubstructureFinder();
    public final ICompressor COMPRESSOR = new Compressor();
    public final IIncludedSubstructuresFinder INCLUDED_SUBSTRUCTURE_FINDER = new IncludedSubstructuresFinder();
    public final IInstanceFinder INSTANCE_FINDER = new InstanceFinder();
    public final IIsomorphismDetector ISOMORPHISM_DETECTOR = new IsomorphismDetector();
    public final IStructureExtender STRUCTURE_EXTENDER = new StructureExtender();
    public final ITransformationCostCalculator TRANSFORMATION_COST_CALCULATOR = new TransformationCostCalculator();
}
