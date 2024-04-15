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


    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            result.add(new GroupData()
                    .withName(CommonFunctions.randomString(i+10))
                    .withHeader(CommonFunctions.randomString(i+10))
                    .withFooter(CommonFunctions.randomString(i+10)));
        }
        return result;
    }

    private Object generateAddress() {
        var result = new ArrayList<AddressData>();
            for (int i = 0; i < count; i++) {
                result.add(new AddressData()
                        .withFirsName(CommonFunctions.randomString(i+5))
                        .withLastName(CommonFunctions.randomString(i+5))
                        .withAddress(CommonFunctions.randomString(i+5))
                        .withMobile(CommonFunctions.randomString(i+10))
                        .withEmail(CommonFunctions.randomString(i+10))
//                        .withPhoto("src/test/resources/images/avatar.png"))
                        .withPhoto(randomFile("src/test/resources/images")));
            }
        return result;
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
