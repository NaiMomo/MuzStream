/**
 * This a priority queue special for queueing songs.
 * Priority of an item is defined by an inter, base value is 0.
 * Higher priority means least -ve number. The number of time
 * similar song is enqueued the priority value is decreased by 1
 */
public class PlaylistQueue {

    /**
     * This is single node of the double linked list
     */
    private static class Node {
        int priority; // priority of the ndoe
        Playlist song; // the song object
        Node prev; // next node
        Node next; // previous node

        public Node(Playlist song) {
            this.song = song;
            this.priority = 0; // initially priority is 0
            prev = next = null;
        }

        public void increasePriority() {
            priority--;
        }

        @Override
        public String toString() {
            return "("+priority+","+song+")";
        }
    }

    private int size; // size of the queue
    private Node head; // the head node
    private Node tail; // tail node

    // constructor method
    public PlaylistQueue() {
        this.size = 0;
        this.head = this.tail = null;
    }

    // enqueues a new song. first it checks if the song
    // already exists in the queue or not. if yes it increases
    // the priority and reposition the node. otherwise it adds
    // at the end of the queue
    public void enqueue(Playlist song) {
        if (null == song)
            throw new NullPointerException("song = null");
        Node n = findSong(song);
        if (null == n) {
            n = new Node(song);
            addNode(n);
        }
        else {
            n.increasePriority();
            moveToFront(n);
        }
    }

    // helper method to find a song
    private Node findSong(Playlist song) {
        for (Node n = head; null != n; n = n.next) {
            if (n.song.equals(song))
                return n;
        }
        return null;
    }

    // helper method to add a node to the internal double linked list
    private void addNode(Node node) {
        if (null == head) {
            head = tail = node;
        }
        else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
    }

    // helper method to make the given node the head of the list
    private void moveToFront(Node t) {
        Node r = null;
        for (Node n = head; t != n; n = n.next) {
            if (t.priority < n.priority) {
                r = n;
                break;
            }
        }
        if (null != r) {
            Node tprev = t.prev;
            Node tnext = t.next;
            Node rprev = r.prev;

            t.prev = rprev;
            t.next = r;
            r.prev = t;
            if (null != rprev)
                rprev.next = t;
            if (null != tprev)
                tprev.next = tnext;
            if (null != tnext)
                tnext.prev = tprev;
            if (null == t.prev)
                head = t;
            if (null == tnext)
                tail = tprev;
        }
    }

    // dequeues a item from the tail of the queue
    public Playlist dequeue() {
        if (0 == size) return null;
        Node front = head;
        Playlist song = head.song;
        Node newHead = front.next;
        if (null != newHead) newHead.prev = null;
        head = newHead;
        front.song = null;
        front.prev = front.next = null;
        size--;
        return song;
    }

    // returns the size of the queue
    public int size() {
        return size;
    }

    // string representation of the queue with its priority
    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer();
        buff.append("[");
        int i = 0;
        for (Node n = head; null != n; n = n.next, i++) {
            if (i > 0) buff.append(", ");
            buff.append(n);
        }
        buff.append("]");
        return buff.toString();
    }
}
