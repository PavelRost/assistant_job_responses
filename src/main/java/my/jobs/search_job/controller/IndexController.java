package my.jobs.search_job.controller;

import my.jobs.search_job.model.Response;
import my.jobs.search_job.service.ResponseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    private final ResponseService responseService;

    public IndexController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        List<Response> responseListAllFalse = responseService.findRespDoneArchiveFalse();
        int countRespToday = responseService.getCountResponseToday();
        int countRespYesterday = responseService.getCountResponseYesterday();
        String motivation = responseService.getMotivationText(countRespToday, countRespYesterday);
        model.addAttribute("motivation", motivation);
        model.addAttribute("countRespToday", countRespToday);
        model.addAttribute("countRespYesterday", countRespYesterday);
        model.addAttribute("responseDoneFalseArchiveFalse", responseListAllFalse);
        model.addAttribute("responseDoneTrueArchiveFalse", responseService.findRespDoneTrueArchiveFalse());
        model.addAttribute("responseDoneFalseArchiveTrue", responseService.findRespDoneFalseArchiveTrue());
        return "index";
    }

}
