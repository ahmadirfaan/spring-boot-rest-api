package com.irfaan.restapi.service;

import com.irfaan.restapi.models.JobResponse;

import java.util.List;

public interface JobsDetailService {

    List<JobResponse> getAllJob();

    JobResponse getJobDetails(String id);
}
