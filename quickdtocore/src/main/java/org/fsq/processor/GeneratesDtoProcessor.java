package org.fsq.processor;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.fsq.model.DtoGenModel;
import org.fsq.model.GenItem;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SupportedAnnotationTypes("org.fsq.processor.GeneratesDto")
@SupportedSourceVersion(SourceVersion.RELEASE_9)
public class GeneratesDtoProcessor extends AbstractProcessor {
    private static final String TEMPLATE = "org/fsq/quickdtogen/generatesdto.mustache";
    private static final String COMMENT = "Generated using quickdtogen by FinalSquall";
    private static final String GET = "get";
    private static final String SET = "set";
    private static final String DTO = "Dto";

    public Mustache template;

    public GeneratesDtoProcessor() {
        this.template = createTemplate();
    }

    private Mustache createTemplate() {
        MustacheFactory mustacheFactory = new DefaultMustacheFactory();
        return mustacheFactory.compile(TEMPLATE);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            for (Element element : roundEnv.getElementsAnnotatedWith(annotation)) {
                 try {
                     produceDtoFile(element);
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
            }
        }
        return true;
    }

    protected void produceDtoFile(Element element) throws IOException {
        if (element instanceof TypeElement) {
            TypeElement typeElement = (TypeElement) element;
            DtoGenModel model = createModel(typeElement);
            writeClassToFile(element, model);
        }
    }

    protected void readFromFile(String filePath) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            stream.forEach(System.out::println);
        }
    }

    private void writeClassToFile(Element element, DtoGenModel model) throws IOException {
        Filer filer = processingEnv.getFiler();
        JavaFileObject jfo = filer.createSourceFile(model.getFullClassNameAndPath(), element);
        try (Writer writer = jfo.openWriter()) {
            template.execute(writer, model);
        }
    }

    private DtoGenModel createModel(TypeElement element) {
        String className = element.getSimpleName().toString();

        GeneratesDto generatesDto = element.getAnnotation(GeneratesDto.class);
        String res = generatesDto.dtoPackage();

        String packageName = ((PackageElement) element.getEnclosingElement()).getQualifiedName().toString();
        DtoGenModel model = new DtoGenModel();
        model.setSourceClassName(className);
        model.setTargetClassName(className + DTO);
        model.setGenDate(ZonedDateTime.now());
        model.setComments(COMMENT);
        model.setPackageName(res.equals(GeneratesDto.USE_DEFAULT) ? packageName : res);

        populateGettersAndSetters(element, model);

        return model;
    }

    private void populateGettersAndSetters(TypeElement element, DtoGenModel model) {
        List<GenItem> genItems = new ArrayList<>();
        for (Element field : getEntityFields(element)) {
            TypeMirror te = field.asType();
            genItems.add(new GenItem(field.getSimpleName().toString(),
                    applyGetterOrSetterMorph(field.getSimpleName().toString(), GET),
                    applyGetterOrSetterMorph(field.getSimpleName().toString(), SET),
                    te.toString()));
        }
        model.setItems(genItems);
    }

    private String applyGetterOrSetterMorph(String item, String type) {
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        sb.append(item.substring(0, 1).toUpperCase());
        sb.append(item.substring(1));
        String res = sb.toString();
        return res;
    }

    private List<Element> getEntityFields(TypeElement typeElement) {
        return processingEnv.getElementUtils().getAllMembers(typeElement)
                .stream()
                .filter(element -> element.getKind() == ElementKind.FIELD)
                .collect(Collectors.toList());
    }
}