package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    public static Service service;

    @org.junit.jupiter.api.BeforeAll
    public static void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();
        Validator<Grade> gradeValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        HomeworkXMLRepository fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "homework.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(gradeValidator, "grades.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void findAllStudents() {
        Iterable<Student> originalStudents = service.findAllStudents();
        long originalCount = StreamSupport.stream(originalStudents.spliterator(), false).count();

        Student student = new Student("99", "Kis Pal", 5);
        int result = service.saveStudent(student.getID(), student.getName(), student.getGroup());

        Iterable<Student> newStudent = service.findAllStudents();
        long newCount = StreamSupport.stream(newStudent.spliterator(), false).count();

        assertTrue(originalCount + 1 == newCount);

        service.deleteHomework(student.getID());
    }

    @org.junit.jupiter.api.Test
    void findAllHomework() {
        Iterable<Homework> originalHw = service.findAllHomework();
        long originalCount = StreamSupport.stream(originalHw.spliterator(), false).count();

        Homework hw = new Homework("47", "Test HW", 6, 2);
        int result = service.saveHomework(hw.getID(), hw.getDescription(), hw.getDeadline(), hw.getStartline());

        Iterable<Homework> newHw = service.findAllHomework();
        long newCount = StreamSupport.stream(originalHw.spliterator(), false).count();

        assertTrue(originalCount + 1 == newCount);

        service.deleteHomework(hw.getID());
    }

    @org.junit.jupiter.api.Test
    void findAllGrades() {
    }

    @org.junit.jupiter.api.Test
    void saveStudent() {
        Student student = new Student("99", "Kis Pal", 5);
        int result = service.saveStudent(student.getID(), student.getName(), student.getGroup());
        assertEquals(1, result);
        service.deleteStudent(student.getID());
    }

    @org.junit.jupiter.api.Test
    void saveHomework() {
        Homework hw = new Homework("143", "Test HW", 6, 2);
        int result = service.saveHomework(hw.getID(), hw.getDescription(), hw.getDeadline(), hw.getStartline());
        assertEquals(1, result);
        service.deleteHomework(hw.getID());
    }

    @org.junit.jupiter.api.Test
    void saveGrade() {
    }

    @org.junit.jupiter.api.Test
    void deleteStudent() {
        Student student = new Student("99", "Kis Pal", 5);
        service.saveStudent(student.getID(), student.getName(), student.getGroup());
        int result = service.deleteStudent(student.getID());
        assertEquals(1, result);
    }

    @org.junit.jupiter.api.Test
    void deleteHomework() {
        Homework hw = new Homework("143", "Test HW", 6, 2);
        service.saveHomework(hw.getID(), hw.getDescription(), hw.getDeadline(), hw.getStartline());
        int result = service.deleteHomework(hw.getID());
        assertEquals(1, result);
    }

    @org.junit.jupiter.api.Test
    void updateStudent() {
    }

    @org.junit.jupiter.api.Test
    void updateHomework() {
    }

    @org.junit.jupiter.api.Test
    void extendDeadline() {
    }

    @org.junit.jupiter.api.Test
    void createStudentFile() {
    }
}