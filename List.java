
import javax.swing.JOptionPane;

public class List {

    int data;
    Node start;
    Node end;

    void List() {
        start = null;
        end = null;
    }

    boolean isEmpty() {
        return start == null;
    }

    void addElement(int data) {

        if (isEmpty()) {
            Node newNode = new Node(data);
            start = end = newNode;
        } else {
            Node newNode = new Node(data);
            end.next = newNode;
            newNode.previous = end;
            end = newNode;
            end.next = null;
        }
        JOptionPane.showMessageDialog(null, "Elemento agregado");
    }

    void addElementInOrder(int data) {
        if (isEmpty()) {
            Node newNode = new Node(data);
            start = end = newNode;
            JOptionPane.showMessageDialog(null, "Elemento ordenado agregado");
        } else {
            Node newNode = new Node(data);
            Node aux = start;
            if (newNode.data <= start.data) {
                start.previous = newNode;
                newNode.next = start;
                start = newNode;
                start.previous = null;
                JOptionPane.showMessageDialog(null, "Elemento ordenado agregado");
            } else {
                while (aux != null) {
                    if (newNode.data <= aux.data) {
                        Node prev = aux.previous;
                        newNode.next = aux;
                        newNode.previous = prev;
                        prev.next = newNode;
                        aux.previous = newNode;
                        JOptionPane.showMessageDialog(null, "Elemento ordenado agregado");
                        return;
                    }
                    aux = aux.next;
                }
                if (newNode.data >= end.data) {
                    end.next = newNode;
                    newNode.previous = end;
                    end = newNode;
                    end.next = null;
                    JOptionPane.showMessageDialog(null, "Elemento ordenado agregado");
                }
            }
        }
    }

    void delete(int data) {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "La lista está vacia");
        } else {
            Node aux = start;
            boolean found = false;
            while (aux != null) {
                if (aux.data == data) {
                    found = true;
                    if (aux == start && aux == end) {
                        start = end = null;
                        JOptionPane.showMessageDialog(null, "Valor eliminado\n\nLa lista ha quedado vacía");
                        return;
                    } else if (aux == start) {
                        start = aux.next;
                        start.previous = null;
                        JOptionPane.showMessageDialog(null, "Valor eliminado");
                        return;
                    } else if (aux == end) {
                        end = aux.previous;
                        end.next = null;
                        JOptionPane.showMessageDialog(null, "Valor eliminado");
                        return;
                    } else {
                        Node prev = aux.previous;
                        Node nex = aux.next;
                        prev.next = nex;
                        nex.previous = prev;
                        JOptionPane.showMessageDialog(null, "Valor eliminado");
                        return;
                    }

                }
                aux = aux.next;
            }
            if (!found) {
                JOptionPane.showMessageDialog(null, "Valor no encontrado");
            }
        }
    }

    void invertList() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "La lista está vacia");
        } else {
            String list = "";
            Node aux = end;
            while (aux != start) {
                list += "\t[" + aux.data + "]";
                aux = aux.previous;
            }
            list += "\t[" + aux.data + "]";
            JOptionPane.showMessageDialog(null, "Recorrido hacia atrás: \n" + list);
        }
    }

    void printList() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "La lista está vacia");
        } else {
            String list = "";
            Node aux = start;
            while (aux != end) {
                list += "\t[" + aux.data + "]";
                aux = aux.next;
            }
            list += "\t[" + aux.data + "]";
            JOptionPane.showMessageDialog(null, "Recorrido hacia delante: \n" + list);
        }
    }
}
