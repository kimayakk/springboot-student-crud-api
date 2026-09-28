package com.kimaya.learningRESTAPIs.Service;

import com.kimaya.learningRESTAPIs.DTO.AddStudentRequestDto;
import com.kimaya.learningRESTAPIs.DTO.StudentDTO;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface StudentService {

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    StudentDTO createNewStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudentById(Long id);

    StudentDTO updateStudent(Long id,AddStudentRequestDto addStudentRequestDto);

    StudentDTO updateStudentData(Long id, Map<String, Object> updates);
}
