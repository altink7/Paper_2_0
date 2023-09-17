package mapper;

import dto.PaperDTO;
import model.Paper;

import java.util.ArrayList;
import java.util.List;

public class PaperMapper {

    public static PaperDTO toDTO(Paper paper) {
        PaperDTO dto = new PaperDTO();
        dto.setAuthor(paper.getAuthor());
        dto.setTitle(paper.getTitle());
        dto.setPublicationDate(paper.getPublicationDate());
        dto.setPageCount(paper.getPageCount());

        List<PaperDTO> referenceDTOs = new ArrayList<>();
        for (Paper reference : paper.getReferences()) {
            PaperDTO referenceDTO = toDTO(reference);
            referenceDTOs.add(referenceDTO);
        }
        dto.setReferences(referenceDTOs);

        return dto;
    }

    public static List<PaperDTO> toDTOList(List<Paper> papers) {
        List<PaperDTO> paperDTOs = new ArrayList<>();
        for (Paper paper : papers) {
            PaperDTO dto = toDTO(paper);
            paperDTOs.add(dto);
        }
        return paperDTOs;
    }

    public static Paper toModel(PaperDTO dto) {
        Paper paper = new Paper();
        paper.setAuthor(dto.getAuthor());
        paper.setTitle(dto.getTitle());
        paper.setPublicationDate(dto.getPublicationDate());
        paper.setPageCount(dto.getPageCount());


        List<Paper> references = new ArrayList<>();
        for (PaperDTO referenceDTO : dto.getReferences()) {
            Paper reference = toModel(referenceDTO);
            references.add(reference);
        }
        paper.setReferences(references);

        return paper;
    }

    public static List<Paper> toModelList(List<PaperDTO> dtos) {
        List<Paper> papers = new ArrayList<>();
        for (PaperDTO dto : dtos) {
            Paper paper = toModel(dto);
            papers.add(paper);
        }
        return papers;
    }
}