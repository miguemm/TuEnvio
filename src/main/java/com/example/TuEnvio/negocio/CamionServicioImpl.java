package com.example.TuEnvio.negocio;

import com.example.TuEnvio.dominio.CamionDTO;
import com.example.TuEnvio.dominio.CargaDTO;
import com.example.TuEnvio.dominio.Respuesta;
import com.example.TuEnvio.excepciones.custom.CapacidadExcedidaExcepcion;
import com.example.TuEnvio.excepciones.custom.EntidadExisteExcepcion;
import com.example.TuEnvio.excepciones.custom.EntidadNoExisteExcepcion;
import com.example.TuEnvio.modelo.Camion;
import com.example.TuEnvio.repositorio.CamionRespositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Clase encargada de implementar la logica de negocios para las operaciones a realizar sobre el objeto de dominio de tipo Camion.
 */
@Service
public class CamionServicioImpl implements CamionServicio{

    @Autowired
    private CamionRespositorio camionRespositorio;

    @Autowired
    private ModelMapper camionMapper;

    /**
     * @see CamionServicio#createCamion(CamionDTO)
     */
    @Override
    @Transactional
    public Respuesta<Object> createCamion(CamionDTO nuevoCamion) {
        if(camionRespositorio.existsById(nuevoCamion.getPlaca()))
            throw new EntidadExisteExcepcion("Camion " + nuevoCamion.getPlaca() + " ya existe");

        Camion camion = camionMapper.map(nuevoCamion, Camion.class);
        camion.setEstadoCarga(0);
        camionRespositorio.save(camion);
        return Respuesta.builder()
                .mensajeDesarrollador(HttpStatus.CREATED.toString())
                .mensajeUsuario("Creacion Exitosa")
                .data(true)
                .build();
    }

    /**
     * @see CamionServicio#createCamion(CamionDTO)
     */
    @Override
    @Transactional
    public Respuesta<Object> cargaCamion(CargaDTO carga) {

        List<Camion> camiones = camionRespositorio.findBytipoProducto(carga.getTipoProducto());

        camiones = camiones.stream()
                .filter(x -> x.getCapacidad() > 0)
                .collect(Collectors.toList());

        if (camiones.isEmpty())
            throw new CapacidadExcedidaExcepcion("No hay camiones de este tipo: " + carga.getTipoProducto());

        Boolean bandera = false;
        List<Camion> potencial = new ArrayList<>();
        int restante = carga.getCantidad();

        for (Camion camion:camiones) {
            if (camion.getCapacidad() >= restante){
                camion.setCapacidad(camion.getCapacidad() - restante);
                potencial.add(camion);
                bandera = true;
                restante = 0;
                break;
            } else {
                restante = restante - camion.getCapacidad();

                camion.setCapacidad(0);
                potencial.add(camion);

            }
        }

        if (restante == 0){
            bandera = true;
            for (Camion camion:potencial) {
                camionRespositorio.save(camion);
            }
        }

        if (!bandera){
            throw new CapacidadExcedidaExcepcion("No hay espacio disponible en los camiones: " + carga.getTipoProducto());
        }

        return Respuesta.builder()
                .mensajeDesarrollador(HttpStatus.OK.toString())
                .mensajeUsuario("Camion cargado con el pedido")
                .data(camiones)
                .build();
    }


    /**
     * @see CamionServicio#deleteCamion(String)
     */
    @Override
    @Transactional
    public Respuesta<Object> deleteCamion(String placa) {
        if(!camionRespositorio.existsById(placa))
            throw new EntidadNoExisteExcepcion("Camion " + placa + " no existe");

        camionRespositorio.deleteById(placa);
        return Respuesta.builder()
                .mensajeDesarrollador(HttpStatus.OK.toString())
                .mensajeUsuario("Eliminacion Exitosa")
                .data(true)
                .build();
    }

    /**
     * @see CamionServicio#updateCamion(CamionDTO)
     */
    @Override
    @Transactional
    public Respuesta<Object> updateCamion(CamionDTO nuevoCamion) {
        if(!camionRespositorio.existsById(nuevoCamion.getPlaca()))
            throw new EntidadNoExisteExcepcion("Camion " + nuevoCamion.getPlaca() + " No existe");


        Camion camion = camionMapper.map(nuevoCamion, Camion.class);
        camion.setEstadoCarga(0);
        camionRespositorio.save(camion);
        return Respuesta.builder()
                .mensajeDesarrollador(HttpStatus.CREATED.toString())
                .mensajeUsuario("Actualizacion Exitosa")
                .data(true)
                .build();
    }

    /**
     * @see CamionServicio#getCamionByPlaca(String)
     */
    @Override
    @Transactional(readOnly = true)
    public Respuesta<Object> getCamionByPlaca(String placa) {
        if(!camionRespositorio.existsById(placa))
            throw new EntidadNoExisteExcepcion("Camion " + placa + " no existe");

        Optional<CamionDTO> camionDtoObtenido;
        Optional<Camion> camionOptenido = camionRespositorio.findById(placa);
        if (camionOptenido.isPresent())
            camionDtoObtenido = Optional.of(camionMapper.map(camionOptenido.get(), CamionDTO.class));
        else
            camionDtoObtenido = Optional.empty();

        return Respuesta.builder()
                .mensajeDesarrollador(HttpStatus.OK.toString())
                .mensajeUsuario("Busqueda Exitosa")
                .data(camionDtoObtenido)
                .build();
    }

    /**
     * @see CamionServicio#findAllByPlacaAsc(int, int)
     */
    @Override
    @Transactional(readOnly = true)
    public Respuesta<Object> findAllByPlacaAsc(int pagina, int tamanio) {
        Pageable pageable = PageRequest.of(pagina, tamanio, Sort.by("placa").ascending());
        Page<?> page = camionRespositorio.findAll(pageable);

        return Respuesta.builder()
                .mensajeDesarrollador(HttpStatus.OK.toString())
                .mensajeUsuario("Busqueda Exitosa")
                .data(page)
                .build();
    }
}
