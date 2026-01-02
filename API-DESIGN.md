1. احراز هویت (Authentication)
1.1 ثبت‌نام دانشجو

POST

/api/auth/register


Request Body (JSON):

{
  "username": "student1",
  "password": "123456",
  "fullName": "Ali Ahmadi"
}


Behavior (مطابق AuthService):

ایجاد Student جدید

نقش: STUDENT

وضعیت اولیه: فعال

Response (201 Created):

{
  "message": "Student registered successfully"
}

1.2 ورود به سیستم (همه کاربران)

POST

/api/auth/login


Request Body:

{
  "username": "student1",
  "password": "123456"
}


Response (200 OK):

{
  "id": "uuid",
  "username": "student1",
  "role": "STUDENT"
}

1.3 تغییر رمزعبور (کارمند / مدیر)

POST

/api/auth/change-password


Request Body:

{
  "oldPassword": "old123",
  "newPassword": "new123"
}


Access Control:
EMPLOYEE ، ADMIN

2. کتاب‌ها (Books)
2.1 دریافت لیست کتاب‌ها (با فیلتر)

GET

/api/books


Query Parameters (اختیاری):

?title=Java&author=Bloch&year=2018


Behavior:
نگاشت مستقیم به:

bookRepo.search(title, author, year)


Response:

[
  {
    "id": "uuid",
    "title": "Effective Java",
    "author": "Joshua Bloch",
    "publishYear": 2018,
    "available": true
  }
]

2.2 دریافت جزئیات یک کتاب

GET

/api/books/{id}


Response (200 OK):

{
  "id": "uuid",
  "title": "Clean Code",
  "author": "Robert Martin",
  "publishYear": 2008,
  "available": false
}

2.3 ایجاد کتاب جدید (کارمند)

POST

/api/books


Request Body:

{
  "title": "Design Patterns",
  "author": "GoF",
  "publishYear": 1994
}


Behavior:
استفاده از:

new Book(title, author, year)
bookRepo.add(book)

2.4 به‌روزرسانی کتاب

PUT

/api/books/{id}


Request Body:

{
  "title": "Clean Code (Updated)",
  "author": "Robert Martin",
  "publishYear": 2009
}

2.5 جستجوی پیشرفته کتاب

GET

/api/books/search


(معادل /api/books ولی جدا برای توسعه آینده)

3. امانت کتاب (Borrowing)
3.1 ثبت درخواست امانت (دانشجو)

POST

/api/borrow/request


Request Body:

{
  "bookId": "uuid"
}


Behavior (مطابق LoanService):

بررسی فعال بودن دانشجو

بررسی موجود بودن کتاب

ایجاد LoanRequest با وضعیت PENDING

3.2 درخواست‌های در انتظار تأیید (کارمند)

GET

/api/borrow/requests/pending


Response:

[
  {
    "id": "loan-id",
    "studentId": "student-id",
    "bookId": "book-id",
    "status": "PENDING"
  }
]

3.3 تأیید / رد درخواست امانت

PUT

/api/borrow/requests/{id}/approve
/api/borrow/requests/{id}/reject


Effect:

approve → وضعیت APPROVED

reject → وضعیت REJECTED

3.4 ثبت بازگرداندن کتاب

PUT

/api/borrow/{id}/return


Effect:

تغییر وضعیت LoanRequest

book.setAvailable(true)

4. مدیریت دانشجویان (Students)
4.1 دریافت پروفایل دانشجو

GET

/api/students/{id}


Response:

{
  "id": "uuid",
  "username": "student1",
  "fullName": "Ali Ahmadi",
  "active": true
}

4.2 فعال / غیرفعال کردن دانشجو

PUT

/api/students/{id}/status


Request Body:

{
  "active": false
}

4.3 تاریخچه امانت‌های دانشجو

GET

/api/students/{id}/borrow-history

5. گزارش‌ها و آمار (Reports)
5.1 آمار خلاصه

GET

/api/stats/summary


Response:

{
  "students": 120,
  "books": 450,
  "activeBorrows": 32
}

5.2 آمار پیشرفته امانت‌ها (مدیر)

GET

/api/stats/borrows

5.3 عملکرد کارمند

GET

/api/stats/employees/{id}/performance

5.4 دانشجویان با بیشترین تأخیر

GET

/api/stats/top-delayed

6. مدیریت کارکنان (Admin)
6.1 ایجاد کارمند

POST

/api/admin/employees

6.2 لیست کارکنان

GET

/api/admin/employees