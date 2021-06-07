package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Klasa odpowiadajaca za ogniki na ekranie menu.
 */
public class Flames {

    /**
     * Obecna klatka animacji.
     */
    int aniCurr = 0;
    /**
     * Poczatkowa klatka animacji.
     */
    int aniStart = 0;
    /**
     * oreslamy kolor dostepnych ognikow.
     */
    int fColour;

    /**
     * Animacje ognikow.
     */
    TextureAtlas flamesAtlas = new TextureAtlas("Allflames");
    /**
     * Tablica tekstur ognikow.
     */
    TextureRegion[] flames = new TextureRegion[200];
    /**
     * Tekstura ktora przekazujemy do pokazania na ekranie.
     */
    TextureRegion frame;

    /**
     * Funkcja odpowiadajaca z wczytanie kazdej klatki animacji z atlasu.
     * @param fColour kolor ognika.
     */
    public Flames(int fColour){
        this.fColour = fColour;
        flames[0] = flamesAtlas.findRegion("blue1");
        flames[1] = flamesAtlas.findRegion("blue2");
        flames[2] = flamesAtlas.findRegion("blue3");
        flames[3] = flamesAtlas.findRegion("blue4");
        flames[4] = flamesAtlas.findRegion("blue5");
        flames[5] = flamesAtlas.findRegion("blue6");
        flames[6] = flamesAtlas.findRegion("blue7");
        flames[7] = flamesAtlas.findRegion("blue8");
        flames[8] = flamesAtlas.findRegion("blue9");
        flames[9] = flamesAtlas.findRegion("blue10");
        flames[10] = flamesAtlas.findRegion("blue11");
        flames[11] = flamesAtlas.findRegion("blue12");
        flames[12] = flamesAtlas.findRegion("blue13");
        flames[13] = flamesAtlas.findRegion("blue14");
        flames[14] = flamesAtlas.findRegion("blue15");
        flames[15] = flamesAtlas.findRegion("blue16");
        flames[16] = flamesAtlas.findRegion("blue17");
        flames[17] = flamesAtlas.findRegion("blue18");
        flames[18] = flamesAtlas.findRegion("blue19");
        flames[19] = flamesAtlas.findRegion("blue20");
        flames[20] = flamesAtlas.findRegion("blue21");
        flames[21] = flamesAtlas.findRegion("blue22");
        flames[22] = flamesAtlas.findRegion("blue23");
        flames[23] = flamesAtlas.findRegion("blue24");
        flames[24] = flamesAtlas.findRegion("blue25");
        flames[25] = flamesAtlas.findRegion("blue26");
        flames[26] = flamesAtlas.findRegion("blue27");
        flames[27] = flamesAtlas.findRegion("blue28");
        flames[28] = flamesAtlas.findRegion("blue29");
        flames[29] = flamesAtlas.findRegion("blue30");
        flames[30] = flamesAtlas.findRegion("blue31");
        flames[31] = flamesAtlas.findRegion("blue32");
        flames[32] = flamesAtlas.findRegion("blue33");
        flames[33] = flamesAtlas.findRegion("blue34");
        flames[34] = flamesAtlas.findRegion("blue35");
        flames[35] = flamesAtlas.findRegion("blue36");
        flames[36] = flamesAtlas.findRegion("blue37");
        flames[37] = flamesAtlas.findRegion("blue38");
        flames[38] = flamesAtlas.findRegion("blue39");
        flames[39] = flamesAtlas.findRegion("blue40");
        flames[40] = flamesAtlas.findRegion("blue41");
        flames[41] = flamesAtlas.findRegion("blue42");
        flames[42] = flamesAtlas.findRegion("blue43");
        flames[43] = flamesAtlas.findRegion("blue44");
        flames[44] = flamesAtlas.findRegion("blue45");
        flames[45] = flamesAtlas.findRegion("blue46");
        flames[46] = flamesAtlas.findRegion("blue47");
        flames[47] = flamesAtlas.findRegion("blue48");
        flames[48] = flamesAtlas.findRegion("blue49");
        flames[49] = flamesAtlas.findRegion("blue50");
        flames[50] = flamesAtlas.findRegion("blue51");
        flames[51] = flamesAtlas.findRegion("blue52");
        flames[52] = flamesAtlas.findRegion("blue53");
        flames[53] = flamesAtlas.findRegion("blue54");
        flames[54] = flamesAtlas.findRegion("blue55");
        flames[55] = flamesAtlas.findRegion("blue56");
        flames[56] = flamesAtlas.findRegion("blue57");
        flames[57] = flamesAtlas.findRegion("blue58");
        flames[58] = flamesAtlas.findRegion("blue59");
        flames[59] = flamesAtlas.findRegion("blue50");
        flames[50] = flamesAtlas.findRegion("blue51");
        flames[51] = flamesAtlas.findRegion("blue52");
        flames[52] = flamesAtlas.findRegion("blue53");
        flames[53] = flamesAtlas.findRegion("blue54");
        flames[54] = flamesAtlas.findRegion("blue55");
        flames[55] = flamesAtlas.findRegion("blue56");
        flames[56] = flamesAtlas.findRegion("blue57");
        flames[57] = flamesAtlas.findRegion("blue58");
        flames[58] = flamesAtlas.findRegion("blue59");
        flames[59] = flamesAtlas.findRegion("blue60");
        flames[60] = flamesAtlas.findRegion("blue61");
        flames[61] = flamesAtlas.findRegion("blue62");
        flames[62] = flamesAtlas.findRegion("blue63");
        flames[63] = flamesAtlas.findRegion("blue64");
        flames[64] = flamesAtlas.findRegion("blue65");
        flames[65] = flamesAtlas.findRegion("purple1");
        flames[66] = flamesAtlas.findRegion("purple2");
        flames[67] = flamesAtlas.findRegion("purple3");
        flames[68] = flamesAtlas.findRegion("purple4");
        flames[69] = flamesAtlas.findRegion("purple5");
        flames[70] = flamesAtlas.findRegion("purple6");
        flames[71] = flamesAtlas.findRegion("purple7");
        flames[72] = flamesAtlas.findRegion("purple8");
        flames[73] = flamesAtlas.findRegion("purple9");
        flames[74] = flamesAtlas.findRegion("purple10");
        flames[75] = flamesAtlas.findRegion("purple11");
        flames[76] = flamesAtlas.findRegion("purple12");
        flames[77] = flamesAtlas.findRegion("purple13");
        flames[78] = flamesAtlas.findRegion("purple14");
        flames[79] = flamesAtlas.findRegion("purple15");
        flames[80] = flamesAtlas.findRegion("purple16");
        flames[81] = flamesAtlas.findRegion("purple17");
        flames[82] = flamesAtlas.findRegion("purple18");
        flames[83] = flamesAtlas.findRegion("purple19");
        flames[84] = flamesAtlas.findRegion("purple20");
        flames[85] = flamesAtlas.findRegion("purple21");
        flames[86] = flamesAtlas.findRegion("purple22");
        flames[87] = flamesAtlas.findRegion("purple23");
        flames[88] = flamesAtlas.findRegion("purple24");
        flames[89] = flamesAtlas.findRegion("purple25");
        flames[90] = flamesAtlas.findRegion("purple26");
        flames[91] = flamesAtlas.findRegion("purple27");
        flames[92] = flamesAtlas.findRegion("purple28");
        flames[93] = flamesAtlas.findRegion("purple29");
        flames[94] = flamesAtlas.findRegion("purple30");
        flames[95] = flamesAtlas.findRegion("red1");
        flames[96] = flamesAtlas.findRegion("red2");
        flames[97] = flamesAtlas.findRegion("red3");
        flames[98] = flamesAtlas.findRegion("red4");
        flames[99] = flamesAtlas.findRegion("red5");
        flames[100] = flamesAtlas.findRegion("red6");
        flames[101] = flamesAtlas.findRegion("red7");
        flames[102] = flamesAtlas.findRegion("red8");
        flames[103] = flamesAtlas.findRegion("red9");
        flames[104] = flamesAtlas.findRegion("red10");
        flames[105] = flamesAtlas.findRegion("red11");
        flames[106] = flamesAtlas.findRegion("red12");
        flames[107] = flamesAtlas.findRegion("red13");
        flames[108] = flamesAtlas.findRegion("red14");
        flames[109] = flamesAtlas.findRegion("red15");
        flames[110] = flamesAtlas.findRegion("red16");
        flames[111] = flamesAtlas.findRegion("red17");
        flames[112] = flamesAtlas.findRegion("red18");
        flames[113] = flamesAtlas.findRegion("red19");
        flames[114] = flamesAtlas.findRegion("red20");
        flames[115] = flamesAtlas.findRegion("red21");
        flames[116] = flamesAtlas.findRegion("red22");
        flames[117] = flamesAtlas.findRegion("red23");
        flames[118] = flamesAtlas.findRegion("red24");
        flames[119] = flamesAtlas.findRegion("red25");
        flames[120] = flamesAtlas.findRegion("red26");
        flames[121] = flamesAtlas.findRegion("red27");
        flames[122] = flamesAtlas.findRegion("red28");
        flames[123] = flamesAtlas.findRegion("red29");
        flames[124] = flamesAtlas.findRegion("red30");
        flames[125] = flamesAtlas.findRegion("red31");
        flames[126] = flamesAtlas.findRegion("red32");
        flames[127] = flamesAtlas.findRegion("red33");
        flames[128] = flamesAtlas.findRegion("red34");
        flames[129] = flamesAtlas.findRegion("red35");
        flames[130] = flamesAtlas.findRegion("red36");
        flames[131] = flamesAtlas.findRegion("red37");
        flames[132] = flamesAtlas.findRegion("red38");
        flames[133] = flamesAtlas.findRegion("red39");
        flames[134] = flamesAtlas.findRegion("red40");
        flames[135] = flamesAtlas.findRegion("red41");
        flames[136] = flamesAtlas.findRegion("red42");
        flames[137] = flamesAtlas.findRegion("red43");
        flames[138] = flamesAtlas.findRegion("red44");
        flames[139] = flamesAtlas.findRegion("red45");
        flames[140] = flamesAtlas.findRegion("red46");
        flames[141] = flamesAtlas.findRegion("red47");
        flames[142] = flamesAtlas.findRegion("red48");
        flames[143] = flamesAtlas.findRegion("red49");
        flames[144] = flamesAtlas.findRegion("red50");
        flames[145] = flamesAtlas.findRegion("red51");
        flames[146] = flamesAtlas.findRegion("red52");
        flames[147] = flamesAtlas.findRegion("red53");
        flames[148] = flamesAtlas.findRegion("red54");
        flames[149] = flamesAtlas.findRegion("red55");
        flames[150] = flamesAtlas.findRegion("red56");
        flames[151] = flamesAtlas.findRegion("red57");
        flames[152] = flamesAtlas.findRegion("red58");
        flames[153] = flamesAtlas.findRegion("red59");
        flames[154] = flamesAtlas.findRegion("red50");
        flames[155] = flamesAtlas.findRegion("red51");
        flames[156] = flamesAtlas.findRegion("red52");
        flames[157] = flamesAtlas.findRegion("red53");
        flames[158] = flamesAtlas.findRegion("red54");
        flames[159] = flamesAtlas.findRegion("red55");
        flames[160] = flamesAtlas.findRegion("red56");
        flames[161] = flamesAtlas.findRegion("red57");
        flames[162] = flamesAtlas.findRegion("red58");
        flames[163] = flamesAtlas.findRegion("red59");
        flames[164] = flamesAtlas.findRegion("red60");
        flames[165] = flamesAtlas.findRegion("red61");
        flames[166] = flamesAtlas.findRegion("red62");
        flames[167] = flamesAtlas.findRegion("red63");
        flames[168] = flamesAtlas.findRegion("red64");
        flames[169] = flamesAtlas.findRegion("red65");
        flames[170] = flamesAtlas.findRegion("green1");
        flames[171] = flamesAtlas.findRegion("green2");
        flames[172] = flamesAtlas.findRegion("green3");
        flames[173] = flamesAtlas.findRegion("green4");
        flames[174] = flamesAtlas.findRegion("green5");
        flames[175] = flamesAtlas.findRegion("green6");
        flames[176] = flamesAtlas.findRegion("green7");
        flames[177] = flamesAtlas.findRegion("green8");
        flames[178] = flamesAtlas.findRegion("green9");
        flames[179] = flamesAtlas.findRegion("green10");
        flames[180] = flamesAtlas.findRegion("green11");
        flames[181] = flamesAtlas.findRegion("green12");
        flames[182] = flamesAtlas.findRegion("green13");
        flames[183] = flamesAtlas.findRegion("green14");
        flames[184] = flamesAtlas.findRegion("green15");
        flames[185] = flamesAtlas.findRegion("green16");
        flames[186] = flamesAtlas.findRegion("green17");
        flames[187] = flamesAtlas.findRegion("green18");
        flames[188] = flamesAtlas.findRegion("green19");
        flames[189] = flamesAtlas.findRegion("green20");
        flames[190] = flamesAtlas.findRegion("green21");
        flames[191] = flamesAtlas.findRegion("green22");
        flames[192] = flamesAtlas.findRegion("green23");
        flames[193] = flamesAtlas.findRegion("green24");
        flames[194] = flamesAtlas.findRegion("green25");
        flames[195] = flamesAtlas.findRegion("green26");
        flames[196] = flamesAtlas.findRegion("green27");
        flames[197] = flamesAtlas.findRegion("green28");
        flames[198] = flamesAtlas.findRegion("green29");
        flames[199] = flamesAtlas.findRegion("green30");
        switch(fColour){
            case 0: //blue
                aniStart = 0;
                aniCurr = 0;
                break;
            case 1: //purple
                aniStart = 65;
                aniCurr = 65;
                break;
            case 2: //red
                aniStart = 95;
                aniCurr = 95;
                break;
            case 3: //green
                aniStart = 170;
                aniCurr = 170;
                break;
        }
        frame = flames[aniCurr];
    }

    /**
     * Funkcja zmienia klatki animacji.
     */
    void change_flame(){
        switch(fColour){
            case 0:
                if(aniCurr - aniStart == 64)
                    aniCurr = aniStart;
                else
                    aniCurr++;
                break;
            case 1:
            case 3:
                if(aniCurr - aniStart == 29)
                    aniCurr = aniStart;
                else
                    aniCurr++;
                break;
            case 2:
                if(aniCurr - aniStart == 74)
                    aniCurr = aniStart;
                else
                    aniCurr++;
                break;

        }
        frame = flames[aniCurr];
    }
}
