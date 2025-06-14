package cl.duoc.gamestore.controller;
import cl.duoc.gamestore.model.Videojuego;
import cl.duoc.gamestore.service.VideojuegoService;
//1 Importaciones de JUnit 5
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//2 Anotación para probar solo el controlador (no el contexto completo de Spring Boot)
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

//3 Anotación para simular un bean dentro del ApplicationContext de Spring
import org.springframework.test.context.bean.override.mockito.MockitoBean;

//4 Inyección automática del cliente de pruebas web
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

//5 Métodos estáticos de Mockito
import static org.mockito.Mockito.*;

//6 Métodos para construir peticiones HTTP simuladas y verificar respuestas
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//7 Librería para convertir objetos a JSON (necesaria en peticiones POST)
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

//Anotación indicar que en esta clase se realizará testing del controlador
@WebMvcTest(VideojuegoController.class)
public class VideojuegoControllerTest {

    //Injectando MOkck para utilizar dentro de esta clase
    @Autowired
    private MockMvc mockMvc;
    //
    @MockitoBean
    private VideojuegoService service;

    //Convertir de texto a JSON y viceversa
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("TESTING 1 GET")
    void testGetAll() throws Exception{
        //1 Simulación que el servicio me entrega una lista
        when(service.findAll()).thenReturn(List.of(new Videojuego(1L, "Mario", "Super Nintendo")));
        //2 Simular una petición al EndPoint
        mockMvc.perform(get("/api/videojuegos"))
                //3- Indico que es lo que espero como respuesta
                .andExpect(status().isOk()) //200
        // 4 - Verificamos la coincidencia
                .andExpect(jsonPath("$[0].nombre").value("Mario"));
    }

    //GUARDAR POST

    @Test
    @DisplayName("TESTING 2 POST")
    void testPost() throws Exception{
        //1 Simulación Guardar un juego con su ID
        Videojuego v = new Videojuego(null, "Rayman Legends", "Xbox");
        //2 - Simular el guardado de un videojuego y su respuesta
        when(service.save(any())).thenReturn(new Videojuego(1L, "Rayman Legends", "Xbox"));
        //3 Ejecutar una petición POST y hacer la conversión de datos Cast Casteo = Parseo
        mockMvc.perform(post("/api/videojuegos")
                .contentType("application/json")//indicando que es en formato JSON
                .content(mapper.writeValueAsString(v)))//Convirtiendo el objeto o dato
                .andExpect(status().isOk()) //indicamos la respuesta esperada 200
                .andExpect(jsonPath("$.nombre").value("Rayman Legends"));
    }
    //DELETE
    @Test
    @DisplayName("TEST 3 - DELETE")
    void testDelete() throws Exception{
        //1 Simulamos una petición dlete sin lanzar una excepción //DN= trabajar peticiones que no devuelven nada
        doNothing().when(service).delete(1L);
        //2 Ejecutamos la peticion
        mockMvc.perform(delete("/api/videojuegos/1"))
        //Esperamos una respuesta
                .andExpect(status().isOk());
    }


}
