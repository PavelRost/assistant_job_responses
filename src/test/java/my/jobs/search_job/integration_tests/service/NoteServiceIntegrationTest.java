package my.jobs.search_job.integration_tests.service;

import my.jobs.search_job.model.Note;
import my.jobs.search_job.model.Response;
import my.jobs.search_job.repository.NoteRepository;
import my.jobs.search_job.service.NoteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/delete-all-note-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/delete-all-note-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class NoteServiceIntegrationTest {

    @Autowired
    private NoteService noteService;

    @Test
    public void whenFindByIdThenSuccess() {
        Note expect1 = noteService.findById(1);
        //Note expect1 = noteRepository.findById(1).get();
        Assertions.assertEquals(expect1.getDescription(), "1");
    }

    @Test
    public void whenDeleteNoteThenSuccess() {
        Note note = noteService.findById(1);
        noteService.delete(note.getId());
        NoSuchElementException thrown = Assertions.assertThrows(NoSuchElementException.class, () ->
        {
            noteService.findById(1);
        });
        Assertions.assertEquals("No value present", thrown.getMessage());
    }

    @Test
    public void whenGetAllNoteThenSuccess() {
        List<Note> notes = noteService.getAllNote();
        Assertions.assertEquals("1", notes.get(0).getDescription());
        Assertions.assertEquals("2", notes.get(1).getDescription());
        Assertions.assertEquals("3", notes.get(2).getDescription());
    }





}
