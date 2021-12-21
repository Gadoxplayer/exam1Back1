package com.company.dao;

import com.company.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    final static Logger log = Logger.getLogger(OdontologoDaoH2.class);

    private ConfiguracionJDBC configuracionJDBC;

    public OdontologoDaoH2(ConfiguracionJDBC configuracionJDBC) {
        this.configuracionJDBC = configuracionJDBC;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {

        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement statement = null;
        String QUERY_BUSCAR = String.format("INSERT INTO odontologos(numMatricula, nombre, apellido) VALUES('%s','%s','%s')", odontologo.getNumMatricula(), odontologo.getNombre(), odontologo.getApellido());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(QUERY_BUSCAR, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next())
                odontologo.setId(keys.getInt(1));
            statement.close();
            connection.close();
            log.debug("Registrando un odontologo con id: " + odontologo.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error al ejecutar sentencia SQL: " + e);
        }
        return odontologo;
    }

    @Override
    public List<Odontologo> listar() {
        log.debug("Se genera lista de odontologos registrados en la base de datos");
        Connection connection = configuracionJDBC.conectarConBaseDeDatos();
        Statement statement = null;
        String QUERY_LIST = String.format("SELECT * FROM odontologos");
        List<Odontologo> odontologos = new ArrayList();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_LIST);
            while (resultSet.next()) {
                odontologos.add(new Odontologo(resultSet.getInt("id"), resultSet.getInt("numMatricula"),resultSet.getString("nombre"), resultSet.getString("apellido")));
                log.debug("Listando odontologos de base de datos: " + odontologos);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error al ejecutar sentencia SQL: " + e);
        }
        return odontologos;
    }
}
