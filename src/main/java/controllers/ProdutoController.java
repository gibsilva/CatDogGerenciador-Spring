package controllers;

import entidades.Imagem;
import entidades.Produto;
import entidades.enums.EPorteAnimal;
import entidades.enums.ETipoAnimal;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import repositorios.ICategoriaRepositorio;
import repositorios.IImagemRepositorio;
import repositorios.IMarcaRepositorio;
import repositorios.IProdutoRepositorio;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final IProdutoRepositorio produtoRepositorio;
    private final ICategoriaRepositorio categoriaRepositorio;
    private final IMarcaRepositorio marcaRepositorio;
    private final IImagemRepositorio imagemRepositorio;

    @Autowired
    public ProdutoController(IProdutoRepositorio produtoRepositorio,
            ICategoriaRepositorio categoriaRepositorio,
            IMarcaRepositorio marcaRepositorio,
            IImagemRepositorio imagemRepositorio) {
        this.produtoRepositorio = produtoRepositorio;
        this.categoriaRepositorio = categoriaRepositorio;
        this.marcaRepositorio = marcaRepositorio;
        this.imagemRepositorio = imagemRepositorio;
    }

    @GetMapping("/salvar")
    public ModelAndView salvar(Produto produto) {
        ModelAndView view = new ModelAndView("produto/incluir-produto");
        view.addObject("categorias", categoriaRepositorio.findAll());
        view.addObject("marcas", marcaRepositorio.findAll());
        view.addObject("tipoAnimais", ETipoAnimal.values());
        view.addObject("porteAnimais", EPorteAnimal.values());
        view.addObject("produto", produto);
        return view;
    }
    
    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute("produto") @Valid Produto produto,
    		BindingResult bindingResult, RedirectAttributes redirAttr, @RequestParam("imagem") MultipartFile[] files) {
    	produto.setAtivo(true);
    	
    	if (files.length <= 0)
    		bindingResult.reject("imagens", "Por favor insira pelo menos uma imagem");
    	
    	File dir = new File(obterCaminho());
    	List<Imagem> imagens = new ArrayList<Imagem>();
    	for (int i = 0; i < files.length; i++) {
    		MultipartFile file = files[i];
            try {
                byte[] bytes = file.getBytes();

                if (!dir.exists())
                    dir.mkdirs();

                File uploadFile = new File(dir.getAbsolutePath()
                        + File.separator + file.getOriginalFilename());
                BufferedOutputStream outputStream = new BufferedOutputStream(
                        new FileOutputStream(uploadFile));
                outputStream.write(bytes);
                outputStream.close();
                Imagem imagem = new Imagem(0, file.getOriginalFilename(), uploadFile.getAbsolutePath(), file.getContentType(), 0);
                imagens.add(imagem);
            } catch (Exception e) {
                bindingResult.reject("imagem", "Ocorreu um erro ao salvar a imagem");
            }
    	}
    	
        if(bindingResult.hasErrors()){
        	ModelAndView viewErro = new ModelAndView("produto/incluir-produto");
        	viewErro.addObject("produto", produto);
        	//viewErro.addObject("categorias", categoriaRepositorio.findAll());
        	//viewErro.addObject("marcas", marcaRepositorio.findAll());
        	//viewErro.addObject("tipoAnimais", ETipoAnimal.values());
        	//viewErro.addObject("porteAnimais", EPorteAnimal.values());      	
        	return viewErro;
        } else{
        	produtoRepositorio.save(produto);
        	int idProduto = produtoRepositorio.findByLastId();
        	for(Imagem i : imagens){
        		i.setIdProduto(idProduto);
        		imagemRepositorio.save(i);
        	}
        }
        
        ModelAndView view = new ModelAndView("redirect:/produto/lista");
        redirAttr.addFlashAttribute("produto", produto);
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
    public ModelAndView detalhes(@PathVariable("id") Integer id) {
        ModelAndView view = new ModelAndView("produto/detalhes-produto");
        view.addObject("produto", produtoRepositorio.findById(id));
        return view;
    }
    
    public String obterCaminho() {
    	String os = System.getProperty("os.name");
    	if(os.toUpperCase().contains("windows".toUpperCase()))
    		return "C://uploads//";
    	else
    		return "uploads//";
    }
}
