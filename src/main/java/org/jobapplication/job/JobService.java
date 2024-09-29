package org.jobapplication.job;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {

  // private List<Jobs> jobs = new ArrayList<>();
    JobRepositories jobRepositories;

    /*
    * Why create constructor:- It is bean that is being managed by spring and because of this
    * constructor it will be autowired
    * at run time.*/

    public JobService(JobRepositories jobRepositories) {
        this.jobRepositories = jobRepositories;
    }

    public List<Jobs> findAll(){
        return jobRepositories.findAll();
    }

    public String createJob(Jobs job){
        //jobs.add(job);
        jobRepositories.save(job);
        return "Jobs added successfully";
    }

    public Jobs getJobbyid(Long id) {
     /*   for(Jobs job : jobs){
            if(job.getId() == id){
                return job;
            }
        }
        return null; */
        return jobRepositories.findById(id).orElse(null);
    }

    public String deleteJobbyid(Long id){
       /* for(Jobs job : jobs){
            if(job.getId() == id){
                jobs.remove(job);
                return "Jobs deleted successfully";
            }
        }
        return null; */
        try {
            jobRepositories.deleteById(id);
            return "Jobs deleted successfully";
        }catch (Exception e){
            return "Job not found";
        }
    }

    public Jobs updateJob(Long id, Jobs updatejob){
        Optional<Jobs> jobsOptional = jobRepositories.findById(id);
        if(jobsOptional.isPresent()){
            Jobs job = jobsOptional.get();
            job.setTitle(updatejob.getTitle());
            job.setDescription(updatejob.getDescription());
            job.setMinsalary(updatejob.getMinsalary());
            job.setMaxsalary(updatejob.getMaxsalary());
            job.setLocation(updatejob.getLocation());
            jobRepositories.save(job);
        }
        return null;
    }
}
