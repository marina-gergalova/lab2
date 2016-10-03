package lab2;

/**
 * Created by developer-kc3e on 27.9.16.
 */
// Импортируются классы, используемые в приложении

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
// Главный класс приложения, он же класс фрейма
public class MainFrame extends JFrame {
    // Размеры окна приложения в виде констант
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;
    // Текстовые поля для считывания значений переменных,
// как компоненты, совместно используемые в различных методах
    private JTextField textFieldX;
    private JTextField textFieldY;
    // Текстовое поле для отображения результата,
// как компонент, совместно используемый в различных методах
    private JTextField textFieldResult;
    private JTextField mem1FieldResult;
    private JTextField mem2FieldResult;
    private JTextField mem3FieldResult;
    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons1 = new ButtonGroup();
    private ButtonGroup memoryRadioButtons = new ButtonGroup();
    // Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox();
    private Box hboxMemType = Box.createHorizontalBox();

    private int formulaId = 1;
    private int memoryId = 1;

    // Конструктор класса
    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();

// Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH) / 2,
                (kit.getScreenSize().height - HEIGHT) / 2);

        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons1.setSelected(
                radioButtons1.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.YELLOW));


        hboxMemType.add(Box.createHorizontalGlue());
        addMemoryRadioButton("mem1", 1);
        addMemoryRadioButton("mem2", 2);
        addMemoryRadioButton("mem3", 3);
        memoryRadioButtons.setSelected(memoryRadioButtons.getElements().nextElement().getModel(), true);
        hboxMemType.add(Box.createHorizontalGlue());
        //    hboxMemType.setBorder(BorderFactory.createLineBorder(Color.BLACK));


// Создать область с полями ввода для X и Y
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(
                BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForY);
        Box.createHorizontalBox();
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalGlue());

// Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");


//labelResult = new JLabel("0");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        mem1FieldResult = new JTextField("0", 5);
        mem1FieldResult.setMaximumSize(mem1FieldResult.getPreferredSize());
        mem2FieldResult = new JTextField("0", 5);
        mem2FieldResult.setMaximumSize(mem2FieldResult.getPreferredSize());
        mem3FieldResult = new JTextField("0", 5);
        mem3FieldResult.setMaximumSize(mem3FieldResult.getPreferredSize());
        Box hboxMemResult = Box.createHorizontalBox();
        hboxMemResult.add(Box.createHorizontalGlue());
        hboxMemResult.add(mem1FieldResult);
        hboxMemResult.add(Box.createHorizontalStrut(5));
        hboxMemResult.add(mem2FieldResult);
        hboxMemResult.add(Box.createHorizontalStrut(7));
        hboxMemResult.add(mem3FieldResult);
        hboxMemResult.add(Box.createHorizontalGlue());
        // hboxMemResult.setBorder(BorderFactory.createLineBorder(Color.CYAN));
// Создать область для кнопок

        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Box.createHorizontalBox();
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double result;
                    if (formulaId == 1)
                        result = calculate1(x, y);
                    else
                        result = calculate2(x, y);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonMPlus = new JButton("M+");
        buttonMPlus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Box.createHorizontalBox();
                    Double result;
                    if (memoryId == 1) {
                        result = Double.parseDouble(mem1FieldResult.getText()) + Double.parseDouble(textFieldResult.getText());
                        mem1FieldResult.setText(result.toString());
                    } else if (memoryId == 2) {
                        result = Double.parseDouble(mem2FieldResult.getText()) + Double.parseDouble(textFieldResult.getText());
                        mem2FieldResult.setText(result.toString());
                    } else {
                        result = Double.parseDouble(mem3FieldResult.getText()) + Double.parseDouble(textFieldResult.getText());
                        mem3FieldResult.setText(result.toString());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldResult.setText("0");
            }
        });

        JButton buttonMClearReset = new JButton("МС");
        buttonMClearReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (memoryId == 1) {
                    mem1FieldResult.setText("0");
                } else if (memoryId == 2) {
                    mem2FieldResult.setText("0");
                } else {
                    mem3FieldResult.setText("0");
                }
            }
        });
///////////BUTTONS GROUP1
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));

///////////BUTTONS GROUP2
        Box hboxCalculateButtons = Box.createHorizontalBox();
        hboxCalculateButtons.add(Box.createVerticalGlue());
        hboxCalculateButtons.add(Box.createHorizontalGlue());
        hboxCalculateButtons.add(buttonMPlus);
        hboxCalculateButtons.add(Box.createHorizontalStrut(30));
        hboxCalculateButtons.add(buttonMClearReset);
        hboxCalculateButtons.add(Box.createHorizontalGlue());
        hboxCalculateButtons.add(Box.createVerticalGlue());
        hboxCalculateButtons.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));


        Box bigBoxForGleb = Box.createVerticalBox();
        bigBoxForGleb.add(hboxMemType);
        bigBoxForGleb.add(hboxMemResult);

// Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(bigBoxForGleb);
        contentBox.add(hboxCalculateButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    // Главный метод класса
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Формула No1 для рассчѐта
    public Double calculate1(Double x, Double y) {
        return x * x + y * y;
    }

    // Формула No2 для рассчѐта
    public Double calculate2(Double x, Double y) {
        return x * x * x + 1 / y;
    }

    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
            }
        });
        radioButtons1.add(button);
        hboxFormulaType.add(button);

    }

    private void addMemoryRadioButton(String buttonName, final int memoryId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.memoryId = memoryId;
            }
        });
        memoryRadioButtons.add(button);
        hboxMemType.add(button);
    }
}
