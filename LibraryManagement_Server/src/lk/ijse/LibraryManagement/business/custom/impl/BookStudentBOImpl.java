package lk.ijse.LibraryManagement.business.custom.impl;

import lk.ijse.LibraryManagement.business.custom.BookStudentBO;
import lk.ijse.LibraryManagement.dto.BookDTO;
import lk.ijse.LibraryManagement.dto.BookStudentDTO;
import lk.ijse.LibraryManagement.dto.PositionDTO;
import lk.ijse.LibraryManagement.dto.StudentDTO;
import lk.ijse.LibraryManagement.entity.Book;
import lk.ijse.LibraryManagement.entity.BookStudent;
import lk.ijse.LibraryManagement.entity.Position;
import lk.ijse.LibraryManagement.entity.Student;
import lk.ijse.LibraryManagement.repo.RepositoryFactory;
import lk.ijse.LibraryManagement.repo.custom.StudentBookRepository;
import lk.ijse.LibraryManagement.resources.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class BookStudentBOImpl implements BookStudentBO {

    private StudentBookRepository studentBookRepository;

    public BookStudentBOImpl() {

        studentBookRepository=(StudentBookRepository) RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryTypes.STUDENTBOOK);
    }

    @Override
    public boolean saveBookStudent(BookStudentDTO bookStudentDTO, BookDTO bookDTO, StudentDTO studentDTO, PositionDTO positionDTO) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            session.beginTransaction();
            studentBookRepository.setSession(session);
            BookStudent bookStudent=new BookStudent(new Book(bookDTO.getBid(),new Position(positionDTO.getPosId(),positionDTO.getDvNo(),positionDTO.getRackNo(),positionDTO.getRowNo(),positionDTO.getColNo()),bookDTO.getCategory(),bookDTO.getMachiningNo(),bookDTO.getReceiveDate(),bookDTO.getBatchNo(),bookDTO.getBookName(),bookDTO.getPublisher(),bookDTO.getPublishDate(),bookDTO.getPages(),bookDTO.getPrice(),bookDTO.getSuplier(),bookDTO.getRemovedDate(),bookDTO.getOther()), new Student(studentDTO.getSid(),studentDTO.getRegId(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getBday(),studentDTO.getGrade(),studentDTO.getPhoto()), bookStudentDTO.getBorrowDate(),bookStudentDTO.getReturnDate(),bookStudentDTO.getNumber());
            boolean isSave=false;
            if(studentBookRepository.save(bookStudent)){
                isSave=true;
                session.getTransaction().commit();
                session.close();
            }else{
                session.getTransaction().rollback();
                session.close();
            }
            return isSave;
        }
    }

    @Override
    public boolean updateBookStudent(BookStudentDTO bookStudentDTO, BookDTO bookDTO, StudentDTO studentDTO, PositionDTO positionDTO) throws Exception {


        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            session.beginTransaction();
            studentBookRepository.setSession(session);
            BookStudent bookStudent=new BookStudent(bookStudentDTO.getBookStudentId(),new Book(bookDTO.getBid(),new Position(positionDTO.getPosId(),positionDTO.getDvNo(),positionDTO.getRackNo(),positionDTO.getRowNo(),positionDTO.getColNo()),bookDTO.getCategory(),bookDTO.getMachiningNo(),bookDTO.getReceiveDate(),bookDTO.getBatchNo(),bookDTO.getBookName(),bookDTO.getPublisher(),bookDTO.getPublishDate(),bookDTO.getPages(),bookDTO.getPrice(),bookDTO.getSuplier(),bookDTO.getRemovedDate(),bookDTO.getOther()), new Student(studentDTO.getSid(),studentDTO.getRegId(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getBday(),studentDTO.getGrade(),studentDTO.getPhoto()), bookStudentDTO.getBorrowDate(),bookStudentDTO.getReturnDate(),bookStudentDTO.getNumber());
            boolean isUpdate=false;
            if(studentBookRepository.update(bookStudent)){
                isUpdate=true;
                session.getTransaction().commit();
                session.close();
            }else{
                session.getTransaction().rollback();
                session.close();
            }
            return isUpdate;
        }
    }

    @Override
    public boolean removeBookStudent(int bid) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            session.beginTransaction();
            studentBookRepository.setSession(session);
            boolean isRemove=false;
            if(studentBookRepository.removeBookStudent(bid)){
                isRemove=true;
                session.getTransaction().commit();
                session.close();
            }else{
                session.getTransaction().rollback();
                session.close();
            }
            return isRemove;
        }
    }

    @Override
    public List<BookStudentDTO> getAll() throws Exception {

        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            studentBookRepository.setSession(session);

            List<BookStudent> list=studentBookRepository.getAll();
            List<BookStudentDTO> books=new ArrayList<>();
            if(list!=null){
                for (BookStudent bookStudent : list) {
                    books.add(new BookStudentDTO(bookStudent.getBookStudentId(),bookStudent.getBook().getBid(),bookStudent.getStudent().getSid(),bookStudent.getBorrowDate(),bookStudent.getReturnDate(),bookStudent.getNumber(),new BookDTO(bookStudent.getBook().getCategory(),bookStudent.getBook().getMachiningNo(),bookStudent.getBook().getBookName(),bookStudent.getBook().getPublisher(),bookStudent.getBook().getPosition().getPosId() ),new StudentDTO(bookStudent.getStudent().getSid(), bookStudent.getStudent().getRegId(), bookStudent.getStudent().getName())));
                }
            }
            return books;
        }
    }

    @Override
    public List<BookStudentDTO> searchBookStudentByStudent(int sid) throws Exception {

        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            studentBookRepository.setSession(session);

            List<BookStudent> list=studentBookRepository.searchBookStudentByStudent(sid);
            List<BookStudentDTO> books=new ArrayList<>();
            if(list!=null){
                for (BookStudent bookStudent : list) {
                    books.add(new BookStudentDTO(bookStudent.getBookStudentId(),bookStudent.getBook().getBid(),bookStudent.getStudent().getSid(),bookStudent.getBorrowDate(),bookStudent.getReturnDate(),bookStudent.getNumber()));
                }
            }
            return books;
        }
    }

    @Override
    public List<BookStudentDTO> searchBookStudentByBook(int bid) throws Exception {



        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            studentBookRepository.setSession(session);

            List<BookStudent> list=studentBookRepository.searchBookStudentByBook(bid);
            List<BookStudentDTO> books=new ArrayList<>();
            if(list!=null){
                for (BookStudent bookStudent : list) {
                    books.add(new BookStudentDTO(bookStudent.getBookStudentId(),bookStudent.getBook().getBid(),bookStudent.getStudent().getSid(),bookStudent.getBorrowDate(),bookStudent.getReturnDate(),bookStudent.getNumber()));
                }
            }
            return books;
        }
    }

    @Override
    public BookStudentDTO getLastReservationDetails(int stId) throws Exception {
        try(Session session=HibernateUtil.getSessionFactory().openSession()){

            studentBookRepository.setSession(session);
            BookStudent bookStudent=studentBookRepository.getLastReservationDetails(stId);
            if(bookStudent!=null){
                session.close();
                return new BookStudentDTO(bookStudent.getBookStudentId(),bookStudent.getBook().getBid(),bookStudent.getStudent().getSid(),bookStudent.getBorrowDate(),bookStudent.getReturnDate(),bookStudent.getNumber(),new BookDTO(bookStudent.getBook().getCategory(),bookStudent.getBook().getMachiningNo(),bookStudent.getBook().getBookName(),bookStudent.getBook().getPublisher(),bookStudent.getBook().getPosition().getPosId() ));
            }
            return null;
        }
    }

    @Override
    public BookStudentDTO getLastRes(int sId) throws Exception {
        return null;
    }
}
