package com.okta.springbootvue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolationException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.okta.springbootvue.entity.UserRegister;
import com.okta.springbootvue.entity.Sex;
import com.okta.springbootvue.entity.Question;
import com.okta.springbootvue.entity.TypeName;
import com.okta.springbootvue.repository.SexRepository;
import com.okta.springbootvue.repository.QuestionRepository;
import com.okta.springbootvue.repository.TypeNameRepository;
import com.okta.springbootvue.repository.UserRegisterRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

@DataJpaTest
public class UserregisterTests {

    private Validator validator;

    @Autowired
    private UserRegisterRepository userregisterRepository;
    @Autowired
    private SexRepository sexRepository;
    
    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private TypeNameRepository typenameRepository;
    @BeforeEach
    public void setup() {
     ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ตั้งชื่อ test ให้สอดคล้องกับสิ่งที่ต้อง test
    @Test
    void B6001520_testUserregisterOK() {
        Sex sex = new Sex();
        Question question = new Question();
        TypeName typename = new TypeName();
        UserRegister userregister = new UserRegister();
        userregister.setName("กิตติชัย");
        userregister.setTel("0901316436");
        userregister.setEmail("mosmos11289@gmail.com");
        userregister.setAnswer("Chanthaburi");
        userregister.setPassword("Chanthaburi");
        sex.setName("ชาย");
        sex = sexRepository.saveAndFlush(sex);
        typename.setName("นาย");
        typename = typenameRepository.saveAndFlush(typename);
        question.setName("บ้านเกิดคุณอยู่ที่ไหน");
        question = questionRepository.saveAndFlush(question);
        
         userregister = userregisterRepository.saveAndFlush(userregister);

         Optional<UserRegister> found = userregisterRepository.findById(userregister.getId());
        assertEquals(userregister, found.get());

    }

    // ==============================================================================
    // ==============================================================================
    // Test for Name

    @Test
    void B6001520_testNameMustNotBeNull() {
        final UserRegister userregister = new UserRegister();
        userregister.setName(null);
        userregister.setTel("0901316436");
        userregister.setEmail("mosmos11289@gmail.com");
        userregister.setAnswer("Chanthaburi");
        userregister.setPassword("Chanthaburi");
        Sex sex = new Sex();
        Question question = new Question();
        TypeName typename = new TypeName();
        sex.setName("ชาย");
        sex = sexRepository.saveAndFlush(sex);
        typename.setName("นาย");
        typename = typenameRepository.saveAndFlush(typename);
        question.setName("บ้านเกิดคุณอยู่ที่ไหน");
        question = questionRepository.saveAndFlush(question);

        final Set<ConstraintViolation<UserRegister>> result = validator.validate(userregister);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<UserRegister> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void B6001520_testNameMustMatchString() {
        final UserRegister userregister = new UserRegister();
        userregister.setName("12345678 12345678");
        userregister.setTel("0901316436");
        userregister.setEmail("mosmos11289@gmail.com");
        userregister.setAnswer("Chanthaburi");
        userregister.setPassword("Chanthaburi");
        Sex sex = new Sex();
        Question question = new Question();
        TypeName typename = new TypeName();
        sex.setName("ชาย");
        sex = sexRepository.saveAndFlush(sex);
        typename.setName("นาย");
        typename = typenameRepository.saveAndFlush(typename);
        question.setName("บ้านเกิดคุณอยู่ที่ไหน");
        question = questionRepository.saveAndFlush(question);

        final Set<ConstraintViolation<UserRegister>> result = validator.validate(userregister);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<UserRegister> v = result.iterator().next();
        assertEquals("must match \"^[A-Za-zก-๙ ]{4,60}$\"", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void B6001520_testNameMustBeMore60String() {
        final UserRegister userregister = new UserRegister();
        userregister.setName("kittichai jitjaroenkittichai jitjaroenkittichai jitjaroenkittichai jitjaroen");
        userregister.setTel("0901316436");
        userregister.setEmail("mosmos11289@gmail.com");
        userregister.setAnswer("Chanthaburi");
        userregister.setPassword("Chanthaburi");
        Sex sex = new Sex();
        Question question = new Question();
        TypeName typename = new TypeName();
        sex.setName("ชาย");
        sex = sexRepository.saveAndFlush(sex);
        typename.setName("นาย");
        typename = typenameRepository.saveAndFlush(typename);
        question.setName("บ้านเกิดคุณอยู่ที่ไหน");
        question = questionRepository.saveAndFlush(question);

        final Set<ConstraintViolation<UserRegister>> result = validator.validate(userregister);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<UserRegister> v = result.iterator().next();
        assertEquals("must match \"^[A-Za-zก-๙ ]{4,60}$\"", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    // ==============================================================================
    // ==============================================================================
    // Test for Email
    @Test
    void B6001520_testEmailMustNotBeNull() {
        final UserRegister userregister = new UserRegister();
        userregister.setName("kittichai jitjaroen");
        userregister.setTel("0901316436");
        userregister.setEmail(null);
        userregister.setAnswer("Chanthaburi");
        userregister.setPassword("Chanthaburi");
        Sex sex = new Sex();
        Question question = new Question();
        TypeName typename = new TypeName();
        sex.setName("ชาย");
        sex = sexRepository.saveAndFlush(sex);
        typename.setName("นาย");
        typename = typenameRepository.saveAndFlush(typename);
        question.setName("บ้านเกิดคุณอยู่ที่ไหน");
        question = questionRepository.saveAndFlush(question);

        final Set<ConstraintViolation<UserRegister>> result = validator.validate(userregister);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<UserRegister> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
    }
    @Test
    void B6001520_testEmailMustNotSIZE30() {
        final UserRegister userregister = new UserRegister();
        userregister.setName("kittichai jitjaroen");
        userregister.setTel("0901316436");
        userregister.setEmail("mosmos1128911111111111111111111@gmail.com");
        userregister.setAnswer("Chanthaburi");
        userregister.setPassword("Chanthaburi");
        Sex sex = new Sex();
        Question question = new Question();
        TypeName typename = new TypeName();
        sex.setName("ชาย");
        sex = sexRepository.saveAndFlush(sex);
        typename.setName("นาย");
        typename = typenameRepository.saveAndFlush(typename);
        question.setName("บ้านเกิดคุณอยู่ที่ไหน");
        question = questionRepository.saveAndFlush(question);

        final Set<ConstraintViolation<UserRegister>> result = validator.validate(userregister);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<UserRegister> v = result.iterator().next();
        assertEquals("email max30", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
    }

    // ==============================================================================
    // ==============================================================================
    // Test for Tel
    @Test
    void B6001520_testTelMustNotBeNull() {
        Sex sex = new Sex();

        final UserRegister userregister = new UserRegister();
        userregister.setName("kittichai jitjaroen");
        userregister.setTel(null);
        userregister.setEmail("mosmos11289@gmail.com");
        userregister.setAnswer("Chanthaburi");
        userregister.setPassword("Chanthaburi");
        Question question = new Question();
        TypeName typename = new TypeName();
        sex.setName("ชาย");
        typename.setName("นาย");
        question.setName("บ้านเกิดคุณอยู่ที่ไหน");

        sex = sexRepository.saveAndFlush(sex);
        typename = typenameRepository.saveAndFlush(typename);
        question = questionRepository.saveAndFlush(question);

        final Set<ConstraintViolation<UserRegister>> result = validator.validate(userregister);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<UserRegister> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());

    }

    @Test
    void B6001520_testTelMustNotBe9Digits() {
        final UserRegister userregister = new UserRegister();
        userregister.setName("kittichai jitjaroen");
        userregister.setTel("012345678");
        userregister.setEmail("mosmos11289@gmail.com");
        userregister.setAnswer("Chanthaburi");
        userregister.setPassword("Chanthaburi");
        Sex sex = new Sex();
        Question question = new Question();
        TypeName typename = new TypeName();
        sex.setName("ชาย");
        typename.setName("นาย");
        question.setName("บ้านเกิดคุณอยู่ที่ไหน");

        sex = sexRepository.saveAndFlush(sex);
        typename = typenameRepository.saveAndFlush(typename);
        question = questionRepository.saveAndFlush(question);

        final Set<ConstraintViolation<UserRegister>> result = validator.validate(userregister);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<UserRegister> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }

    @Test
    void B6001520_testTelMustNotBe11Digits() {
        final UserRegister userregister = new UserRegister();
        userregister.setName("kittichai jitjaroen");
        userregister.setTel("012345678901");
        userregister.setEmail("mosmos11289@gmail.com");
        userregister.setAnswer("Chanthaburi");
        userregister.setPassword("Chanthaburi");
        Sex sex = new Sex();
        Question question = new Question();
        TypeName typename = new TypeName();
        sex.setName("ชาย");
        sex = sexRepository.saveAndFlush(sex);
        typename.setName("นาย");
        typename = typenameRepository.saveAndFlush(typename);
        question.setName("บ้านเกิดคุณอยู่ที่ไหน");
        question = questionRepository.saveAndFlush(question);

        final Set<ConstraintViolation<UserRegister>> result = validator.validate(userregister);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<UserRegister> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }

    @Test
    void B6001520_testTelMustNotDigits() {

        final UserRegister userregister = new UserRegister();
        userregister.setName("kittichai jitjaroen");
        userregister.setTel("asdfghth");
        userregister.setEmail("mosmos11289@gmail.com");
        userregister.setAnswer("Chanthaburi");
        userregister.setPassword("Chanthaburi");
        Sex sex = new Sex();
        Question question = new Question();
        TypeName typename = new TypeName();
        sex.setName("ชาย");
        sex = sexRepository.saveAndFlush(sex);
        typename.setName("นาย");
        typename = typenameRepository.saveAndFlush(typename);
        question.setName("บ้านเกิดคุณอยู่ที่ไหน");
        question = questionRepository.saveAndFlush(question);

        final Set<ConstraintViolation<UserRegister>> result = validator.validate(userregister);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<UserRegister> v = result.iterator().next();
        assertEquals("must match \"\\d{10}\"", v.getMessage());
        assertEquals("tel", v.getPropertyPath().toString());
    }
    
        //Answer Test



    @Test
    void B6001520_testAnswerMustNotBeNull() {
        final UserRegister userregister = new UserRegister();
        userregister.setName("kittichai jitjaroen");
        userregister.setTel("0901316436");
        userregister.setEmail("mosmos11289@gmail.com");
        userregister.setAnswer(null);
        userregister.setPassword("Chanthaburi");
        Sex sex = new Sex();
        Question question = new Question();
        TypeName typename = new TypeName();
        sex.setName("ชาย");
        sex = sexRepository.saveAndFlush(sex);
        typename.setName("นาย");
        typename = typenameRepository.saveAndFlush(typename);
        question.setName("บ้านเกิดคุณอยู่ที่ไหน");
        question = questionRepository.saveAndFlush(question);

        final Set<ConstraintViolation<UserRegister>> result = validator.validate(userregister);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<UserRegister> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("answer", v.getPropertyPath().toString());
    }
    @Test
    void B6001520_testAnswerMustNotMAXSIZE20() {
        final UserRegister userregister = new UserRegister();
        userregister.setName("kittichai jitjaroen");
        userregister.setTel("0901316436");
        userregister.setEmail("mosmos11289@gmail.com");
        userregister.setAnswer("ChanthaburiChanthaburiChanthaburiChanthaburi");
        userregister.setPassword("Chanthaburi");
        Sex sex = new Sex();
        Question question = new Question();
        TypeName typename = new TypeName();
        sex.setName("ชาย");
        sex = sexRepository.saveAndFlush(sex);
        typename.setName("นาย");
        typename = typenameRepository.saveAndFlush(typename);
        question.setName("บ้านเกิดคุณอยู่ที่ไหน");
        question = questionRepository.saveAndFlush(question);

        final Set<ConstraintViolation<UserRegister>> result = validator.validate(userregister);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<UserRegister> v = result.iterator().next();
        assertEquals("answer max20", v.getMessage());
        assertEquals("answer", v.getPropertyPath().toString());
    }

    @Test
    void B6001520_testAnswerMustNotMINSIZE2() {
        final UserRegister userregister = new UserRegister();
        userregister.setName("kittichai jitjaroen");
        userregister.setTel("0901316436");
        userregister.setEmail("mosmos11289@gmail.com");
        userregister.setAnswer("A");
        userregister.setPassword("Chanthaburi");
        Sex sex = new Sex();
        Question question = new Question();
        TypeName typename = new TypeName();
        sex.setName("ชาย");
        sex = sexRepository.saveAndFlush(sex);
        typename.setName("นาย");
        typename = typenameRepository.saveAndFlush(typename);
        question.setName("บ้านเกิดคุณอยู่ที่ไหน");
        question = questionRepository.saveAndFlush(question);

        final Set<ConstraintViolation<UserRegister>> result = validator.validate(userregister);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        final ConstraintViolation<UserRegister> v = result.iterator().next();
        assertEquals("answer min2", v.getMessage());
        assertEquals("answer", v.getPropertyPath().toString());
    }

   // Password Test



   @Test
   void B6001520_testPasswordMustNotBeNull() {
       final UserRegister userregister = new UserRegister();
       userregister.setName("kittichai jitjaroen");
       userregister.setTel("0901316436");
       userregister.setEmail("mosmos11289@gmail.com");
       userregister.setAnswer("Chanthaburi");
       userregister.setPassword(null);
       Sex sex = new Sex();
       Question question = new Question();
       TypeName typename = new TypeName();
       sex.setName("ชาย");
       sex = sexRepository.saveAndFlush(sex);
       typename.setName("นาย");
       typename = typenameRepository.saveAndFlush(typename);
       question.setName("บ้านเกิดคุณอยู่ที่ไหน");
       question = questionRepository.saveAndFlush(question);

       final Set<ConstraintViolation<UserRegister>> result = validator.validate(userregister);

       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       final ConstraintViolation<UserRegister> v = result.iterator().next();
       assertEquals("must not be null", v.getMessage());
       assertEquals("password", v.getPropertyPath().toString());
   }

   @Test
   void B6001520_testPasswordMustNotMAXSIZE30() {
       final UserRegister userregister = new UserRegister();
       userregister.setName("kittichai jitjaroen");
       userregister.setTel("0901316436");
       userregister.setEmail("mosmos11289@gmail.com");
       userregister.setAnswer("Chanthaburi");
       userregister.setPassword("012345678901234567890123456789012345");
       Sex sex = new Sex();
       Question question = new Question();
       TypeName typename = new TypeName();
       sex.setName("ชาย");
       sex = sexRepository.saveAndFlush(sex);
       typename.setName("นาย");
       typename = typenameRepository.saveAndFlush(typename);
       question.setName("บ้านเกิดคุณอยู่ที่ไหน");
       question = questionRepository.saveAndFlush(question);

       final Set<ConstraintViolation<UserRegister>> result = validator.validate(userregister);

       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       final ConstraintViolation<UserRegister> v = result.iterator().next();
       assertEquals("password max30", v.getMessage());
       assertEquals("password", v.getPropertyPath().toString());
   }

   @Test
   void B6001520_testPasswordMustNotMINSIZE8() {
       final UserRegister userregister = new UserRegister();
       userregister.setName("kittichai jitjaroen");
       userregister.setTel("0901316436");
       userregister.setEmail("mosmos11289@gmail.com");
       userregister.setAnswer("Chanthaburi");
       userregister.setPassword("1234567");
       Sex sex = new Sex();
       Question question = new Question();
       TypeName typename = new TypeName();
       sex.setName("ชาย");
       sex = sexRepository.saveAndFlush(sex);
       typename.setName("นาย");
       typename = typenameRepository.saveAndFlush(typename);
       question.setName("บ้านเกิดคุณอยู่ที่ไหน");
       question = questionRepository.saveAndFlush(question);

       final Set<ConstraintViolation<UserRegister>> result = validator.validate(userregister);

       // result ต้องมี error 1 ค่าเท่านั้น
       assertEquals(1, result.size());

       // error message ตรงชนิด และถูก field
       final ConstraintViolation<UserRegister> v = result.iterator().next();
       assertEquals("password min8", v.getMessage());
       assertEquals("password", v.getPropertyPath().toString());
   }

}
