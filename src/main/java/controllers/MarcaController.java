package controllers;

import entidades.Fornecedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entidades.Marca;

import java.util.List;

import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import repositorios.IFornecedorRepositorio;
import repositorios.IMarcaRepositorio;

@Controller
@RequestMapping("/marca")
public class MarcaController {

    private final IMarcaRepositorio repositorio;
    private final IFornecedorRepositorio fornecedorRepositorio;

    @Autowired
    public MarcaController(IMarcaRepositorio repositorio, IFornecedorRepositorio fornecedorRepositorio) {
        this.repositorio = repositorio;
        this.fornecedorRepositorio = fornecedorRepositorio;
    }

    @GetMapping("/lista")
    public ModelAndView listaMarcas() {
        List<Marca> marcas = repositorio.findAll();
        ModelAndView view = new ModelAndView("marca/lista-marca");
        view.addObject("marcas", marcas);
        return view;
    }

    @GetMapping("/salvar")
    public ModelAndView salvar(Marca marca) {
        ModelAndView view = new ModelAndView("marca/incluir-marca");
        List<Fornecedor> fornecedores = fornecedorRepositorio.findAll();
        view.addObject("fornecedores", fornecedores);
        view.addObject("marca", marca);
        return view;
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute("marca") @Valid Marca marca,
            BindingResult bindingResult, RedirectAttributes redirAttr) {
        marca.setAtivo(true);
        if (bindingResult.hasErrors()) {
            return new ModelAndView("marca/incluir-marca");
        } else {
            repositorio.save(marca);
        }

        ModelAndView view = new ModelAndView("redirect:/marca/lista");
        redirAttr.addFlashAttribute("marca", marca);
        return view;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id) {
        ModelAndView view = new ModelAndView("marca/alterar-marca");
        List<Fornecedor> fornecedores = fornecedorRepositorio.findAll();
        view.addObject("fornecedores", fornecedores);
        view.addObject("marca", repositorio.findById(id));
        return view;
    }

    @PostMapping("/alterar")
    public ModelAndView alterar(@ModelAttribute("marca") @Valid Marca marca,
            BindingResult bindingResult, RedirectAttributes redirAttr) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("marca/incluir-marca");
        } else {
            repositorio.save(marca);
        }

        ModelAndView view = new ModelAndView("redirect:/marca/lista");
        redirAttr.addFlashAttribute("marca", marca);
        return view;
    }
}
