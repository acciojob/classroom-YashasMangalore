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
        if (student != null && student.getName() != null)
        {
            studentMap.put(student.getName(), student);
        }
    }

    public void saveTeacher(Teacher teacher){
        // your code goes here
        if(teacher!=null && teacher.getName()!=null)
        {
            teacherMap.put(teacher.getName(),teacher);
        }
    }

    public void saveStudentTeacherPair(String student, String teacher)
    {
        if(student != null && teacher != null && studentMap.containsKey(student) && teacherMap.containsKey(teacher))
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
        // your code goes here
        if(studentMap.containsKey(studentName))
        {
            return studentMap.get(studentName);
        }
        return null;
    }

    public Teacher findTeacher(String teacherName){
        // your code goes here
        if(teacherMap.containsKey(teacherName))
        {
            return teacherMap.get(teacherName);
        }
        return null;
    }

    public List<String> findStudentsFromTeacher(String teacher)
    {// your code goes here
        // find student list corresponding to a teacher
        return teacherStudentMapping.getOrDefault(teacher,new ArrayList<>());
    }

    public List<String> findAllStudents(){
        // your code goes here
        //        List<String> students=new ArrayList<>();
//        students.addAll(studentMap.keySet());  --OR--
//        for(String studentName:studentMap.keySet())
//        {
//            students.add(studentName);
//        }
//        if(!studentMap.isEmpty())
//        {
//            return new ArrayList<>(studentMap.keySet());
//        }
//        return new ArrayList<>();
        return !studentMap.isEmpty() ? new ArrayList<>(studentMap.keySet()) : new ArrayList<>();
    }

    public void deleteTeacher(String teacher)
    {
        // your code goes here
        if(teacherMap.containsKey(teacher))
        {
            teacherMap.remove(teacher);
        }
    }

    public void deleteAllTeachers()
    {
        // your code goes here
        if(!teacherMap.isEmpty())
        {
            teacherMap.clear();
        }
    }
}