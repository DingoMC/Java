package lab10.z3;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static int getPages () throws IOException {
        Document doc = Jsoup.connect("https://weii.pollub.pl/aktualnosci/").get();
        Elements el_list = doc.select("ul.pagination");
        Element ul = el_list.get(0);
        Element li = ul.children().get(ul.children().size() - 2);
        AtomicInteger x = new AtomicInteger();
        li.select("a").forEach(element -> {
            try {
                x.set(Integer.parseInt(element.text().trim()));
            } catch (NumberFormatException e) {
                x.set(0);
            }
        });
        System.out.println("Liczba stron: " + x.get());
        return x.get();
    }

    public static void main (String[] args) throws IOException {
        int pages = getPages();
        List<Map.Entry<String, String>> events = new ArrayList<>();
        for (int i = pages; i >= 1; i--) {
            Document doc = Jsoup.connect("https://weii.pollub.pl/aktualnosci/page" + i + ".html").get();
            doc.select("div.news-content").forEach((element) -> {
                Elements date = element.select("div.text-primary");
                Elements content = element.select("a");
                content.forEach(element1 -> {
                    if (element1.text().contains("Godziny wolne") || element1.text().contains("Godziny dziekańskie")) {
                        date.forEach(e -> {
                            events.add(Map.entry(e.text(), element1.text()));
                            System.out.print(e.text() + " - ");
                        });
                        System.out.println(element1.text());
                    }
                });
            });
        }
    }
}
