package fr.weit.sandbox;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class MiscSandbox {
    @Test
    public void test() {
        List<Server> servers = l(Server.builder()
                .name("one")
                .metrics(
                        l(
                                Metric.builder().name("M68").dates(l("2017-01-01", "2017-01-02", "2017-01-04", "2017-01-05")).build(),
                                Metric.builder().name("M69").dates(l("2017-01-01", "2017-01-02")).build(),
                                Metric.builder().name("M83").dates(l("2017-01-01", "2017-01-02", "2017-01-04", "2017-01-05")).build(),
                                Metric.builder().name("M16").dates(l("2017-01-01", "2017-01-02")).build()
                        )
                )
                .build(), Server.builder()
                .name("two")
                .metrics(
                        l(
                                Metric.builder().name("M68").dates(l("2017-01-01", "2017-01-02", "2017-01-04", "2017-01-05")).build(),
                                Metric.builder().name("M69").dates(l("2017-01-01", "2017-01-02")).build(),
                                Metric.builder().name("M83").dates(l("2017-01-01", "2017-01-02", "2017-01-04", "2017-01-05")).build(),
                                Metric.builder().name("M16").dates(l("2017-01-01", "2017-01-02")).build()
                        )
                )
                .build());

        Map<String, Server> serverByName = servers.stream().collect(Collectors.toMap((s) -> s.getName(), s -> s));
        Map<String, List<Metric>> metricsByServerName = servers.stream().collect(Collectors.toMap((s) -> s.getName(), s -> s.getMetrics()));
        Map<String, List<String>> datesByServerName = servers.stream().collect(Collectors.toMap((s) -> s.getName(), s -> s.getMetrics().stream().flatMap(m -> m.getDates().stream()).distinct().collect(Collectors.toList())));

        log.debug("servers : {}", servers);
        log.debug("serverByName : {}", serverByName);
        log.debug("metricsByServerName : {}", metricsByServerName);
        log.debug("datesByServerName : {}", datesByServerName);
    }

    private <T> List<T> l(T... elts) {return Arrays.asList(elts);}

    @Data
    @Builder
    public static class Server {
        String name;
        List<Metric> metrics;

    }

    @Data
    @Builder
    public static class Metric {
        String name;
        List<String> dates;
    }
}
