package my.jobs.search_job.repository;

import my.jobs.search_job.model.Response;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResponseRepository extends CrudRepository<Response, Integer> {

    List<Response> findResponseByDoneFalse();

    List<Response> findResponseByDoneTrue();

    List<Response> findResponseByArchiveTrue();

    List<Response> findResponseByDoneFalseAndArchiveFalse();

    List<Response> findResponseByDoneFalseAndArchiveTrue();

    List<Response> findResponseByDoneTrueAndArchiveFalse();

    @Query("SELECT res FROM Response res WHERE res.name LIKE %:name%")
    List<Response> searchResponseByNameLike(@Param("name") String name);
}
