package it.unibo.plantsfarm.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.plantsfarm.model.menu.api.Storage;
import it.unibo.plantsfarm.model.menu.impl.StorageImpl;
import it.unibo.plantsfarm.model.plant.PlantType;

/**
 * Test for Storage implementation.
 */
final class StorageTest {

    private static final int INITIAL_QUANTITY = 0;
    private static final int Q1 = 10;
    private static final int Q2 = 50;
    private static final int Q3 = -100;
    private static final int Q4 = 9;
    private static final int Q5 = 5000;
    private static final int Q6 = 100;

    private final Storage storage = new StorageImpl();

    /**
     * Test initialization logic: edible plants start with items, ornamental do not.
     */
    @Test
    void testInitialization() {
        storage.reset();

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
        storage.reset();

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
        storage.reset();

        storage.addItem(PlantType.POTATO, Q6);

        assertTrue(storage.removeItem(PlantType.POTATO, Q4));
        assertEquals(Q6 - Q4, storage.getQuantity(PlantType.POTATO));

        assertFalse(storage.removeItem(PlantType.POTATO, Q5));
        assertEquals(Q6 - Q4, storage.getQuantity(PlantType.POTATO));

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
