
import javax.swing.*;

public class DoublyLinkedList {

    public static void main(String[] args) {
        List lista = new List();
        String menu[] = {"Insertar valor", "Eliminar", "Recorrer hacia delante", "Recorrer hacia atrás", "Insertar ordenado", "Salir"};
        String decision = null;
        while (!"Salir".equals(decision)) {
            decision = (String) JOptionPane.showInputDialog(
                    null,
                    "¿Qué desea hacer?",
                    null,
                    JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(),
                    menu, menu[0]);
            switch (decision) {
                case "Insertar valor":
                    lista.addElement(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el dato a agregar")));
                    break;
                case "Eliminar":
                    if (!lista.isEmpty()) {
                        lista.delete(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el valor a eliminar")));
                    } else {
                        JOptionPane.showMessageDialog(null, "La lista está vacia");
                    }
                    break;
                case "Recorrer hacia delante":
                    lista.printList();
                    break;
                case "Recorrer hacia atrás":
                    lista.invertList();
                    break;
                case "Insertar ordenado":
                    lista.addElementInOrder(Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el dato a agregar ordenado")));
                    break;
            }
        }

    }

    static class Node {

        int data;
        Node next;
        Node previous;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }
    }

    static class List {

        int data;
        int position;

        Node start;
        Node end;

        void List() {
            start = null;
            end = null;
        }

        boolean isEmpty() {
            if (start == null) {
                return true;
            } else {
                return false;
            }
        }

        void addElement(int data) {

            if (isEmpty()) {
                Node newNode = new Node(data);
                start = newNode;
                end = newNode;
                setPosition(1);
            } else {
                Node newNode = new Node(data);
                end.next = newNode;
                newNode.previous = end;
                end = newNode;
                setPosition(getPosition() + 1);
            }
        }

        void addElementInOrder(int data) {
            if (isEmpty()) {
                Node newNode = new Node(data);
                start = newNode;
                end = newNode;
                setPosition(getPosition() + 1);
            } else {
                if (data <= start.data) {
                    Node newNode = new Node(data);
                    newNode.next = start;
                    start.previous = newNode;
                    start = newNode;
                    setPosition(getPosition() + 1);
                } else if (data >= start.data && data <= end.data) {
                    Node newNode = new Node(data);
                    Node prev = null;
                    Node aux = start;
                    while (aux != end) {
                        if (newNode.data < aux.data) {
                            newNode.next = aux;
                            prev = aux.previous;
                            newNode.previous = prev;
                            prev.next = newNode;
                            aux.previous = newNode;
                            setPosition(getPosition() + 1);
                            break;
                        }
                        aux = aux.next;
                    }
                } else if (data >= end.data) {
                    Node newNode = new Node(data);
                    end.next = newNode;
                    newNode.previous = end;
                    newNode.next = null;
                    end = newNode;
                    setPosition(getPosition() + 1);
                } else if (getPosition() == 3 || data >= start.data && data <= end.data) {
                    Node newNode = new Node(data);
                    newNode.next = end;
                    newNode.previous = start;
                    end.previous = newNode;
                    end.next = null;
                    start.next = newNode;
                    start.previous = null;
                    setPosition(getPosition() + 1);
                }

            }
            JOptionPane.showMessageDialog(null, "Elemento ordenado agregado");
        }

        void delete(int data) {
            boolean found = false;
            Node prev = null;
            Node selector = start;
            while (selector != end) {
                if (selector.data == data) {
                    found = true;
                    if (prev == null) {
                        start = selector.next;
                        start.previous = null;
                    } else {
                        prev.next = selector.next;
                        Node aux;
                        aux = selector.next;
                        aux.previous = prev;
                    }
                    JOptionPane.showMessageDialog(null, "Valor: " + data + "\nEliminado");
                    setPosition(getPosition() - 1);
                }
                prev = selector;
                selector = selector.next;
            }
            if (data == end.data) {
                end = selector.previous;
            }
            if (!found) {
                JOptionPane.showMessageDialog(null, "Valor: " + data + "\nNo encontrado");
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

        public void setPosition(int position) {
            this.position = position;
        }

        public int getPosition() {
            return position;
        }
    }

}
