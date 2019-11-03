package controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import entidades.Login;
import entidades.Usuario;
import repositorios.IUsuarioRepositorio;

@Controller
public class LoginController {
	private final IUsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	public LoginController(IUsuarioRepositorio usuarioRepositorio){
		this.usuarioRepositorio = usuarioRepositorio;
	}
	
    @GetMapping("/login")
    public ModelAndView efetuaLogin(Login usuario){
        ModelAndView view = new ModelAndView("login");
        view.addObject("usuario", usuario);
        return view;
    }
    
    @PostMapping("/login")
    public ModelAndView efetuaLogin(@ModelAttribute("usuario") @Valid Login usuario, 
    		HttpSession session, BindingResult bindingResult) {
    	
    	if(usuario.getEmail().isEmpty())
    		bindingResult.reject("email", "O e-mail é obrigatório");
    	
    	if(usuario.getSenha().isEmpty())
    		bindingResult.reject("senha", "A senha é obrigatória");
    	
    	if (!usuario.getEmail().isEmpty()){
    		if(usuarioRepositorio.findByEmail(usuario.getEmail()) == null)
    			bindingResult.reject("email", "Não encontramos um cadastro com esse e-mail");
    	}
    	
        if(bindingResult.hasErrors()){
            return new ModelAndView("login");
        }
        
        Usuario usuarioLogado = autenticar(usuario.getEmail(), usuario.getSenha());
        ModelAndView view;
        if(usuarioLogado != null){
            session.setAttribute("usuarioLogado", usuarioLogado);
            view = new ModelAndView("redirect:/");
            view.addObject("usuarioLogado", usuarioLogado);
            return view;
        } else {
            view = new ModelAndView("login");
            bindingResult.reject("login", "Login inválido, verifique seu e-mail ou senha");
            return view;
        }
    }
    
	private final Usuario autenticar(String email, String senha){
		Usuario usuario = usuarioRepositorio.findByEmail(email);
    	if(usuario != null && usuario.validarSenha(senha))
    		return usuario;
    	return null;
    }
}
