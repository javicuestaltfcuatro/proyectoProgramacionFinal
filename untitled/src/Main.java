import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Producto {
    //atributos de producto
    private static int nextID = 1;
    private int id;
    private String tipo;
    private double precio;
    private String modelo;
    //constructor de un producto
    public Producto(String tipo, double precio, String modelo) {
        this.id = nextID++;
        this.tipo = tipo;
        this.precio = precio;
        this.modelo = modelo;
    }
    //getters y setters del producto
    public int getId() {
        return id;
    }
    public String getTipo() {
        return tipo;
    }
    public double getPrecio() {
        return precio;
    }
    public void cambiarPrecio(double nuevoPrecio) {
        this.precio = nuevoPrecio;
    }
    @Override
    public String toString() {
        return "TIPO: " + tipo + "\nID: " + id + "\nMODELO: " + modelo + "\nPRECIO: " + precio;
    }
}

class Almacen {

    //atributos del almacen
    private ArrayList<Producto> productos;
    public double facturacionRam=0;
    public double facturacionTarjeta=0;
    public double facturacionPlaca=0;
    public double facturacionFuente=0;
    public double facturacionSsd=0;
    //constructor de almacen
    public Almacen() {
        this.productos = new ArrayList<>();
    }
    //metodo para insertar un producto
    public void insertarProducto(Producto producto) {
        productos.add(producto);
    }
    //metodo para buscar un producto
    public Producto buscarProducto(int id) {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }
    //metodo para vender un producto
    public void venderProducto(String tipo) {
        Producto productoVendido = null;
        for (Producto producto : productos) {
            if (producto.getTipo().equalsIgnoreCase(tipo)) {

                if (producto.getTipo().equalsIgnoreCase("RAM")){
                    facturacionRam = facturacionRam + producto.getPrecio();
                    if (productoVendido == null || producto.getId() < productoVendido.getId()) {
                        productoVendido = producto;
                    }
                }

                if (producto.getTipo().equalsIgnoreCase("Tarjeta Gráfica")){
                    facturacionTarjeta = facturacionTarjeta + producto.getPrecio();
                    if (productoVendido == null || producto.getId() < productoVendido.getId()) {
                        productoVendido = producto;
                    }
                }

                if (producto.getTipo().equalsIgnoreCase("Placa base")){
                    facturacionPlaca = facturacionPlaca + producto.getPrecio();
                    if (productoVendido == null || producto.getId() < productoVendido.getId()) {
                        productoVendido = producto;
                    }
                }

                if (producto.getTipo().equalsIgnoreCase("Fuente Alimentación")){
                    facturacionFuente = facturacionFuente + producto.getPrecio();
                    if (productoVendido == null || producto.getId() < productoVendido.getId()) {
                        productoVendido = producto;
                    }
                }

                if (producto.getTipo().equalsIgnoreCase("SSD")){
                    facturacionSsd = facturacionSsd + producto.getPrecio();
                    if (productoVendido == null || producto.getId() < productoVendido.getId()) {
                        productoVendido = producto;
                    }
                }

            }

        }

        if (productoVendido != null) {
            productos.remove(productoVendido);
            System.out.println("Producto vendido:\n" + productoVendido);
        } else {
            System.out.println("No hay producto con del tipo " + tipo + " en el almacén.");
        }
    }
    //metodo para cambiar el precio del producto
    public void cambiarPrecioProducto(int id, double nuevoPrecio) {
        Producto producto = buscarProducto(id);
        if (producto != null) {
            producto.cambiarPrecio(nuevoPrecio);
            System.out.println("Precio cambiado correctamente");
        } else {
            System.out.println("Producto no encontrado");
        }
    }



}

public class Main {
    public static void main(String[] args) {
        Almacen almacen = new Almacen();
        Scanner scanner = new Scanner(System.in);


        //menú
        while (true) {
            System.out.println("******************************************************************");
            System.out.println("Elija una de las siguientes opciones a través de número correspondiente:");
            System.out.println("1- Insertar producto en almacén");
            System.out.println("2- Buscar producto");
            System.out.println("3- Venta de producto");
            System.out.println("4- Cambiar precio producto");
            System.out.println("5- Mostrar facturación general");
            System.out.println("6- Mostrar facturación de producto");
            System.out.println("7- Salir");
            System.out.println("*******************************************************************");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    // Insertar producto en almacén
                    System.out.println("Ingrese tipo de producto (RAM, Tarjeta Gráfica, Placa Base, Fuente Alimentación, SSD):");
                    String tipo="";
                    while (!tipo.equalsIgnoreCase("RAM") && !tipo.equalsIgnoreCase("Tarjeta Gráfica") && !tipo.equalsIgnoreCase("Placa Base") && !tipo.equalsIgnoreCase("Fuente Alimentación") && !tipo.equalsIgnoreCase("SSD")) {
                        tipo = scanner.nextLine();
                        if (!tipo.equalsIgnoreCase("RAM") && !tipo.equalsIgnoreCase("Tarjeta Gráfica") && !tipo.equalsIgnoreCase("Placa Base") && !tipo.equalsIgnoreCase("Fuente Alimentación") && !tipo.equalsIgnoreCase("SSD")) {
                            System.out.println("Introduce correctamente el tipo de producto");
                        }

                    }

                    System.out.println("Ingrese precio del producto:");
                    double precio = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Ingrese modelo del producto:");
                    String modelo = scanner.nextLine();

                    Producto nuevoProducto = new Producto(tipo, precio, modelo);
                    almacen.insertarProducto(nuevoProducto);
                    System.out.println("Producto insertado correctamente");
                    break;

                case 2:
                    // Buscar producto
                    System.out.println("Ingrese el ID del producto a buscar:");
                    int idBuscar = scanner.nextInt();
                    scanner.nextLine();

                    Producto productoEncontrado = almacen.buscarProducto(idBuscar);
                    if (productoEncontrado != null) {
                        System.out.println("PRODUCTO ENCONTRADO:\n" + productoEncontrado);
                    } else {
                        System.out.println("PRODUCTO NO ENCONTRADO");
                    }
                    break;

                case 3:
                    // Venta de producto
                    System.out.println("Ingrese el tipo del producto a vender:");
                    String tipoVenta = scanner.nextLine();
                    almacen.venderProducto(tipoVenta);
                    break;

                case 4:
                    // Cambiar precio de producto
                    System.out.println("Ingrese el ID del producto cuyo precio desea cambiar:");
                    int idCambiarPrecio = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese el nuevo precio:");
                    double nuevoPrecio = scanner.nextDouble();
                    scanner.nextLine();

                    almacen.cambiarPrecioProducto(idCambiarPrecio, nuevoPrecio);
                    break;

                case 5:
                    // Mostrar facturación general
                    System.out.println("FACTURACIÓN GENERAL:");

                    System.out.println("TIPO: RAM , FACTURACIÓN: €" + almacen.facturacionRam);
                    System.out.println("TIPO: Tarjeta Gráfica , FACTURACIÓN: €" + almacen.facturacionTarjeta);
                    System.out.println("TIPO: Placa base , FACTURACIÓN: €" + almacen.facturacionPlaca);
                    System.out.println("TIPO: Fuente alimentación , FACTURACIÓN: €" + almacen.facturacionFuente);
                    System.out.println("TIPO: SSD , FACTURACIÓN: €" + almacen.facturacionSsd);

                    break;

                case 6:
                    // Mostrar facturación de producto
                    System.out.println("Ingrese el tipo de producto (RAM, Tarjeta Gráfica, Placa Base, Fuente Alimentación, SSD):");
                    String tipoFacturacion = scanner.nextLine();

                    if(!tipoFacturacion.equalsIgnoreCase("RAM") && !tipoFacturacion.equalsIgnoreCase("Tarjeta Gráfica") && !tipoFacturacion.equalsIgnoreCase("Placa Base") && !tipoFacturacion.equalsIgnoreCase("Fuente Alimentación") && !tipoFacturacion.equalsIgnoreCase("SSD")){
                        System.out.println("Tipo de producto fuera de RAM, Tarjeta Gráfica, Placa Base, Fuente Alimentación, SSD");
                        break;
                    }

                    if (tipoFacturacion.equalsIgnoreCase("RAM")){
                        System.out.println("VENTAS DE " + tipoFacturacion.toUpperCase() + ":\nFacturación de producto €" + almacen.facturacionRam);
                    }
                    if (tipoFacturacion.equalsIgnoreCase("Tarjeta Gráfica")){
                        System.out.println("VENTAS DE " + tipoFacturacion.toUpperCase() + ":\nFacturación de producto €" + almacen.facturacionTarjeta);
                    }
                    if (tipoFacturacion.equalsIgnoreCase("Placa Base")){
                        System.out.println("VENTAS DE " + tipoFacturacion.toUpperCase() + ":\nFacturación de producto €" + almacen.facturacionPlaca);
                    }
                    if (tipoFacturacion.equalsIgnoreCase("Fuente Alimentación")){
                        System.out.println("VENTAS DE " + tipoFacturacion.toUpperCase() + ":\nFacturación de producto €" + almacen.facturacionFuente);
                    }
                    if (tipoFacturacion.equalsIgnoreCase("SSD")){
                        System.out.println("VENTAS DE " + tipoFacturacion.toUpperCase() + ":\nFacturación de producto €" + almacen.facturacionSsd);
                    }


                    break;

                case 7:
                    // Salir
                    System.out.println("Saliendo del programa");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, ingrese un número del 1 al 7.");
                    break;
            }
        }
    }
}
