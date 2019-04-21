package com.sunlong.cloud.eurekaserver;

import com.google.common.collect.Lists;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 需要签名的类
 * @author : shipp
 * @data : 2018/11/12 16:56
 */
public class SignBase implements Serializable {
    private String sign;

    private int value = 0;

    private Timer timer;

    public static void main(String[] args) throws Exception {
//        test();
        SignBase base = new SignBase();
        base.test5();
    }

    public void test5() {
        PathMatcher matcher = new AntPathMatcher();
        String url = "user/orders/list";
        String s1 = "user/**";
        String s2 = "user/orders";
//        System.out.println(matcher.match(s1, url));
//        System.out.println(matcher.match(s2, url));
        System.out.println(url.substring(url.length() - 3));
    }

    public void test4() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE, 30);
//        System.out.println(calendar.getTime());
//        System.out.println(UUID.randomUUID().toString().replace("-",""));
        String JWT_TOKEN_KEY = "8aa2a45c7e594e79bc91b396dbe57b19";
        String uuid = UUID.randomUUID().toString().replace("-","");
        String jwt = Jwts.builder().claim("identity", uuid)
                .setAudience("2").setSubject("jolly travel")
                .setIssuer("jolly travel shanghai").setIssuedAt(new Date()).setExpiration(getOneMonthLater())
                .signWith(SignatureAlgorithm.HS512, JWT_TOKEN_KEY).compact();


        System.out.println(jwt);

        System.out.println(Jwts.parser().setSigningKey(JWT_TOKEN_KEY).isSigned(jwt));
        System.out.println(Jwts.parser().setSigningKey(JWT_TOKEN_KEY).isSigned(jwt + "aa"));

        String ss = "eyJhbGciOiJIUzUxMiJ9.eyJpZGVudGl0eSI6ImE0YzUzOWVhMjliNTQwOWU4ODgzOWM5N2I5MTc5MDgwIiwiYXVkIjoiMiIsInN1YiI6ImpdbGx5IHRyYXZlbCIsImlzcyI6ImpvbGx5IHRyYXZlbCBzaGFuZ2hhaSIsImlhdCI6MTU0Mzg5MTYyMSwiZXhwIjoxNTQ2NDgzNjIxfQ.YJmJeRpHVmALPVCShcp1ump1bzRFS6v4Zq25CBAalBkhgQWrI_NXADpbmcBK_Mi7kvVJVlX-BAJXkqCI4n78Tg";

        Jwts.parser().setSigningKey(JWT_TOKEN_KEY).parseClaimsJwt(ss).getBody();
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_TOKEN_KEY)
                .parseClaimsJws(jwt).getBody();

        System.out.println("uuid = " + uuid);
        System.out.println("audience = " + claims.getAudience());
        System.out.println("subject = " + claims.getSubject());
        System.out.println("Issure = " + claims.getIssuer());
        System.out.println("identity = " + claims.get("identity"));
    }

    private Date getOneMonthLater() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);
        return calendar.getTime();
    }


    public void test3() {
        this.timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                value += 1;
                System.out.println(value);
                if (value == 10) {
                    timer.cancel();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("aaaa");
                }

            }
        };
        timer.schedule(task,1000,1000);

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("aa");
        System.gc();
    }

    public static void test() throws DatatypeConfigurationException {
        List<Integer> lst = new ArrayList<>();
        lst.add(361);
        lst.add(222);
        lst.add(23);
        lst.add(345);
        lst.forEach(System.out :: println);
        lst.forEach((aaa) -> {System.out.println(aaa);});

        new Thread(() -> System.out.println("aaa")).start();

        Runnable run = () -> System.out.println("111");
        run.run();

        Collections.sort(lst, Integer::compareTo);
        Collections.sort(lst, Comparator.naturalOrder());
//        Collections.sort(lst,(s1, s2) -> s1.compareTo(s2));
//        Collections.sort(lst,(Integer s1, Integer s2) -> s1.compareTo(s2));
        Collections.sort(lst, Integer::compareTo);

        lst.forEach(System.out::println);

        List<String> list = new ArrayList<>();
        list.add("4107");
        list.add("369");
        list.add("3835");
        list.add("3118");
        list.add("35991");
        list.add("350");
        list.add("75");
        list.add("70");
        list.add("301");
        list.add("315");
        list.add("23");
        list.add("25");
        Collections.sort(list, (s1, s2) -> s1.length() - s2.length());
        Collections.sort(list, Comparator.comparingInt(String::length));
        Collections.sort(list, Comparator.comparingInt(s -> s.charAt(s.length() - 1)));
//        Collections.sort(list, (s1, s2) -> s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1));
        list.forEach(System.out::println);

    }

    public void test1() {
        List<Person> javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };

        List<Person> phpProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };
        javaProgrammers.forEach(p -> System.out.println(p.firstName));
        javaProgrammers.forEach(p -> System.out.println(p.salary * 1.5));
        phpProgrammers.stream().filter(p -> p.salary > 1400).filter(p -> "female".equals(p.gender)).forEach(p -> System.out.println(p.salary));
        phpProgrammers.forEach(p -> System.out.println(p.salary));
        phpProgrammers.stream().limit(3).forEach(p -> System.out.println(p.salary));
        phpProgrammers.stream().sorted((p1, p2) -> p1.firstName.compareTo(p2.firstName));
//        phpProgrammers.stream().sorted(Comparator.comparing(p -> p.firstName)).forEach(p -> System.out.println(p.firstName));
        phpProgrammers.forEach(person -> System.out.println(person.firstName));

        List<Person> list = phpProgrammers.stream().sorted(Comparator.comparing(Person::getFirstName)).collect(Collectors.toList());
        list.forEach(person -> System.out.println(person.firstName));
//        System.out.println(phpProgrammers.stream().max(Comparator.comparing(Person::getAge)).get().salary);

        phpProgrammers.stream().max(Comparator.comparing(Person::getAge)).get().getSalary();
        System.out.println(phpProgrammers.stream().map(Person::getFirstName).collect(Collectors.joining(";")));
        System.out.println(phpProgrammers.stream().map(Person::getFirstName).collect(Collectors.counting()));
        System.out.println(phpProgrammers.stream().collect(Collectors.toMap(Person::getFirstName, Person::getAge)));

//        System.out.println(phpProgrammers.stream().collect(Collectors.toMap(Person::getFirstName, Function.identity())));
        phpProgrammers.stream().mapToInt(Person::getSalary).summaryStatistics();
        IntSummaryStatistics sum = phpProgrammers.stream().mapToInt(Person::getSalary).summaryStatistics();
//        System.out.println(sum.getMax());
//        System.out.println(sum.getMin());
//        System.out.println(sum.getAverage());
//        System.out.println(sum.getCount());
//        System.out.println(sum.getSum());

//        List<String> proNames = Arrays.asList(new String[]{"Ni","Hao","Lambda"});
//        List<String> lowercaseNames1 = proNames.stream().map(String::toLowerCase).limit(2).collect(Collectors.toList());
//        System.out.println(lowercaseNames1);
//        Stream.generate(Math::random).limit(10).forEach(System.out::println);
//        Stream.of(1,2,3,5,6,7).forEach(System.out::println);
//        Stream.of(1,3,2,4,5,1,4,3,2,1).distinct().forEach(System.out::println);
//        Stream.of(1,3,2,4,5,1,4,3,2,1).distinct().forEach(System.out::println);
//        Stream.of(Arrays.asList(1), Arrays.asList(2, 3, 4), Arrays.asList(4, 2, 3, 4, 5, 6, 7, 87)).flatMap(list -> list.stream()).distinct().forEach(System.out::println);
//        List<Integer> lst = Stream.of(Arrays.asList(1), Arrays.asList(2, 3, 4), Arrays.asList(4, 2, 3, 4, 5, 6, 7, 87)).flatMap(list -> list.stream()).collect(Collectors.toList());
//        System.out.println(lst);
//        Stream.of(Arrays.asList(1), Arrays.asList(2, 3, 4), Arrays.asList(4, 2, 3, 4, 5, 6, 7, 87)).forEach(System.out::println);
//        System.out.println(Stream.of(1,3,2,4,5,1,4,3,2,1).peek(System.out::println).distinct().mapToInt(p -> p).skip(3).sum());
//        Stream.of(1,3,2,4,5,1,4,3,2,1).peek(System.out::println).distinct().mapToInt(p -> p).skip(3).sum();
////        Stream.generate()
//        System.out.println(Stream.of(1,3,2,4,5,1,4,3,2,1).peek(System.out::println));
        Stream.of(1, 2, 3, 4, 5).reduce((acc, item) -> {
                    acc *= item;
                    System.out.println(acc);
                    return acc;
        });
        Random random = new Random();
//        System.out.println(random.nextInt(10) + 1);
//        System.out.println(random.nextInt(10) + 1);
//        System.out.println(random.nextInt(10) + 1);
//        System.out.println(random.nextInt(10) + 1);
//        System.out.println(random.nextInt(10) + 1);
//        System.out.println(random.nextInt(10) + 1);
//        Random random = new Random();

        List<Integer> tt = Stream.generate(() -> random.nextInt(10)).limit(10).distinct().collect(Collectors.toList());
        System.out.println(-18/10);
        tt.stream().filter(a -> a > 3).forEach(System.out::println);
        tt.stream().map(p -> "aa" + p).forEach(System.out::println);

        new Thread(() -> System.out.println("123")).start();
    }

    /**
     * @author shipp
     * @date 2018/11/12 16:58
     * @param 
     * @return java.lang.String
     */
    public String getSign() {
        return sign;
    }

    /**
     * @author shipp
     * @date 2018/11/12 16:58
     * @param sign
     * @return void
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    public class Person {

        private String firstName, lastName, job, gender;
        private int salary, age;

        public Person(String firstName, String lastName, String job,
                      String gender, int age, int salary)       {
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.age = age;
            this.job = job;
            this.salary = salary;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getJob() {
            return job;
        }

        public String getGender() {
            return gender;
        }

        public int getSalary() {
            return salary;
        }

        public int getAge() {
            return age;
        }
    }
}

