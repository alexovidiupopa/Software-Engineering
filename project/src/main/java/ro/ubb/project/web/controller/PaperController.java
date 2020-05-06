package ro.ubb.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.project.core.model.Paper;
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

    @RequestMapping(value = "/upload-abstract",method = RequestMethod.POST)
    MessageResponse createPaper(@RequestBody PaperDto paperDto) {
        try {
            paperService.addPaper(converter.dtoToModel(paperDto));
            return new MessageResponse("success");
        } catch (RuntimeException e){
            return new MessageResponse("failed");
        }
    }

    @RequestMapping(value = "/upload-paper",method = RequestMethod.PUT)
    MessageResponse addPaperBody(@RequestBody PaperDto paperDto){
        try {
            Paper paper = converter.dtoToModel(paperDto);
            paperService.getPaperById(paper.getPid()).setContenturl(paper.getContenturl());
            return new MessageResponse("success");
        } catch (RuntimeException e){
            return new MessageResponse("failed");
        }
    }

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
