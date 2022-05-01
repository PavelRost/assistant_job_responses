package my.jobs.search_job.repository;

import my.jobs.search_job.model.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

    List<Company> findCompanyByDoneFalse();

    List<Company> findCompanyByDoneTrue();

    @Query("SELECT comp FROM Company comp WHERE comp.name LIKE %:name%")
    List<Company> searchCompanyByNameLike(@Param("name") String name);
}
