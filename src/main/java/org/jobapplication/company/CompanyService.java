package org.jobapplication.company;

import org.jobapplication.job.Jobs;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

   private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getallCompanies(){
        return companyRepository.findAll();
    }

    public Company updateCompany(Long id, Company company){
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setDescription(company.getDescription());
            companyToUpdate.setName(company.getName());
            companyToUpdate.setJobs(company.getJobs());
            companyRepository.save(companyToUpdate);
        }
        return null;
    }

    public void createCompany(Company company){
        companyRepository.save(company);
    }

    public boolean deletComanyById(Long id){
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public Company getById(Long id){
        return companyRepository.findById(id).orElse(null);
    }
}
