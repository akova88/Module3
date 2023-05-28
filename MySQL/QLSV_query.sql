USE QuanlySinhVien;
-- hiển thị ds sv có tên bđ bằng ký tự 'h'
SELECT *
FROM student
WHERE StudentName LIKE 'h%';
-- hiển thị tt lớp học có tg bđ vào tháng 12
SELECT *
FROM class
-- WHERE month(StarDate) = 12; 
WHERE extract(month from StarDate) = 12;
-- hiển thị tt môn học có credit từ 3-5
SELECT *
FROM subject
WHERE Credit BETWEEN 3 AND 5;
-- Thay đổi mã lớp(ClassID) của sinh viên có tên ‘Hung’ là 2.
UPDATE student SET ClassID = 2 WHERE StudentName = 'Hung' AND StudentID = 1;
-- Hiển thị các thông tin: StudentName, SubName, Mark. 
-- Dữ liệu sắp xếp theo điểm thi (mark) giảm dần. nếu trùng sắp theo tên tăng dần.
SELECT s.StudentName, sub.SubName, m.Mark
FROM student s JOIN mark m on s.StudentID = m.StudentID
join subject sub on sub.SubID = m.SubID
ORDER BY m.Mark DESC, s.StudentName ASC;
