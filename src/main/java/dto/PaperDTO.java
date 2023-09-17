package dto;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PaperDTO {
    private String author;
    private String title;
    private Date publicationDate;
    private int pageCount;
    private List<PaperDTO> references;

}
