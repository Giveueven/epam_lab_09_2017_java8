package lambda.part1.exercise;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import data.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Lambdas01Exercise {

    @Test
    public void sortPersonsByAge() {
        Person[] persons = {
                new Person("name 3", "lastName 3", 20),
                new Person("name 1", "lastName 2", 40),
                new Person("name 2", "lastName 1", 30)
        };

        Arrays.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        assertArrayEquals(new Person[]{
                new Person("name 3", "lastName 3", 20),
                new Person("name 2", "lastName 1", 30),
                new Person("name 1", "lastName 2", 40),
        }, persons);
    }

    @Test
    public void findFirstWithAge30() {
        List<Person> persons = ImmutableList.of(
                new Person("name 3", "lastName 3", 20),
                new Person("name 1", "lastName 2", 30),
                new Person("name 2", "lastName 1", 30)
        );

        Person person = null;

        for (Person p : persons) {
            if (p.getAge() == 30) {
                person = p;
                break;
            }
        }

        if (person != null) {
            person.print();
        }


        assertEquals(person, new Person("name 1", "lastName 2", 30));
    }

    @Test
    public void findFirstWithAge20() {
        List<Person> persons = ImmutableList.of(
                new Person("name 3", "lastName 3", 30),
                new Person("name 1", "lastName 2", 20),
                new Person("name 2", "lastName 1", 20)
        );

        Person person = null;

        final Optional<Person> personOptional =
                FluentIterable.from(persons)
                        .firstMatch(new Predicate<Person>() {
                            @Override
                            public boolean apply(Person p) {
                                return p.getAge() == 20;
                            }
                        });

        if (personOptional.isPresent()) {
            personOptional.get().print();
            assertNotNull(personOptional.get());
            assertEquals(new Person("name 1", "lastName 2", 20), personOptional.get());
        }
    }
}
