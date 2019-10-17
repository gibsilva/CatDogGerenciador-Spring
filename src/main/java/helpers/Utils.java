/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

/**
 *
 * @author Girlaine
 */
public class Utils {

    public Utils() {
    }

    public static String removePontosBarraStr(String str) {
        String strFormatada = str.replaceAll("[./()-]", "");

        return strFormatada;
    }

    public String formatarValor(String valor) {
        String valorFormatado = valor.replace(".", "");
        return valorFormatado.replace(",", ".");
    }

    public String removeEspacoBranco(String str) {
        return str.replace(" ", "");
    }

    public String removeQuebraLinha(String str) {
        String texto = str.trim();
        texto = texto.replace("\n", "");

        return texto;
    }
}
