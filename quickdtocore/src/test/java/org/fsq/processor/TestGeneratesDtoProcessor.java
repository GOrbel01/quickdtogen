package org.fsq.processor;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.CompilationSubject;
import com.google.testing.compile.Compiler;
import com.google.testing.compile.JavaFileObjects;
import org.fsq.processor.GeneratesDtoProcessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.tools.JavaFileObject;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static com.google.testing.compile.Compiler.javac;

public class TestGeneratesDtoProcessor {

    private GeneratesDtoProcessor generatesDtoProcessor;

    private static final String INDENT = "    ";

    private static final String GEN_PATH = "target/generated-test-sources/test-annotations/org/fsq/entity/TestEntityDto.java";

    private File sourceFile;
    private File generatedFile;

    private Map<Integer, String> expectedSourceLines;

    @Before
    public void setup() {
        sourceFile = new File("src/test/java/org/fsq/entity/TestEntity.java");
        generatedFile = new File(GEN_PATH);
        if (generatedFile.exists()) {
            generatedFile.delete();
        }
        expectedSourceLines = buildExpectedSourceLinesMap();
    }

    @Test
    public void testGeneratesDtoProcessor() throws MalformedURLException, IOException {
        generatesDtoProcessor = new GeneratesDtoProcessor();
        JavaFileObject testEntityFile = JavaFileObjects.forResource(sourceFile.toURI().toURL());

        Compilation compilation =
                Compiler.javac()
                        .withProcessors(generatesDtoProcessor)
                        .compile(testEntityFile);
        CompilationSubject.assertThat(compilation).succeeded();
        JavaFileObject jfo = compilation.generatedSourceFiles().get(0);
        Scanner sc = new Scanner(jfo.openInputStream());

        int lineNum = 1;
        Set mapKeys = expectedSourceLines.keySet();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            System.out.println(lineNum + ": " + line);
            if (mapKeys.contains(lineNum)) {
                Assert.assertEquals(expectedSourceLines.get(lineNum), line);
            }
            lineNum++;
        }
        Assert.assertTrue(sourceFile.exists());
    }

    /*
        Indent line to match expected generated file indentation
     */
    private String indentLine(String line, int indentNo) {
        StringBuilder sb = new StringBuilder(line);
        for (int i = 0; i < indentNo; i++) {
            sb.insert(0, INDENT);
        }
        return sb.toString();
    }

    /*
        Provide expected values for some lines of the source file
     */
    private Map<Integer, String> buildExpectedSourceLinesMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "package org.fsq.entity;");
        map.put(3, "import javax.annotation.processing.Generated;");
        map.put(13, "public final class TestEntityDto {");
        map.put(16, indentLine("private java.lang.Integer testNumber;", 1));
        map.put(18, indentLine("private org.fsq.entity.TestField testField;", 1));
        map.put(23, indentLine("public java.lang.String getTestName() {", 1));
        map.put(24, indentLine("return this.testName;", 2));
        map.put(25, indentLine("}", 1));
        map.put(51, indentLine("public java.util.List<java.lang.String> getList() {", 1));
        map.put(59, "}");
        return map;
    }
}
