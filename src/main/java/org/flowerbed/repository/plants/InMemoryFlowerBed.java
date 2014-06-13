package org.flowerbed.repository.plants;

/**
 * Created by pere5 on 11/06/14.
 */
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryFlowerBed implements FlowerBed {

    private final List<Flower> flowerBed = new CopyOnWriteArrayList<>();

    public List<Flower> getFlowerBed() {
        return this.flowerBed;
    }

    public void plantFlower(Flower flower) {
        this.flowerBed.add(flower);
    }
}