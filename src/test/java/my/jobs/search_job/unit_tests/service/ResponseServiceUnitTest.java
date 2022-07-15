package my.jobs.search_job.unit_tests.service;

import my.jobs.search_job.model.Response;
import my.jobs.search_job.repository.ResponseRepository;
import my.jobs.search_job.service.ResponseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ResponseServiceUnitTest {

    @Mock
    private ResponseRepository responseRepository;

    @InjectMocks
    private ResponseService responseService;

    private final Response responseTest = mock(Response.class);

    @Test
    public void whenAddResponseSuccess() {
        Mockito.when(responseRepository.save(Mockito.any())).thenReturn(responseTest);
        responseService.addResponse(responseTest);
        verify(responseRepository).save(responseTest);
    }

    @Test
    public void whenFindByIdResponseThenSuccess() {
        Mockito.when(responseRepository.findById(responseTest.getId())).thenReturn(Optional.of(responseTest));

        Response actual = responseService.findById(responseTest.getId());

        assertNotNull(actual);
        assertEquals(responseTest, actual);
        verify(responseRepository).findById(responseTest.getId());
    }

    @Test
    public void whenDeleteResponseByIdThenSuccess() {
        Mockito.when(responseRepository.findById(responseTest.getId())).thenReturn(Optional.of(responseTest));
        responseService.delete(responseTest.getId());
        verify(responseRepository).findById(responseTest.getId());
    }

    @Test
    public void whenResponseDoneArchiveFalseSortReverseSuccess() throws InterruptedException {
        List<Response> responseListTest = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            responseListTest.add(new Response(String.valueOf(i)));
            Thread.sleep(500);
        }
        Mockito.when(responseRepository.findResponseByDoneFalseAndArchiveFalse()).thenReturn(responseListTest);
        List<Response> actual = responseService.findRespDoneArchiveFalse();
        assertNotNull(actual);
        assertEquals(new Response("2").getName(), actual.get(0).getName());
        verify(responseRepository).findResponseByDoneFalseAndArchiveFalse();
    }

    @Test
    public void whenResponseDoneTrueArchiveFalseSortReverseSuccess() throws InterruptedException {
        List<Response> responseListTest = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            responseListTest.add(new Response(String.valueOf(i)));
            Thread.sleep(500);
        }
        Mockito.when(responseRepository.findResponseByDoneTrueAndArchiveFalse()).thenReturn(responseListTest);
        List<Response> actual = responseService.findRespDoneTrueArchiveFalse();
        assertNotNull(actual);
        assertEquals(new Response("2").getName(), actual.get(0).getName());
        verify(responseRepository).findResponseByDoneTrueAndArchiveFalse();
    }

    @Test
    public void whenResponseDoneFalseArchiveTrueSortReverseSuccess() throws InterruptedException {
        List<Response> responseListTest = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            responseListTest.add(new Response(String.valueOf(i)));
            Thread.sleep(500);
        }
        Mockito.when(responseRepository.findResponseByDoneFalseAndArchiveTrue()).thenReturn(responseListTest);
        List<Response> actual = responseService.findRespDoneFalseArchiveTrue();
        assertNotNull(actual);
        assertEquals(new Response("2").getName(), actual.get(0).getName());
        verify(responseRepository).findResponseByDoneFalseAndArchiveTrue();
    }

    @Test
    public void whenUpdateStatusDoneThenSuccess() {
        Response response = new Response("Test");
        Mockito.when(responseRepository.findById(response.getId())).thenReturn(Optional.of(response));
        Mockito.when(responseRepository.save(Mockito.any())).thenReturn(responseTest);

        responseService.updateStatusDone(response.getId());

        assertTrue(response.getDone());
        verify(responseRepository).findById(responseTest.getId());
        verify(responseRepository).save(Mockito.any());
    }

    @Test
    public void whenUpdateStatusArchiveThenSuccess() {
        Response response = new Response("Test");
        Mockito.when(responseRepository.findById(response.getId())).thenReturn(Optional.of(response));
        Mockito.when(responseRepository.save(Mockito.any())).thenReturn(responseTest);

        responseService.updateStatusArchive(response.getId());

        assertTrue(response.getArchive());
        verify(responseRepository).findById(responseTest.getId());
        verify(responseRepository).save(Mockito.any());
    }

    @Test
    public void whenFindResponseByNameSortReverseSuccess() throws InterruptedException {
        List<Response> responseListTest = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            responseListTest.add(new Response("test" + i));
            Thread.sleep(500);
        }
        Mockito.when(responseRepository.searchResponseByNameLike("test")).thenReturn(responseListTest);
        List<Response> actual = responseService.findResponseByName("test");
        assertNotNull(actual);
        assertEquals(new Response("test2").getName(), actual.get(0).getName());
        verify(responseRepository).searchResponseByNameLike("test");
    }


}
