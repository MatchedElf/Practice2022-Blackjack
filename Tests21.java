package src.Tests;
import src.Model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Tests21 {
    private Game gam;
    @Before
    public void setUp(){
        
        gam = new Game(100);
    }
    @Test
    public void decide1(){
        boolean actual = gam.player2Decide(13);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void decide2(){
        boolean actual = gam.player2Decide(0);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void decide3(){
        boolean actual = gam.player2Decide(25);
        boolean expected = false;
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void give_card(){
        gam.make_cards();
        int actual = gam.give_card(0, 0);
        int expected = 2;
        Assert.assertEquals(expected, actual);
    }
}
