package interfaz;

import negocio.Administrador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {
    private Administrador admin;
    private static final long serialVersionUID = 1L;

    public MenuFrame(Administrador admin) {
        this.admin = admin;

        setTitle("Menú Principal - Tutta la Macchina");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(12, 1));

        JButton agregarButton = new JButton("Agregar autoparte");
        JButton listarButton = new JButton("Listar autopartes");
        JButton eliminarButton = new JButton("Eliminar autoparte del catálogo");
        JButton modificarStockButton = new JButton("Modificar stock autoparte");
        JButton modificarCatalogoButton = new JButton("Modificar catálogo");
        JButton disponibilidadButton = new JButton("Disponibilidad stock de una autoparte");
        JButton agregarClienteButton = new JButton("Agregar cliente");
        JButton cargarPedidoButton = new JButton("Cargar pedido");
        JButton cancelarPedidoButton = new JButton("Cancelar pedido");
        JButton registrarVentaConPedidoButton = new JButton("Registrar venta con pedido");
        JButton registrarVentaSinPedidoButton = new JButton("Registrar venta sin pedido");
        JButton cerrarSesionButton = new JButton("Cerrar Sesión");

        panel.add(agregarButton);
        panel.add(listarButton);
        panel.add(eliminarButton);
        panel.add(modificarStockButton);
        panel.add(modificarCatalogoButton);
        panel.add(disponibilidadButton);
        panel.add(agregarClienteButton);
        panel.add(cargarPedidoButton);
        panel.add(cancelarPedidoButton);
        panel.add(registrarVentaConPedidoButton);
        panel.add(registrarVentaSinPedidoButton);
        panel.add(cerrarSesionButton);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AgregarAutoparteFrame(admin);
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListarAutopartesFrame(admin);
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EliminarAutoparteFrame(admin);
            }
        });

        modificarStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModificarStockFrame(admin);
            }
        });

        modificarCatalogoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModificarCatalogoFrame(admin);
            }
        });

        disponibilidadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StockDisponibleFrame(admin);
            }
        });

        agregarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	new AgregarClienteFrame(admin);
            }
        });

        cargarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CargarPedidoFrame(admin).setVisible(true);;
            }
        });

        cancelarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CancelarPedidoFrame(admin).setVisible(true);;
            }
        });

        registrarVentaConPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrarVentaConPedidoFrame(admin).setVisible(true);
            }
        });

        registrarVentaSinPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrarVentaSinPedidoFrame(admin).setVisible(true);
            }
        });

        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // cerrarSesion();
            }
        });

        panel.add(cerrarSesionButton);
        add(panel);
        setVisible(true);
    }

}