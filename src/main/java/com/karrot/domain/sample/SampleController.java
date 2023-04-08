package com.karrot.domain.sample;

import com.karrot.global.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1/sample")
public class SampleController extends BaseController {
    @Autowired
    SampleRepository sampleRepository;

    @GetMapping("/findSamples")
    public ResponseEntity<List<SampleVO>> getAllSamples(@RequestParam(required = false) String name){
        try {
            List<SampleVO> samples = new ArrayList<>();

            if(name == null){
                sampleRepository.findAll().forEach(samples::add);
            } else {
                sampleRepository.findByName(name).forEach(samples::add);
            }

            if(samples.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(samples, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
