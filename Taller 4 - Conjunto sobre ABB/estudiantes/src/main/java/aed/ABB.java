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
        T res = actual.valor;
        while (actual.izq != null){
            actual = actual.izq;
        }
        return res;
    }

    public T maximo(){
        Nodo actual = this._raiz;
        T res = actual.valor;
        while (actual.der != null){
            actual = actual.der;
        }
        return res;
    }

    public void insertar(T elem){
        Nodo actual = this._raiz;
        T val = actual.valor;
        if (pertenece(elem)){
            //
        }else{
            if (actual == null){
                this._raiz = new Nodo(val);
            }else{
            if (elem.compareTo(val)<0){
                while (elem.compareTo(val)<0){
                    actual = actual.izq;
                }
                actual.der.izq.valor = elem;
            }else{
                while (elem.compareTo(val)>0){
                    actual = actual.der;
                }
                actual.izq.der.valor = elem;
            }
        }
        this._cardinal = this._cardinal + 1;
    }
    }
    


    public boolean pertenece(T elem){
        Nodo actual = this._raiz;
        T val = actual.valor;
        boolean res = false;
        
        if (actual == null ){
            res = false;
        }else{
            if (elem.compareTo(val)>0){
                actual = actual.der;
                res= pertenece (elem);
            }else {
                actual = actual.izq;
                res= pertenece (elem);
            }
        }
        return res;
    }

    
    public void eliminar(T elem){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {            
            throw new UnsupportedOperationException("No implementada aun");
        }
    
        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
