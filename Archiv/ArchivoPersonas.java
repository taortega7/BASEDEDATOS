package gestion;

import java.io.*;
import java.util.*;

public class ArchivoPersonas {

    private static final String ARCHIVO = "personas.dat";

    // ─────────────────────────────────────────────────────────────────────────
    // PASO 1 & 2 – Crear el archivo con 20 personas iniciales
    // ─────────────────────────────────────────────────────────────────────────
    public static void crearArchivoInicial() {
        File f = new File(ARCHIVO);
        if (f.exists()) {
            System.out.println("✔ El archivo ya existe. No se sobreescribe.");
            return;
        }
        List<Persona> datos = new ArrayList<>(Arrays.asList(
            new Persona(1,  "García",    "Carlos",    'M', "1985-03-12", "Bogotá",       "3001234567", "carlos.garcia@gmail.com",      "Ingeniero",       4500000),
            new Persona(2,  "Martínez",  "María",     'F', "1990-07-22", "Medellín",     "3112345678", "maria.martinez@hotmail.com",   "Doctora",         6800000),
            new Persona(3,  "López",     "Andrés",    'M', "1988-11-05", "Cali",         "3223456789", "andres.lopez@yahoo.com",       "Abogado",         5200000),
            new Persona(4,  "Rodríguez", "Luisa",     'F', "1995-01-30", "Barranquilla", "3134567890", "luisa.rodriguez@gmail.com",    "Contadora",       3800000),
            new Persona(5,  "Hernández", "Pedro",     'M', "1982-09-18", "Cartagena",    "3005678901", "pedro.hernandez@outlook.com",  "Arquitecto",      5900000),
            new Persona(6,  "Torres",    "Sofía",     'F', "1993-04-25", "Bucaramanga",  "3116789012", "sofia.torres@gmail.com",       "Psicóloga",       4100000),
            new Persona(7,  "Ramírez",   "Juan",      'M', "1987-12-08", "Cúcuta",       "3227890123", "juan.ramirez@hotmail.com",     "Economista",      4700000),
            new Persona(8,  "Vargas",    "Paula",     'F', "1991-06-14", "Pereira",      "3048901234", "paula.vargas@gmail.com",       "Enfermera",       3200000),
            new Persona(9,  "Castro",    "Miguel",    'M', "1979-08-20", "Manizales",    "3159012345", "miguel.castro@yahoo.com",      "Veterinario",     4300000),
            new Persona(10, "Moreno",    "Valentina", 'F', "1997-02-11", "Ibagué",       "3200123456", "valentina.moreno@gmail.com",   "Diseñadora",      3600000),
            new Persona(11, "Jiménez",   "Felipe",    'M', "1984-05-28", "Santa Marta",  "3101234560", "felipe.jimenez@outlook.com",   "Administrador",   4900000),
            new Persona(12, "Díaz",      "Camila",    'F', "1996-10-03", "Villavicencio","3212345671", "camila.diaz@gmail.com",        "Periodista",      3400000),
            new Persona(13, "Sánchez",   "Sebastián", 'M', "1989-07-17", "Armenia",      "3023456782", "sebastian.sanchez@hotmail.com","Biólogo",         4200000),
            new Persona(14, "Reyes",     "Isabella",  'F', "1994-03-09", "Pasto",        "3134567893", "isabella.reyes@yahoo.com",     "Química",         4600000),
            new Persona(15, "Cruz",      "Nicolás",   'M', "1986-11-22", "Montería",     "3245678904", "nicolas.cruz@gmail.com",       "Físico",          5100000),
            new Persona(16, "Flores",    "Daniela",   'F', "1998-08-31", "Neiva",        "3056789015", "daniela.flores@outlook.com",   "Nutricionista",   3300000),
            new Persona(17, "Guerrero",  "Santiago",  'M', "1983-01-14", "Popayán",      "3167890126", "santiago.guerrero@gmail.com",  "Geólogo",         5500000),
            new Persona(18, "Medina",    "Natalia",   'F', "1992-09-27", "Tunja",        "3278901237", "natalia.medina@hotmail.com",   "Optómetra",       3900000),
            new Persona(19, "Ruiz",      "Alejandro", 'M', "1981-04-06", "Florencia",    "3009012348", "alejandro.ruiz@yahoo.com",     "Matemático",      4800000),
            new Persona(20, "Suárez",    "Gabriela",  'F', "1999-12-19", "Riohacha",     "3110123459", "gabriela.suarez@gmail.com",    "Fisioterapeuta",  3700000)
        ));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            for (Persona p : datos) {
                oos.writeObject(p);
            }
            System.out.println("✔ Archivo creado con " + datos.size() + " personas.");
        } catch (IOException e) {
            System.err.println("✖ Error al crear el archivo: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Leer TODOS los registros del archivo
    // ─────────────────────────────────────────────────────────────────────────
    public static List<Persona> leerTodos() {
        List<Persona> lista = new ArrayList<>();
        File f = new File(ARCHIVO);
        if (!f.exists()) return lista;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            while (true) {
                try {
                    lista.add((Persona) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("✖ Error al leer: " + e.getMessage());
        }
        return lista;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Escribir TODA la lista de vuelta al archivo
    // ─────────────────────────────────────────────────────────────────────────
    private static void guardarTodos(List<Persona> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            for (Persona p : lista) {
                oos.writeObject(p);
            }
        } catch (IOException e) {
            System.err.println("✖ Error al guardar: " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // ADICIÓN
    // ─────────────────────────────────────────────────────────────────────────
    public static boolean adicionar(Persona nueva) {
        List<Persona> lista = leerTodos();
        for (Persona p : lista) {
            if (p.getCodigo() == nueva.getCodigo()) {
                System.out.println("✖ Ya existe un registro con código " + nueva.getCodigo());
                return false;
            }
        }
        lista.add(nueva);
        guardarTodos(lista);
        System.out.println("✔ Persona adicionada exitosamente.");
        return true;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // MODIFICACIÓN
    // ─────────────────────────────────────────────────────────────────────────
    public static boolean modificar(Persona modificada) {
        List<Persona> lista = leerTodos();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCodigo() == modificada.getCodigo()) {
                lista.set(i, modificada);
                guardarTodos(lista);
                System.out.println("✔ Persona modificada exitosamente.");
                return true;
            }
        }
        System.out.println("✖ No se encontró persona con código " + modificada.getCodigo());
        return false;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // ELIMINACIÓN
    // ─────────────────────────────────────────────────────────────────────────
    public static boolean eliminar(int codigo) {
        List<Persona> lista = leerTodos();
        boolean eliminado = lista.removeIf(p -> p.getCodigo() == codigo);
        if (eliminado) {
            guardarTodos(lista);
            System.out.println("✔ Persona con código " + codigo + " eliminada.");
        } else {
            System.out.println("✖ No se encontró persona con código " + codigo);
        }
        return eliminado;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // BÚSQUEDA
    // ─────────────────────────────────────────────────────────────────────────
    public static Persona buscar(int codigo) {
        for (Persona p : leerTodos()) {
            if (p.getCodigo() == codigo) return p;
        }
        return null;
    }

    // ─────────────────────────────────────────────────────────────────────────
    // LISTADO
    // ─────────────────────────────────────────────────────────────────────────
    public static void listar() {
        List<Persona> lista = leerTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay registros en el archivo.");
            return;
        }
        String linea = "+------+-----------------+-----------------+-------+--------------+-----------------+--------------+---------------------------+--------------------+------------+";
        String encab = "| Cod  | Apellido        | Nombre          | Sexo  | Nacimiento   | Ciudad          | Teléfono     | Correo                    | Profesión          | Salario    |";
        System.out.println(linea);
        System.out.println(encab);
        System.out.println(linea);
        for (Persona p : lista) {
            System.out.println(p);
        }
        System.out.println(linea);
        System.out.println("  Total de registros: " + lista.size());
    }
}