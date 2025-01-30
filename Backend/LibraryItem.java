import java.io.*;
import java.util.*;

interface LibraryItem {
    void displayInfo();
}

class Book implements LibraryItem {
    private String title;
    private String author;
    private String isbn;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public void displayInfo() {
        System.out.println("Book Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
    }
}

class Member {
    private String name;
    private int memberId;
    private List<Book> borrowedBooks;

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        System.out.println(name + " borrowed " + book.getTitle());
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        System.out.println(name + " returned " + book.getTitle());
    }

    public void displayBorrowedBooks() {
        System.out.println(name + " has borrowed the following books:");
        for (Book book : borrowedBooks) {
            book.displayInfo();
        }
    }
}

class Library {
    private List<Book> books;
    private List<Member> members;

    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Added book: " + book.getTitle());
    }

    public void addMember(Member member) {
        members.add(member);
        System.out.println("Added member: " + member.getName());
    }

    public void displayBooks() {
        System.out.println("Library Books:");
        for (Book book : books) {
            book.displayInfo();
        }
    }

    public void displayMembers() {
        System.out.println("Library Members:");
        for (Member member : members) {
            System.out.println("Member Name: " + member.getName() + ", Member ID: " + member.getMemberId());
        }
    }

    public Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    public void saveLibraryData(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(books);
            oos.writeObject(members);
            System.out.println("Library data saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving library data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadLibraryData(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            books = (List<Book>) ois.readObject();
            members = (List<Member>) ois.readObject();
            System.out.println("Library data loaded from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading library data: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        Library library = new Library();

        // Adding books to the library
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565"));
        library.addBook(new Book("1984", "George Orwell", "9780451524935"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084"));

        // Adding members to the library
        library.addMember(new Member("Alice", 1));
        library.addMember(new Member("Bob", 2));

        // Displaying library books and members
        library.displayBooks();
        library.displayMembers();

        // Borrowing and returning books
        Member alice = library.findMemberById(1);
        Book gatsby = library.findBookByIsbn("9780743273565");

        if (alice != null && gatsby != null) {
            alice.borrowBook(gatsby);
            alice.displayBorrowedBooks();
            alice.returnBook(gatsby);
            alice.displayBorrowedBooks();
        }

        // Saving and loading library data
        library.saveLibraryData("library_data.dat");
        library.loadLibraryData("library_data.dat");

        // Displaying library books and members after loading data
        library.displayBooks();
        library.displayMembers();
    }
}