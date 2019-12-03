package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entidades.Pedido;
import repositorios.IClienteRepositorio;
import repositorios.IItensPedidoRepositorio;
import repositorios.IPedidoRepositorio;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	private final IPedidoRepositorio pedidoRepositorio;
	private final IClienteRepositorio clienteRepositorio;
	private final IItensPedidoRepositorio itensPedidoRepositorio;
	
	@Autowired
	public PedidoController(IPedidoRepositorio pedidoRepositorio, IClienteRepositorio clienteRepositorio,
			IItensPedidoRepositorio itensPedidoRepositorio) {
		this.pedidoRepositorio = pedidoRepositorio;
		this.clienteRepositorio = clienteRepositorio;
		this.itensPedidoRepositorio = itensPedidoRepositorio;
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
}
