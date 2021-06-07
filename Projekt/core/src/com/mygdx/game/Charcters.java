package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Klasa przechowujaca wszystkie pola i metody dotyczace bohatera gry.
 */
public class Charcters {
    /**
     * Flaga okreslajaca czy gracz atakuje.
     */
    boolean fAttack = false;
    /**
     * Aktualne polozenie x gracza.
     */
    int x = 43;
    /**
     * Poprzednie polozenie x gracza.
     */
    int prevx = 25;
    /**
     * Aktualne polozenie y gracza.
     */
    int y = 46;
    /**
     * Poprzednie polozenie y gracza.
     */
    int prevy = 30;
    /**
     * poczatkowa klataka animacji.
     */
    int aniStart = 0;
    /**
     * aktualna klatka animacji.
     */
    int aniCurr = 0;
    /**
     * Animacja ruchu bohatera.
     */
    TextureAtlas playerTexture;
    /**
     * Animacja ataku bohatera.
     */
    TextureAtlas attackTexture;
    /**
     * Tablica tekstur ruchu bohatera.
     */
    TextureRegion[] Warrior = new TextureRegion[24];
    /**
     * Tablica tekstur ataku bohatera
     */
    TextureRegion[] attack = new TextureRegion[24];
    /**
     * Tekstura ktora przekazujemy do pokazania na ekranie.
     */
    TextureRegion texture;

    /**
     * Funkcja odpowiadajaca z wczytanie kazdej klatki animacji z atlasu.
     */
    void Load_characters(){
        playerTexture = new TextureAtlas("Projekt.atlas");
        attackTexture = new TextureAtlas("Attack.atlas");
        Warrior[0] = playerTexture.findRegion("warriorlewo1");
        Warrior[1] = playerTexture.findRegion("warriorlewo2");
        Warrior[2] = playerTexture.findRegion("warriorlewo3");
        Warrior[3] = playerTexture.findRegion("warriorlewo4");
        Warrior[4] = playerTexture.findRegion("warriorlewodol1");
        Warrior[5] = playerTexture.findRegion("warriorlewodol2");
        Warrior[6] = playerTexture.findRegion("warriorlewodol3");
        Warrior[7] = playerTexture.findRegion("warriorlewodol4");
        Warrior[8] = playerTexture.findRegion("warriorlewogora1");
        Warrior[9] = playerTexture.findRegion("warriorlewogora2");
        Warrior[10] = playerTexture.findRegion("warriorlewogora3");
        Warrior[11] = playerTexture.findRegion("warriorlewogora4");
        Warrior[12] = playerTexture.findRegion("warriorprawo1");
        Warrior[13] = playerTexture.findRegion("warriorprawo2");
        Warrior[14] = playerTexture.findRegion("warriorprawo3");
        Warrior[15] = playerTexture.findRegion("warriorprawo4");
        Warrior[16] = playerTexture.findRegion("warriorprawodol1");
        Warrior[17] = playerTexture.findRegion("warriorprawodol2");
        Warrior[18] = playerTexture.findRegion("warriorprawodol3");
        Warrior[19] = playerTexture.findRegion("warriorprawodol4");
        Warrior[20] = playerTexture.findRegion("warriorprawogora1");
        Warrior[21] = playerTexture.findRegion("warriorprawogora2");
        Warrior[22] = playerTexture.findRegion("warriorprawogora3");
        Warrior[23] = playerTexture.findRegion("warriorprawogora4");
        attack[0] = attackTexture.findRegion("attacklewo1");
        attack[1] = attackTexture.findRegion("attacklewo2");
        attack[2] = attackTexture.findRegion("attacklewo3");
        attack[3] = attackTexture.findRegion("attacklewo4");
        attack[4] = attackTexture.findRegion("attacklewodol1");
        attack[5] = attackTexture.findRegion("attacklewodol2");
        attack[6] = attackTexture.findRegion("attacklewodol3");
        attack[7] = attackTexture.findRegion("attacklewodol4");
        attack[8] = attackTexture.findRegion("attacklewogora1");
        attack[9] = attackTexture.findRegion("attacklewogora2");
        attack[10] = attackTexture.findRegion("attacklewogora3");
        attack[11] = attackTexture.findRegion("attacklewogora4");
        attack[12] = attackTexture.findRegion("attackprawo1");
        attack[13] = attackTexture.findRegion("attackprawo2");
        attack[14] = attackTexture.findRegion("attackprawo3");
        attack[15] = attackTexture.findRegion("attackprawo4");
        attack[16] = attackTexture.findRegion("attackprawodol1");
        attack[17] = attackTexture.findRegion("attackprawodol2");
        attack[18] = attackTexture.findRegion("attackprawodol3");
        attack[19] = attackTexture.findRegion("attackprawodol4");
        attack[20] = attackTexture.findRegion("attackprawogora1");
        attack[21] = attackTexture.findRegion("attackprawogora2");
        attack[22] = attackTexture.findRegion("attackprawogora3");
        attack[23] = attackTexture.findRegion("attackprawogora4");
        texture = Warrior[0];
    }

    /**
     * @return zwraca nam pozycje x hexa na ktorym znajduje sie aktualnie gracz.
     */
    public int getX(){
        return this.x;
    }

    /**
     * @return zwraca nam pozycje y hexa na ktorym znajduje sie aktualnie gracz.
     */
    public int getY(){
        return this.y;
    }

    /**
     * Funkcja odpowiada za zmiane polozenia gracza na mapie oraz zmiane kierunku animacji.
     * @param x Przekazujemy wspolrzedne x hexa na ktory mamy przejsc.
     * @param y Przekazujemy wspolrzedne y hexa na ktory mamy przejsc.
     */
    public void move_player(int x,int y){
        if(!fAttack)
        {
            this.prevx = this.x;
            this.x = x;

            this.prevy = this.y;
            this.y = y;
            if(prevy == y )
                if(prevx < x)
                    aniStart = 12;
                else
                    aniStart = 0;

            else if(prevy < y)
                if(prevx < x )
                    aniStart = 20;
                else if(prevx == x && y%2 !=0)
                    aniStart = 20;
                else
                    aniStart = 8;
            else
                if(prevx < x )
                    aniStart = 16;
                else if(prevx == x && y%2 !=0)
                    aniStart = 16;
                else
                    aniStart = 4;
                aniCurr = aniStart;
        }
    }

    /**
     * Funkcja zmieniajaca klatke animacji bohatera.
     */
    void update(){

        if((aniCurr - aniStart) == 3){
            if(fAttack)
                 fAttack = false;
            aniCurr = aniStart;
        }
        else
            aniCurr++;
        if(!fAttack){
            texture = Warrior[aniCurr];
        }
        else{
            texture = attack[aniCurr];
        }
    }
}