/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmeans.fx;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.sql.*;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import java.io.*;

/**
 *
 * @author esau
 */

public class KMEANSFX extends Application {

    /*Ya que extraemos datos literales de la base de datos, usamos el 
    siguiente metodo para cuantificarlos y poder usarlos para el calculo de la distancia Euclidiana*/
    public static int Cuantificacion(String estado) {
        int valor = 0;
        switch (estado) {
            case "CDMX":
                valor = 1;
                break;
            case "EDOMEX":
                valor = 2;
                break;
            case "Morelos":
                valor = 3;
                break;
            case "Puebla":
                valor = 4;
                break;
            case "Tabasco":
                valor = 5;
                break;
            case "Michoacan":
                valor = 6;
                break;
            default:
                valor = 0;
                break;
        }

        return valor;
    }

    public static int Kmeans(int arr[][], int arr2[][]) {
        /*las variables C son los centroides que vamos a usar, 4 por cada centroide
        un total de 3 Centroides (osea 3 clusters)*/
        int i, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, n = 0; 
        /*Se compararan las variables P con los C
        */
        int p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12 = 0;
        float calculo1, calculo2, calculo3 = 0;
        boolean flag;
        float sum1, sum2, sum3, sum4, sum5, sum6, sum7, sum8, sum9, sum10, sum11, sum12;
        /*Tomamos centroides "aleatoriamente"*/
        /*Centroide 1*/
        p1 = arr[0][15];
        p2 = arr[1][15];
        p3 = arr2[0][15];
        p4 = arr2[1][15];
        /*Centroide 2*/
        p5 = arr[0][85];
        p6 = arr[1][85];
        p7 = arr2[0][85];
        p8 = arr2[1][85];
        /*Centroide 3*/
        p9 = arr[0][170];
        p10 = arr[1][170];
        p11 = arr2[0][170];
        p12 = arr2[1][170];
        int k = 0, j = 0, q = 0;
        /*Creamos registros auxiliares para ir organizando los valores en los clusters*/
        int cluster11[][] = new int[2][arr[1].length];
        int cluster12[][] = new int[2][arr[1].length];
        int cluster21[][] = new int[2][arr[1].length];
        int cluster22[][] = new int[2][arr[1].length];
        int cluster31[][] = new int[2][arr[1].length];
        int cluster32[][] = new int[2][arr[1].length];
        /*Registros auxiliares para ir guardando los calculos 
        (distancias euclidianas) de cada punto con respecto a los centroides actuales*/
        float registro_calculos1[][] = new float[3][arr[1].length];
        float registro_calculos2[][] = new float[3][arr[1].length];
        float registro_calculos3[][] = new float[3][arr[1].length];

        do {
            sum1 = 0;
            sum2 = 0;
            sum3 = 0;
            sum4 = 0;
            sum5 = 0;
            sum6 = 0;
            sum7 = 0;
            sum8 = 0;
            sum9 = 0;
            sum10 = 0;
            sum11 = 0;
            sum12 = 0;
            n++;
            k = 0;
            j = 0;
            q = 0;
            /*Comparar cada valor obtenido de la base de datos con los centroides actuales*/
            for (i = 0; i < arr[1].length; i++) {
                /*Distancia a cada centroide respectivamente*/
                calculo1 = (float) Math.sqrt(Math.pow((arr[0][i] - p1), 2) + Math.pow((arr[1][i] - p2), 2)
                        + Math.pow((arr2[0][i] - p3), 2) + Math.pow((arr2[1][i] - p4), 2));
                calculo2 = (float) Math.sqrt(Math.pow((arr[0][i] - p5), 2) + Math.pow((arr[1][i] - p6), 2)
                        + Math.pow((arr2[0][i] - p7), 2) + Math.pow((arr2[1][i] - p8), 2));
                calculo3 = (float) Math.sqrt(Math.pow((arr[0][i] - p9), 2) + Math.pow((arr[1][i] - p10), 2)
                        + Math.pow((arr2[0][i] - p11), 2) + Math.pow((arr2[1][i] - p12), 2));
                /*Condiciones para definir en que cluster queda el registro
                anteriormente comparado*/
                if (calculo1 <= calculo2 && calculo1 <= calculo3) {
                    cluster11[0][k] = arr[0][i];
                    cluster11[1][k] = arr[1][i];
                    cluster12[0][k] = arr2[0][i];
                    cluster12[1][k] = arr2[1][i];
                    registro_calculos1[0][k] = calculo1;
                    registro_calculos1[1][k] = calculo2;
                    registro_calculos1[2][k] = calculo3;
                    k++;
                } else if (calculo2 <= calculo3 && calculo2 <= calculo1) {
                    cluster21[0][j] = arr[0][i];
                    cluster21[1][j] = arr[1][i];
                    cluster22[0][j] = arr2[0][i];
                    cluster22[1][j] = arr2[1][i];
                    registro_calculos2[0][j] = calculo1;
                    registro_calculos2[1][j] = calculo2;
                    registro_calculos2[2][j] = calculo3;
                    j++;
                } else {
                    cluster31[0][q] = arr[0][i];
                    cluster31[1][q] = arr[1][i];
                    cluster32[0][q] = arr2[0][i];
                    cluster32[1][q] = arr2[1][i];
                    registro_calculos3[0][q] = calculo1;
                    registro_calculos3[1][q] = calculo2;
                    registro_calculos3[2][q] = calculo3;
                    q++;
                }
            }
            /*las variables sum son utilizadas para despues
            obtener los nuevos centroines mediante el promedio (media aritmetica)*/
            for (i = 0; i < k; i++) {
                sum1 = sum1 + cluster11[0][i];
                sum2 = sum2 + cluster11[1][i];
                sum3 = sum3 + cluster12[0][i];
                sum4 = sum4 + cluster12[1][i];
            }
            for (i = 0; i < j; i++) {
                sum5 = sum5 + cluster21[0][i];
                sum6 = sum6 + cluster21[1][i];
                sum7 = sum7 + cluster22[0][i];
                sum8 = sum8 + cluster22[1][i];
            }
            for (i = 0; i < q; i++) {
                sum9 = sum9 + cluster31[0][i];
                sum10 = sum10 + cluster31[1][i];
                sum11 = sum11 + cluster32[0][i];
                sum12 = sum12 + cluster32[1][i];
            }
            //printing Centroids/Means\

            System.out.println("Centroide 1=(" + p1 + "," + p2 + "," + p3 + "," + p4
                    + ")   Centroide 2=(" + p5 + "," + p6 + "," + p7 + "," + p8
                    + ")   Centroide 3=(" + p9 + "," + p10 + "," + p11 + "," + p12 + ")");
            /*Asignamos a las c's los "centroides anteriores" (n-1)*/
            c1 = p1;
            c2 = p2;
            c3 = p3;
            c4 = p4;
            c5 = p5;
            c6 = p6;
            c7 = p7;
            c8 = p8;
            c9 = p9;
            c10 = p10;
            c11 = p11;
            c12 = p12;
            /*Actualizamos los centroides (n)*/
            p1 = Math.round(sum1 / k);
            p2 = Math.round(sum2 / k);
            p3 = Math.round(sum3 / k);
            p4 = Math.round(sum4 / k);
            p5 = Math.round(sum5 / j);
            p6 = Math.round(sum6 / j);
            p7 = Math.round(sum7 / j);
            p8 = Math.round(sum8 / j);
            p9 = Math.round(sum9 / q);
            p10 = Math.round(sum10 / q);
            p11 = Math.round(sum11 / q);
            p12 = Math.round(sum12 / q);
            /*Mientras los centroides (n-1) y los centroides (n)
            sean diferentes, el ciclo continua*/
            flag = !(p1 == c1 && p2 == c2 && p3 == c3 && p4 == c4 && p5 == c5 && p6 == c6 && p7 == c7 && p8 == c8
                    && p9 == c9 && p10 == c10 && p11 == c11 && p12 == c12);
            /*Imprimimos como se van acomodando los valores en los clusters*/
            System.out.println("Despues de la iteracion " + n + " , cluster 1 :\n");    //printing the clusters of each iteration
            for (i = 0; i < cluster11[1].length; i++) {
                System.out.print(cluster11[0][i] + " | " + cluster11[1][i] + " | " + cluster12[0][i] + " | "
                        + cluster12[1][i] + "   Distancia a Centroide 1: " + registro_calculos1[0][i]
                        + "   Distancia a Centroide 2: " + registro_calculos1[1][i]
                        + "   Distancia a Centroide 3: " + registro_calculos1[2][i] + "\n");
                registro_calculos1[0][i] = 0;
                registro_calculos1[1][i] = 0;
                registro_calculos1[2][i] = 0;
            }

            System.out.println("\n");
            System.out.println("Despues de la iteracion " + n + " , cluster 2 :\n");
            for (i = 0; i < cluster21[1].length; i++) {
                System.out.print(cluster21[0][i] + " | " + cluster21[1][i] + " | " + cluster22[0][i] + " | "
                        + cluster22[1][i] + "   Distancia a Centroide 1: " + registro_calculos2[0][i]
                        + "   Distancia a Centroide 2: " + registro_calculos2[1][i]
                        + "   Distancia a Centroide 3: " + registro_calculos2[2][i] + "\n");
                registro_calculos2[0][i] = 0;
                registro_calculos2[1][i] = 0;
                registro_calculos2[2][i] = 0;
            }

            System.out.println("\n");
            System.out.println("Despues de la iteracion " + n + " , cluster 3 :\n");
            for (i = 0; i < cluster31[1].length; i++) {
                System.out.print(cluster31[0][i] + " | " + cluster31[1][i] + " | " + cluster32[0][i] + " | "
                        + cluster32[1][i] + "   Distancia a Centroide 1: " + registro_calculos3[0][i]
                        + "   Distancia a Centroide 2: " + registro_calculos3[1][i]
                        + "   Distancia a Centroide 3: " + registro_calculos3[2][i] + "\n");
                registro_calculos3[0][i] = 0;
                registro_calculos3[1][i] = 0;
                registro_calculos3[2][i] = 0;
            }
            System.out.println("Objetos en cluster 1:" + k);
            System.out.println("Objetos en cluster 2:" + j);
            System.out.println("Objetos en cluster 3:" + q);

        } while (flag); 
        /*Terminado el ciclo, donde el metodo ha convergido 
        y ya no haya cambios en los clusters imprimimos los clusters finales*/
        i = 0;

        System.out.println("Centroide 1=(" + p1 + "," + p2 + "," + p3 + "," + p4
                + ")   Centroide 2=(" + p5 + "," + p6 + "," + p7 + "," + p8
                + ")   Centroide 3=(" + p9 + "," + p10 + "," + p11 + "," + p12 + ")");
        System.out.println("Final cluster 1 :\n");            // final clusters
        System.out.println("Marca|Tipo de Falta|Estado|Edad");
        do {
            System.out.print(cluster11[0][i] + "   |      " + cluster11[1][i] + "      |   " + cluster12[0][i] + "   |   " + cluster12[1][i] + "\n");
            i++;
        } while (cluster12[1][i] != 0);
        i = 0;
        System.out.println();
        System.out.println("Final cluster 2 :\n");
        System.out.println("Marca|Tipo de Falta|Estado|Edad");
        do {
            System.out.print(cluster21[0][i] + "   |      " + cluster21[1][i] + "      |   " + cluster22[0][i] + "   |   " + cluster22[1][i] + "\n");
            i++;
        } while (cluster22[1][i] != 0);
        i = 0;
        System.out.println();
        System.out.println("Final cluster 3 :\n");
        System.out.println("Marca|Tipo de Falta|Estado|Edad");
        do {

            System.out.print(cluster31[0][i] + "   |      " + cluster31[1][i] + "      |   " + cluster32[0][i] + "   |   " + cluster32[1][i] + "\n");
            i++;
        } while (cluster32[1][i] != 0);
        return 0;
    }

    @Override
    public void start(Stage stage) throws SQLException {
        //Esta parte no es muy importante, simplemente es tratar de graficar los datos con JAVAFX
        stage.setTitle("K-Means");
        final NumberAxis xAxis = new NumberAxis(0, 4, 1);
        final NumberAxis yAxis = new NumberAxis(0, 30, 1);
        final NumberAxis xAxis2 = new NumberAxis(16, 101, 1);
        final NumberAxis yAxis2 = new NumberAxis(0, 7, 1);
        final ScatterChart<Number, Number> sc = new ScatterChart<Number, Number>(xAxis, yAxis);
        final ScatterChart<Number, Number> sc2 = new ScatterChart<Number, Number>(xAxis2, yAxis2);
        xAxis.setLabel("Tipo Falta");
        yAxis.setLabel("Marca");
        sc.setTitle("Marca-Tipo de Falta");
        xAxis2.setLabel("Edad");
        yAxis2.setLabel("Estado");
        sc2.setTitle("Edad-Estado");

        //Ejecucion del Query (Una vista minable)
        ConexionSQL SQL = new ConexionSQL();
        Connection conn = SQL.conectarMySQL();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM infracciones.Vista_Proyecto1;");
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        int total = 0;
        //Saber cuantos registros obtenemos desde la base de datos
        boolean ultimo = rs.last();
        if (ultimo) {
            total = rs.getRow();
        }
        /*Como obtenemos 4 campos de la base de datos, lo dividimos en 2 
        registros de 2x(El total de registros obtenidos)*/
        int[][] arreglo = new int[2][total];
        int[][] arreglo2 = new int[2][total];
        
        
        int i = 1;//1 por facilidad para recorrer los arreglos
        rs.first();
        //almacenamos los primeros 2 campos en el arreglo1
        arreglo[0][0] = rs.getInt(1);
        arreglo[1][0] = rs.getInt(2);
        String estado;
        estado = rs.getString(3);
        //almacenamos el 3er (Estado) y cuarto campo en el arreglo2
        arreglo2[0][0] = Cuantificacion(estado);
        arreglo2[1][0] = rs.getInt(4);
        //lo anterior para el primer registro, para todos los demas registros usamos el siguiente ciclo
        while (rs.next()) {
            arreglo[0][i] = rs.getInt(1);
            arreglo[1][i] = rs.getInt(2);
            estado = rs.getString(3);
            arreglo2[0][i] = Cuantificacion(estado);
            arreglo2[1][i] = rs.getInt(4);
            i++;
        }
        //cerramos la conexion con la base de datos
        conn.close();
        //Metemos los arreglos obtenidos en nuestro metodo KMeans
        Kmeans(arreglo, arreglo2);
        //Graficamos
        for (int j = 0; j < total; j++) {
            series1.getData().add(new XYChart.Data(arreglo[1][j], arreglo[0][j]));
            series2.getData().add(new XYChart.Data(arreglo2[1][j], arreglo2[0][j]));
        }
        series1.setName("Registros");
        series1.setNode(yAxis);
        series2.setName("Registros");
        series2.setNode(yAxis);
        sc.getData().addAll(series1);
        sc2.getData().addAll(series2);
        HBox hbox = new HBox(sc, sc2);
        Scene scene = new Scene(hbox, 1000, 600);
        scene.getStylesheets().add(getClass().getResource("resources/css/Chart.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
