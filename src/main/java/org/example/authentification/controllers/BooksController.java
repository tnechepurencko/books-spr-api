package org.example.authentification.controllers;

import org.example.authentification.entity.Fulltext;
import org.example.authentification.entity.Book;
import org.example.authentification.entity.Story;
import org.example.authentification.repository.FulltextsRepo;
import org.example.authentification.repository.BooksRepo;
import org.example.authentification.repository.StoryRepo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/v1/books")
public class BooksController {
    @Autowired private FulltextsRepo fulltextsRepo;
    @Autowired private BooksRepo bookRepo;
    @Autowired private StoryRepo storyRepo;

    @GetMapping("/getfeed")
    public ResponseEntity<String> getRecs(@RequestParam(name = "page") int page, @RequestParam(name = "amount") int amount) { // params required? // where is jwt?
        int from = (page - 1) * amount + 1;
        int to = from + amount - 1;

        return ResponseEntity.status(HttpStatus.OK).body(processRecs(from, to));
    }

    @GetMapping("/getbooks")
    public ResponseEntity<String> getBooks(@RequestParam(name = "fulltextid") String fullTextId) { // need jwt?
        Optional<Fulltext> booksRes = fulltextsRepo.findById(Long.valueOf(fullTextId));

        if (booksRes.isEmpty()) {
            throw new UsernameNotFoundException("Could not find books with id = " + fullTextId);
        }

        Fulltext ft = booksRes.get();
        return ResponseEntity.status(HttpStatus.OK).body(ft.getText());
    }

    private String processRecs(int from, int to) {
        List<Book> books = bookRepo.getPage(from, to);
        JSONArray booksArray = new JSONArray();

        for (Book n : books) {
            JSONObject booksObj = new JSONObject();
            booksObj.put("id", n.getId());
            booksObj.put("title", n.getTitle());

            List<Story> stories = storyRepo.getStories(n.getId());
            booksObj.put("amount", stories.size());

            JSONArray storiesArray = new JSONArray();
            for (Story s : stories) {
                JSONObject storyObj = new JSONObject();
                storyObj.put("id", s.getId());
                storyObj.put("title", s.getTitle());
                storyObj.put("img", s.getImgSrc());
                storyObj.put("fulltextpreview", s.getFulltextPreview());
                storyObj.put("fulltext", s.getFulltextId());
                storyObj.put("isnew", s.isNew());

                storiesArray.put(storyObj);
            }

            booksObj.put("stories", storiesArray);
            booksArray.put(booksObj);
        }

        return booksArray.toString();
    }

//    @GetMapping("/info")
//    public User getUserDetails() {
//        String id = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return userRepo.findById(Long.parseLong(id)).get();
//    }
//
//    @GetMapping("/helloworld")
//    public String greeting() {
//        return "hello world";
//    }
//
//    @GetMapping("/trends/getImage")
//    public ResponseEntity<byte[]> fromClasspathAsResEntity() throws IOException {
//        String filePath = "5307711608371398929.jpg";
//        File file = new File(filePath);
//        byte[] fileContent = Files.toByteArray(file);
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(fileContent);
//    }
}
