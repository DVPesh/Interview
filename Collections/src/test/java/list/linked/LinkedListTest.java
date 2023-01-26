package list.linked;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.peshekhonov.list.linked.LinkedList;

import java.util.NoSuchElementException;

public class LinkedListTest {

    private LinkedList<Double> linkedList;

    @BeforeEach
    public void init() {
        linkedList = new LinkedList<>();
    }

    @AfterEach
    public void finish() {
        linkedList = null;
    }

    @Test
    public void testGetUponEmptyList() {
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.get(0));
    }

    @Test
    public void testAddLastAndGet() {
        linkedList.add(2.0);
        linkedList.add(3.0);
        linkedList.add(2, 4.0);
        linkedList.add(3, 5.0);
        Assertions.assertEquals(2.0, linkedList.get(0));
        Assertions.assertEquals(3.0, linkedList.get(1));
        Assertions.assertEquals(4.0, linkedList.get(2));
        Assertions.assertEquals(5.0, linkedList.get(3));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.get(4));
    }

    @Test
    public void testAddByIndexAndGet() {
        linkedList.add(2.0);
        linkedList.add(3.0);
        linkedList.add(4.0);
        Assertions.assertEquals(2.0, linkedList.get(0));
        Assertions.assertEquals(3.0, linkedList.get(1));
        Assertions.assertEquals(4.0, linkedList.get(2));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.get(3));

        linkedList.add(0, 1.0);
        Assertions.assertEquals(1.0, linkedList.get(0));
        Assertions.assertEquals(2.0, linkedList.get(1));
        Assertions.assertEquals(3.0, linkedList.get(2));
        Assertions.assertEquals(4.0, linkedList.get(3));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.get(4));

        linkedList.add(2, 2.5);
        Assertions.assertEquals(1.0, linkedList.get(0));
        Assertions.assertEquals(2.0, linkedList.get(1));
        Assertions.assertEquals(2.5, linkedList.get(2));
        Assertions.assertEquals(3.0, linkedList.get(3));
        Assertions.assertEquals(4.0, linkedList.get(4));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.get(5));
    }

    @Test
    public void testAddByNonexistentIndex() {
        linkedList.add(2.0);
        linkedList.add(3.0);
        linkedList.add(4.0);
        Assertions.assertEquals(2.0, linkedList.get(0));
        Assertions.assertEquals(3.0, linkedList.get(1));
        Assertions.assertEquals(4.0, linkedList.get(2));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.add(23, 50.0));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.add(-1, -10.0));
    }

    @Test
    public void testRemoveUponEmptyList() {
        Assertions.assertThrowsExactly(NoSuchElementException.class, () -> linkedList.remove());
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.remove(0));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.remove(-3));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.remove(50));
        Assertions.assertFalse(linkedList.remove(45.0));
    }

    @Test
    public void testRemove() {
        linkedList.add(2.0);
        linkedList.add(3.0);
        linkedList.add(4.0);
        linkedList.add(0, 1.0);
        Assertions.assertEquals(1.0, linkedList.get(0));
        Assertions.assertEquals(2.0, linkedList.get(1));
        Assertions.assertEquals(3.0, linkedList.get(2));
        Assertions.assertEquals(4.0, linkedList.get(3));

        Assertions.assertEquals(1.0, linkedList.remove());
        Assertions.assertEquals(2.0, linkedList.get(0));
        Assertions.assertEquals(3.0, linkedList.get(1));
        Assertions.assertEquals(4.0, linkedList.get(2));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.get(3));

        Assertions.assertFalse(linkedList.remove(1.0));
        Assertions.assertEquals(2.0, linkedList.get(0));
        Assertions.assertEquals(3.0, linkedList.get(1));
        Assertions.assertEquals(4.0, linkedList.get(2));

        Assertions.assertTrue(linkedList.remove(3.0));
        Assertions.assertEquals(2.0, linkedList.get(0));
        Assertions.assertEquals(4.0, linkedList.get(1));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.get(2));

        Assertions.assertEquals(4.0, linkedList.remove(1));
        Assertions.assertEquals(2.0, linkedList.get(0));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.get(1));

        Assertions.assertEquals(2.0, linkedList.remove(0));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.get(0));
    }
}
