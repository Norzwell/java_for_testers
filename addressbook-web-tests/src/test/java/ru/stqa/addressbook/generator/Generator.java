package ru.stqa.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;

public class Generator {

    @Parameter(names={"--type", "-t"})
    String type;

    @Parameter(names={"--output", "-o"})
    String output;

    @Parameter(names={"--format", "-f"})
    String format;

    @Parameter(names={"--count", "-n"})
    int count;


    public static void main(String[] args) {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        }
        else if ("address".equals(type)) {
            return generateAddress();
        }
        else {
            throw new IllegalArgumentException("Неивестый тип данных" + type);
        }
    }


    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            result.add(new GroupData().withName(CommonFunctions.randomString(i+5))
                    .withHeader(CommonFunctions.randomString(i+10))
                    .withFooter(CommonFunctions.randomString(i+10)));
        }
        return result;
    }

    private Object generateAddress() {
        return null;
    }


    private void save(Object data) {

    }
}