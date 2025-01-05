package patterns.decorator;

public class ExtraOlives extends ToppingDecorator {
    BasePizza pizza;

    public ExtraOlives(BasePizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public int cost() {
        return pizza.cost() + 25;
    }
}
