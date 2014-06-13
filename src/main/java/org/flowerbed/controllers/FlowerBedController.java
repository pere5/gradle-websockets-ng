package org.flowerbed.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.flowerbed.repository.plants.Flower;
import org.flowerbed.repository.plants.FlowerBed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FlowerBedController {
    @RequestMapping(value = "flowerbed.html", method = RequestMethod.GET)
    public String flowerbed(Model model, @RequestParam(value="name", required=false, defaultValue="Dood!") String name) {
        Map<String, Object> attributesMap = new HashMap<>();
        attributesMap.put("name", name);
        model.addAllAttributes(attributesMap);
        return "flowerbed";
    }
}