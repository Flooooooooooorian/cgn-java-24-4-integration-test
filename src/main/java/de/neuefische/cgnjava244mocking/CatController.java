package de.neuefische.cgnjava244mocking;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cats")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @GetMapping
    public List<Cat> getAllCats() {
        return catService.findAllCats();
    }

    @PostMapping
    public Cat postCat(@RequestBody CatDto cat) {
        return catService.saveCat(cat);
    }
}
