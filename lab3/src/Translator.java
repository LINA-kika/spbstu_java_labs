import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

// N - количество символов в тексте
// M - количество значений в дереве
public class Translator {
    TreeMap<String, String> dictionary;

    public Translator(String dictPath) throws InvalidFileFormatException, FileReadException {
        dictionary = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        fillTheDictionary(dictPath);
    }

    private void fillTheDictionary(String path) throws InvalidFileFormatException, FileReadException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 2) {
                    throw new InvalidFileFormatException("");
                }
                String word = parts[0];
                String translation = parts[1];
                dictionary.put(word, translation);
            }

        } catch (IOException e) {
            throw new FileReadException(e.getMessage());
        }
        System.out.println(dictionary.toString());
    }

    // O(w*log(m)*k(t+n))
    public String translate(String text) {
        String cleanedText = text.replaceAll("[\\p{Punct}]", ""); // O(n)
        String[] words = cleanedText.split(" "); // O(n)
        StringBuilder translated_text = new StringBuilder();
        int skip_next = 0;

        for (int i = 0; i < words.length; i++) { // O(w), w - количество слов в тексте
            if (skip_next > 0) { // пропуск слова
                skip_next--;
                continue;
            }
            List<String> translationVars = getTranslationVars(words[i]); // O(log(m))
            String translation = translationVars.get(0); // дефолтный перевод
            // поиск альтернативных переводов
            if (translationVars.size() > 1) {
                Pair translationPair = findBestTranslation(words, i, translationVars);
                skip_next = translationPair.tLength > 1 ? translationPair.tLength-1 : 0;
                translation = translationPair.translation;
            }
            translated_text.append(translation);
            translated_text.append(" ");
        }

        return translated_text.toString().trim();
    }


    // O(k*(t+n)) k - размер translationVars, t - длина перевода, n - длина текста для перевода
    private Pair findBestTranslation(String[] words, int index, List<String> translationVars) {
        String originalWord = words[index];
        Pair result = new Pair(dictionary.get(words[index]), 1); // по дефолту первый вариант
        String wordCombination = originalWord;
        int currentTranslationVarSize;
        for (int k = 0; k < translationVars.size(); k++) { //O(k)
            currentTranslationVarSize = translationVars.get(k).split(" ").length; // расчёт длины последовательности предпологаемого перевода
            if (index + currentTranslationVarSize < words.length) {
                wordCombination = String.join(" ", Arrays.copyOfRange(words, index, index + currentTranslationVarSize)); // составление последовательности слов такой же длины из текста
            }
            if (wordCombination.equals(translationVars.get(k))) {
                result = new Pair(dictionary.get(translationVars.get(k)), currentTranslationVarSize);
            }
            else{
                break; //при первом несовпадении выход, т.к. правое поддерево всегда по значению больше
            }
        }
        return result;
    }

    private List<String> getTranslationVars(String word) {
        if (!dictionary.containsKey(word)) { // O(log(m))
            return Collections.singletonList(word); // возвращаем оригинальное слово
        }
        List<String> translations = new ArrayList<>();
        // Получаем правое поддерево
        translations.addAll(getRightSubTreeTranslations(word)); // O(log(m))
        // Получаем правое поддерево, если оно существует
        String parent = dictionary.higherKey(word);
        if (parent != null) {
            translations.addAll(getRightSiblingTranslations(parent)); // O(log(m))
        }
        return translations;
    }

    private List<String> getRightSubTreeTranslations(String word) {
        NavigableMap<String, String> rightSubTree = dictionary.tailMap(word, true); // O(log(m))
        return new ArrayList<>(rightSubTree.keySet());
    }

    private List<String> getRightSiblingTranslations(String parent) {
        NavigableMap<String, String> rightSiblingSubtree = dictionary.tailMap(parent, false); // O(log(m))
        return new ArrayList<>(rightSiblingSubtree.keySet());
    }

    public record Pair(String translation, Integer tLength) {
    }

}
