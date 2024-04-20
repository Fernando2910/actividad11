import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Interfaz Shape
interface Shape {
    double getArea();
    double getPerimeter();
}

// Clase Circle que implementa la interfaz Shape
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}

// Clase Triangle que implementa la interfaz Shape
class Triangle implements Shape {
    private double side;

    public Triangle(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return (Math.sqrt(3) / 4) * side * side;
    }

    @Override
    public double getPerimeter() {
        return 3 * side;
    }
}

// Clase Square que implementa la interfaz Shape
class Square implements Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public double getPerimeter() {
        return 4 * side;
    }
}

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora de Formas");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        JLabel labelShape = new JLabel("Seleccione la figura:");
        JLabel labelProperty = new JLabel("Seleccione la propiedad:");
        JLabel resultLabel = new JLabel("");
        JComboBox<String> shapeComboBox = new JComboBox<>(new String[]{"Círculo", "Triángulo equilatero", "Cuadrado"});
        JComboBox<String> propertyComboBox = new JComboBox<>(new String[]{"Área", "Perímetro"});
        JTextField inputField = new JTextField();
        inputPanel.add(labelShape);
        inputPanel.add(shapeComboBox);
        inputPanel.add(labelProperty);
        inputPanel.add(propertyComboBox);
        inputPanel.add(new JLabel("Medida:"));
        inputPanel.add(inputField);

        JPanel buttonPanel = new JPanel();
        JButton calculateButton = new JButton("Calcular");
        buttonPanel.add(calculateButton);

        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(resultLabel, BorderLayout.NORTH);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String shape = (String) shapeComboBox.getSelectedItem();
                String property = (String) propertyComboBox.getSelectedItem();

                double result = 0;

                try {
                    double value = Double.parseDouble(inputField.getText());
                    switch (shape) {
                        case "Círculo":
                            Circle circle = new Circle(value);
                            if (property.equals("Área")) {
                                result = circle.getArea();
                            } else {
                                result = circle.getPerimeter();
                            }
                            break;
                        case "Triángulo":
                            Triangle triangle = new Triangle(value);
                            if (property.equals("Área")) {
                                result = triangle.getArea();
                            } else {
                                result = triangle.getPerimeter();
                            }
                            break;
                        case "Cuadrado":
                            Square square = new Square(value);
                            if (property.equals("Área")) {
                                result = square.getArea();
                            } else {
                                result = square.getPerimeter();
                            }
                            break;
                    }
                    resultLabel.setText(property + " = " + result);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Por favor ingrese un valor válido.");
                }
            }
        });

        frame.setVisible(true);
    }
}
