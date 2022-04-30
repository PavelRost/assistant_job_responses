package my.jobs.search_job.controller;

import my.jobs.search_job.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RefusalController {

    private final CompanyService companyService;

    public RefusalController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping({"/refusal"})
    public String index(Model model) {
        model.addAttribute("companys", companyService.findAllWithDoneTrue());
        return "refusal";
    }

}
