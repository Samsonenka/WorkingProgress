package com.samson.workingProgress.controllers;

import com.samson.workingProgress.models.Repos.TonerRepo;
import com.samson.workingProgress.models.Toner;
import com.samson.workingProgress.models.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TonerController {

    @Autowired
    TonerRepo tonerRepo;

    @RequestMapping("/toners")
    public String showToners(ModelMap modelMap){

        List<Toner> tonerList = tonerRepo.findAll();
        modelMap.put("tonerList", tonerList);

        return "toners";
    }

    @PostMapping("/toners")
    public String createToner(@RequestParam String tonerName, @RequestParam int points, ModelMap modelMap){

        Toner newToner = new Toner(tonerName, points);
        tonerRepo.save(newToner);
        List<Toner> tonerList = tonerRepo.findAll();
        modelMap.put("tonerList", tonerList);

        return "toners";
    }

    @GetMapping("/deleteToner/{tonerID}")
    public String deleteToner(@PathVariable int tonerID){

        Toner toner = tonerRepo.findById(tonerID).get();
        tonerRepo.delete(toner);

        return "redirect:/toners";
    }
}
