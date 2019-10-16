package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entidades.Raca;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import repositorios.IRacaRepositorio;

@Controller
@RequestMapping("/raca")
public class RacaController {

    private final IRacaRepositorio repositorio;

    @Autowired
    public RacaController(IRacaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/lista")
    public ModelAndView listaRacas() {
        List<Raca> racas = repositorio.findAll();
        ModelAndView view = new ModelAndView("raca/lista-raca");
        view.addObject("racas", racas);
        return view;
    }

    @GetMapping("/salvar")
    public ModelAndView salvar(Raca raca) {
        ModelAndView view = new ModelAndView("raca/incluir-raca");
        view.addObject("raca", raca);
        return view;
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute("raca") @Valid Raca raca,
            BindingResult bindingResult, RedirectAttributes redirAttr) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("raca/incluir-raca");
        } else {
            repositorio.save(raca);
        }

        ModelAndView view = new ModelAndView("redirect:/raca/lista");
        redirAttr.addFlashAttribute("raca", raca);
        return view;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id) {
        ModelAndView view = new ModelAndView("raca/alterar-raca");
        view.addObject("raca", repositorio.findById(id));
        return view;
    }

    @PostMapping("/alterar")
    public ModelAndView alterar(@ModelAttribute("raca") @Valid Raca raca,
            BindingResult bindingResult, RedirectAttributes redirAttr) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("raca/alterar-raca");
        } else {
            repositorio.save(raca);
        }

        ModelAndView view = new ModelAndView("redirect:/raca/lista");
        redirAttr.addFlashAttribute("raca", raca);
        return view;
    }
}
