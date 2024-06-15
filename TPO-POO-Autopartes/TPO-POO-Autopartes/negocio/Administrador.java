package negocio;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Administrador {

	private int codigo;
	private String nombre;
	private int contrasenia;
	private String correo;
	private String perfil;

	private ArrayList<Autoparte> catalogo;
	private ArrayList<Pedido> pedidos;
	private ArrayList<Venta> cantVentas;
	private ArrayList<Cliente> clientes;

	public Administrador() {
		catalogo = new ArrayList<Autoparte>(); // lista que contiene todas las autopartes
		pedidos = new ArrayList<Pedido>(); // lista que contiene todos los pedidos
		cantVentas = new ArrayList<Venta>(); // lista que contiene todas las ventas --> guarda numero factura
		clientes = new ArrayList<Cliente>(); // lista que contiene a todos los clientes registrados
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(int contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	// Valida los datos introducidos por el Administrador
	public boolean IniciarSesion(String correo, String contra) {
		if (correo.equals("admin") && contra.equals("admin")) {
			return true;
		} else {
			return false;
		}
	}

	// Finaliza la sesión actual
	public boolean CerrarSesion() {
		return true;
	}

	// Muestra por pantalla el catalogo con todas las autopartes
	public ArrayList<Autoparte> ListarCatalogo() {
        if (!catalogoVacio()) {
            return new ArrayList<>(catalogo);
        } else {
            return new ArrayList<>(); // Devuelve una lista vacía si el catálogo está vacío
        }
    }


	// Utilicé el scanner acá también ya que no puede recibir otro dato debido que
	// hay opciones
	// que son Strings y otras Int, por ende, recibe la opción elegida por el
	// usuario y se recorre
	// el catalogo y se modifica la autoparte de acuerdo a lo seleccionado
	public void ModificarCatalogo(int codigo, int opcion, Object nuevoValor) {
	    for (int i = 0; i < catalogo.size(); i++) {
	        if (catalogo.get(i).getCodigo() == codigo) {
	            switch (opcion) {
	                case 1:
	                    if (nuevoValor instanceof Integer) {
	                        int nuevoCodigo = (int) nuevoValor;
	                        catalogo.get(i).setCodigo(nuevoCodigo);
	                    }
	                    break;
	                case 2:
	                    if (nuevoValor instanceof String) {
	                        String nuevaDenominacion = (String) nuevoValor;
	                        catalogo.get(i).setDenominacion(nuevaDenominacion);
	                    }
	                    break;
	                case 3:
	                    if (nuevoValor instanceof String) {
	                        String nuevaDescripcion = (String) nuevoValor;
	                        catalogo.get(i).setDescripcion(nuevaDescripcion);
	                    }
	                    break;
	                case 4:
	                    if (nuevoValor instanceof String) {
	                        String nuevaCategoria = (String) nuevoValor;
	                        catalogo.get(i).setCategoria(nuevaCategoria);
	                    }
	                    break;
	                case 5:
	                    if (nuevoValor instanceof String) {
	                        String nuevaMarca = (String) nuevoValor;
	                        catalogo.get(i).setMarca(nuevaMarca);
	                    }
	                    break;
	                case 6:
	                    if (nuevoValor instanceof String) {
	                        String nuevoModelo = (String) nuevoValor;
	                        catalogo.get(i).setModelo(nuevoModelo);
	                    }
	                    break;
	                case 7:
	                    if (nuevoValor instanceof Double) {
	                        double nuevoPrecio = (double) nuevoValor;
	                        catalogo.get(i).setPrecio(nuevoPrecio);
	                    }
	                    break;
	                case 8:
	                    if (nuevoValor instanceof Integer) {
	                        int nuevoStockMinimo = (int) nuevoValor;
	                        catalogo.get(i).setStockMinimo(nuevoStockMinimo);
	                    }
	                    break;
	                case 9:
	                    if (nuevoValor instanceof String) {
	                        String nuevoEnlace = (String) nuevoValor;
	                        catalogo.get(i).setEnlace(nuevoEnlace);
	                    }
	                    break;
	                case 10:
	                    if (nuevoValor instanceof Integer) {
	                        int nuevoStock = (int) nuevoValor;
	                        catalogo.get(i).setCantStock(nuevoStock);
	                    }
	                    break;
	                default:
	                    System.out.println("Opción inválida.");
	                    break;
	            }
	            return; // Salir del método después de realizar la modificación
	        }
	    }
	}
	// carga la autoparte al catalogo
	public void CargarAutoparte(Autoparte a) {
		catalogo.add(a);
	}

	// elimina la autoparte mediante el código de la misma
	public void EliminarDelCatalogo(int codigo) {
		if (catalogoVacio() == false) {
			for (int i = 0; i < catalogo.size(); i++) {
				if (catalogo.get(i).getCodigo() == codigo) {
					catalogo.remove(i);
				}
			}
			System.out.println("Autoparte eliminada exitosamente!");
		} else {
			return;
		}
	}

	public int getClienteEnPedidoById(int pedidoId) {
		int idCliente = -1;
		// Comprobar si el catálogo no está vacío
		if (!pedidos.isEmpty()) {
			// Recorrer el catálogo
			for (int i = 0; i < pedidos.size(); i++) {
				// Comprobar si el código de la autoparte coincide con el ID buscado
				if (pedidos.get(i).getIdPedido() == pedidoId) {
					System.out.println("ID Cliente: " + pedidoId + "| Nombre: " + clientes.get(i).getNombre());
					// Devolver el precio de la autoparte encontrada
					idCliente = pedidos.get(i).getCliente();
					return idCliente;
				}
			}
		}

		// Devolver el valor predeterminado si no se encuentra la autoparte
		return idCliente;
	}

	public Cliente getClienteById(int id) {
		// Inicializar con un valor predeterminado
		Cliente cliente = null;

		// Comprobar si el catálogo no está vacío
		if (!clientes.isEmpty()) {
			// Recorrer el catálogo
			for (int i = 0; i < clientes.size(); i++) {
				// Comprobar si el código de la autoparte coincide con el ID buscado
				if (clientes.get(i).getCodigo() == id) {
					// Devolver el precio de la autoparte encontrada
					return clientes.get(i);
				}
			}
		}

		// Devolver el valor predeterminado si no se encuentra la autoparte
		return cliente;
	}

	public double getPrecioAutoparte(int id) {
		// Inicializar con un valor predeterminado
		double precio = 0.0;

		// Comprobar si el catálogo no está vacío
		if (!catalogoVacio()) {
			// Recorrer el catálogo
			for (int i = 0; i < catalogo.size(); i++) {
				// Comprobar si el código de la autoparte coincide con el ID buscado
				if (catalogo.get(i).getCodigo() == id) {
					System.out.println("Precio unidad: $" + catalogo.get(i).getPrecio());
					// Devolver el precio de la autoparte encontrada
					return catalogo.get(i).getPrecio();
				}
			}
		}

		// Devolver el valor predeterminado si no se encuentra la autoparte
		return precio;
	}

	// modifica el stock de una autoparte mediante su codigo y se lo guarda en el
	// catalogo
	public void ModificarStock(int codigo, int nuevoStock, int opcion) {
		if (catalogoVacio() == false) {
			if (existeAutoparte(codigo) == true) {
				for (int i = 0; i < catalogo.size(); i++) {
					if (catalogo.get(i).getCodigo() == codigo) {

						int stockAntiguo = catalogo.get(i).getCantStock();
						int stockMinimo = catalogo.get(i).getStockMinimo();
						int stockFinal = 0;

						if (opcion == 1) { // sumar el stock
							stockFinal = stockAntiguo + nuevoStock;
						} else if (opcion == 2) { // modificar stock
							stockFinal = nuevoStock;
						} else { // restar, en caso de reservas
							stockFinal = stockAntiguo - nuevoStock;
						}
						catalogo.get(i).setCantStock(stockFinal);
						StockMinimo(stockFinal, stockMinimo);
						return;
					}
				}
			} else {
				return;
			}
		} else {
			return;
		}
	}

	// Agrega un cliente nuevo al sistema
	public boolean agregarCliente(Cliente c) {
        // Verificar si el cliente ya existe por su ID
        if (existeCliente(c.getCodigo())) {
            return false; // Cliente no agregado, ya existe por su ID
        }

        // Si no existe, agregar el cliente a la lista
        clientes.add(c);
        return true; // Cliente agregado exitosamente
    }

	// Verifica si un cliente ya se encuentra en el sistema
	public boolean existeCliente(int id) {
		if (!clientes.isEmpty()) {
			for (int i = 0; i < clientes.size(); i++) {
				if (clientes.get(i).getCodigo() == id) {
					System.out.print("El cliente con ID " + id + " ya está registrado\n");
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

	public boolean idAutoparteRepetido(int id) {
		if (!catalogo.isEmpty()) {
			for (int i = 0; i < catalogo.size(); i++) {
				if (catalogo.get(i).getCodigo() == id) {
					System.out.print("La autoparte con ID: " + id + " ya está registrado");
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

	// Carga un pedido al contenedor de pedidos
	public boolean CargarPedido(Pedido p) {
		if (!catalogoVacio()) {
			// Aquí es donde revisamos el precio sin cambiarlo
			for (Pedido.Detalle detalle : p.getDetalles()) {
				int id = detalle.getArticulo();
				if (id >= 0 && id < catalogo.size()) {
					Autoparte a = catalogo.get(id);
					if (a != null) {
					} else {
						System.out.println("Autoparte con ID " + id + " no encontrada en el catálogo.");
					}
				}
			}
			pedidos.add(p);
			System.out.println("Pedido agregado exitosamente!");
			
			
		}return true;
	}

	// Verifica que exista el pedido, lo cancela en base al numero de pedido y
	// devuelve el stock
	public void CancelarPedido(int codigo) {
		if (existePedido(codigo) == true) {
			for (int i = 0; i < pedidos.size(); i++) {
				if (pedidos.get(i).getIdPedido() == codigo) {
					pedidos.remove(i);
					System.out.println("Pedido cancelado exitosamente!");
					return;
				}
			}
		}
	}

	public void listarPedidos() {
		if (pedidos.isEmpty()) {
			System.out.println("No hay pedidos registrados en el sistema.");
		} else {
			System.out.println("Listado de Pedidos:");
			for (Pedido pedido : pedidos) {
				System.out.println("ID Pedido: " + pedido.getIdPedido());
				System.out.println("Fecha: " + pedido.getFecha());
				System.out.println("Cliente: " + pedido.getCliente());
				System.out.println("Monto Total: " + pedido.getMontoTotal());
				System.out.println("Detalles:");

				// Listar detalles de cada pedido
				for (Pedido.Detalle detalle : pedido.getDetalles()) {
					System.out.println("  ID Artículo: " + detalle.getArticulo());
					System.out.println("  Precio: " + detalle.getPrecio());
					System.out.println("  Cantidad: " + detalle.getCantidad());
					System.out.println("-----");
				}

				System.out.println("--------------------");
			}
		}
	}

	public void listarVentas() {
		if (cantVentas.isEmpty()) {
			System.out.println("No hay ventas registradas en el sistema.");
		} else {
			System.out.println("Listado de Ventas:");
			for (Venta venta : cantVentas) {
				System.out.println("ID Venta: " + venta.getCodigo());
				System.out.println("Nombre Cliente: " + venta.getCliente().getNombre());
				System.out.println("Monto total Venta: " + venta.getDetalleVenta().getMontoTotal());
				System.out.println("--------------------");
			}
		}
	}

	// Realiza una venta de un autoparte CON pedido para un cliente
	public void RegistrarVentaConPedido(int numPedido, Venta v) {
		Pedido p = null;
		if (existePedido(numPedido) == true) {
			for (int i = 0; i < pedidos.size(); i++) {
				if (pedidos.get(i).getIdPedido() == numPedido) {
					p = pedidos.get(i);
				}
			}
			// se añade el detalle del producto a la venta
			v.setDetalleVenta(p);

			// se añaden los datos faltantes a la venta
			v.setProvincia("Buenos Aires"); // autodefino xq son de la sucursal
			v.setLocalidad("Monserrat"); // autodefino xq son de la sucursal
			v.setTelefono(1122334455); // autodefino xq son de la sucursal

			// se añade la venta al registro
			cantVentas.add(v);

			System.out.println("Operación exitosa!");
		}
	}

	// Realiza una venta de un autoparte para un cliente SIN un pedido previo
	public void RegistrarVentaSinPedido(Pedido detalleVenta, Venta v) {
		// buscamos la autoparte
		if (!catalogoVacio()) {
			// Aquí es donde revisamos el precio sin cambiarlo
			for (Pedido.Detalle detalle : detalleVenta.getDetalles()) {
				int id = detalle.getArticulo();
				if (id >= 0 && id < catalogo.size()) {
					Autoparte a = catalogo.get(id);
					if (a != null) {
					} else {
						System.out.println("Autoparte con ID " + id + " no encontrada en el catálogo.");
					}
				}
			}
			pedidos.add(detalleVenta);
		} else {
			System.out.println("El catálogo está vacío.");
		}

		// se añaden los datos faltantes a la venta
		v.setDetalleVenta(detalleVenta);
		v.setProvincia("Buenos Aires"); // autodefino xq son de la sucursal
		v.setLocalidad("Monserrat"); // autodefino xq son de la sucursal
		v.setTelefono(1122334455); // autodefino xq son de la sucursal

		// se añade la venta al registro
		cantVentas.add(v);

		System.out.println("Operación exitosa!");
	}

	// Verifica la disponibilidad y la cantidad de stock de un autoparte y devuelve
	// el stock disponible
	public int DisponibilidadStock(int codigo) {
        for (Autoparte autoparte : catalogo) {
            if (autoparte.getCodigo() == codigo) {
                int stock = autoparte.getCantStock();
                if (stock == 1) {
                    System.out.println("La autoparte " + codigo + " dispone de un stock de " + stock + " unidad");
                } else if (stock > 1) {
                    System.out.println("La autoparte " + codigo + " dispone de un stock de " + stock + " unidades");
                } else {
                    System.out.println("La autoparte " + codigo + " no dispone de stock disponible");
                }
                return stock; // Retornar el stock encontrado
            }
        }
        System.out.println("La autoparte con código " + codigo + " no existe en el catálogo.");
        return 0; // Retornar 0 si no se encuentra la autoparte
    }

	// Genera la factura de la venta con o sin pedido relacionada con un cliente
	public void GeneradorDeFacturas(Venta venta) {

		Cliente cliente = venta.getCliente();
		Pedido pedido = venta.getDetalleVenta();
		double totalVenta = venta.getMontoFinal();
		String medioPago = venta.getMedioDePago();
		int cantCuotas = venta.getCantCuotas();

		System.out.println("**********************************************************************************");
		System.out.println("                         FACTURA                          ");
		System.out.println("**********************************************************************************");
		System.out.println("Fecha de venta: " + pedido.getFecha());
		System.out.println("\nDATOS DEL NEGOCIO                       	DATOS DEL CLIENTE");
		System.out.println("Nombre: TUTTA LA MACCHINA               	ID: " + cliente.getCodigo());
		System.out.println("Dirección: Avenida Corrientes 123       	Nombre: " + cliente.getNombre());
		System.out.println("Localidad: Monserrat                    	Dirección: " + cliente.getDireccion());
		System.out.println("Provincia: Buenos Aires                 	Localidad: " + cliente.getLocalidad());
		System.out.println("Teléfono: 1122334455                    	Provincia: " + cliente.getProvincia());
		System.out.println("                                        	Correo: " + cliente.getCorreo());
		System.out.println("                                        	Teléfono: " + cliente.getTelefono());
		System.out.println("**********************************************************************************");
		System.out.println("Detalles de la Venta:");
		System.out.printf("%-10s %-20s %-15s %-20s %-10s\n", "ID", "Autoparte", "Cantidad", "Precio Unitario",
				"Subtotal");
		System.out.println("----------------------------------------------------------------------------------");
		for (Pedido.Detalle detalle : pedido.getDetalles()) {
			String nombreAutoparte = getNombreAutoparte(detalle.getArticulo());
			System.out.printf("%-10s %-24s %-15d %-18.2f %-18.2f\n", detalle.getArticulo(), nombreAutoparte,
					detalle.getCantidad(), detalle.getPrecio(), (detalle.getCantidad() * detalle.getPrecio()));
		}
		System.out.println("----------------------------------------------------------------------------------");
		System.out.printf("%-70s %-10.2f\n", "Total Venta:", pedido.getMontoTotal());
		System.out.println("**********************************************************************************");
		System.out.println("Medio de pago: " + medioPago);
		System.out.println("Cantidad de Cuotas: " + cantCuotas);
		System.out.printf("%-70s %-10.2f\n", "Monto Total a Pagar:", totalVenta);
		System.out.println("**********************************************************************************");
		System.out.println("Gracias por su compra!");
	}

	// Alerta en caso de que el stock quede por debajo del minimo
	public void StockMinimo(int stock, int minimo) {
		if (stock < minimo) {
			System.out.println("\nAlerta! El stock de esta autoparte se encuentra por debajo el mínimo --> " + minimo
					+ ". Contacte con proveedores.\n");
			return;
		} else {
			return;
		}
	}

	// Verifica si existe "x" autoparte dentro del catálogo. Si no existe devuelve
	// false, caso contrario true
	public boolean existeAutoparte(int codigo) {
		if (catalogoVacio() == false) {
			for (int i = 0; i < catalogo.size(); i++) {
				if (catalogo.get(i).getCodigo() == codigo) {
					return true;
				}
			}
			System.out.println("La autoparte con código " + codigo + " no existe, intente nuevamente");
			return false;
		} else {
			return false;
		}
	}

	public boolean existePedidoConId(int codigo) {
		if (catalogoVacio() == false) {
			for (int i = 0; i < pedidos.size(); i++) {
				if (pedidos.get(i).getIdPedido() == codigo) {
					System.out.println("El pedido con código " + codigo + "  existe, intente cargando otro código");
					return true;
				}
			}

			return false;
		} else {
			return false;
		}
	}
	public boolean existeVentaConId(int codigo) {
		if (catalogoVacio() == false) {
			for (int i = 0; i < cantVentas.size(); i++) {
				if (cantVentas.get(i).getCodigo() == codigo) {
					System.out.println("La operación de venta con código " + codigo + " ya existe en el sistema, intente utilizando otro código");
					return true;
				}
			}

			return false;
		} else {
			return false;
		}
	}

	// Devuelve la denominacion del autoparte necesitado
	public String getNombreAutoparte(int id) {
		// Inicializar con un valor predeterminado
		String nombre = "";

		// Comprobar si el catálogo no está vacío
		if (!catalogoVacio()) {
			// Recorrer el catálogo
			for (int i = 0; i < catalogo.size(); i++) {
				// Comprobar si el código de la autoparte coincide con el ID buscado
				if (catalogo.get(i).getCodigo() == id) {
					// Devolver el precio de la autoparte encontrada
					return catalogo.get(i).getDenominacion();
				}
			}
		}

		// Devolver el valor predeterminado si no se encuentra la autoparte
		return nombre;
	}

	// Verifica si el catálogo no dispone de autopartes y devuelve true en caso de
	// serlo. Caso contrario devuelve false
	public boolean catalogoVacio() {
		if (catalogo.isEmpty()) {
			System.out.println("El catálogo está vacío, se necesita al menos 1 autoparte");
			return true;
		}
		return false;
	}

	// Verifica si existe un pedido mediante el ingreso del número del mismo.
	public boolean existePedido(int numero) {
		for (int i = 0; i < pedidos.size(); i++) {
			if (pedidos.get(i).getIdPedido() == numero) {
				return true;
			}
		}
		System.out.println("No existe un pedido con ID: " + numero + ", intente nuevamente!");
		return false;
	}
}