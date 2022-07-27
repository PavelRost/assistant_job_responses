package my.jobs.search_job.service;

import my.jobs.search_job.model.Response;
import my.jobs.search_job.repository.ResponseRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
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
        List<Response> responseList = responseRep.findResponseByDoneFalseAndArchiveFalse();
        responseList.sort(Comparator.reverseOrder());
        return responseList;
    }

    public List<Response> findRespDoneTrueArchiveFalse() {
        List<Response> responseList = responseRep.findResponseByDoneTrueAndArchiveFalse();
        responseList.sort(Comparator.reverseOrder());
        return responseList;
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
        List<Response> responseList = responseRep.searchResponseByNameLike(name);
        responseList.sort(Comparator.reverseOrder());
        return responseList;
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
        List<Response> responseList = responseRep.findResponseByDoneFalseAndArchiveTrue();
        responseList.sort(Comparator.reverseOrder());
        return responseList;
    }

    public int getCountResponseToday() {
        List<Response> responseList = responseRep.findResponseByDoneFalseAndArchiveFalse();
        return (int) responseList.stream()
                .filter(response -> response.getCreated().getDate() == new Date().getDate())
                .count();
    }

    public int getCountResponseYesterday() {
        List<Response> responseList = responseRep.findResponseByDoneFalseAndArchiveFalse();
        return (int) responseList.stream()
                .filter(response -> response.getCreated().getDate() == (new Date().getDate() - 1))
                .count();
    }

    public String getMotivationText(int countRespToday, int countRespYesterday) {
        String motivation;
        if (countRespToday > countRespYesterday) {
            motivation = "Отличный результат, продолжай в том же духе!";
        } else if (countRespToday < countRespYesterday) {
            motivation = "Сегодня ты мало откликался, так работу не найдешь!";
        } else {
            motivation = countRespToday == 0 ? "За два дня ни одного отклика, хватит бездельничать!" : "Количество откликов сравнялось со вчерашним днем, неплохо, навались еще!";
        }
        return motivation;
    }
}
