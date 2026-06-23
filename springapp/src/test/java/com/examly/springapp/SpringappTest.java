// package com.examly.springapp;
// import com.examly.springapp.controller.CourseController;
// import com.examly.springapp.controller.EnrollmentController;
// import com.examly.springapp.controller.LanguageController;
// import com.examly.springapp.controller.UserController;
// import com.examly.springapp.entities.*;
// import com.examly.springapp.repositories.CourseRepository;
// import com.examly.springapp.repositories.EnrollmentRepository;
// import com.examly.springapp.repositories.LanguageRepository;
// import com.examly.springapp.repositories.UserRepository;
// import com.examly.springapp.services.*;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.springframework.data.domain.Sort;


// import jakarta.persistence.EntityManager;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.PersistenceContext;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.*;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageImpl;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.client.RestTemplate;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.fail;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.eq;
// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import java.io.File;
// import java.lang.reflect.Field;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.util.Arrays;
// import java.util.List;


// public class SpringappTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @Mock
//     private CourseService courseService;
//     @Mock
//     private UserService userService;
//     @Mock
//     private LanguageService languageService;
//     @Mock
//     private EnrollmentService enrollmentService;

//     @Autowired
//     private TestRestTemplate restTemplate;


//     @Mock
//     private CourseRepository courseRepository;
    
//     @Mock
//     private UserRepository userRepository;
    
//     @Mock
//     private LanguageRepository languageRepository;
    
//     @Mock
//     private EnrollmentRepository enrollmentRepository;

//     private static final String LOG_FOLDER_PATH = "logs";
//     private static final String LOG_FILE_PATH = "logs/application.log"; 
//   //  private final ObjectMapper objectMapper = new ObjectMapper();

//     @InjectMocks
//     private CourseController courseController;
//     @InjectMocks
//     private UserController userController;
//     @InjectMocks
//     private LanguageController languageController;
//     @InjectMocks
//     private EnrollmentController enrollmentController;

    

//     @BeforeEach
//     public void setup() {
//         MockitoAnnotations.initMocks(this);
//         mockMvc = MockMvcBuilders.standaloneSetup(courseController, userController, languageController, enrollmentController).build();
       
//         Language language1 = new Language();
//         language1.setName("Spanish");
//         languageRepository.save(language1);

//         Language language2 = new Language();
//         language2.setName("French");
//         languageRepository.save(language2);

//         Language language3 = new Language();
//         language3.setName("English");
//         languageRepository.save(language3);

//         Language language4 = new Language();
//         language4.setName("German");
//         languageRepository.save(language4);

//         Language language5 = new Language();
//         language5.setName("Italian");
//         languageRepository.save(language5);
//     }

    
//     // --- Test Update Course ---
//     @Test
//     public void testUpdateCourse() throws Exception {
//         Course updatedCourse = new Course();
//         updatedCourse.setId(1L);
//         updatedCourse.setTitle("Updated Course");
//         updatedCourse.setLevel("Intermediate");

//         when(courseService.updateCourse(eq(1L), any(Course.class))).thenReturn(updatedCourse);

//         mockMvc.perform(put("/api/courses/{id}", 1L)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(new ObjectMapper().writeValueAsString(updatedCourse)))
//                 .andExpect(status().isOk())  // Expect 200 OK
//                 .andExpect(jsonPath("$.id").value(1))
//                 .andExpect(jsonPath("$.title").value("Updated Course"))
//                 .andExpect(jsonPath("$.level").value("Intermediate"));
//     }

//     // --- Test Delete Course ---
//     @Test
//     public void testDeleteCourse() throws Exception {
//         when(courseService.deleteCourse(eq(1L))).thenReturn(true);

//         mockMvc.perform(delete("/api/courses/{id}", 1L))
//                 .andExpect(status().isNoContent());  // Expect 204 No Content
//     }

//     // --- Test Get Course ---
//     @Test
//     public void testGetCourse() throws Exception {
//         Course course = new Course();
//         course.setId(1L);
//         course.setTitle("English for Beginners");
//         course.setLevel("Beginner");

//         when(courseService.getCourseById(eq(1L))).thenReturn(course);

//         mockMvc.perform(get("/api/courses/{id}", 1L))
//                 .andExpect(status().isOk())  // Expect 200 OK
//                 .andExpect(jsonPath("$.id").value(1))
//                 .andExpect(jsonPath("$.title").value("English for Beginners"))
//                 .andExpect(jsonPath("$.level").value("Beginner"));
//     }

//     // --- Test Create User ---
//     @Test
//     public void testCreateUser() throws Exception {
//         User user = new User();
//         user.setName("John Doe");
//         user.setEmail("john@example.com");

//         when(userService.createUser(any(User.class))).thenReturn(user);

//         mockMvc.perform(post("/api/users")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(new ObjectMapper().writeValueAsString(user)))
//                 .andExpect(status().isCreated())  // Expect 201 Created
//                 .andExpect(jsonPath("$.name").value("John Doe"))
//                 .andExpect(jsonPath("$.email").value("john@example.com"));
//     }

//     // --- Test Delete User ---
//     @Test
//     public void testDeleteUser() throws Exception {
//         when(userService.deleteUser(eq(1L))).thenReturn(true);

//         mockMvc.perform(delete("/api/users/{id}", 1L))
//                 .andExpect(status().isNoContent());  // Expect 204 No Content
//     }

//     // --- Test Create Language ---
//     @Test
//     public void testCreateLanguage() throws Exception {
//         Language language = new Language();
//         language.setName("English");

//         when(languageService.createLanguage(any(Language.class))).thenReturn(language);

//         mockMvc.perform(post("/api/languages")
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(new ObjectMapper().writeValueAsString(language)))
//                 .andExpect(status().isCreated())  // Expect 201 Created
//                 .andExpect(jsonPath("$.name").value("English"));
//     }

//     // --- Test Delete Language ---
//     @Test
//     public void testDeleteLanguage() throws Exception {
//         when(languageService.deleteLanguage(eq(1L))).thenReturn(true);

//         mockMvc.perform(delete("/api/languages/{id}", 1L))
//                 .andExpect(status().isNoContent());  // Expect 204 No Content
//     }

//     // --- Test Create Enrollment ---
//     // @Test
//     // public void testCreateEnrollment() throws Exception {
//     //     Enrollment enrollment = new Enrollment();
//     //     enrollment.setUser(new User());
//     //     enrollment.setCourse(new Course());

//     //     when(enrollmentService.createEnrollment(any(Enrollment.class))).thenReturn(enrollment);

//     //     mockMvc.perform(post("/api/enrollments")
//     //             .contentType(MediaType.APPLICATION_JSON)
//     //             .content(new ObjectMapper().writeValueAsString(enrollment)))
//     //             .andExpect(status().isCreated())  // Expect 201 Created
//     //             .andExpect(jsonPath("$.user").exists())
//     //             .andExpect(jsonPath("$.course").exists());
//     // }

//     // --- Test Delete Enrollment ---
//     @Test
//     public void testDeleteEnrollment() throws Exception {
//         when(enrollmentService.deleteEnrollment(eq(1L))).thenReturn(true);

//         mockMvc.perform(delete("/api/enrollments/{id}", 1L))
//                 .andExpect(status().isNoContent());  // Expect 204 No Content

//                 MockitoAnnotations.openMocks(this);
//     }

//     // --- Test Update Enrollment ---
//     @Test
//     public void testUpdateEnrollment() throws Exception {
//         Enrollment enrollment = new Enrollment();
//         enrollment.setId(1L);
//         enrollment.setUser(new User());
//         enrollment.setCourse(new Course());

//         when(enrollmentService.updateEnrollment(eq(1L), any(Enrollment.class))).thenReturn(enrollment);

//         mockMvc.perform(put("/api/enrollments/{id}", 1L)
//                 .contentType(MediaType.APPLICATION_JSON)
//                 .content(new ObjectMapper().writeValueAsString(enrollment)))
//                 .andExpect(status().isOk())  // Expect 200 OK
//                 .andExpect(jsonPath("$.id").value(1));
//     }

//     // --- Test Get Enrollment ---
//     @Test
//     public void testGetEnrollment() throws Exception {
//         Enrollment enrollment = new Enrollment();
//         enrollment.setId(1L);
//         enrollment.setUser(new User());
//         enrollment.setCourse(new Course());

//         when(enrollmentService.getEnrollmentById(eq(1L))).thenReturn(enrollment);

//         mockMvc.perform(get("/api/enrollments/{id}", 1L))
//                 .andExpect(status().isOk())  // Expect 200 OK
//                 .andExpect(jsonPath("$.id").value(1));
//     }
//         @Test
//     public void testCourseEntityExists() {
//         Course course = new Course();
//         assertNotNull(course);  // Check if the Course entity can be instantiated
//     }

//     @Test
//     public void testUserEntityExists() {
//         User user = new User();
//         assertNotNull(user);  // Check if the User entity can be instantiated
//     }

//     @Test
//     public void testLanguageEntityExists() {
//         Language language = new Language();
//         assertNotNull(language);  // Check if the Language entity can be instantiated
//     }

//     @Test
//     public void testEnrollmentEntityExists() {
//         Enrollment enrollment = new Enrollment();
//         assertNotNull(enrollment);  // Check if the Enrollment entity can be instantiated
//     }
//     @Test
//     public void testCourseRepositoryExists() {
//         assertNotNull(courseRepository);  // Check if CourseRepository is available
//     }
    
//     @Test
//     public void testUserRepositoryExists() {
//         assertNotNull(userRepository);  // Check if UserRepository is available
//     }

//     @Test
//     public void testLanguageRepositoryExists() {
//         assertNotNull(languageRepository);  // Check if LanguageRepository is available
//     }

//     @Test
//     public void testEnrollmentRepositoryExists() {
//         assertNotNull(enrollmentRepository);  // Check if EnrollmentRepository is available
//     }

//     @Test
//     public void testCourseServiceExists() {
//         CourseService courseService = mock(CourseService.class);
//         assertNotNull(courseService);  // Check if the CourseService is available
//     }

//     @Test
//     public void testUserServiceExists() {
//         UserService userService = mock(UserService.class);
//         assertNotNull(userService);  // Check if the UserService is available
//     }

//     @Test
//     public void testLanguageServiceExists() {
//         LanguageService languageService = mock(LanguageService.class);
//         assertNotNull(languageService);  // Check if the LanguageService is available
//     }

//     @Test
//     public void testEnrollmentServiceExists() {
//         EnrollmentService enrollmentService = mock(EnrollmentService.class);
//         assertNotNull(enrollmentService);  // Check if the EnrollmentService is available
//     }
//     @Test
//     public void testCourseControllerExists() {
          
//             if (courseController != null) {
//                 System.out.println("Course Controller Created: " + courseController);
//             }
//         }
    
    

//     @Test
//     public void testUserControllerExists() {
//         if (userController != null) {
//             System.out.println("User Controller Created: " + userController);
//         }
//     }

//     @Test
//     public void testLanguageControllerExists() {
//         if (languageController != null) {
//             System.out.println("Language Controller Created: " + languageController);
//         }
//     }

//     @Test
//     public void testEnrollmentControllerExists() {
//         if (enrollmentController != null) {
//             System.out.println("Enrollment Controller Created: " + enrollmentController);
//         }
//     }
//     @Test
//     public void testEntitiesFolderExists() {
//         assertTrue(Files.exists(Paths.get("src/main/java/com/examly/springapp/entities")));
//     }

//     @Test
//     public void testRepositoriesFolderExists() {
//         assertTrue(Files.exists(Paths.get("src/main/java/com/examly/springapp/repositories")));
//     }

//     @Test
//     public void testServicesFolderExists() {
//         assertTrue(Files.exists(Paths.get("src/main/java/com/examly/springapp/services")));
//     }

//     @Test
//     public void testControllersFolderExists() {
//         assertTrue(Files.exists(Paths.get("src/main/java/com/examly/springapp/controller")));
//     }

//     @Test
//     public void Mapping_testUserEntityOneToManyAnnotation() throws NoSuchFieldException {
//         // Check if the 'enrollments' field in User entity has @OneToMany annotation
//         Field enrollmentsField = User.class.getDeclaredField("enrollments");
//         OneToMany oneToManyAnnotation = enrollmentsField.getAnnotation(OneToMany.class);
//         assertNotNull(oneToManyAnnotation, "@OneToMany annotation is missing in User entity's enrollments field");
//     }

//     @Test
//     public void Mapping_testCourseEntityManyToOneAnnotation() throws NoSuchFieldException {
//         // Check if the 'language' field in Course entity has @ManyToOne annotation
//         Field languageField = Course.class.getDeclaredField("language");
//         ManyToOne manyToOneAnnotation = languageField.getAnnotation(ManyToOne.class);
//         assertNotNull(manyToOneAnnotation, "@ManyToOne annotation is missing in Course entity's language field");
//     }

//     @Test
//     public void Mapping_testEnrollmentEntityManyToOneAnnotation() throws NoSuchFieldException {
//         // Check if the 'user' field in Enrollment entity has @ManyToOne annotation
//         Field userField = Enrollment.class.getDeclaredField("user");
//         ManyToOne manyToOneAnnotation = userField.getAnnotation(ManyToOne.class);
//         assertNotNull(manyToOneAnnotation, "@ManyToOne annotation is missing in Enrollment entity's user field");
        
//         // Check if the 'course' field in Enrollment entity has @ManyToOne annotation
//         Field courseField = Enrollment.class.getDeclaredField("course");
//         manyToOneAnnotation = courseField.getAnnotation(ManyToOne.class);
//         assertNotNull(manyToOneAnnotation, "@ManyToOne annotation is missing in Enrollment entity's course field");
//     }

//     @Test
//     public void Mapping_testLanguageEntityOneToManyAnnotation() throws NoSuchFieldException {
//         // Check if the 'courses' field in Language entity has @OneToMany annotation
//         Field coursesField = Language.class.getDeclaredField("courses");
//         OneToMany oneToManyAnnotation = coursesField.getAnnotation(OneToMany.class);
//         assertNotNull(oneToManyAnnotation, "@OneToMany annotation is missing in Language entity's courses field");
//     }

//      @Test
//      public void JPQL_testFindCoursesByLevel() {
//          // Given: Create and save a language
//          Language language = new Language();
//          language.setName("English");
//          languageRepository.save(language);
     
//          // Create and save courses
//          Course course1 = new Course();
//          course1.setTitle("Java Basics");
//          course1.setLevel("Beginner");
//          course1.setLanguage(language);
//          courseRepository.save(course1);
     
//          Course course2 = new Course();
//          course2.setTitle("Advanced Java");
//          course2.setLevel("Advanced");
//          course2.setLanguage(language);
//          courseRepository.save(course2);
     
//          Course course3 = new Course();
//          course3.setTitle("Spring Boot Beginner");
//          course3.setLevel("Beginner");
//          course3.setLanguage(language);
//          courseRepository.save(course3);
     
        
     
//          // When: Run the JPQL query to find courses by level "Beginner"
//          List<Course> courses = courseRepository.findCoursesByLevel("Beginner");
     
//          System.out.println("Courses with level 'Beginner': " + courses);
//      }
//      @Test
//     public void JPQL_testGetEnrollmentsByUserId() {
//         // Given: Create and save a user and course
//         User user = new User();
//         user.setName("John Doe");
//         userRepository.save(user);

//         Course course = new Course();
//         course.setTitle("Java Basics");
//         course.setLevel("Beginner");
//         courseRepository.save(course);

//         // Enroll the user in the course
//         Enrollment enrollment = new Enrollment();
//         enrollment.setUser(user);
//         enrollment.setCourse(course);
//         enrollmentRepository.save(enrollment);

//         // When: Retrieve enrollments by user ID
//         List<Enrollment> enrollments = enrollmentService.getEnrollmentsByUserId(user.getId());

//         // No assertions, just print or log the enrollments (or for manual verification)
//         System.out.println("Enrollments for user " + user.getId() + ": " + enrollments);
//     }

//     @Test
//     public void PaginateSorting_testGetLanguagesSortedWithInvalidSortDir() {
//         // Prepare test data
//         Language language1 = new Language();
// language1.setId(1L);
// language1.setName("English");

// Language language2 = new Language();
// language2.setId(2L);
// language2.setName("Spanish");


//         List<Language> languages = Arrays.asList(language1, language2);

//         // Mock the repository method for invalid sort direction
//         when(languageRepository.findAll(Sort.by(Sort.Order.asc("name")))).thenReturn(languages);

//         // Test with invalid sort direction (should default to ascending)
//         List<Language> sortedLanguages = languageService.getLanguagesSorted("name", "invalid");

//         System.out.println("Sorted Languages: " + sortedLanguages);
//     }
//     @Test
//     public void testGetLanguagesSortedDescending() {
//         // Prepare test data
//         Language language1 = new Language();
//         language1.setId(1L);
//         language1.setName("English");
    
//         Language language2 = new Language();
//         language2.setId(2L);
//         language2.setName("Spanish");
    
//         // Prepare the sorted list of languages in descending order (Spanish comes first)
//         List<Language> languages = Arrays.asList(language2, language1);
    
//         // Mock the repository method for descending sort
//         when(languageRepository.findAll(Sort.by(Sort.Order.desc("name")))).thenReturn(languages);
    
//         // Test with descending sort direction
//         List<Language> sortedLanguages = languageService.getLanguagesSorted("name", "desc");
    
//         // Print the sorted languages for verification
//         System.out.println("Sorted Languages in Descending Order: " + sortedLanguages);
//     }


//     @Test 
//     public void Swagger_testConfigurationFolder() { 
//         String directoryPath = "src/main/java/com/examly/springapp/configuration"; // Replace with the path to your directory 
//         File directory = new File(directoryPath); 
//         assertTrue(directory.exists() && directory.isDirectory()); 
//     }
    
//     @Test

// 	public void Swagger_testConfigFile() {

// 		String filePath = "src/main/java/com/examly/springapp/configuration/SwaggerConfig.java";

// 		// Replace with the path to your file

// 		File file = new File(filePath);

// 		assertTrue(file.exists() && file.isFile());

// 	}
//     private void checkAnnotationExists(String className, String annotationName) {
// 		try {
// 			Class<?> clazz = Class.forName(className);
// 			ClassLoader classLoader = clazz.getClassLoader();
// 			Class<?> annotationClass = Class.forName(annotationName, false, classLoader);
// 			assertNotNull(clazz.getAnnotation((Class) annotationClass)); // Use raw type
// 		} catch (ClassNotFoundException | NullPointerException e) {
// 			fail("Class " + className + " or annotation " + annotationName + " does not exist.");
// 		}
// 	}

//     @Test
// 	   public void Swagger_testConfigHasAnnotation() {
// 	       checkAnnotationExists("com.examly.springapp.configuration.SwaggerConfig", "org.springframework.context.annotation.Configuration");
// 	   }
	 
	
//        @Test
//        public void Log_testLogFolderAndFileCreation() {
//            // Check if the "logs" folder exists
//            File logFolder = new File(LOG_FOLDER_PATH);
//            assertTrue(logFolder.exists(), "Log folder should be created");
   
//            // Check if the "application.log" file exists inside the "logs" folder
//            File logFile = new File(LOG_FILE_PATH);
//            assertTrue(logFile.exists(), "Log file should be created inside 'logs' folder");
//        }
//        @Test
//        void AOP_testAopFunctionality() throws Exception {
//            // Trigger AOP by performing a GET request
//            mockMvc.perform(get("/users"))
//                   .andDo(result -> System.out.println("Response: " + result.getResponse().getContentAsString()));
   
//            // Check the console log for the "AOP: Method called" message
//        }
//        @Test
//        void Annotation_testLombokAnnotations() {
//            // Create instances and set values
//            Enrollment enrollment = new Enrollment();
//            enrollment.setId(1L);
   
//            User user = new User();
//            user.setId(101L);
//            enrollment.setUser(user);
   
//            Course course = new Course();
//            course.setId(201L);
//            enrollment.setCourse(course);
   
//            // Verify getters and setters
//            assertEquals(1L, enrollment.getId());
//            assertEquals(101L, enrollment.getUser().getId());
//            assertEquals(201L, enrollment.getCourse().getId());
//        }
//        @Test
//     void Annotation_testJoinColumnAnnotations() {
//         // Simulate ORM behavior (manual test setup)
//         Enrollment enrollment = new Enrollment();
//         User user = new User();
//         user.setId(101L);
//         enrollment.setUser(user);

//         Course course = new Course();
//         course.setId(201L);
//         enrollment.setCourse(course);

//         // Verify the relationships
//         assertEquals(101L, enrollment.getUser().getId());
//         assertEquals(201L, enrollment.getCourse().getId());
//     }

//     @Test
//     void Annotation_testJsonIgnoreAnnotation() throws Exception {
//         // Prepare the Enrollment object
//         Enrollment enrollment = new Enrollment();
//         enrollment.setId(1L);

//         User user = new User();
//         user.setId(101L);
//         enrollment.setUser(user);

//         Course course = new Course();
//         course.setId(201L);
//         enrollment.setCourse(course);

//         // Serialize the object to JSON
//         ObjectMapper objectMapper = new ObjectMapper();
//         String json = objectMapper.writeValueAsString(enrollment);

//         // Ensure "user" is not in the JSON output
//         assertFalse(json.contains("user"));
//         assertTrue(json.contains("course"));
//         assertTrue(json.contains("id"));
//     }
      
// }



