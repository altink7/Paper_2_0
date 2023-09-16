import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.util.*

@Controller
@RequestMapping("/papers")
class PaperController @Autowired constructor(private val paperService: PaperService) {

    @GetMapping("/")
    fun listPapers(model: Model): String {
        val papers = paperService.getAllPapers()
        model.addAttribute("papers", papers)
        return "papers/list"
    }

    @GetMapping("/new")
    fun createPaperForm(model: Model): String {
        model.addAttribute("paper", PaperDTO())
        return "papers/edit"
    }

    @PostMapping("/save")
    fun createPaper(@ModelAttribute("paper") paperDTO: PaperDTO): String {
        val paper = paperDTO.toEntity()
        paperService.createPaper(paper)
        return "redirect:/papers/"
    }

    @GetMapping("/{id}/edit")
    fun editPaperForm(@PathVariable id: Long, model: Model): String {
        val paper = paperService.getPaperById(id)
        if (paper != null) {
            model.addAttribute("paper", paper.toDTO())
            return "papers/edit"
        }
        return "redirect:/papers/"
    }

    @GetMapping("/{id}/delete")
    fun deletePaper(@PathVariable id: Long): String {
        paperService.deletePaper(id)
        return "redirect:/papers/"
    }
}
