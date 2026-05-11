package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.fromzero.DTO.*;
import pe.edu.upc.fromzero.ServiceInterface.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/query")
public class QueryController {
    @Autowired
    private IEmpresasService EmpresasService;
    @Autowired
    private IProyectosService ProyectosService;
    @Autowired
    private IDesarrolladoresService DesarrolladoresService;
    @Autowired
    private INotificacionesService NotificacionesService;
    @Autowired
    private IRevisionesService RevisionesService;
    @Autowired
    private IUrgentProyectoService UrgentProyectoService;
    @Autowired
    private ICodigoGeneradoService codigoGeneradoService;

    @Autowired
    private IAiInteractionService AiInteractionsService;

    @Autowired
    private ITareasService TareasService;

    @GetMapping("/Query1")
    @PreAuthorize("hasAnyAuthority('Desarrollador', 'Administrador', 'Consultor')")
    public ResponseEntity<?> Query1(){
        List<Object[]> Query1 = EmpresasService.GetQuery1();
        if(Query1.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay datos");
        }
        List<Query1DTO> respuesta = new ArrayList<>();
        for(Object[] fila: Query1){
            Query1DTO dto = new Query1DTO();
            dto.setEmpresa((String) fila[0]);
            dto.setTotal_Proyectos(((Number) fila[1]).intValue());
            dto.setInversion_Total(((Number) fila[2]).doubleValue());
            dto.setTotal_Tareas_Asignadas(((Number) fila[3]).intValue());
            dto.setPresupuesto_Promedio(((Number) fila[4]).doubleValue());
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/Query2")
    @PreAuthorize("hasAnyAuthority('Desarrollador', 'Administrador', 'Consultor')")
    public ResponseEntity<?> Query2() {
        List<Object[]> Query2 = DesarrolladoresService.GetQuery2();

        if (Query2.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay datos");
        }

        List<Query2DTO> respuesta = new ArrayList<>();

        for (Object[] fila : Query2) {
            Query2DTO dto = new Query2DTO();
            dto.setDesarrollador((String) fila[0]);
            dto.setAños_Exp(fila[1] != null ? ((Number) fila[1]).intValue() : 0);
            dto.setSkills((String) fila[2]);
            dto.setProyectos_Participados(fila[3] != null ? ((Number) fila[3]).intValue() : 0);
            dto.setReputacion_Promedio(fila[4] != null ? ((Number) fila[4]).doubleValue() : 0.0);
            dto.setCantidad_Valoraciones(fila[5] != null ? ((Number) fila[5]).intValue() : 0);
            respuesta.add(dto);
        }

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/Query3")
    @PreAuthorize("hasAnyAuthority('Desarrollador', 'Administrador', 'Consultor')")
    public ResponseEntity<?> Query3() {
        List<Object[]> lista = ProyectosService.GetQuery3();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay proyectos activos");
        }
        List<Query3DTO> respuesta = new ArrayList<>();
        for (Object[] fila : lista) {
            Query3DTO dto = new Query3DTO();
            dto.setIdProyecto(((Number) fila[0]).intValue());
            dto.setTitulo((String) fila[1]);
            dto.setPresupuesto(((Number) fila[2]).doubleValue());
            dto.setNombreEmpresa((String) fila[3]);
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/Query4")
    @PreAuthorize("hasAnyAuthority('Desarrollador', 'Administrador', 'Consultor')")
    public ResponseEntity<?> Query4() {
        List<Object[]> lista = TareasService.GetQuery4();
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay tareas registradas");
        }
        List<Query4DTO> respuesta = new ArrayList<>();
        for (Object[] fila : lista) {
            Query4DTO dto = new Query4DTO();
            dto.setEstado((String) fila[0]);
            dto.setCantidad(((Number) fila[1]).intValue());
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/Query5")
    @PreAuthorize("hasAnyAuthority('Desarrollador', 'Administrador', 'Consultor')")
    public ResponseEntity<?> Query5() {
        List<Object[]> Query5 = NotificacionesService.GetQuery5();
        if (Query5.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay datos");
        }
        List<Query5DTO> respuesta = new ArrayList<>();
        for (Object[] fila : Query5) {
            Query5DTO dto = new Query5DTO();
            dto.setUsuario((String) fila[0]);
            dto.setMensaje((String) fila[1]);
            dto.setFecha(fila[2].toString());
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/Query6")
    @PreAuthorize("hasAnyAuthority('Desarrollador', 'Administrador', 'Consultor')")
    public ResponseEntity<?> Query6() {
        List<Object[]> Query6 = RevisionesService.GetQuery6();
        if (Query6.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay datos");
        }
        List<Query6DTO> respuesta = new ArrayList<>();
        for (Object[] fila : Query6) {
            Query6DTO dto = new Query6DTO();
            dto.setTarea((String) fila[0]);
            dto.setProyecto((String) fila[1]);
            dto.setEstado((String) fila[2]);
            dto.setComentario((String) fila[3]);
            dto.setFecha(fila[4].toString());
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/Query7")
    @PreAuthorize("hasAnyAuthority('Desarrollador', 'Administrador', 'Consultor')")
    public ResponseEntity<?> Query7(@RequestParam("developerId") Long developerId){
        List<Object[]> Query7 = UrgentProyectoService.GetQuery7(developerId);
        if(Query7.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay datos");
        }
        List<Query7DTO> respuesta = new ArrayList<>();
        for(Object[] fila: Query7){
            Query7DTO dto = new Query7DTO();
            dto.setProyecto((String) fila[0]);
            dto.setEmpresa((String) fila[1]);
            dto.setFechaEntrega(fila[2].toString());
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }
    @GetMapping("/Query8")
    @PreAuthorize("hasAnyAuthority('Administrador', 'Empresa', 'Gerente')")
    public ResponseEntity<?> Query8(){
        List<Object[]> Query8 = AiInteractionsService.GetQuery8();
        if(Query8.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay datos");
        }
        List<Query8DTO> respuesta = new ArrayList<>();
        for(Object[] fila: Query8){
            Query8DTO dto = new Query8DTO();
            dto.setProyecto((String) fila[0]);
            dto.setTotalInteracciones(((Number) fila[1]).intValue());
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/Query9")
    @PreAuthorize("hasAnyAuthority('Desarrollador', 'Administrador', 'Consultor')")
    public ResponseEntity<?> Query9(){
        List<Object[]> Query9 = codigoGeneradoService.getQuery9();
        if(Query9.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay datos");
        }
        List<Query9DTO> respuesta = new ArrayList<>();
        for(Object[] fila: Query9){
            Query9DTO dto = new Query9DTO();
            dto.setUsuario((String) fila[0]);
            dto.setLenguaje((String) fila[1]);
            dto.setTotalGeneraciones(((Number) fila[2]).intValue());
            dto.setUltimaGeneracion(fila[3] != null ? fila[3].toString() : null);
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/Query10")
    @PreAuthorize("hasAnyAuthority('Desarrollador', 'Administrador', 'Consultor')")
    public ResponseEntity<?> Query10(){
        List<Object[]> Query10 = ProyectosService.getQuery10();
        if(Query10.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay datos");
        }
        List<Query10DTO> respuesta = new ArrayList<>();
        for(Object[] fila: Query10){
            Query10DTO dto = new Query10DTO();
            dto.setTitulo((String) fila[0]);
            dto.setEmpresa((String) fila[1]);
            dto.setFechaFin(fila[2] != null ? fila[2].toString() : null);
            dto.setEstado((String) fila[3]);
            dto.setTareasPendientes(((Number) fila[4]).intValue());
            respuesta.add(dto);
        }
        return ResponseEntity.ok(respuesta);
    }
}
