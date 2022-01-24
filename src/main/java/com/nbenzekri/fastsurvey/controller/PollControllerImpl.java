package com.nbenzekri.fastsurvey.controller;

import com.nbenzekri.fastsurvey.dto.*;
import com.nbenzekri.fastsurvey.entity.Answer;
import com.nbenzekri.fastsurvey.entity.Poll;
import com.nbenzekri.fastsurvey.entity.Question;
import com.nbenzekri.fastsurvey.repository.PollRepository;
import com.nbenzekri.fastsurvey.service.AnswerService;
import com.nbenzekri.fastsurvey.service.PollServiceImpl;
import com.nbenzekri.fastsurvey.service.QuestionService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Api(value = "PollController", tags = "Poll controller")
public class PollControllerImpl implements IGenericController<PollDTO, PollSaveDTO> {

    private static final Logger _logger = LoggerFactory.getLogger(PollControllerImpl.class);

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private PollServiceImpl pollServiceImpl;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private ModelMapper modelMapper;

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
    public ResponseEntity<ResponseDTO<PollDTO>> createPoll(@RequestBody @Valid PollSaveDTO pollSaveDto) {
        // save the Poll
        PollDTO pollDTO = modelMapper.map(this.pollServiceImpl.save(modelMapper.map(pollSaveDto, Poll.class)), PollDTO.class);
        List<Question> questionList = pollSaveDto
                .getQuestionList()
                .stream()
                .map(questionSaveDTO -> modelMapper.map(questionSaveDTO, Question.class))
                .collect(Collectors.toList());
        questionList.forEach(q -> q.setPollId(pollDTO.getId()));
        // save the question with PollID
        // and save the answers with their question ID
        pollSaveDto
                .getQuestionList()
                .stream()
                .forEach(questionSaveDTO -> {
                    Question question = modelMapper.map(questionSaveDTO, Question.class);
                    question.setPollId(pollDTO.getId());
                    question = this.questionService.save(question);
                    List<Answer> answersToSave = questionSaveDTO
                            .getAnswers()
                            .stream()
                            .map(answerSaveDTO -> modelMapper.map(answerSaveDTO, Answer.class))
                            .collect(Collectors.toList());
                    this.answerService.save(question.getId(), answersToSave);
                });

        //Get the question list of the inserted poll
        // to send it back to the client with the pollDTO
        List<Question> questionsByPollId = this.questionService.findByPollId(pollDTO.getId());
        List<QuestionDTO> questionDTOS = questionsByPollId
                .stream()
                .map(question -> modelMapper.map(question, QuestionDTO.class))
                .collect(Collectors.toList());
        questionDTOS.forEach(questionDTO -> {
                    List<Answer> ansbyQuestionId = this.answerService.findByQuestionId(questionDTO.getId());
                    questionDTO
                            .setAnswers(ansbyQuestionId
                            .stream()
                            .map(answer -> modelMapper.map(answer, AnswerDTO.class))
                            .collect(Collectors.toList()));
                });
        pollDTO.setQuestionList(questionDTOS);

        return new ResponseDTO<>(pollDTO).buildCreated();
    }

    @Override
    @PutMapping("/v1/polls/{id}")
    public ResponseEntity<ResponseDTO<PollDTO>> updatePoll(@PathVariable String id, @RequestBody PollDTO pollDto) {
        return null;
    }

    @Override
    @DeleteMapping("/v1/polls/{id}")
    public ResponseEntity<ResponseDTO<Map<String, Boolean>>> deletePoll(@PathVariable String id) {
        this.pollServiceImpl.deleteById(id);
        return new ResponseDTO<>(Collections.singletonMap("deleted", Boolean.TRUE)).buildOk();
    }
}
