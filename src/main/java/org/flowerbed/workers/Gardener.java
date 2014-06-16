package org.flowerbed.workers;

/**
 * Created by pere5 on 11/06/14.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.flowerbed.repository.plants.EmptySpot;
import org.flowerbed.repository.plants.Flower;
import org.flowerbed.repository.plants.FlowerBed;
import org.apache.log4j.Logger;
import org.flowerbed.repository.plants.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@EnableScheduling
public class Gardener {

    private static Logger logger = Logger.getLogger(Gardener.class);
    private final FlowerBed flowerBed;
    private final CommonSense commonSense;
    private SimpMessagingTemplate socket;
    private boolean first = true;
    private final AtomicInteger atomicInteger = new AtomicInteger();

    @Autowired
    public Gardener(FlowerBed flowerBed, SimpMessagingTemplate socket, CommonSense commonSense) {
        this.flowerBed = flowerBed;
        this.socket = socket;
        this.commonSense = commonSense;
    }

    @Scheduled(fixedRate = 5000)
    public void worker() {
        if (first) {
            plantFirstFlowers();
            first = false;
        }
        garden();
        pushToClient();
    }

    private void garden() {
        int noFlowers = 0;
        for (List<Spot> flowerList: flowerBed.getFlowerBed()) {
            for (Spot spot: flowerList) {
                if (spot instanceof Flower) {
                    noFlowers++;
                }
            }
        }
        logger.info("Gardener is working. Nr plants in garden: " + noFlowers);
    }

    public void plant(Flower flower, int x, int y) {
        int id = atomicInteger.incrementAndGet();
        flower.setId(id);
        this.flowerBed.getFlowerBed().get(x).set(y, flower);
    }

    public void weed(int x, int y) {
        this.flowerBed.getFlowerBed().get(x).set(y, new EmptySpot());
    }

    public void weedFlowerAnywhere() {
        boolean weeded = false;
        while (!weeded) {
            int min = 0;
            int max = 9;
            int x = min + (int)(Math.random() * ((max - min) + 1));
            int y = min + (int)(Math.random() * ((max - min) + 1));
            if (commonSense.onOtherPlant(x, y)) {
                weed(x, y);
                weeded = true;
            }
        }
    }

    private void plantFirstFlowers() {
        List<Flower> flowerList = new ArrayList<>();
        flowerList.add(new Flower("Planticus", "Apocynaceae", 150, 130));
        flowerList.add(new Flower("Floweriam", "Hydrophyllaceae", 110, 45));
        flowerList.add(new Flower("Growadomus", "Plumbaginaceae", 75, 95));
        plantAnywhere(flowerList);
    }

    public void plantAnywhere(Flower flower) {
        List<Flower> flowerList = new ArrayList<>();
        flowerList.add(flower);
        plantAnywhere(flowerList);
    }

    public void plantAnywhere(List<Flower> flowerList) {
        for (Flower newFlower : flowerList) {
            boolean planted = false;
            while (!planted) {
                int min = 0;
                int max = 9;
                int x = min + (int)(Math.random() * ((max - min) + 1));
                int y = min + (int)(Math.random() * ((max - min) + 1));
                if (commonSense.notOnOtherPlant(x, y)) {
                    plant(newFlower, x, y);
                    planted = true;
                }
            }
        }
    }

    private void pushToClient() {
        socket.convertAndSend("/topic/flowerbed", convertToJSON(flowerBed.getFlowerBed()));
    }

    public @ResponseBody List<List<Spot>> convertToJSON(List<List<Spot>> flowerList) {
        return flowerList;
    }

    public List<List<Spot>> getFlowerBed() {
        return flowerBed.getFlowerBed();
    }
}