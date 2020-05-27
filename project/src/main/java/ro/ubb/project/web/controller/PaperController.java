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
import ro.ubb.project.web.converter.AssignmentConverter;
import ro.ubb.project.web.converter.PaperConverter;
import ro.ubb.project.web.dto.AssignmentDto;
import ro.ubb.project.web.dto.PaperDecisionDto;
import ro.ubb.project.web.dto.PaperDto;
import ro.ubb.project.web.dto.ReviewerDto;
import ro.ubb.project.web.request.AbstractRequest;
import ro.ubb.project.web.request.BiddingRequest;
import ro.ubb.project.web.request.PaperRequest;
import ro.ubb.project.web.request.ReviewersRequest;
import ro.ubb.project.web.response.MessageResponse;
import ro.ubb.project.web.response.PapersResponse;
import ro.ubb.project.web.response.ReviewersResponse;

import java.util.*;
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

    @Autowired
    private PersonService personService;


    @RequestMapping(value = "/upload-abstract/meta", method = RequestMethod.POST)
    MessageResponse upload(@RequestBody AbstractRequest abstractRequest) {
        //update paper title and author
        Paper paper = new Paper();
        int aid = authorService.getAuthorByUid(abstractRequest.getAuthorId()).getAid();
        paper.setAid(aid);
        paper.setTitle(abstractRequest.getPaperName());
        paper.setSession(1);
        paper.setAbstractUrl("/src/main/resources/abstract/" + abstractRequest.getFilename());
        System.out.println(paper);

        this.paperService.addPaper(paper);
        List<String> keywords = Arrays.asList(abstractRequest.getKeywords().split(" "));
        keywords.forEach(
                keyword -> {
                    try{
                        keywordService.addKeyword(Keyword.builder()
                                .name(keyword)
                                .build());
                    }
                    catch (RuntimeException ignored){

                    }
                }
        );

        keywords.forEach(
                keyword -> {
                    int keywordId = keywordService.getIdByName(keyword);
                    paperSubjectService.addPaperSubject(PaperSubject.builder()
                            .kid(keywordId)
                            .pid(paper.getPid())
                            .build());
                }
        );

        //bind paper to new keywords
        return new MessageResponse("true");
    }

    @RequestMapping(value = "/update/meta", method = RequestMethod.PUT)
    MessageResponse update(@RequestBody PaperRequest paperRequest) {
        //update paper title and author
        int paperId = paperRequest.getPaperId();
        Paper paper = this.paperService.getPaperById(paperId);

        int aid = authorService.getAuthorByUid(paperRequest.getAuthorId()).getAid();

        paper.setAid(aid);
        paper.setTitle(paperRequest.getPaperName());
        paper.setContentUrl("/src/main/resources/content/" + paperRequest.getFileName());

        this.paperService.updatePaper(paper);

        List<String> keywords = Arrays.asList(paperRequest.getKeywords().split(" "));
        keywords.forEach(
             keyword -> {
                 try{
                     keywordService.addKeyword(Keyword.builder()
                             .name(keyword)
                             .build());
                 }
                 catch (RuntimeException ignored){

                 }
             }
        );


        //bind paper to new keywords
        keywords.forEach(
                keyword -> {
                    int keywordId = keywordService.getIdByName(keyword);
                    paperSubjectService.addPaperSubject(PaperSubject.builder()
                            .kid(keywordId)
                            .pid(paper.getPid())
                            .build());
                }
        );

        return new MessageResponse("true");
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

    @RequestMapping(value = "/review/submit/{pcid}/{pid}/{qualifier}/{filename}", method = RequestMethod.POST)
    MessageResponse uploadReview(@RequestParam("file") MultipartFile content, @PathVariable java.lang.Integer pid, @PathVariable java.lang.Integer pcid, @PathVariable java.lang.Integer qualifier, @PathVariable String filename) {
        this.assignmentService.addAssignment(new Assignment(pcid, pid, qualifier, "/src/main/resources/review/" + filename));
        String reviewUrl = FileHelper.storeFile(content, "/src/main/resources/review");
        return new MessageResponse("true");
    }

    @RequestMapping(value = "/review/submit/{pcid}/{pid}", method = RequestMethod.POST)
    MessageResponse submitReview(@RequestParam("file") MultipartFile content, @PathVariable java.lang.Integer pid, @PathVariable java.lang.Integer pcid) {
        this.assignmentService.addAssignment(new Assignment(pcid, pid, -1, "/src/main/resources/review" + content.getName()));
        String reviewUrl = FileHelper.storeFile(content, "/src/main/resources/review");
        return new MessageResponse("true");
    }

    @RequestMapping(value = "/getAllPapers", method = RequestMethod.GET)
    PapersResponse getAllPapers() {
        return new PapersResponse((ArrayList<PaperDto>) converter.convertModelsToDtos(paperService.getAllPapers()));
    }


    @RequestMapping(value = "/getPapersForAuthor/{id}", method = RequestMethod.GET)
    PapersResponse getPapersForAuthor(@PathVariable Integer id) {
        int aid = authorService.getAuthorByUid(id).getAid();
        return new PapersResponse((ArrayList<PaperDto>) converter.convertModelsToDtos(paperService.getPapersOfAuthor(aid)));
    }

    @RequestMapping(value = "/getPaper/{id}", method = RequestMethod.GET)
    PaperDto getPaperWithId(@PathVariable Integer id) {
        Paper paper = paperService.getPaperById(id);
        PaperDto dto = converter.modelToDto(paper);
        List<Integer> keywordIds = paperSubjectService.getAllPaperSubjects()
                .stream()
                .filter(p -> p.getPid() == dto.getPid())
                .map(p -> p.getKid())
                .collect(Collectors.toList());

        String keyword = keywordIds.stream()
                .map(kid -> keywordService.getKeywordById(kid).getName())
                .reduce((a, b) -> a + " " + b)
                .orElse("NO_KEYWORDS");

        dto.setTopic(
                keyword
        );
        return dto;
    }

    @RequestMapping(value = "/decision", method = RequestMethod.PUT)
    MessageResponse setDecision(@RequestBody PaperDecisionDto paperDecisionDto) {
        try {
            paperService.getPaperById(paperDecisionDto.getPid())
                    .setAccepted(paperDecisionDto.getDecision());
            return new MessageResponse("true");
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

    @RequestMapping(value = "/get-abstract/{id}", method = RequestMethod.GET)
    MessageResponse getAbstractUrl(@PathVariable Integer id) {
        return new MessageResponse(paperService.getPaperById(id).getAbstractUrl());
    }

    @RequestMapping(value = "/get-content/{id}", method = RequestMethod.GET)
    MessageResponse getContentUrl(@PathVariable Integer id) {
        return new MessageResponse(paperService.getPaperById(id).getContentUrl());
    }

    @RequestMapping(value = "/has-content/{id}", method = RequestMethod.GET)
    MessageResponse hasContent(@PathVariable Integer id) {
        if (paperService.getPaperById(id).getContentUrl() == null)
            return new MessageResponse("false");
        return new MessageResponse("true");
    }

    @RequestMapping(value = "/abstract/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource> getAbstract(@PathVariable Integer id) {
        Paper paper = paperService.getPaperById(id);
        String url = paper.getAbstractUrl();
        Resource file = FileHelper.loadFileAsResource(url);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @RequestMapping(value = "/content/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource> getContent(@PathVariable Integer id) {
        Paper paper = paperService.getPaperById(id);
        System.out.println(paper);
        String url = paper.getContentUrl();
        Resource file = FileHelper.loadFileAsResource(url);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @RequestMapping(value = "/bid", method = RequestMethod.POST)
    MessageResponse bid(@RequestBody BiddingRequest biddingRequest) {
        int userId = biddingRequest.getUserId();
        int pcid = pcMemberService.getPcIdByUid(userId);
        for (int pid : biddingRequest.getAccepted()) {
            try {
                biddingService.addBidding(Bidding.builder()
                        .pcid(pcid)
                        .pid(pid)
                        .build());
            } catch (RuntimeException e) {
                return new MessageResponse("false");
            }
        }
        return new MessageResponse("true");
    }

    @RequestMapping(value = "/assignToReview", method = RequestMethod.POST)
    MessageResponse assignToReview(@RequestBody ReviewersRequest reviewersRequest) {
        int paperId = reviewersRequest.getPid();
        reviewersRequest.getReviewers().forEach(
                reviewer -> assignmentService.addAssignment(Assignment.builder()
                        .pid(paperId)
                        .pcid(pcMemberService.getPcIdByUid(reviewer))
                        .qualifier(-1)
                        .reviewUrl(null)
                        .build())
        );
        return new MessageResponse("true");
    }

    @RequestMapping(value = "/reviewersForPaper/{pid}", method = RequestMethod.GET)
    ReviewersResponse getReviewersForPaper(@PathVariable Integer pid) {
        List<Integer> pcIds = biddingService.getReviewersForPaperId(pid);
        return new ReviewersResponse(
                (ArrayList<ReviewerDto>)
                        pcIds
                                .stream()
                                .map(pcId -> {
                                    int uid = pcMemberService.getPcMemberById(pcId).get().getUid();
                                    Person person = personService.getPersonById(uid);
                                    return new ReviewerDto(
                                            uid,
                                            person.getFirstname(),
                                            person.getLastname()
                                    );
                                })
                                .collect(Collectors.toList())
        );
    }

    @RequestMapping(value = "/papersForReviewer/{pcid}", method = RequestMethod.GET)
    PapersResponse getPapersForReviewer(@PathVariable Integer pcid) {
        List<Integer> paperIds = assignmentService.getPapersForReviewer(pcid);
        return new PapersResponse(
                (ArrayList<PaperDto>) converter.convertModelsToDtos(
                        paperIds
                                .stream()
                                .map(pid -> paperService.getPaperById(pid))
                                .collect(Collectors.toList()))
        );
    }

    @RequestMapping(value = "/getAllExcept/{uid}", method = RequestMethod.GET)
    PapersResponse getAllExceptPcMember(@PathVariable Integer uid) {
        Optional<Author> optionalAuthor = this.authorService.getAllAuthors()
                .stream()
                .filter(a -> a.getUid() == uid)
                .findAny();
        if (optionalAuthor.isEmpty()) {
            return new PapersResponse(
                    (ArrayList<PaperDto>) converter.convertModelsToDtos(
                            paperService.getAllPapers()
                    )
            );
        } else {
            return new PapersResponse((ArrayList<PaperDto>) converter.convertModelsToDtos(
                    paperService.getAllPapers()
                            .stream()
                            .filter(paper -> paper.getAid() == optionalAuthor.get().getAid())
                            .collect(Collectors.toList())
            ));
        }
    }


    @RequestMapping(value = "/accept/{id}", method = RequestMethod.PUT)
    MessageResponse acceptPaper(@PathVariable int id) {
        try {
            Paper paper = paperService.getPaperById(id);
            paper.setAccepted("accepted");
            paperService.updatePaper(paper);
            return new MessageResponse("true");
        } catch (RuntimeException e) {
            return new MessageResponse("false");
        }
    }

    @RequestMapping(value = "/reject/{id}", method = RequestMethod.PUT)
    MessageResponse rejectPaper(@PathVariable int id) {
        try {
            Paper paper = paperService.getPaperById(id);
            paper.setAccepted("rejected");
            paperService.updatePaper(paper);
            return new MessageResponse("true");
        } catch (RuntimeException e) {
            return new MessageResponse("false");
        }
    }


    @RequestMapping(value = "/delete/{pid}", method = RequestMethod.DELETE)
    MessageResponse deletePaper(@PathVariable Integer pid){
        try{
            paperService.deletePaper(paperService.getPaperById(pid));
            return new MessageResponse("true");
        }
        catch (RuntimeException e){
            return new MessageResponse("false");
        }
    }

    @RequestMapping(value = "/reassign/paper={pid}", method = RequestMethod.PUT)
    MessageResponse reassignPaper(@PathVariable int pid, @RequestBody ReviewersRequest reviewersRequest) {
        try {
            List<Assignment> assignments = this.assignmentService.getAllAssignments()
                    .stream()
                    .filter(a -> a.getPid() == pid)
                    .collect(Collectors.toList());
            assignments.forEach(a -> this.assignmentService.deleteAssignment(a));

            reviewersRequest.getReviewers().forEach(reviewer -> assignmentService.addAssignment(Assignment.builder()
                    .pid(pid)
                    .pcid(reviewer)
                    .qualifier(-1)
                    .build()));

            return new MessageResponse("true");
        } catch (RuntimeException e) {
            return new MessageResponse("false");
        }
    }

    @RequestMapping(value = "/reviews", method = RequestMethod.GET)
    ArrayList<AssignmentDto> getAllReviews() {
        AssignmentConverter converter = new AssignmentConverter();
        return (ArrayList<AssignmentDto>) converter.convertModelsToDtos(assignmentService.getAllAssignments());
    }

    @RequestMapping(value="/getReviewsForAuthor/{id}", method = RequestMethod.GET)
    PapersResponse getReviewsForAuthor(@PathVariable Integer id){
        List<Paper> papers = new ArrayList<>();

        return new PapersResponse((ArrayList<PaperDto>) converter.convertModelsToDtos(papers));
    }

    @RequestMapping(value="/getPapersWithQualifiers",method = RequestMethod.GET)
    PapersResponse getPapersWithQualifiers(){
        List<Paper> papers = new ArrayList<>();

        paperService.getAllPapers().forEach(
                paper->{
                    double average = assignmentService.getAllAssignments()
                            .stream()
                            .filter(assg->assg.getPid()==paper.getPid())
                            .map(Assignment::getQualifier)
                            .mapToInt(Integer::intValue)
                            .average().orElse(0);
                    if (average>2 && average<4)
                        paper.setAccepted("conflict");
                    if(average>=4)
                        paper.setAccepted("rejected");
                    if(average<=2)
                        paper.setAccepted("accepted");
                    papers.add(paper);
                    paperService.updatePaper(paper);
                }
        );

        return new PapersResponse(
                (ArrayList<PaperDto>) converter.convertModelsToDtos(papers)
        );
    }

    @RequestMapping(value = "/getUnscheduledPapers",method = RequestMethod.GET)
    PapersResponse getUnscheduledPapers(){
        return new PapersResponse(
                (ArrayList<PaperDto>) converter.convertModelsToDtos(
                        paperService.getAllPapers()
                        .stream()
                        .filter(paper-> paper.getAccepted().equals("accepted") && paper.getSession()==-1)
                        .collect(Collectors.toList())
                )
        );
    }

    @RequestMapping(value = "/addPaperToSession/{pid}/{sid}", method = RequestMethod.POST)
    MessageResponse addPaperToSession(@PathVariable Integer pid, @PathVariable Integer sid){
        try {
            Paper paper = paperService.getPaperById(pid);
            paper.setSession(sid);
            paperService.updatePaper(paper);
            return new MessageResponse("true");
        } catch (RuntimeException e) {
            return new MessageResponse("false");
        }
    }

    @RequestMapping(value = "/deletePaperFromSession/{pid}", method = RequestMethod.POST)
    MessageResponse deletePaperFromSession(@PathVariable Integer pid){
        try {
            Paper paper = paperService.getPaperById(pid);
            paper.setSession(-1);
            paperService.updatePaper(paper);
            return new MessageResponse("true");
        } catch (RuntimeException e) {
            return new MessageResponse("false");
        }
    }
}
