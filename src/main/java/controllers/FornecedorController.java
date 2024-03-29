package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entidades.Fornecedor;
import helpers.Utils;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import repositorios.IFornecedorRepositorio;
import services.FornecedorService;

@Controller
@RequestMapping("/fornecedor")
public class FornecedorController {

    private final IFornecedorRepositorio repositorio;
    FornecedorService service = new FornecedorService();
    
    @Autowired
    public FornecedorController(IFornecedorRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/lista")
    public ModelAndView listaFornecedores() {
        List<Fornecedor> fornecedores = repositorio.findAll();
        ModelAndView view = new ModelAndView("fornecedor/lista-fornecedor");
        view.addObject("fornecedores", fornecedores);
        return view;
    }

    @GetMapping("/salvar")
    public ModelAndView salvar(Fornecedor fornecedor) {
        ModelAndView view = new ModelAndView("fornecedor/incluir-fornecedor");
        view.addObject("fornecedor", fornecedor);
        return view;
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute("fornecedor") @Valid Fornecedor fornecedor,
            BindingResult bindingResult, RedirectAttributes redirAttr) {
        fornecedor.setAtivo(true);

        //retirar pontos e traços do CNPJ
        fornecedor.setCnpj(Utils.removePontosBarraStr(fornecedor.getCnpj()));
        
        if (!service.validarCnpj(fornecedor.getCnpj())) {
            bindingResult.reject("cnpj", "CNPJ inválido");
        }

        if (bindingResult.hasErrors()) {
            return new ModelAndView("fornecedor/incluir-fornecedor");
        } else {
            repositorio.save(fornecedor);
        }

        ModelAndView view = new ModelAndView("redirect:/fornecedor/lista");
        redirAttr.addFlashAttribute("fornecedor", fornecedor);
        return view;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Integer id) {
        ModelAndView view = new ModelAndView("fornecedor/alterar-fornecedor");
        Optional<Fornecedor> fornecedor = repositorio.findById(id);
        view.addObject("fornecedor", fornecedor);
        return view;
    }

    @PostMapping("/alterar")
    public ModelAndView alterar(@ModelAttribute("fornecedor") @Valid Fornecedor fornecedor,
            BindingResult bindingResult, RedirectAttributes redirAttr) {

        //retirar pontos e traços do CNPJ
        fornecedor.setCnpj(Utils.removePontosBarraStr(fornecedor.getCnpj()));
        
        if (bindingResult.hasErrors()) {
            return new ModelAndView("fornecedor/alterar-fornecedor");
        } else {
            repositorio.save(fornecedor);
        }

        ModelAndView view = new ModelAndView("redirect:/fornecedor/lista");
        redirAttr.addFlashAttribute("fornecedor", fornecedor);
        return view;
    }
}
