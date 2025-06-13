package cl.duoc.gamestore.controller;
import cl.duoc.gamestore.model.Videojuego;
import cl.duoc.gamestore.service.VideojuegoService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    private VideojuegoService service;
    public VideojuegoController(VideojuegoService service){
        this.service = service;
    }

    //GET
    @GetMapping
    public List<Videojuego> getAll(){
        return service.findAll();
    }

    //POST
    @PostMapping
    public Videojuego create(@RequestBody Videojuego v){
        return service.save(v);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
