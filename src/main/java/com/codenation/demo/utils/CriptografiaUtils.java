package com.codenation.demo.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class CriptografiaUtils {

	public static String decriptCesar(int chave, String textoCifrado){
		textoCifrado = textoCifrado.toLowerCase();
		String text = "";
		int tamanhoTexto = textoCifrado.length();
		int min = 97;
		int max = 122;

		for (int c = 0; c < tamanhoTexto; c++) {
			int caracter = ((int) textoCifrado.charAt(c));
			if (caracter < min || caracter > max){
				text += (char) caracter;
			}else{
				int ASCII = ((int) textoCifrado.charAt(c)) - chave;

				if (ASCII < min){
					ASCII = max - (min - ASCII)+1;
				}
				text += (char) ASCII;
			}
		}

		return text;
	}

    public static String toSHA1(String value){    	
        return DigestUtils.shaHex(value);
    }

}
