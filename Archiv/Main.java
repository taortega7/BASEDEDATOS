package gestion;

import java.util.Scanner;

/**
 * ══════════════════════════════════════════════════════════════════
 *   SISTEMA DE GESTIÓN DE PERSONAS – Menú Principal
 *   Transacciones: Adición, Modificación, Eliminación,
 *                  Búsqueda, Listado
 * ══════════════════════════════════════════════════════════════════
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // ── PASO 1 & 2: crear archivo con 20 registros iniciales ──
        ArchivoPersonas.crearArchivoInicial();

        int opcion;
        do {
            mostrarMenu();
            opcion = leerInt("Seleccione una opción: ");
            switch (opcion) {
                case 1 -> menuAdicionar();
                case 2 -> menuModificar();
                case 3 -> menuEliminar();
                case 4 -> menuBuscar();
                case 5 -> ArchivoPersonas.listar();
                case 0 -> System.out.println("\n✔ Fin del programa. ¡Hasta pronto!");
                default -> System.out.println("✖ Opción inválida.");
            }
        } while (opcion != 0);
    }

    // ─────────────────────────────────────────────────────────────────────────
    private static void mostrarMenu() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║   GESTIÓN DE PERSONAS – ARCHIVO    ║");
        System.out.println("╠════════════════════════════════════╣");
        System.out.println("║  1. Adicionar persona               ║");
        System.out.println("║  2. Modificar persona               ║");
        System.out.println("║  3. Eliminar persona                ║");
        System.out.println("║  4. Buscar persona                  ║");
        System.out.println("║  5. Listar todas las personas       ║");
        System.out.println("║  0. Salir                           ║");
        System.out.println("╚════════════════════════════════════╝");
    }

    // ─────────────────────────────────────────────────────────────────────────
    // ADICIÓN
    // ─────────────────────────────────────────────────────────────────────────
    private static void menuAdicionar() {
        System.out.println("\n─── ADICIONAR PERSONA ───");
        int    codigo   = leerInt   ("Código         : ");
        String apellido = leerStr   ("Apellido       : ");
        String nombre   = leerStr   ("Nombre         : ");
        char   sexo     = leerChar  ("Sexo (M/F)     : ");
        String fecha    = leerStr   ("Fecha Nac.(AAAA-MM-DD): ");
        String ciudad   = leerStr   ("Ciudad         : ");
        String telefono = leerStr   ("Teléfono       : ");
        String correo   = leerStr   ("Correo         : ");
        String profesion= leerStr   ("Profesión      : ");
        double salario  = leerDouble("Salario        : ");

        Persona p = new Persona(codigo, apellido, nombre, sexo,
                fecha, ciudad, telefono, correo, profesion, salario);
        ArchivoPersonas.adicionar(p);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // MODIFICACIÓN
    // ─────────────────────────────────────────────────────────────────────────
    private static void menuModificar() {
        System.out.println("\n─── MODIFICAR PERSONA ───");
        int codigo = leerInt("Código a modificar: ");
        Persona actual = ArchivoPersonas.buscar(codigo);
        if (actual == null) {
            System.out.println("✖ No existe persona con código " + codigo);
            return;
        }
        System.out.println("Registro actual:");
        imprimirPersona(actual);
        System.out.println("Ingrese nuevos datos (Enter = mantener valor actual):");

        String apellido  = leerConDefault("Apellido       [" + actual.getApellido()  + "]: ", actual.getApellido());
        String nombre    = leerConDefault("Nombre         [" + actual.getNombre()    + "]: ", actual.getNombre());
        String sexoStr   = leerConDefault("Sexo(M/F)      [" + actual.getSexo()      + "]: ", String.valueOf(actual.getSexo()));
        String fecha     = leerConDefault("Fecha Nac.     [" + actual.getFechaNacimiento() + "]: ", actual.getFechaNacimiento());
        String ciudad    = leerConDefault("Ciudad         [" + actual.getCiudad()    + "]: ", actual.getCiudad());
        String telefono  = leerConDefault("Teléfono       [" + actual.getTelefono()  + "]: ", actual.getTelefono());
        String correo    = leerConDefault("Correo         [" + actual.getCorreo()    + "]: ", actual.getCorreo());
        String profesion = leerConDefault("Profesión      [" + actual.getProfesion() + "]: ", actual.getProfesion());
        String salarioStr= leerConDefault("Salario        [" + actual.getSalario()   + "]: ", String.valueOf(actual.getSalario()));

        Persona mod = new Persona(
                codigo, apellido, nombre,
                sexoStr.toUpperCase().charAt(0), fecha, ciudad,
                telefono, correo, profesion,
                Double.parseDouble(salarioStr));
        ArchivoPersonas.modificar(mod);
    }

    // ─────────────────────────────────────────────────────────────────────────
    // ELIMINACIÓN
    // ─────────────────────────────────────────────────────────────────────────
    private static void menuEliminar() {
        System.out.println("\n─── ELIMINAR PERSONA ───");
        int codigo = leerInt("Código a eliminar: ");
        Persona p = ArchivoPersonas.buscar(codigo);
        if (p == null) {
            System.out.println("✖ No existe persona con código " + codigo);
            return;
        }
        imprimirPersona(p);
        String conf = leerStr("¿Confirma eliminación? (S/N): ");
        if (conf.equalsIgnoreCase("S")) {
            ArchivoPersonas.eliminar(codigo);
        } else {
            System.out.println("✔ Eliminación cancelada.");
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // BÚSQUEDA
    // ─────────────────────────────────────────────────────────────────────────
    private static void menuBuscar() {
        System.out.println("\n─── BUSCAR PERSONA ───");
        int codigo = leerInt("Código a buscar: ");
        Persona p = ArchivoPersonas.buscar(codigo);
        if (p != null) {
            System.out.println("✔ Persona encontrada:");
            imprimirPersona(p);
        } else {
            System.out.println("✖ No existe persona con código " + codigo);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Helpers de entrada
    // ─────────────────────────────────────────────────────────────────────────
    private static int leerInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  ✖ Ingrese un número entero válido.");
            }
        }
    }

    private static double leerDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("  ✖ Ingrese un número decimal válido.");
            }
        }
    }

    private static String leerStr(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    private static char leerChar(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim().toUpperCase();
            if (s.equals("M") || s.equals("F")) return s.charAt(0);
            System.out.println("  ✖ Ingrese M o F.");
        }
    }

    private static String leerConDefault(String msg, String defVal) {
        System.out.print(msg);
        String s = sc.nextLine().trim();
        return s.isEmpty() ? defVal : s;
    }

    private static void imprimirPersona(Persona p) {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.printf ("│ Código      : %-26d │%n", p.getCodigo());
        System.out.printf ("│ Apellido    : %-26s │%n", p.getApellido());
        System.out.printf ("│ Nombre      : %-26s │%n", p.getNombre());
        System.out.printf ("│ Sexo        : %-26c │%n", p.getSexo());
        System.out.printf ("│ Nacimiento  : %-26s │%n", p.getFechaNacimiento());
        System.out.printf ("│ Ciudad      : %-26s │%n", p.getCiudad());
        System.out.printf ("│ Teléfono    : %-26s │%n", p.getTelefono());
        System.out.printf ("│ Correo      : %-26s │%n", p.getCorreo());
        System.out.printf ("│ Profesión   : %-26s │%n", p.getProfesion());
        System.out.printf ("│ Salario     : %-26.2f │%n", p.getSalario());
        System.out.println("└─────────────────────────────────────────┘");
    }
}
