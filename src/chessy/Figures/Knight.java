package chessy.Figures;
import chessy.Main.GamePlay;
import chessy.Misc.*;
import java.util.ArrayList;


/**
 *
 * @author Gio Eufshi
 */
public class Knight extends Figure
{
    
    
    public Knight(Figure.Color clr, Position p, GamePlay gp)
    {
        setColor(clr);
        figureType = Figure.FigureType.KNIGHT;
        setPosition(p);
        setGame(gp);
    }

    @Override
    public ArrayList<Position> generateThreateningPositions()
    {
        ArrayList<Position>  ansList = new ArrayList<Position>();
        ArrayList<Position>  tmp = new ArrayList<Position>();
        
        tmp.add(Position.add(curPosition, new Position(1, 2)));
        tmp.add(Position.add(curPosition, new Position(1, -2)));
        tmp.add(Position.add(curPosition, new Position(2, 1)));
        tmp.add(Position.add(curPosition, new Position(2, -1)));
        
        tmp.add(Position.add(curPosition, new Position(-1, 2)));
        tmp.add(Position.add(curPosition, new Position(-1, -2)));
        tmp.add(Position.add(curPosition, new Position(-2, 1)));
        tmp.add(Position.add(curPosition, new Position(-2, -1)));
        
        
        for(Position dest: tmp)
        {
            if (checkBoundAndColor(dest)) ansList.add(dest);
        }
        
        return ansList;
    }
}