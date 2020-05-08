package ro.ubb.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.ubb.project.core.model.Paper;
import ro.ubb.project.core.service.PaperService;
import ro.ubb.project.web.converter.PaperConverter;
import ro.ubb.project.web.dto.PaperDecisionDto;
import ro.ubb.project.web.dto.PaperDto;
import ro.ubb.project.web.response.MessageResponse;
import ro.ubb.project.web.response.PapersResponse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    @RequestMapping(value = "/decision", method = RequestMethod.PUT)
    MessageResponse setDecision(@RequestBody PaperDecisionDto paperDecisionDto){
        try {
            paperService.getPaperById(paperDecisionDto.getPid())
                    .setAccepted(paperDecisionDto.getDecision());
            return new MessageResponse("success");
        } catch (RuntimeException e){
            e.printStackTrace();
            return new MessageResponse("failed");
        }
    }

    @RequestMapping(value = "/get-for-session/{id}", method = RequestMethod.GET)
    Collection<PaperDto> getPapersForSession(@PathVariable Integer id){
        List<Paper> papers = paperService.getAllPapers()
                .stream()
                .filter(p -> p.getSession() == id)
                .collect(Collectors.toList());
        return converter.convertModelsToDtos(papers);
    }

    @RequestMapping(value = "/add-session",method = RequestMethod.PUT)
    MessageResponse addPaperSession(@RequestBody PaperDto paperDto){
        try {
            Paper paper = converter.dtoToModel(paperDto);
            paperService.getPaperById(paper.getPid()).setSession(paper.getSession());
            return new MessageResponse("success");
        } catch (RuntimeException e){
            return new MessageResponse("failed");
        }
    }

    @RequestMapping(value = "/remove-session",method = RequestMethod.PUT)
    MessageResponse removePaperSession(@RequestBody PaperDto paperDto){
        try {
            Paper paper = converter.dtoToModel(paperDto);
            paperService.getPaperById(paper.getPid()).setSession(0);
            return new MessageResponse("success");
        } catch (RuntimeException e){
            return new MessageResponse("failed");
        }
    }

    @RequestMapping(value = "/add-author",method = RequestMethod.PUT)
    MessageResponse addPaperAuthor(@RequestBody PaperDto paperDto){
        try {
            Paper paper = converter.dtoToModel(paperDto);
            paperService.getPaperById(paper.getPid()).setAid(paper.getAid());
            return new MessageResponse("success");
        } catch (RuntimeException e){
            return new MessageResponse("failed");
        }
    }

    @RequestMapping(value = "/update-topic",method = RequestMethod.PUT)
    MessageResponse updatePaperTopic(@RequestBody PaperDto paperDto){
        try {
            Paper paper = converter.dtoToModel(paperDto);
            paperService.getPaperById(paper.getPid()).setTopic(paper.getTopic());
            return new MessageResponse("success");
        } catch (RuntimeException e){
            return new MessageResponse("failed");
        }
    }

    @RequestMapping(value = "/get-topic/{id}", method = RequestMethod.GET)
    MessageResponse getTopic(@PathVariable Integer id){
        return new MessageResponse(paperService.getPaperById(id).getTopic());
    }

    @RequestMapping(value = "/get-abstract/{id}", method = RequestMethod.GET)
    MessageResponse getAbstract(@PathVariable Integer id){
        return new MessageResponse(paperService.getPaperById(id).getAbstracturl());
    }

    @RequestMapping(value = "/get-content/{id}", method = RequestMethod.GET)
    MessageResponse getContent(@PathVariable Integer id){
        return new MessageResponse(paperService.getPaperById(id).getContenturl());
    }

    @RequestMapping(value = "/get-presentation/{id}", method = RequestMethod.GET)
    MessageResponse getPresentation(@PathVariable Integer id){
        return new MessageResponse(paperService.getPaperById(id).getPresentationurl());
    }
}
