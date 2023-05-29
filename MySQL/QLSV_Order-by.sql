USE quanlysinhvien;
-- Hiển thị tất cả các thông tin môn học (bảng subject) có credit lớn nhất.
SELECT *
FROM subject
WHERE Credit = (SELECT MAX(Credit) FROM subject);
-- Hiển thị các thông tin môn học có điểm thi lớn nhất.
SELECT sj.SubID, sj.SubName, sj.Credit, sj.Status, m.Mark
FROM subject sj join mark m on sj.SubID = m.SubID
-- WHERE m.Mark = (SELECT MAX(m.Mark) FROM subject sj join mark m on sj.SubID = m.SubID);
ORDER BY m.Mark DESC
LIMIT 1;
-- Hiển thị các thông tin sinh viên và điểm trung bình của mỗi sinh viên, xếp hạng theo thứ tự điểm giảm dần
SELECT s.StudentID, s.StudentName, avg(m.Mark) as ĐTB
FROM student s join mark m on s.StudentID = m.StudentID
GROUP BY s.StudentID
ORDER BY ĐTB DESC;


