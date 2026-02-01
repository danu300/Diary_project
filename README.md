📔 Diary Management System (Java)

A Java-based console application that enables users to manage personal diary entries and tasks efficiently. 
The system supports full CRUD operations, task prioritization, due-date tracking, reporting, and automatic data persistence using file serialization.
✨ Features
📝 Diary Management

Create, view, edit, search, and delete diary entries

Search entries by keyword

Automatic timestamping and unique entry IDs

✅ Task & Appointment Management

Add tasks with priority and due date

Mark tasks as completed

View pending, completed, and overdue tasks

Automatic sorting by priority and due date

📊 Reports

Total number of diary entries

Total tasks

Completed and overdue task statistics

💾 Data Persistence

Automatic save on program exit

Loads saved data on restart using Java serialization

🛠️ Technologies Used

Java (Core Java, OOP)

Java Collections Framework

Java Time API

File I/O & Object Serialization

Git & GitHub
📂 Project Structure
Diary_project/
│
├── Diary.java        // Main class & user interface
├── Models.java       // Record, DiaryEntry, Task models
├── Managers.java     // DiaryManager & TaskManager logic
├── DataManager.java  // File persistence (save/load)
├── README.md
└── LICENSE
How to Run
Prerequisites

Java JDK 8 or higher

Git (optional)
Steps
git clone https://github.com/danu300/Diary_project.git
cd Diary_project
javac *.java
java Diary
🎯 Use Cases

Personal diary and task tracking

Academic or final-year Java project

Demonstration of OOP and file handling concepts

Portfolio project for internships and junior developer roles

📜 License

This project is licensed under the MIT License.
You are free to use, modify, and distribute it with proper attribution.

👤 Author

DANAYT ABRHA
SECOND YEAR COMPUTER SCIENCE 
AT MEKELLE UNIVERSITY
Aspiring Software Developer | Interested in Data Science & AI

🚀 Future Improvements (Optional)

JavaFX or Swing GUI

Database integration (MySQL / PostgreSQL)

User authentication

Export reports to PDF or CSV
