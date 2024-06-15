package negocio;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

	private int idPedido;
	private String fecha;
	private double montoTotal; // precio*cantidad de la autoparte
	private int cliente;
	private List<Detalle> detalles; // denominacion
	
	public Pedido() {
		this.detalles = new ArrayList<>(); // Inicializar la lista de detalles
	    this.montoTotal = 0.00;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int id) {
		this.idPedido = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public int getCliente() {
		return cliente;
	}

	public void setCliente(int cliente) {
		this.cliente = cliente;
	}

	public List<Detalle> getDetalles() {
		return detalles;
	}

	public void agregarDetalle(int articulo, double precio, int cantidad) {
		Detalle detalle = new Detalle(articulo, precio, cantidad);
		this.detalles.add(detalle);
		this.montoTotal += detalle.getPrecio() * detalle.getCantidad(); // Actualiza el monto total del pedido
	}

	// Clase anidada Detalle
	public class Detalle {
		private int idArticulo;
		private double precio;
		private int cantidad;

		public Detalle( int articulo, double precio, int cantidad) {
			this.idArticulo = articulo;
			this.precio = precio;
			this.cantidad = cantidad;
		}

		// Getters y setters
	

		public int getArticulo() {
			return idArticulo;
		}

		public void setArticulo(int idArticulo) {
			this.idArticulo = idArticulo;
		}

		public double getPrecio() {
			return precio;
		}

		public void setPrecio(double precio) {
			this.precio = precio;
		}

		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
	}
}
