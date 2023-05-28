USE QuanLySinhVien;
-- hiển thị ds tất cả học viên
SELECT * 
FROM student;
-- hiển thị ds học viên có trạng thái status = true
SELECT *
FROM student
WHERE status = true;
-- hiển thị ds các môn học có thời gian học < 10
SELECT *
FROM subject
WHERE Credit < 10;
-- hiển thị ds học viên lớp A1
SELECT s.StudentID, s.StudentName, c.ClassName
FROM student s join Class c on s.ClassID = c.ClassID
WHERE c.ClassName = 'A1';
-- Hiển thị điểm môn CF của học viên
SELECT s.StudentID, s.StudentName, sub.SubName, m.Mark
FROM student s join mark m on s.StudentID = m.StudentID join subject sub on m.SubID = sub.SubID
WHERE sub.SubName = 'CF';
-- 