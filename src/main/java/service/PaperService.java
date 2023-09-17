package service;

import data.PaperDao;
import dto.PaperDTO;
import mapper.PaperMapper;
import model.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PaperService {

    private final PaperDao paperDao;

    @Autowired
    public PaperService(PaperDao paperDao) {
        this.paperDao = paperDao;
    }

    public void createPaper(Paper paper) {
        paperDao.save(paper);
    }

    public Paper getPaperById(Long id) {
        return paperDao.findById(id).orElse(null);
    }

    public void addPaper(String author, String title, Date publicationDate, int pageCount) {
        Paper paper = new Paper(author, title, publicationDate, pageCount, Collections.emptyList());
        paperDao.save(paper);
    }

    public List<PaperDTO> getAllPapers() {
        return PaperMapper.toDTOList(paperDao.findAll());
    }

    public void deletePaper(Long id) {
        paperDao.deleteById(id);
    }

    public List<Paper> sortPapers(int sortBy) {
        switch (sortBy) {
            case 1:
                return paperDao.findAllByOrderByAuthor();
            case 2:
                return paperDao.findAllByOrderByTitle();
            case 3:
                return paperDao.findAllByOrderByPublicationDate();
            case 4:
                return paperDao.findAllByOrderByPageCount();
            default:
                return Collections.emptyList();
        }
    }

    public List<Paper> filterPapers(String filterString) {
        return paperDao.findAllByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCase(filterString, filterString);
    }

    public void addReference(Long sourceId, Long targetId) {
        Optional<Paper> sourcePaper = paperDao.findById(sourceId);
        Optional<Paper> targetPaper = paperDao.findById(targetId);

        if (sourcePaper.isPresent() && targetPaper.isPresent()) {
            Paper source = sourcePaper.get();
            Paper target = targetPaper.get();
            source.addReference(target);
            paperDao.save(source);
        }
    }

    public Map<String, Integer> showStatistics() {
        List<Paper> papers = paperDao.findAll();
        int totalPapers = papers.size();
        int totalReferences = papers.stream().mapToInt(Paper::getReferenceCount).sum();

        Map<String, Integer> statistics = new HashMap<>();
        statistics.put("totalPapers", totalPapers);
        statistics.put("totalReferences", totalReferences);

        return statistics;
    }
}

