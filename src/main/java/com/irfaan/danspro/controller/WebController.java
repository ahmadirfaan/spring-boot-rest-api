package com.irfaan.danspro.controller;

import com.irfaan.danspro.models.JobResponse;
import com.irfaan.danspro.models.UserLoginRequest;
import com.irfaan.danspro.service.JobsDetailService;
import com.irfaan.danspro.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class WebController {

    protected final LoginService loginService;

    protected final JobsDetailService jobsDetailService;

    @Autowired
    public WebController(LoginService loginService, JobsDetailService jobsDetailService) {
        this.loginService = loginService;
        this.jobsDetailService = jobsDetailService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid UserLoginRequest request) {
        Map<String, String> data = loginService.userLogin(request);
        data.put("msg", "success login");
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping(value = "/jobs")
    public ResponseEntity<List<JobResponse>> getAllJob() {
        List<JobResponse> allJob = jobsDetailService.getAllJob();

        return ResponseEntity.status(HttpStatus.CREATED).body(allJob);
    }

    @GetMapping(value = "/jobs/{id}")
    public ResponseEntity<JobResponse> getDetailJob(@PathVariable String id) {
        JobResponse jobDetails = jobsDetailService.getJobDetails(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobDetails);
    }

}
