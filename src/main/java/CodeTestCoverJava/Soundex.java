package CodeTestCoverJava;

public class Soundex {

    public static String generateSoundex(String name) {
        if (StringUtils.isEmpty(name)) {
            return "";
        }
        StringBuilder soundex = buildSoundex(name);
        while (soundex.length() < 4) {
            soundex.append('0');
        }
        return soundex.toString();
    }
	
	private static StringBuilder buildSoundex(String name) {
		StringBuilder soundex = new StringBuilder();
        soundex.append(Character.toUpperCase(name.charAt(0)));
        char prevCode = getSoundexCode(name.charAt(0));
        for (int i = 1; i < name.length() && soundex.length() < 4; i++) {
            char code = getSoundexCode(name.charAt(i));
            if (isAppend(code, prevCode)) {
                soundex.append(code);
                prevCode = code;
            }
        }
        return soundex;
	}
	
	private static boolean isAppend(char code, char prevCode) {
		return code != '0' && code != prevCode;
	}

    private static char getSoundexCode(char c) {
    	Map<Character, Character> characterMap = buildSoundexMap();
    	if(characterMap.containsKey(Character.toUpperCase(c))) {
    		return characterMap.get(Character.toUpperCase(c));
    	}
    	return '0';
    }
    
    private static Map<Character, Character> buildSoundexMap() {
    	Map<Character, Character> characterMap = new HashMap<>();
    	characterMap.putAll(populateSoundexMap(Arrays.asList('B', 'F', 'P', 'V'), '1'));
    	characterMap.putAll(populateSoundexMap(Arrays.asList('C', 'G', 'J', 'K', 'Q', 'S', 'X', 'Z'), '2'));
    	characterMap.putAll(populateSoundexMap(Arrays.asList('D', 'T'), '3'));
    	characterMap.putAll(populateSoundexMap(Arrays.asList('L'), '4'));
    	characterMap.putAll(populateSoundexMap(Arrays.asList('M', 'N'), '5'));
    	characterMap.putAll(populateSoundexMap(Arrays.asList('R'), '6'));
    	return characterMap;
    }
    
    private static Map<Character, Character> populateSoundexMap(List<Character> nameCharList, char code) {
    	Map<Character, Character> characterMap = new HashMap<>();
    	nameCharList.stream().forEach(nameChar -> characterMap.put(nameChar, code));
    	return characterMap;
    }
}
