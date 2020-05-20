package ro.ubb.project.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.project.core.service.AssignmentService;
import ro.ubb.project.web.converter.AssignmentConverter;
import ro.ubb.project.web.dto.AssignmentDto;
import ro.ubb.project.web.response.MessageResponse;

@RestController
@RequestMapping("/api/assignment")
public class AssignmentController {

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    private AssignmentConverter assignmentConverter;

    @RequestMapping(value = "/review", method = RequestMethod.PUT)
    public MessageResponse submitReview(@RequestBody AssignmentDto assignmentDto) {
        try {
            assignmentService.updateAssignment(assignmentConverter.dtoToModel(assignmentDto));
            return new MessageResponse("true");
        } catch (RuntimeException e) {
            return new MessageResponse("false");
        }
    }

    @RequestMapping(value = "/review/{pcid}/{pid}", method = RequestMethod.GET)
    public MessageResponse getReviewURL(@PathVariable Integer pcid, @PathVariable Integer pid) {
        try {
            return new MessageResponse(assignmentService.getAssignmentById(pcid, pid).getReviewUrl());
        } catch (RuntimeException e) {
            return new MessageResponse("false");
        }
    }


}
