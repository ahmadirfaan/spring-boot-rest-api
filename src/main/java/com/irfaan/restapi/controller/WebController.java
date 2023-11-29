package com.irfaan.restapi.controller;

import com.irfaan.restapi.entities.Users;
import com.irfaan.restapi.models.JobResponse;
import com.irfaan.restapi.models.UserLoginRequest;
import com.irfaan.restapi.models.UserRequestDTO;
import com.irfaan.restapi.service.JobsDetailService;
import com.irfaan.restapi.service.LoginService;
import com.irfaan.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WebController {

    protected final LoginService loginService;

    protected final JobsDetailService jobsDetailService;

    protected final UserService userService;

    @Autowired
    public WebController(LoginService loginService, JobsDetailService jobsDetailService, UserService userService) {
        this.loginService = loginService;
        this.jobsDetailService = jobsDetailService;
        this.userService = userService;
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

    @PostMapping(value = "/user")
    public ResponseEntity<Map<String, String>> addUsers(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        Users users = userService.addUser(userRequestDTO);
        Map<String, String> body = new HashMap<>();
        body.put("id", users.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

}
