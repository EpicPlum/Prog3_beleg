package main.GL;

import main.GL.interfaces.Verkaufsobjektbar;

import java.io.Serial;
import java.io.Serializable;
/*
Knoten der List
 */
public class Node implements Serializable
{
        @Serial
        private static final long serialVersionUID = 8L;
        Verkaufsobjektbar data;
        Node next;


        public Node()
        {
            this.data = null;
            this.next = null;
        }

        public Node(Verkaufsobjektbar data)
        {
            this.data = data;
            this.next = null;
        }

        public Verkaufsobjektbar getData()
        {
            return data;
        }
        public Node getNext()
        {
            return next;
        }
}
