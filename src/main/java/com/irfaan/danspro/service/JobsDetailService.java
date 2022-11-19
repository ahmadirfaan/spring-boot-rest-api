package com.irfaan.danspro.service;

import com.irfaan.danspro.models.JobResponse;

import java.util.List;

public interface JobsDetailService {

    List<JobResponse> getAllJob();

    JobResponse getJobDetails(String id);
}
