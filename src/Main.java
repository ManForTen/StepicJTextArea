import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    static JPanel panel = new JPanel ();

    static void addObject(JComponent o, int n, float alignmentX){ // Метод добавления объектов на панель
        o.setAlignmentX(alignmentX); // Горизонтальное выравнивание
        panel.add(o);
        if (n>0)
            panel.add(Box.createVerticalStrut(n)); // Добавляем пустой промежуток после него, если он задан
    };

    public static void main(String[] args) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Менеджер для выравнивания
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,0,10)); // Расширяем границы панели, чтобы элементы не прижимались вплотную к границе
        JFrame frame = new JFrame();
        frame.setResizable(false); // Отключение возможности изменения ее размеров
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Текст");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int width=300, height=300;
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);
        JTextField textBox = new JTextField();
        textBox.setMaximumSize(new Dimension(300,30));
        addObject(textBox,10,Component.CENTER_ALIGNMENT);
        JButton button = new JButton("Записать");
        addObject(button,10,Component.CENTER_ALIGNMENT);

        JTextArea textArea = new JTextArea();
        textArea.setRows(4); // Задаем ему видимое значение строк, иначе после упаковки он сожмется до одной строки
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // Создаем скролл для текстареа
        scroll.setMaximumSize(new Dimension(300,100));
        addObject(scroll,10,Component.CENTER_ALIGNMENT);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText(textArea.getText()+(textArea.getText().length()==0 ? "" : "\n")+textBox.getText()); // Считываем значение из тексбокса в тексареа
            }
        });
        frame.add(panel);
        frame.setVisible(true);
    }
}