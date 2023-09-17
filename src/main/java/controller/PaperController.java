package controller;

import dto.PaperDTO;
import mapper.PaperMapper;
import model.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.PaperService;

import java.util.*;

@Controller
@RequestMapping("/papers")
public class PaperController {

    private final PaperService paperService;

    @Autowired
    public PaperController(PaperService paperService) {
        this.paperService = paperService;
    }

    @GetMapping("/")
    public String listPapers(Model model) {
        List<PaperDTO> papers = paperService.getAllPapers();
        model.addAttribute("papers", papers);
        return "papers/list";
    }

    @GetMapping("/new")
    public String createPaperForm(Model model) {
        model.addAttribute("paper", new PaperDTO());
        return "papers/new";
    }

    @PostMapping("/save")
    public String createPaper(@ModelAttribute("paper") PaperDTO paperDTO) {
        Paper paper = PaperMapper.toModel(paperDTO);
        paperService.createPaper(paper);
        return "redirect:/papers/";
    }

    @GetMapping("/{id}/edit")
    public String editPaperForm(@PathVariable Long id, Model model) {
        Paper paper = paperService.getPaperById(id);
        if (paper != null) {
            model.addAttribute("paper", PaperMapper.toDTO(paper));
            return "papers/edit";
        }
        return "redirect:/papers/";
    }

    @GetMapping("/{id}/addReference")
    public String addReferenceForm(@PathVariable Long id, Model model) {
        Paper paper = paperService.getPaperById(id);
        if (paper != null) {
            model.addAttribute("paper", PaperMapper.toDTO(paper));
            model.addAttribute("papers", paperService.getAllPapers());
            return "papers/addReference";
        }
        return "redirect:/papers/";
    }

    @GetMapping("/{id}/delete")
    public String deletePaper(@PathVariable Long id) {
        paperService.deletePaper(id);
        return "redirect:/papers/";
    }

    @GetMapping("/sort")
    public String sortPapers(@RequestParam int sortBy, Model model) {
        List<Paper> papers = paperService.sortPapers(sortBy);
        model.addAttribute("papers", papers);
        return "papers/list";
    }

    @GetMapping("/filter")
    public String filterPapers(@RequestParam String filterString, Model model) {
        List<Paper> papers = paperService.filterPapers(filterString);
        model.addAttribute("papers", papers);
        return "papers/list";
    }

    @GetMapping("/statistics")
    public String showStatistics(Model model) {
        Map<String, Integer> statistics = paperService.showStatistics();
        model.addAttribute("statistics", statistics);
        return "papers/statistics";
    }
}
