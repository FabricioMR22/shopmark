package com.example.shopmark.base;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.shopmark.Modelo.ClienteModelo;
import com.example.shopmark.Modelo.ProductoModelo;
import com.example.shopmark.cliente.register_MainActivity;

import java.util.ArrayList;
import java.util.List;

public class TiendaDB extends SQLiteOpenHelper {
    private static final String NOMBRE_BD = "tienda.bd";
    private static final int VERSION_BD =1 ;

//    private static final String TABLA_CLIENTES = "CREATE TABLE CLIENTES(CODIGO VARCHAR (40) PRIMARY KEY,DNI VARCHAR (40) NOT NULL,NOMBRE VARCHAR (40) NOT NULL, APELLIDO VARCHAR (40) NOT NULL, CORREO VARCHAR (40) NOT NULL, PASSWORD blob)";
    private static final String TABLA_CLIENTES = "CREATE TABLE CLIENTES(" +
        "CODIGO VARCHAR (6) PRIMARY KEY," +
        "DNI VARCHAR (40) NOT NULL," +
        "NOMBRE VARCHAR (40) NOT NULL," +
        " APELLIDO VARCHAR (40) NOT NULL," +
        " CORREO VARCHAR (40) UNIQUE NOT NULL," +
        " PASSWORD VARCHAR (40) NOT NULL)";

    private static final String TABLA_PRODUCTOS = "CREATE TABLE PRODUCTOS(" +
            "CODIGO VARCHAR (6) PRIMARY KEY," +
            "PRODUCTO VARCHAR (40) NOT NULL," +
            "STOCK INTEGER NOT NULL, " +
            "PRECIO DECIMAL (9, 2) DEFAULT(0) NOT NULL)";


    public TiendaDB(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_CLIENTES);
        sqLiteDatabase.execSQL(TABLA_PRODUCTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLA_CLIENTES);
        sqLiteDatabase.execSQL(TABLA_CLIENTES);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLA_PRODUCTOS);
        sqLiteDatabase.execSQL(TABLA_PRODUCTOS);
    }

    //CLIENTES INICIAL

    public void agregarClientes(
            String codigo, String dni,String nombre,
            String apellido, String correo, String password) {

        SQLiteDatabase bd1 = getReadableDatabase();
        Cursor cursor = bd1.rawQuery("SELECT CODIGO FROM CLIENTES" +
                " WHERE CODIGO=(SELECT MAX(CODIGO) FROM CLIENTES)", null);

        String num="";
        if (cursor.moveToFirst()) {
            do {
                num=cursor.getString(0);
            } while (cursor.moveToNext());
        }

        /*Algoritmo para autogenerar el código*/
        if(num.equals("")){
            num="C00001";
        }else{
            String sql_new1 = num;

            //agregado abajo
            String[] digito = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
            String[] digito2 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
            //evalua el inicio del digito
            int cont_digito = 0;
            //nuevo digito solo número string
            String new_ide = "";
            //nuevo digito solo número int
            int new_digito;

            char[] caracteres = sql_new1.toCharArray();

            for (int i = 0; i < sql_new1.length(); i++) {

                String caracter_simplificado = String.valueOf(caracteres[i]);

                for (int e = 0; e < digito.length; e++) {
                    if (caracter_simplificado.equals(digito[e])) {
                        cont_digito = 1;
                    }
                }

                if (i == 5 && caracter_simplificado.equals("0")) {
                    cont_digito = 1;
                }

                if (cont_digito == 1) {
                    for (int p = 0; p < digito2.length; p++) {
                        if (caracter_simplificado.equals(digito2[p])) {
                            new_ide += caracter_simplificado;
                        }
                    }
                }

            }

            if (!String.valueOf(new_ide).equalsIgnoreCase("99999")) {
                new_digito = Integer.parseInt(new_ide) + 1;

                //convertimos el nuevo número a String
                String new_digito_string = "" + new_digito;

                int tam_digito = new_digito_string.length();
                int tam_anterior = sql_new1.length();
                int tam_inicial = tam_anterior - tam_digito;

                String final_ide = "";

                for (int i = 0; i < tam_inicial; i++) {
                    String caracter_simplificado2 = String.valueOf(caracteres[i]);
                    final_ide += caracter_simplificado2;

                }
                num = final_ide + new_digito;
            } else {
                num = sql_new1;
            }
        }

            SQLiteDatabase bd = getWritableDatabase();
            if (bd != null) {
// no funciona  bd.execSQL("INSERT INTO CLIENTES VALUES('" + num + "','" + dni+ "','" + nombre+ "','" + apellido + "','" + correo + "',AES_ENCRYPT('" + password + "','2022'))");
                bd.execSQL("INSERT INTO CLIENTES VALUES('" + num + "','" + dni + "','" + nombre + "','" + apellido + "','" + correo + "','" + password + "')");

//            String clave="hola";
//            bd.execSQL("INSERT INTO CLIENTES(CODIGO,DNI,NOMBRE,APELLIDO,CORREO,PASSWORD)"+
//                    "VALUES('" + num + "','" + dni+ "','" + nombre+ "','" + apellido + "','" + correo + "', AES_ENCRYPT('"+ password +"','"+ password +"'))");
                bd.close();
            }
    }

    public List<ClienteModelo> mostrarClientes() {
        SQLiteDatabase bd = getReadableDatabase();
//      Cursor cursor = bd.rawQuery("SELECT CODIGO, DNI, NOMBRE, APELLIDO, CORREO, AES_DECRYPT(PASSWORD, '2022') as PASSWORD FROM CLIENTES", null);
        Cursor cursor = bd.rawQuery("SELECT CODIGO, DNI, NOMBRE, APELLIDO, CORREO, PASSWORD  FROM CLIENTES", null);
        List<ClienteModelo> clientes = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                clientes.add(new ClienteModelo(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4), cursor.getString(5)));
            } while (cursor.moveToNext());

        }
        return clientes;
    }

    public void buscarClientes(ClienteModelo clientes, String codigo) {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM CLIENTES WHERE CODIGO='"+codigo+"'", null);
        if (cursor.moveToFirst()) {
            do {
                clientes.setDni(cursor.getString(1));
                clientes.setNombre(cursor.getString(2));
                clientes.setApellido(cursor.getString(3));
                clientes.setCorreo(cursor.getString(4));
                clientes.setPassword(cursor.getString(5));
            } while (cursor.moveToNext());
        }
    }

    public void buscarUsuario(ClienteModelo clientes, String correo, String password) {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM CLIENTES WHERE CORREO='"+correo+"' AND PASSWORD='"+password+"'", null);
        if (cursor.moveToFirst()) {
            do {
                clientes.setCodigo(cursor.getString(0));
            } while (cursor.moveToNext());
        }
    }


    public void editarClientes(String codigo, String dni,String nombre, String apellido, String correo, String password) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
//            bd.execSQL("UPDATE CLIENTES SET DNI='" + dni + "', NOMBRE='" + nombre + "',APELLIDO='" + apellido + "',CORREO='" + correo + "',PASSWORD=AES_ENCRYPT('" +password+ "','2022') WHERE CODIGO='" + codigo + "'");
            bd.execSQL("UPDATE CLIENTES SET DNI='" + dni + "', NOMBRE='" + nombre + "',APELLIDO='" + apellido + "',CORREO='" + correo + "',PASSWORD='" +password+ "' WHERE CODIGO='" + codigo + "'");
            bd.close();
        }
    }

    public void eliminarClientes(String codigo) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            bd.execSQL("DELETE FROM CLIENTES WHERE CODIGO='" + codigo + "'");
            bd.close();
        }
    }

    public void buscarCorreo(ClienteModelo clientes,String correo) {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM CLIENTES WHERE CORREO='"+correo+"'", null);
        if (cursor.moveToFirst()) {
            do {
                clientes.setCorreo(cursor.getString(4));
            } while (cursor.moveToNext());

        }

    }

    //CLIENTES FINAL



    //PRODUCTOS INICIAL


    public void agregarProductos(String codigo, String producto,int stock, double precio) {

        SQLiteDatabase bd1 = getReadableDatabase();
        Cursor cursor = bd1.rawQuery("SELECT CODIGO FROM PRODUCTOS WHERE CODIGO=(SELECT MAX(CODIGO) FROM PRODUCTOS)", null);
        String num="";
        if (cursor.moveToFirst()) {
            do {
                num=cursor.getString(0);

            } while (cursor.moveToNext());

        }

        //
        /*Algoritmo para autogeneral el código*/
        if(num.equals("")){
            num="P00001";
        }else{
            String sql_new1 = num;

            //agregado abajo
            String[] digito = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
            String[] digito2 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
            //evalua el inicio del digito
            int cont_digito = 0;
            //nuevo digito solo número string
            String new_ide = "";
            //nuevo digito solo número int
            int new_digito;

            char[] caracteres = sql_new1.toCharArray();

            for (int i = 0; i < sql_new1.length(); i++) {

                String caracter_simplificado = String.valueOf(caracteres[i]);

                for (int e = 0; e < digito.length; e++) {
                    if (caracter_simplificado.equals(digito[e])) {
                        cont_digito = 1;
                    }
                }

                if (i == 5 && caracter_simplificado.equals("0")) {
                    cont_digito = 1;
                }

                if (cont_digito == 1) {
                    for (int p = 0; p < digito2.length; p++) {
                        if (caracter_simplificado.equals(digito2[p])) {
                            new_ide += caracter_simplificado;
                        }
                    }
                }

            }

            if (!String.valueOf(new_ide).equalsIgnoreCase("99999")) {
                new_digito = Integer.parseInt(new_ide) + 1;

                //convertimos el nuevo número a String
                String new_digito_string = "" + new_digito;

                int tam_digito = new_digito_string.length();
                int tam_anterior = sql_new1.length();
                int tam_inicial = tam_anterior - tam_digito;

                String final_ide = "";

                for (int i = 0; i < tam_inicial; i++) {
                    String caracter_simplificado2 = String.valueOf(caracteres[i]);
                    final_ide += caracter_simplificado2;

                }
                num = final_ide + new_digito;
            } else {
                num = sql_new1;
            }
        }

        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            bd.execSQL("INSERT INTO PRODUCTOS VALUES('" + num + "','" + producto +"','" + stock+ "','" + precio + "')");
            bd.close();
        }
    }

    public List<ProductoModelo> mostrarProductos() {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT CODIGO, PRODUCTO, STOCK, PRECIO FROM PRODUCTOS", null);
        List<ProductoModelo> productos = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                productos.add(new ProductoModelo(cursor.getString(0), cursor.getString(1), cursor.getInt(2), cursor.getDouble(3)));
            } while (cursor.moveToNext());

        }
        return productos;
    }

    public void buscarProductos(ProductoModelo productos, String codigo) {
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM PRODUCTOS WHERE CODIGO='"+codigo+"'", null);
        if (cursor.moveToFirst()) {
            do {
                productos.setProducto(cursor.getString(1));
                productos.setStock(cursor.getInt(2));
                productos.setPrecio(cursor.getDouble(3));

            } while (cursor.moveToNext());
        }
    }


    public void editarProductos(String codigo, String producto,int stock, double precio) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            bd.execSQL("UPDATE PRODUCTOS SET PRODUCTO='" + producto + "', STOCK='" + stock + "',PRECIO='" + precio + "' WHERE CODIGO='" + codigo + "'");
            bd.close();
        }
    }

    public void eliminarProductos(String codigo) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            bd.execSQL("DELETE FROM PRODUCTOS WHERE CODIGO='" + codigo + "'");
            bd.close();
        }
    }

    //PRODUCTOS FINAL

    
}
