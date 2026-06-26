package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.fromzero.DTO.NotificacionesDTO;
import pe.edu.upc.fromzero.Entities.Notificaciones;
import pe.edu.upc.fromzero.ServiceInterface.INotificacionesService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionesController {

    @Autowired
    private INotificacionesService NotificacionesService;

    /*CRUD------------------------------------*/

    @GetMapping("/Get")
    //@PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador', 'Empresa', 'Moderador', 'Soporte', 'Invitado', 'Tester', 'Analista', 'Gerente', 'Consultor')")
    public ResponseEntity<?> GetNotificaciones() {
        ModelMapper m = new ModelMapper();
        List<NotificacionesDTO> listaDTO = NotificacionesService.GetNotificacion().stream()
                .map(n -> m.map(n, NotificacionesDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/Get/{id}")
    public ResponseEntity<?> GetNotificacionById(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<Notificaciones> notificacion = NotificacionesService.GetNotificacionById(id);
        if (notificacion.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Notificación no encontrada");
        }
        NotificacionesDTO dto = m.map(notificacion.get(), NotificacionesDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/Post")
    //@PreAuthorize("hasAnyAuthority('Administrador', 'Moderador', 'Soporte')")
    public ResponseEntity<?> PostNotificaciones(@RequestBody NotificacionesDTO dto) {
        if (dto.getIdUser() == 0 || dto.getFecha() == null || dto.getMensaje() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ningún campo puede ser nulo");
        }
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La notificación no puede ser nula");
        }
        ModelMapper m = new ModelMapper();
        Notificaciones n = m.map(dto, Notificaciones.class);
        Notificaciones nueva = NotificacionesService.InsertNotificacion(n);
        NotificacionesDTO nuevaDTO = m.map(nueva, NotificacionesDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDTO);
    }

    @PutMapping("/Put")
    //@PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador', 'Empresa', 'Moderador', 'Soporte')")
    public ResponseEntity<?> PutNotificaciones(@RequestBody NotificacionesDTO dto) {
        if (dto.getIdUser() == 0 || dto.getFecha() == null || dto.getMensaje() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ningún campo puede ser nulo");
        }
        Optional<Notificaciones> existente = NotificacionesService.GetNotificacionById(dto.getIdNotification());

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La notificación no existe");
        }

        if (dto.getMensaje() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El mensaje de la notificación es obligatorio");
        }

        Notificaciones n = existente.get();

        n.setMensaje(dto.getMensaje());
        n.setLeido(dto.isLeido());
        n.setFecha(dto.getFecha());

        NotificacionesService.UpdateNotificacion(n);
        return ResponseEntity.ok("Notificación actualizada");
    }

    @DeleteMapping("/Delete/{IdNotification}")
    //@PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<?> DeleteNotificaciones(@PathVariable("IdNotification") int IdNotification) {
        Optional<Notificaciones> existente = NotificacionesService.GetNotificacionById(IdNotification);

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La notificación no existe");
        }

        NotificacionesService.DeleteNotificacion(IdNotification);
        return ResponseEntity.ok("Notificación eliminada");
    }
}