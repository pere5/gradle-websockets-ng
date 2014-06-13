package org.flowerbed.workers;

/**
 * Created by pere5 on 11/06/14.
 */

import java.text.SimpleDateFormat;
import java.util.List;

import org.flowerbed.repository.plants.Flower;
import org.flowerbed.repository.plants.FlowerBed;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableScheduling
public class ScheduledWorker {

    private static Logger logger = Logger.getLogger(ScheduledWorker.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private final FlowerBed flowerBed;

    private SimpMessagingTemplate template;

    @Autowired
    public ScheduledWorker(FlowerBed flowerBed, SimpMessagingTemplate template) {
        this.flowerBed = flowerBed;
        this.template = template;
    }

    @Scheduled(fixedRate = 5000)
    public void worker() {
        if (flowerBed.getPlants(0).isEmpty()) {
            flowerBed.plantPlant(new Flower("Planticus", "Apocynaceae", 50, 130));
            flowerBed.plantPlant(new Flower("Floweriam", "Hydrophyllaceae", 110, 45));
            flowerBed.plantPlant(new Flower("Growadomus", "Plumbaginaceae", 75, 95));
        }
        for (Flower fgp: flowerBed.getPlants(0)) {
            fgp.setAge(fgp.getAge() + 1);
            fgp.setHeight(fgp.getAge() + 1);
            logger.info("Updating model: " + fgp);
        }
        template.convertAndSend("/topic/flowerbed", convertToJSON(flowerBed));
    }

    public @ResponseBody
    List<Flower> convertToJSON(FlowerBed flowerBed) {
        return flowerBed.getPlants(0);
    }
}