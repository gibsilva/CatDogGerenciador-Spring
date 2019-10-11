package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produto")
public class ProdutoController{

    @GetMapping("/salvar")
    public String produto (){
        return "incluir-produto";
    }

    @GetMapping("/alterar")
    public String produtoAlterar (){
        return "alterar-produto";
    }

    @GetMapping("/lista")
    public String produtoLista (){
        return "lista-produto";
    }

    @GetMapping("/detalhes")
    public String produtoDetalhes (){
        return "detalhes-produto";
    }
}