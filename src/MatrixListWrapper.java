import java.io.Serializable;
import java.util.ArrayList;

public class MatrixListWrapper implements Serializable {
    public ArrayList<int[][]> MatrixList;
    public boolean UseThreading;

    public MatrixListWrapper(ArrayList<int[][]> matrixList, boolean useThreading){
        this.MatrixList = matrixList;
        this.UseThreading = useThreading;
    }
}
