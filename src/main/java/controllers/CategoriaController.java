package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entidades.Categoria;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import repositorios.ICategoriaRepositorio;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    private final ICategoriaRepositorio repositorio;

    @Autowired
    public CategoriaController(ICategoriaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/lista")
    public ModelAndView listaCategorias() {
        List<Categoria> categorias = repositorio.findAll();
        ModelAndView view = new ModelAndView("categoria/lista-categoria");
        view.addObject("categorias", categorias);
        return view;
    }

    @GetMapping("/salvar")
    public ModelAndView salvar(Categoria categoria) {
        ModelAndView view = new ModelAndView("categoria/incluir-categoria");
        view.addObject("categoria", categoria);
        return view;
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute("categoria") @Valid Categoria categoria,
            BindingResult bindingResult, RedirectAttributes redirAttr) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("categoria/incluir-categoria");
        } else {
            repositorio.save(categoria);
        }

        ModelAndView view = new ModelAndView("redirect:/categoria/lista");
        redirAttr.addFlashAttribute("categoria", categoria);
        return view;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id) {
        ModelAndView view = new ModelAndView("categoria/alterar-categoria");
        view.addObject("categoria", repositorio.findById(id));
        return view;
    }

    @PostMapping("/alterar")
    public ModelAndView alterar(@ModelAttribute("categoria") @Valid Categoria categoria,
            BindingResult bindingResult, RedirectAttributes redirAttr) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("categoria/incluir-categoria");
        } else {
            repositorio.save(categoria);
        }

        ModelAndView view = new ModelAndView("redirect:/categoria/lista");
        redirAttr.addFlashAttribute("categoria", categoria);
        return view;
    }
}
