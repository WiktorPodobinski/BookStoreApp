package V.book.store.service;

import V.book.store.entity.Book;
import V.book.store.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	@Autowired
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book getBookById(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
	}

	@Override
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}
	@Override
	public Book updateBook(Long id, Book book) {
		Book existingBook = getBookById(id);
		existingBook.setTitle(book.getTitle());
		existingBook.setAuthor(book.getAuthor());
		existingBook.setCategory(book.getCategory());
		return bookRepository.save(existingBook);
	}

	@Override
	public void deleteBook(Long id) {
		Book book = getBookById(id);
		bookRepository.delete(book);
	}
}
