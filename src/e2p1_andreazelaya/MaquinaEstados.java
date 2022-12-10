package e2p1_andreazelaya;

import java.util.*;

public class MaquinaEstados {

    private ArrayList<String> estados = new ArrayList();
    private ArrayList<String> estados_aceptacion = new ArrayList();
    private ArrayList<String> aristas = new ArrayList();
    private String estado_actual;

    public MaquinaEstados(String estados, String aristas) {
        this.estados = (splitStr(estados, ';'));
        extractAcceptNodes();
        this.aristas = splitStr(aristas, ';');
        this.estado_actual = this.estados.get(0);
    }

    public ArrayList<String> splitStr(String str, char delim) {
        ArrayList<String> array = new ArrayList<>();
        String[] arregloStr = str.split(Character.toString(delim));

        for (int i = 0; i < arregloStr.length; i++) {
            array.add(arregloStr[i]);
        }

        return array;
    }

    public void extractAcceptNodes() {
        String temp = "";

        for (int i = 0; i < estados.size(); i++) {
            if (estados.get(i).contains(".")) {
                temp = estados.get(i).substring(1);
                estados_aceptacion.add(temp);
                estados.set(i, temp);
            }
        }
    }

    public String getArista(String str) {
        for (int i = 0; i < aristas.size(); i++) {
            if (aristas.get(i).contains(str)) {
                return aristas.get(i);
            }
        }

        return "";
    }

    public String computeStr(String str) {
        String cadena = "";
        estado_actual = estados.get(0);

        for (int i = 0; i < str.length(); i++) {
            String arist = getArista(estado_actual + ',' + str.charAt(i));
            if (!arist.equals("")) {
                estado_actual = arist.split(",")[2];
                cadena += arist.split(",")[0] + ':' + str.charAt(i) + "->" + arist.split(",")[2] + '\n';
            } else {
                cadena += "Rechazada";
                return cadena;
            }

        }
        if (estados_aceptacion.contains(estado_actual)) {
            cadena += "Aceptada";
            return cadena;
        } else {
            cadena += "Rechazada";
            return cadena;
        }
    }

}
