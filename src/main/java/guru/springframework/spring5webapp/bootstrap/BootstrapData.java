package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {


    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {


        //publisher
        Publisher blackForest = new Publisher();
        blackForest.setAddressLine("Opatijska");
        blackForest.setName("Black Forest");
        blackForest.setCity("Osijek");
        blackForest.setState("RH");
        blackForest.setZip(31000);

        Publisher oldChild = new Publisher();
        oldChild.setAddressLine("Umaška");
        oldChild.setName("Old Child");
        oldChild.setCity("Osijek");
        oldChild.setState("RH");
        oldChild.setZip(31000);


        //book raven, author Edgar
        Author edgar = new Author("Edgar","Allan-Poe");
        Book raven = new Book("Raven", "123456");
        edgar.getBooks().add(raven);
        raven.getAuthors().add(edgar);

        raven.setPublisher(blackForest);
        blackForest.getBooks().add(raven);

        //save data to db
        authorRepository.save(edgar);
        bookRepository.save(raven);
        publisherRepository.save(blackForest);

        //book necronomicon, author hp
        Author hp = new Author("Hp", "Lovecraft");
        Book necronomicon = new Book("Necronomicoin", "456789");
        hp.getBooks().add(necronomicon);
        necronomicon.getAuthors().add(hp);
        necronomicon.setPublisher(oldChild);
        oldChild.getBooks().add(necronomicon);

        //save data to db
        authorRepository.save(hp);
        bookRepository.save(necronomicon);
        publisherRepository.save(oldChild);


        //print
        System.out.println("Started in Bootstrap");
        System.out.println("Publisher count: " + publisherRepository.count());
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of authors: " + authorRepository.count());
        System.out.println("Number of books from " + blackForest.getName() + " :" +  + blackForest.getBooks().size());
        System.out.println("Number of books from " + oldChild.getName() + " :" +  + oldChild.getBooks().size());

    }
}
