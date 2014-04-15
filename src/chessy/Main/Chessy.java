package chessy.Main;

import chessy.Figures.Figure;
import chessy.Misc.Position;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import javax.swing.border.LineBorder;

public class Chessy extends JFrame implements ActionListener {

    private JPanel boardPanel = new JPanel();
    private boolean alreadyClicked = false;
    private Position firstClickedPosition = new Position(0, 0);
    private ArrayList<Position> posMoves;
    private JButton[][] btnMatrix = null;
    private BufferedImage boardImg = null;
    private boolean playingWithComputer;

    {
        try
        {
            boardImg = ImageIO.read(new File("board.bmp"));
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(this, "/board.bmp not found!");
            System.exit(0);
        }
    }
    private GamePlay myGame = null;

    public Chessy() {
        setTitle("Chessy");
        setSize(30 + 75 * 8 + 100, 15 + 75 * 8 + 50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);




        getContentPane().add(boardPanel);
        boardPanel.setLayout(null);


        btnMatrix = new JButton[9][];
        for (int i = 1; i <= 8; i++) {
            btnMatrix[i] = new JButton[9];
            for (int j = 1; j <= 8; j++) {
                btnMatrix[i][j] = new JButton();
                btnMatrix[i][j].setBounds(10 + 75 * (i - 1), 10 + 75 * (j - 1), 73, 73);
                btnMatrix[i][j].addActionListener(this);
                btnMatrix[i][j].setName(i + "" + j);
                boardPanel.add(btnMatrix[i][j]);
            }
        }

        JButton startButton = new JButton("Start game");
        startButton.setBounds(10 + 8 * 75 + 10, 10, 100, 30);
        startButton.setActionCommand("STARG GAME");
        startButton.addActionListener(this);
        boardPanel.add(startButton);

        JButton playComp = new JButton("<html>Play </br> with </br> computer</html>");
        playComp.setBounds(10 + 8 * 75 + 10, 50, 100, 60);
        playComp.setActionCommand("PLAYCOMP");
        playComp.addActionListener(this);
        boardPanel.add(playComp);


        myGame = new GamePlay();
        myGame.loadDebugPositions();


        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                btnMatrix[i][j].setBorder(new LineBorder(Color.GRAY, 1));
            }
        }

        drawFigures();


    }

    public void startGame() {
        myGame = new GamePlay();
        myGame.startGame();

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                btnMatrix[i][j].setBorder(new LineBorder(Color.GRAY, 1));
            }
        }

        drawFigures();
    }

    private void drawFigures()
    {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                Figure.FigureType curFigureType = myGame.getFigureType(i, j);
                Figure.Color curFigureColor = myGame.getFigureColor(i, j);
                Position positionToCrop = new Position(0, 0);

                if (curFigureColor == Figure.Color.WHITE) {
                    positionToCrop.y += 75 * 3;
                }

                if (curFigureType == null) {
                    positionToCrop.y += 75 * 2;
                    if ((i + j) % 2 != 0) {
                        positionToCrop.x += 75;
                    }
                    drawFigure(positionToCrop, i, j);
                    continue;
                }

                if (curFigureType == Figure.FigureType.PAWN) {
                    positionToCrop.y += 75;
                    if ((i + j) % 2 == 0) {
                        positionToCrop.x += 75;
                    }
                    drawFigure(positionToCrop, i, j);
                    continue;
                }

                positionToCrop.x += curFigureType.getValue() * 75;
                if ((i + j) % 2 != curFigureType.getValue() % 2) {
                    positionToCrop.y += 2 * 75;
                    positionToCrop.x = 7 * 75 - positionToCrop.x;
                }
                drawFigure(positionToCrop, i, j);
            }
        }
    }

    private void drawFigure(Position positionToCrop, int i, int j) {
        BufferedImage figImage = boardImg.getSubimage(positionToCrop.x, positionToCrop.y, 75, 75);
        boolean flip = (myGame.whosMove() == Figure.Color.BLACK);

        if (flip)
            btnMatrix[9 - i][9 - j].setIcon(new ImageIcon(figImage));
        else
            btnMatrix[i][j].setIcon(new ImageIcon(figImage));
    }
    private void drawBorders(ArrayList<Position> posMoves, Color borderColor, int w)
    {
        boolean flip = (myGame.whosMove() == Figure.Color.BLACK);
        for (Position p : posMoves)
        {
            if (flip)
                btnMatrix[9-p.x][9-p.y].setBorder(new LineBorder(borderColor, w));
            else
                btnMatrix[p.x][p.y].setBorder(new LineBorder(borderColor, w));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("STARG GAME".equals(e.getActionCommand())) {
            playingWithComputer = false;
            startGame();
            return;
        }
        else if ("PLAYCOMP".equals(e.getActionCommand())) {
            playingWithComputer = true;
            startGame();
            return;
        }

        JButton callerButton = (JButton) e.getSource();


        int x = callerButton.getName().charAt(0) - '0';
        int y = callerButton.getName().charAt(1) - '0';
        boolean flip = (myGame.whosMove() == Figure.Color.BLACK);
        if (flip) {
            y = 9 - y;
            x = 9 - x;
        }
        Position clickedPosition = new Position(x, y);

        if (!alreadyClicked && myGame.getFigureObj(clickedPosition) == null) {
            return;
        }
        if (!alreadyClicked && myGame.getFigureObj(clickedPosition).getColor() != myGame.whosMove()) {
            return;
        }

        if (alreadyClicked) {
            if (myGame.canMoveTo(firstClickedPosition, clickedPosition)) {
                System.out.println("Yes I can");
                
                drawBorders(posMoves, Color.GRAY, 1);
                myGame.moveTo(firstClickedPosition, clickedPosition);

                if (myGame.isCheck()) {
                    if (!myGame.canDefend())
                    {
                        JOptionPane.showMessageDialog(this, "Checkmate!");
                        System.exit(0);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Check!");
                    }
                }

                if (playingWithComputer) {
                    if (myGame.yourTurnYourMajestyComputer())
                    {
                        JOptionPane.showMessageDialog(this, "Checkmate!");
                        System.exit(0);
                    }
                }
                drawFigures();
            }
            else
            {
                drawBorders(posMoves, Color.GRAY, 1);//shlis witelsac da lurjsac
                
                System.out.println("I can't!");
                alreadyClicked = !alreadyClicked;
                
                ///////
                if (myGame.getFigureObj(x, y)==null) return;
                if (firstClickedPosition.equals(new Position(x,y))) return;
                if (myGame.getFigureObj(x,y ).getColor()!=myGame.whosMove()) return;
                
                alreadyClicked = !alreadyClicked;
                callerButton.setBorder(new LineBorder(Color.RED, 2));
                firstClickedPosition.x = x;
                firstClickedPosition.y = y;

                posMoves = myGame.getFigureObj(clickedPosition).generateMovablePositions();
                drawBorders(posMoves, Color.BLUE, 2);
                posMoves.add(firstClickedPosition);

                System.out.println("first kliki dafiqsirda");
                ///////
                return;
            }
        }
        else 
        {
            callerButton.setBorder(new LineBorder(Color.RED, 2));
            firstClickedPosition.x = x;
            firstClickedPosition.y = y;
            
            posMoves = myGame.getFigureObj(clickedPosition).generateMovablePositions();
            drawBorders(posMoves, Color.BLUE, 2);
            posMoves.add(firstClickedPosition);

            System.out.println("first kliki dafiqsirda");
        }
        alreadyClicked = !alreadyClicked;
        System.out.println(x + ", " + y);
        System.out.println("alreadyClicked:" + alreadyClicked);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
            public void run() {
                Chessy ex = new Chessy();
                ex.setVisible(true);
            }
        });
    }
}
