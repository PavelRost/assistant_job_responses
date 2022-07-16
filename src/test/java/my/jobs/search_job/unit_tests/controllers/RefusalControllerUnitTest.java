package my.jobs.search_job.unit_tests.controllers;

import my.jobs.search_job.controller.RefusalController;
import my.jobs.search_job.model.Response;
import my.jobs.search_job.service.ResponseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(RefusalController.class)
public class RefusalControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResponseService responseService;

    @Test
    public void whenGetResponseRefusalThenStatus200() throws Exception {
        List<Response> responseTrueFalse = List.of(new Response("TrueFalse"));
        Mockito.when(responseService.findRespDoneTrueArchiveFalse()).thenReturn(responseTrueFalse);

        mockMvc.perform(get("/refusal"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("responses"))
                .andExpect(model().attribute("responses", responseTrueFalse));

        verify(responseService).findRespDoneTrueArchiveFalse();
    }

}
