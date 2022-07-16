package my.jobs.search_job.unit_tests.controllers;


import my.jobs.search_job.controller.NoteController;
import my.jobs.search_job.model.Note;
import my.jobs.search_job.service.NoteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoteController.class)
public class NoteControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Test
    public void whenGetAllNoteThenStatus200() throws Exception {
        List<Note> listNote = List.of(new Note("Test"));
        Mockito.when(noteService.getAllNote()).thenReturn(listNote);

        mockMvc.perform(get("/indexNote"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("notes"))
                .andExpect(model().attribute("notes", listNote))
                .andExpect(forwardedUrl("/WEB-INF/views/note/indexNote.jsp"));

        verify(noteService).getAllNote();
    }

    @Test
    public void whenRedirectToCreateNoteThenStatus200() throws Exception {
        mockMvc.perform(get("/newNote"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/note/createNote.jsp"));
    }

    @Test
    public void whenCreateNoteThenSuccess() throws Exception {
        Note noteTest = new Note("OLD");
        mockMvc.perform(post("/createNote")
                        .param("description", "NEW")
                        .flashAttr("note", noteTest)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/indexNote"));

        assertEquals("NEW", noteTest.getDescription());
    }

    @Test
    public void whenGetNoteForUpdateStatus200() throws Exception {
        Note testNote = new Note("Test");
        Mockito.when(noteService.findById(testNote.getId())).thenReturn(testNote);

        mockMvc.perform(get("/updateNoteGet")
                        .param("id", String.valueOf(testNote.getId()))
                )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("note"))
                .andExpect(model().attribute("note", testNote))
                .andExpect(forwardedUrl("/WEB-INF/views/note/updateNote.jsp"));

        verify(noteService).findById(testNote.getId());
    }

    @Test
    public void whenUpdateNoteThenSuccess() throws Exception {
        Note noteTest = new Note("OLD");
        Mockito.when(noteService.findById(noteTest.getId())).thenReturn(noteTest);

        mockMvc.perform(post("/updateNotePost")
                        .param("description", "NEW")
                        .param("id", String.valueOf(noteTest.getId()))
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/indexNote"));

        assertEquals("NEW", noteTest.getDescription());
        verify(noteService).findById(noteTest.getId());
    }

    @Test
    public void whenDeleteNoteThenSuccess() throws Exception {
        Note noteTest = new Note();

        mockMvc.perform(get("/deleteNote")
                .param("id", String.valueOf(noteTest.getId()))
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/indexNote"));
    }










}
