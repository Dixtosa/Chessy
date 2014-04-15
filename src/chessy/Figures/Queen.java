package chessy.Figures;
import chessy.Main.GamePlay;
import chessy.Misc.*;
import java.util.ArrayList;

/**
 *
 * @author Gio Eufshi
 */
public class Queen extends Figure
{
    private Rook mockRook = null;
    private Bishop mockBishop = null;
    public Queen(Figure.Color clr, Position p, GamePlay gp)
    {
        setColor(clr);
        setFigureType(Figure.FigureType.QUEEN);
        setPosition(p);
        setGame(gp);
        
        mockRook = new Rook(clr, p, curGame);
        mockBishop = new Bishop(clr, p, curGame);
    }
    
    @Override
    public ArrayList<Position> generateThreateningPositions()
    {
        ArrayList<Position> ans1 = mockBishop.generateThreateningPositions();
        ArrayList<Position> ans2 = mockRook.generateThreateningPositions();
        
        ArrayList<Position> ansList = new ArrayList<Position>(ans1);
        ansList.addAll(ans2);
        return ansList;
    }
    @Override
    public void moveTo(Position dest)
    {
        super.moveTo(dest);
        mockBishop.moveTo(dest);
        mockRook.moveTo(dest);
    }
}
