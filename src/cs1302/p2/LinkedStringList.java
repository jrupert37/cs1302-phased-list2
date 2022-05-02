package cs1302.p2;

import cs1302.adt.StringList;
import cs1302.adt.FancyStringList;
import cs1302.adt.Node;

/**
 * Creaes a new LinkedStringList which inherits methods from BaseStrinList parent.
 */
public class LinkedStringList extends BaseStringList {
    private Node head;

    /**
     * Constructs a new LinkedStringList object. Calls parent constructor to intialize
     * size of the list to 0. Sets the head node to null.
     */
    public LinkedStringList() {
        super();
        head = null;
    } // default constructor

    /**
     * Constructs a new LinkedStringList that is an exact copy of another given StringList.
     * Uses {@code add} method to copy items to new list.
     *
     * @param other the StringList being copied
     */
    public LinkedStringList(StringList other) {
        super();
        for (int i = 0; i < other.size(); i++) {
            this.add(i, other.get(i));
        } // for
    } // specific constructor

    /**
     * {@inheritDoc}
     */
    protected FancyStringList copy(StringList other) {
        return new LinkedStringList(other);
    } // copy

    /**
     * Adds given item to the list at the given index and adjusts size accordingly.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean add(int index, String item) {
        checkAddIndex(index); // checks that given index is appropriate
        checkItem(item); // checks if item is empty or null
        if (index == 0) {
            head = new Node(item, head);
        } else {
            Node node = getNode(index);                   // traverses through nodes until arriving
            node.setNext(new Node(item, node.getNext())); // at the node prior to the given index,
        } // if                                           // then creates a new node which is
        size++;                                           // assigned as the prior node's next node
        return true;
    } // add

    /**
     * Returns item from the list at the given index.
     *
     * {@inheritDoc}
     */
    @Override
    public String get(int index) {
        checkGetIndex(index); // checks that given index is in bounds
        if (index == 0) {
            return head.getItem();
        } else {
            Node node = getNode(index);      // traverses through nodes until arriving at the node
            return node.getNext().getItem(); // prior to the given index, then returns prior
        } // if                              // node's next node's item. Done this way to reduce
    } // get                                 // lines of code by using pre-existing method

    /**
     * Clears the LinkedStringList by setting head equal to null and size
     * equal to 0. Pre-existing nodes are no longer accessible and therfore list is "emtpy".
     *
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        head = null;
        this.size = 0;
    } // clear

    /**
     * Removes item at the given index and size is adjusted accordingly.
     *
     * {@inheritDoc}
     */
    @Override
    public String remove(int index) {
        String removed = get(index);
        if (index == 0) {
            head = head.getNext();
        } else {
            Node node = getNode(index);
            node.setNext(node.getNext().getNext());
        } // if
        size--;
        return removed;
    } // remove

    /**
     * Returns the node located at the position of the list index - 1. Created to be used
     * by other methods (add, get, remove) in order to reduce number of lines of code.
     *
     * @param index the given index from the calling method
     * @return the node objected located at index - 1.
     */
    public Node getNode(int index) {
        Node node = head;
        for (int i = 0; i < index - 1; i++) {
            node = node.getNext();
        } // for
        return node;
    } // getNode

} // LinkedStringList
