package org.flowerbed.repository.plants;

/**
 * Created by pere5 on 11/06/14.
 */
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryFlowerBed implements FlowerBed {

    //We are not thread safe.
    private final List<List<Spot>> flowerBed = new ArrayList<>();

    public InMemoryFlowerBed() {
        for (int i = 0; i < 10; i++) {
            List<Spot> spotRow = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                spotRow.add(new EmptySpot());
            }
            flowerBed.add(spotRow);
        }
    }

    public List<List<Spot>> getFlowerBed() {
        return this.flowerBed;
    }
}