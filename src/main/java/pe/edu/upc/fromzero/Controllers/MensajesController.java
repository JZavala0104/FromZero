package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.fromzero.DTO.MensajesDTO;
import pe.edu.upc.fromzero.Entities.Mensajes;
import pe.edu.upc.fromzero.Entities.Proyectos;
import pe.edu.upc.fromzero.Entities.Usuarios;
import pe.edu.upc.fromzero.ServiceInterface.IMensajesService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/mensajes")
public class MensajesController {

    @Autowired
    private IMensajesService MensajesService;

    /*CRUD------------------------------------*/

    @GetMapping("/Get")
    //@PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador', 'Empresa', 'Moderador', 'Soporte')")
    public ResponseEntity<?> GetMensajes() {
        List<MensajesDTO> listaDTO = MensajesService.GetMensaje().stream()
                .map(msg -> {
                    MensajesDTO dto = new MensajesDTO();
                    dto.setIdMensaje(msg.getIdMensaje());
                    dto.setMensaje(msg.getMensaje());
                    dto.setFecha(msg.getFecha());
                    dto.setIdProyecto(msg.getIdProyecto() != null ? msg.getIdProyecto().getIdProject() : 0);
                    dto.setIdUser(msg.getIdUser() != null ? msg.getIdUser().getIdUser() : 0);
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/Get/{id}")
    public ResponseEntity<?> GetMensajeById(@PathVariable int id) {
        Optional<Mensajes> mensaje = MensajesService.GetMensajeById(id);
        if (mensaje.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mensaje no encontrado");
        }
        Mensajes msg = mensaje.get();
        MensajesDTO dto = new MensajesDTO();
        dto.setIdMensaje(msg.getIdMensaje());
        dto.setMensaje(msg.getMensaje());
        dto.setFecha(msg.getFecha());
        dto.setIdProyecto(msg.getIdProyecto() != null ? msg.getIdProyecto().getIdProject() : 0);
        dto.setIdUser(msg.getIdUser() != null ? msg.getIdUser().getIdUser() : 0);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/GetByProyecto/{idProyecto}")
    public ResponseEntity<?> GetMensajesByProyecto(@PathVariable int idProyecto) {
        List<MensajesDTO> listaDTO = MensajesService.GetMensajesByProyecto(idProyecto).stream()
                .map(msg -> {
                    MensajesDTO dto = new MensajesDTO();
                    dto.setIdMensaje(msg.getIdMensaje());
                    dto.setMensaje(msg.getMensaje());
                    dto.setFecha(msg.getFecha());
                    dto.setIdProyecto(msg.getIdProyecto() != null ? msg.getIdProyecto().getIdProject() : 0);
                    dto.setIdUser(msg.getIdUser() != null ? msg.getIdUser().getIdUser() : 0);
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }

    @PostMapping("/Post")
    //@PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador', 'Empresa', 'Soporte')")
    public ResponseEntity<?> PostMensajes(@RequestBody MensajesDTO dto) {
        if (dto.getIdUser() == 0 || dto.getIdProyecto() == 0 || dto.getMensaje() == null || dto.getFecha() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ningún campo puede ser nulo");
        }
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El mensaje no puede ser nulo");
        }
        Mensajes msg = new Mensajes();
        msg.setIdMensaje(dto.getIdMensaje());
        msg.setMensaje(dto.getMensaje());
        msg.setFecha(dto.getFecha());

        Proyectos p = new Proyectos();
        p.setIdProject(dto.getIdProyecto());
        msg.setIdProyecto(p);

        Usuarios u = new Usuarios();
        u.setIdUser(dto.getIdUser());
        msg.setIdUser(u);

        Mensajes nuevo = MensajesService.InsertMensaje(msg);

        MensajesDTO nuevoDTO = new MensajesDTO();
        nuevoDTO.setIdMensaje(nuevo.getIdMensaje());
        nuevoDTO.setMensaje(nuevo.getMensaje());
        nuevoDTO.setFecha(nuevo.getFecha());
        nuevoDTO.setIdProyecto(nuevo.getIdProyecto() != null ? nuevo.getIdProyecto().getIdProject() : 0);
        nuevoDTO.setIdUser(nuevo.getIdUser() != null ? nuevo.getIdUser().getIdUser() : 0);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDTO);
    }

    @PutMapping("/Put")
    //@PreAuthorize("hasAnyAuthority('Administrador', 'Desarrollador', 'Empresa', 'Moderador')")
    public ResponseEntity<?> PutMensajes(@RequestBody MensajesDTO dto) {
        if (dto.getIdUser() == 0 || dto.getIdProyecto() == 0 || dto.getMensaje() == null || dto.getFecha() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ningún campo puede ser nulo");
        }
        Optional<Mensajes> existente = MensajesService.GetMensajeById(dto.getIdMensaje());

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El mensaje no existe");
        }

        if (dto.getMensaje() == null || dto.getMensaje().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El contenido del mensaje es obligatorio");
        }

        Mensajes msg = existente.get();
        msg.setMensaje(dto.getMensaje());
        msg.setFecha(dto.getFecha());

        Proyectos p = new Proyectos();
        p.setIdProject(dto.getIdProyecto());
        msg.setIdProyecto(p);

        Usuarios u = new Usuarios();
        u.setIdUser(dto.getIdUser());
        msg.setIdUser(u);

        MensajesService.UpdateMensaje(msg);
        return ResponseEntity.ok("Mensaje actualizado");
    }

    @DeleteMapping("/Delete/{IdMensaje}")
    //@PreAuthorize("hasAnyAuthority('Administrador', 'Moderador')")
    public ResponseEntity<?> DeleteMensajes(@PathVariable("IdMensaje") int IdMensaje) {
        Optional<Mensajes> existente = MensajesService.GetMensajeById(IdMensaje);

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El mensaje no existe");
        }

        MensajesService.DeleteMensaje(IdMensaje);
        return ResponseEntity.ok("Mensaje eliminado");
    }
}