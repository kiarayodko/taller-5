package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    private Nodo _raiz;
    private int _cardinal;

    private class Nodo {
        // Agregar atributos privados del Nodo
      

        // Crear Constructor del nodo
            T valor;
            Nodo izq;
            Nodo der;
            Nodo padre;

            Nodo (T v){
                valor= v;
                izq= null;
                der= null;
            
        }
    }

    public ABB() {
        this._raiz = null;
        this._cardinal = 0;
    }

    public int cardinal() {
        return this._cardinal;
    }

    public T minimo(){
        Nodo actual = this._raiz;
       
        while (actual.izq != null){
            actual = actual.izq;
        }
        return actual.valor;
    }

    public T maximo(){
        Nodo actual = this._raiz;
      
        while (actual.der != null){
            actual = actual.der;
        }
        return actual.valor;
    }

    private Nodo buscador (Nodo n, T elem){
        if (n.valor.equals(elem)){
            return n;
        }else if(elem.compareTo(n.valor)>0){
            if (n.der == null){
                return n;
            }else{
                return buscador(n.der, elem);
            }
                        
        }else {
            if (n.izq == null){
                return n;
            }else{
                return buscador(n.izq, elem);
            }
        }
    }

    public void insertar(T elem){
        Nodo insert = new Nodo(elem);
        

        if (this._raiz == null){            
            this._raiz = insert; 
            this._cardinal++;     

        }else if (!(this.pertenece(elem))){
            Nodo ultNodo = buscador(this._raiz, elem);

            if (!(ultNodo.valor.equals(elem ))) {
            
                if (elem.compareTo(ultNodo.valor) > 0){
                    ultNodo.der = insert;
                }else{
                    ultNodo.izq = insert;
                }
    
                insert.padre = ultNodo;
                this._cardinal++;
            }
        }
            
    }
    
    

    public boolean pertenece(T elem){
        boolean res = false;
        Nodo actual = this._raiz;
        
        if (actual == null ){
            res = false;
        }else{
            Nodo ultimo =  buscador(actual, elem);
            res = ultimo.valor.equals(elem);
        
        }
            
        return res;
    }

    
    public void eliminar(T elem){
        Nodo actual = buscador (this._raiz, elem);
        this._cardinal--;

        if (cantidadHijos(actual)==0){
            if (actual.padre != null){
                if (elem.compareTo(actual.padre.valor)>0){
                    actual.padre.der = null;
                }else{
                    actual.padre.izq = null;
                }
            }else{
                this._raiz = null;
            }
        } else if (cantidadHijos (actual)==1){
            if (actual.padre == null){
                if (actual.der != null){
                    this._raiz = actual.der;
                }else{
                    this._raiz = actual.izq;
                }
            }else{
                if (elem.compareTo(actual.padre.valor)>0){
                    if (actual.der != null){
                        actual.padre.der = actual.der;
                    }else{
                        if ((actual.izq.valor).compareTo(actual.padre.valor)>0){
                            actual.padre.der = actual.izq;
                        }else{
                            if (actual.padre.izq == null){
                                actual.padre.izq = actual.izq;
                            }else{
                                Nodo reemplazo = new Nodo(actual.padre.izq.valor);
                                actual.padre.izq = reemplazo;
                                reemplazo.der = actual.padre;
                                reemplazo.izq = actual.izq;

                            }
                        }
                        }
                    }else{
                        if (actual.izq != null){
                            actual.padre.izq = actual.izq;
                        }else{
                            if ((actual.der.valor).compareTo(actual.padre.valor)>0){
                                actual.padre.der = actual.der;
                            }else{
                                if (actual.padre.der == null){
                                    actual.padre.der = actual.der;
                                }else{
                                    Nodo reemplazo = new Nodo(actual.padre.der.valor);
                                    actual.padre.der = reemplazo;
                                    reemplazo.izq = actual.padre;
                                    reemplazo.der = actual.der;
    
                                }
                            }
                            }

                    }
                }


            }else{
                Nodo reemplazo = predecesorInmediato(actual.izq);
                
                actual.valor = reemplazo.valor;


                if(reemplazo.valor.compareTo(reemplazo.padre.valor) > 0){
                    reemplazo.padre.der = reemplazo.izq;
                    if(reemplazo.izq != null){
                        reemplazo.izq.padre = reemplazo.padre;
                    }
                }else{
                    reemplazo.padre.izq = reemplazo.izq;
                    if(reemplazo.izq != null){
                        reemplazo.izq.padre = actual;
                    }
                }
            }
        }

    


            
        
        
    

    private int cantidadHijos (Nodo n){
        
        if (n.der == null && n.izq == null){
            return 0;
        }else if (n.der != null && n.izq != null){
            return 2;
        }else{
            return 1;
        }
    }

    private Nodo predecesorInmediato (Nodo n){
        if (n.der == null){
            return n;
        }else{
            return predecesorInmediato(n.der);
        }

    }


    public String toString(){
        String res = "{";
        
        while (this._cardinal>0){
            res = res + this.minimo().toString() + ",";
            this.eliminar(this.minimo());
        }
        res= res + this._raiz.valor.toString() + "}";
        return res;
    }

    private Nodo sucesor (Nodo n){
        Nodo actual = n;
        if (n.der != null){
            actual = n.der;
            while (actual.izq != null){
                actual = actual.izq;
            }
            return actual;
        }else if (n.padre !=null){
            
            actual = actual.padre;

            while (n.valor.compareTo(actual.valor) > 0 && actual.padre != null){
                actual = actual.padre;
            }
            
            if(n.valor.compareTo(actual.valor) > 0){
                Nodo nuevo = new Nodo(null);
                return nuevo;
            }else{
                return actual;
            }

        }else{
            Nodo nuevo = new Nodo(null);
            return nuevo;
        }
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {            
            return (sucesor(_actual).valor != null);
        }
    
        public T siguiente() {
            return sucesor(_actual).valor;
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
