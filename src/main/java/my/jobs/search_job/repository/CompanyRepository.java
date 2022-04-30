package my.jobs.search_job.repository;

import my.jobs.search_job.model.Company;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

    List<Company> findCompanyByDoneFalse();

    List<Company> findCompanyByDoneTrue();

    Optional<Company> findCompanyByName(String nameCompany);

}
