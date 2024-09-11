package de.neuefische.cgnjava244mocking;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CatServiceTest {

    private final CatRepository catRepository = mock(CatRepository.class);
    private final CatService catService = new CatService(catRepository);

    @Test
    void findAllCats() {
        // GIVEN
        Cat cat = new Cat("1", "Mauzi", "black");
        catRepository.save(cat);
        List<Cat> expected = List.of(cat);
        // WHEN
        when(catRepository.findAll()).thenReturn(expected);
        List<Cat> actual = catService.findAllCats();
        // THEN
        assertEquals(expected, actual);
    }

    @Test
    void findCatById() {
        // GIVEN
        String id = "1";
        Cat expected = new Cat("1", "Mauzi", "black");
        // WHEN
        when(catRepository.findById(id)).thenReturn(Optional.of(expected));
        Cat actual = catService.findCatById(id);
        // THEN
        verify(catRepository).findById(id);
        assertEquals(expected, actual);
    }

    @Test
    void findCatById_fail() {
        // GIVEN
        String id = "1";
        // WHEN
        //when(catRepository.findById(id)).thenReturn(Optional.empty());
        // THEN
        assertThrows(NoSuchElementException.class, () -> catService.findCatById(id));
    }

    @Test
    void saveCat() {
        // GIVEN
        Cat expected = new Cat("1", "Mauzi", "black");
        CatDto catDto = new CatDto("Mauzi", "black");
        // WHEN
        when(catRepository.save(any(Cat.class))).thenReturn(expected);
        Cat actual = catService.saveCat(catDto);
        // THEN
        verify(catRepository).save(any(Cat.class));
        assertEquals(expected, actual);
    }

    @Test
    void deleteCat() {
        // GIVEN
        String id = "1";
        Cat existingCat = new Cat("1", "Mauzi", "black");
        catRepository.save(existingCat);
        String expected = "cat deleted";
        // WHEN
        when(catRepository.findById(id)).thenReturn(Optional.of(existingCat));
        String actual = catService.deleteCat(id);
        // THEN
        verify(catRepository).findById(id);
        assertEquals(expected, actual);
    }
}