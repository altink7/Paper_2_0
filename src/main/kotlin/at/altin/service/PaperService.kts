import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PaperService @Autowired constructor(private val paperDao: PaperDao) {

    fun addPaper(author: String, title: String, publicationDate: Date, pageCount: Int) {
        val paper = Paper(author, title, publicationDate, pageCount)
        paperDao.save(paper)
    }

    fun getAllPapers(): List<Paper> {
        return paperDao.findAll()
    }

    fun deletePaper(id: Long) {
        paperDao.deleteById(id)
    }

    fun sortPapers(sortBy: Int): List<Paper> {
        return when (sortBy) {
            1 -> paperDao.findAllByOrderByAuthor()
            2 -> paperDao.findAllByOrderByTitle()
            3 -> paperDao.findAllByOrderByPublicationDate()
            4 -> paperDao.findAllByOrderByPageCount()
            else -> emptyList()
        }
    }

    fun filterPapers(filterString: String): List<Paper> {
        return paperDao.findAllByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCase(filterString, filterString)
    }

    fun addReference(sourceId: Long, targetId: Long) {
        val sourcePaper = paperDao.findById(sourceId)
        val targetPaper = paperDao.findById(targetId)

        if (sourcePaper.isPresent && targetPaper.isPresent) {
            val source = sourcePaper.get()
            val target = targetPaper.get()
            source.addReference(target)
            paperDao.save(source)
        }
    }

    fun showStatistics(): Map<String, Int> {
        val totalPapers = paperDao.findAll().size
        val totalReferences = paperDao.findAll().sumBy { it.getReferenceCount() }

        val statistics = mutableMapOf<String, Int>()
        statistics["totalPapers"] = totalPapers
        statistics["totalReferences"] = totalReferences

        return statistics
    }
}
