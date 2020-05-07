package ro.ubb.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ro.ubb.project.core.service.PaperService;
import ro.ubb.project.web.converter.PaperConverter;
import ro.ubb.project.web.dto.PaperDto;
import ro.ubb.project.web.response.MessageResponse;
import ro.ubb.project.web.response.PapersResponse;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/paper")
public class PaperController {
    @Autowired
    private PaperService paperService;

    @Autowired
    private PaperConverter converter;

    @RequestMapping(value = "/getAllPapers",method = RequestMethod.GET)
    PapersResponse getAllPapers(){
        return new PapersResponse((ArrayList<PaperDto>) converter.convertModelsToDtos(paperService.getAllPapers()));
    }

    @RequestMapping(value = "/getAbstractUrl/{id}", method = RequestMethod.GET)
    MessageResponse getAbstractUrl(@PathVariable Integer id){
        return new MessageResponse(
                paperService.getPaperById(id).getAbstracturl()
        );
    }

    @RequestMapping(value = "/getContentUrl/{id}", method = RequestMethod.GET)
    MessageResponse getContentUrl(@PathVariable Integer id){
        return new MessageResponse(
                paperService.getPaperById(id).getContenturl()
        );
    }

    @RequestMapping(value = "/getPaper/{id}", method = RequestMethod.GET)
    PaperDto getPaperWithId(@PathVariable Integer id){
        return converter.modelToDto(paperService.getPaperById(id));
    }

}
