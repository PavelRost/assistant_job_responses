package my.jobs.search_job.controller;

import my.jobs.search_job.model.Company;
import my.jobs.search_job.model.Note;
import my.jobs.search_job.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/indexNote")
    public String indexNote(Model model) {
        model.addAttribute("notes", noteService.getAllNote());
        return "note/indexNote";
    }

    @GetMapping("/newNote")
    public String indexCreate() {
        return "note/createNote";
    }

    @PostMapping("/createNote")
    public String saveNote(@ModelAttribute Note note, HttpServletRequest req) {
        String desc = req.getParameter("description");
        note.setDescription(desc);
        noteService.save(note);
        return "redirect:/indexNote";
    }

    @GetMapping("/updateNoteGet")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("note", noteService.findById(id));
        return "note/updateNote";
    }

    @PostMapping("/updateNotePost")
    public String update(@RequestParam("id") int id, HttpServletRequest req) {
        String desc = req.getParameter("description");
        Note note = noteService.findById(id);
        note.setDescription(desc);
        noteService.save(note);
        return "redirect:/indexNote";
    }

    @GetMapping("/deleteNote")
    public String update(@RequestParam("id") int id) {
        noteService.delete(id);
        return "redirect:/indexNote";
    }

}
