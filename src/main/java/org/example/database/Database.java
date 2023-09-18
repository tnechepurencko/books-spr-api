package org.example.database;

import org.example.authentification.entity.Fulltext;
import org.example.authentification.entity.Book;
import org.example.authentification.entity.Story;
import org.example.authentification.repository.FulltextsRepo;
import org.example.authentification.repository.BooksRepo;
import org.example.authentification.repository.StoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/v1/test")
public class Database {
    @Autowired private BooksRepo booksRepo;
    @Autowired private StoryRepo storyRepo;
    @Autowired private FulltextsRepo fulltextsRepo;

    @PostMapping("/createMysqlSample")
    public void fillBooks() {
        Random random = new Random();
        long sid = 1;

        for (int j = 0; j < 23; j++) {
            for (int i = 0; i < 17; i++) {
                long fulltextId = random.nextLong();

                Story story = new Story(sid++, (long) j,
                        "title:" + random.nextInt(),
                        "img:" + random.nextInt(),
                        "ftp:" + random.nextInt(),
                        fulltextId,
                        random.nextBoolean(),
                        "src:" + random.nextInt(),
                        new Date());
                storyRepo.save(story);

                Fulltext fulltext = new Fulltext(fulltextId, "ft:" + random.nextInt());
                fulltextsRepo.save(fulltext);
            }

            Book book = new Book((long) j, new Date(), (long) random.nextInt(17 * j, 17 * (j + 1)),
                    "title:" + random.nextInt());

            booksRepo.save(book);
        }
    }
}