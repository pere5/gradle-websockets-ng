package org.flowerbed.workers;

/**
 * Created by pere5 on 16/06/14.
 */

import org.flowerbed.repository.plants.EmptySpot;
import org.flowerbed.repository.plants.Flower;
import org.flowerbed.repository.plants.FlowerBed;
import org.flowerbed.repository.plants.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonSense {

    private final FlowerBed flowerBed;

    @Autowired
    public CommonSense(FlowerBed flowerBed) {
        this.flowerBed = flowerBed;
    }

    public boolean notOnOtherPlant(int x, int y) {
        Spot spot = this.flowerBed.getFlowerBed().get(x).get(y);
        return spot instanceof EmptySpot;
    }

    public boolean onOtherPlant(int x, int y) {
        Spot spot = this.flowerBed.getFlowerBed().get(x).get(y);
        return spot instanceof Flower;
    }
}
