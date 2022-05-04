package my.jobs.search_job.controller;

import my.jobs.search_job.model.Company;
import my.jobs.search_job.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        return "create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Company company, HttpServletRequest req) {
        company.setName(req.getParameter("name"));
        companyService.addCompany(company);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateStatus(@RequestParam("id") int id) {
        companyService.updateStatus(id);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String create() {
        return "search";
    }

    @PostMapping("/findName")
    public String findCompanyByName(Model model, HttpServletRequest req) {
        String name = req.getParameter("name");
        model.addAttribute("company", companyService.findCompanyByName(name));
        return "search";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        companyService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/deleteAll")
    public String deleteAll() {
        companyService.deleteAll();
        return "redirect:/";
    }
}
