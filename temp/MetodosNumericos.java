import java.io.IOError;
import java.util.HashMap;
import java.util.Scanner;

import com.ezylang.evalex.EvaluationException;
import com.ezylang.evalex.Expression;
import com.ezylang.evalex.parser.ParseException;
public class MetodosNumericos {
    public static void main(String[] args) throws ParseException, EvaluationException {
        //Declaramos las variables a usar, para los datos númericos usamos variables double
        double vverda, vaprox, errAbs;
        int decimales;
        Expression función;
        double intervalo_A;
        double intervalo_B;
        double inicioIntervalo = 0;
        double finIntervalo = 1;
        int divisiones = 6;
        double integral = 0;
        int opc;
        Scanner t = new Scanner(System.in);
        do {
            //Menú principal del programa
            System.out.println("=====Bienvenido al programa de métodos numéricos=====");
            System.out.println("Selecciona una opcción del menú");
            System.out.println("1.Truncar valor vedadero, valor aproximado y sacar resultado de error absoluto"); //Opción de truncado
            System.out.println("2.Redondear valor vedadero, valor aproximado y sacar resultado de error absoluto"); //Opción de redondeado
            System.out.println("3.Calcular el método de Bisección"); //Opción de método de bisección
            System.out.println("4.Calcular el metodo de Simpson");
            System.out.println("5.Método de Jacobi");
            System.out.println("6.Interpolación de Lagrange");
            System.out.println("7.Salir");
            opc = t.nextInt();

            if (opc == 1) {
                /*
                 //Se pide al usuario que ingrese el valor verdadero y el aproximado
                 System.out.println("Dame el valor verdadero: ");
                 vverda = t.nextDouble();
                 System.out.println("Dame el valor aproximado: ");
                 vaprox = t.nextDouble();
                 //Así como el número de posiciones decimales deseadas
                 System.out.println("Dame el numero de decimales a los que desea truncar");
                 decimales = t.nextInt();

                 //Se calcula el error absoluto restando el valor apróximado del verdadero
                 errAbs = Math.abs(vverda - vaprox);
                 //Imprimimos el valor completo, el número de decimales deseados y el valor truncado
                 System.out.println("Error Absoluto: " + errAbs + ", Decimales: " + decimales);
                 System.out.println("Truncado: " + truncar(errAbs, decimales));
                 */

                System.out.print("Ingresa el valor verdadero: ");
                vverda = t.nextDouble(); //Solicitamos el valor verdadero
                System.out.print("Ingresa el numero de decimales que desea truncar en el valor verdadero: ");
                decimales = t.nextInt(); //Solicitamos el no. de decimales para truncar
                vverda = truncar(vverda, decimales);
                System.out.println("El resultado del valor verdadero truncado: " + vverda + '\n');

                System.out.print("Ingresa el valor aproximado: ");
                vaprox = t.nextDouble(); //Solicitamos el valor aproximado
                System.out.print("Ingresa el numero de decimales que desea truncar en el valor aproximado: ");
                decimales = t.nextInt();
                vaprox = truncar(vaprox, decimales);
                System.out.println("El resaultado del valor aproximado truncado: " + vaprox + '\n');

                errAbs = Math.abs(vverda - vaprox); //Calculamos el error absoluto restando el valor apróximado del verdadero
                System.out.print("Ingresa el número de decimales que desea truncar en el error absoluto: ");
                decimales = t.nextInt();
                System.out.println("El valor absuluto es: " + truncar(errAbs, decimales) + '\n');

            } else if (opc == 2) {
                System.out.print("Ingresa el valor verdadero: ");
                vverda = t.nextDouble(); //Solicitamos el valor verdadero
                System.out.print("Ingresa el numero de decimales que desea redondear en el valor verdadero: ");
                decimales = t.nextInt(); //Solicitamos el no. de decimales para redondear
                vverda = redondear(vverda, decimales);
                System.out.println("El resultado del valor verdadero redondeado: " + vverda + '\n');

                System.out.print("Ingresa el valor aproximado: ");
                vaprox = t.nextDouble(); //Solicitamos el valor aproximado
                System.out.print("Ingresa el numero de decimales que desea redondear en el valor aproximado: ");
                decimales = t.nextInt();
                vaprox = redondear(vaprox, decimales);
                System.out.println("El resaultado del valor aproximado redondeado: " + vaprox + '\n');

                errAbs = Math.abs(vverda - vaprox); //Calculamos el error absoluto restando el valor apróximado del verdadero
                System.out.print("Ingresa el número de decimales que desea redondear en el error absoluto: ");
                decimales = t.nextInt();
                System.out.println("El valor absuluto es: " + redondear(errAbs, decimales) + '\n');

            } else if (opc == 3) {
                System.out.println("====MÉTODO DE BISECCIÓN====");
                boolean loop;
                do {
                    System.out.print("Ingrese la fucnión: "); //Se solicita una función como string
                    try {
                        función = new Expression(System.console().readLine());
                        función.validate(); //Se crea y valida la función con la librería EvalEx
                        System.out.print("Ingrese el inicio del intervalo: "); //Se solicita el inicio del intervalo
                        intervalo_A = Double.parseDouble(System.console().readLine());
                        System.out.print("Ingrese el fin del intervalo: "); //Se solicita el fin del intervalo
                        intervalo_B = Double.parseDouble(System.console().readLine());
                        System.console().flush();
                        System.out.println();
                        if (intervalo_A >= intervalo_B) { //Se comprueba que el intervalo sea de menor a mayor "a < b"
                            throw new IllegalArgumentException("El inicio del intervalo debe ser menor que el final");
                        }
                        System.out.println("Raíz: " + metodoBiseccion(función, 0, intervalo_A, intervalo_B));
                        System.out.println(); //Se imprime la raíz encontrada
                        loop = false;

                        //Bloque catch para el manejo de errores en la entrada de datos
                    } catch (IOError | ParseException  | NullPointerException | IllegalArgumentException | EvaluationException e) {
                        if (e instanceof IOError) {
                            System.out.println("Error de captura - " + e.getMessage());
                        } else if (e instanceof ParseException) {
                            System.out.println("Función inválida - " + e.getMessage());
                        } else if (e instanceof NullPointerException) {
                            System.out.println("Valor de intervalo nulo - " + e.getMessage());
                        } else if (e instanceof IllegalArgumentException) {
                            System.out.println("Error en el intervalo - " + e.getMessage());
                        } else if (e instanceof EvaluationException) {
                            System.out.println("Error al evaluar la función - " + e.getMessage());
                        }
                        loop = false;
                        System.out.println("presione 1 para reintentar");
                        int opcion = t.nextInt();
                        if (opcion == 1) {
                            loop = true;
                        }
                    }
                } while (loop);

            } else if (opc == 4){
                System.out.println("Ingrese la función a integrar:");
                String funcionStr = t.nextLine();
                funcionStr = t.nextLine();
                función = new Expression(funcionStr);
                System.out.println("Ingrese el inicio del intervalo:");
                inicioIntervalo = t.nextDouble();

                System.out.println("Ingrese el fin del intervalo:");
                finIntervalo = t.nextDouble();

                System.out.println("Ingrese el número de divisiones:");
                divisiones = t.nextInt();

                System.out.println("El resultado de la integral es: " + integral);

            } else if (opc == 5) {
                System.out.println("Ingrese el numero de variables");
                int n1= t.nextInt();

                double [][] matrizCoeficientes = new double[n1][n1];
                System.out.println("Ingrese el valor de los ceficientes");

                for (int i = 0; i<n1; i++) {
                    for (int j = 0; j<n1; j++) {
                        matrizCoeficientes[i][j]=t.nextDouble();
                    }
                }

                double [] resultados= new double[n1];
                System.out.println("Ingrese los resultados de cada ecuacion");

                for (int i = 0; i < n1; i++) {
                    resultados[i]= t.nextDouble();
                }

                double valoresIniciales[]= new double[n1];
                System.out.println("Ingrese los valores la solución inicial");
                for (int i = 0; i <n1; i++) {
                    valoresIniciales[i]=t.nextDouble();
                }

                System.out.println("Escriba la tolerancia");
                double tolerancia= t.nextDouble();
                System.out.println("Ingrese el limite de iteraciones");
                int iteraciones= t.nextInt();

                try {
                    double[][] matrizDominante = hacerDominante(matrizCoeficientes);
                    metodoJacobi(matrizDominante,resultados,valoresIniciales, tolerancia, iteraciones);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            else if (opc==6){
                try {
                    System.out.print("Ingrese el número de puntos de datos: ");
                    int numDatoPuntos = t.nextInt();
        
                    double[] x = new double[numDatoPuntos]; // Arreglo para almacenar los valores de x
                    double[] y = new double[numDatoPuntos]; // Arreglo para almacenar los valores de y
        
                    System.out.println("Ingrese los valores de x e y:");
                    Lagrange.datoReal(x, y, t); // Método para leer los valores de x e y
        
                    System.out.print("Ingrese el valor para interpolar: ");
                    double n = t.nextDouble(); // Lee el valor para interpolar desde la entrada estándar
        
                    // Calcula el valor interpolado utilizando el método de Lagrange
                    double valorInterpolado = Lagrange.interpolar(x, y, n);
                    // Muestra el valor interpolado en la salida estándar
                    System.out.println("El valor interpolado en " + n + " es: " + valorInterpolado);
                } catch (Exception e) {
                    System.out.println("Error: Ingrese un valor válido.");
                } finally {
                    t.close(); // Cierra el scanner para evitar fugas de recursos
                }
            }

            else if (opc==7){
                System.out.println("Saliendo del programa");
            }
        } while (opc != 6);
        t.close();
    }

    public static double truncar(double numero, int decimales) {
        // Multiplicamos el número por 10 elevado al número de decimales deseados
        // para pasar ese número de decimales a la parte "entera"
        // 123.4567 * 10^2 = 12345.67
        // ^^ ^^
        numero = numero * Math.pow(10, decimales);
        // Usamos el m. floor para reducir los decimales restantes a 0
        numero = Math.floor(numero);
        // Dividimos el número por el mismo poder de 10 para regresar los
        // decimales a su lugar
        numero = numero / Math.pow(10, decimales);
        return numero;
    }

    public static double redondear(double numero, int decimales) {
        numero = numero * Math.pow(10, decimales);
        // Lo mismo que con el m. truncar pero usamos el m.rint para redondear
        // la parte entera hacía arriba si el siguiente decimal es >= 5
        numero = Math.rint(numero);
        numero = numero / Math.pow(10, decimales);
        return numero;
    }

    public static double metodoBiseccion(Expression función, double intervalo_M, double intervalo_A, double intervalo_B)
            throws EvaluationException, ParseException {
        // Caclulo de error y punto medio
        double error = (intervalo_B - intervalo_A) / 2;
        intervalo_M = (intervalo_A + intervalo_B) / 2;
        System.out.println("Evaluando en intervalo [" + intervalo_A + ", " + intervalo_B + "]");
        System.out.println("Punto medio: " + intervalo_M);
        System.out.println("Error: " + error + '\n');
        // Condición base de retorno
        if (error <= .01) {
            return intervalo_M;
        }
        // Calculo de las f's
        double función_A = función.with("x", intervalo_A).evaluate().getNumberValue().doubleValue();
        double función_B = función.with("x", intervalo_B).evaluate().getNumberValue().doubleValue();
        double función_M = función.with("x", intervalo_M).evaluate().getNumberValue().doubleValue();
        // Condición f(m) = 0, raíz exacta
        if (función_M == 0) {
            error = 0;
            return intervalo_M;
        }
        // Comprobación de signos opuestos
        if (función_M * función_A < 0) {
            intervalo_B = intervalo_M;
        } else if (función_M * función_B < 0) {
            intervalo_A = intervalo_M;
            // Caso sin signos opuestos
        } else {
            throw new IllegalArgumentException("No existe raiz en el intervalo dado");
        }
        // Llamada recursiva
        return metodoBiseccion(función, intervalo_M, intervalo_A, intervalo_B);
    }

    //M. RECURSIVO DE JACOBI
    /* Retorna un arreglo con los valores de las variables (x1, x2, ..., xn)
     * Pide la matriz de coeficientes (c11, c12, ..., cnn), el vector de resultados (b1, b2, ..., bn) y el vector de solución inicial (x0)
     * También la tolerancia (o error) aceptado y un límite máximo de iteraciones */
    public static double[] metodoJacobi(double[][] matrizCoeficientes, double[] resultados, double[] valoresIniciales,
                                        double tolerancia, int limiteIteraciones) {

        limiteIteraciones -= 1;
        if (limiteIteraciones == 0) { //Primer caso base, retorna los valores de entrada si el límite de iteraciones llega a cero
            return valoresIniciales;
        }

        double[] valoresSiguientes = new double[valoresIniciales.length]; //Nuevo arreglo para guardar la siguiente iteración

        //Ciclo para recorrer la matriz de coeficientes
        /* Para cada "línea" de la matriz de coeficientes (que corresponde a una de las ecuaciones del sistema), se usa otro
         * ciclo "for" para hacer la sumatoria del producto de cada coeficiente por el valor actual de su variable asociada,
         * salvo por el término diagonal de esa ecuación (e.g. a11) */
        double sum;
        for (int i = 0; i < valoresIniciales.length; i++) {
            sum = 0;
            for (int j = 0; j < matrizCoeficientes.length; j++) {
                if (j != i) {
                    sum += matrizCoeficientes[i][j] * valoresIniciales[j];
                }
            }

            //Se calcula el valor de cada variable para la siguiente iteración usando la formula del método:
            //xi(k=n) = (bi-(sumatoria(aij+xj(k=n-1))))/aii, para xj != xi
            valoresSiguientes[i] = (resultados[i] - sum)/matrizCoeficientes[i][i];
            System.out.println("x" + i + ": " + valoresSiguientes[i]); //Se imprimen los resultados de cada iteración
        }
        System.out.println();

        //Calculamos la tolerancia de la iteración actual usando la formúla:
        //T=sqrt((sumatoria(xi(k=n)-xi(k=n-1)))^2)
        sum = 0;
        for (int i = 0; i < valoresIniciales.length; i++) {
            sum += Math.pow(valoresSiguientes[i] -  valoresIniciales[i], 2);
        }

        double toleranciaActual = Math.sqrt(sum);
        if (toleranciaActual <= tolerancia) { //Segundo caso base, retorna los valores nuevos si la tolerancia es menor o igual a la solicitada
            return valoresSiguientes;
        } else { //Si no, llama recursivamente el método con los nuevos valores
            return metodoJacobi(matrizCoeficientes, resultados, valoresSiguientes, tolerancia, limiteIteraciones);
        }
    }

    //M. HACER DOMINANTE
    /* Recibe una matriz nxn e intenta reordenar sus filas para convertirla en una matriz diagonal dominante, retorna la matriz
     * ordenada o en caso de que no se pueda hacer dominante arroja una excepción */
    public static double[][] hacerDominante(double[][] matriz) throws IllegalArgumentException {

        /* Mapa <llave, valor> para relacionar la posición del elemento dominante en una fila con la fila misma,
         * la fila en este caso corresponde a un arreglo unidimensional */
        HashMap<Integer, double[]> rankingEcuaciones = new HashMap<>();
        for (double[] coeficientes : matriz) { //Ciclo para recorrer cada fila de la matriz
            double sumaAbsCoeficientes = 0;
            for (int i = 0; i < coeficientes.length; i++) { //Ciclo para sumar el valor absoluto de todos los elementos (coeficientes) de la fila
                sumaAbsCoeficientes += Math.abs(coeficientes[i]);
            }

            boolean diagonalDominante = false; //Variable booleana para indicar si la matriz puede ser diagonal dominante
            for (int i = 0; i < coeficientes.length; i++) { //Ciclo para checar cual elemento de la fila es dominante

                /* Formula para checar dominancia, checamos si la suma absoluta de todos los valores de la fila es menor que el doble
                 * del elemento a checar. Esto es equivalente a restar el elemento de la suma y checar si el restante es menor que el
                 * elemento mismo, e.g., a0 > (a1 + a2 + ... + an)*/
                if (sumaAbsCoeficientes < 2 * Math.abs(coeficientes[i])) {
                    diagonalDominante = true;

                    /* Si existe un elemento dominante se ingresa toda la fila en el mapa con la posición del elemto dominante como su llave.
                     * En una matriz dominante diagonal todos los elementos dominantes deben estar en posiciones diferentes (1 por fila:
                     * a11, a22, ..., ann), por lo que si se repite la llave indicamos que la matriz no se puede convertir en dominante*/
                    if (rankingEcuaciones.putIfAbsent(i, coeficientes) != null) {
                        diagonalDominante = false;
                        //System.out.println("Condición de error, ranking repetido");
                        // Condición de error, ranking repetido
                    }
                }
            }
            if (!diagonalDominante) { //Si la matriz no se puede hacer dominante arrojamos una excepción
                throw new IllegalArgumentException("La matriz no se puede convertir en diagonal estrictamente dominante");
                //System.out.println("Condición de error, no existe dominante");
                // Condición de error, no existe dominante
            }
        }

        for (int i = 0; i < matriz.length; i++) { //Ciclo para ordenar la matriz acorde al mapa
            matriz[i] = rankingEcuaciones.get(i);
            for (int j = 0; j < matriz.length; j++) { //Ciclo para imprimir la matriz
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        return matriz; //Retorno de la matriz ordenada
    }

    public static void ranking(double[][] m) {
        int posiciónMayor;
        double mayor;
        for (double[] ds : m) {
            posiciónMayor = -1;
            mayor = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < m.length; i++) {
                if (ds[i] > mayor) {
                    mayor = ds[i];
                    posiciónMayor = i;
                }
            }
            System.out.println(ds + "Mayor: " + mayor + ", Posción: " + posiciónMayor);
        }
        double sumaAbsCoeficientes;
        int posiciónDominante;
        double dominante;
        for (double[] ds : m) {
            sumaAbsCoeficientes = 0;
            posiciónDominante = -1;
            dominante = Double.NaN;
            for (int i = 0; i < ds.length; i++) {
                sumaAbsCoeficientes += Math.abs(ds[i]);
            }
            for (int i = 0; i < ds.length; i++) {
                if (sumaAbsCoeficientes < 2 * Math.abs(ds[i])) {
                    posiciónDominante = i;
                    dominante = ds[i];
                }
            }
            System.out.println(ds + "Dominante: " + dominante + ", Posción: " + posiciónDominante);
        }
    }

    public static double simpson(Expression función, double inicioIntervalo, double finIntervalo, int divisiones)
            throws ParseException, EvaluationException {
        if (finIntervalo <= inicioIntervalo) {
            throw new IllegalArgumentException("EL fin del intervalo debe ser mayor que el inicio!!");
        }
        función.validate();

        double incremento = (finIntervalo - inicioIntervalo)/divisiones;
        double límite = finIntervalo - incremento;
        double sumaEvaluaciones = función.with("x", inicioIntervalo).evaluate().getNumberValue().doubleValue();
        System.out.println("F(x0) = " + sumaEvaluaciones);
        sumaEvaluaciones += simpsonEvaluarTerminoNon(función, inicioIntervalo + incremento, incremento, límite, 0);
        System.out.println("Sum(F(x1), F(xn-1)) = " + sumaEvaluaciones);
        sumaEvaluaciones += función.with("x", finIntervalo).evaluate().getNumberValue().doubleValue();
        return (incremento/3)*sumaEvaluaciones;
    }

    public static double simpsonEvaluarTerminoNon(Expression función, double termino, double incremento, double límite, double sumaEvaluaciones)
            throws EvaluationException, ParseException {
        if (termino > límite) {
            return sumaEvaluaciones;
        }
        sumaEvaluaciones += (4*(función.with("x", termino).evaluate().getNumberValue().doubleValue()));
        System.out.println("F(" + termino + ") = " + función.with("x", termino).evaluate().getNumberValue().doubleValue());
        System.out.println("sum = " + sumaEvaluaciones + "\n");
        return simpsonEvaluarTerminoPar(función, termino + incremento, incremento, límite, sumaEvaluaciones);
    }

    public static double simpsonEvaluarTerminoPar(Expression función, double termino, double incremento, double límite, double sumaEvaluaciones)
            throws EvaluationException, ParseException {
        if (termino > límite) {
            return sumaEvaluaciones;
        }
        sumaEvaluaciones += (2*(función.with("x", termino).evaluate().getNumberValue().doubleValue()));
        System.out.println("F(" + termino + ") = " + función.with("x", termino).evaluate().getNumberValue().doubleValue());
        System.out.println("sum = " + sumaEvaluaciones + "\n");
        return simpsonEvaluarTerminoNon(función, termino + incremento, incremento, límite, sumaEvaluaciones);
    }
}