package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entidades.ItensPedido;
import entidades.Pedido;
import repositorios.IClienteRepositorio;
import repositorios.IItensPedidoRepositorio;
import repositorios.IPedidoRepositorio;
import repositorios.IProdutoRepositorio;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	private final IPedidoRepositorio pedidoRepositorio;
	private final IClienteRepositorio clienteRepositorio;
	private final IItensPedidoRepositorio itensPedidoRepositorio;
	private final IProdutoRepositorio produtoRepositorio;
	
	@Autowired
	public PedidoController(IPedidoRepositorio pedidoRepositorio, IClienteRepositorio clienteRepositorio,
			IItensPedidoRepositorio itensPedidoRepositorio, IProdutoRepositorio produtoRepositorio) {
		this.pedidoRepositorio = pedidoRepositorio;
		this.clienteRepositorio = clienteRepositorio;
		this.itensPedidoRepositorio = itensPedidoRepositorio;
		this.produtoRepositorio = produtoRepositorio;
	}
	
	@GetMapping("/lista")
	public ModelAndView lista() {
		ModelAndView view = new ModelAndView("pedidos/lista-pedidos");
		List<Pedido> pedidos = pedidoRepositorio.findAll();
		for(Pedido p : pedidos) {
			p.setCliente(clienteRepositorio.findById(p.getIdCliente()).get());
			p.setItensPedido(itensPedidoRepositorio.obterPorIdPedido(p.getId()));
		}
		
		view.addObject("pedidos", pedidos);
		return view;
	}
	
	@GetMapping("/detalhes/{id}")
	public ModelAndView detalhes(@PathVariable("id") int id) {
        ModelAndView view = new ModelAndView("pedidos/detalhes-pedido");
        Pedido p = pedidoRepositorio.findById(id).get();
        
        p.setItensPedido(itensPedidoRepositorio.obterPorIdPedido(p.getId()));
        for(ItensPedido i : p.getItensPedido())
        	i.setProduto(produtoRepositorio.findById(i.getIdProduto()).get());
        
        view.addObject("pedido", p);
        return view;
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterar(Pedido pedido) {
		Pedido p = pedidoRepositorio.obterPorId(pedido.getId());
		p.setStatus(pedido.getStatus());
		pedidoRepositorio.save(p);
		
		return new ModelAndView("redirect:/pedido/lista");
	}
}
