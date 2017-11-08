package org.CandyLand.view;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by 15mik_000 on 11/7/2017.
 */
public class TokenSpaceTest {

    /*
* US: Visible Avatar
*
* placeAvatar() - test creation of avatar, and palcement onto space
* removeAvatar() - test ability to remove avatar from a space
* spaceLimit() - test capacity and overflow of 4 avatars per space
*/
    @Test
    public void placeAvatar(){

        TokenSpace ts = new TokenSpace(Color.BLUE);
        Token token = new Token();
        ts.setToken(token);
        assertEquals(ts.getToken(), token);
    }

    @Test
    public void removeAvatar(){

        TokenSpace ts = new TokenSpace(Color.BLUE);
        Token token = new Token();
        ts.setToken(token);
        ts.removeToken();
        assertNull(ts.getToken());
    }

    @Test
    public void spaceLimit(){
        GamePathSpace gps = new GamePathSpace(Color.BLUE);

        Token toke1 = new Token();
        Token toke2 = new Token();
        Token toke3 = new Token();
        Token toke4 = new Token();
        Token toke5 = new Token();

        try {
            gps.addToken(toke1);
            gps.addToken(toke2);
            gps.addToken(toke3);
            gps.addToken(toke4);
            gps.addToken(toke5);
        }catch(NoSpaceForTokenException ns){
            assertNotNull(ns);
        }
    }

}