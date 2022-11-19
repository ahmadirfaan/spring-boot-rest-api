package com.irfaan.danspro.service.impl;

import com.irfaan.danspro.models.JobResponse;
import com.irfaan.danspro.service.JobsDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class JobsDetailServiceImpl implements JobsDetailService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String URL_JOB_ALL = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";

    private final String URL_JOB_DETAIL = "http://dev3.dansmultipro.co.id/api/recruitment/positions";


    @Override
    public List<JobResponse> getAllJob() {
        ResponseEntity<JobResponse[]> exchange = restTemplate.getForEntity(URL_JOB_ALL, JobResponse[].class);
        return Arrays.asList(exchange.getBody());
    }

    @Override
    public JobResponse getJobDetails(String id) {
        String url = String.format("%s/%s", URL_JOB_DETAIL, id);
        ResponseEntity<JobResponse> exchange = restTemplate.getForEntity(url, JobResponse.class);
        return exchange.getBody();
    }
}
