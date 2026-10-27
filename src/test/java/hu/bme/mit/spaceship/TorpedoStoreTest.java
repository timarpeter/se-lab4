package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

    @Test
    void fire_Success() {
        // Arrange
        TorpedoStore store = new TorpedoStore(10, 0.0);

        // Act
        boolean result = store.fire(1);

        // Assert
        assertTrue(result);
        assertEquals(9, store.getTorpedoCount());
    }

    @Test
    void fire_Failure_DoesNotConsumeTorpedo() {
        TorpedoStore store = new TorpedoStore(10, 1.0);

        boolean result = store.fire(1);

        assertFalse(result);
        assertEquals(10, store.getTorpedoCount());
    }

    @Test
    void fire_RepeatedFailures_KeepTorpedoes() {
        TorpedoStore store = new TorpedoStore(3, 1.0);

        assertFalse(store.fire(1));
        assertFalse(store.fire(1));
        assertFalse(store.fire(1));

        assertEquals(3, store.getTorpedoCount());
        assertFalse(store.isEmpty());
    }

    @Test
    void fire_MultipleTorpedoesAtOnce() {
        TorpedoStore store = new TorpedoStore(10, 0.0);

        boolean result = store.fire(3);

        assertTrue(result);
        assertEquals(7, store.getTorpedoCount());
    }

    @Test
    void fire_LastTorpedo_EmptiesStore() {
        TorpedoStore store = new TorpedoStore(1, 0.0);

        boolean result = store.fire(1);

        assertTrue(result);
        assertTrue(store.isEmpty());
        assertEquals(0, store.getTorpedoCount());
    }

    @Test
    void fire_ZeroTorpedoes_ThrowsException() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_NegativeNumber_ThrowsException() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(-1));
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void fire_MoreThanAvailable_ThrowsException() {
        TorpedoStore store = new TorpedoStore(2, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(3));
        assertEquals(2, store.getTorpedoCount());
    }

    @Test
    void fire_FromEmptyStore_ThrowsException() {
        TorpedoStore store = new TorpedoStore(0, 0.0);

        assertThrows(IllegalArgumentException.class, () -> store.fire(1));
    }

    @Test
    void isEmpty_NonEmptyStore_ReturnsFalse() {
        TorpedoStore store = new TorpedoStore(5, 0.0);

        assertFalse(store.isEmpty());
        assertEquals(5, store.getTorpedoCount());
    }

    @Test
    void isEmpty_EmptyStore_ReturnsTrue() {
        TorpedoStore store = new TorpedoStore(0, 0.0);

        assertTrue(store.isEmpty());
        assertEquals(0, store.getTorpedoCount());
    }
}
