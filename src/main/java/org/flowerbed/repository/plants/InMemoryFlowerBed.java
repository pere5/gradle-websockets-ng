package org.flowerbed.repository.plants;

/**
 * Created by pere5 on 11/06/14.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryFlowerBed implements FlowerBed {

    private final List<List<Flower>> flowerBed = new ArrayList<List<Flower>>();
    private final AtomicInteger atomicInteger = new AtomicInteger();

    public InMemoryFlowerBed() {
        boolean empty = true;
        for (int i = 0; i < 10; i++) {
            List<Flower> flowerRow = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                flowerRow.add(new Flower(empty));
            }
            flowerBed.add(flowerRow);
        }
    }

    public List<List<Flower>> getFlowerBed() {
        return this.flowerBed;
    }

    public void plant(Flower flower, int x, int y) {
        int id = atomicInteger.incrementAndGet();
        flower.setId(id);
        this.flowerBed.get(x).set(y, flower);
    }
}