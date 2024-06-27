package CodeTestCoverJava;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Soundex {

    public static String generateSoundex(String name) {
        if (isEmptyString(name)) {
            return "";
        }
        StringBuilder soundex = buildSoundex(name);
        while (soundex.length() < 4) {
            soundex.append('0');
        }
        return soundex.toString();
    }
	
	public static StringBuilder buildSoundex(String name) {
		StringBuilder soundex = new StringBuilder();
        soundex.append(Character.toUpperCase(name.charAt(0)));
        char prevCode = getSoundexCode(name.charAt(0));
        for (int index = 1; checkLength(index, name.length(), soundex.length()); index++) {
            char code = getSoundexCode(name.charAt(index));
            if (doAppend(code, prevCode)) {
                soundex.append(code);
                prevCode = code;
            }
        }
        return soundex;
	}
	
	public static boolean checkLength(int index, int nameLength, int soundexLength) {
		return index < nameLength && soundexLength < 4;
	}
	
	public static boolean doAppend(char code, char prevCode) {
		return code != '0' && code != prevCode;
	}

    public static char getSoundexCode(char c) {
    	Map<Character, Character> characterMap = buildSoundexMap();
    	if(characterMap.containsKey(Character.toUpperCase(c))) {
    		return characterMap.get(Character.toUpperCase(c));
    	}
    	return '0';
    }
    
    public static Map<Character, Character> buildSoundexMap() {
    	Map<Character, Character> characterMap = new HashMap<>();
    	characterMap.putAll(populateSoundexMap(Arrays.asList('B', 'F', 'P', 'V'), '1'));
    	characterMap.putAll(populateSoundexMap(Arrays.asList('C', 'G', 'J', 'K', 'Q', 'S', 'X', 'Z'), '2'));
    	characterMap.putAll(populateSoundexMap(Arrays.asList('D', 'T'), '3'));
    	characterMap.putAll(populateSoundexMap(Arrays.asList('L'), '4'));
    	characterMap.putAll(populateSoundexMap(Arrays.asList('M', 'N'), '5'));
    	characterMap.putAll(populateSoundexMap(Arrays.asList('R'), '6'));
    	return characterMap;
    }
    
    public static Map<Character, Character> populateSoundexMap(List<Character> nameCharList, char code) {
    	Map<Character, Character> characterMap = new HashMap<>();
    	if(isEmptyList(nameCharList)) {
    		for(Character nameChar: nameCharList) {
    			characterMap.put(nameChar, code);
        	}
    	}
    	return characterMap;
    }
    
    public static boolean isEmptyString(String input) {
    	return input == null || input.isEmpty();
    }
    
    public static boolean isEmptyList(List<Character> inputList) {
    	return inputList != null && !inputList.isEmpty();
    }
}
