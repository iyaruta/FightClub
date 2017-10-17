package home.inna.fc.controller;

import home.inna.fc.data.Experience;
import home.inna.fc.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/experience")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Experience> get() {
        List<Experience> exp = experienceService.findAll();
        return exp;
    }


}
