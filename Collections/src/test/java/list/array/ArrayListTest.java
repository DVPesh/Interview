package list.array;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.peshekhonov.list.array.ArrayList;

import java.lang.reflect.Field;

public class ArrayListTest {

    private ArrayList<Double> arrayList;

    @BeforeEach
    public void init() {
        arrayList = new ArrayList<>();
    }

    @AfterEach
    public void finish() {
        arrayList = null;
    }

    @Test
    public void testGetUponEmptyList() {
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.get(0));
    }

    @Test
    public void testAddLastAndGet() {
        arrayList.add(2.0);
        arrayList.add(3.0);
        arrayList.add(2, 4.0);
        arrayList.add(3, 5.0);
        Assertions.assertEquals(2.0, arrayList.get(0));
        Assertions.assertEquals(3.0, arrayList.get(1));
        Assertions.assertEquals(4.0, arrayList.get(2));
        Assertions.assertEquals(5.0, arrayList.get(3));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.get(4));
    }

    @Test
    public void testAddByIndexAndGet() {
        arrayList.add(2.0);
        arrayList.add(3.0);
        arrayList.add(4.0);
        Assertions.assertEquals(2.0, arrayList.get(0));
        Assertions.assertEquals(3.0, arrayList.get(1));
        Assertions.assertEquals(4.0, arrayList.get(2));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.get(3));

        arrayList.add(0, 1.0);
        Assertions.assertEquals(1.0, arrayList.get(0));
        Assertions.assertEquals(2.0, arrayList.get(1));
        Assertions.assertEquals(3.0, arrayList.get(2));
        Assertions.assertEquals(4.0, arrayList.get(3));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.get(4));

        arrayList.add(2, 2.5);
        Assertions.assertEquals(1.0, arrayList.get(0));
        Assertions.assertEquals(2.0, arrayList.get(1));
        Assertions.assertEquals(2.5, arrayList.get(2));
        Assertions.assertEquals(3.0, arrayList.get(3));
        Assertions.assertEquals(4.0, arrayList.get(4));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.get(5));
    }

    @Test
    public void testAddByNonexistentIndex() {
        arrayList.add(2.0);
        arrayList.add(3.0);
        arrayList.add(4.0);
        Assertions.assertEquals(2.0, arrayList.get(0));
        Assertions.assertEquals(3.0, arrayList.get(1));
        Assertions.assertEquals(4.0, arrayList.get(2));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.add(23, 50.0));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.add(-1, -10.0));
    }

    @Test
    public void testRemoveUponEmptyList() {
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.remove(0));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.remove(-3));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.remove(50));
        Assertions.assertFalse(arrayList.remove(45.0));
    }

    @Test
    public void testRemove() {
        arrayList.add(2.0);
        arrayList.add(3.0);
        arrayList.add(4.0);
        arrayList.add(0, 1.0);
        Assertions.assertEquals(1.0, arrayList.get(0));
        Assertions.assertEquals(2.0, arrayList.get(1));
        Assertions.assertEquals(3.0, arrayList.get(2));
        Assertions.assertEquals(4.0, arrayList.get(3));

        Assertions.assertFalse(arrayList.remove(-10.0));
        Assertions.assertTrue(arrayList.remove(1.0));
        Assertions.assertEquals(2.0, arrayList.get(0));
        Assertions.assertEquals(3.0, arrayList.get(1));
        Assertions.assertEquals(4.0, arrayList.get(2));

        Assertions.assertTrue(arrayList.remove(3.0));
        Assertions.assertEquals(2.0, arrayList.get(0));
        Assertions.assertEquals(4.0, arrayList.get(1));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.get(2));

        Assertions.assertEquals(4.0, arrayList.remove(1));
        Assertions.assertEquals(2.0, arrayList.get(0));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.get(1));

        Assertions.assertEquals(2.0, arrayList.remove(0));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.get(0));
    }

    @Test
    public void testInitialCapacity() throws NoSuchFieldException, IllegalAccessException {
        Assertions.assertEquals(10, getCapacity(arrayList));
        Assertions.assertEquals(25, getCapacity(new ArrayList<>(25)));
        Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> new ArrayList<Double>(-5));
    }

    @Test
    public void testCapacityChange() throws NoSuchFieldException, IllegalAccessException {
        Assertions.assertEquals(10, getCapacity(arrayList));
        for (double value = 0; value < 11; value++) {
            arrayList.add(value);
        }
        Assertions.assertEquals(15, getCapacity(arrayList));
    }

    private int getCapacity(ArrayList list) throws NoSuchFieldException, IllegalAccessException {
        Field field = ArrayList.class.getDeclaredField("elementData");
        field.setAccessible(true);
        return ((Object[]) field.get(list)).length;
    }
}
