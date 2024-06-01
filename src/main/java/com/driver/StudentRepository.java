package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentMapping;

    public StudentRepository(){
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherStudentMapping = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student student)
    {// your code goes here
        if (student != null && !studentMap.containsKey(student.getName()))
        {
            studentMap.put(student.getName(), student);
        }
    }

    public void saveTeacher(Teacher teacher){
        // your code goes here
        if(teacher!=null && !teacherMap.containsKey(teacher.getName()))
        {
            teacherMap.put(teacher.getName(),teacher);
        }
    }

    public void saveStudentTeacherPair(String student, String teacher)
    {
        if( studentMap.containsKey(student) && teacherMap.containsKey(teacher))
        {// your code goes here
            //get all students
            List<String> students = teacherStudentMapping.getOrDefault(teacher, new ArrayList<>());
            //add students
            students.add(student);
            //update
            teacherStudentMapping.put(teacher,students);
        }
    }
    public Student findStudent(String studentName)
    {
        return studentMap.getOrDefault(studentName, null);
    }

    public Teacher findTeacher(String teacherName)
    {
        return teacherMap.getOrDefault(teacherName, null);
    }

    public List<String> findStudentsFromTeacher(String teacher)
    {// your code goes here
        // find student list corresponding to a teacher
        return teacherStudentMapping.getOrDefault(teacher,new ArrayList<>());
    }

    public List<String> findAllStudents()
    {
        // your code goes here
        return !studentMap.isEmpty() ? new ArrayList<>(studentMap.keySet()) : new ArrayList<>();
    }

    public void deleteTeacher(String teacher)
    {
        // your code goes here
        teacherMap.remove(teacher);
        for(String student:teacherStudentMapping.get(teacher))
        {
            studentMap.remove(student);
        }
        teacherStudentMapping.remove(teacher);
    }

    public void deleteAllTeachers()
    {// your code goes here
        teacherMap.clear();
        for(String teacher : teacherStudentMapping.keySet())
        {
            for(String student:teacherStudentMapping.get(teacher))
            {
                studentMap.remove(student);
            }

        }
        teacherStudentMapping.clear();
    }
}