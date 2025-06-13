package cl.duoc.gamestore.service;

import cl.duoc.gamestore.model.Videojuego;
import cl.duoc.gamestore.repository.VideojuegoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

//Mockito || Mock == Simular
import org.mockito.*;
import static org.mockito.Mockito.*;


public class VideojuegoServiceTest {

    //Creando una instancia Para injectar los Mocks
    @InjectMocks
    private VideojuegoService service;

    //Creando una instancia para reemplazar el repository por datos simulados
    @Mock
    private VideojuegoRepository repo;

    //Constructor
    public VideojuegoServiceTest(){
        //inicializar los mocks anotados para el test @Mock @InjectMocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testing 1 al servicio del método findAll (buscar todo)")
    void testFindAll(){

        //1 Simular al repo y a simular un videojuego
        when(repo.findAll()).thenReturn(List.of(new Videojuego(1L, "Bubsy", "SEGA GENESIS")));

        //2- llamamos al método del servicio que será probado
        List<Videojuego> resultado = service.findAll();

        //3- Verificar que los datos simulados tengan coincidencia
        assertEquals(1, resultado.size());

    }


}
