package jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;


@BenchmarkMode(Mode.Throughput)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = {"-Xms2G", "-Xmx2G"}) // Options can be declared here or with the OptionsBuilder. The OptionsBuilder always overrides declared Options
@Warmup(iterations = 2)
@Measurement(iterations = 2)
public class BenchmarkDataStructures {


    @Param({"1000"})
    private int BATCH_SIZE;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(BenchmarkDataStructures.class.getSimpleName())
                .forks(1)
                .jvmArgsAppend("-Xms2G", "-Xmx2G")
                .resultFormat(ResultFormatType.CSV)
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    public void arrayListTest(Blackhole bh) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < BATCH_SIZE; i++) {
            arrayList.add("data");
            bh.consume(arrayList.get(i));
        }
    }

    @Benchmark
    public void linkedListTest(Blackhole bh) {
        LinkedList<String> linkedList = new LinkedList<>();
        for (int i = 0; i < BATCH_SIZE; i++) {
            linkedList.add("data");
            bh.consume(linkedList.get(i));
        }
    }

    @Benchmark
    public void vectorTest(Blackhole bh) {
        Vector<String> vector = new Vector<>();
        for (int i = 0; i < BATCH_SIZE; i++) {
            vector.add("data");
            bh.consume(vector.get(i));
        }
    }

    @Benchmark
    public void stackTest(Blackhole bh) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < BATCH_SIZE; i++) {
            stack.add("data");
            bh.consume(stack.get(i));
        }
    }

    @Benchmark
    public void hashMapTest(Blackhole bh) {
        Map<String, String> stringMap = new HashMap<>();
        for (int i = 0; i < BATCH_SIZE; i++) {
            stringMap.put("key", "value");
            bh.consume(stringMap.get(i));
        }
    }
}
