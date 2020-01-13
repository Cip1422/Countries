package com.example.deltatest;

import com.example.deltatest.model.NationModel;

import org.hamcrest.collection.IsMapContaining;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }



    @Test
    public void toMap() {

        ArrayList<NationModel> items = new ArrayList<>();
        items.add(new NationModel("country", "city", "Region"));
        items.add(new NationModel("country", "city", "Region2"));
        items.add(new NationModel("apple", "w", "Region2"));
        items.add(new NationModel("pizza", "hunger", "Region3"));
        items.add(new NationModel("pizza", "hunger", "Region3"));
        items.add(new NationModel("pizza", "hunger", "Region3"));
        items.add(new NationModel("pizza", "hunger", "Region3"));
        items.add(new NationModel("pizza", "hunger", "Region3"));
        items.add(new NationModel("pizza", "hunger", "Region3"));

        Map<String, ArrayList<NationModel>> map = new TreeMap<>();
        for ( int i = 0; i < items.size(); i++) {
            ArrayList<NationModel> value;
            value = map.get(items.get(i).getRegion());
            if (value == null) {
                value = new ArrayList<>();
                map.put(items.get(i).getRegion(), value);
            }
            value.add(items.get(i));


            assertThat(map, IsMapContaining.hasValue(value));

        }

        assertThat(map.size(), is(3)); //important to see if the same region doesn't actually change the map size
        assertThat(map, IsMapContaining.hasKey("Region"));
        assertThat(map, IsMapContaining.hasKey("Region2"));

    }
}