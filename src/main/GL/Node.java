package main.GL;

import java.io.Serial;
import java.io.Serializable;
/*
Knoten der List
 */
public class Node implements Serializable
{
        @Serial
        private static final long serialVersionUID = 8L;
        Verkaufsobjekt data;
        Node next;


        public Node()
        {
            this.data = null;
            this.next = null;
        }

        public Node(Verkaufsobjekt data)
        {
            this.data = data;
            this.next = null;
        }

        public Verkaufsobjekt getData()
        {
            return data;
        }
        public void setData(Verkaufsobjekt data)
        {
            this.data = data;
        }
        public Node getNext()
        {
            return next;
        }
        public void setNext(Node next)
        {
            this.next = next;
        }
}
