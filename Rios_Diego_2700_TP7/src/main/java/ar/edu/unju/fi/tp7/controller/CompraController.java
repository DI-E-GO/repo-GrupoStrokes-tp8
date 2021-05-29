package ar.edu.unju.fi.tp7.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tp7.model.Compra;
import ar.edu.unju.fi.tp7.model.Producto;
import ar.edu.unju.fi.tp7.service.ICompraService;
import ar.edu.unju.fi.tp7.service.IProductoService;

@Controller
public class CompraController {
	@Autowired
	@Qualifier("compraServiceMysql")
	private ICompraService compraService;
	
	@Autowired
	@Qualifier("productoServiceMysql")
	private IProductoService productoService;
	List<Producto> productos = new ArrayList<>();
	
	@GetMapping("/compra/nuevo")
	public ModelAndView getNuevaCompraPage(Model model) {
		this.productos=productoService.listaProductos();
		model.addAttribute(compraService.getCompra());
		ModelAndView modelo = new ModelAndView("nueva-compra");
		  modelo.addObject("productos", productos);
		 
		return modelo;
	}
	
	@PostMapping("/compra/guardar")
	//Codigo q vi en las clase practica de la profe
	public ModelAndView proccesFormCompra(@ModelAttribute("compra") Compra unaCompra) {
			ModelAndView modelView = new ModelAndView("listacompras");
			unaCompra.setProductos(productos);
			compraService.addCompra(unaCompra);
			modelView.addObject("compras", compraService.getCompras());
		return modelView;
	} 
	
	//Codigo que escribiste (diego) verificar porque se cambiaron algunas cosas
	/*public String proccesFormCompra(Model model, @ModelAttribute("compra") Compra unaCompra) {
		Producto aux = new Producto();
		for (Producto producto : productos) {
			if (((Producto) unaCompra.getProductos()).getCodigo()==producto.getCodigo()) {
				aux=producto;
			}
		}
		unaCompra.setProductos(aux);
		compraService.addCompra(unaCompra);
		/*
		 * model.addAttribute(compraService.getCompra());
		 * this.getNuevaCompraPage(model);
		 */
	/*	return "resultado-compra";
	} */
	

	@GetMapping("/compra/lista")
	public ModelAndView getListaComprasPage() {
		ModelAndView modelView = new ModelAndView("listacompras");
		modelView.addObject("compras", compraService.getCompras());
		modelView.addObject("compra", compraService.getCompra());
		return modelView;
	}
	
	@GetMapping("/compra/editar/{id}")
	public ModelAndView editarCompra(@PathVariable Long id, Model model) {
		this.productos=productoService.listaProductos();
		Optional<Compra> compra = compraService.getCompra(id);
		model.addAttribute("compra", compra);
		ModelAndView editCompra = new ModelAndView("nueva-compra");
		editCompra.addObject("productos",productos);
		return editCompra;
	}
	
	@GetMapping("/compra/borrar/{id}")
	public String borrarCompra(@PathVariable Long id, Model model) {
		compraService.eliminarCompra(id);
		return "redirect:/compra/lista";
	}
	
	@GetMapping("/compra/busqueda")
	public String getComprasFiltradas(@RequestParam(name="nombre") String nombre , @RequestParam(name="total") double total, Model model, @ModelAttribute(name="compra")Compra compra) {
		model.addAttribute("compra",compraService.getCompra());
		model.addAttribute("compras", compraService.buscarCompras(nombre, total));
		return "listacompras";
	}
	
	
}
