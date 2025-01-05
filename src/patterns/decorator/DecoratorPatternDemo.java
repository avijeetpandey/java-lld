package patterns.decorator;

public class DecoratorPatternDemo {
    public static void main(String[] args) {
        BasePizza pizza = new ExtraOlives(new ExtraCheese(new VeggieDelight()));
        System.out.println(pizza.cost());
    }
}
