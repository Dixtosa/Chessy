package chessy.Figures;
import chessy.Main.GamePlay;
import chessy.Misc.*;
import java.util.ArrayList;


/**
 *
 * @author Gio Eufshi
 */
public class Bishop extends Figure
{
    public Bishop(Figure.Color clr, Position p, GamePlay gp)
    {
        setColor(clr);
        figureType = Figure.FigureType.BISHOP;
        setPosition(p);
        setGame(gp);
    }
    @Override
    public ArrayList<Position> generateThreateningPositions()
    {
        ArrayList<Position> ansList = new ArrayList<Position>();
        //rigt, left
        for(int i = 1; i <= 7; i++)
        {
            Position dest = Position.add(curPosition, new Position(i, i));
            if (!checkBoundAndColor(dest)) break;
            ansList.add(dest);
            if (curGame.getFigureType(dest)!=null) break;
        }
        for(int i = 1; i <= 7; i++)
        {
            Position dest = Position.add(curPosition, new Position(-i, -i));
            if (!checkBoundAndColor(dest)) break;
            ansList.add(dest);
            if (curGame.getFigureType(dest)!=null) break;
        }
            
            //up, down
        for(int i = 1; i <= 7; i++)
        {
            Position dest = Position.add(curPosition, new Position(i, -i));
            if (!checkBoundAndColor(dest)) break;
            ansList.add(dest);
            if (curGame.getFigureType(dest)!=null) break;
        }
        for(int i = 1; i <= 7; i++)
        {
            Position dest = Position.add(curPosition, new Position(-i, i));
            if (!checkBoundAndColor(dest)) break;
            ansList.add(dest);
            if (curGame.getFigureType(dest)!=null) break;
        }
        return ansList;
    }
}
