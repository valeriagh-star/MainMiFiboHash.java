package fibonacci;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Hashtable;


public class MainMiFiboHash {
 
	    // Tabla hash estática para la memoización. Simula el diccionario 'sucFib'.
	    private static Hashtable<Integer, Integer> sucFib = new Hashtable<>();

	    /**
	     * Función recursiva que calcula F(n) usando la tabla hash para guardar
	     * y recuperar resultados (memoización).
	     */
	    public static int fibMem(int n) {
	        // Acción: Obtener datos dada una llave (get)
	        // Si 'n' ya está calculado, retorna el valor guardado.
	        if (sucFib.containsKey(n)) {
	            return sucFib.get(n);
	        }

	        // Si no está, calcula F(n) = F(n-1) + F(n-2)
	        int resultado = fibMem(n - 1) + fibMem(n - 2);
	        
	        // Acción: Crear (put) el nuevo valor calculado en la tabla hash
	        sucFib.put(n, resultado); 

	        return resultado;
	    }

	    public static void main(String[] args) throws IOException {
	        
	        // Inicialización de la tabla hash: F(0)=0 y F(1)=1.
	        sucFib.put(0, 0);
	        sucFib.put(1, 1);
	        
	        // Lectura de la entrada del usuario
	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	        System.out.print("¿Fibonacci en la posición?: ");
	        String input = reader.readLine();
	        
	        int n;
	        try {
	            n = Integer.parseInt(input);
	        } catch (NumberFormatException e) {
	            System.out.println("Entrada inválida.");
	            return;
	        }

	        if (n < 0) {
	            System.out.println("Posición no válida.");
	            return;
	        }
	        
	        // Cálculo y resultado
	        int fibonacciResult = fibMem(n);
	        System.out.printf("El %d-ésimo número de Fibonacci es: %d%n", n, fibonacciResult);
	        
	        // --- Recorrer la tabla hash ---
	        System.out.println("\nLos valores calculados de la sucesión son:");
	        
	        // Acción: Recorrer mediante forEach (Forma 2 en la imagen)
	        sucFib.forEach((key, value) -> System.out.printf("{%d}: {%d}%n", key, value));

	        // --- Ejemplo de Modificación y Recorrido Adicional ---
	        System.out.println("\n--- Ejemplo de Modificación (replace) ---");
	        int keyToModify = 2;
	        if (sucFib.containsKey(keyToModify)) {
	            int oldValue = sucFib.get(keyToModify);
	            int newValue = oldValue + 100;
	            
	            // Acción: Modificar datos: método replace(key, valor)
	            sucFib.replace(keyToModify, newValue);
	            System.out.printf("Valor original de F(%d): %d. Nuevo valor: %d%n", 
	                               keyToModify, oldValue, sucFib.get(keyToModify));
	        }

	        System.out.println("\n--- Ejemplo de Recorrer (keySet) ---");
	        // Acción: Recorrer la tabla hash mediante foreach e keySet() (Forma 1 en la imagen)
	        for (int key : sucFib.keySet()) {
	            int value = sucFib.get(key);
	            System.out.printf("Key: %d, Value: %d%n", key, value);
	        }
	    }
	}
