package test;

import algorithm.Solver;
import org.junit.Assert;
import org.junit.Test;

public class AlgorithmTest {

    @Test
    public void findMaxSpectreTest(){
        var func = "00000000";
        var res = new Solver(func).solve();
        Assert.assertEquals("0, 0, 0, 0", res);
        func = "1111111111111111";
        res = new Solver(func).solve();
        Assert.assertEquals("0, 0, 0, 0, 0", res);
        func = "10110011100110110011100110011001";
        res = new Solver(func).solve();
        Assert.assertEquals("0, 1, 2, 1, 1, 0", res);
        func = "1011001110011011011001110011011001110011001100110011100110011001";
        res = new Solver(func).solve();
        Assert.assertEquals("0, 1, 2, 3, 2, 1, 0", res);
        func = "10111011001110011011011001110011011001110011001100110011100110011001001110011011011001110011011001110011001100110011100110011001";
        res = new Solver(func).solve();
        Assert.assertEquals("0, 1, 2, 3, 2, 1, 1, 0", res);
    }
}
