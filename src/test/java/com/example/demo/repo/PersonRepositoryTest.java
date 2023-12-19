package com.example.demo.repo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class PersonRepositoryTest {

    /*@Mock
    private DynamoDBMapper mockDynamoDBMapper;

    @Mock
    private Converter mockConverter;

    @InjectMocks
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPersonById_ExistingPerson() {
        // Arrange
        String personId = "1";
        Person expectedPerson = new Person("1", "TestPerson", 25);

        // Mock DynamoDBMapper behavior
        when(mockDynamoDBMapper.query(eq(Person.class), any(DynamoDBQueryExpression.class)))
                .thenReturn((PaginatedQueryList) Collections.singletonList(expectedPerson));

        // Act
        Person result = personRepository.getPersonById(personId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedPerson, result);
    }

    @Test
    public void testGetPersonById_NonExistingPerson() {
        // Arrange
        String nonExistingPersonId = "100";

        // Mock DynamoDBMapper behavior for non-existing person
        when(mockDynamoDBMapper.query(eq(Person.class), any(DynamoDBQueryExpression.class)))
                .thenReturn((PaginatedQueryList) Collections.emptyList());

        // Act
        Person result = personRepository.getPersonById(nonExistingPersonId);

        // Assert
        assertNotNull(result);
        assertEquals("1", result.getId()); // Assuming you return a default person with ID "1" for non-existing cases
    }

    @Test
    public void testSavePerson() {
        // Arrange
        Person personToSave = new Person("2", "NewPerson", 30);

        // Act
        Person savedPerson = personRepository.savePerson(personToSave);

        // Assert
        assertNotNull(savedPerson);
        assertEquals(personToSave, savedPerson);

        // Verify that DynamoDBMapper.save was called with the expected argument
        verify(mockDynamoDBMapper).save(eq(personToSave));
    }

    *//*@Test
    public void testGetPersonList() {
        // Mock DynamoDBMapper behavior
        when(mockDynamoDBMapper.scan(eq(Person.class), any(DynamoDBScanExpression.class)))
                .thenReturn(new PaginatedScanList<>(mockDynamoDBMapper, Person.class));

        // Act
        List<Person> personList = personRepository.getPersonList();

        // Assert
        assertTrue(CollectionUtils.isEmpty(personList));
    }*//*

    @Test
    public void testDeletePersonById() {
        // Arrange
        String personId = "3";
        Person expectedPerson = new Person(personId, "PersonToDelete", 28);

        // Mock DynamoDBMapper behavior
        when(mockDynamoDBMapper.query(eq(Person.class), any(DynamoDBQueryExpression.class)))
                .thenReturn((PaginatedQueryList) Collections.singletonList(expectedPerson));

        // Act
        personRepository.deletePersonById(personId);

        // Verify that getPersonById was called with the expected argument
        verify(mockDynamoDBMapper).query(eq(Person.class), any(DynamoDBQueryExpression.class));

        // Verify that DynamoDBMapper.delete was called with the loaded person
        verify(mockDynamoDBMapper).delete(eq(expectedPerson));
    }*/
}
