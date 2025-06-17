import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/*
 * Aquesta entrega consisteix en implementar tots els mètodes anomenats "exerciciX". Ara mateix la
 * seva implementació consisteix en llançar `UnsupportedOperationException`, ho heu de canviar així
 * com els aneu fent. 
 *
 * Criteris d'avaluació:
 *
 * - Si el codi no compila tendreu un 0.
 * 
 * - Les úniques modificacions que podeu fer al codi són:
 *    + Afegir un mètode (dins el tema que el necessiteu)
 *    + Afegir proves a un mètode "tests()"
 *    + Òbviament, implementar els mètodes que heu d'implementar ("exerciciX")
 *   Si feu una modificació que no sigui d'aquesta llista, tendreu un 0.
 *
 * - Principalment, la nota dependrà del correcte funcionament dels mètodes implementats (provant
 *   amb diferents entrades).
 *
 * - Tendrem en compte la neteja i organització del codi. Un estandard que podeu seguir és la guia
 *   d'estil de Google per Java: https://google.github.io/styleguide/javaguide.html . Per exemple:
 *    + IMPORTANT: Aquesta entrega està codificada amb UTF-8 i finals de línia LF.
 *    + Indentació i espaiat consistent
 *    + Bona nomenclatura de variables
 *    + Declarar les variables el més aprop possible al primer ús (és a dir, evitau blocs de
 *      declaracions).
 *    + Convé utilitzar el for-each (for (int x : ...)) enlloc del clàssic (for (int i = 0; ...))
 *      sempre que no necessiteu l'índex del recorregut. Igualment per while si no és necessari.
 *
 * Per com està plantejada aquesta entrega, no necessitau (ni podeu) utilitzar cap `import`
 * addicional, ni qualificar classes que no estiguin ja importades. El que sí podeu fer és definir
 * tots els mètodes addicionals que volgueu (de manera ordenada i dins el tema que pertoqui).
 *
 * Podeu fer aquesta entrega en grups de com a màxim 3 persones, i necessitareu com a minim Java 10.
 * Per entregar, posau els noms i cognoms de tots els membres del grup a l'array `Entrega.NOMS` que
 * està definit a la línia 53.
 *
 * L'entrega es farà a través d'una tasca a l'Aula Digital que obrirem abans de la data que se us
 * hagui comunicat. Si no podeu visualitzar bé algun enunciat, assegurau-vos de que el vostre editor 
 * de texte estigui configurat amb codificació UTF-8.
 */
class Entrega {
  static final String[] NOMS = {};

  /*
   * Aquí teniu els exercicis del Tema 1 (Lògica).
   */
  static class Tema1 {
    /*
     * Determinau si l'expressió és una tautologia o no:
     *
     * (((vars[0] ops[0] vars[1]) ops[1] vars[2]) ops[2] vars[3]) ...
     *
     * Aquí, vars.length == ops.length+1, i cap dels dos arrays és buid. Podeu suposar que els
     * identificadors de les variables van de 0 a N-1, i tenim N variables diferents (mai més de 20
     * variables).
     *
     * Cada ops[i] pot ser: CONJ, DISJ, IMPL, NAND.
     *
     * Retornau:
     *   1 si és una tautologia
     *   0 si és una contradicció
     *   -1 en qualsevol altre cas.
     *
     * Vegeu els tests per exemples.
     */
    static final char CONJ = '∧';
    static final char DISJ = '∨';
    static final char IMPL = '→';
    static final char NAND = '.';

    static int exercici1(char[] ops, int[] vars) {
    //   throw new UnsupportedOperationException("pendent"); 
           // primero verificamos que los arrays de entrada sean válidos 
      if (vars ==null || ops==null ||vars.length!=ops.length+1 || vars.length ==0 ) 
        return -1; // caso inválido 

      
    int numerodeVariables =0; // contador para el número máximo de variables 
    // encontramos el valor máximo en vars para saber cuantás variables distintas hay 
    for (int i=0; i<vars.length; i++) {
        if (vars[i]>=numerodeVariables ) { 
          numerodeVariables = vars[i] + 1; // actualizamos el contador si encontramos una variables mayor }} 
        }} 

      
          if (numerodeVariables >20) { 
            return -1; } 

          boolean esTautologia = true; 
          boolean esContradiccion = true; 
         
      // generamos todas las posibles combinaciones de valores de verdad (2^numerodeVariables) 
          int totalCombinaciones = 1<<numerodeVariables ;  

      
 // probamos cada combinacion posible de valores de verdad 
          for (int combinacion = 0; combinacion <totalCombinaciones ; combinacion++) {
            // evaluamos la expresión para esta combinacion de valores 
            boolean resultado = evaluarExpresion (vars, ops, combinacion, numerodeVariables ) ; 
            // si alguna comporabción da falso, no es tautología  

            
            if (!resultado) { 
              esTautologia= false ;} 
            // si alguna comprobación da verdadero, no es contradicción 
            if (resultado ) { 
              esContradiccion = false; } 
            if (!esTautologia && !esContradiccion) { 
              return -1; }  }


      
          // retornamos el resultado según lo encontrado 
      
          if (esTautologia) { return 1;} 
          else if ( esContradiccion) { return 0; } 
          else { return -1 ; }  } 

    
  // Método auxiliar para evaluar una expresión lógica para una combinación específica

       private static boolean evaluarExpresion(int[] vars, char[] ops, int combinacion, int numVariables) {
            // Creamos un array de booleanos que representará los valores de verdad  para cada variable

            boolean[] valors = new boolean[numVariables];
            // Recorremos todas las variables para asignarles su valor según la combinación actual

            for (int i = 0; i < numVariables; i++) {
                // Extraemos el i-ésimo bit de 'combinacion'. Si es 1, será true; si es 0, será false.

                valors[i] = ((combinacion >> i) & 1) == 1;
            }

            boolean resultado = valors[vars[0]];
            for (int i = 0; i < ops.length; i++) {
                boolean siguienteValor = valors[vars[i + 1]];
                switch (ops[i]) {
                    case '∧':
                        resultado = resultado && siguienteValor;
                        break;
                    case '∨':
                        resultado = resultado || siguienteValor;
                        break;
                    case '→':
                        resultado = !resultado || siguienteValor;
                        break;
                    case '.':
                        resultado = !(resultado && siguienteValor);
                        break;
                    default:
                        return false;
                }
            }

            return resultado;
        }
    //}
//}

   // }

    /*
     * Aquest mètode té de paràmetre l'univers (representat com un array) i els predicats
     * adients `p` i `q`. Per avaluar aquest predicat, si `x` és un element de l'univers, podeu
     * fer-ho com `p.test(x)`, que té com resultat un booleà (true si `P(x)` és cert).
     *
     * Amb l'univers i els predicats `p` i `q` donats, returnau true si la següent proposició és
     * certa.
     *
     * (∀x : P(x)) <-> (∃!x : Q(x))
     */
    static boolean exercici2(int[] universe, Predicate<Integer> p, Predicate<Integer> q) {
    //  throw new UnsupportedOperationException("pendent"); 
      // Verificar si todos los elementos del universo cumplen el predicado P(x)
boolean todosCumplenP = true; // Suponemos inicialmente que todos cumplen P

// Recorremos todo el array universo
for (int i = 0; i < universe.length; i++) {
    int elemento = universe[i]; // Obtenemos el elemento actual
    if (!p.test(elemento)) {    // Si este elemento no cumple P(x)
        todosCumplenP = false;  // Marcamos que no todos cumplen P
    }
}

// Contar cuántos elementos del universo cumplen el predicado Q(x)
int contadorQ = 0; 

// Recorremos todo el array universo nuevamente
for (int i = 0; i < universe.length; i++) {
    int elemento = universe[i];       // Obtenemos el elemento actual
    if (q.test(elemento)) {           // Si el elemento cumple Q(x)
        contadorQ++;                  // Aumentamos el contador
    }
}

// Verificar si hay exactamente un único elemento que cumple Q(x)
boolean hayUnSoloQ = false;       // Suponemos inicialmente que no hay uno solo
if (contadorQ == 1) {             
    hayUnSoloQ = true;            // Entonces hay exactamente uno que cumple Q
}

// Comprobamos la equivalencia lógica entre (∀x : P(x)) y (∃!x : Q(x))
// Si ambos valores booleanos son iguales, devolvemos true
if (todosCumplenP == hayUnSoloQ) {
    return true;  // La equivalencia es cierta
} else {
    return false; // La equivalencia es falsa
}

    }

    static void tests() {
      // Exercici 1
      // Taules de veritat

      // Tautologia: ((p0 → p2) ∨ p1) ∨ p0
      test(1, 1, 1, () -> exercici1(new char[] { IMPL, DISJ, DISJ }, new int[] { 0, 2, 1, 0 }) == 1);

      // Contradicció: (p0 . p0) ∧ p0
      test(1, 1, 2, () -> exercici1(new char[] { NAND, CONJ }, new int[] { 0, 0, 0 }) == 0);

      // Exercici 2
      // Equivalència

      test(1, 2, 1, () -> {
        return exercici2(new int[] { 1, 2, 3 }, (x) -> x == 0, (x) -> x == 0);
      });

      test(1, 2, 2, () -> {
        return exercici2(new int[] { 1, 2, 3 }, (x) -> x >= 1, (x) -> x % 2 == 0);
      });
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 2 (Conjunts).
   *
   * Per senzillesa tractarem els conjunts com arrays (sense elements repetits). Per tant, un
   * conjunt de conjunts d'enters tendrà tipus int[][]. Podeu donar per suposat que tots els
   * arrays que representin conjunts i us venguin per paràmetre estan ordenats de menor a major.
   *
   * Les relacions també les representarem com arrays de dues dimensions, on la segona dimensió
   * només té dos elements. L'array estarà ordenat lexicogràficament. Per exemple
   *   int[][] rel = {{0,0}, {0,1}, {1,1}, {2,2}};
   * i també donarem el conjunt on està definida, per exemple
   *   int[] a = {0,1,2};
   * Als tests utilitzarem extensivament la funció generateRel definida al final (també la podeu
   * utilitzar si la necessitau).
   *
   * Les funcions f : A -> B (on A i B son subconjunts dels enters) les representam o bé amb el seu
   * graf o bé amb un objecte de tipus Function<Integer, Integer>. Sempre donarem el domini int[] a
   * i el codomini int[] b. En el cas de tenir un objecte de tipus Function<Integer, Integer>, per
   * aplicar f a x, és a dir, "f(x)" on x és d'A i el resultat f.apply(x) és de B, s'escriu
   * f.apply(x).
   */
  static class Tema2 {
    /*
     * Trobau el nombre de particions diferents del conjunt `a`, que podeu suposar que no és buid.
     *
     * Pista: Cercau informació sobre els nombres de Stirling.
     */
    static int exercici1(int[] a) {
      //throw new UnsupportedOperationException("pendent");
      int totalPart = particiones(a.length);
      return totalPart;  
    }

    static int particiones(int elementos) {
            int particiones = 0;
            for (int i = 1; i <= elementos; i++) {
                particiones += Stirling(elementos, i);
            }
            return particiones;
    }   

    static int Stirling(int n, int k) {
            int numStirling = 0;
            for (int j = 0; j <= k; j++) {
                numStirling += Math.pow(-1, k - j) * (factorial(k) / (factorial(j) * factorial(k - j))) * Math.pow(j, n);

            }
            numStirling = numStirling / factorial(k);
            return numStirling;
    } 

     static public int factorial (int n){
            int factorial = 1;
            
            for (; n>0; n--){
                factorial = factorial*n;
            }
            return factorial;
      }

    /*
     * Trobau el cardinal de la relació d'ordre parcial sobre `a` més petita que conté `rel` (si
     * existeix). En altres paraules, el cardinal de la seva clausura reflexiva, transitiva i
     * antisimètrica.
     *
     * Si no existeix, retornau -1.
     */
    static int exercici2(int[] a, int[][] rel) {
      //throw new UnsupportedOperationException("pendent");
      int cardinal = -1;
      
      //clausura reflexiva 
            
            for (int i = 0; i < a.length; i++) { //recorrido por el conjunto a
                boolean existe = false;
                for (int j = 0; j < rel.length && !existe; j++) {
                    if (a[i] == rel[i][0] && a[i] == rel[i][1]) { // comprobar si existe (a,a)
                        existe = true;
                    }
                }
                if (!existe) { // si no existe, agregarlo a la relación
                    rel = agregar(rel, i, i);
                }
            }

      // clausura transitiva
            
            for (int[] rel1 : rel) {
                //recorrido por el conjunto a (a,b)
                for (int[] rel2 : rel) {
                    // segundo recorrido (comprobar si (b,c)
                    if (rel2[0] == rel1[1]) {
                        boolean existe = false;
                        for (int k = 0; k < rel.length && !existe; k++) {
                            // tercer recorrido (comprobar si existe (a,c) )
                            if (rel1[0] == rel[k][0] && rel2[1] == rel[k][1]) {
                                existe = true;
                            }
                        }
                        if (!existe) { // si no existe, agregarlo a la relación
                            rel = agregar(rel, rel1[0], rel2[1]);
                        }
                    }
                }
            }

      // clausura antisimétrica (a,b) (b,a) -> a = b  / (a,b) (c,d) -> a = b = c = d         
            boolean existeClausura = true;
            
            for (int i = 0; i < rel.length && existeClausura; i++) { //recorrido por el conjunto (A) (a,b)  
                for (int j = 0; j < rel.length && existeClausura; j++) { // segundo recorrido (comprobar si (b,a) a = b)
                    if (rel[j][0] == rel[i][1] && rel[j][1] == rel[i][0] && rel[j][0] != rel[j][1]) {
                        existeClausura = false;
                    }
                }
            }
            
            if (existeClausura){
                cardinal = rel.length;
            }

      rel = lexSorted(rel); // ordenar rel
      return cardinal;
    }

    static int[][] agregar(int [][]a, int x, int y){
            int [][] nuevaRel = new int [a.length + 1][a[0].length];
            
            for (int i = 0; i < a.length; i++){
                for (int j = 0; j < a[0].length; j++){
                    nuevaRel[i][j] = a[i][j];                    
                }
            }
            
            nuevaRel[a.length][0] = x;
            nuevaRel[a.length][1] = y;
            return nuevaRel;
        }

    // método para comprobar el contenido de rel, no es necesario imprimir en los ejercicios 1 y 2
    static String imprimirArray2D(int a[][]){
            String s = "{";
            for (int i = 0; i < a.length; i++) {
                s += "(";
                for (int j = 0; j < a[0].length; j++) {
                    s += a[i][j];
                    if (j < a[0].length - 1) {
                        s += ",";
                    }
                }
                s += ")";
                if (i < a.length - 1) {
                    s += ", ";
                }
            }            
            s += "}";
            return s;
        }

    /*
     * Donada una relació d'ordre parcial `rel` definida sobre `a` i un subconjunt `x` de `a`,
     * retornau:
     * - L'ínfim de `x` si existeix i `op` és false
     * - El suprem de `x` si existeix i `op` és true
     * - null en qualsevol altre cas
     */
    static Integer exercici3(int[] a, int[][] rel, int[] x, boolean op) {
      //throw new UnsupportedOperationException("pendent");
      Integer resultado = null;
        //el ínfim es la fita inferior máxima, es decir, el mayor número que está relacionado por abajo con el subconjunto

        //primero, buscar todas las fitas inferiores
        int indiceInf = 0;
        int[] fitesInf = new int[a.length]; // array de las fitas inferiores

        // buscar en todos los elementos del conjunto a
        for (int i = 0; i < a.length; i++) {

            // búsqueda para ver si el elemento de a está relacionado con todos los de x
            boolean esFitaInf = true;
            
            for (int j = 0; j < x.length && esFitaInf; j++) { 

                int k = 0; //k = i;
                while (k < rel.length && !(rel[k][0] == a[i] && rel[k][1] == x[j])) {
                    k++;
                }

                if (k == rel.length) { // si llega al final y no se encuentra es que no es fita 
                    esFitaInf = false;
                }

            }

            if (esFitaInf) {
                fitesInf[indiceInf++] = a[i];
                System.out.println("Fita inferior: " + a[i]);
            }
        }
        
        // buscar la mayor de las fitas inferiores
            for (int i = 0; i < indiceInf; i++) { // recorrido por el array fitesInf
                boolean esInfim = true;
                for (int j = 0; j < indiceInf && esInfim; j++) { // recorrido por el array fitesInf
                    int k = 0;
                    while (k < rel.length && !(rel[k][0] == fitesInf[j] && rel[k][1] == fitesInf[i])) {
                        k++;
                    }
                    if (k == rel.length){
                        esInfim = false;
                    }
                }
                if (esInfim && !op){
                    resultado = fitesInf[i];
                    System.out.println("Ínfimo: " + resultado);
                }
            }
            if(resultado == null){
                System.out.println("No hay ínfimo");
            }
            
        //el supremo es la fita inferior máxima, es decir, el mayor número que está relacionado por abajo con el subconjunto
        //primero, buscar todas las fitas superiores
        int indiceSup = 0;
        int[] fitesSup = new int[a.length]; // array de las fitas superiores

        // buscar en todos los elementos del conjunto a
        for (int i = 0; i < a.length; i++) {

            // búsqueda para ver si el elemento de a está relacionado con todos los de x
            boolean esFitaSup = true;

            for (int j = 0; j < x.length && esFitaSup; j++) {

                int k = 0; //k = i;
                while (k < rel.length && !(rel[k][0] == x[j] && rel[k][1] == a[i])) {
                    k++;
                }

                if (k == rel.length) { // si llega al final y no se encuentra es que no es fita 
                    esFitaSup = false;
                }

            }

            if (esFitaSup) {
                fitesSup[indiceSup++] = a[i];
                System.out.println("Fita superior: " + a[i]);
            }
        }

        // buscar la mayor de las fitas superiores
        for (int i = 0; i < indiceSup; i++) { // recorrido por el array fitesSup
            boolean esSuprem = true;
            for (int j = 0; j < indiceSup && esSuprem; j++) { // recorrido por el array fitesSup
                int k = 0;
                while (k < rel.length && !(rel[k][0] == fitesSup[i] && rel[k][1] == fitesSup[j])) {
                    k++;
                }
                if (k == rel.length) {
                    esSuprem = false;
                }
            }
            if (esSuprem && op) {
                resultado = fitesSup[i];
                System.out.println("Supremo: " + resultado);
            }
        }
        if (resultado == null) {
            System.out.println("No hay supremo");
        }
      
        return resultado;
      
    }

    /*
     * Donada una funció `f` de `a` a `b`, retornau:
     *  - El graf de la seva inversa (si existeix)
     *  - Sinó, el graf d'una inversa seva per l'esquerra (si existeix)
     *  - Sinó, el graf d'una inversa seva per la dreta (si existeix)
     *  - Sinó, null.
     */
    static int[][] exercici4(int[] a, int[] b, Function<Integer, Integer> f) {
      //throw new UnsupportedOperationException("pendent");

      // para saber si tiene inversa hay que comprobar que es bijectiva
            boolean inyectiva = true;

            // Comprobar si es inyectiva
            for (int i = 0; i < a.length && inyectiva; i++) {
                int x = f.apply(a[i]);
                int cont = 0;
                for (int j = 0; j < a.length && inyectiva; j++) {
                    int y = f.apply(a[j]);
                    if (x == y) {
                        cont++;
                    }
                    if (cont > 1) {
                        inyectiva = false;
                        //System.out.println("No es inyectiva");
                    }
                }
            }

            boolean sobreyectiva = true;

            // Comprobar si es sobreyectiva
            int[] imagenes = new int[a.length];
            for (int i = 0; i < a.length; i++) { // guardar las imágenes de los elementos de a
                imagenes[i] = f.apply(a[i]);
            }

            for (int i = 0; i < b.length && sobreyectiva; i++) {

                boolean encontrada = false;
                for (int j = 0; j < imagenes.length && !encontrada; j++) {
                    if (b[i] == imagenes[j]) {
                        encontrada = true;
                    }
                }
                if (!encontrada) {
                    sobreyectiva = false;
                    //System.out.println("No es sobreyectiva");
                }
            }

            int[][] inversa = null;
            /* 
            Si f es biyectiva (inyectiva y sobreyectiva):
            retornar el grafo de la inversa: conjunto de pares (f(x), x) para cada x en a
             */
            if (inyectiva && sobreyectiva) {
                //System.out.println("Tiene inversa");
                inversa = new int[a.length][2];

                for (int i = 0; i < inversa.length; i++) {
                    inversa[i][0] = f.apply(a[i]);
                    inversa[i][1] = a[i];
                }

                /* 
                Si f es inyectiva pero no sobreyectiva:
                retornar el grafo de la inversa por la izquierda: conjunto de pares (f(x), x) para cada x en a
                 */
            } else if (inyectiva && !sobreyectiva) { // no todos los elementos de b son imagen de algún elemento de a

                inversa = new int[b.length][2];
                for (int i = 0; i < b.length; i++) {
                    int y = b[i];
                    boolean encontrada = false;
                    for (int j = 0; j < a.length && !encontrada; j++) {
                        if (f.apply(a[j]) == y) {   // encontrar la antiimagen de y, que pertenece a b
                            inversa[i][0] = y;
                            inversa[i][1] = a[j];
                            encontrada = true;
                        }
                    }
                    if (!encontrada) { // si el elemento de b no es imagen de un de a
                        inversa[i][0] = y;
                        inversa[i][1] = a[0]; // asignar cualquier cualquier x de a
                    }
                }

                /*
                Si f es sobreyectiva pero no inyectiva:
                retornar el grafo de la inversa por la derecha: conjunto de pares (f(x), x) para cada f(x) en b
                 */
            } else if (!inyectiva && sobreyectiva) {

                inversa = new int[b.length][2];

                for (int i = 0; i < b.length; i++) {
                    int y = b[i];

                    boolean encontrada = false;
                    for (int j = 0; j < a.length && !encontrada; j++) { //buscar antiimagen del elemento de b
                        if (f.apply(a[j]).equals(y)) {
                            inversa[i][0] = y;
                            inversa[i][1] = a[j];
                            encontrada = true;
                        }
                    }
                }
            }

            if (inversa != null) {
                inversa = lexSorted(inversa);
            }
            
            //Si ninguna condición se cumple, retorna null
            return inversa;
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {
      // Exercici 1
      // Nombre de particions

      test(2, 1, 1, () -> exercici1(new int[] { 1 }) == 1);
      test(2, 1, 2, () -> exercici1(new int[] { 1, 2, 3 }) == 5);

      // Exercici 2
      // Clausura d'ordre parcial

      final int[] INT02 = { 0, 1, 2 };

      test(2, 2, 1, () -> exercici2(INT02, new int[][] { {0, 1}, {1, 2} }) == 6);
      test(2, 2, 2, () -> exercici2(INT02, new int[][] { {0, 1}, {1, 0}, {1, 2} }) == -1);

      // Exercici 3
      // Ínfims i suprems

      final int[] INT15 = { 1, 2, 3, 4, 5 };
      final int[][] DIV15 = generateRel(INT15, (n, m) -> m % n == 0);
      final Integer ONE = 1;

      test(2, 3, 1, () -> ONE.equals(exercici3(INT15, DIV15, new int[] { 2, 3 }, false)));
      test(2, 3, 2, () -> exercici3(INT15, DIV15, new int[] { 2, 3 }, true) == null);

      // Exercici 4
      // Inverses

      final int[] INT05 = { 0, 1, 2, 3, 4, 5 };

      test(2, 4, 1, () -> {
        var inv = exercici4(INT05, INT02, (x) -> x/2);

        if (inv == null)
          return false;

        inv = lexSorted(inv);

        if (inv.length != INT02.length)
          return false;

        for (int i = 0; i < INT02.length; i++) {
          if (inv[i][0] != i || inv[i][1]/2 != i)
            return false;
        }

        return true;
      });

      test(2, 4, 2, () -> {
        var inv = exercici4(INT02, INT05, (x) -> x);

        if (inv == null)
          return false;

        inv = lexSorted(inv);

        if (inv.length != INT05.length)
          return false;

        for (int i = 0; i < INT02.length; i++) {
          if (inv[i][0] != i || inv[i][1] != i)
            return false;
        }

        return true;
      });
    }

    /*
     * Ordena lexicogràficament un array de 2 dimensions
     * Per exemple:
     *  arr = {{1,0}, {2,2}, {0,1}}
     *  resultat = {{0,1}, {1,0}, {2,2}}
     */
    static int[][] lexSorted(int[][] arr) {
      if (arr == null)
        return null;

      var arr2 = Arrays.copyOf(arr, arr.length);
      Arrays.sort(arr2, Arrays::compare);
      return arr2;
    }

    /*
     * Genera un array int[][] amb els elements {a, b} (a de as, b de bs) que satisfàn pred.test(a, b)
     * Per exemple:
     *   as = {0, 1}
     *   bs = {0, 1, 2}
     *   pred = (a, b) -> a == b
     *   resultat = {{0,0}, {1,1}}
     */
    static int[][] generateRel(int[] as, int[] bs, BiPredicate<Integer, Integer> pred) {
      var rel = new ArrayList<int[]>();

      for (int a : as) {
        for (int b : bs) {
          if (pred.test(a, b)) {
            rel.add(new int[] { a, b });
          }
        }
      }

      return rel.toArray(new int[][] {});
    }

    // Especialització de generateRel per as = bs
    static int[][] generateRel(int[] as, BiPredicate<Integer, Integer> pred) {
      return generateRel(as, as, pred);
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 3 (Grafs).
   *
   * Els (di)grafs vendran donats com llistes d'adjacència (és a dir, tractau-los com diccionaris
   * d'adjacència on l'índex és la clau i els vèrtexos estan numerats de 0 a n-1). Per exemple,
   * podem donar el graf cicle no dirigit d'ordre 3 com:
   *
   * int[][] c3dict = {
   *   {1, 2}, // veïns de 0
   *   {0, 2}, // veïns de 1
   *   {0, 1}  // veïns de 2
   * };
   */
  static class Tema3 {
    /*
     * Determinau si el graf `g` (no dirigit) té cicles.
     */

        static boolean exercici1(int[][] g) { 
           //      throw new UnsupportedOperationException("pendent"); 

   
    int n = g.length; // Obtenemos el número total de nodos en el grafo

    boolean[] visitado = new boolean[n]; // Creamos un array para marcar qué nodos ya hemos visitado

    // Recorremos todos los nodos del grafo
    for (int nodo = 0; nodo < n; nodo++) {
        // Si el nodo aún no ha sido visitado
        if (!visitado[nodo]) {
            // Iniciamos una búsqueda en profundidad desde este nodo
            if (tieneCiclo(g, nodo, -1, visitado)) { // Usamos -1 como padre porque es el inicio
                return true; // Si encontramos un ciclo, devolvemos true inmediatamente
            }
        }
    }

    return false; // Si recorremos todo el grafo sin encontrar ciclos, devolvemos false
}

// Función auxiliar  para detectar ciclos
private static boolean tieneCiclo(int[][] g, int actual, int padre, boolean[] visitado) {
    visitado[actual] = true; // Marcamos el nodo actual como visitado

    for (int i = 0; i < g[actual].length; i++) { // Recorremos todos los vecinos del nodo actual
        int vecino = g[actual][i]; // Obtenemos el vecino actual

        if (!visitado[vecino]) { // Si el vecino no ha sido visitado
            if (tieneCiclo(g, vecino, actual, visitado)) { // Continuamos buscando desde el vecino
                return true; // Si se detecta un ciclo en la rama, devolvemos true
            }
        } else if (vecino != padre) { // Si ya fue visitado y no es el padre
            return true; // Hay un ciclo porque volvemos a un nodo ya visitado
        }
    }

    return false; // No se encontró ciclo desde este nodo
}







      
    //}

    /*
     * Determinau si els dos grafs són isomorfs. Podeu suposar que cap dels dos té ordre major que
     * 10.
     */

   static boolean exercici2(int[][] g1, int[][] g2) {
     // throw new UnsupportedOperationException("pendent");

            if (g1.length != g2.length) {
                return false; // Distinto número de nodos, no pueden ser isomorfos
            }

            int n = g1.length; // Número de nodos en el grafo
            if (n == 0) {
                return true; // Si ambos grafos están vacíos, son isomorfos
            }
            // Calculamos el grado de cada nodo para ambos grafos
            int[] gradosG1 = calcularGrados(g1);
            int[] gradosG2 = calcularGrados(g2);
            if (!compararGrados(gradosG1, gradosG2)) {
                return false; // Secuencia de grados distinta, no son isomorfos
            }

            // Creamos un array con la permutación inicial 
            int[] permutacion = new int[n];
            for (int i = 0; i < n; i++) {
                permutacion[i] = i; // Inicializamos con nodos en orden natural
            }

            // Intentamos encontrar alguna permutación que haga isomorfismo
            return generarPermutaciones(g1, g2, permutacion, 0);
        }

        // Calcula los grados de cada nodo en un grafo
        private static int[] calcularGrados(int[][] grafo) {
            int[] grados = new int[grafo.length]; // Array para guardar grados
            for (int i = 0; i < grafo.length; i++) {
                grados[i] = grafo[i].length; // El grado es la longitud del array de adyacencia
            }
            Arrays.sort(grados); // Ordenamos los grados para compararlos fácilmente
            return grados;
        }

        // Compara dos secuencias de grados
        private static boolean compararGrados(int[] g1, int[] g2) {
            if (g1.length != g2.length) {
                return false; // Distinta longitud, no iguales
            }
            for (int i = 0; i < g1.length; i++) {
                if (g1[i] != g2[i]) {
                    return false; // Algún grado distinto, no iguales
                }
            }
            return true; // Secuencias de grados idénticas
        }

        // Genera permutaciones y verifica si alguna es isomorfismo
        private static boolean generarPermutaciones(int[][] g1, int[][] g2, int[] perm, int k) {
            if (k == perm.length) {
                // Si ya generamos una permutación completa, comprobamos si es isomorfismo
                return esIsomorfismo(g1, g2, perm);
            }

            for (int i = k; i < perm.length; i++) {
                intercambio(perm, k, i); // Intercambiamos elementos para generar permutaciones
                if (generarPermutaciones(g1, g2, perm, k + 1)) {
                    return true; // Si encontramos isomorfismo, terminamos
                }
                intercambio(perm, k, i);  // Volvemos a intercambiar para restaurar el array
            }
            return false; // No se encontró ninguna permutación válida
        }

        // Intercambia dos elementos en un array
        private static void intercambio(int[] arr, int i, int j) {
            int temp = arr[i]; // Guardamos temporalmente el valor de arr[i]
            arr[i] = arr[j];   // Ponemos el valor de arr[j] en arr[i]
            arr[j] = temp;     // Ponemos el valor guardado en arr[j]
        }

        // Verifica si la permutación dada es un isomorfismo entre g1 y g2
        private static boolean esIsomorfismo(int[][] g1, int[][] g2, int[] perm) {
            for (int i = 0; i < g1.length; i++) {
                // Verificamos que los grados coincidan para el nodo permutado
                if (g1[i].length != g2[perm[i]].length) {
                    return false; // Grados distintos, no es isomorfismo
                }

                // Creamos un array booleano para marcar conexiones mapeadas
                boolean[] conexiones = new boolean[g2[perm[i]].length];
                for (int j = 0; j < g1[i].length; j++) {
                    int vecinoEnG1 = g1[i][j];          // Nodo vecino en g1
                    int vecinoEnG2 = perm[vecinoEnG1]; // Nodo mapeado en g2
                    boolean encontrado = false;         // Marca si encontramos la conexión

                    // Buscamos el vecino mapeado en la lista de adyacencia de g2
                    for (int k = 0; k < g2[perm[i]].length; k++) {
                        if (g2[perm[i]][k] == vecinoEnG2) {
                            encontrado = true;      // Conexión encontrada
                            conexiones[k] = true;   // Marcamos conexión como usada
                        }
                    }

                    if (!encontrado) {
                        return false; // Si falta conexión, no es isomorfismo
                    }
                }

                // Verificamos que todas las conexiones en g2 fueron usadas 
                for (int i2 = 0; i2 < conexiones.length; i2++) {
                    boolean conexion = conexiones[i2];
                    if (!conexion) {
                        return false; // Alguna conexión no mapeada, no es isomorfismo
                    }
                }
            }
            return true; // Todas las verificaciones pasaron, es isomorfismo
        }




    /*
     * Determinau si el graf `g` (no dirigit) és un arbre. Si ho és, retornau el seu recorregut en
     * postordre desde el vèrtex `r`. Sinó, retornau null;
     *
     * En cas de ser un arbre, assumiu que l'ordre dels fills vé donat per l'array de veïns de cada
     * vèrtex.
     */

      static int[] exercici3(int[][] g, int r) { 
     //         throw new UnsupportedOperationException("pendent");

            if (g.length == 0) {
                return null;
            }

            if (!esArbol(g)) {
                return null;
            }

            // Creamos un array para guardar el recorrido postorden 
            int[] postorden = new int[g.length];

            // Usamos un array de tamaño 1 para simular paso por referencia y llevar el índice en postorden
            int[] index = {0};

            // Array para controlar qué nodos ya han sido visitados durante el recorrido 
            boolean[] visitado = new boolean[g.length];

            // Ejecutamos la función recursiva que hace el recorrido postorden, partiendo del nodo raíz r
            postorden(g, r, -1, visitado, postorden, index);

            // Devolvemos el recorrido postorden calculado
            return postorden;
        }

        private static boolean esArbol(int[][] g) {
            if (g.length == 0) {
                return true;
            }

            // Array para marcar los nodos visitados
            boolean[] visitado = new boolean[g.length];

            // Comprobamos si tiene ciclos 
            if (tieneCiclos(g, 0, -1, visitado)) {
                return false; // Si hay ciclos, no es árbol
            }

            // Comprobamos que el grafo sea conexo
            for (boolean v : visitado) {
                if (!v) {
                    return false; // Algún nodo no visitado -> no conexo -> no es árbol
                }
            }

            // Si no tiene ciclos y es conexo -> es un árbol
            return true;
        }

        private static boolean tieneCiclos(int[][] g, int actual, int padre, boolean[] visitado) {
            visitado[actual] = true; // Marcamos el nodo actual como visitado  

            // Recorremos todos los vecinos del nodo actual
            for (int vecino : g[actual]) {
                if (!visitado[vecino]) {
                    if (tieneCiclos(g, vecino, actual, visitado)) {
                        return true;
                    }
                } else if (vecino != padre) {
                    // Si el vecino ya está visitado y no es el padre, significa que hay un ciclo
                    return true;
                }
            }
            return false; // No encontramos ciclos en esta rama
        }

        private static void postorden(int[][] g, int actual, int padre, boolean[] visitado, int[] postorden, int[] index) {
            visitado[actual] = true; // Marcamos el nodo actual como visitado

            // Recorremos los vecinos que no son el padre y que aún no hemos visitado
            for (int vecino : g[actual]) {
                if (vecino != padre && !visitado[vecino]) {
                    postorden(g, vecino, actual, visitado, postorden, index);
                }
            }

            // Una vez visitados todos los hijos, añadimos el nodo actual al recorrido postorden
            postorden[index[0]++] = actual;
        }
  //  }

    /*
     * Suposau que l'entrada és un mapa com el següent, donat com String per files (vegeu els tests)
     *
     *   _____________________________________
     *  |          #       #########      ####|
     *  |       O  # ###   #########  ##  ####|
     *  |    ####### ###   #########  ##      |
     *  |    ####  # ###   #########  ######  |
     *  |    ####    ###              ######  |
     *  |    ######################## ##      |
     *  |    ####                     ## D    |
     *  |_____________________________##______|
     *
     * Els límits del mapa els podeu considerar com els límits de l'array/String, no fa falta que
     * cerqueu els caràcters "_" i "|", i a més podeu suposar que el mapa és rectangular.
     *
     * Donau el nombre mínim de caselles que s'han de recorrer per anar de l'origen "O" fins al
     * destí "D" amb les següents regles:
     *  - No es pot sortir dels límits del mapa
     *  - No es pot passar per caselles "#"
     *  - No es pot anar en diagonal
     *
     * Si és impossible, retornau -1.
     */
    static int exercici4(char[][] mapa) {
      throw new UnsupportedOperationException("pendent");
    }

    /*
     * Aquí teniu alguns exemples i proves relacionades amb aquests exercicis (vegeu `main`)
     */
    static void tests() {

      final int[][] D2 = { {}, {} };
      final int[][] C3 = { {1, 2}, {0, 2}, {0, 1} };

      final int[][] T1 = { {1, 2}, {0}, {0} };
      final int[][] T2 = { {1}, {0, 2}, {1} };

      // Exercici 1
      // G té cicles?

      test(3, 1, 1, () -> !exercici1(D2));
      test(3, 1, 2, () -> exercici1(C3));

      // Exercici 2
      // Isomorfisme de grafs

      test(3, 2, 1, () -> exercici2(T1, T2));
      test(3, 2, 2, () -> !exercici2(T1, C3));

      // Exercici 3
      // Postordre

      test(3, 3, 1, () -> exercici3(C3, 1) == null);
      test(3, 3, 2, () -> Arrays.equals(exercici3(T1, 0), new int[] { 1, 2, 0 }));

      // Exercici 4
      // Laberint

      test(3, 4, 1, () -> {
        return -1 == exercici4(new char[][] {
            " #O".toCharArray(),
            "D# ".toCharArray(),
            " # ".toCharArray(),
        });
      });

      test(3, 4, 2, () -> {
        return 8 == exercici4(new char[][] {
            "###D".toCharArray(),
            "O # ".toCharArray(),
            " ## ".toCharArray(),
            "    ".toCharArray(),
        });
      });
    }
  }

  /*
   * Aquí teniu els exercicis del Tema 4 (Aritmètica).
   *
   * En aquest tema no podeu:
   *  - Utilitzar la força bruta per resoldre equacions: és a dir, provar tots els nombres de 0 a n
   *    fins trobar el que funcioni.
   *  - Utilitzar long, float ni double.
   *
   * Si implementau algun dels exercicis així, tendreu un 0 d'aquell exercici.
   */
  static class Tema4 {
    /*
     * Primer, codificau el missatge en blocs de longitud 2 amb codificació ASCII. Després encriptau
     * el missatge utilitzant xifrat RSA amb la clau pública donada.
     *
     * Per obtenir els codis ASCII del String podeu utilitzar `msg.getBytes()`.
     *
     * Podeu suposar que:
     * - La longitud de `msg` és múltiple de 2
     * - El valor de tots els caràcters de `msg` està entre 32 i 127.
     * - La clau pública (n, e) és de la forma vista a les transparències.
     * - n és major que 2¹⁴, i n² és menor que Integer.MAX_VALUE
     *
     * Pista: https://en.wikipedia.org/wiki/Exponentiation_by_squaring
     */
    static int[] exercici1(String msg, int n, int e) {
      //throw new UnsupportedOperationException("pendent");

      // guardar los códigos ASCII de cada letra del mensaje
            byte[] codigoASCII = msg.getBytes(); 
            

            // bloque de 2 en 2 con codificación ASCII
            int[] bloques = new int[msg.length() / 2];
            for (int i = 0; i < codigoASCII.length; i += 2) { // avanzar de 2 en 2
                bloques[i / 2] = (codigoASCII[i]) * 128 + (codigoASCII[i + 1]);
                
            }
            
            // encriptado de cada bloque
            int[] mensajeEncriptado = new int[bloques.length];
            
            for (int i = 0; i < mensajeEncriptado.length; i++){
                mensajeEncriptado[i] = expModular(bloques[i], e, n);
            }
            
            return mensajeEncriptado;
      
    }

          // Exponenciación modular 
        public static int expModular(int base, int exp, int mod) {
            int result = 1;
            int b = base % mod;

            while (exp > 0) {
                if (exp % 2 == 1) {
                    result = modMult(result, b, mod);
                }
                b = modMult(b, b, mod);
                exp = exp / 2;
            }

            return result;
        }
        
        // Multiplicación modular segura sin overflow
        public static int modMult(int a, int b, int mod) {
            int result = 0;
            a = a % mod;

            while (b > 0) {
                if (b % 2 == 1) {
                    result = (result + a) % mod;
                }
                a = (a + a) % mod;
                b = b / 2;
            }

            return result;
        }
    

    /*
     * Primer, desencriptau el missatge utilitzant xifrat RSA amb la clau pública donada. Després
     * descodificau el missatge en blocs de longitud 2 amb codificació ASCII (igual que l'exercici
     * anterior, però al revés).
     *
     * Per construir un String a partir d'un array de bytes podeu fer servir el constructor
     * `new String(byte[])`. Si heu de factoritzar algun nombre, ho podeu fer per força bruta.
     *
     * També podeu suposar que:
     * - La longitud del missatge original és múltiple de 2
     * - El valor de tots els caràcters originals estava entre 32 i 127.
     * - La clau pública (n, e) és de la forma vista a les transparències.
     * - n és major que 2¹⁴, i n² és menor que Integer.MAX_VALUE
     */
    static String exercici2(int[] m, int n, int e) {
      //throw new UnsupportedOperationException("pendent");
        // calcular la clave privada
            
            // calcular phi de n
            int phi = 1;
            for (int i = 2; i < n; i++){
                int [] euclides = algoritmoEuclides (i, n);
                if (euclides[0] == 1){ // el primer elemento es el MCD
                    phi++;
                }
            }
            
            // suponiendo que existe, calcular inversa de e mod phi de n
            int [] euclides = algoritmoEuclides(e, phi);
            int inversa = (euclides[1]%phi + phi) % phi; // asegurarse de que sea un número positivo
            
            // primero hacer el módulo
            for (int i = 0; i < m.length; i++){
                m[i] = expModular(m[i], inversa, n); 
            }
            
            // decodificar el mensaje
            String mensaje = "";
            
            for (int bloque : m) {
                int letra2 = bloque % 128;
                int letra1 = ((bloque - letra2) / 128) % 128;
                
                mensaje += (char) letra1;
                mensaje += (char) letra2;
            }
            
            //System.out.println("mensaje = " + mensaje);
            return mensaje;
      
    }

    public static int[] algoritmoEuclides(int a, int b){            
            int x0 = 1, y0 = 0;
            int x1 = 0, y1 = 1;

            while (b != 0){
                int q = a/b;
                int r = a % b;
                
                int x2 = x0 - q*x1;
                x0 = x1;
                x1 = x2;
                
                int y2 = y0 - q*y1;
                y0 = y1;
                y1 = y2;
                
                a = b;
                b = r;
            }
            
            // a es el MCD, y x0 e y0 los coeficientes
            int [] resultados = {a, x0, y0};
            return resultados;
        }
    

    static void tests() {
      // Exercici 1
      // Codificar i encriptar
      test(4, 1, 1, () -> {
        var n = 2*8209;
        var e = 5;

        var encr = exercici1("Patata", n, e);
        return Arrays.equals(encr, new int[] { 4907, 4785, 4785 });
      });

      // Exercici 2
      // Desencriptar i decodificar
      test(4, 2, 1, () -> {
        var n = 2*8209;
        var e = 5;

        var encr = new int[] { 4907, 4785, 4785 };
        var decr = exercici2(encr, n, e);
        return "Patata".equals(decr);
      });
    }
  }

  /*
   * Aquest mètode `main` conté alguns exemples de paràmetres i dels resultats que haurien de donar
   * els exercicis. Podeu utilitzar-los de guia i també en podeu afegir d'altres (no els tendrem en
   * compte, però és molt recomanable).
   *
   * Podeu aprofitar el mètode `test` per comprovar fàcilment que un valor sigui `true`.
   */
  public static void main(String[] args) {
    System.out.println("---- Tema 1 ----");
    Tema1.tests();
    System.out.println("---- Tema 2 ----");
    Tema2.tests();
    System.out.println("---- Tema 3 ----");
    Tema3.tests();
    System.out.println("---- Tema 4 ----");
    Tema4.tests();
  }

  // Informa sobre el resultat de p, juntament amb quin tema, exercici i test es correspon.
  static void test(int tema, int exercici, int test, BooleanSupplier p) {
    try {
      if (p.getAsBoolean())
        System.out.printf("Tema %d, exercici %d, test %d: OK\n", tema, exercici, test);
      else
        System.out.printf("Tema %d, exercici %d, test %d: Error\n", tema, exercici, test);
    } catch (Exception e) {
      if (e instanceof UnsupportedOperationException && "pendent".equals(e.getMessage())) {
        System.out.printf("Tema %d, exercici %d, test %d: Pendent\n", tema, exercici, test);
      } else {
        System.out.printf("Tema %d, exercici %d, test %d: Excepció\n", tema, exercici, test);
        e.printStackTrace();
      }
    }
  }
}

// vim: set textwidth=100 shiftwidth=2 expandtab :
