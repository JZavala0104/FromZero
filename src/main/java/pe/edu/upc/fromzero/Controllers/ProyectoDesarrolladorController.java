package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.fromzero.DTO.ProyectoDesarrolladorDTO;
import pe.edu.upc.fromzero.Entities.ProyectoDesarrollador;
import pe.edu.upc.fromzero.Entities.Proyectos;
import pe.edu.upc.fromzero.Entities.Desarrolladores;
import pe.edu.upc.fromzero.ServiceInterface.IProyectoDesarrolladorService;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/api/proyectodesarrollador")
@PreAuthorize("hasAnyAuthority('ADMIN', 'OPERADOR', 'EMPRESARIO')")
public class ProyectoDesarrolladorController {

    @Autowired
    private IProyectoDesarrolladorService ProyectoDesarrolladorService;

    /*CRUD------------------------------------*/

    @GetMapping("/Get")
    public ResponseEntity<?> GetProyectoDesarrollador() {
        List<ProyectoDesarrolladorDTO> listaDTO = ProyectoDesarrolladorService.GetProyectoDesarrollador().stream()
                .map(pd -> {
                    ProyectoDesarrolladorDTO dto = new ProyectoDesarrolladorDTO();
                    dto.setIdProyDesar(pd.getIdProyDesar());
                    dto.setIdProyecto(pd.getIdProyecto() != null ? pd.getIdProyecto().getIdProject() : 0);
                    dto.setIdDesarrollador(pd.getIdDesarrollador() != null ? pd.getIdDesarrollador().getIdDesarrollador() : 0);
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaDTO);
    }

    @GetMapping("/Get/{id}")
    public ResponseEntity<?> GetProyectoDesarrolladorById(@PathVariable int id) {
        Optional<ProyectoDesarrollador> pdOpt = ProyectoDesarrolladorService.GetProyectoDesarrolladorById(id);
        if (pdOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Asignación no encontrada");
        }
        ProyectoDesarrollador pd = pdOpt.get();
        ProyectoDesarrolladorDTO dto = new ProyectoDesarrolladorDTO();
        dto.setIdProyDesar(pd.getIdProyDesar());
        dto.setIdProyecto(pd.getIdProyecto() != null ? pd.getIdProyecto().getIdProject() : 0);
        dto.setIdDesarrollador(pd.getIdDesarrollador() != null ? pd.getIdDesarrollador().getIdDesarrollador() : 0);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/Post")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERADOR', 'EMPRESARIO')")
    public ResponseEntity<?> PostProyectoDesarrollador(@RequestBody ProyectoDesarrolladorDTO dto) {
        if (dto.getIdDesarrollador() == 0 || dto.getIdProyecto() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ningún campo puede ser nulo");
        }
        if (dto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La asignación no puede ser nula");
        }
        ProyectoDesarrollador pd = new ProyectoDesarrollador();
        pd.setIdProyDesar(dto.getIdProyDesar());

        Proyectos p = new Proyectos();
        p.setIdProject(dto.getIdProyecto());
        pd.setIdProyecto(p);

        Desarrolladores d = new Desarrolladores();
        d.setIdDesarrollador(dto.getIdDesarrollador());
        pd.setIdDesarrollador(d);

        ProyectoDesarrollador nuevo = ProyectoDesarrolladorService.InsertProyectoDesarrollador(pd);

        ProyectoDesarrolladorDTO nuevoDTO = new ProyectoDesarrolladorDTO();
        nuevoDTO.setIdProyDesar(nuevo.getIdProyDesar());
        nuevoDTO.setIdProyecto(nuevo.getIdProyecto() != null ? nuevo.getIdProyecto().getIdProject() : 0);
        nuevoDTO.setIdDesarrollador(nuevo.getIdDesarrollador() != null ? nuevo.getIdDesarrollador().getIdDesarrollador() : 0);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDTO);
    }

    @PutMapping("/Put")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERADOR')")
    public ResponseEntity<?> PutProyectoDesarrollador(@RequestBody ProyectoDesarrolladorDTO dto) {
        if (dto.getIdDesarrollador() == 0 || dto.getIdProyecto() == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ningún campo puede ser nulo");
        }
        Optional<ProyectoDesarrollador> existente = ProyectoDesarrolladorService.GetProyectoDesarrolladorById(dto.getIdProyDesar());

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La asignación no existe");
        }

        ProyectoDesarrollador pd = existente.get();

        Proyectos p = new Proyectos();
        p.setIdProject(dto.getIdProyecto());
        pd.setIdProyecto(p);

        Desarrolladores d = new Desarrolladores();
        d.setIdDesarrollador(dto.getIdDesarrollador());
        pd.setIdDesarrollador(d);

        ProyectoDesarrolladorService.UpdateProyectoDesarrollador(pd);
        return ResponseEntity.ok("Asignación actualizada correctamente");
    }

    @DeleteMapping("/Delete/{IdProyDesar}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERADOR')")
    public ResponseEntity<?> DeleteProyectoDesarrollador(@PathVariable("IdProyDesar") int IdProyDesar) {
        Optional<ProyectoDesarrollador> existente = ProyectoDesarrolladorService.GetProyectoDesarrolladorById(IdProyDesar);

        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La asignación no existe");
        }

        ProyectoDesarrolladorService.DeleteProyectoDesarrollador(IdProyDesar);
        return ResponseEntity.ok("Asignación eliminada");
    }
}