public class Fila {
    
    Node head;
    Node tail;
    int size;

    public Fila(int size) {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }


    public void add(int data){
        Node newNode = new Node(data);

        if(head == null){
            head = newNode;
            tail = newNode;
            size++;
        }else{
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    public void remove(){
        if(head == null){
            System.out.println("Fila vazia");
        }else{
            head = head.next;
            size--;
        }
    }

    public void peek(){
        if(head == null){
            System.out.println("Fila vazia");
        }else{
            System.out.println(head.data);
        }
    }

    public void isEmpty(){
        if(head == null){
            System.out.println("Fila vazia");
        }
    }

    public void size(){
        System.out.println("Tamanho da fila: " + size);
    }

}
