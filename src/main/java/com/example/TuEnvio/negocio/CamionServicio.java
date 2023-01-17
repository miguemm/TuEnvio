package com.example.TuEnvio.negocio;

import com.example.TuEnvio.dominio.CamionDTO;
import com.example.TuEnvio.dominio.Respuesta;

/**
 * Interfaz que define los metodos necesario para la logica de la clase Camion
 */
public interface CamionServicio {
    /**
     * Metodo que permite la creacion de camiones
     *
     * @param nuevoCamion
     *      {@link CamionDTO} Objeto con la información a insertar, recibido en el cuerpo de la petición al servicio Rest
     * @return {@link Respuesta} Objeto de respuesta para el servicio, el cual contiene un boolean para indicar el estado de la transaccion
     */
    Respuesta<Object> createCamion(CamionDTO nuevoCamion);

    /**
     * Metodo que permite eliminar un Camion
     *
     * @param placa
     *            Placa del camion a eliminar
     * @return {@link Respuesta} Objeto de respuesta para el servicio, el cual contiene la informacion sobre el resultado de la transaccion
     */
    Respuesta<Object> deleteCamion(String placa);

    /**
     * Metodo que permite la actualizacion de la informacion basica de un Camion
     * @param nuevoCamion
     *      {@link CamionDTO} Objeto con la información a actualizar, recibido en el cuerpo de la petición al servicio Rest
     * @return {@link Respuesta} Objeto de respuesta para el servicio, el cual contiene la informacion sobre el resultado de la transaccion
     */
    Respuesta<Object> updateCamion(CamionDTO nuevoCamion);

    /**
     * Metodo que permite obtener la informacion de un Camion
     *
     * @param placa
     *            Placa del camion a consultar
     * @return {@link Respuesta} Objeto de respuesta para el servicio, el cual contiene la informacion sobre el resultado de la transaccion
     */
    Respuesta<Object> getCamionByPlaca(String placa);

    /**
     * Metodo que permite obtener un listado de los Camiones almacenados
     *
     * @param pagina numero de pagina del listado a obtener
     * @param tamanio tamanio del listado a obtener
     * @return {@link Respuesta} Objeto de respuesta para el servicio, el cual contiene la informacion sobre el resultado de la transaccion
     */
    Respuesta<Object> findAllByPlacaAsc(int pagina, int tamanio);
}
