package com.kimaya.learningRESTAPIs.Service.impl;

import com.kimaya.learningRESTAPIs.DTO.AddStudentRequestDto;
import com.kimaya.learningRESTAPIs.DTO.StudentDTO;
import com.kimaya.learningRESTAPIs.Entity.Student;
import com.kimaya.learningRESTAPIs.Repository.StudentRepository;
import com.kimaya.learningRESTAPIs.Service.StudentService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DialectOverride;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student>  students=studentRepository.findAll();
        List<StudentDTO> studentDTOList=students.stream().map(student->new StudentDTO(student.getId(),student.getEmail(),student.getName())).toList();
        return studentDTOList;
    }

    @Override
    public StudentDTO getStudentById(Long id) {
       Student student = studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found with ID: "+id));
        //return new StudentDTO(student.getId(),student.getEmail(),student.getName());
         StudentDTO studentDTO=modelMapper.map(student,StudentDTO.class);
         return studentDTO;

    }

    @Override
    public StudentDTO createNewStudent(AddStudentRequestDto addStudentRequestDto)
    {
     Student newStudent=modelMapper.map(addStudentRequestDto,Student.class);
    Student student=studentRepository.save(newStudent);
    return modelMapper.map(student,StudentDTO.class);
    }

    @Override
    public void deleteStudentById(Long id)
    {
        if(!studentRepository.existsById(id))
        {
            throw new IllegalArgumentException("Student does not exists by id : "+id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDTO updateStudent(Long id, AddStudentRequestDto addStudentRequestDto)
    {
        Student student= studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student not found with id :"+id));
        modelMapper.map(addStudentRequestDto,student);
        student=studentRepository.save(student);
        return modelMapper.map(student,StudentDTO.class);
    }

    @Override
    public StudentDTO updateStudentData(Long id, Map<String, Object> updates)
    {
         Student student =studentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Student not founf with id:"+id));

        updates.forEach((field,value)->
        {
            switch (field)
            {
                case "name":  student.setName((String) value);break;
                case "email": student.setEmail((String)  value);break;
                default:
                    throw new IllegalArgumentException("field is not supported");
            }
        });


       Student Savedstudent=studentRepository.save(student);
        return modelMapper.map(Savedstudent,StudentDTO.class);
    }
}
