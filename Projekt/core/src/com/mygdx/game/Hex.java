package com.mygdx.game;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


/**
 * Odbywa sie tu rysowanie hexow i cala matematyka z tym zwiazana.
 */
public class Hex {

    /**
     * Znajduje sie tutaj funkcja ktora ma zainicjowac gre.
     */
        Hex() {
            initGame();
        }


    /**
     * Przechowuje wielkosc planszy.
     */
        final public static int BSIZE = 49; //board size.
    /**
     * Wielkosc hexu w pixelach.
     */
        final static int HEXSIZE = 20;	//hex size in pixels
    /**
     * Margines ekranu.
     */
        final static int BORDERS = 15;


    /**
     * Tablica przewujaca informacje o poszczegolnych hexach na mapie.
     * Hex z wartoscia:
     * -1 - nie mozna na niego wejsc,
     * 0 - mozna na niego wejsc.
     * 1 - hex z przeciwnikiem.
     */
    static public int[][] board = new int[BSIZE][BSIZE];


    /**
     * Funkcja tworzaca ograniczenia w poruszaniu sie na mapie
     */
        static private void limit(){
            int zm ;
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

    /**
     * Funkcja majaca na celu wypelnienie tablicy hexow wartoscia 0 i ustawienie limitow w poruszaniu sie.
     * @see Hex#board
     */
        void initGame(){

            Hex.setSize(HEXSIZE);

            for (int i=0;i<BSIZE;i++) {
                for (int j=0;j<BSIZE;j++) {
                    board[i][j]=0;
                }
            }
            limit();
        }


    /**
     * Dlugosc boku hexa.
     */
        public static int s=0;	// length of one side
    /**
     * Krotszy bok trojkata dopelniajacego hex.
     */
        public static int t=0;	// short side of 30o triangle outside of each hex
    /**
     * Promien kola wpisanego w hex.
     */
        public static int r=0;	// radius of inscribed circle (centre to middle of each side). r= h/2
    /**
     * Srednica kola wpisanego w hex.
     */
        public static int h=0;	// height. Distance between centres of two adjacent hexes. Distance between two opposite sides in a hex.


    /**
     * Funkcjia ustawiajaca wymiary hexa.
     * @param height Wysokosc hexa.
     */
        public static void setSize(int height) {
            h = height;
            r = h/2;
            s = (int) ((h / 1.73205)) - 3;
            t = (int) (r / 1.73205);
        }


    /**
     * Funkcja wylicajaca wierzcholki hexa.
     * @param x0 wspolrzedne x pierwszego wierzcholka hexu.
     * @param y0 wspolrzedne y pierwszego wierzcholka hexu.
     * @return Zwraca tablice ktora zawiera te punkty
     */
        public static float[] hex (int x0, int y0) {

            int y = y0 + BORDERS;
            int x = x0 + BORDERS;

            if (s == 0  || h == 0) {

                return new float[0];
            }

            return new float[] {x,y+t,x+r,y,x+h,y+t,x+h,y+t+s,x+r,y+s+t+t,x,y+t+s};
        }

    /**
     * @param x0 pozycja x w tablicy hexow.
     * @param y0 pozycja y w tablicy hexow.
     * @return Zwraca wspolrzedna x srodka hexa.
     */
        public static int hexCenterx(int x0,int y0){
            int x1 = x0 * h + (y0%2) * h/2;

            return x1+r+3;
        }

    /**
     * @param y0 pozycja y w tablicy hexow.
     * @return Zwraca wspolrzedna x srodka hexa.
     */
        public static int hexCentery(int y0){
            int y1 = y0 * (s+t);

            return y1+s+t+t;
    }


    /**
     * Funkcja odpowiadajaca za rysowanie odpowiednich hexow.
     * @param i przekazuje pozycje x hexow w tablicy.
     * @param j przekazuje pozycje y hexow w tablicy.
     * @param batch warstwa na ktorej rysujemy hexy.
     * @param camera glowna kamera gry.
     * @param hero flaga odpowiadajaca za kolor hexa.
     */
        public static void drawHex(int i, int j, SpriteBatch batch, OrthographicCamera camera,int hero) {
            int y = j * (s+t);
            int x = i * h + (j%2) * h/2;

            if (board[i][j] != -1) {
                camera.update();
                ShapeRenderer hexy = new ShapeRenderer();
                ShapeRenderer tri = new ShapeRenderer();

                hexy.setProjectionMatrix(batch.getProjectionMatrix());
                tri.setProjectionMatrix(batch.getProjectionMatrix());

                hexy.begin(ShapeRenderer.ShapeType.Line);
                hexy.setColor(com.badlogic.gdx.graphics.Color.BLACK);
                tri.begin(ShapeRenderer.ShapeType.Filled);
                tri.setColor(new com.badlogic.gdx.graphics.Color(0x4D80BCFF));
                hexy.polygon(Hex.hex(x,y));
                float[] points = Hex.hex(x, y);


                if(hero == 2){
                    tri.setColor(new com.badlogic.gdx.graphics.Color(0xA74B4DFF));
                }

                    for (int k = 0; k < 10; k = k + 2) {
                        tri.triangle(points[k], points[k + 1], points[k + 2], points[k + 3], x + r + BORDERS, y + t + ((float)s / 2) + BORDERS);
                    }
                    tri.triangle(points[0], points[1], points[10], points[11], x + r + BORDERS, y + t + ((float)s / 2) + BORDERS);
                    tri.end();

                hexy.end();
                hexy.dispose();
                tri.dispose();
            }
        }
}

