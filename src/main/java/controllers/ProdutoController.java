package controllers;

import entidades.Produto;
import entidades.enums.EPorteAnimal;
import entidades.enums.ETipoAnimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import repositorios.ICategoriaRepositorio;
import repositorios.IMarcaRepositorio;
import repositorios.IProdutoRepositorio;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final IProdutoRepositorio produtoRepositorio;
    private final ICategoriaRepositorio categoriaRepositorio;
    private final IMarcaRepositorio marcaRepositorio;

    @Autowired
    public ProdutoController(IProdutoRepositorio produtoRepositorio,
            ICategoriaRepositorio categoriaRepositorio,
            IMarcaRepositorio marcaRepositorio) {
        this.produtoRepositorio = produtoRepositorio;
        this.categoriaRepositorio = categoriaRepositorio;
        this.marcaRepositorio = marcaRepositorio;
    }

    @GetMapping("/salvar")
    public ModelAndView produto(Produto produto) {
        ModelAndView view = new ModelAndView("produto/incluir-produto");
        view.addObject("categorias", categoriaRepositorio.findAll());
        view.addObject("marcas", marcaRepositorio.findAll());
        view.addObject("tipoAnimais", ETipoAnimal.values());
        view.addObject("porteAnimais", EPorteAnimal.values());
        view.addObject("produto", produto);
        return view;
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView produtoAlterar(@PathVariable("id") Integer id) {
        ModelAndView view = new ModelAndView("produto/alterar-produto");
        view.addObject("categorias", categoriaRepositorio.findAll());
        view.addObject("marcas", marcaRepositorio.findAll());
        view.addObject("produto", produtoRepositorio.findById(id));
        return view;
    }

    @GetMapping("/lista")
    public ModelAndView listaProdutos() {
        List<Produto> produtos = produtoRepositorio.findAll();
        ModelAndView view = new ModelAndView("produto/lista-produto");
        view.addObject("produtos", produtos);
        return view;
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView produtoDetalhes(@PathVariable("id") Integer id) {
        ModelAndView view = new ModelAndView("produto/detalhes-produto");
        view.addObject("produto", produtoRepositorio.findById(id));
        return view;
    }
}
