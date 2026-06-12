package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.fromzero.DTO.RolesDTO;
import pe.edu.upc.fromzero.Entities.Roles;
import pe.edu.upc.fromzero.ServiceInterface.IRolesService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolesController {
    @Autowired
    private IRolesService RolesService;
    /*CRUD------------------------------------*/
    @GetMapping("/Get")
    //@PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<?> GetRoles(){
        ModelMapper m = new ModelMapper();
        List<RolesDTO> rolesDTO = RolesService.GetRol().stream().map(r -> m.map(r, RolesDTO.class)).collect(Collectors.toList());
        if(rolesDTO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay roles registrados");
        }
        return ResponseEntity.ok(rolesDTO);
    }

    @GetMapping("/Get/{id}")
    public ResponseEntity<?> GetRolById(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<Roles> rol = RolesService.GetRolById(id);
        if (rol.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rol no encontrado");
        }
        RolesDTO dto = m.map(rol.get(), RolesDTO.class);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/Post")
    //@PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<?> PostRoles(@RequestBody RolesDTO dto){
        if (dto.getNombre() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre del rol no puede ser nulo");
        }
        if (dto == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El rol no puede ser nulo");
        }
        ModelMapper m = new ModelMapper();
        Roles r = m.map(dto, Roles.class);
        Roles rol = RolesService.InsertRol(r);
        RolesDTO rolDTO = m.map(rol, RolesDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(rolDTO);
    }
    @PutMapping("/Put")
    //@PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<?> PutRoles(@RequestBody RolesDTO dto){
        if (dto.getNombre() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre del rol no puede ser nulo");
        }
        Optional<Roles> rol = RolesService.GetRolById(dto.getIdRol());
        if (rol.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El rol no existe");
        }
        if (dto.getNombre() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre del rol no puede ser nulo");
        }
        Roles r = rol.get();
        r.setNombre(dto.getNombre());
        RolesService.UpdateRol(r);
        return ResponseEntity.ok("Rol actualizado");
    }
    @DeleteMapping("/Delete/{IdRol}")
    //@PreAuthorize("hasAuthority('Administrador')")
    public ResponseEntity<?> DeleteRoles(@PathVariable("IdRol") int IdRol){
        Optional<Roles> rol = RolesService.GetRolById(IdRol);
        if (rol.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El rol no existe");
        }
        RolesService.DeleteRol(IdRol);
        return ResponseEntity.ok("Rol eliminado");
    }
}
