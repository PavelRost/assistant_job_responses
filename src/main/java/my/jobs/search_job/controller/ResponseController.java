package my.jobs.search_job.controller;

import my.jobs.search_job.model.Note;
import my.jobs.search_job.model.Response;
import my.jobs.search_job.service.ResponseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ResponseController {

    private final ResponseService responseService;

    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        return "create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Response response, HttpServletRequest req) {
        response.setName(req.getParameter("name"));
        responseService.addResponse(response);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateStatus(@RequestParam("id") int id) {
        responseService.updateStatus(id);
        return "redirect:/";
    }

    @GetMapping("/updateRespDescGet")
    public String updateDesc(@RequestParam("id") int id, Model model) {
        model.addAttribute("response", responseService.findById(id));
        return "update";
    }

    @PostMapping("/updateRespDescPost")
    public String updateDesc(@RequestParam("id") int id, HttpServletRequest req) {
        String name = req.getParameter("name");
        Response response = responseService.findById(id);
        response.setName(name);
        responseService.addResponse(response);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String create() {
        return "search";
    }

    @PostMapping("/findName")
    public String findResponseByName(Model model, HttpServletRequest req) {
        String name = req.getParameter("name");
        model.addAttribute("response", responseService.findResponseByName(name));
        return "search";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        responseService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/deleteAll")
    public String deleteAll() {
        responseService.deleteAll();
        return "redirect:/";
    }
}
