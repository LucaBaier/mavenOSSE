package de.hfu;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class UtilTest 
{
    
    @Test
    public void istErstesHalbjahrTest() {
    	assertTrue(Util.istErstesHalbjahr(1));
    	assertTrue(Util.istErstesHalbjahr(6));
    	assertFalse(Util.istErstesHalbjahr(7));
    	assertFalse(Util.istErstesHalbjahr(12));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testMonatZuKlein() {
    	Util.istErstesHalbjahr(0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testMonatZuGross() {
    	Util.istErstesHalbjahr(13);
    }
}
