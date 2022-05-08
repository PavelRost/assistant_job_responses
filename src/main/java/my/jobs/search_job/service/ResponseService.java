package my.jobs.search_job.service;

import my.jobs.search_job.model.Response;
import my.jobs.search_job.repository.ResponseRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ResponseService {

    private final ResponseRepository responseRep;

    public ResponseService(ResponseRepository responseRep) {
        this.responseRep = responseRep;
    }

    public void addResponse(Response response) {
        responseRep.save(response);
    }

    public List<Response> findRespDoneArchiveFalse() {
        List<Response> rsl = responseRep.findResponseByDoneFalseAndArchiveFalse();
        rsl.sort(Comparator.reverseOrder());
        return rsl;
    }

    public List<Response> findRespDoneTrueArchiveFalse() {
        List<Response> rsl = responseRep.findResponseByDoneTrueAndArchiveFalse();
        rsl.sort(Comparator.reverseOrder());
        return rsl;
    }

    public void updateStatusDone(int id) {
        Response response = responseRep.findById(id).get();
        response.setDone(true);
        responseRep.save(response);
    }

    public void updateStatusArchive(int id) {
        Response response = responseRep.findById(id).get();
        response.setArchive(true);
        responseRep.save(response);
    }

    public List<Response> findResponseByName(String name) {
        List<Response> rsl = responseRep.searchResponseByNameLike(name);
        rsl.sort(Comparator.reverseOrder());
        return rsl;
    }

    public void delete(int id) {
        responseRep.delete(responseRep.findById(id).get());
    }

    public void deleteAll() {
        responseRep.deleteAll();
    }

    public Response findById(int id) {
        return responseRep.findById(id).get();
    }

    public List<Response> findRespDoneFalseArchiveTrue() {
        List<Response> rsl = responseRep.findResponseByDoneFalseAndArchiveTrue();
        rsl.sort(Comparator.reverseOrder());
        return rsl;
    }




}
