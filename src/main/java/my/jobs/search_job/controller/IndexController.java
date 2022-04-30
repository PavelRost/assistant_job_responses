package my.jobs.search_job.controller;

import my.jobs.search_job.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final CompanyService companyService;

    public IndexController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("companyDoneFalse", companyService.findAllWithDoneFalse());
        model.addAttribute("companyDoneTrue", companyService.findAllWithDoneTrue());
        return "index";
    }

}
