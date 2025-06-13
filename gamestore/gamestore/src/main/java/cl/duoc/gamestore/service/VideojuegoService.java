package cl.duoc.gamestore.service;
import cl.duoc.gamestore.model.Videojuego;
import cl.duoc.gamestore.repository.VideojuegoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VideojuegoService {

    private final VideojuegoRepository repo;
    public VideojuegoService(VideojuegoRepository repo){
        this.repo = repo;
    }
    //Obtener
    public List<Videojuego> findAll(){
        return repo.findAll();
    }
    //Guardar
    public Videojuego save(Videojuego v){
        return repo.save(v);
    }
    //Eliminar
    public void delete(Long id){
        repo.deleteById(id);
    }
}
