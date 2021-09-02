package test;

import instances.NCube;
import org.junit.Assert;
import org.junit.Test;

public class NCubeTest {
    @Test
    public void findMaxSpectreTest(){
        var func = "01001010010010010100101101111011";
        var cube = new NCube(func);
        Assert.assertEquals(16, cube.getVertexes().size());
        func = "0100101001001001010010110111101101001010010010010100101101111011";
        cube = new NCube(func);
        Assert.assertEquals(32, cube.getVertexes().size());
    }
}
