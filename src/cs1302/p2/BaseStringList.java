package cs1302.p2;

import cs1302.adt.FancyStringList;
import cs1302.adt.StringList;

/**
 * Abstract class that implements the {@code StringList} interface and serves as
 * a parent for ArrayStringList and LinkedStringList.
 * Contains general methods to be used by both implementations.
 */
public abstract class BaseStringList implements FancyStringList {
    protected int size;

    /**
     * Sets the initial size of the {@code StringList} to 0.
     */
    public BaseStringList() {
        this.size = 0;
    } // constructor

    /**
     * A copy method that creates a new FancyStringList, which is an exact copy
     * of a given StringList.
     *
     * @param other the StringList to be copied into a new FancyStringList
     * @return a new FancyStringList, which is a copy of the given StringList
     */
    protected abstract FancyStringList copy(StringList other); // returns a FSL because FSLs can be
                                                               // assigned to both FSL and SL vars

    /**
     * Returns the {@code size} of the list.
     *
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.size;
    } // size

    /**
     * Adds all elements of a given StringList to the calling StringList.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean add(int index, StringList items) {
        if (items == null) {
            throw new NullPointerException("items cannot be null");
        } // if
        StringList copy = copy(items);           // by creating a copy instead of using the actual
        for (int i = 0; i < copy.size(); i++) {  // calling StringList, self-reference becomes
            this.add(index++, copy.get(i));      // much easier.
        } // for
        return !isEmpty();
    } // add - SL

    /**
     * Access child classes {@code add} method to add item at the very beginning of a list.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean prepend(String item) {
        add(0, item);
        return true;
    } // prepend

    /**
     * Accesses child classes {@code add} method to add an item
     * at the end of the given list.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean append(String item) {
        add(size(), item);
        return true;
    } // append

    /**
     * Uses the FancyStringList {@code add} method to add a given StringList
     * to the beginning of the calling StringList.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean prepend(StringList items) {
        add(0, items);
        return !isEmpty();
    } // prepend - SL

    /**
     * Uses the FancyStringList {@code add} method to add a given StringList
     * to the end of the calling StringList.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean append(StringList items) {
        add(this.size(), items);
        return !isEmpty();
    } // append - SL

    /**
     * Returns a new StringList that contains the items of a given StringList
     * between the {@code start} and {@code stop} indexes.
     *
     * {@inheritDoc}
     */
    @Override
    public StringList slice(int start, int stop) {
        if (start < 0 || stop > size() || start > stop) {
            throw new IndexOutOfBoundsException("invalid start or stop given");
        } // if
        StringList newList = copy(this); // will create a copy of the calling StringList, and of
        newList.clear();                 // the same type as the calling list. clear() used
        int addIndex = 0;                // to empty out the elements while allowing us to still
        for (int i = start; i < stop; i++) { // use a SL of same underlying type (ASL or LSL).
            newList.add(addIndex++, get(i)); // Allows this method to be moved up to BaseStringList.
        } // for
        return newList;
    } // slice - SL

    /**
     * Returns a new FancyStringList that contains the items of a given StringList
     * between the {@code start} and {@code stop} indexes, at a certain interval
     * given by {@code step}.
     *
     * {@inheritDoc}
     */
    @Override
    public FancyStringList slice(int start, int stop, int step) {
        if (start < 0 || stop > size() || start > stop || step < 1) {
            throw new IndexOutOfBoundsException("invalid start, stop, or step given");
        } // if
        FancyStringList newList = copy(this);
        newList.clear();
        int addIndex = 0;
        for (int i = start; i < stop; i += step) {
            newList.add(addIndex++, this.get(i));
        } // for
        return newList;
    } // slice - FSL

    /**
     * Returns a new FancyStringList that contains the items from the calling
     * StringList in reverse order.
     *
     * {@inheritDoc}
     */
    @Override
    public FancyStringList reverse() {
        FancyStringList newList = copy(this);
        newList.clear();
        int addIndex = 0;
        for (int i = this.size() - 1; i >= 0; i--) {
            newList.add(addIndex++, this.get(i));
        } // for
        return newList;
    } // revese

    /**
     * Returns the contents of a given list with a starting, separating, and ending character.
     *
     * {@inheritDoc}
     */
    @Override
    public String makeString(String start, String sep, String end) {
        if (isEmpty()) {
            return start + end;
        } else {
            String all = start;
            for (int i = 0; i < size() - 1; i++) {  // loop does not include final item
                all = all + this.get(i) + sep;      // of the list, because final item
            } // for                                // should be be followed by ending character
            all = all + this.get(size() - 1) + end; // instead of sperating character
            return all;
        } // if
    } // makeString

    /**
     * Returns the contents of a list using {@code makeString} with brackets and a comma separator.
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return makeString("[", ", ", "]");
    } // toString

    /**
     * Uses {@code indexOf to} check if the list contains a given {@code target} String at or
     * after the given {@code start} index.
     *
     * {@inheritDoc}
     */
    @Override
    public boolean contains(int start, String target) {
        return indexOf(start, target) != -1; // uses indexOf because if indeOf does not return -1
    } // contains                            // there must exist an index where target exists.
                                             // Reduces lines of code needed.

    /**
     * Returns the index of the given {@code target} String, if that String
     * exists in the list at or after the given {@code start} index.
     *
     * {@inheritDoc}
     */
    @Override
    public int indexOf(int start, String target) {
        for (int i = start; i < this.size(); i++) {
            if (i > 0) {
                if (this.get(i).equals(target)) {
                    return i;
                } // if
            } // if
        } // for
        return -1;
    } // indexOf

    /**
     * Determines if a given list is empty or not (if the {@code size} of the list
     * is equal to 0).
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        if (this.size() == 0) {
            return true;
        } else {
            return false;
        } // if
    } // isEmpty

    /**
     * Checks if a given item to be added to a list is null or empty and throws
     * the appropriate excpetion if so.
     *
     * @param item the item to be added to a list
     * @throws IllegalArgumentException if item is empty
     * @throws NullPointerExceptino if item is null
     */
    public void checkItem(String item) {
        if (item.isEmpty()) {
            throw new IllegalArgumentException("item cannot be empty");
        } else if (item == null) {
            throw new NullPointerException("item cannot be null");
        } // if
    } // checkItem

    /**
     * Checks if the index for a call to the {@code get} method is in range (must not be less than 0
     * or greater than or equal to the size of the list).
     *
     * @param index the given index called by the {@code get} method
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal
     * to the size of the list (if index is out of bounds)
     */
    public void checkGetIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index given is out of bounds");
        } // if
    } // checkIndex

    /**
     * Checks if the index for a call to the {@code add} method is valid (must not be less than 0
     * or greater than the size of the list).
     *
     * @param index the given index called by the {@code add} method
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than the size
     * of the list
     */
    public void checkAddIndex(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("index " + index + " is out of bounds");
        } // if
    } // checkAddIndex

} // BaseStrigList
