package org.jobapplication.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Company>> findall(){
        return new ResponseEntity<>(companyService.getallCompanies(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company saved successfully", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<String> UpdateCompany(@PathVariable Long id, @RequestBody Company company){
        companyService.updateCompany(id,company);
        return new ResponseEntity<>("Company updated successfully", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<String> DeleteCompany(@PathVariable Long id){
       boolean isDeleted = companyService.deletComanyById(id);
       if(isDeleted){
           return new ResponseEntity<>("Company deleted succesfully", HttpStatus.OK);
       }
       return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, value= "/{id}")
    public ResponseEntity<Company> getCompanyByid(@PathVariable ("id") Long id){
        Company comp = companyService.getById(id);
        if(comp != null){
            return new ResponseEntity<>(comp, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
