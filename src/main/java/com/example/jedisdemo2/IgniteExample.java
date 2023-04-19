package com.example.jedisdemo2;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;

public class IgniteExample {

    public static void main(String[] args) throws SQLException {


//        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
//
//        igniteConfiguration.setClientMode( true );
//
//        igniteConfiguration.setPeerClassLoadingEnabled( false );
//
//        TcpDiscoveryMulticastIpFinder ipFinder = new TcpDiscoveryMulticastIpFinder();
//        ipFinder.setAddresses( Collections.singletonList( "192.168.2.108" ) );
//
//        igniteConfiguration.setDiscoverySpi( new TcpDiscoverySpi().setIpFinder( ipFinder ) );
//
//        Ignite ignite = Ignition.start(igniteConfiguration);
//        IgniteCache cache1 = ignite.getOrCreateCache("armDisarmCommandRegistry");
//        System.out.println(cache1.get( 370 ));

//        Class.forName("org.apache.ignite.IgniteJdbcThinDriver");

        Connection conn = DriverManager.getConnection(
                "jdbc:ignite:thin://192.168.2.108/",
                "",
                ""
        );

        Statement stmt = conn.createStatement();

//        stmt.executeUpdate(
//        "CREATE TABLE City2 (id LONG PRIMARY KEY, name VARCHAR) WITH \"template=replicated\";"
//        );
        stmt.executeUpdate(
        "CREATE PROCEDURE xsum(num1 INT, num2 INT) RETURNS INT " +
                "AS $$ RETURN num1 + num2 $$ LANGUAGE SQL"
        );

        // upsert some data
        stmt.executeUpdate("INSERT INTO City2 (id, name) VALUES (1, 'Forest Hill')");


    }
}
