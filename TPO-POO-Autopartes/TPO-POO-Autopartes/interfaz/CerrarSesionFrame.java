package interfaz;
import javax.swing.*;

import negocio.Administrador;

import java.awt.event.*;

public class CerrarSesionFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private Administrador admin;

    private JButton btnCerrarSesion;

    public CerrarSesionFrame(Administrador admin) {
        this.admin = admin;

        setTitle("Cerrar Sesión");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar ventana en la pantalla

        initComponents();
    }

    private void initComponents() {
        // Crear componentes
        JPanel panel = new JPanel();

        btnCerrarSesion = new JButton("Cerrar Sesión");

        panel.add(btnCerrarSesion);

        // Acción del botón Cerrar Sesión
        btnCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });

        add(panel);
    }

    private void cerrarSesion() {
        if (admin.CerrarSesion()) {
            JOptionPane.showMessageDialog(this, "Se ha finalizado la sesión exitosamente.");
            System.exit(0); // Cerrar toda la aplicación
        } else {
            JOptionPane.showMessageDialog(this, "Error al cerrar sesión.");
        }
    }

}
