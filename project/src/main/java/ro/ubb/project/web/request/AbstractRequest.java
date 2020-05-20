package ro.ubb.project.web.request;

import lombok.Data;

@Data
public class AbstractRequest {
    private int authorId;
    private String paperName;
    private String keywords;
    private String filename;
}
