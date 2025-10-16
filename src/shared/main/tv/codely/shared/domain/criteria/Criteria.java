package tv.codely.shared.domain.criteria;

import lombok.Value;

@Value
public class Criteria {
    Filters filters;
    Order order;
    Integer limit;
    Integer offset;
}
