package chessy.Figures;

import chessy.Main.GamePlay;
import java.util.ArrayList;
import chessy.Misc.*;




public abstract class Figure
{
    static public enum Color
    {
        WHITE,
        BLACK;
    }
    static public enum FigureType
    {
        ROOK(0), KNIGHT(1), BISHOP(2), KING(3), QUEEN(4), PAWN(-1), NOFIGURE(-1);
        FigureType(int val)
        {
            value = val;
        }
        int value;
        public int getValue() { return value;}
    }
    
    protected Color clr;
    protected FigureType figureType;
    protected String picturePath;
    protected Position curPosition;
    protected GamePlay curGame = null;
    
    
    public void moveTo(Position dest)
    {        
        curPosition.x = dest.x;
        curPosition.y = dest.y;
    }
    protected final void setColor(Color clr)
    {
        this.clr = clr;
    }
    public Figure.Color getColor()
    {
        return clr;
    }
    protected void setFigureType(Figure.FigureType ft)
    {
        figureType = ft;
    }
    public Figure.FigureType getFigureType()
    {
        return figureType;
    }
    protected void setPosition(Position wantedPosition)
    {
        curPosition = wantedPosition;
    }
    protected void setGame(GamePlay pGame)
    {
        curGame = pGame;
    }
    protected boolean checkBoundAndColor(Position dest)
    {
        if (dest.x<1 || dest.x>8 || dest.y<1 || dest.y>8)
            return false;
        if (curGame.getFigureObj(dest) != null && curGame.getFigureColor(dest) == clr)
            return false;
        return true;
    }
    
    public abstract ArrayList<Position> generateThreateningPositions();
    public ArrayList<Position> generateMovablePositions()
    {
        ArrayList<Position> ans = new ArrayList<Position>();
        for(Position p: generateThreateningPositions())
        {
            if (curGame.isSafeToGo(curPosition, p))
            {
                ans.add(p);
            }
        }
        return ans;
    }
}
