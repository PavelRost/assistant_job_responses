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
        String motivation;
        long countRespToday = responseListAllFalse.stream()
                .filter(response -> response.getCreated().getDate() == new Date().getDate())
                .count();
        long countRespYesterday = responseListAllFalse.stream()
                .filter(response -> response.getCreated().getDate() == (new Date().getDate() - 1))
                .count();
        if (countRespToday > countRespYesterday) {
            motivation = "Отличный результат, продолжай в том же духе!";
        } else if (countRespToday < countRespYesterday) {
            motivation = "Сегодня ты мало откликался, так работу не найдешь!";
        } else {
            motivation = countRespToday == 0 ? "За два дня ни одного отклика, хватит бездельничать!" : "Количество откликов сравнялось со вчерашним днем, неплохо, навались еще!";
        }
        model.addAttribute("motivation", motivation);
        model.addAttribute("countRespToday", countRespToday);
        model.addAttribute("countRespYesterday", countRespYesterday);
        model.addAttribute("responseDoneFalseArchiveFalse", responseListAllFalse);
        model.addAttribute("responseDoneTrueArchiveFalse", responseService.findRespDoneTrueArchiveFalse());
        model.addAttribute("responseDoneFalseArchiveTrue", responseService.findRespDoneFalseArchiveTrue());
        return "index";
    }

}
