package com.nbenzekri.fastsurvey.controller;

import com.nbenzekri.fastsurvey.dto.*;
import com.nbenzekri.fastsurvey.entity.Answer;
import com.nbenzekri.fastsurvey.entity.Poll;
import com.nbenzekri.fastsurvey.entity.Question;
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
    private PollServiceImpl pollServiceImpl;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @GetMapping("/v1/polls/{id}")
    public ResponseEntity<ResponseDTO<PollDTO>> getById(@PathVariable String id) {
        _logger.info("Get Poll By ID " + id);
        PollDTO pollDto = modelMapper.map(this.pollServiceImpl.findById(id), PollDTO.class);
        pollDto.setQuestionList(this.getPollQuestionsDTOs(id));
        // get the votes of the poll
        return new ResponseDTO<>(pollDto).buildOk();
    }

    @Override
    @GetMapping("/v1/polls")
    public ResponseEntity<ResponseDTO<List<PollDTO>>> getAll() {
        _logger.info("Get All Polls");
        List<PollDTO> pollDTOS = pollServiceImpl
                .findAll()
                .stream()
                .map(poll -> modelMapper.map(poll, PollDTO.class))
                .collect(Collectors.toList());
        pollDTOS.forEach(pollDTO -> {
            pollDTO.setQuestionList(this.getPollQuestionsDTOs(pollDTO.getId()));
        });
        return new ResponseDTO<>(pollDTOS).buildOk();
    }

    @Override
    @PostMapping("/v1/polls")
    public ResponseEntity<ResponseDTO<PollDTO>> create(@RequestBody @Valid PollSaveDTO pollSaveDto) {
        _logger.info("Create new Poll");
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
        //Get the question list of the inserted poll to send it back to the client with the pollDTO
        List<QuestionDTO> questionDTOS = getPollQuestionsDTOs(pollDTO.getId());
        pollDTO.setQuestionList(questionDTOS);

        return new ResponseDTO<>(pollDTO).buildCreated();
    }

    @Override
    @PutMapping("/v1/polls/{id}")
    public ResponseEntity<ResponseDTO<PollDTO>> update(@PathVariable String id, @RequestBody PollDTO pollDto) {
        _logger.info("Update Poll with id: " + id);
        PollDTO pollDTO = modelMapper.map(this.pollServiceImpl.update(id, modelMapper.map(pollDto, Poll.class)), PollDTO.class);
        // update questions and answers
        pollDto.getQuestionList().forEach(questionDTO -> {
            Question question;
            if (questionDTO.getId() == null) {
                question = modelMapper.map(questionDTO, Question.class);
                question.setPollId(pollDTO.getId());
                question = this.questionService.save(question);
                List<Answer> answersToSave = questionDTO
                        .getAnswers()
                        .stream()
                        .map(answerSaveDTO -> modelMapper.map(answerSaveDTO, Answer.class))
                        .collect(Collectors.toList());
                this.answerService.save(question.getId(), answersToSave);
            } else {
                this.questionService.update(questionDTO.getId(), modelMapper.map(questionDTO, Question.class));
            }
        });
        pollDTO.setQuestionList(this.getPollQuestionsDTOs(id));
        return new ResponseDTO<>(pollDTO).buildOk();
    }

    @Override
    @DeleteMapping("/v1/polls/{id}")
    public ResponseEntity<ResponseDTO<Map<String, Boolean>>> delete(@PathVariable String id) {
        _logger.info("Delete Poll with Id: " + id);
        this.pollServiceImpl.deleteById(id);
        this.questionService.findByPollId(id)
                .forEach(question -> {
                            this.answerService.findByQuestionId(question.getId())
                                    .forEach(answer -> this.answerService.deleteById(answer.getId()));
                            this.questionService.deleteById(question.getId());
                        }
                );
        _logger.info("Poll deleted!");
        return new ResponseDTO<>(Collections.singletonMap("deleted", Boolean.TRUE)).buildOk();
    }

    /**
     * Get the Questions of a spesific POLL
     *
     * @return List of Questions DTOs
     */
    private List<QuestionDTO> getPollQuestionsDTOs(String pollId) {
        List<Question> questionsByPollId = this.questionService.findByPollId(pollId);
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
        return questionDTOS;
    }
}
