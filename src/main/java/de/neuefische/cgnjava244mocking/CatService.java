package de.neuefische.cgnjava244mocking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CatService {

    private final CatRepository catRepository;

    public List<Cat> findAllCats() {
        return catRepository.findAll();
    }

    public Cat findCatById(String id){
        return catRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Cat with id " + id + " not found"));
    }

    public Cat saveCat(CatDto catDto){
        Cat cat = new Cat(UUID.randomUUID().toString(), catDto.name(), catDto.color());
        return catRepository.save(cat);
    }

    public String deleteCat(String id){
        Cat catToDelete = findCatById(id);
        catRepository.delete(catToDelete);
        return "cat deleted";
    }

}
