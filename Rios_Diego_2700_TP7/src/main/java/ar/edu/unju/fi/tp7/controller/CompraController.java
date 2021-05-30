package ar.edu.unju.fi.tp7.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String getNuevaCompraPage(Model model) {
		this.productos=productoService.listaProductos();
		model.addAttribute(compraService.getCompra());
		model.addAttribute("productos", productos);
		return "nueva-compra";
	}
	
	@PostMapping("/compra/guardar")
	public ModelAndView proccesFormCompra(Model model, @Valid @ModelAttribute("compra") Compra unaCompra, BindingResult resultadoValidacion) {
		ModelAndView modelView;
		if (resultadoValidacion.hasErrors()) {
			model.addAttribute("productos", productoService.listaProductos());
			modelView = new ModelAndView("nueva-compra");
			return modelView;
		} else {
			modelView = new ModelAndView("resultado-compra");
			Producto aux = new Producto();
			for (Producto producto : productos) {
				if (unaCompra.getProducto().getCodigo()==producto.getCodigo()) {
					aux=producto;
				}
			}
			unaCompra.setProducto(aux);
			compraService.addCompra(unaCompra);
			return modelView;
		}
	}
	
	@GetMapping("/compra/lista")
	public ModelAndView getListaComprasPage() {
		ModelAndView modelView = new ModelAndView("listacompras");
		modelView.addObject("compras", compraService.getCompras());
		
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
}
