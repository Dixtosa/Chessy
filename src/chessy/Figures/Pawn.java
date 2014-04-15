package chessy.Figures;
import chessy.Main.GamePlay;
import chessy.Misc.*;
import java.util.ArrayList;



public class Pawn extends Figure
{
    boolean Moved = false;
    public Pawn(Figure.Color clr, Position p, GamePlay gp)
    {
        setColor(clr);
        figureType = Figure.FigureType.PAWN;
        setPosition(p);
        setGame(gp);
    }

    @Override
    public ArrayList<Position> generateThreateningPositions()
    {
        ArrayList<Position> ansList = new ArrayList<Position>();
        Position forward1, forward2, killLeft, killRight;
        if (clr == Figure.Color.BLACK)
        {
            forward1 = Position.add(curPosition, new Position(0, 1));
            forward2 = Position.add(curPosition, new Position(0, 2));
            killLeft = Position.add(curPosition, new Position(1, 1));
            killRight = Position.add(curPosition, new Position(-1, 1));
        }
        else
        {
            forward1 = Position.add(curPosition, new Position(0, -1));
            forward2 = Position.add(curPosition, new Position(0, -2));
            killLeft = Position.add(curPosition, new Position(1, -1));
            killRight = Position.add(curPosition, new Position(-1, -1));
        }
        
        if (checkBoundAndColor(forward1) && curGame.getFigureType(forward1)==null)
        {
            ansList.add(forward1);
            if(!Moved && checkBoundAndColor(forward2) && curGame.getFigureType(forward2)==null)
            {
                ansList.add(forward2);
            }
        }

        if (checkBoundAndColor(killRight) && curGame.getFigureType(killRight)!=null) ansList.add(killRight);
        if (checkBoundAndColor(killLeft) && curGame.getFigureType(killLeft)!=null) ansList.add(killLeft);
        
        //cecxlovani kdelei TO BE DONE
        return ansList;
    }
    public void moveTo(Position dest)
    {        
        Moved = true;
        super.moveTo(dest);
    }
}