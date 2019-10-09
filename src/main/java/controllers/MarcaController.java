package controllers;

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
import repositorios.IMarcaRepositorio;

@Controller
@RequestMapping("/marca")
public class MarcaController{
    private final IMarcaRepositorio repositorio;

    @Autowired
    public MarcaController(IMarcaRepositorio repositorio){
        this.repositorio = repositorio;
    }

    @GetMapping("/lista")
    public ModelAndView listaMarcas(){
        List<Marca> marcas = repositorio.findAll();
        ModelAndView view = new ModelAndView("marca/lista-marca");
        view.addObject("marca", marcas);
        return view;
    }

    
}