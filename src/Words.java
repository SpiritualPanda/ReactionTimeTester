import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Words {

    private final ArrayList<String> wordList;

    public ArrayList<String> getList() {
        return wordList;
    }

    public String randomWord() {
        int index = (int) (Math.random() * wordList.size());
        return wordList.get(index);
    }

    public Words() {

        wordList = new ArrayList<>();

        try {
            File file = new File("C:\\Users\\James\\ReactionTime\\src\\resources\\engmix");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String word = sc.nextLine();
                if (word.length() <= 9)
                    wordList.add(word);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
