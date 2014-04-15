package chessy.Figures;
import chessy.Main.GamePlay;
import chessy.Misc.*;
import java.util.ArrayList;


/**
 *
 * @author Gio Eufshi
 */
public class King extends Figure
{
    boolean Moved = false;
    public King(Figure.Color clr, Position p, GamePlay gp)
    {
        setColor(clr);
        figureType = Figure.FigureType.KING;
        setPosition(p);
        setGame(gp);
    }
    
    
    @Override
    public ArrayList<Position> generateThreateningPositions()
    {
        ArrayList<Position>  ansList = new ArrayList<Position>();
        ArrayList<Position>  tmp = new ArrayList<Position>();
        
        tmp.add(Position.add(curPosition, new Position(1, 0)));
        tmp.add(Position.add(curPosition, new Position(1, 1)));
        tmp.add(Position.add(curPosition, new Position(0, 1)));
        tmp.add(Position.add(curPosition, new Position(-1, 1)));
        
        tmp.add(Position.add(curPosition, new Position(-1, 0)));
        tmp.add(Position.add(curPosition, new Position(-1, -1)));
        tmp.add(Position.add(curPosition, new Position(0, -1)));
        tmp.add(Position.add(curPosition, new Position(1, -1)));
        
        
        for(Position dest: tmp)
        {
            if (checkBoundAndColor(dest)) ansList.add(dest);
        }
        
        return ansList;
    }
    public ArrayList<Position> generateMovablePositions()
    {
        ArrayList<Position> ans = super.generateMovablePositions();
        
        if (!Moved)
        {
            curGame.
        }
        
        return ans;
    }
    public void moveTo(Position dest)
    {        
        Moved = true;
        super.moveTo(dest);
    }
}
