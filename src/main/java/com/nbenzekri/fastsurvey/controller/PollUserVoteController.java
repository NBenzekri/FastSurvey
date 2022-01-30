package com.nbenzekri.fastsurvey.controller;

import com.nbenzekri.fastsurvey.dto.PollUserVoteDTO;
import com.nbenzekri.fastsurvey.dto.PollUserVoteSaveDTO;
import com.nbenzekri.fastsurvey.dto.response.ResponseDTO;
import com.nbenzekri.fastsurvey.entity.PollUserVote;
import com.nbenzekri.fastsurvey.entity.VoteAnswer;
import com.nbenzekri.fastsurvey.service.PollUserVoteService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/votes")
public class PollUserVoteController implements IGenericController<PollUserVoteDTO, PollUserVoteSaveDTO> {

    private static final Logger _logger = LoggerFactory.getLogger(PollUserVoteController.class);

    @Autowired
    PollUserVoteService pollUserVoteService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<ResponseDTO<List<PollUserVoteDTO>>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDTO<PollUserVoteDTO>> getById(String id) {
        return null;
    }

    @Override
    @PostMapping(value = "")
    @ApiOperation(value = "Create new Poll user vote")
    public ResponseEntity<ResponseDTO<PollUserVoteDTO>> create(@RequestBody @Valid PollUserVoteSaveDTO entityDto) {
        _logger.info("Create a poll vote");
        // This configuration only makes ModelMapper skip the unknown  fields
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        PollUserVote pollUserVote = new PollUserVote(entityDto.getPollId());
        pollUserVote.setVoteAnswers(
                entityDto.getVoteAnswers().stream().map(voteAnswersDTO -> modelMapper.map(voteAnswersDTO, VoteAnswer.class))
                        .collect(Collectors.toList())
        );
        PollUserVoteDTO pollUserVoteDTO = modelMapper.map(this.pollUserVoteService.save(pollUserVote), PollUserVoteDTO.class);
        return new ResponseDTO<>(pollUserVoteDTO).buildCreated();
    }

//    public List<VoteAnswersDTO> getVoteAnswersDTO(String pollUserVoteId) {
//        return this.voteAnswerService.findByPollUserVoteId(pollUserVoteId)
//                .stream()
//                .map(voteAnswer -> modelMapper.map(voteAnswer, VoteAnswersDTO.class))
//                .collect(Collectors.toList());
//    }

    @Override
    public ResponseEntity<ResponseDTO<PollUserVoteDTO>> update(String id, PollUserVoteDTO entityDto) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseDTO<Map<String, Boolean>>> delete(String id) {
        return null;
    }
}
