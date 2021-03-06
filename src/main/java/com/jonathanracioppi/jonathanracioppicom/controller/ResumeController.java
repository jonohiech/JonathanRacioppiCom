package com.jonathanracioppi.jonathanracioppicom.controller;

import com.jonathanracioppi.jonathanracioppicom.exception.ResourceNotFoundException;
import com.jonathanracioppi.jonathanracioppicom.model.*;
import com.jonathanracioppi.jonathanracioppicom.service.ResumeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
@Log4j2
public class ResumeController {


    @Autowired
    ResumeService resumeService;

    @PostMapping("/resume")
    public ResponseEntity getResume(@RequestBody String name) {
        log.trace("ResumeController.getResume - Start");
        ResponseEntity rs;

        try {

            Resume resume;
            resume = resumeService.getResumeByName(name);
            log.debug("Resume found");
            rs = new ResponseEntity<>(resume, HttpStatus.OK);

        } catch (ResourceNotFoundException rex) {
            rs = new ResponseEntity<>(rex, HttpStatus.NO_CONTENT);
        }
        log.trace("ResumeController.getResume - End");
        return rs;

    }

    @GetMapping("/resume/template")
    public Resume getResumeTemplate() {

        return Resume.builder().resumeId(1L)
                .resumeSection(Arrays.asList(new PersonJob(), new Education(), new Project())).build();


    }

    @PutMapping("/resume/update")
    public ResponseEntity<Resume> updateResume(@RequestBody Resume resume) {
        log.trace("ResumeController.updateResume - Start");

        ResponseEntity rs;
        try {
            resumeService.updateResume(resume);
            rs = new ResponseEntity(HttpStatus.OK);
        } catch (Exception exception) {
            log.error(exception.getMessage());
            rs = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.trace("ResumeController.updateResume - End");
        return rs;
    }


}
