# Library Management System API
This project implements a RESTful API for managing books, authors, publishers, categories, and book borrowings in a library system.

## Technologies Used
- Spring Boot: Framework for creating Java applications.
- Spring Data JPA: Simplifies the implementation of JPA repositories.
- PostgreSQL: Database management system.
- ModelMapper: Facilitates object mapping.
## Project Structure
The project follows a layered architecture with the following components:

- Controllers: Handle incoming HTTP requests and manage data flow.
- Services: Implement business logic and interact with repositories.
- Repositories: Interface with the database using Spring Data JPA.
- Entities: Represent database tables and relationships.
## Entity Relationships
The project includes the following entities and their relationships:

- Author: One-to-Many relationship with Book.
- Book: Many-to-One relationships with Author and Publisher, Many-to-Many relationship with Category.
- BookBorrowing: Many-to-One relationship with Book.
- Category: Many-to-Many relationship with Book.
- Publisher: One-to-Many relationship with Book.
## API Endpoints
### Authors
- POST /v1/authors: Create a new author.
- GET /v1/authors/{id}: Retrieve author details.
- GET /v1/authors: Retrieve paginated list of authors.
- PUT /v1/authors: Update an existing author.
- DELETE /v1/authors/{id}: Delete an author.
### Books
- POST /v1/books: Create a new book.
- GET /v1/books/{id}: Retrieve book details.
- GET /v1/books/{id}/author: Retrieve author of a book.
- GET /v1/books/{id}/publisher: Retrieve publisher of a book.
- GET /v1/books/{id}/category: Retrieve categories of a book.
- GET /v1/books: Retrieve paginated list of books.
- PUT /v1/books: Update an existing book.
- DELETE /v1/books/{id}: Delete a book.
### Categories
- POST /v1/categories: Create a new category.
- GET /v1/categories/{id}: Retrieve category details.
- GET /v1/categories: Retrieve paginated list of categories.
- PUT /v1/categories: Update an existing category.
- DELETE /v1/categories/{id}: Delete a category.
- Publishers
- POST /v1/publishers: Create a new publisher.
- GET /v1/publishers/{id}: Retrieve publisher details.
- GET /v1/publishers: Retrieve paginated list of publishers.
- PUT /v1/publishers: Update an existing publisher.
- DELETE /v1/publishers/{id}: Delete a publisher.
### Book Borrowings
- POST /v1/bookBorrowings: Create a new book borrowing record.
- GET /v1/bookBorrowings/{id}: Retrieve book borrowing details.
- GET /v1/bookBorrowings/{id}/book: Retrieve book details for a borrowing record.
- GET /v1/bookBorrowings: Retrieve paginated list of book borrowings.
- PUT /v1/bookBorrowings: Update an existing book borrowing record.
- DELETE /v1/bookBorrowings/{id}: Delete a book borrowing record.
## Installation
To run this project locally, ensure you have Java 11 or higher and PostgreSQL installed. Follow these steps:

### 1. Clone the repository:

git clone https://github.com/your/repository.git
cd repository-folder

### 2. Configure PostgreSQL database settings in application.properties.

### 3. Build the project:

./mvnw clean package

### 4. Run the application:

java -jar target/library-management-system-1.0.jar

## Usage
- Use an API development tool like Postman or curl to interact with the endpoints listed above.
- Ensure authentication and authorization mechanisms are implemented before deploying to production.