package tv.codely.shared.domain.criteria;

import lombok.Value;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Value
public class Filters {
    List<Filter> filters;

    public static Filters fromValues(List<Map<String, String>> filters) {
        return new Filters(filters.stream().map(Filter::fromValues).collect(Collectors.toList()));
    }
}
