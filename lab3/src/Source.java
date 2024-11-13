import java.util.Scanner;

public class Source {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "res/dictionary.txt";
        try {
            Translator translator = new Translator(filePath);
            System.out.println("Введите текст для перевода:");
            String inputText = scanner.nextLine();
            String translatedText = translator.translate(inputText);
            System.out.println("Перевод: " + translatedText);
        } catch (FileReadException | InvalidFileFormatException e) {
            System.err.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
