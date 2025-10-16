package tv.codely.shared.domain.criteria;

import lombok.Value;

import java.util.Optional;

@Value
public class Order {
    OrderBy orderBy;
    OrderType orderType;

    public static Order fromValues(String orderBy, String orderType) {
        return Optional.ofNullable(orderBy)
            .map(order -> new Order(new OrderBy(order), OrderType.valueOf(Optional.ofNullable(orderType).orElse("ASC"))))
            .orElseGet(Order::none);
    }

    public static Order none() {
        return new Order(new OrderBy(""), OrderType.NONE);
    }

    public static Order desc(String orderBy) {
        return new Order(new OrderBy(orderBy), OrderType.DESC);
    }

    public static Order asc(String orderBy) {
        return new Order(new OrderBy(orderBy), OrderType.ASC);
    }

    public OrderBy orderBy() {
        return this.orderBy;
    }

    public OrderType orderType() {
        return this.orderType;
    }

    public boolean hasOrder() {
        return !this.orderType.isNone();
    }

    public String serialize() {
        return String.format("%s.%s", this.orderBy.value(), this.orderType.value());
    }
}
