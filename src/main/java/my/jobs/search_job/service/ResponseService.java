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

    public List<Response> findAllWithDoneFalse() {
        List<Response> rsl = responseRep.findResponseByDoneFalse();
        rsl.sort(Comparator.reverseOrder());
        return rsl;
    }

    public List<Response> findAllWithDoneTrue() {
        List<Response> rsl = responseRep.findResponseByDoneTrue();
        rsl.sort(Comparator.reverseOrder());
        return rsl;
    }

    public void updateStatus(int id) {
        Response response = responseRep.findById(id).get();
        response.setDone(true);
        responseRep.save(response);
    }

    public List<Response> findResponseByName(String name) {
        return responseRep.searchResponseByNameLike(name);
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


}
