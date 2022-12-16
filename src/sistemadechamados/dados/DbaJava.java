/*
 * Copyright (C) 2022  Diego Soares da Silva.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sistemadechamados.dados;

import java.sql.*; //Biblioteca Mysql
/**
 * Conexão com o Banco de Dados
 * @author Diego Soares da Silva
 * @version 1.0
 */
public class DbaJava { //classe de comunicação Banco/Java: Mysqlconnector

    /**
     * Metodo conexão Banco de dados/Java
     *
     * @return con
     */
    public static Connection ligacao() { //Metodo de conexão Banco/Java
        java.sql.Connection con = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/bdsistema?characterEncoding=utf-8";
        String user = "ireportuser";
        String password = "user";

        try {
            Class.forName(driver); //executar driver 
            con = DriverManager.getConnection(url, user, password); //variável con recebe a string de conexão, ao qual recebe o banco de dados
            return con; //tratamento de exceção - //Se Retornar uma string de conexão, banco ok

        } catch (Exception f) {
            return null; //caso ocorra uma exceção: o Banco de dados está offline, retornar NULO
        }

    }
}
