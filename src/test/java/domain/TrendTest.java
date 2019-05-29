package domain;

import domain.Kweet;
import domain.Trend;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class TrendTest {
    private UUID testId;
    private Trend testTrend;
    private Trend testEmptyTrend;
    private List<Kweet> testKweets;
    private String testName;

    @Before
    public void setup(){
        this.testId = UUID.randomUUID();
        this.testKweets = new ArrayList<>();
        this.testName = "#Test";
        this.testTrend = new Trend(testId, testName, testKweets);
        this.testEmptyTrend = new Trend();
    }

    @Test
    public void getIdTest(){
        assertEquals("The ID was not right.", this.testId, testTrend.getId());
    }

    @Test
    public void getEmptyIdTest(){
        assertNull("The ID was not right.", testEmptyTrend.getId());
    }


    @Test
    public void getNameTest() {
        assertEquals("The name was not right.", testName, this.testTrend.getName());
    }

    @Test
    public void getEmptyNameTest() {
        assertNull("The name was not empty.", this.testEmptyTrend.getName());
    }

    @Test
    public void getKweetsTest() {
        assertNotNull("The Kweet list was null.", testTrend.getKweets());
    }

    @Test
    public void getEmptyKweetsTest() {
        assertNull("The Kweet list was not null.", testEmptyTrend.getKweets());
    }
}