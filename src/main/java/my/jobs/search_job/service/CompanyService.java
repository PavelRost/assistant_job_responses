package my.jobs.search_job.service;

import my.jobs.search_job.model.Company;
import my.jobs.search_job.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRep;

    public CompanyService(CompanyRepository companyRep) {
        this.companyRep = companyRep;
    }

    public void addCompany(Company company) {
        companyRep.save(company);
    }

    public List<Company> findAllWithDoneFalse() {
        List<Company> rsl = companyRep.findCompanyByDoneFalse();
        rsl.sort(Comparator.reverseOrder());
        return rsl;
    }

    public List<Company> findAllWithDoneTrue() {
        List<Company> rsl = companyRep.findCompanyByDoneTrue();
        rsl.sort(Comparator.reverseOrder());
        return rsl;
    }

    public void updateStatus(int id) {
        Company company = companyRep.findById(id).get();
        company.setDone(true);
        companyRep.save(company);
    }

    public Company findCompanyByName(String name) {
        return companyRep.findCompanyByName(name).get();
    }
}
