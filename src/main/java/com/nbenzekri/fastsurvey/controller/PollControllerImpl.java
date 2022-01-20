package com.nbenzekri.fastsurvey.controller;

import com.nbenzekri.fastsurvey.dto.PollDTO;
import com.nbenzekri.fastsurvey.dto.ResponseDTO;
import com.nbenzekri.fastsurvey.entity.Poll;
import com.nbenzekri.fastsurvey.repository.PollRepository;
import com.nbenzekri.fastsurvey.service.PollServiceImpl;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(value = "PollController", tags = "Poll controller")
public class PollControllerImpl implements IGenericController<PollDTO> {

    private static final Logger _logger = LoggerFactory.getLogger(PollControllerImpl.class);

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private PollServiceImpl pollServiceImpl;

    @Autowired
    private ModelMapper modelMapper;

//    @GetMapping("/test")
//    @Override
//    public String getTest() {
//        _logger.info("Hello requested!");
//        return "hello world!";
//    }

    @Override
    @GetMapping("/v1/polls/{id}")
    public ResponseEntity<ResponseDTO<PollDTO>> getPollById(@PathVariable String id) {
        return new ResponseDTO<>(modelMapper.map(this.pollServiceImpl.findById(id), PollDTO.class)).buildOk();
    }

    @Override
    @GetMapping("/v1/polls")
    public ResponseEntity<ResponseDTO<List<PollDTO>>> getPolls() {
        return new ResponseDTO<>(pollServiceImpl.findAll().stream().map(poll -> modelMapper.map(poll, PollDTO.class)).collect(Collectors.toList())).buildOk();
    }

    @Override
    @PostMapping("/v1/polls")
    public ResponseEntity<ResponseDTO<PollDTO>> createPoll(@RequestBody @Valid PollDTO pollDto) {
        return new ResponseDTO<>(modelMapper.map(this.pollServiceImpl.save(modelMapper.map(pollDto, Poll.class)), PollDTO.class)).buildCreated();
    }

    @Override
    @PutMapping("/v1/polls/{id}")
    public ResponseEntity<ResponseDTO<PollDTO>> updatePoll(@PathVariable String id, @RequestBody PollDTO pollDto) {
        return null;
    }

    @Override
    @DeleteMapping("/v1/polls/{id}")
    public ResponseEntity<ResponseDTO<Map<String, Boolean>>> deletePoll(@PathVariable String id) {
        Map<String, Boolean> x = new HashMap<>();
        x.put("deleted", Boolean.TRUE);
        return new ResponseDTO<>(x).buildOk();
    }
}
