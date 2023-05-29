USE quanlysinhvien;
-- Hiển thị số lượng sinh viên ở từng nơi
SELECT Address, count(StudentID) AS 'Số lượng học viên'
FROM student
GROUP BY Address;
-- Tính điểm trung bình các môn học của mỗi học viên
SELECT s.StudentID, s.StudentName, avg(Mark) as ĐTB
FROM student s JOIN mark m on s.StudentID = m.StudentID
GROUP BY s.StudentID, s.StudentName;
-- Hiển thị những bạn học viên co điểm trung bình các môn học lớn hơn 15
SELECT s.StudentID, s.StudentName, avg(Mark) as ĐTB
FROM student s JOIN mark m on s.StudentID = m.StudentID
GROUP BY s.StudentID, s.StudentName
HAVING avg(Mark) > 15;
--  Hiển thị thông tin các học viên có điểm trung bình lớn nhất.
SELECT s.StudentID, s.StudentName, avg(Mark) as ĐTB
FROM student s JOIN mark m on s.StudentID = m.StudentID
GROUP BY s.StudentID, s.StudentName
HAVING avg(Mark) >= ALL (SELECT avg(Mark) FROM Mark GROUP BY Mark.StudentID);
