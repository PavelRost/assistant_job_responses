package my.jobs.search_job.unit_tests.service;

import my.jobs.search_job.model.Note;
import my.jobs.search_job.repository.NoteRepository;
import my.jobs.search_job.service.NoteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class NoteServiceUnitTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    private final Note noteTest = mock(Note.class);

    @Test
    public void whenSaveNoteThenSuccess() {
        Mockito.when(noteRepository.save(Mockito.any())).thenReturn(noteTest);
        noteService.save(noteTest);
        verify(noteRepository).save(noteTest);
    }

    @Test
    public void whenFindByIdNoteThenSuccess() {
        Mockito.when(noteRepository.findById(noteTest.getId())).thenReturn(Optional.of(noteTest));

        Note actual = noteService.findById(noteTest.getId());

        assertNotNull(actual);
        assertEquals(noteTest, actual);
        verify(noteRepository).findById(noteTest.getId());
    }

    @Test
    public void whenGetAllNoteThenSuccess() {
        List<Note> noteListTest = List.of(noteTest, noteTest, noteTest);
        Mockito.when(noteRepository.findAll()).thenReturn(noteListTest);

        List<Note> actual = noteService.getAllNote();

        assertNotNull(actual);
        assertEquals(noteListTest, actual);
        verify(noteRepository).findAll();
    }

    @Test
    public void whenDeleteNoteSuccess() {
        Mockito.when(noteRepository.findById(noteTest.getId())).thenReturn(Optional.of(noteTest));
        noteService.delete(noteTest.getId());
        verify(noteRepository).findById(noteTest.getId());
        verify(noteRepository).delete(noteTest);
    }

}
