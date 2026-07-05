package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.fromzero.DTO.UsuariosDTO;
import pe.edu.upc.fromzero.DTO.UsuariosGetDTO;
import pe.edu.upc.fromzero.Entities.Usuarios;
import pe.edu.upc.fromzero.ServiceInterface.IRolesService;
import pe.edu.upc.fromzero.ServiceInterface.IUsuariosService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERADOR')")
public class UsuariosController {

    @Autowired
    private IUsuariosService UsuariosService;
    @Autowired
    private IRolesService RolesService;

    /*CRUD------------------------------------*/

    @GetMapping("/Get")
    public ResponseEntity<?> GetUsuarios() {
        ModelMapper m = new ModelMapper();
        List<UsuariosGetDTO> usuariosDTO = UsuariosService.GetUsuario().stream()
                .map(u -> m.map(u, UsuariosGetDTO.class))
                .collect(Collectors.toList());

        if (usuariosDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay usuarios registrados");
        }
        return ResponseEntity.ok(usuariosDTO);
    }

    @GetMapping("/Get/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> GetUsuarioById(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<Usuarios> usuario = UsuariosService.GetUsuarioById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
        UsuariosDTO dto = m.map(usuario.get(), UsuariosDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/Post")
    public ResponseEntity<?> PostUsuarios(@RequestBody UsuariosDTO dto) {
        if (dto.getNombre() == null || dto.getEmail() == null || dto.getPassword() == null || dto.getFechaRegistro() == null || dto.getIdRol() == 0 || dto.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ningún campo puede ser nulo");
        }
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no puede ser nulo");
        }
        ModelMapper m = new ModelMapper();
        Usuarios u = m.map(dto, Usuarios.class);
        Usuarios usuario = UsuariosService.InsertUsuario(u);
        UsuariosDTO usuarioDTO = m.map(usuario, UsuariosDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }

    @PutMapping("/Put")
    public ResponseEntity<?> PutUsuarios(@RequestBody UsuariosDTO dto) {
        if (dto.getNombre() == null || dto.getEmail() == null || dto.getPassword() == null || dto.getFechaRegistro() == null || dto.getIdRol() == 0 || dto.getUsername() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ningún campo puede ser nulo");
        }
        Optional<Usuarios> usuarioExistente = UsuariosService.GetUsuarioById(dto.getIdUser());

        if (usuarioExistente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe");
        }

        if (dto.getNombre() == null || dto.getEmail() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los datos del usuario no pueden ser nulos");
        }

        ModelMapper m = new ModelMapper();
        Usuarios u = usuarioExistente.get();
        u.setUsername(dto.getUsername());
        u.setNombre(dto.getNombre());
        u.setEmail(dto.getEmail());
        u.setPassword(dto.getPassword());
        u.setFechaRegistro(dto.getFechaRegistro());
        u.setIdRol(RolesService.GetRolById(dto.getIdRol()).get());
        UsuariosService.UpdateUsuario(u);
        return ResponseEntity.ok("Usuario actualizado");
    }

    @DeleteMapping("/Delete/{IdUser}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> DeleteUsuarios(@PathVariable("IdUser") int IdUser) {
        Optional<Usuarios> usuario = UsuariosService.GetUsuarioById(IdUser);

        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El usuario no existe");
        }

        UsuariosService.DeleteUsuario(IdUser);
        return ResponseEntity.ok("Usuario eliminado");
    }
}