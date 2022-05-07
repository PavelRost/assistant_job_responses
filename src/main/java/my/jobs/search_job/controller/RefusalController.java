package my.jobs.search_job.controller;

import my.jobs.search_job.service.ResponseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RefusalController {

    private final ResponseService responseService;

    public RefusalController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping({"/refusal"})
    public String index(Model model) {
        model.addAttribute("responses", responseService.findAllWithDoneTrueAndArchiveFalse());
        return "refusal";
    }

}
