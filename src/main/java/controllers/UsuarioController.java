package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entidades.Usuario;
import helpers.Utils;

import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import repositorios.IUsuarioRepositorio;
import services.UsuarioService;

;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final IUsuarioRepositorio repositorio;
    UsuarioService service;

    @Autowired
    public UsuarioController(IUsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
        service = new UsuarioService();
    }

    @GetMapping("/lista")
    public ModelAndView listaUsuarios() {
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
        usuario.setAtivo(true);

        //retirar pontos e traços do CPF
        usuario.setCpf(Utils.removePontosBarraStr(usuario.getCpf()));

        if (usuario.getSenha().length() < 6 || usuario.getSenha().length() > 12) {
            bindingResult.reject("senha", "A senha deve ter o tamanho entre 6 e 12 caracteres");
        }

        if (!service.validarCpf(usuario.getCpf())) {
            bindingResult.reject("cpf", "CPF inválido");
        }

        if (repositorio.findByEmail(usuario.getEmail()) != null) {
            bindingResult.reject("email", "E-mail já cadastrado");
        }

        if (repositorio.findByCpf(usuario.getCpf()) != null) {
            bindingResult.reject("cpf", "CPF já cadastrado");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("usuario/incluir-usuario");
        } else {
            usuario.setSenhaEncriptada(usuario.getSenha());
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

        //retirar pontos e traços do CPF
        usuario.setCpf(Utils.removePontosBarraStr(usuario.getCpf()));

        if (bindingResult.hasErrors()) {
            return new ModelAndView("usuario/alterar-usuario");
        } else {
            repositorio.save(usuario);
        }

        ModelAndView view = new ModelAndView("redirect:/usuario/lista");
        redirAttr.addFlashAttribute("usuario", usuario);
        return view;
    }
}
