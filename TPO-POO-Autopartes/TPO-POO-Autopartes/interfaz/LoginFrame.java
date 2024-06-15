package interfaz;
import negocio.Administrador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField usuarioField;
    private JPasswordField contrasenaField;
    private JButton loginButton;
    private int contador = 3;
    private Administrador admin;

    public LoginFrame(Administrador admin) {
        this.admin = admin;

        setTitle("Inicio de Sesión - Tutta la Macchina");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(new JLabel("Usuario:"));
        usuarioField = new JTextField();
        panel.add(usuarioField);

        panel.add(new JLabel("Contraseña:"));
        contrasenaField = new JPasswordField();
        panel.add(contrasenaField);

        loginButton = new JButton("Iniciar Sesión");
        panel.add(loginButton);

        add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = usuarioField.getText();
                String contrasena = new String(contrasenaField.getPassword());

                if (admin.IniciarSesion(usuario, contrasena)) {
                    new MenuFrame(admin);
                    dispose();
                } else {
                    contador--;
                    if (contador == 0) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Intentos agotados. Cerrando aplicación.");
                        System.exit(0);
                    } else {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Datos incorrectos. Intentos restantes: " + contador);
                    }
                }
            }
        });

        setVisible(true);
    }
}
