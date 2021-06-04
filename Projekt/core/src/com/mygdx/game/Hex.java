package com.mygdx.game;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.awt.*;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Hex {


    /**********************************
     This is the main class of a Java program to play a game based on hexagonal tiles.
     The mechanism of handling hexes is in the file hexmech.java.

     Written by: M.H.
     Date: December 2012

     ***********************************/
        Hex(SpriteBatch batch) {
            initGame();
        }


        //constants and global variables]
        final static Color COLOURCELL =  Color.GRAY;
        final static Color COLOURGRID =  Color.BLACK;
        final static Color COLOURONE = new Color(0,255,255,200);
        final static Color COLOURONETXT = Color.BLUE;
        final static Color COLOURTWO = new Color(0,0,0,200);
        final static Color COLOURTWOTXT = new Color(255,100,255);
        final static int EMPTY = 0;
        final public static int BSIZE = 49; //board size.
        final static int HEXSIZE = 20;	//hex size in pixels
        final static int BORDERS = 15;


        static public int[][] board = new int[BSIZE][BSIZE];

        static private void limit(){
            int zm = 0;
            for (int i=2;i<32;i++) {
                zm = 0;
                for (int j=29;j>=0;j--) {
                    if(i+27 <= j + zm) {
                        board[i][j] = -1;
                    }
                    zm+=2;
                }
            }
            for (int i=4;i<20;i++) {
                zm = 0;
                for (int j = 29; j <= 39; j++) {
                    if (i + 25 <= j) {
                        board[i][j] = -1;
                    }
                    zm += 1;
                }
            }
            for (int i=14;i<20;i++) {
                zm = 0;
                for (int j=40;j>=31;j--) {
                    if(i + 27 >= j + zm) {
                        board[i][j] = -1;
                    }
                    zm+=2;
                }
            }
            for (int i=20;i<36;i++) {
                zm = 0;
                for (int j = 34; j <= 48; j++) {
                    if (i + 14 <= j) {
                        board[i][j] = -1;
                    }
                    zm += 1;
                }
            }
            for (int i=38;i<42;i++) {
                zm = 0;
                for (int j=48;j>=47;j--) {
                    if(i + 10 >= j + zm) {
                        board[i][j] = -1;
                    }
                    zm+=2;
                }
            }
            for (int i=26;i<48;i++) {
                zm = 0;
                for (int j = 27; j <= 48; j++) {
                    if (i + 1 >= j) {
                        board[i][j] = -1;
                    }
                    zm += 1;
                }
            }
            for (int i=26;i<48;i++) {
                zm = 0;
                for (int j=26;j>=0;j--) {
                    if(i >= j + zm) {
                        board[i][j] = -1;
                    }
                    zm+=2;
                }
            }
            for (int i=34;i<41;i++) {
                zm = 0;
                for (int j = 0; j <= 48; j++) {
                    if (i - 34 >= j) {
                        board[i][j] = -1;
                    }
                    zm += 1;
                }
            }
        }

        void initGame(){

            Hex.setSize(HEXSIZE);

            for (int i=0;i<BSIZE;i++) {
                for (int j=0;j<BSIZE;j++) {
                    board[i][j]=0;
                }
            }
            limit();
        }

        void drawHexes(SpriteBatch batch, OrthographicCamera camera){
            for (int i=0;i<BSIZE;i++) {
                for (int j=0;j<BSIZE;j++) {
                    Hex.drawHex(i,j,batch, camera,0);
                }
            }
        }

        public static int s=0;	// length of one side
        public static int t=0;	// short side of 30o triangle outside of each hex
        public static int r=0;	// radius of inscribed circle (centre to middle of each side). r= h/2
        public static int h=0;	// height. Distance between centres of two adjacent hexes. Distance between two opposite sides in a hex.

        /** This functions takes the Side length in pixels and uses that as the basic dimension of the hex.
         It calculates all other needed constants from this dimension.
         */
        public static void setSize(int height) {
            h = height;
            r = h/2;
            s = (int) ((h / 1.73205)) - 3;
            t = (int) (r / 1.73205);
        }

        /*********************************************************
         Name: hex()
         Parameters: (x0,y0) This point is normally the top left corner
         of the rectangle enclosing the hexagon.
         However, if XYVertex is true then (x0,y0) is the vertex of the
         top left corner of the hexagon.
         Returns: a polygon containing the six points.
         Called from: drawHex(), fillhex()
         Purpose: This function takes two points that describe a hexagon
         and calculates all six of the points in the hexagon.
         *********************************************************/
        public static float[] hex (int x0, int y0) {

            int y = y0 + BORDERS;
            int x = x0 + BORDERS;

            if (s == 0  || h == 0) {

                return new float[0];
            }

            float[] cxy = new float[] {x,y+t,x+r,y,x+h,y+t,x+h,y+t+s,x+r,y+s+t+t,x,y+t+s};
            return cxy;
        }
        public static int hexCenterx(int x0,int y0){
            int x1 = x0 * h + (y0%2) * h/2;
            int x = x1+r+3;

            return x;
        }
        public static int hexCentery(int y0){
            int y1 = y0 * (s+t);
            int y = y1+s+t+t;

            return y;
    }


        /********************************************************************
         Name: drawHex()
         Parameters: (i,j) : the x,y coordinates of the inital point of the hexagon
         g2: the Graphics2D object to draw on.
         Returns: void
         Calls: hex()
         Purpose: This function draws a hexagon based on the initial point (x,y).
         The hexagon is drawn in the colour specified in hexgame.COLOURELL.
         *********************************************************************/
        public static void drawHex(int i, int j, SpriteBatch batch, OrthographicCamera camera,int hero) {
            int y = j * (s+t);
            int x = i * h + (j%2) * h/2;

            if(board[i][j] == -1)
            {
                return;
            }
            else {
                camera.update();
                ShapeRenderer hex = new ShapeRenderer();
                ShapeRenderer tri = new ShapeRenderer();

                hex.setProjectionMatrix(batch.getProjectionMatrix());
                tri.setProjectionMatrix(batch.getProjectionMatrix());

                hex.begin(ShapeRenderer.ShapeType.Line);
                hex.setColor(com.badlogic.gdx.graphics.Color.BLACK);
                tri.begin(ShapeRenderer.ShapeType.Filled);
                tri.setColor(new com.badlogic.gdx.graphics.Color(0x4D80BCFF));//O TU SIĘ WYBIERA KOLORY
                hex.polygon(Hex.hex(x, y));

                if (hero == 1) {
                    float[] points = hex(x, y);
                    for (int k = 0; k < 10; k = k + 2) {
                        tri.triangle(points[k], points[k + 1], points[k + 2], points[k + 3], x + r + BORDERS, y + t + (s / 2) + BORDERS);
                    }
                    tri.triangle(points[0], points[1], points[10], points[11], x + r + BORDERS, y + t + (s / 2) + BORDERS);
                    tri.end();
                }
                else if(hero == 2){
                    tri.setColor(new com.badlogic.gdx.graphics.Color(0xA74B4DFF));
                    float[] points = hex(x, y);
                    for (int k = 0; k < 10; k = k + 2) {
                        tri.triangle(points[k], points[k + 1], points[k + 2], points[k + 3], x + r + BORDERS, y + t + (s / 2) + BORDERS);
                    }
                    tri.triangle(points[0], points[1], points[10], points[11], x + r + BORDERS, y + t + (s / 2) + BORDERS);
                    tri.end();
                }

                hex.end();
                hex.dispose();
                tri.dispose();
            }
        }
}

