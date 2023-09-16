import org.springframework.stereotype.Service
import java.util.*

@Service
class PaperService {

    private val papers: MutableList<Paper> = mutableListOf()

    fun addPaper(author: String, title: String, publicationDate: Date, pageCount: Int) {
        val paper = Paper(author, title, publicationDate, pageCount)
        papers.add(paper)
    }

    fun getAllPapers(): List<Paper> {
        return papers.toList()
    }

    fun deletePaper(index: Int) {
        if (index >= 0 && index < papers.size) {
            papers.removeAt(index)
        }
    }

    fun sortPapers(sortBy: Int) {
        when (sortBy) {
            1 -> papers.sortBy { it.author }
            2 -> papers.sortBy { it.title }
            3 -> papers.sortBy { it.publicationDate }
            4 -> papers.sortBy { it.pageCount }
            else -> {
            }
        }
    }

    fun filterPapers(filterString: String): List<Paper> {
        return papers.filter { it.containsFilterString(filterString) }
    }

    fun addReference(sourceIndex: Int, targetIndex: Int) {
        if (sourceIndex >= 0 && sourceIndex < papers.size && targetIndex >= 0 && targetIndex < papers.size) {
            val sourcePaper = papers[sourceIndex]
            val targetPaper = papers[targetIndex]
            sourcePaper.addReference(targetPaper)
        }
    }

    fun showStatistics(): Map<String, Int> {
        val totalPapers = papers.size
        val totalReferences = papers.sumBy { it.referenceCount }

        val statistics = mutableMapOf<String, Int>()
        statistics["totalPapers"] = totalPapers
        statistics["totalReferences"] = totalReferences

        return statistics
    }
}
