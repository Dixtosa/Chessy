package chessy.Main;

import chessy.Misc.Position;
import chessy.Figures.*;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author Gio Eufshi
 */
public class GamePlay
{
    private Figure[][] board; //IMPORTANT NOTE: BASIS IS LEFT UP
    
    private Figure.Color[] whosMove = {Figure.Color.WHITE, Figure.Color.BLACK};
    private int counter;
        
    public boolean canMoveTo(Position source, Position destination)
    {
        ArrayList<Position> arrList = getFigureObj(source).generateMovablePositions();
        
        for(Position p: arrList)
        {
            if (p.x == destination.x && p.y == destination.y)
                return true;
        }
        return false;
    }
    public void moveTo(Position source, Position destination)
    {
        Figure curFigure = board[source.x][source.y];
        
        if(curFigure instanceof Pawn)
        {
            if (curFigure.getColor() == Figure.Color.WHITE && destination.y == 1)
            {
                curFigure = new Queen(Figure.Color.WHITE, destination, this);
            }
            if (curFigure.getColor() == Figure.Color.BLACK && destination.y == 8)
            {
                curFigure = new Queen(Figure.Color.BLACK, destination, this);
            }
        }
        
        curFigure.moveTo(destination);
        
        board[source.x][source.y] = null;
        board[destination.x][destination.y] = curFigure;
        
        counter++; counter%=2;
    }
    
    public boolean yourTurnYourMajestyComputer()
    {
        ArrayList<Position> ableToMoveFigures = new ArrayList<Position> ();
        Random rnd = new Random();
        
        for (int i = 1; i <=8; i++) {
            for (int j = 1; j <= 8; j++) {
                if (getFigureColor(i, j) == Figure.Color.BLACK 
                        && getFigureObj(i, j).generateMovablePositions().size()>0)
                {
                    ableToMoveFigures.add(new Position(i, j));
                }
            }
        }
        Position rndPosition;
        try
        {
            rndPosition = ableToMoveFigures.get(rnd.nextInt(ableToMoveFigures.size()));
        }
        catch (IllegalArgumentException e)
        {
            return true;
        }
        
        ArrayList<Position> ableToGo = getFigureObj(rndPosition).generateMovablePositions();
        
        Position rndMove = ableToGo.get(rnd.nextInt(ableToGo.size()));

        moveTo(rndPosition, rndMove);
        return false;
    }
    public Figure getFigureObj(Position p)
    {
        return board[p.x][p.y];
    }
    public Figure getFigureObj(int i, int j)
    {
        return board[i][j];
    }
    public Figure.FigureType getFigureType(Position p)
    {
        return getFigureType(p.x, p.y);
    }
    public Figure.Color getFigureColor(Position p)
    {
        return getFigureColor(p.x, p.y);
    }
    public Figure.FigureType getFigureType(int i , int j)
    {
        if (board[i][j]==null)
        {
            return null;
        }
        return board[i][j].getFigureType();
    }
    public Figure.Color getFigureColor(int i , int j)
    {
        if (board[i][j]==null)
        {
            return null;
        }
        return board[i][j].getColor();
    }
    void loadDebugPositions()
    {
        Figure.Color tmpClr;
        int tmpX, tmpY;
        //whities
        tmpClr = Figure.Color.BLACK;
        
        
        tmpX = 4;
        tmpY = 1;
        //board[tmpX][tmpY] = new Queen(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
        board[tmpX][tmpY] = new King(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
        
        tmpClr = Figure.Color.WHITE;
        board[1][2] = new Pawn( tmpClr, new Position(1, 2) , this);
        
        tmpX = 4;
        tmpY = 3;
        board[tmpX][tmpY] = new King(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
        //board[tmpX][tmpY] = new Queen(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
    }
    void loadStartingPositions()
    {
        Figure.Color tmpClr;
        int tmpX, tmpY;
        //whities
        tmpClr = Figure.Color.BLACK;
        for(int i = 1; i<=8; i++)
        {
            board[i][2] = new Pawn( tmpClr, new Position(i, 2) , this);
        }
        
        tmpX = 1;
        tmpY = 1;
        board[tmpX][tmpY] = new Rook(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
        board[tmpX][tmpY] = new Knight(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
        board[tmpX][tmpY] = new Bishop(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
        board[tmpX][tmpY] = new Queen(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
        board[tmpX][tmpY] = new King(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
        board[tmpX][tmpY] = new Bishop(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
        board[tmpX][tmpY] = new Knight(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
        board[tmpX][tmpY] = new Rook(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
        
        
        
        ////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////
        //niggas
        
        tmpClr = Figure.Color.WHITE;
        for(int i = 1; i<=8; i++)
        {
            board[i][7] = new Pawn( tmpClr, new Position(i, 7), this);
        }
        
        tmpX = 1;
        tmpY = 8;
        board[tmpX][tmpY] = new Rook(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
        board[tmpX][tmpY] = new Knight(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
        board[tmpX][tmpY] = new Bishop(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
        board[tmpX][tmpY] = new Queen(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
        board[tmpX][tmpY] = new King(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
        board[tmpX][tmpY] = new Bishop(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
        board[tmpX][tmpY] = new Knight(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
        board[tmpX][tmpY] = new Rook(tmpClr, new Position(tmpX,tmpY), this);tmpX++;
    }
    
    
    public boolean isSafeToGo(Position from, Position to)
    {
        Figure fromFig = getFigureObj(from);
        Figure toFig = getFigureObj(to);
        board[from.x][from.y] = null;
        board[to.x][to.y] = fromFig;
        boolean davicavit = !isCheck();
        board[from.x][from.y] = fromFig;
        board[to.x][to.y] = toFig;
        if (davicavit) return true;
        return false;
    }
    public boolean isCheck()
    {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                Figure figS = getFigureObj(i, j);
                if (figS!=null && figS.getColor() != whosMove()){
                    ArrayList<Position> curList = figS.generateThreateningPositions();
                    for (int k = 0; k < curList.size(); k++) {
                        Figure figD = getFigureObj(curList.get(k));
                        if (figD == null) continue;
                        if (figD.getFigureType() == Figure.FigureType.KING)
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean canDefend()
    {
        //if(1+1 == 2) return true;
        for (int i = 1; i <=8; i++) {
            for (int j = 1; j <= 8; j++) {
                Figure defenderFig = getFigureObj(i, j);
                if (defenderFig!=null && defenderFig.getColor() == whosMove()){
                    ArrayList<Position> curList = defenderFig.generateThreateningPositions();
                    
                    for (int k = 0; k < curList.size(); k++)
                    {
                        if (isSafeToGo(new Position(i, j), curList.get(k)))
                            return true;
                    }
                }
            }
        }
        return false;
    }
    public Figure.Color whosMove()
    {
        return whosMove[counter];
    }
    public void startGame()
    {
        counter=0;
        loadStartingPositions();
    }
    
    public GamePlay()
    {
        board = new Figure[9][];
        for(int i = 1; i<=8; i++)
        {
            board[i] = new Figure[9];
            for(int j = 1; j<=8; j++)
            {
               board[i][j] = null;
            }
        }
    }
}
