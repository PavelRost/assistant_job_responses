package my.jobs.search_job.integration_tests.service;

import my.jobs.search_job.model.Note;
import my.jobs.search_job.model.Response;
import my.jobs.search_job.service.ResponseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.Assert;

import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/delete-all-response-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/delete-all-response-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ResponseServiceIntegrationTest {

    @Autowired
    private ResponseService responseService;

    @Test
    public void whenFindResponseDoneAndArchiveFalseSuccess() {
        List<Response> respFalse = responseService.findRespDoneArchiveFalse();
        Assertions.assertEquals("2", respFalse.get(0).getName());
        Assertions.assertEquals("1", respFalse.get(1).getName());
        Assertions.assertEquals("3", respFalse.get(2).getName());
    }

    @Test
    public void whenFindResponseDoneTrueArchiveFalseThenSuccess() {
        List<Response> respTrueFalse = responseService.findRespDoneTrueArchiveFalse();
        Assertions.assertEquals("5", respTrueFalse.get(0).getName());
        Assertions.assertEquals("4", respTrueFalse.get(1).getName());
        Assertions.assertEquals("6", respTrueFalse.get(2).getName());
    }

    @Test
    public void whenUpdateStatusDoneThenSuccess() {
        responseService.updateStatusDone(1);
        Assertions.assertTrue(responseService.findById(1).getDone());
    }

    @Test
    public void whenUpdateStatusArchiveThenSuccess() {
        responseService.updateStatusArchive(1);
        Assertions.assertTrue(responseService.findById(1).getArchive());
    }

    @Test
    public void whenFindResponseByNameThenSuccess() {
        List<Response> responseList = responseService.findResponseByName("1");
        Assertions.assertEquals("1", responseList.get(0).getName());
        Assertions.assertEquals("111", responseList.get(1).getName());
    }

    @Test
    public void whenDeleteResponseThenSuccess() {
        Response response = responseService.findById(1);
        responseService.delete(response.getId());
        NoSuchElementException thrown = Assertions.assertThrows(NoSuchElementException.class, () ->
        {
            responseService.findById(1);
        });
        Assertions.assertEquals("No value present", thrown.getMessage());
    }

    @Test
    public void whenDeleteAllResponseThenSuccess() {
        responseService.deleteAll();
        List<Response> responseListFalse = responseService.findRespDoneArchiveFalse();
        List<Response> responseListTrueFalse = responseService.findRespDoneTrueArchiveFalse();
        List<Response> responseListFalseTrue = responseService.findRespDoneFalseArchiveTrue();
        Assertions.assertEquals(0, responseListFalse.size());
        Assertions.assertEquals(0, responseListFalseTrue.size());
        Assertions.assertEquals(0, responseListTrueFalse.size());
    }

    @Test
    public void whenFindByIdResponseThenSuccess() {
        Response response = responseService.findById(1);
        Assertions.assertEquals("1", response.getName());
    }

    @Test
    public void whenFindResponseDoneFalseArchiveTrueThenSuccess() {
        List<Response> responseList = responseService.findRespDoneFalseArchiveTrue();
        Assertions.assertEquals("8", responseList.get(0).getName());
        Assertions.assertEquals("7", responseList.get(1).getName());
        Assertions.assertEquals("9", responseList.get(2).getName());
        Assertions.assertEquals("111", responseList.get(3).getName());

    }











}
