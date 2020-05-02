package ro.ubb.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.ubb.project.core.model.Paper;
import ro.ubb.project.core.service.PaperService;
import ro.ubb.project.web.converter.PaperConverter;
import ro.ubb.project.web.dto.PaperDto;
import ro.ubb.project.web.response.MessageResponse;
import ro.ubb.project.web.response.PapersResponse;

import java.io.File;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/paper")
public class PaperController {
    @Autowired
    private PaperService paperService;

    @Autowired
    private PaperConverter converter;


    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    Boolean update(@RequestBody PaperDto paper){
        //TODO: settle this implementation with the front-end crew
        this.paperService.updatePaper(converter.dtoToModel(paper));
        return true;
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

    @RequestMapping(value = "/getPapersForAuthor/{id}", method = RequestMethod.GET)
    PapersResponse getPapersForAuthor(@PathVariable Integer id){
         return new PapersResponse((ArrayList<PaperDto>) converter.convertModelsToDtos(paperService.getPapersOfAuthor(id)));
    }

    @RequestMapping(value = "/getPaper/{id}", method = RequestMethod.GET)
    PaperDto getPaperWithId(@PathVariable Integer id){
        return converter.modelToDto(paperService.getPaperById(id));
    }

    @RequestMapping(value = "/getAbstract/{id}", method = RequestMethod.GET)
    MultipartFile getAbstract(@PathVariable Integer id){
        Paper paper = paperService.getPaperById(id);
        String url = paper.getAbstracturl();
        File f = new File(url);
        return new MockMultipartFile(
                f.getName(),
                f.toString().getBytes()
        );
    }

    @RequestMapping(value = "/getContent/{id}", method = RequestMethod.GET)
    MultipartFile getContent(@PathVariable Integer id){
        Paper paper = paperService.getPaperById(id);
        String url = paper.getContenturl();
        File f = new File(url);
        return new MockMultipartFile(
                f.getName(),
                f.toString().getBytes()
        );
    }
}
