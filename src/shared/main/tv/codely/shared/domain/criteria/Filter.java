package tv.codely.shared.domain.criteria;

import lombok.Value;

import java.util.Map;

@Value
public class Filter {
    FilterField field;
    FilterOperator operator;
    FilterValue value;

    public static Filter fromValues(Map<String, String> values) {
        return new Filter(
            new FilterField(values.get("field")),
            FilterOperator.fromValue(values.get("operator")),
            new FilterValue(values.get("value"))
        );
    }
}
