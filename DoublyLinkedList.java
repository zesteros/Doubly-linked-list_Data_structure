
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class DoublyLinkedList {

    public static void main(String[] args) {
        List list = new List();
        String menu[] = {"Insertar valor", "Eliminar", "Recorrer hacia delante", "Recorrer hacia atrás", "Insertar ordenado", "Salir"};
        String decision = null;
        while (!"Salir".equals(decision)) {
            decision = (String) JOptionPane.showInputDialog(
                    null,
                    "Bienvenido\n¿Qué desea hacer?",
                    "Lista Doblemente Enlazada",
                    JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(),
                    menu, menu[0]);
            switch (decision) {
                case "Insertar valor":
                    list.addElement(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el dato a agregar")));
                    break;
                case "Eliminar":
                    if (!list.isEmpty()) {
                        list.delete(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el valor a eliminar")));
                    } else {
                        JOptionPane.showMessageDialog(null, "La lista está vacia");
                    }
                    break;
                case "Recorrer hacia delante":
                    list.printList();
                    break;
                case "Recorrer hacia atrás":
                    list.invertList();
                    break;
                case "Insertar ordenado":
                    list.addElementInOrder(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el dato a agregar ordenado")));
                    break;
            }
        }
    }
}
