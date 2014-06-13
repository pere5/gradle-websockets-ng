package org.flowerbed.repository.plants;

/**
 * Created by pere5 on 11/06/14.
 */
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class InMemoryFlowerBed implements FlowerBed {

    private final List<Flower> plantList = new CopyOnWriteArrayList<>();

    public List<Flower> getPlants(int index) {
        if (this.plantList.isEmpty()) {
            return Collections.<Flower> emptyList();
        }
        Assert.isTrue((index >= 0) && (index <= this.plantList.size()), "Invalid message index");
        return this.plantList.subList(index, this.plantList.size());
    }

    public void plantPlant(Flower flower) {
        this.plantList.add(flower);
    }
}