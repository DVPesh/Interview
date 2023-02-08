package ru.peshekhonov;

import ru.peshekhonov.config.JpaDaoConfig;
import ru.peshekhonov.dao.BookDao;
import ru.peshekhonov.dao.StudentDao;
import ru.peshekhonov.entities.Book;
import ru.peshekhonov.entities.Student;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    private static final JpaDaoConfig JPA_DAO_CONFIG = JpaDaoConfig.getInstance();

    public static void main(String[] args) {
        StudentDao studentDao = new StudentDao();
        BookDao bookDao = new BookDao();
        System.out.println("studentDao.getGenericTypes() = " + studentDao.getGenericTypes());
        System.out.println("bookDao.getGenericTypes() = " + bookDao.getGenericTypes());
        studentDao.getAll().forEach(studentDao::delete);
        System.out.println("studentDao.getAll() = " + studentDao.getAll());
        Student student = new Student();
        student.setName("Peter");
        student.setMark(4);
        System.out.println("transient student = " + student);
        studentDao.save(student);
        System.out.println("persistent student = " + student);
        student.setName("Dima");
        studentDao.save(student);
        System.out.println("studentDao.getAll() after persistent student set new name= " + studentDao.getAll());
        student.setId(student.getId() + 5);
        student.setMark(3);
        student.setName("Ura");
        studentDao.save(student);
        System.out.println("student after change id= " + student);
        System.out.println("studentDao.getAll() = " + studentDao.getAll());
        System.out.println("studentDao.update(student) = " + studentDao.update(student));
        System.out.println("studentDao.getAll() after update(student)= " + studentDao.getAll());
        System.out.println("student = " + student);
        List<Student> students = studentDao.getAll();
        studentDao.get(students.get(0).getId()).ifPresent(s -> {
            s.setName("Nick");
            s.setMark(5);
            studentDao.update(s);
        });
        System.out.println("studentDao.getAll() after update= " + studentDao.getAll());
        System.out.println("students.get(0) = " + students.get(0));
        studentDao.delete(students.get(0));
        System.out.println("studentDao.getAll() after delete= " + studentDao.getAll());
        System.out.println("student = " + student);

        bookDao.getAll().forEach(bookDao::delete);
        System.out.println("bookDao.getAll() = " + bookDao.getAll());
        Book book = new Book();
        book.setPrice(BigDecimal.valueOf(567.89));
        book.setTitle("Полный курс SQL");
        System.out.println("book = " + book);
        System.out.println("bookDao.update(book) = " + bookDao.update(book));
        System.out.println("book = " + book);
        JPA_DAO_CONFIG.getSession().getTransaction().begin();
        try {
            studentDao.get(studentDao.getAll().get(0).getId()).ifPresent(s -> {
                s.setMark(1);
                s.setName("Bob");
                studentDao.update(s);
            });
            book = new Book();
            book.setPrice(BigDecimal.valueOf(1000));
            book.setTitle("Тайны Вселенной");
            bookDao.save(book);
            bookDao.get(bookDao.getAll().get(0).getId()).ifPresent(bookDao::delete);
            JPA_DAO_CONFIG.getSession().getTransaction().commit();
        } catch (Exception e) {
            JPA_DAO_CONFIG.getSession().getTransaction().rollback();
            e.printStackTrace();
        }
        book = new Book();
        book.setTitle("Про Луну");
        bookDao.update(book);
        System.out.println("Before close session studentDao.getAll() = " + studentDao.getAll());
        System.out.println("Before close session bookDao.getAll() = " + bookDao.getAll());

        Integer id = studentDao.getAll().get(0).getId();
        System.out.println("******************* L1 cache before close Session *******************");
        System.out.println("1. studentDao.get(id).get() = " + studentDao.get(id).get());
        System.out.println("2. studentDao.get(id).get() = " + studentDao.get(id).get());

        JPA_DAO_CONFIG.closeSession();
        JPA_DAO_CONFIG.getSession();

        System.out.println("****************** L1 cache after new session open ******************");
        System.out.println("1. studentDao.get(id).get() = " + studentDao.get(id).get());
        System.out.println("2. studentDao.get(id).get() = " + studentDao.get(id).get());
        System.out.println("*********************************************************************");

        System.out.println("During new session studentDao.getAll() = " + studentDao.getAll());
        System.out.println("During new session bookDao.getAll() = " + bookDao.getAll());

        JPA_DAO_CONFIG.closeSession();
        JPA_DAO_CONFIG.closeSessionFactory();
    }
}
