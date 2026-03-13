import ui.LaboratorioFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LaboratorioFrame frame = new LaboratorioFrame();
            frame.setVisible(true);
        });
    }
}
