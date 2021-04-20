package stream;/* 스트림을 사용하는 이유
* 람다를 활용할 수 있다.(자바8 이후 사용 가능)
* 배열과 컬렉션을 함수형으로 처리 가능
* 병렬처리 가능 - 쓰레드를 이용해 많은 요소를 처리*/


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.lang.Math;


/* stream 사용 흐름
* 1. 생성: 스트림 인스턴스 생성
* 2. 가공: filtering, mapping 등의 intermediate operations 진행
* 3. 결과: terminal operations 진행*/
public class main {

    public static void main(String[] args){

        // Stream.of
        Stream<String> stream = Stream.of("Java8", "Lambdas", "Ain");
        stream.map(String::toUpperCase).forEach(System.out::println);

        // Arrays.stream
        int[] numbers = {1, 2, 3, 4, 5};
        System.out.println(Arrays.stream(numbers).sum());

        // Stream.iterate
        Stream.iterate(0, n -> n+2)
                .limit(5)
                .forEach(System.out::println);

        // random stream of doubles with Stream.generate
        Stream.generate(Math::random)
                .limit(10)
                /*.forEach(s->{System.out.println(s);});*/
                .forEach(System.out::println);

        // stream of 1s with Stream.generate
        IntStream.generate(()->1)
                .limit(5)
                .forEach(System.out::println);

        // 피보나치 수열
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            public int getAsInt(){
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return this.previous;
            }
        };
        IntStream.generate(fib)
                .limit(10)
                .forEach(System.out::println);

        /*// 유니크 단어 카운트
        long uniqueWords = Files.lines(Paths.get("data2.txt"), Charset.defaultCharset(" "))
                .flatmap(line->Arrays.stream(line.split(" ")))
                .distinct()
                .count();*/



        List<Member> list = Arrays.asList(
                new Member(1L, "Tom"),
                new Member(2L, "Smith"),
                new Member(3L, "Tom"));

        boolean unique = list.stream()
                .map(Member::getName)
                .allMatch(new HashSet<>()::add);

        System.out.println(unique);


        // 빈스트림 테스트
        List<String> listTest = Arrays.asList("");
        Stream<String> result;
        result = streamTest(listTest);
        System.out.println(result.);


    }

    // 빈스트림 생성
    public Stream<String> streamOf(List<String> list){
        return list == null || list.isEmpty()
                ? Stream.empty()
                : list.stream();

    }

    // 빈스트림 생성2
    public static Stream<String> streamTest(List<String> list){
        Stream<String> result = Stream.ofNullable(list.stream().collect(Collectors.joining()));
        return result;
    }





    /* 배열 스트림 */
    /* Arrays.stream 메소드를 통해 스트림 형성*/
    public void testScream(){
        String[] arr = new String[]{"a", "b", "c"};
        Stream<String> stream = Arrays.stream(arr);
        Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3); // arr의 1~2 요소 [b, c]
    }

    /* 컬렉션 스트림 */
    /* Collection, List, Set 의 경우 인터페이스에 추가된 default 메소드 stream()으로 스트림 형성 가능*/
    public interface Collection<E> extends  Iterable<E>{
      default Stream<E> stream(){
          return StreamSupport.stream(spliterator(), false);
      }
    }

    /* 컬렉션과 스트림의 공통점과 차이점
    * 공통점
    * - 연속된 요소형식의 값을 저장하는 자료구조 인터페이스를 제공한다.
      - 둘다 순차적으로 요소에 접근한다.

    * 차이점
      컬렉션은
        - 각 계산식을 만날 때마다 데이터가 계산된다.

        - 데이터의 접근, 읽기, 변경, 저장이 주요 관심사이다.

        - 데이터에 접근하는 방법을 직접 작성해야한다.

        - Iterator로 모든요소를 순환해야한다.

        - 메모리에 모든 요소가 올라가 있는 상태에서 요소들을 누적시키며 결과를 계산한다.

        - 메모리 사용량이 늘어난다.

    * 스트림은
        - 최종 연산이 실행 될 때에 데이터가 계산된다.

        - 계산식(람다)을 표현하는 것이 주요 관심사이다.

        - 데이터에 접근하는 방법이 추상화되어있다.

        - 계산식을 미리 적어두고 계산시에 람다식으로 JVM에 넘긴다.

        - 내부에서 요소들을 어떻게 메모리에 올리는 지는 관심사가 아니다.

        - 메모리 사용량이 줄어든다.*/

}
