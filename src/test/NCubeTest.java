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

    @Test
    public void setKFacesSizeTest(){
        var func = "00100010";
        var cube = new NCube(func);
        cube.setKFaces();
        var kFaces = cube.getKFaces();
        Assert.assertEquals(4, kFaces.size());
    }

    @Test
    public void setKFacesMaskTest(){
        var func = "11110010";
        var cube = new NCube(func);
        cube.setKFaces();
        var kFaces = cube.getKFaces();
        Assert.assertEquals(6, kFaces.size());
        Assert.assertEquals("[0, 1]", kFaces.values().toArray()[0].toString());
        Assert.assertEquals("[0, 10, 110]", kFaces.values().toArray()[1].toString());
        Assert.assertEquals("[0, 1, 10, 11]", kFaces.values().toArray()[2].toString());
        Assert.assertEquals("[110]", kFaces.values().toArray()[3].toString());
        Assert.assertEquals("[10, 11, 110]", kFaces.values().toArray()[4].toString());
        Assert.assertEquals("[1, 11]", kFaces.values().toArray()[5].toString());
    }

    @Test
    public void divideTest(){
        var func = "11110010";
        var cube = new NCube(func);
        cube.setKFaces();
        var divided = cube.divide(1, cube.getVertexes());
        Assert.assertEquals(2, divided.size());
        Assert.assertEquals("[10, 11, 110]", divided.get(0).toString());
        Assert.assertEquals("[0, 1]", divided.get(1).toString());
    }
}
