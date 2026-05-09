package pe.edu.upc.fromzero.Controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/Query1")
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
}
