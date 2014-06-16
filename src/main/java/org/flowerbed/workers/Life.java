package org.flowerbed.workers;

/**
 * Created by pere5 on 15/06/14.
 */

import org.apache.log4j.Logger;
import org.flowerbed.repository.plants.Flower;
import org.flowerbed.repository.plants.FlowerBed;
import org.flowerbed.repository.plants.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@EnableScheduling
public class Life {

    private static Logger logger = Logger.getLogger(Life.class);
    private final FlowerBed flowerBed;
    private final CommonSense commonSense;

    @Autowired
    public Life(FlowerBed flowerBed, CommonSense commonSense) {
        this.flowerBed = flowerBed;
        this.commonSense = commonSense;
    }

    @Scheduled(fixedRate = 5000)
    public void life() {
        live();
    }

    private void live() {
        logger.info("Life is happening.");
        int x = 0;
        for (List<Spot> flowerList: flowerBed.getFlowerBed()) {
            int y = 0;
            for (Spot spot: flowerList) {
                if (spot instanceof Flower) {
                    Flower flower = (Flower) spot;
                    grow(flower);
                    spread(flower, x, y);
                }
                y++;
            }
            x++;
        }
    }

    private void spread(Flower flower, int x, int y) {
        //logger.info("name: " + flower.getName() + " x: " + x + " y: " + y);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int neighborX = x + i;
                int neighborY = y + j;
                if (!flower.isWithered()
                        && withinFlowerBed(neighborX, neighborY)
                        && notThisSpot(neighborX, neighborY, x, y)
                        && commonSense.notOnOtherPlant(neighborX, neighborY)) {
                    perhapsSpread(flower, neighborX, neighborY);
                }
            }
        }
    }

    private void perhapsSpread(Flower flower, int neighborX, int neighborY) {
        int max = 100;
        int min = 0;
        double lifetimeLikelihood = 50.0; //likelihood a plant will spread to each empty neighbor during lifetime.
        double target = 100 - ( lifetimeLikelihood / flower.getMaxAge() );
        double percentage = min + (Math.random() * (max - min));
        //logger.info("name: " + flower.getName() + " target: " + target + " percentage: " + percentage);
        if (percentage >= target) {
            logger.info("Life spawned a new flower.");
            Flower newFlower = spawnNewFlower(flower);
            this.flowerBed.getFlowerBed().get(neighborX).set(neighborY, newFlower);
        }
    }

    private Flower spawnNewFlower(Flower flower) {
        return new Flower(flower.getName(), flower.getFamily(), flower.getMaxAge(), flower.getMaxHeight());
    }

    private boolean notThisSpot(int neighborX, int neighborY, int x, int y) {
        return !(neighborX == x && neighborY == y);
    }

    private boolean withinFlowerBed(int neighborX, int neighborY) {
        return neighborX >= 0 && neighborX <= 9 && neighborY >= 0 && neighborY <= 9;
    }

    private void grow(Flower flower) {
        if (flower.getAge() < flower.getMaxAge()) {
            flower.setAge(flower.getAge() + 1);
        } else {
            flower.setWithered(true);
        }
        if (flower.getHeight() < flower.getMaxHeight()) {
            flower.setHeight(flower.getHeight() + 1);
        }
    }
}
