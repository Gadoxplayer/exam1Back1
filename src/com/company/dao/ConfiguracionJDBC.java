package com.company.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfiguracionJDBC {


    final static Logger log = Logger.getLogger(ConfiguracionJDBC.class);

    private String jdbcDriver;
    private String dbUrl;
    private String nombreUsuario;
    private String contrasena;

    public ConfiguracionJDBC(String jdbcDriver, String dbUrl, String nombreUsuario, String contrasena) {
        this.jdbcDriver = jdbcDriver;
        this.dbUrl = dbUrl;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }

    public ConfiguracionJDBC() {
        this.jdbcDriver = "org.h2.Driver";
        this.dbUrl = "jdbc:h2:tcp://localhost/~/nude1";
        this.nombreUsuario = "";
        this.contrasena = "";
    }

     public Connection conectarConBaseDeDatos() {
        Connection connection = null;
        try {
            log.debug("Se realizo la conexion exitosa a tu base de datos");
            connection = DriverManager.getConnection(dbUrl, nombreUsuario, contrasena);
        } catch (SQLException throwables) {
            log.error("No se logro la conexion a la base de datos");
            throwables.printStackTrace();
        }


        return connection;
    }
}
