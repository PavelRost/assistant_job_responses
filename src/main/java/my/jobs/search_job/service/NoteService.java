package my.jobs.search_job.service;

import my.jobs.search_job.model.Note;
import my.jobs.search_job.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRep;

    public NoteService(NoteRepository noteRep) {
        this.noteRep = noteRep;
    }

    public void save(Note note) {
        noteRep.save(note);
    }

    public void delete(int id) {
        noteRep.delete(noteRep.findById(id).get());
    }

    public List<Note> getAllNote() {
        List<Note> rsl = new ArrayList<>();
        noteRep.findAll().forEach(rsl::add);
        return rsl;
    }

    public Note findById(int id) {
        return noteRep.findById(id).get();
    }
}
