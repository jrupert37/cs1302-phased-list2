package cs1302.p2;

import cs1302.adt.StringList;
import cs1302.adt.FancyStringList;

/**
 * Class that creates a new Array based StringList.
 * Inherits methods from BaseStringList parent.
 */
public class ArrayStringList extends BaseStringList {
    private String[] items;

    /**
     * Constructs a new ArrayStringList. Calls parent constructor to set initial list size
     * equal to 0, and initiates {@code item} array with an array with a size of 10.
     */
    public ArrayStringList() {
        super();
        this.items = new String[10];
    } // default constructor

    /**
     * Constructs a new ArrayStringList that is an exact copy of another given StringList.
     * Uses the {@code add} method to copy items to new list.
     *
     * @param other the StringList being copied
     */
    public ArrayStringList(StringList other) {
        super();
        this.items = new String[other.size()];
        for (int i = 0; i < other.size(); i++) {
            this.add(i, other.get(i));
        } // for
    } // constructor

    /**
     * {@inheritDoc}
     */
    protected FancyStringList copy(StringList other) {
        return new ArrayStringList(other);
    } // copy

    /**
     * Adds item to the ArrayStringList at the given index.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean add(int index, String item) {
        checkAddIndex(index); // checks that given index is appropriate
        checkItem(item); // checks if item is null or empty
        checkSize(); // checks if array size needs to be adjusted before adding a new element
        for (int i = size() - 1; i >= index; i--) { // if item is added in the middle of
            items[i + 1] = items[i];                // the list, elements are shifted accordingly
        } // for
        items[index] = item;
        size++;
        return true;
    } // add

    /**
     * Returns the item at the given index of the list.
     *
     * {@inheritDoc}
     */
    @Override
    public String get(int index) {
        checkGetIndex(index);
        return items[index];
    } // get

    /**
     * Removes the item at the given index from the list.
     *
     * {@inheritDoc}
     */
    @Override
    public String remove(int index) {
        checkGetIndex(index);
        checkSize();
        String removed = items[index];
        for (int i = index; i < size(); i++) {
            items[i] = items[i + 1];
        } // for
        size--;
        return removed;
    } // remove

    /**
     * Clears the ArrayStringList, elements are removed, size is reset to 0.
     *
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        items = new String[10];
        this.size = 0;
    } // clear

    /**
     * Checks if the size of the array needs to be adjusted to allow
     * more items to be added. If array is full, {@code rebuildArray}
     * is called.
     */
    private void checkSize() {
        if (items.length == size()) {
            double temp = items.length * 1.5;
            int newSize = (int) temp;
            rebuildArray(newSize);
        } // if
    } // checkSize

    /**
     * If array is full, a new array that is 50% bigger is constructed. Items
     * from initial array are copied over to new array.
     *
     * @param newSize the size the {@code items} array is being changed to, which
     * is 50% bigger.
     */
    private void rebuildArray(int newSize) {
        String[] newItems = new String[newSize];
        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        } // for
        items = newItems;
    } // rebuildArray

} // ArrayStringList
