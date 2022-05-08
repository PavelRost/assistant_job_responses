package my.jobs.search_job.controller;

import my.jobs.search_job.service.ResponseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final ResponseService responseService;

    public IndexController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("responseDoneFalseArchiveFalse", responseService.findRespDoneArchiveFalse());
        model.addAttribute("responseDoneTrueArchiveFalse", responseService.findRespDoneTrueArchiveFalse());
        model.addAttribute("responseDoneFalseArchiveTrue", responseService.findRespDoneFalseArchiveTrue());
        return "index";
    }

}
