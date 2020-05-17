package ro.ubb.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.ubb.project.core.model.*;
import ro.ubb.project.core.service.*;
import ro.ubb.project.core.utils.FileHelper;
import ro.ubb.project.web.converter.PaperConverter;
import ro.ubb.project.web.dto.PaperDecisionDto;
import ro.ubb.project.web.dto.PaperDto;
import ro.ubb.project.web.request.AbstractRequest;
import ro.ubb.project.web.request.PaperRequest;
import ro.ubb.project.web.request.ReviewersRequest;
import ro.ubb.project.web.response.MessageResponse;
import ro.ubb.project.web.response.PapersResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PcMemberService pcMemberService;


    @RequestMapping(value = "/upload-abstract/meta", method = RequestMethod.POST)
    MessageResponse upload(@RequestBody AbstractRequest abstractRequest) {
        //update paper title and author
        Paper paper = new Paper();
        paper.setAid(abstractRequest.getAuthorId());
        paper.setTitle(abstractRequest.getPaperName());
        paper.setSession(1);
        paper.setAbstracturl("/src/main/resources/abstract/" + abstractRequest.getFilename());
        System.out.println(paper);
        this.paperService.addPaper(paper);

        //bind paper to new keywords
        return new MessageResponse("true");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    Boolean update(@RequestBody PaperRequest paperRequest) {
        //update paper title and author
        int paperId = paperRequest.getPaperId();
        Paper paper = this.paperService.getPaperById(paperId);
        paper.setAid(paperRequest.getAuthorId());
        paper.setTitle(paperRequest.getPaperTitle());

        this.paperService.updatePaper(paper);

        //bind paper to new keywords
        paperRequest.getKeywords().forEach(
                keyword -> {
                    int keywordId = keywordService.getIdByName(keyword);
                    paperSubjectService.addPaperSubject(PaperSubject.builder()
                            .kid(keywordId)
                            .pid(paperId)
                            .build());
                }
        );

        return true;
    }

    @RequestMapping(value = "/upload-content/content", method = RequestMethod.PUT)
    MessageResponse updateContent(@RequestParam("file") MultipartFile content) {
        String contentUrl = FileHelper.storeFile(content, "/src/main/resources/content");
        return new MessageResponse("true");
    }

    @RequestMapping(value = "/upload-abstract/abstract", method = RequestMethod.PUT)
    MessageResponse updateAbstract(@RequestParam("file") MultipartFile content) {
        String abstractUrl = FileHelper.storeFile(content, "/src/main/resources/abstract");
        return new MessageResponse("true");
    }

    @RequestMapping(value = "/review/submit/{pcid}/{pid}/{qualifier}", method = RequestMethod.POST)
    MessageResponse uploadReview(@RequestParam("file") MultipartFile content, @PathVariable java.lang.Integer pid, @PathVariable java.lang.Integer pcid, @PathVariable java.lang.Integer qualifier) {
        this.assignmentService.addAssignment(new Assignment(pcid, pid, qualifier,"/src/main/resources/review" + content.getName()));
        String reviewUrl = FileHelper.storeFile(content, "/src/main/resources/review");
        return new MessageResponse("true");
    }

    @RequestMapping(value = "/review/submit/{pcid}/{pid}", method = RequestMethod.POST)
    MessageResponse submitReview(@RequestParam("file") MultipartFile content, @PathVariable java.lang.Integer pid, @PathVariable java.lang.Integer pcid) {
        this.assignmentService.addAssignment(new Assignment(pcid, pid, -1,"/src/main/resources/review" + content.getName()));
        String reviewUrl = FileHelper.storeFile(content, "/src/main/resources/review");
        return new MessageResponse("true");
    }

    @RequestMapping(value = "/getAllPapers", method = RequestMethod.GET)
    PapersResponse getAllPapers() {
        return new PapersResponse((ArrayList<PaperDto>) converter.convertModelsToDtos(paperService.getAllPapers()));
    }

    /*
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
  */
    @RequestMapping(value = "/getPapersForAuthor/{id}", method = RequestMethod.GET)
    PapersResponse getPapersForAuthor(@PathVariable Integer id) {
        return new PapersResponse((ArrayList<PaperDto>) converter.convertModelsToDtos(paperService.getPapersOfAuthor(id)));
    }

    @RequestMapping(value = "/getPaper/{id}", method = RequestMethod.GET)
    PaperDto getPaperWithId(@PathVariable Integer id) {
        return converter.modelToDto(paperService.getPaperById(id));
    }

    @RequestMapping(value = "/decision", method = RequestMethod.PUT)
    MessageResponse setDecision(@RequestBody PaperDecisionDto paperDecisionDto) {
        try {
            paperService.getPaperById(paperDecisionDto.getPid())
                    .setAccepted(paperDecisionDto.getDecision());
            return new MessageResponse("success");
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new MessageResponse("failed");
        }
    }

    @RequestMapping(value = "/get-for-session/{id}", method = RequestMethod.GET)
    Collection<PaperDto> getPapersForSession(@PathVariable Integer id) {
        List<Paper> papers = paperService.getAllPapers()
                .stream()
                .filter(p -> p.getSession() == id)
                .collect(Collectors.toList());
        return converter.convertModelsToDtos(papers);
    }

    @RequestMapping(value = "/add-session", method = RequestMethod.PUT)
    MessageResponse addPaperSession(@RequestBody PaperDto paperDto) {
        try {
            Paper paper = converter.dtoToModel(paperDto);
            paperService.getPaperById(paper.getPid()).setSession(paper.getSession());
            return new MessageResponse("true");
        } catch (RuntimeException e) {
            return new MessageResponse("false");
        }
    }

    @RequestMapping(value = "/remove-session", method = RequestMethod.PUT)
    MessageResponse removePaperSession(@RequestBody PaperDto paperDto) {
        try {
            Paper paper = converter.dtoToModel(paperDto);
            paperService.getPaperById(paper.getPid()).setSession(0);
            return new MessageResponse("true");
        } catch (RuntimeException e) {
            return new MessageResponse("false");
        }
    }

    @RequestMapping(value = "/add-author", method = RequestMethod.PUT)
    MessageResponse addPaperAuthor(@RequestBody PaperDto paperDto) {
        try {
            Paper paper = converter.dtoToModel(paperDto);
            paperService.getPaperById(paper.getPid()).setAid(paper.getAid());
            return new MessageResponse("true");
        } catch (RuntimeException e) {
            return new MessageResponse("false");
        }
    }

    @RequestMapping(value = "/update-topic", method = RequestMethod.PUT)
    MessageResponse updatePaperTopic(@RequestBody PaperDto paperDto) {
        try {
            Paper paper = converter.dtoToModel(paperDto);
            paperService.getPaperById(paper.getPid()).setTopic(paper.getTopic());
            return new MessageResponse("true");
        } catch (RuntimeException e) {
            return new MessageResponse("false");
        }
    }

    @RequestMapping(value = "/get-topic/{id}", method = RequestMethod.GET)
    MessageResponse getTopic(@PathVariable Integer id) {
        return new MessageResponse(paperService.getPaperById(id).getTopic());
    }

    @RequestMapping(value = "/get-abstract/{id}", method = RequestMethod.GET)
    MessageResponse getAbstractUrl(@PathVariable Integer id) {
        return new MessageResponse(paperService.getPaperById(id).getAbstracturl());
    }

    @RequestMapping(value = "/get-content/{id}", method = RequestMethod.GET)
    MessageResponse getContentUrl(@PathVariable Integer id) {
        return new MessageResponse(paperService.getPaperById(id).getContenturl());
    }

    @RequestMapping(value = "/get-presentation/{id}", method = RequestMethod.GET)
    MessageResponse getPresentation(@PathVariable Integer id) {
        return new MessageResponse(paperService.getPaperById(id).getPresentationurl());
    }

    @RequestMapping(value = "/abstract/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource> getAbstract(@PathVariable Integer id) {
        Paper paper = paperService.getPaperById(id);
        String url = paper.getAbstracturl();
        Resource file = FileHelper.loadFileAsResource(url);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @RequestMapping(value = "/content/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource> getContent(@PathVariable Integer id) throws IOException {
        Paper paper = paperService.getPaperById(id);
        System.out.println(paper);
        String url = paper.getContenturl();
        Resource file = FileHelper.loadFileAsResource(url);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @RequestMapping(value = "/bid/paper={pid}-pc={pcid}", method = RequestMethod.POST)
    MessageResponse bid(@PathVariable Integer pid, @PathVariable Integer pcid) {
        biddingService.addBidding(Bidding.builder()
                .pcid(pcid)
                .pid(pid)
                .build());
        return new MessageResponse("success");
    }

    @RequestMapping(value = "/assignToReview", method = RequestMethod.POST)
    MessageResponse assignToReview(@RequestBody ReviewersRequest reviewersRequest) {
        int paperId = reviewersRequest.getPid();
        reviewersRequest.getReviewers().forEach(
                reviewer -> {
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
    ArrayList<Integer> getReviewersForPaper(@PathVariable Integer pid) {
        return (ArrayList<Integer>) assignmentService.getReviewersForPaperId(pid);
    }

    @RequestMapping(value = "/papersForReviewer/{pcid}", method = RequestMethod.GET)
    ArrayList<Integer> getPapersForReviewer(@PathVariable Integer pcid) {
        return (ArrayList<Integer>) assignmentService.getPapersForReviewer(pcid);
    }

    @RequestMapping(value = "/getAllExcept/{uid}", method = RequestMethod.GET)
    PapersResponse getAllExceptPcMember(@PathVariable Integer uid) {
        Optional<Author> optionalAuthor = this.authorService.getAllAuthors()
                .stream()
                .filter(a -> a.getUid() == uid)
                .findAny();
        if(optionalAuthor.isEmpty()) {
            return new PapersResponse();
        }
        else {
            return new PapersResponse((ArrayList<PaperDto>) converter.convertModelsToDtos(
                    paperService.getAllPapers()
                            .stream()
                            .filter(paper -> paper.getAid() == optionalAuthor.get().getAid())
                            .collect(Collectors.toList())
            ));
        }
    }

    @RequestMapping(value = "/bid/paper={pid}-uid={uid}", method = RequestMethod.POST)
    MessageResponse addBidding(@PathVariable Integer pid, @PathVariable Integer uid) {
        Optional<PcMember> optionalPcMember = this.pcMemberService.getAllPcMembers()
                .stream()
                .filter(pc -> pc.getUid() == uid)
                .findAny();
        if(optionalPcMember.isEmpty()) {
            return new MessageResponse("error");
        }
        else {
            biddingService.addBidding(Bidding.builder()
                    .pcid(optionalPcMember.get().getPcid())
                    .pid(pid)
                    .build());
            return new MessageResponse("success");
        }
    }

    @RequestMapping(value = "/accept/:{id}", method = RequestMethod.PUT)
    MessageResponse acceptPaper(@PathVariable int id) {
        try {
            paperService.getPaperById(id).setAccepted("accepted");
            return new MessageResponse("true");
        } catch (RuntimeException e) {
            return new MessageResponse("false");
        }
    }

    @RequestMapping(value = "/reject/:{id}", method = RequestMethod.PUT)
    MessageResponse rejectPaper(@PathVariable int id) {
        try {
            paperService.getPaperById(id).setAccepted("rejected");
            return new MessageResponse("true");
        } catch (RuntimeException e) {
            return new MessageResponse("false");
        }
    }

    @RequestMapping(value = "/reassign/paper={pid}", method = RequestMethod.PUT)
    MessageResponse reassignPaper(@PathVariable int pid, @RequestBody ReviewersRequest reviewersRequest) {
        try {
            Paper paper = paperService.getPaperById(pid);
            List<Assignment> assignments = this.assignmentService.getAllAssignments()
                    .stream()
                    .filter(a -> a.getPid() == pid)
                    .collect(Collectors.toList());
            assignments.forEach(a -> this.assignmentService.deleteAssignment(a));

            reviewersRequest.getReviewers().forEach(reviewer -> {
                        assignmentService.addAssignment(Assignment.builder()
                                .pid(pid)
                                .pcid(reviewer)
                                .qualifier(-1)
                                .build());});

            return new MessageResponse("success");
        } catch (RuntimeException e) {
            return new MessageResponse("error");
        }
    }
}
