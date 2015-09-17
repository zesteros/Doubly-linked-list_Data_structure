
import javax.swing.*;

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
}
