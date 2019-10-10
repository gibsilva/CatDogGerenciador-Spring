package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entidades.Usuario;;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import repositorios.IUsuarioRepositorio;;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final IUsuarioRepositorio repositorio;

    @Autowired
    public UsuarioController(IUsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/lista")
    public ModelAndView listaCategorias() {
        List<Usuario> usuarios = repositorio.findAll();
        ModelAndView view = new ModelAndView("usuario/lista-usuario");
        view.addObject("usuarios", usuarios);
        return view;
    }

    @GetMapping("/salvar")
    public ModelAndView salvar(Usuario usuario) {
        ModelAndView view = new ModelAndView("usuario/incluir-usuario");
        view.addObject("usuario", usuario);
        return view;
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute("usuario") @Valid Usuario usuario,
            BindingResult bindingResult, RedirectAttributes redirAttr) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("usuario/incluir-usuario");
        } else {
            repositorio.save(usuario);
        }

        ModelAndView view = new ModelAndView("redirect:/usuario/lista");
        redirAttr.addFlashAttribute("usuario", usuario);
        return view;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id) {
        ModelAndView view = new ModelAndView("usuario/alterar-usuario");
        view.addObject("usuario", repositorio.findById(id));
        return view;
    }

    @PostMapping("/alterar")
    public ModelAndView alterar(@ModelAttribute("usuario") @Valid Usuario usuario,
            BindingResult bindingResult, RedirectAttributes redirAttr) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("usuario/incluir-usuario");
        } else {
            repositorio.save(usuario);
        }

        ModelAndView view = new ModelAndView("redirect:/usuario/lista");
        redirAttr.addFlashAttribute("usuario", usuario);
        return view;
    }
}
