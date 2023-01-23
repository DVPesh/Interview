package task1;

public class Test {
    public static void main(String[] args) {
        Person person = Person.builder()
                .setFirstName("Анна")
                .setLastName("Васильева")
                .setMiddleName("Николаевна")
                .build();
        System.out.println(person);
        System.out.println(Person.builder()
                .setFirstName("Николай")
                .setLastName("Кузьмин")
                .setMiddleName("Никифорович")
                .setCountry("Россия")
                .setAddress("г. Новосибирск ул. Ленина дом 2 кв. 5")
                .setAge(50)
                .setGender("мужчина")
                .setPhone("78956")
                .build());
    }
}
