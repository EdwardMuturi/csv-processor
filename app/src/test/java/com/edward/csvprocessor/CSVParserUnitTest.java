package com.edward.csvprocessor;

import com.edward.csvprocessor.util.Util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(JUnit4.class)
public class CSVParserUnitTest {
    private Util util;

    /**
     * Set up environment
     */
    @Before
    public void setUpEnvironment(){
        util= new Util();
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void convertToInt(){
        int convertedResult= util.convertToInteger("20");
        assertThat(convertedResult, is(equalTo(20)));
    }

    @Test
    public void convertToDate(){
        String convertedDate= util.convertToDate("Fri Sep 20 20:56:02 EDT 2019");
        assertThat(convertedDate, is(equalTo("2019-09-21")));
    }

}