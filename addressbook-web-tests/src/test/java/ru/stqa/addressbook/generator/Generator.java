package ru.stqa.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.AddressData;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.stqa.addressbook.tests.TestBase.randomFile;

public class Generator {

    @Parameter(names={"--type", "-t"})
    String type;
    @Parameter(names={"--output", "-o"})
    String output;
    @Parameter(names={"--format", "-f"})
    String format;
    @Parameter(names={"--count", "-n"})
    int count;


    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
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
            throw new IllegalArgumentException("Неивестый тип данных " + type);
        }
    }

    private Object generateData (Supplier<Object> dataSupplier) {
     return Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
    }


    private Object generateGroups() {
        return generateData(() -> new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(10))
                .withFooter(CommonFunctions.randomString(10)));
    }

    private Object generateAddress() {
        return generateData(() -> new AddressData()
                .withFirsName(CommonFunctions.randomString(15))
                .withLastName(CommonFunctions.randomString(15))
                .withAddress(CommonFunctions.randomString(5))
                .withMobile(CommonFunctions.randomString(10))
                .withEmail(CommonFunctions.randomString(10))
//                        .withPhoto("src/test/resources/images/avatar.png"))
                .withPhoto(randomFile("src/test/resources/images")));

    }


    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(output), data);
        }
        else {
            throw new IllegalArgumentException("Неизвестный формат данных " + format);
        }
    }
}
