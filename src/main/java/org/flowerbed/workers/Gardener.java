package org.flowerbed.workers;

/**
 * Created by pere5 on 11/06/14.
 */

import java.util.List;

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
    private SimpMessagingTemplate socket;
    private boolean first = true;

    @Autowired
    public Gardener(FlowerBed flowerBed, SimpMessagingTemplate socket) {
        this.flowerBed = flowerBed;
        this.socket = socket;
    }

    @Scheduled(fixedRate = 5000)
    public void worker() {
        if (first) {
            plantFirstFlowers();
            first = false;
        }
        logic();
        pushToClient();
    }

    private void logic() {
        logger.info("Tending to flowers.");
        for (List<Spot> flowerList: flowerBed.getFlowerBed()) {
            for (Spot spot: flowerList) {
                if (spot instanceof Flower) {
                    Flower flower = (Flower) spot;
                    flower.setAge(flower.getAge() + 1);
                    flower.setHeight(flower.getHeight() + 1);
                }
            }
        }
    }

    private void plantFirstFlowers() {
        flowerBed.plant(new Flower("Planticus", "Apocynaceae", 50, 130), 0, 0);
        flowerBed.plant(new Flower("Floweriam", "Hydrophyllaceae", 110, 45), 0, 1);
        flowerBed.plant(new Flower("Growadomus", "Plumbaginaceae", 75, 95), 1, 0);
    }

    private void pushToClient() {
        socket.convertAndSend("/topic/flowerbed", convertToJSON(flowerBed.getFlowerBed()));
    }

    public @ResponseBody List<List<Spot>> convertToJSON(List<List<Spot>> flowerList) {
        return flowerList;
    }
}