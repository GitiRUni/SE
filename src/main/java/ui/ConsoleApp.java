package ui;

import model.*;
import repository.*;
import service.*;
import util.InputUtil;

import java.time.LocalDate;
import java.util.*;

public class ConsoleApp {
    private final Scanner sc = new Scanner(System.in);
    private final UserRepository userRepo = new UserRepository();
    private final BookRepository bookRepo = new BookRepository();
    private final LoanRepository loanRepo = new LoanRepository();
    private final AuthService authService = new AuthService(userRepo);
    private final BookService bookService = new BookService(bookRepo);
    private final LoanService loanService = new LoanService(loanRepo, bookRepo);

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== سیستم مدیریت کتابخانه ===");
            System.out.println("1. ورود");
            System.out.println("2. ثبت نام دانشجو");
            System.out.println("3. ورود به عنوان مهمان");
            System.out.println("0. خروج");
            int ch = InputUtil.readInt(sc, "انتخاب: ");
            switch (ch) {
                case 1 -> loginFlow();
                case 2 -> registerFlow();
                case 3 -> guestMenu();
                case 0 -> running = false;
            }
        }
        System.out.println("خداحافظ!");
    }

    private void registerFlow() {
        String u = InputUtil.readLine(sc, "نام کاربری: ");
        String p = InputUtil.readLine(sc, "رمز عبور: ");
        String f = InputUtil.readLine(sc, "نام کامل: ");
        authService.registerStudent(u, p, f);
        System.out.println("✅ ثبت‌نام با موفقیت انجام شد!");
    }

    private void loginFlow() {
        String u = InputUtil.readLine(sc, "نام کاربری: ");
        String p = InputUtil.readLine(sc, "رمز عبور: ");
        try {
            User user = authService.login(u, p);
            switch (user.getRole()) {
                case STUDENT -> studentMenu((Student) user);
                default -> System.out.println("این نقش هنوز پیاده‌سازی نشده است.");
            }
        } catch (Exception e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void guestMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n-- منوی مهمان --");
            System.out.println("1. مشاهده تعداد دانشجویان ثبت‌شده");
            System.out.println("2. جستجوی کتاب بر اساس نام");
            System.out.println("3. نمایش همه کتاب‌ها");
            System.out.println("0. بازگشت");
            int ch = InputUtil.readInt(sc, "انتخاب: ");
            switch (ch) {
                case 1 -> System.out.println("تعداد دانشجویان: " + userRepo.getAllStudents().size());
                case 2 -> {
                    String name = InputUtil.readLine(sc, "نام کتاب: ");
                    bookService.search(name, null, null).forEach(System.out::println);
                }
                case 3 -> bookService.allBooks().forEach(System.out::println);
                case 0 -> back = true;
            }
        }
    }

    private void studentMenu(Student s) {
        boolean back = false;
        while (!back) {
            System.out.println("\n-- منوی دانشجو --");
            System.out.println("1. جستجوی کتاب");
            System.out.println("2. درخواست امانت کتاب");
            System.out.println("3. نمایش درخواست‌های ثبت‌شده");
            System.out.println("0. خروج");
            int ch = InputUtil.readInt(sc, "انتخاب: ");
            switch (ch) {
                case 1 -> {
                    String title = InputUtil.readLine(sc, "عنوان: ");
                    String author = InputUtil.readLine(sc, "نویسنده: ");
                    Integer year = null;
                    String y = InputUtil.readLine(sc, "سال (اختیاری): ");
                    if (!y.isEmpty()) year = Integer.parseInt(y);
                    bookService.search(title, author, year).forEach(System.out::println);
                }
                case 2 -> {
                    String title = InputUtil.readLine(sc, "نام کتاب: ");
                    List<Book> books = bookService.search(title, null, null);
                    if (books.isEmpty()) { System.out.println("کتاب یافت نشد."); break; }
                    Book book = books.get(0);
                    LocalDate start = LocalDate.now();
                    LocalDate end = start.plusDays(7);
                    try {
                        loanService.requestLoan(s, book, start, end);
                        System.out.println("✅ درخواست امانت ثبت شد.");
                    } catch (Exception e) {
                        System.out.println("❌ " + e.getMessage());
                    }
                }
                case 3 -> loanService.allLoans()
                        .stream().filter(l -> l.getStudentId().equals(s.getId()))
                        .forEach(l -> System.out.println(l.getBookId() + " - " + l.getStatus()));
                case 0 -> back = true;
            }
        }
    }
}
