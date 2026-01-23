package it.unibo.oop.plantsfarm.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.plantsfarm.model.menu.Storage;
import it.unibo.plantsfarm.model.plant.PlantType;

/**
 * Test for Storage implementation.
 */
final class StorageTest {

    private static final int INITIAL_QUANTITY = 999;
    private static final int Q1 = 10;
    private static final int Q2 = 50;
    private static final int Q3 = -100;
    private static final int Q4 = 9;
    private static final int Q5 = 5000;

    private final Storage storage = new Storage();

    /**
     * Test initialization logic: edible plants start with items, ornamental do not.
     */
    @Test
    void testInitialization() {
        assertEquals(INITIAL_QUANTITY, storage.getQuantity(PlantType.CARROT));
        assertEquals(INITIAL_QUANTITY, storage.getQuantity(PlantType.TOMATO));

        assertEquals(0, storage.getQuantity(PlantType.BEGONIA));
        assertEquals(0, storage.getQuantity(PlantType.MONSTERA));
    }

    /**
     * Test adding items.
     */
    @Test
    void testAddItem() {
        storage.addItem(PlantType.CARROT, Q1);
        assertEquals(INITIAL_QUANTITY + Q1, storage.getQuantity(PlantType.CARROT));

        storage.addItem(PlantType.BEGONIA, Q2);
        assertEquals(0, storage.getQuantity(PlantType.BEGONIA));

        storage.addItem(PlantType.CARROT, Q3);
        assertEquals(INITIAL_QUANTITY + Q1, storage.getQuantity(PlantType.CARROT));
    }

    /**
     * Test removing items.
     */
    @Test
    void testRemoveItem() {
        assertTrue(storage.removeItem(PlantType.POTATO, Q4));

        assertEquals(INITIAL_QUANTITY - Q4, storage.getQuantity(PlantType.POTATO));

        assertFalse(storage.removeItem(PlantType.POTATO, Q5));

        assertEquals(INITIAL_QUANTITY - Q4, storage.getQuantity(PlantType.POTATO));

        assertFalse(storage.removeItem(PlantType.BEGONIA, 1));
    }

    /**
     * Test the immutability of the storage map.
     */
    @Test
    void testUnmodifiableMap() {
        final var items = storage.getAllItems();
        assertThrows(UnsupportedOperationException.class, () -> {
            items.put(PlantType.CARROT, 0);
        });
    }
}
