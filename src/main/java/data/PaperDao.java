package data;


import model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaperDao extends JpaRepository<Paper, Long> {
    List<Paper> findAllByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCase(String filterString, String filterString1);

    List<Paper> findAllByOrderByAuthor();

    List<Paper> findAllByOrderByTitle();

    List<Paper> findAllByOrderByPublicationDate();

    List<Paper> findAllByOrderByPageCount();
}
