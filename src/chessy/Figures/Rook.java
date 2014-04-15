package chessy.Figures;
import chessy.Main.GamePlay;
import chessy.Misc.*;
import java.util.ArrayList;

/**
 *
 * @author Gio Eufshi
 */
public class Rook extends Figure
{
    boolean Moved = false;
    public Rook(Figure.Color clr, Position p, GamePlay pGame)
    {
        setColor(clr);
        figureType = Figure.FigureType.ROOK;
        setPosition(p);
        setGame(pGame);
    }
    

    @Override
    public ArrayList<Position> generateThreateningPositions()
    {
        ArrayList<Position> ansList = new ArrayList<Position>();
        //rigt, left
        for(int i = 1; i <= 7; i++)
        {
            Position dest = Position.add(curPosition, new Position(i, 0));
            if (!checkBoundAndColor(dest)) break;
            
            ansList.add(dest);
            
            if (curGame.getFigureType(dest)!=null) break;
        }
        for(int i = 1; i <= 7; i++)
        {
            Position dest = Position.add(curPosition, new Position(-i, 0));
            if (!checkBoundAndColor(dest)) break;
            ansList.add(dest);
            if (curGame.getFigureType(dest)!=null) break;
        }
            
            //up, down
        for(int i = 1; i <= 7; i++)
        {
            Position dest = Position.add(curPosition, new Position(0, i));
            if (!checkBoundAndColor(dest)) break;
            ansList.add(dest);
            if (curGame.getFigureType(dest)!=null) break;
        }
        for(int i = 1; i <= 7; i++)
        {
            Position dest = Position.add(curPosition, new Position(0, -i));
            if (!checkBoundAndColor(dest)) break;
            ansList.add(dest);
            if (curGame.getFigureType(dest)!=null) break;
        }
        return ansList;
    }
    public void moveTo(Position dest)
    {        
        Moved = true;
        super.moveTo(dest);
    }
}
