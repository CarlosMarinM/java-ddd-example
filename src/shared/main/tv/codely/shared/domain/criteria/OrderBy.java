package tv.codely.shared.domain.criteria;

import tv.codely.shared.domain.ValueObject;

public class OrderBy extends ValueObject<String> {
    public OrderBy(String value) {
        super(value);
    }
}
