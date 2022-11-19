package com.irfaan.danspro.controller;

import com.irfaan.danspro.exception.PathNotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: DefaultErrorController.java, v 0.1 2021‐11‐14 11.24 Ahmad Irfaan Hibatullah Exp $$
 */

@Controller
public class DefaultErrorController implements ErrorController {

    @GetMapping("/error")
    public void handleError() {
        throw new PathNotFoundException();
    }


    public String getErrorPath() {
        return "/error";
    }
}