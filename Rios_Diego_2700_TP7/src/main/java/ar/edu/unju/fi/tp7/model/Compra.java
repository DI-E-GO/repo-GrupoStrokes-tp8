/**
 * 
 */
package ar.edu.unju.fi.tp7.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * @author Diego
 *
 */
@Entity
@Table(name = "COMPRAS")
@Component("unaCompra")
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@OneToMany(mappedBy = "compra")
	private List<Producto> productos = new ArrayList<Producto>();
	@Column(name = "cantidad")
	private int cantidad;
	@Column(name = "total")
	private double total;
	
	public Compra() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param id
	 * @param productos
	 * @param cantidad
	 * @param total
	 */
	public Compra(Long id, List<Producto> productos, int cantidad, double total) {
		super();
		this.id = id;
		this.productos = productos;
		this.cantidad = cantidad;
		this.total = total;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}
	
	
	/**
	 * @return the productos
	 */
	public List<Producto> getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", producto=" + productos + ", cantidad=" + cantidad + ", total=" + total + "]";
	}
	
}
