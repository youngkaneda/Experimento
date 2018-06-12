/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package experimento.tcc;

import ifpb.gpes.Call;
import ifpb.gpes.ExportManager;
import ifpb.gpes.Parse;
import ifpb.gpes.Project;
import ifpb.gpes.jdt.ParseStrategies;
import ifpb.gpes.study.Study;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author juan
 */
public class ClientTCC {

    public static void main(String[] args) {
        Project project = Project
                .root("../estudo-de-casos/jgrapht-0.8.1/")
                .path("src/")
                .sources("src/")
                .filter(".java");

        Study.of(project).with(Parse.with(ParseStrategies.JDT))
                .analysis(new ExportQualifier())
                .execute();
    }

    static class ExportQualifier implements ExportManager {

        private final List<String> types = new ArrayList<>();
        private List<Output> outputs = new ArrayList<>();
        private Set<String> classes = new HashSet();

        {
            types.add("java.util.Set");
            types.add("java.util.List");
            types.add("java.util.Map");
        }

        @Override
        public void export(List<Call> elements) {
            StringBuilder sb = new StringBuilder("class;collection;methods;adicao;remocao;busca;acesso;edicao").append("\n");
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream("exp.csv");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            //
            for (Call call : elements) {
                try {
                    if (!call.getClassType().startsWith("java.util.")) {
                        continue;
                    }
                    Output output = null;
                    for (String type : types) {
                        if (Class.forName(type).isAssignableFrom(Class.forName(call.getClassType()))) {
                            output = new Output(type,
                                    call.getMethodName().split("\\[")[0],
                                    Qualifier.type(call.getMethodName()),
                                    call.getCalledInClass());
                        }
                    }
                    if (output != null) {
                        outputs.add(output);
                    }
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            //populando set com os nomes de todas as classes presentes em outputs
            outputs.stream().map(Output::getTargetClass).forEach(classes::add);
            long[] results = new long[6];
            classes.stream().forEach((c) -> {
                types.stream().forEach((type) -> {
                    List<Output> filtered = outputs.stream().filter(o -> o.getTargetClass().equals(c)
                            && !o.getCategory().isEmpty()
                            && o.getCollectionType().equals(type)).collect(Collectors.toList());
                    results[0] = filtered.stream().filter(o -> o.getCategory().equals("adicao")).count();
                    results[1] = filtered.stream().filter(o -> o.getCategory().equals("remocao")).count();
                    results[2] = filtered.stream().filter(o -> o.getCategory().equals("busca")).count();
                    results[3] = filtered.stream().filter(o -> o.getCategory().equals("acesso")).count();
                    results[4] = filtered.stream().filter(o -> o.getCategory().equals("edicao")).count();
                    results[5] = filtered.size();
                    String message = String.format("%s;%s;%d;%d;%d;%d;%d;%d",
                            c, type, results[5], results[0], results[1], results[2], results[3], results[4]);
                    //
//                    int sum = 0;
//                    for (int i = 0; i < 5; i++) {
//                        sum += results[i];
//                    }
                    //
//                    if (sum != 0) {
                        sb.append(message).append("\n");
//                    }
                });
            });
            try {
                fos.write(sb.toString().getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println(sb.toString());
            outputs.forEach(System.out::println);
        }

    }

    static class Qualifier {

        private static final List<String> adicao = Arrays.asList("add", "addAll", "set", "put", "putAll");
        private static final List<String> remocao = Arrays.asList("clear", "remove", "removeAll", "retainAll");
        private static final List<String> busca = Arrays.asList("contains", "containsAll", "get", "indexOf", "lastIndexOf", "listIterator", "subList", "size");
        private static final List<String> edicao = Arrays.asList("sort", "replaceAll");
        private static final List<String> acesso = Arrays.asList("equals", "isEmpty", "iterator", "hashcode", "listIterator", "toArray");

        public static String type(String method) {
            if (adicao.contains(method.split("\\[")[0])) {
                return "adicao";
            }
            if (edicao.contains(method.split("\\[")[0])) {
                return "edicao";
            }
            if (remocao.contains(method.split("\\[")[0])) {
                return "remocao";
            }
            if (busca.contains(method.split("\\[")[0])) {
                return "busca";
            }
            if (acesso.contains(method.split("\\[")[0])) {
                return "acesso";
            }
            return "";
        }
    }
}
