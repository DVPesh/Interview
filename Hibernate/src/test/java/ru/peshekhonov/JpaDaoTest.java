package ru.peshekhonov;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.peshekhonov.config.JpaDaoConfig;
import ru.peshekhonov.dao.BookDao;
import ru.peshekhonov.dao.StudentDao;
import ru.peshekhonov.entities.Book;
import ru.peshekhonov.entities.Student;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class JpaDaoTest {

    private BookDao bookDao;
    private StudentDao studentDao;
    private JpaDaoConfig jpaDaoConfig;

    @BeforeEach
    public void init() {
        jpaDaoConfig = JpaDaoConfig.getInstance();
        bookDao = new BookDao();
        studentDao = new StudentDao();
    }

    @AfterEach
    public void finish() {
        bookDao = null;
        studentDao = null;
        jpaDaoConfig.closeSession();
        jpaDaoConfig.closeSessionFactory();
    }

    @Test
    public void testDeleteMethod() {
        List<Book> books = null;
        List<Student> students = null;
        try {
            jpaDaoConfig.getSession().getTransaction().begin();
            books = createBooks();
            students = createStudents();
            books.forEach(bookDao::save);
            students.forEach(studentDao::save);
            jpaDaoConfig.getSession().getTransaction().commit();
        } catch (Exception e) {
            jpaDaoConfig.getSession().getTransaction().rollback();
        }
        jpaDaoConfig.closeSession();
        jpaDaoConfig.getSession();
        Assertions.assertTrue(bookDao.getAll().size() >= 1000);
        Assertions.assertTrue(studentDao.getAll().size() >= 1000);
        Long bookId = bookDao.getAll().get(500).getId();
        Assertions.assertDoesNotThrow(() -> bookDao.get(bookId).get());
        bookDao.delete(bookDao.get(bookId).get());
        Assertions.assertThrowsExactly(NoSuchElementException.class, () -> bookDao.get(bookId).get());
        Integer studentId = studentDao.getAll().get(500).getId();
        Assertions.assertDoesNotThrow(() -> studentDao.get(studentId).get());
        studentDao.delete(studentDao.get(studentId).get());
        Assertions.assertThrowsExactly(NoSuchElementException.class, () -> studentDao.get(studentId).get());
        try {
            jpaDaoConfig.getSession().getTransaction().begin();
            deleteAllBooks();
            deleteAllStudents();
            jpaDaoConfig.getSession().getTransaction().commit();
        } catch (Exception e) {
            jpaDaoConfig.getSession().getTransaction().rollback();
        }
        Assertions.assertEquals(0, bookDao.getAll().size());
        Assertions.assertEquals(0, studentDao.getAll().size());
    }

    @Test
    public void testSaveMethod() {
        List<Book> books = null;
        List<Student> students = null;
        try {
            jpaDaoConfig.getSession().getTransaction().begin();
            deleteAllBooks();
            deleteAllStudents();
            books = createBooks();
            students = createStudents();
            books.forEach(bookDao::save);
            students.forEach(studentDao::save);
            jpaDaoConfig.getSession().getTransaction().commit();
        } catch (Exception e) {
            jpaDaoConfig.getSession().getTransaction().rollback();
        }
        Assertions.assertEquals(1000, bookDao.getAll().size());
        Assertions.assertEquals(1000, studentDao.getAll().size());
        jpaDaoConfig.closeSession();
        jpaDaoConfig.getSession();
        Assertions.assertIterableEquals(books, bookDao.getAll());
        Assertions.assertIterableEquals(students, studentDao.getAll());
    }

    @Test
    public void testUpdateMethod() {
        List<Book> books = null;
        List<Student> students = null;
        try {
            jpaDaoConfig.getSession().getTransaction().begin();
            deleteAllBooks();
            deleteAllStudents();
            books = createBooks();
            students = createStudents();
            books.forEach(bookDao::save);
            students.forEach(studentDao::save);
            jpaDaoConfig.getSession().getTransaction().commit();
        } catch (Exception e) {
            jpaDaoConfig.getSession().getTransaction().rollback();
        }
        Assertions.assertEquals(1000, bookDao.getAll().size());
        Assertions.assertEquals(1000, studentDao.getAll().size());
        jpaDaoConfig.closeSession();
        jpaDaoConfig.getSession();
        Assertions.assertIterableEquals(books, bookDao.getAll());
        Assertions.assertIterableEquals(students, studentDao.getAll());
        jpaDaoConfig.closeSession();
        jpaDaoConfig.getSession();
        try {
            jpaDaoConfig.getSession().getTransaction().begin();
            final Random random = new Random();
            books.forEach(b -> {
                b.setTitle(b.getTitle() + random.nextInt(1000));
                b.setPrice(BigDecimal.valueOf(random.nextInt(500, 2000)));
                bookDao.update(b);
            });
            students.forEach(s -> {
                s.setName(s.getName() + random.nextInt(2000));
                s.setMark(random.nextInt(5) + 1);
                studentDao.update(s);
            });
            jpaDaoConfig.getSession().getTransaction().commit();
        } catch (Exception e) {
            jpaDaoConfig.getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        Assertions.assertEquals(1000, bookDao.getAll().size());
        Assertions.assertEquals(1000, studentDao.getAll().size());
        jpaDaoConfig.closeSession();
        jpaDaoConfig.getSession();
        Assertions.assertIterableEquals(books, bookDao.getAll());
        Assertions.assertIterableEquals(students, studentDao.getAll());
    }

    @Test
    public void testGetMethod() {
        List<Book> books = null;
        List<Student> students = null;
        try {
            jpaDaoConfig.getSession().getTransaction().begin();
            deleteAllBooks();
            deleteAllStudents();
            books = createBooks();
            students = createStudents();
            books.forEach(bookDao::save);
            students.forEach(studentDao::save);
            jpaDaoConfig.getSession().getTransaction().commit();
        } catch (Exception e) {
            jpaDaoConfig.getSession().getTransaction().rollback();
        }
        Assertions.assertEquals(1000, bookDao.getAll().size());
        Assertions.assertEquals(1000, studentDao.getAll().size());
        jpaDaoConfig.closeSession();
        jpaDaoConfig.getSession();
        books.forEach(b -> {
            Assertions.assertDoesNotThrow(() -> bookDao.get(b.getId()).get());
            Assertions.assertEquals(b, bookDao.get(b.getId()).get());
        });
        students.forEach(s -> {
            Assertions.assertDoesNotThrow(() -> studentDao.get(s.getId()).get());
            Assertions.assertEquals(s, studentDao.get(s.getId()).get());
        });
    }

    private List<Book> createBooks() {
        final Random random = new Random();
        List<Book> books = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            Book book = new Book();
            book.setTitle("book" + i);
            if (i % 100 != 0) {
                book.setPrice(BigDecimal.valueOf(random.nextInt(300, 2500)));
            }
            books.add(book);
        }
        return books;
    }

    private List<Student> createStudents() {
        final Random random = new Random();
        List<Student> students = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            Student student = new Student();
            student.setName("name" + i);
            if (i % 100 != 0) {
                student.setMark(random.nextInt(5) + 1);
            }
            students.add(student);
        }
        return students;
    }

    private void deleteAllBooks() {
        bookDao.getAll().forEach(bookDao::delete);
    }

    private void deleteAllStudents() {
        studentDao.getAll().forEach(studentDao::delete);
    }
}
