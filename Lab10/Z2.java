package lab10.z2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main (String[] args) throws IOException {
        Document doc = Jsoup.connect("https://cs.pollub.pl/staff/").get();

        doc.select("p").forEach((element) -> {
            Element sibling = element.previousElementSibling();
            System.out.println(sibling.text());
            Map<String, String> m = new HashMap<>();
            element.select("a").forEach((element1) -> {
                String text = element1.text();
                String cut_text = text.replaceAll(", prof. uczelni", "");
                String[] sep = cut_text.split(" ");
                m.put(text, sep[sep.length - 1]);
            });
            List<Map.Entry<String, String>> l = new ArrayList<>(m.entrySet());
            l.sort(Map.Entry.comparingByValue());
            for (Map.Entry<String, String> entry : l) {
                if (entry.getKey().contains("dr") || entry.getKey().contains("dr inż.")) {
                    System.out.println(entry.getKey());
                }
            }
            System.out.println();
        });
    }
}
