package org.jobapplication.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public class Jobcontroller {

    /* Another way to call service class is with help of constructor
    * public Jobcontroller(JobService jobService) {
        this.jobService = jobService;
    }
    * */

    @Autowired
    private JobService jobService;

    /* With the help of GetMapping wecan try */

    @RequestMapping(method= RequestMethod.GET, value = "/jobs")
    public ResponseEntity<List<Jobs>>findall(){
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST, value="/jobs")
    public ResponseEntity<String> createJob(@RequestBody Jobs Job){
        jobService.createJob(Job);
        return new ResponseEntity<>("Jobs added successfully", HttpStatus.OK);
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<Jobs> getbyid(@PathVariable ("id") Long id){
        Jobs job = jobService.getJobbyid(id);
        if(job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/jobs/{id}")
    public ResponseEntity<String> deleteJobbyid(@PathVariable Long id){
        jobService.deleteJobbyid(id);
        return new ResponseEntity<>("Jobs deleted successfully", HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Jobs updatejob){
        jobService.updateJob(id,updatejob);
        return new ResponseEntity<>("Jobs updated successfully", HttpStatus.OK);
    }
}
