package pucrs.br.genetic.interfaces;

import javax.swing.*;
import java.awt.*;

public class DrawMaze extends JPanel {
    private int[][] maze;

    public DrawMaze(int[][] maze, String title){
        this.maze = maze;
        JFrame f = new JFrame();
        f.getContentPane().add(this);
        setBackground(Color.WHITE);
        f.setTitle(title);
        f.setSize(400,400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setMaze(int [][] otherMaze){
        for (int i = 0; i < otherMaze.length; i++){
            for (int j = 0; j < otherMaze[0].length; j++){
                maze[i][j] = otherMaze[i][j];
            }
        }
    }

    public void paintComponent(Graphics g)
    { int frame_width = 600;
        int frame_height = 500;
        g.setColor(Color.white);  // paint the white background:
        g.fillRect(0, 0, frame_width, frame_height);
        g.setColor(Color.red);

        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Color color = switch (maze[row][col]) {
                    case 1 -> Color.BLACK;
                    case 2 -> Color.RED;
                    case 4 -> Color.BLUE;
                    case 9 -> Color.GREEN;
                    default -> Color.WHITE;
                };
                g.setColor(color);
                g.fillRect(30 * col, 30 * row, 30, 30);
                g.setColor(Color.BLACK);
                g.drawRect(30 * col, 30 * row, 30, 30);
            }
        }

    }


}
