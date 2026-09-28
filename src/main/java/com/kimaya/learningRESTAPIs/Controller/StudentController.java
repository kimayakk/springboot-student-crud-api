package com.kimaya.learningRESTAPIs.Controller;

import com.kimaya.learningRESTAPIs.DTO.AddStudentRequestDto;
import com.kimaya.learningRESTAPIs.DTO.StudentDTO;
import com.kimaya.learningRESTAPIs.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/")
    public ResponseEntity<List<StudentDTO>> getAllStudents()
    {
        //return new StudentDTO(24l,"Kimaya","kikamble@eagle.org");
        System.out.println("Controller called");
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
        public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id)
        {
            return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById(id));
        }

    @PostMapping ("/addStudent")
    public ResponseEntity<StudentDTO> createNewStudent(@RequestBody @Valid AddStudentRequestDto addStudentRequestDto)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id)
    {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id,@RequestBody @Valid AddStudentRequestDto addStudentRequestDto)
    {
        return ResponseEntity.ok(studentService.updateStudent(id,addStudentRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudentData(@PathVariable Long id, @RequestBody @Valid Map<String,Object> updates)
    {
        return  ResponseEntity.ok(studentService.updateStudentData(id,updates));
    }
}
