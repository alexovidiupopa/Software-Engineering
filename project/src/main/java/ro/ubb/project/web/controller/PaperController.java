package ro.ubb.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.ubb.project.core.model.Assignment;
import ro.ubb.project.core.model.Bidding;
import ro.ubb.project.core.model.Paper;
import ro.ubb.project.core.model.PaperSubject;
import ro.ubb.project.core.service.*;
import ro.ubb.project.core.utils.FileHelper;
import ro.ubb.project.web.converter.PaperConverter;
import ro.ubb.project.web.dto.PaperDto;
import ro.ubb.project.web.request.PaperRequest;
import ro.ubb.project.web.request.ReviewersRequest;
import ro.ubb.project.web.response.MessageResponse;
import ro.ubb.project.web.response.PapersResponse;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/paper")
public class PaperController {
    @Autowired
    private PaperService paperService;

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private PaperSubjectService paperSubjectService;

    @Autowired
    private PaperConverter converter;

    @Autowired
    private BiddingService biddingService;

    @Autowired
    private AssignmentService assignmentService;

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    Boolean update(@RequestBody PaperRequest paperRequest){
        //update paper title and author
        int paperId = paperRequest.getPaperId();
        Paper paper = this.paperService.getPaperById(paperId);
        paper.setAid(paperRequest.getAuthorId());
        paper.setTitle(paperRequest.getPaperTitle());
        this.paperService.updatePaper(paper);

        //bind paper to new keywords
        paperRequest.getKeywords().forEach(
                keyword->{
                    int keywordId = keywordService.getIdByName(keyword);
                    paperSubjectService.addPaperSubject(PaperSubject.builder()
                            .kid(keywordId)
                            .pid(paperId)
                            .build());
                }
        );

        return true;
    }

    @RequestMapping(value = "/update/content", method = RequestMethod.PUT)
    Boolean updateContent(@RequestBody MultipartFile content){
        String contentUrl = FileHelper.storeFile(content, "/src/main/resources/content");
        return true;
    }

    @RequestMapping(value = "/update/abstract", method = RequestMethod.PUT)
    Boolean updateAbstract(@RequestBody MultipartFile content){
        String abstractUrl = FileHelper.storeFile(content, "/src/main/resources/abstract");
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
    ResponseEntity<Resource> getAbstract(@PathVariable Integer id){
        Paper paper = paperService.getPaperById(id);
        String url = paper.getAbstracturl();
        Resource file = FileHelper.loadFileAsResource(url);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @RequestMapping(value = "/getContent/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource> getContent(@PathVariable Integer id){
        Paper paper = paperService.getPaperById(id);
        String url = paper.getContenturl();
        Resource file = FileHelper.loadFileAsResource(url);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @RequestMapping(value = "/bid/paper={pid}-pc={pcid}", method = RequestMethod.POST)
    MessageResponse bid(@PathVariable Integer pid, @PathVariable Integer pcid){
        biddingService.addBidding(Bidding.builder()
                .pcid(pcid)
                .pid(pid)
                .build());
        return new MessageResponse("success");
    }

    @RequestMapping(value = "/assignToReview",method = RequestMethod.POST)
    MessageResponse assignToReview(@RequestBody ReviewersRequest reviewersRequest){
        int paperId = reviewersRequest.getPid();
        reviewersRequest.getReviewers().forEach(
                reviewer->{
                    assignmentService.addAssignment(Assignment.builder()
                            .pid(paperId)
                            .pcid(reviewer)
                            .qualifier(-1)
                            .build());
                }
        );
        return new MessageResponse("success");
    }

    @RequestMapping(value = "/reviewersForPaper/{pid}", method = RequestMethod.GET)
    ArrayList<Integer> getReviewersForPaper(@PathVariable Integer pid){
        return (ArrayList<Integer>) assignmentService.getReviewersForPaperId(pid);
    }

    @RequestMapping(value="/papersForReviewer/{pcid}",method = RequestMethod.GET)
    ArrayList<Integer> getPapersForReviewer(@PathVariable Integer pcid){
        return (ArrayList<Integer>) assignmentService.getPapersForReviewer(pcid);
    }
}
